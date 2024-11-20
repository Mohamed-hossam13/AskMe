import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/** A userManager class to handle the functionalities of the user
 * We have a map usernameToUserObject to make sure that every username is a unique one */
public class UsersManager {
    private Map<String, User> usernameToUserObject;
    private final String path;
    private int lastUserID;
    private User currentUser;


    /** A method to check if the user logged-in to the system is found or not*/
    private boolean isFoundUser(String username, String password) {
        User user = new User();
        boolean found = false;
        if (usernameToUserObject.containsKey(username)) {
            user = usernameToUserObject.get(username);
            if (user.getPassword().equals(password)) {
                found = true;
            }
        }
        return found;
    }

    public UsersManager() {
        usernameToUserObject = new HashMap<>();
        currentUser = new User();
        path = "users.txt";
        lastUserID = 0;
    }

    /** A method to read all the users from the file and add them
     * to the ArrayList (lines) to start dealing with them.
     * And also we will split every single user-data separately
     * Then map every user object to its username
     * We need also to update the lastID variable to contain the max id in the file
     * to give it to the next added user to the system */
    public void loadDatabase() throws FileNotFoundException {
        lastUserID = 0;
        ArrayList<String> lines = new ArrayList<>();
        lines = FileHandler.readFileLines(path);
        for (String line : lines) {
            User user = new User(line);
            usernameToUserObject.put(user.getUserName(), user);
            lastUserID = Math.max(lastUserID, user.getUserId());
        }
    }

    /** A method to return the ID for the last user in the system */
    public int getLastUserID() {
        return lastUserID;
    }

    /** A method to do the sign-up choice to add a new user to the system
     * We need to check if the username is already token before or not
     * so we will keep looping if the username is found
     * After adding all the user's info, we will put the user into the map
     * then update the database with the new user */
    public void signUp() throws IOException {
        Scanner scan = new Scanner(System.in);
        String username, password, name, email;
        int userID, allowAnonymous;
        while (true) {
            System.out.print("Enter username without spaces: ");
            username = scan.next();
            if(usernameToUserObject.containsKey(username))
                System.out.println("Already used. Try again");
            else
                break;
        }
        System.out.print("Enter password: ");
        password = scan.next();
        System.out.print("Enter name: ");
        name = scan.next();
        System.out.print("Enter email: ");
        email = scan.next();
        System.out.print("Allow anonymous questions? (0 or 1) : ");
        allowAnonymous = scan.nextInt();
        userID = ++lastUserID;

        currentUser.setUserId(userID);
        currentUser.setUserName(username);
        currentUser.setPassword(password);
        currentUser.setName(name);
        currentUser.setEmail(email);
        currentUser.setAllowAnonymousQuestions(allowAnonymous);

        usernameToUserObject.put(username, currentUser);
        updateDatabase(currentUser);
    }

    /** A method to do the log-in choice to make user enter to the system */
    public boolean login() {
        Scanner scan = new Scanner(System.in);
        String username, password;
        System.out.print("Enter username: ");
        username = scan.next();
        System.out.print("Enter password: ");
        password = scan.next();
        return isFoundUser(username, password);
    }


    /** A method to list all the users in the system */
    public void listSystemUsers() {
        for (User user : usernameToUserObject.values())
            user.printUser();
    }

    /** A method to update the database with the new added user to the file */
    public void updateDatabase(User user) throws IOException {
        String line = user.toString();
        FileHandler.writeFileLines(path, line);
    }

}
