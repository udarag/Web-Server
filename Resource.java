import conf.Htaccess;
import conf.HttpdConf;

import java.io.File;

public class Resource {

    private HttpdConf httpdConf;
    private String uri;
    private Htaccess htaccess;

    public Resource(String uri, HttpdConf httpdConf) {
        this.uri = uri;
        this.httpdConf = httpdConf;
        this.htaccess = htaccess;

    }

    public HttpdConf getHttpdConf() {
        return httpdConf;
    }

    public void setHtaccess(Htaccess htaccess) {
        this.htaccess = htaccess;
    }

    public void setHttpdConf(HttpdConf httpdConf) {
        this.httpdConf = httpdConf;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String absolutePath() {
        return httpdConf.getDocumentRoot() + uri;
    }

    public boolean isExist() {
        if (!new File(absolutePath()).exists()) {
            return false;
        }
        return true;
    }

    public boolean isScript() {
        return true;
    }

    public boolean isProtected() {
        return true;
    }

    public void deleteFile(String filename) {
        File file = new File(filename);
        file.delete();
    }

    public void createFile(String file, String dir) {
        File directory = new File(dir);
        File newFile = new File(directory, file);

    }


}
