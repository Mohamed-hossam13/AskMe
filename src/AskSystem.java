import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AskSystem {
    private final Scanner scan;
    private UsersManager usersManager;
    private QuestionsManager questionsManager;


    public AskSystem() throws FileNotFoundException {
        scan = new Scanner(System.in);
        usersManager = new UsersManager();
        questionsManager = new QuestionsManager();
    }

    /** A method in which the database of the system is loaded
     * And we'll wait till a user do log-in to fill all the questions */
    public void loadDatabase(boolean loginUser) throws FileNotFoundException {
        usersManager.loadDatabase();
        questionsManager.loadDatabase();
        if (loginUser)
            questionsManager.fillUserQuestionFromAndTo(usersManager.currentUser);
    }

    public void run() throws IOException {
        loadDatabase(false);
        while (true) {
            int mainChoice = mainMenu();
            if (mainChoice == 1) {
                usersManager.login();
                while (true) {
                    loadDatabase(true);
                    int subChoice = subMenu();
                    if (subChoice == 1) {
                        questionsManager.printQuestionsToMe(usersManager.currentUser);
                    } else if (subChoice == 2) {
                        questionsManager.printQuestionsFromMe(usersManager.currentUser);
                    } else if (subChoice == 3) {
                        questionsManager.AnswerQuestion();
                    } else if (subChoice == 4) {
                        System.out.println("choice 4");
                    } else if (subChoice == 5) {
                        System.out.println("choice 5");
                    } else if (subChoice == 6) {
                        usersManager.listSystemUsers();
                    } else if (subChoice == 7) {
                        System.out.println("choice 7");
                    } else {
                        break;
                    }
                }
            }
            else if (mainChoice == 2) {
                usersManager.signUp();
            }
            else {
                break;
            }
        }
    }

    public int mainMenu() {
        int choice = -1;
        while (choice == -1) {
            System.out.println("Menu:");
            System.out.println("\t\t1: Login");
            System.out.println("\t\t2: Sign Up");
            System.out.println("\t\t3: Exit");

            System.out.print("\nEnter number in range 1 - 3: ");
            choice = scan.nextInt();

            if (choice < 1 || choice > 3) {
                System.out.println("Error: Invalid number...Try again");
                choice = -1;
            }
        }
        return choice;
    }

    public int subMenu() {
        int choice = -1;
        while (choice == -1) {
            System.out.println("Menu:");
            System.out.println("\t\t1: Print Questions To Me");
            System.out.println("\t\t2: Print Questions From Me");
            System.out.println("\t\t3: Answer Question");
            System.out.println("\t\t4: Delete Question");
            System.out.println("\t\t5: Ask Question");
            System.out.println("\t\t6: List System Users");
            System.out.println("\t\t7: Feed");
            System.out.println("\t\t8: Logout");

            System.out.print("\nEnter number in range 1 - 8: ");
            choice = scan.nextInt();

            if (choice < 1 || choice > 8) {
                System.out.println("Error: Invalid number...Try again");
                choice = -1;
            }
        }
        return choice;
    }
    
}
