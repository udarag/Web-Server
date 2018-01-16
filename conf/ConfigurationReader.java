package conf;


import java.io.*;

abstract class ConfigurationReader {

    private File file;
    private String fileName, line;

    ConfigurationReader(String fileName) {
        loadFile(fileName);
    }

    public void loadFile(String fileName) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while ((line = br.readLine()) != null) {
                setLine(line);
                parse();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    abstract void parse();

}
