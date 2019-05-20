package packt.volume1section5;

import static java.lang.System.out;
import java.text.BreakIterator;

public class BreakIteratorExample {

    private static final String PARAGRAPH
            = "We can start with a simple sentence. "
            + "Next we check for sentences ending with exclamation marks! "
            + "Within a sentence we may find real numbers like 3.14159. "
            + "A sentence may contain abbreviations such as found in Mr. Jones. "
            + "We may find ellipses within a sentence …, or at the end of a sentence….";

    public static void main(String[] args) {
//        Locale currentLocale = new Locale("en", "US");
        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance();
        sentenceIterator.setText(PARAGRAPH);
        int boundaryPosition = sentenceIterator.first();
        while (boundaryPosition != BreakIterator.DONE) {
            int start = boundaryPosition;
            out.print(boundaryPosition + "-");
            boundaryPosition = sentenceIterator.next();
            int end = boundaryPosition;
            if (end == BreakIterator.DONE) {
                break;
            }
            out.println(boundaryPosition + " ["
                    + PARAGRAPH.substring(start, end) + "]");
        }
    }
}
