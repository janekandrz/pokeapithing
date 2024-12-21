package process;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;


public class ProcessRequest {
    private final HttpClient client;

    public ProcessRequest(){
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    public HttpRequest reqBuilder(String url){
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer YOUR_API_KEY")
                .GET()
                .build();
    }

    public String sendGetReq(String url){
        try{
            HttpResponse<String> response = client.send(reqBuilder(url), HttpResponse.BodyHandlers.ofString());
            return parseRes(response);
        } catch(IOException | InterruptedException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private String parseRes(HttpResponse<String> res){
        int statusCode = res.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            return res.body();
        } else {
            System.err.println("HTTP Error: " + statusCode + " - " + res.body());
            return null;
        }
    }
}
