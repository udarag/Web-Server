import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import conf.HttpdConf;
import conf.MimeTypes;

public class WebServer {

    private HttpdConf httpdConf;
    private MimeTypes mimeTypes;
    private ServerSocket serverSocket;

    public WebServer() {
        httpdConf = new HttpdConf("conf/httpd.conf.txt");
        mimeTypes = new MimeTypes("mime.types.txt");
        try {
            int port = httpdConf.getPort();
            if (port == 0) {
                port = 8080;
                httpdConf.setPort(port);
            }
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpdConf getHttpdConf() {
        return httpdConf;
    }

    public void setHttpdConf(HttpdConf httpdConf) {
        this.httpdConf = httpdConf;
    }

    public MimeTypes getMimeTypes() {
        return mimeTypes;
    }

    public void setMimeTypes(MimeTypes mimeTypes) {
        this.mimeTypes = mimeTypes;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start() throws IOException {

        System.out.println("Server running on port: " + httpdConf.getPort());
        Socket client;
        while (true) {
            Worker worker;
            try {
                client = serverSocket.accept();
                worker = new Worker(client, mimeTypes, httpdConf);
                Thread t = new Thread(worker);
                t.start();
            } catch (IOException e) {
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        WebServer server = new WebServer();
        server.start();
    }

}
