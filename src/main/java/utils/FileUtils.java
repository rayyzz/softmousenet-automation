package utils;

import java.io.File;

public class FileUtils {

    // Returns the latest downloaded file with the given extension
    public static File getLatestFileFromDir(String dirPath, String extension) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(extension));

        if (files == null || files.length == 0) return null;

        File latest = files[0];
        for (File file : files) {
            if (file.lastModified() > latest.lastModified()) {
                latest = file;
            }
        }
        return latest;
    }
}
