 package com.apcs.disunity.app.network.test;

 import java.io.IOException;
 import java.util.Scanner;

 import com.apcs.disunity.app.network.ClientSideSyncHandler;
 import com.apcs.disunity.app.network.HostSideSyncHandler;

 import static com.apcs.disunity.app.network.test.TestingUtils.spawnProcess;

 /// # current status of this test:
 /// connection example
 public class SyncHandlerTest {
   public static void main(String[] args) throws IOException {
     spawnProcess(ServerTest.class);
     spawnProcess(ClientTest.class);
     new Scanner(System.in).nextLine();
   };
   public static class ServerTest {
     public static void main(String[] args) throws IOException {
       HostSideSyncHandler hsh = new HostSideSyncHandler();
       hsh.start();
     }
   }
   public static class ClientTest {
     public static void main(String[] args) throws IOException {
       ClientSideSyncHandler csh = new ClientSideSyncHandler(
         "0:0:0:0:0:0:0:0",
         3000
       );
       csh.start();
     }
   }
 }
