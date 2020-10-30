package sb.orkhoyan.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sb.orkhoyan.ConsoleEventLogger;
import sb.orkhoyan.Main;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// To delete other files
public class DeleteHandler implements Handler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private String fileName;

    public DeleteHandler(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            Files.delete(Paths.get(Main.pathStr + Main.separator + fileName));
            ConsoleEventLogger.println("File was deleted!\n");
        } catch (IOException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
    }
}
