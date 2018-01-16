
public class ResponseFactory {

    public static Response getResponse(Request request, Resource resource) throws Exception {
        switch (request.getVerb()) {
            case "GET":
                if (!resource.isExist()) {
                    return new Response(resource, 404);
                } else {
                    return new Response(resource, 200);
                }


            case "HEAD":
                if (!resource.isExist()) {
                    return new Response(resource, 404);
                } else {
                    return new Response(resource, 204);
                }

            case "PUT":
                if (!resource.isExist()) {
                    return new Response(resource, 404);
                } else {
                    resource.createFile(resource.absolutePath(), resource.absolutePath());
                    return new Response(resource, 201);
                }

            case "DELETE":
                if (!resource.isExist()) {
                    return new Response(resource, 404);
                } else {
                    resource.deleteFile(resource.absolutePath());
                    return new Response(resource, 204);
                }

            default:
                return new Response(resource, 400);
        }
    }

}
