package conf;

import java.util.*;

public class HttpdConf extends ConfigurationReader {

    private String serverRoot, documentRoot, logFile;
    private String accessFileName, directoryIndex;
    private int port;
    private HashMap<String, String> aliases, scriptAliases;
    private ArrayList<String> directoryIndexFiles;

    public HttpdConf(String fileName) {
        super(fileName);
    }

    @Override
    public void parse() {
        String line = getLine();
        String key = line.substring(0, line.indexOf(" "));
        String value = line.substring(line.indexOf(" ") + 1);
        String path, absolutePath;
        int startIndex, endIndex;
        switch (key) {
            case "ServerRoot":
                startIndex = value.indexOf("\"");
                endIndex = value.substring(startIndex + 1).indexOf("\"");
                String serverRoot = value.substring(startIndex + 1, endIndex + 1);
//				System.out.println("Server Root is: " + serverRoot);
                setServerRoot(serverRoot);
                break;
            case "Listen":
                endIndex = value.indexOf(" ");
                if (endIndex != -1) {
                    value = value.substring(0, endIndex);
                }
                int portNumber = Integer.parseInt(value);
//				System.out.println("Listening port is: " + portNumber);
                setPort(portNumber);
                break;
            case "DocumentRoot":
                startIndex = value.indexOf("\"");
                endIndex = value.substring(startIndex + 1).indexOf("\"");
                String documentRoot = value.substring(startIndex + 1, endIndex + 1);
//				System.out.println("Document Root is: " + documentRoot);
                setDocumentRoot(documentRoot);
                break;
            case "LogFile":
                startIndex = value.indexOf("\"");
                endIndex = value.substring(startIndex + 1).indexOf("\"");
                String logFile = value.substring(startIndex + 1, endIndex + 1);
//				System.out.println("Log File is: " + logFile);
                setLogFile(logFile);
                break;
            case "AccessFileName":
                endIndex = value.indexOf(" ");
                if (endIndex != -1) {
                    value = value.substring(0, endIndex);
                }
                String accessFileName = value;
//				System.out.println("Access File Name is: " + accessFileName);
                setAccessFileName(accessFileName);
                break;
            case "ScriptAlias":
                if (scriptAliases == null) {
                    scriptAliases = new HashMap<>();
                }
                endIndex = value.indexOf(" ");
                path = value.substring(0, endIndex);
                value = value.substring(endIndex + 1);
                startIndex = value.indexOf("\"");
                endIndex = value.substring(startIndex + 1).indexOf("\"");
                absolutePath = value.substring(startIndex + 1, endIndex + 1);
                scriptAliases.put(path, absolutePath);
                break;
            case "Alias":
                if (aliases == null) {
                    aliases = new HashMap<>();
                }
                endIndex = value.indexOf(" ");
                path = value.substring(0, endIndex);
                value = value.substring(endIndex + 1);
                startIndex = value.indexOf("\"");
                endIndex = value.substring(startIndex + 1).indexOf("\"");
                absolutePath = value.substring(startIndex + 1, endIndex + 1);
                aliases.put(path, absolutePath);
                break;
            case "DirectoryIndex":
                directoryIndexFiles = new ArrayList<String>(Arrays.asList(value.split("\\s+")));
        }
    }

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public String getAccessFileName() {
        return accessFileName;
    }

    public void setAccessFileName(String accessFileName) {
        this.accessFileName = accessFileName;
    }

    public String getDirectoryIndex() {
        return directoryIndex;
    }

    public void setDirectoryIndex(String directoryIndex) {
        this.directoryIndex = directoryIndex;
    }

    public HashMap<String, String> getAliases() {
        return aliases;
    }

    public void setAliases(HashMap<String, String> aliases) {
        this.aliases = aliases;
    }

    public HashMap<String, String> getScriptAliases() {
        return scriptAliases;
    }

    public void setScriptAliases(HashMap<String, String> scriptAliases) {
        this.scriptAliases = scriptAliases;
    }

    public ArrayList<String> getDirectoryIndexFiles() {
        return directoryIndexFiles;
    }

    public void setDirectoryIndexFiles(ArrayList<String> directoryIndexFiles) {
        this.directoryIndexFiles = directoryIndexFiles;
    }

    public String getServerRoot() {
        return serverRoot;
    }

    public void setServerRoot(String serverRoot) {
        this.serverRoot = serverRoot;
    }

    public String getDocumentRoot() {
        return documentRoot;
    }

    public void setDocumentRoot(String documentRoot) {
        this.documentRoot = documentRoot;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
