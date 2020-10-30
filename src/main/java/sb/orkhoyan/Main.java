package sb.orkhoyan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;

/**
 * This program allows you to monitor the directory for new files.
 * Author: A.Orkhoyan
 */

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static String pathStr = "demo";
    public static String separator = FileSystems.getDefault().getSeparator();

    public static void main(String[] args) throws InterruptedException {

        // The user can select a directory for monitoring.
        // By default, this is the "demo" directory.
        pathStr = Process.askPath(pathStr);

        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(pathStr);
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            ConsoleEventLogger.println("Directory monitoring starts.");

            WatchKey key;
            while ((key = watchService.take()) != null) {
                // The program monitors every event
                for (WatchEvent<?> event : key.pollEvents()) {
                    // if there are too many files for monitoring
                    if (event.kind().toString().equals("OVERFLOW")) {
                        continue;
                    }
                    String fileName = event.context().toString();
                    logger.info(fileName + " " + Utils.getFormattedFileCreationTime(
                            Paths.get(path + separator + fileName)));
                    logger.info("Handling started at " + Utils.getCurrentTime());

                    long start = System.currentTimeMillis();
                    // Depending on the file, a handler is selected and started
                    Process.startSelectedHandler(pathStr);

                    long finish = System.currentTimeMillis();
                    logger.info("Total file handling time is " + (finish - start) + " millis.");
                }
                key.reset();
            }
        } catch (IOException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        ConsoleEventLogger.println("The program was ended.");
    }
}

/*  1. Приложение должно слушать директорию на появление новых файлов:
        - При появлении нового файла необходимо вывести в лог его имя, его расширение и дату создания
        - После необходимо запустить в новом потоке его обработку, выбор обработчика осуществляется в зависимости
          от расширения
        - Обработчик должен вывести в лог данные о времени начала обработки и общее время обработки файла
        - Если файл по расширению не подходит под допустимый, необходимо запустить обработчик, который бы удалил данный файл

     2. Допустимы два расширения: xml, json
     3. В качестве имитации обработки необходимо вывести в лог количество строк в файле
     4. Логгирование должно осуществляться в файл на диске
*/