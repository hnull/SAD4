package Service;

import Model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static List<Presentation> parse(String json) {
        Object obj = null;
        try {
            obj = new JSONParser().parse(json);
        }
        catch (ParseException e) {
            System.out.println("Can not parse json");
            System.exit(0);
        }
        JSONObject jo = (JSONObject) obj;
        JSONArray classes = (JSONArray) jo.get("classes");
        List<Presentation> presentations = new ArrayList<Presentation>();
        for(Object classData: classes) {
            jo = (JSONObject)classData;
            Presentation ps = new Presentation();
            Exam exam = new Exam();
            exam.startAt = (String)jo.get("start_at");
            exam.endAt = (String)jo.get("end_at");
            exam.setExamId(Integer.parseInt(jo.get("exam_id").toString()));
            ClassRoom classRoom = new ClassRoom(Integer.parseInt(jo.get("room_number").toString()));
            exam.classRoom = classRoom;
            Course course = new Course((String)jo.get("course_name"));
            ps.course = course;
            ps.exam = exam;

            addProfessor((JSONObject)jo.get("professor"), ps);
            addStudents((JSONArray)jo.get("students"), ps);

            presentations.add(ps);
        }
        return presentations;
    }

    private static void addStudents(JSONArray ja, Presentation ps) {
        List<StudentCourse> studentCourses = new ArrayList<StudentCourse>();
        for(Object j : ja) {
            JSONObject jo = (JSONObject)j;
            StudentCourse studentCourse = new StudentCourse(Integer.parseInt(jo.get("chair_number").toString()));
            int stdId =  Integer.parseInt(jo.get("id").toString());
            if(DB.findProfessor(stdId) == null) {
                Student student = new Student();
                student.setFirstName((String)jo.get("first_name"));
                student.setLastName((String)jo.get("last_name"));
                student.setId(stdId);
                DB.students.add(student);
                studentCourse.setStudent(student);
            }
            else {
                studentCourse.setStudent(DB.findStudent(stdId));
            }
            studentCourses.add(studentCourse);
        }
        ps.setStudents(studentCourses);
    }

    private static void addProfessor(JSONObject jo, Presentation ps) {
        int profId =  Integer.parseInt(jo.get("id").toString());
        if(DB.findProfessor(profId) == null) {
            Professor professor = new Professor();
            professor.setFirstName((String)jo.get("first_name"));
            professor.setLastName((String)jo.get("last_name"));
            professor.setId(profId);
            ps.setProfessor(professor);
            DB.professors.add(professor);
        }
        else {
            ps.setProfessor(DB.findProfessor(profId));
        }
    }
}
