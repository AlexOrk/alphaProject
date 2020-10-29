package sb.orkhoyan.handlers;

import sb.orkhoyan.Process;

// To handling XML files
public class XmlHandler implements Handler {

    private String fileName;

    public XmlHandler(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        Process.doSomeWork(fileName);
    }
}
