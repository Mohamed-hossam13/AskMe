import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/** A questionManager class to handle the functionalities of the Questions in the system
 * The class also will add the IDs of the questions from and to specific user*/
public class QuestionsManager {
    private final String path;
    private Map<Integer, Question> quesIdToQuesObject;
    private int lastQuestionID;

    public QuestionsManager() {
        quesIdToQuesObject = new HashMap<>();
        path = "questions.txt";
        lastQuestionID = 0;
    }

    /** A method to read all the system questions and store them to start deal with
     * We will map every question id to its question object */
    public void loadDatabase() throws FileNotFoundException{
        lastQuestionID = 0;
        ArrayList<String> lines;
        lines = FileHandler.readFileLines(path);
        for (String line : lines) {
            Question question = new Question(line);
            quesIdToQuesObject.put(question.getQuestionId(), question);
            lastQuestionID = Math.max(lastQuestionID, question.getQuestionId());
        }
    }

    /** A method that takes a current user and adds his related questions' IDs
     * We will loop over all questions in the system and check for the from id and for the to id */
    public void fillUserQuestionFromAndTo(User user) {
        ArrayList<Integer> questionTo = new ArrayList<>();
        ArrayList<Integer> questionFrom = new ArrayList<>();
        for (Question question : quesIdToQuesObject.values()) {
            if (user.getUserId() == question.getToUserId())
                questionTo.add(question.getQuestionId());
            if (user.getUserId() == question.getFromUserId())
                questionFrom.add(question.getQuestionId());
        }
        user.setQuestionsIdsFromMe(questionFrom);
        user.setQuestionsIdsToMe(questionTo);
    }

    /** A method to print all questions sent to the current entered user
     * It takes a user to access questions sent to him */
    public void printQuestionsToMe(User user) {
        ArrayList<Integer> questionID = user.getQuestionsIdsToMe();
        if (questionID.isEmpty()) {
            System.out.println("No Questions");
            return;
        }
        for (int id : questionID) {
            Question question = quesIdToQuesObject.get(id);
            question.printQuestionTo();
        }
        System.out.println();
    }

    /** A method to print all questions the user sent to others */
    public void printQuestionsFromMe(User user) {
        ArrayList<Integer> questionID = user.getQuestionsIdsFromMe();
        if (questionID.isEmpty()) {
            System.out.println("No Questions");
            return;
        }
        for (int id : questionID) {
            Question question = quesIdToQuesObject.get(id);
            question.printQuestionFrom();
        }
        System.out.println();
    }

    /** A method to answer a question asked to the user
     * First, we will ask the user to enter the question id,
     * and we'll check if the id is already in the system
     * Then, we'll ask the user to enter his answer
     * And finally, we'll update the system with the new answer */
    public void AnswerQuestion(User user) throws IOException {
        ArrayList<Integer> askedToMe = user.getQuestionsIdsToMe();

        Scanner scan = new Scanner(System.in);
        int id;
        while (true) {
            System.out.print("Enter Question Id or -1 to cancel: ");
            id = scan.nextInt();

            // Consume the leftover newline character
            scan.nextLine();

            if (id == -1)
                return;
            else if (!quesIdToQuesObject.containsKey(id))
                System.out.println("Id isn't found. Try again...");
            else if (!askedToMe.contains(id))
                System.out.println("You don't have this Id. Try again...");
            else
                break;
        }
        Question question = quesIdToQuesObject.get(id);
        question.printQuestionTo();

        if (!question.getAnswerText().isEmpty())
            System.out.println("Warning: Already answered. Answer will be updated.");


        String answer;
        System.out.print("Enter question answer: ");
        answer = scan.nextLine();
        question.setAnswerText(answer);
        updateDatabase();
    }

    /** A method to update the file with the new changes */
    private void updateDatabase() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        for (Question question : quesIdToQuesObject.values())
            lines.add(question.toString());
        FileHandler.writeFileLines(path, lines, false);
    }
}
