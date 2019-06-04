package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

public class HttpClientGet {
    private HttpClient http_client;
    private HttpGet get_request;
    private HttpPost post_data;
    private HttpResponse response;
    private String ip_addr = "http://142.93.134.194:8088/api/attendance";

    public HttpClientGet() {
        http_client = HttpClientBuilder.create().build();
    }

    public String HttpGetRequest() throws IOException{
        get_request = new HttpGet(ip_addr);
        get_request.addHeader("accept", "application/json");

        HttpResponse response = http_client.execute(get_request);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        String line_data, output = "";
        while ((line_data = br.readLine()) != null) {
            output += line_data;
        }
        return output;
    }

    public HttpResponse postData(StringBuilder jo) {
        post_data = new HttpPost(ip_addr);
        post_data.addHeader("accept", "application/json");
        StringEntity params = null;
        try {
            params =new StringEntity(jo.toString());
        }
        catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            return null;
        }
        post_data.addHeader("content-type", "application/json");
        post_data.setEntity(params);
        try {
            HttpResponse response = http_client.execute(post_data);
            return response;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
