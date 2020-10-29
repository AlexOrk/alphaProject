package sb.orkhoyan.handlers;

import sb.orkhoyan.Process;

// To handling JSON files
public class JsonHandler implements Handler {

    private String fileName;

    public JsonHandler(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        Process.doSomeWork(fileName);
    }
}
