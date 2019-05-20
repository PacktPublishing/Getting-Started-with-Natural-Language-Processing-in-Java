package packt.volume1section4;

import static java.lang.System.out;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SimpleJavaTokenizers {
    
        private static final String SENTENCE = 
            "A simple  sentence,\twith\tembbed  white space.";

    public static void main(String[] args) {
        scannerTokenizer();
        breakIteratorTokenizer();
        stringTokenizerTokenizer();
    }
    
        private static void scannerTokenizer() {
        out.println("Scanner Class Example");
        Scanner scanner = new Scanner(SENTENCE);
//        scanner.useDelimiter("[\t ,\\.]");
        List<String> tokenList = new ArrayList<>();

        while (scanner.hasNext()) {
            tokenList.add(scanner.next());
        }
        tokenList.forEach((token) -> out.println("[" + token + "]"));
        out.println();
        out.println();
    }
        
    private static void breakIteratorTokenizer() {
        out.println("BreakIterator Example");
        BreakIterator wordIterator = BreakIterator.getWordInstance();
        wordIterator.setText(SENTENCE);

        int position = wordIterator.first();
        while (position != BreakIterator.DONE) {
            int begin = position;
            out.print(position + "-");
            position = wordIterator.next();
            if (position == BreakIterator.DONE) {
                break;
            } else {
                out.println(position + " ["
                        + SENTENCE.substring(begin, position) + "]");
            }
        }
        out.println();
        out.println();
    }

    private static void stringTokenizerTokenizer() {
        out.println("StringTokenizer Class Example");
//        StringTokenizer stringTokenizer = new StringTokenizer(SENTENCE);
        StringTokenizer stringTokenizer = new StringTokenizer(SENTENCE, " \t.,");
        while (stringTokenizer.hasMoreTokens()) {
            out.println("[" + stringTokenizer.nextToken() + "]");
        }
    }

}
