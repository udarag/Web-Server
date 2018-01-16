package conf;

public class Htaccess extends ConfigurationReader {
    private String userFile;
    private String authType;
    private String authName;
    private String require;

    public Htaccess(String fileName) {
        super(fileName);
    }

    public void setUserFile(String userFile) {
        this.userFile = userFile;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public void setRequire(String require) {
        this.require = require;
    }

    public String getUserFile() {
        return userFile;
    }

    public String getAuthType() {
        return authType;
    }

    public String getAuthName() {
        return authName;
    }

    public String getRequire() {
        return require;
    }

    @Override
    void parse() {
        String line = getLine();
        if (!line.isEmpty()) {
            String[] tokens = line.split(" ", 2);
            String identifier = tokens[0];
            String key;
            switch (identifier) {
                case "AuthUserFile":
                    key = tokens[1].substring(1, tokens[1].length() - 1);
                    setUserFile(tokens[1].substring(1, tokens[1].length() - 1));
                    break;
                case "AuthType":
                    setAuthType(tokens[1]);
                    break;
                case "AuthName":
                    key = tokens[1].substring(1, tokens[1].length() - 1);
                    setAuthName(key);
                    break;
                case "Require":
                    setRequire(tokens[1]);
                    break;
            }
        }
    }


//
//    public void print(){
//        System.out.println("Authentication User File: " + userFile);
//        System.out.println("Authentication Type Required: " + authType);
//        System.out.println("Authentication Name: " + authName);
//        System.out.println("Required User: " + require);
//    }

//    public static void main(String[] args){
//        Htaccess htaccess = new Htaccess("_.htaccess.txt");
//        htaccess.print();
//    }
}
