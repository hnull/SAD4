package FASAD;

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
        }
    }
}
