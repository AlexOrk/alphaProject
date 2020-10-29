package sb.orkhoyan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

// Class for utility work
public final class Utils {
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getCurrentTime() {
        return ZonedDateTime.now()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .format(formatter);
    }

    public static boolean isDirectoryExist(String pathStr) {
        if(Files.isDirectory(Paths.get(pathStr)) && Files.exists(Paths.get(pathStr)))
            return true;
        else return false;
    }

    private static Instant getFileCreationTime(Path path) {
        Instant dateTime = null;

        try {
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            FileTime fileTime = attr.creationTime();
            dateTime = fileTime.toInstant();

        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        return dateTime;
    }

    public static String getFormattedFileCreationTime(Path path) {
        return getFileCreationTime(path)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .format(formatter);
    }
}
