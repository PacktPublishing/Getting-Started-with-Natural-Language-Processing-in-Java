package packt;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import static java.lang.System.out;

public class BasicCleaningOperations {

    public static void main(String[] args) {
        String text = "A Simple demonstration  of basic DATA\tleaning operations. ";
        text += "The second Sentence\t\talso has issues.";

        out.println("Original text: " + text);

        String cleanedText = text.toLowerCase();
        //NOTE trim only works on leading/trailing spaces
        cleanedText = cleanedText.trim();
        cleanedText = cleanedText.replaceAll("\t", " ");
        while (cleanedText.contains("  ")) {
            cleanedText = cleanedText.replaceAll("  ", " ");
        }
        out.println("Cleaned text: " + cleanedText);
        out.println();

        out.println("Splitter Example");
        Iterable<String> iterator = Splitter.on(CharMatcher.whitespace())
                .omitEmptyStrings()
                .trimResults()
                .split(text);
        iterator.forEach(token -> out.print(token + ' '));
        out.println();
        out.println();

        out.println("CharMatcher Example");
        cleanedText = CharMatcher.whitespace().trimAndCollapseFrom(text, ' ');
        // trim whitespace at ends, and replace/collapse whitespace into single spaces
        out.println(cleanedText);
        out.println();

        out.println("CharMatcher Retention Example");
        String retentionText = CharMatcher.javaDigit()
                .or(CharMatcher.whitespace())
                .or(CharMatcher.javaLowerCase())
                .retainFrom("He is 5 FEET and 6 inches tall.");
        out.println(retentionText);

    }
}
