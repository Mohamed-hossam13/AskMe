import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AskSystem {
    private final Scanner scan;
    private UsersManager usersManager = new UsersManager();


    /** A non-parametrized constructor in which the database is loaded*/
    public AskSystem() throws FileNotFoundException {
        scan = new Scanner(System.in);
        usersManager.loadDatabase();
    }

    public void run() throws IOException {
        while (true) {
            int mainChoice = mainMenu();
            if (mainChoice == 1) {
                if (usersManager.login()) {
                    while (true) {
                        int subChoice = subMenu();
                        if (subChoice == 1) {
                            System.out.println("choice 1");
                        } else if (subChoice == 2) {
                            System.out.println("choice 2");
                        } else if (subChoice == 3) {
                            System.out.println("choice 3");
                        } else if (subChoice == 4) {
                            System.out.println("choice 4");
                        } else if (subChoice == 5) {
                            System.out.println("choice 5");
                        } else if (subChoice == 6) {
                            System.out.println("choice 6");
                        } else if (subChoice == 7) {
                            System.out.println("choice 7");
                        } else {
                            break;
                        }
                    }
                }
                else
                    System.out.println("User is not found...Try again.");
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
