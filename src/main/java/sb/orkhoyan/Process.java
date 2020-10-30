package sb.orkhoyan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sb.orkhoyan.handlers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

// Class for performing the work process
public final class Process {

    private static final Logger logger = LoggerFactory.getLogger(Process.class);

    // Directory assignment at the user's request
    public static String askPath(String pathStr) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ConsoleEventLogger.println("Print \"yes\" if you want to select a directory for monitoring.");
            ConsoleEventLogger.println("By default, the \"demo\" folder will be monitored.");
            String answer = scanner.nextLine();
            if (answer.toLowerCase().equals("yes")) {
                ConsoleEventLogger.println("Enter the path to the directory that will be monitored:");
                String usersPath = scanner.nextLine();

                if (Utils.isDirectoryExist(usersPath)) {
                    pathStr = usersPath;
                } else {
                    ConsoleEventLogger.println("The directory does not exist!\n");
                    continue;
                }
            } else ConsoleEventLogger.println("Default directory was selected.\n");
            break;
        }

        scanner.close();
        return pathStr;
    }

    // Starting a handler thread
    public static void startSelectedHandler(String fileName) {
        Handler handler = null;

        if (fileName.endsWith(".xml"))
            handler = new XmlHandler(fileName);
        else if (fileName.endsWith(".json"))
            handler = new JsonHandler(fileName);
        else
            handler = new DeleteHandler(fileName);

        Thread thread = new Thread(handler);
        thread.setDaemon(true);
        thread.start();
    }

    // Simulate work in a thread
    public static void doSomeWork(String fileName) {
        try {
            long lines = Files.lines(Paths.get(Main.pathStr + Main.separator + fileName)).count();
            logger.info(lines + " lines in the file is.");
        } catch (IOException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
    }
}
