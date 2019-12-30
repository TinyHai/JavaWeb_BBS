package bbs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class UploadFileUtils {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

    private static final String uploadDir = "/home/tiny/web_upload/";

    public static void saveFile(InputStream inputStream, String fileName) throws IOException {
        saveFile(inputStream, new File(uploadDir + fileName));
    }

    private static void saveFile(InputStream inputStream, File file) throws IOException {
        Path path = file.toPath();
        if (file.exists()) {
            Files.delete(path);
        }
        Files.createFile(path);
        byte[] buffer = new byte[1024];
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedInputStream bis = new BufferedInputStream(inputStream)) {
            while (bis.read(buffer) > 0) {
                fos.write(buffer);
            }
            fos.flush();
            logger.info("new upload file: " + file.getAbsolutePath());
        }
    }
}
