import java.util.ArrayList;

/** We use a parent_id to support threads in the system
 * So if the asked question is the first to start with in the thread,
 * we will give it -1 as it has no parent question
 * The answer text will be null if the question isn't answered yet */
public class Question {
    int questionId;
    int parentQuestionId;
    int fromUserId;
    int toUserId;
    int isAnonymousQuestion;
    String questionText;
    String answerText;

    public Question() {
        questionId = parentQuestionId =
        fromUserId = toUserId = -1;
        isAnonymousQuestion = 1;
    }

    /** A parametrized constructor that takes a line containing all the data for a question
     * and split each single data and assign it to its variable */
    public Question(String line) {
        ArrayList<String> questionData = FileHandler.splitLine(line, ",");
        if (questionData.size() == 6) {
            questionData.add("");
        }
        questionId = Integer.parseInt(questionData.get(0));
        parentQuestionId = Integer.parseInt(questionData.get(1));
        fromUserId = Integer.parseInt(questionData.get(2));
        toUserId = Integer.parseInt(questionData.get(3));
        isAnonymousQuestion = Integer.parseInt(questionData.get(4));
        questionText = questionData.get(5);
        answerText = questionData.get(6);
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public int getQuestionId() { return questionId; }

    public int getParentQuestionId() { return parentQuestionId; }

    public int getFromUserId() { return fromUserId; }

    public int getToUserId() { return toUserId; }

    public int getIsAnonymousQuestion() { return isAnonymousQuestion; }

    public String getQuestionText() { return questionText; }

    public String getAnswerText() { return answerText; }

    /** A method to return a String containing all the question's info to write to the file */
    public String toString() {
        String line;
        String delimiter = ",";
        line = getQuestionId() + delimiter
             + getParentQuestionId() + delimiter
             + getFromUserId() + delimiter
             + getToUserId() + delimiter
             + getIsAnonymousQuestion() + delimiter
             + getQuestionText() + delimiter
             + getAnswerText();
        return line;

    }

    /** A method to print the questions sent to me by others */
    public void printQuestionTo() {
        System.out.print("Question Id (" + getQuestionId() + ") from User Id (" + getFromUserId() + ")");
        System.out.print("\t\tQuestion: " + getQuestionText());
        if (!getAnswerText().isEmpty())
            System.out.println("\t\tAnswer: " + getAnswerText());
        else
            System.out.println("\t\tNot Answered Yet");
    }

    /** A method to print the question and its answer asked by me to another user
     * We also show in the print context if the anonymous question is allowed or not */
    public void printQuestionFrom() {
        System.out.print("Question Id (" + getQuestionId() + ") ");
        if (isAnonymousQuestion != 0)
            System.out.print("!AQ to User Id (" + getToUserId() + ")");
        else
            System.out.print("to User Id (" + getToUserId() + ")");

        System.out.print("\tQuestion: " + getQuestionText() + " ");

        if (getAnswerText().isEmpty())
            System.out.println("\tNot Answered Yet");
        else
            System.out.println("\tAnswer: " + getAnswerText());
    }
}

//101,-1,11,13,0,Should I learn C++ first or Java,I think C++ is a better Start
//203,101,11,13,0,Why do you think so!,Just Google. There is an answer on Quora.
//205,101,45,13,0,What about python?,
//211,-1,13,11,1,It was nice to chat to you,For my pleasure Dr Mostafa
//212,-1,13,45,0,Please search archive before asking,
//300,101,11,13,1,Is it ok to learn Java for OOP?,Good choice
//301,-1,11,13,0,Free to meet?,
//302,101,11,13,1,Why so late in reply?,
