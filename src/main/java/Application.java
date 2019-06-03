import Model.Presentation;
import Model.StudentCourse;
import Service.HttpClientGet;
import Service.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String [] args) throws IOException {
        String json = new HttpClientGet().HttpGetRequest();
        List<Presentation> presentations = JsonParser.parse(json);

        for(Presentation ps: presentations) {
            System.out.println(ps.getProfessor().getFirstName());
            System.out.println(ps.getProfessor().getLastName());
            System.out.println(ps.getProfessor().getId());

            for (StudentCourse studentCourse : ps.getStudents()) {
                System.out.println(studentCourse.chairNumber);
                System.out.println(studentCourse.getStudent().getFirstName());
            }
        }
    }
}
