import java.io.IOException;
import java.net.Socket;

import conf.HttpdConf;
import conf.MimeTypes;

public class Worker implements Runnable {

    private Socket client;
    private MimeTypes mimeTypes;
    private HttpdConf httpdConf;

    public Worker(Socket client, MimeTypes mimeTypes, HttpdConf httpdConf) {
        this.client = client;
        this.mimeTypes = mimeTypes;
        this.httpdConf = httpdConf;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public MimeTypes getMimeTypes() {
        return mimeTypes;
    }

    public void setMimeTypes(MimeTypes mimeTypes) {
        this.mimeTypes = mimeTypes;
    }

    public HttpdConf getHttpdConf() {
        return httpdConf;
    }

    public void setHttpdConf(HttpdConf httpdConf) {
        this.httpdConf = httpdConf;
    }

    @Override
    public void run() {
        try {
            Request request = new Request(client);
            Resource resource = new Resource(request.getUri(), httpdConf);
            Response response = ResponseFactory.getResponse(request, resource);
            String responsePhrase = response.getReasonPhrase();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
