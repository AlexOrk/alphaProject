package sb.orkhoyan;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void isDirectoryExistTest() {
        String testPath = "demo/";
        String testPath1 = "src/main/java/sb/orkhoyan/handlers";
        String testPath2 = "/alpha_project//java/sb/orkhoyan/";
        String testPath3 = "\\";
        String testPath4 = "main/java";

        boolean actual = Utils.isDirectoryExist(testPath);
        boolean actual1 = Utils.isDirectoryExist(testPath1);
        boolean actual2 = Utils.isDirectoryExist(testPath2);
        boolean actual3 = Utils.isDirectoryExist(testPath3);
        boolean actual4 = Utils.isDirectoryExist(testPath4);

        assertTrue(actual);
        assertTrue(actual1);
        assertFalse(actual2);
        assertFalse(actual3);
        assertFalse(actual4);
    }

    @Test
    public void getFormattedFileCreationTimeTest() {
        File tempFile = new File("demo", "tempFile.txt");
        try {
            tempFile.createNewFile();
            tempFile.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        BasicFileAttributes attr = null;
        try {
            attr = Files.readAttributes(tempFile.toPath(), BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileTime fileTime = attr.creationTime();
        Instant dateTime = fileTime.toInstant();
        String expected = dateTime.atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .format(formatter);

        String actual = Utils.getFormattedFileCreationTime(tempFile.toPath());

        assertEquals(expected, actual);
    }

}
