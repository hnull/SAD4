import FASAD.CommandHandler;
import Model.Presentation;
import Model.StudentCourse;
import Service.DB;
import Service.HttpClientGet;
import Service.JsonParser;

import java.io.IOException;

public class Application {

    public static void main(String [] args) throws IOException {
        String json = new HttpClientGet().HttpGetRequest();
        DB.presentations = JsonParser.parse(json);
        while(true) {
            CommandHandler.handle();
        }
    }
}
