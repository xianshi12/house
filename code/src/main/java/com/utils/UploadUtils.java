package com.utils;

import java.io.File;
import java.io.IOException;

public final class UploadUtils {
    private static final String DEFAULT_UPLOAD_DIR = "src/main/resources/static/upload";

    private UploadUtils() {
    }

    public static File resolveUploadDir(String uploadDir) throws IOException {
        String configuredDir = uploadDir == null || uploadDir.trim().length() == 0 ? DEFAULT_UPLOAD_DIR : uploadDir.trim();
        File dir = new File(configuredDir);
        if (!dir.isAbsolute()) {
            File userDir = new File(System.getProperty("user.dir"));
            dir = new File(userDir, configuredDir);
            if (!dir.exists()) {
                File moduleDir = new File(userDir, "code");
                if (moduleDir.exists()) {
                    dir = new File(moduleDir, configuredDir);
                }
            }
        }
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("Failed to create upload directory: " + dir.getAbsolutePath());
        }
        return dir.getCanonicalFile();
    }

    public static String toResourceLocation(File dir) {
        String uri = dir.toURI().toASCIIString();
        return uri.endsWith("/") ? uri : uri + "/";
    }

    public static String cleanFileName(String fileName) {
        String cleanFileName = fileName == null ? "" : fileName.replace("\\", "/").replace("upload/", "");
        if (cleanFileName.contains("/")) {
            cleanFileName = cleanFileName.substring(cleanFileName.lastIndexOf("/") + 1);
        }
        if (cleanFileName.contains("..")) {
            return "";
        }
        return cleanFileName;
    }
}
