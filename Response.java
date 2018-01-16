import java.util.Date;

public class Response {

    private int responseCode;
    private String reasonPhrase;
    private Resource resource;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Response(Resource resource) {
        this.resource = resource;
    }

    private String date() {
        Date date = new Date();
        return date.toString();

    }

    public void NotFoundResponse(Resource resource, int code) {
        System.out.println("HTTP/1.1 " + code + " " + getReasonPhrase());
        System.out.println("Date: " + date());
        System.out.println("Server: " + "Vikram & Udaras Server");
    }

    public void okResponse(Resource resource, int code) {
        System.out.println("HTTP/1.1 " + code + " " + getReasonPhrase());
        System.out.println("Date: " + date());
        System.out.println("Server: " + "Vikram & Udaras Server");

    }

    public void noContentResponse(Resource resource, int code) {
        System.out.println("HTTP/1.1 " + code + " " + getReasonPhrase());
        System.out.println("Date: " + date());
        System.out.println("Server: " + "Vikram & Udaras Server");
    }

    public void createdResponse(Resource resource, int code) {
        System.out.println("HTTP/1.1 " + code + " " + getReasonPhrase());
        System.out.println("Date: " + date());
        System.out.println("Server: " + "Vikram & Udaras Server");
        System.out.println("File created in directory");
    }

    public Response(Resource resource, int code) {
        switch (code) {
            case 200:
                setReasonPhrase("OK");
                okResponse(resource, code);
                break;
            case 201:
                setReasonPhrase("Created");
                createdResponse(resource, code);
                break;
            case 204:
                setReasonPhrase("No Content");
                noContentResponse(resource, code);
                break;
            case 400:
                setReasonPhrase("Bad Request");
                break;
            case 401:
                setReasonPhrase("Unauthorized");
                break;
            case 403:
                setReasonPhrase("Forbidden");
                break;
            case 404:
                setReasonPhrase("Not Found");
                NotFoundResponse(resource, code);
                break;
            case 500:
                setReasonPhrase("Internal Server Error");
                break;
        }
        this.resource = resource;
    }

}
