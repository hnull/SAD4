package Service;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class RunnableSend implements Runnable {

    private StringBuilder post_data;
    private static HttpClientGet http_client = new HttpClientGet();
    private boolean unlock = true;
    private long timeMillislis = System.currentTimeMillis();

    public RunnableSend(StringBuilder post_data) {
        this.post_data = post_data;
    }

    @Override
    public void run() {
        while (true) {
            if(unlock) {
                HttpResponse response = http_client.postData(post_data);
                timeMillislis = System.currentTimeMillis();
                unlock = false;
                if(response.getStatusLine().getStatusCode() == 200) {
                    System.out.println("data sent succesfully and response is : " + response.getStatusLine());
                    break;
                }
            }
            else {
                if(System.currentTimeMillis() - timeMillislis > 10000) {
                    System.out.println(111);
                    unlock = true;
                }
            }
        }
    }
}
