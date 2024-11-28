import java.util.ArrayList;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String userName;
    private int allowAnonymousQuestions; // means that the user allows other users to ask him without knowing his ID

    // Two lists, one that contains all the IDs of the questions the user asked to others
    // The other that contains all the IDs of the questions sent to me
    private ArrayList<Integer> questionsIdsFromMe;
    private ArrayList<Integer> questionsIdsToMe;

    public User() {
        userId = -1;
        allowAnonymousQuestions = -1;
        questionsIdsFromMe = new ArrayList<>();
        questionsIdsToMe = new ArrayList<>();
    }

    /** A parametrized constructor that takes a line containing all the data for a user
     * and split each single data and assign it to its variable */
    public User(String line) {
        ArrayList<String> userData = FileHandler.splitLine(line, ",");
        assert(userData.size() != 6);
        userId = Integer.parseInt(userData.getFirst());
        userName = userData.get(1);
        password = userData.get(2);
        name = userData.get(3);
        email = userData.get(4);
        allowAnonymousQuestions = Integer.parseInt(userData.getLast());
        questionsIdsFromMe = new ArrayList<>();
        questionsIdsToMe = new ArrayList<>();
    }

    public int getUserId() { return userId; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getUserName() { return userName; }

    public int getAllowAnonymousQuestions() { return allowAnonymousQuestions; }

    public ArrayList<Integer> getQuestionsIdsFromMe() { return questionsIdsFromMe; }

    public ArrayList<Integer> getQuestionsIdsToMe() { return questionsIdsToMe; }

    public void setUserId(int userId) { this.userId = userId; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public void setUserName(String userName) { this.userName = userName; }

    public void setAllowAnonymousQuestions(int allowAnonymousQuestions) { this.allowAnonymousQuestions = allowAnonymousQuestions; }

    public void setQuestionsIdsFromMe(ArrayList<Integer> questionsIdsFromMe) { this.questionsIdsFromMe = questionsIdsFromMe; }

    public void setQuestionsIdsToMe(ArrayList<Integer> questionsIdsToMe) { this.questionsIdsToMe = questionsIdsToMe; }

    public String toString() {
        String line;
        String delimiter = ",";
        line = getUserId() + delimiter
             + getUserName() + delimiter
             + getPassword() + delimiter
             + getName() + delimiter
             + getEmail() + delimiter
             + getAllowAnonymousQuestions();
        return line;
    }

    /** A method to print a single user's info */
    public void printUser() {
        System.out.println("ID:  " + getUserId() + "\t\t" + "Name:  " + getName());
    }
}
