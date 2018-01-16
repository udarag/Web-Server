import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class Request {

    private String uri, verb, httpVersion;
    private byte[] body;
    private HashMap<String, String> headers;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public void parse(String line) {

    }

    public Request(String test) {

    }

    public Request(Socket client) throws IOException {
        String line;
        int lineCount = 0;

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(client.getInputStream())
        );

        while ((line = reader.readLine()) != null && (line.length() != 0)) {
            lineCount += 1;
            if (lineCount == 1) {
                parseInitialLine(line);
            } else {
                String[] header = line.split(": ");
                if (headers == null) {
                    headers = new HashMap();
                }
                headers.put(header[0], header[1]);
                System.out.println(line);
            }
        }
        System.out.println();

    }

    public void parseInitialLine(String line) {
        String[] request = line.split(" ");
        setVerb(request[0]);
        setUri(request[1]);
        setHttpVersion(request[2]);
    }
}
