package com.apcs.disunity.app.network;

import com.apcs.disunity.app.network.packet.SyncHandler;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

/// a launcher that allows the game to be synced across network
public class MultiplayerLauncher implements Closeable {

    public final Consumer<Boolean> initNodes;
    private SyncHandler handler;

    public MultiplayerLauncher(Consumer<Boolean> initNodes) {
        this.initNodes = initNodes;
    }

    

    public boolean lauch() throws IOException {
        String input = JOptionPane.showInputDialog("DISUNITY STARTUP WIZARD\n\nWould you like to start or join a session? (s/j)");
        if (input == null) System.exit(0);
        boolean b = input.startsWith("s") || input.startsWith("S");
        if (b) {
            launchServer();
        } else {
            launchClient();
        }
        return b;
    }

    public void launchClient() {
        while (true) {
            String address = JOptionPane.showInputDialog("DISUNITY STARTUP WIZARD\n\nEnter the Server address. Leave blank for your local Server instance.");
            if (address == null) System.exit(0);
            String port = JOptionPane.showInputDialog("DISUNITY STARTUP WIZARD\n\nEnter the Server port.");
            if (port == null) System.exit(0);
            if (address.isBlank()) address = "0:0:0:0:0:0:0:0";
            try {
                Field instanceField = SyncHandler.class.getDeclaredField("instance");
                instanceField.setAccessible(true);
                instanceField.set(null, null);
                handler = new ClientSideSyncHandler(address, (int) Integer.parseInt(port));
                break;
            } catch (IOException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ioe) {
                JOptionPane.showMessageDialog(null, "DISUNITY STARTUP WIZARD\nCould not connect to this Server");
            }
            
        }
        initNodes.accept(false);
        handler.start();
    }

    public void launchServer() throws IOException {
        HostSideSyncHandler host = new HostSideSyncHandler();
        handler = host;

        String lastLine = null;
        Process process = null;

        boolean forward = JOptionPane.showInputDialog("DISUNITY STARTUP WIZARD\n\nWould you like to expose to the internet in addition to LAN?\n Doing so requires bash and ssh, but allows players on different networks to join. (y/n)").startsWith("y");
        if (forward) {
            int localPort = host.getPort();
            process = new ProcessBuilder("bash", "-c",
                "ssh -o StrictHostKeyChecking=no -R 0:localhost:" + localPort + " serveo.net")
                .redirectErrorStream(true).start();

            

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    if (++i == 3) {
                        System.out.println(line);
                        lastLine = line;
                        break;
                    }
                }
            } catch (IOException e) { }
        } else {
            lastLine = host.getAddress()+":"+host.getPort();
        }
        initNodes.accept(true);
        host.start();
        JOptionPane.showMessageDialog(null, String.format("JOIN INFORMATION:\n\nAddress: %s\nPort: %s\n\nClose this window to end the instance", lastLine.substring(Math.max(lastLine.lastIndexOf(' '),0)).split(":")));
        if (process != null) process.destroy();
        host.close();
        System.exit(0);
    }



    @Override
    public void close() throws IOException {
        if (handler != null) handler.close();
    }
}
