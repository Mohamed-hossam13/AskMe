import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
}
