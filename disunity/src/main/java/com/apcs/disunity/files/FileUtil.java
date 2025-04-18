package com.apcs.disunity.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Utility class for file operations
 * 
 * @author Qinzhao Li
 */
public class FileUtil {

    /* ================ [ METHODS ] ================ */

    // Get file name
    public static String getName(File file) {
        String name = file.getName();

        // Remove file extension
        int dot = name.lastIndexOf('.');
        return (dot > 0) ? name.substring(0, name.lastIndexOf('.')) : name;
    }

    // Get file type
    public static String getType(File file) throws IOException {
        return Files.probeContentType(file.toPath()).split("/")[0];
    }
    
}
