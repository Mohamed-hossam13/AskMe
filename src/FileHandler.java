import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileHandler {

    /** A method to read all the lines in the file */
    public static ArrayList<String> readFileLines(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scan = new Scanner(file);
        ArrayList<String> result = new ArrayList<>();
        while (scan.hasNextLine()) {
            result.add(scan.nextLine());
        }
        scan.close();
        return result;
    }

    /** A method to write new lines into the file */
    public static void writeFileLines(String path, ArrayList<String> lines, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(path, append);
        PrintWriter writer = new PrintWriter(fileWriter);
        for (String line : lines)
            writer.println(line);
        fileWriter.close();
    }

    /** A method to split every line in the file into separate data to assign each to its proper type
     * It returns an ArrayList that contains every data in a single index */
    public static ArrayList<String> splitLine(String line, String delimiter) {
        ArrayList<String> str = new ArrayList<>();
        Scanner scan = new Scanner(line);
        scan.useDelimiter(Pattern.compile(delimiter));

        while (scan.hasNext()) {
            str.add(scan.next());
        }
        scan.close();
        return str;
    }
}
