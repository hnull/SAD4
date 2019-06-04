package FASAD;

import Model.LifeCycle;
import Service.DB;

import java.util.Scanner;

public class CommandHandler {
    private static Controller controller = new Controller();
    public static void handle() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        switch (command) {
            case "login":
                System.out.print("username: ");
                String username = scanner.nextLine();
                System.out.print("password: ");
                String password = scanner.nextLine();
                controller.login(username, password);
                break;
            case "getExams":
                controller.getExams();
                break;
            case "selectExam":
                System.out.print("exam id : ");
                int examId = scanner.nextInt();
                controller.selectExam(examId);
                break;
            case "setStudentPresence":
                System.out.print("student id : ");
                int studentId = scanner.nextInt();
                controller.selectStudent(studentId);
                break;
            case "finishExam":
                System.out.println("exam presentation process finished");
                DB.lifeCycle = LifeCycle.examFinished;
                break;
            case "signByProfessor":
                System.out.print("professor id : ");
                int profId = scanner.nextInt();
                if(controller.signByProfessor(profId))
                    controller.sendExamPresetationData();
                break;
            default:
                System.out.println("unknown method");
        }
    }
}
