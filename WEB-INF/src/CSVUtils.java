import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

/* Class that parses a .csv file, taken from online */
public class CSVUtils {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    private HashMap<String, HashMap<String, String>> allLines;
    private boolean exceptionCaught = false;
    private String exceptionString = "";

    // Extract data from a csv file with path "csvFile", that has "ignoreLines" number of rows to be ignroed,
    // and with column data corresponding to the "headers" list provided
    public CSVUtils(String csvFile, List<String> headers, int ignoreLines) throws Exception {
        Scanner scanner = null;
        allLines = new HashMap<>();
        try {
            scanner = new Scanner(new File(csvFile));

            for (int i = 0; i < ignoreLines; i++)
                scanner.nextLine(); // ignore header line

            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                HashMap<String, String> record = new HashMap<>();

                // match each record to each header
                for (int i = 0; i < headers.size() && i < line.size(); i++) {
                    record.put(headers.get(i), line.get(i).replace("\"",""));
                }
                allLines.put(line.get(0), record);    // Use first column as unique ID
            }
        } catch (Exception e) {
            exceptionCaught = true;
            exceptionString = e.getMessage();
            throw e;
        } finally {
            if (scanner != null)
                scanner.close();
        }
    }

    public HashMap<String, HashMap<String, String>> getLines() {
        if (!exceptionCaught) {
            return allLines;
        } else {
            return allLines;
        }
    }

    public List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }

}