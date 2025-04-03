package com.apcs.disunity.server.test;

import java.io.IOException;

public final class TestingUtils {
  // prevent getting instantiated
  private TestingUtils(){};

  /// spawns new java application process using same classpath.
  public static void spawnProcess(Class<?> mainClass, String... args) throws IOException {
      String javaPath = ProcessHandle.current().info().command().get();
      String[] command = new String[args.length + 4];
      System.arraycopy(new String[]
        {javaPath, "-cp", System.getProperty("java.class.path"), mainClass.getTypeName(),},
        0,command,0,4);
      System.arraycopy(args,0,command,4,args.length);
      Process childProcess = new ProcessBuilder(command)
        .inheritIO()
        .start();
      Runtime.getRuntime().addShutdownHook(new Thread(() -> childProcess.destroy()));
  }
}
