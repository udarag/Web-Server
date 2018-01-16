package conf;

import java.util.*;


public class MimeTypes extends ConfigurationReader {
    private Hashtable<String, String> types;
    private String mimeType;

    public MimeTypes(String fileName) {
        super(fileName);
    }

    @Override
    void parse() {
        if (types == null) {
            types = new Hashtable<>();
        }
        String line = getLine();
        if (!line.isEmpty()) {
            char key = line.charAt(0);
            if (key != '#') {
                String[] tokens = line.split("\\s+");
                for (int i = 1; i < tokens.length; i++) {
                    setMimeType(tokens[i], tokens[0]);
                }
            }
        }
    }

    public String getMimeType(String extension) {
        if (types.containsKey(extension)) {
            mimeType = types.get(extension);
        } else {
            mimeType = "text/text";
        }
        return mimeType;
    }

    public void setMimeType(String extension, String mimeType) {
        if (extension != null && mimeType != null) {
            types.put(extension, mimeType);
        }
    }

//    public void printTable() {
////        Enumeration items = types.elements();
////        while(items.hasMoreElements()){
////            System.out.println(items.nextElement());
////        }
//        System.out.println(types.size());
//    }
//
//    public static void main(String[] args) {
//        MimeTypes mimeTypes = new MimeTypes("mime.types.txt");
//        mimeTypes.printTable();
//    }

}