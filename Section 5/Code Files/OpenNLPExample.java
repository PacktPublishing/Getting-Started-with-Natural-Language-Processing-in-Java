package packt.volume1section5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class OpenNLPExample {

    private static final String PARAGRAPH
            = "We can start with a simple sentence. "
            + "Next we check for sentences ending with exclamation marks! "
            + "Within a sentence we may find real numbers like 3.14159. "
            + "A sentence may contain abbreviations such as found in Mr. Jones. "
            + "We may find ellipses within a sentence …, or at the end of a sentence….";

    public static void main(String[] args) {
//        http://opennlp.sourceforge.net/models-1.5/

        try (InputStream inputStream = new FileInputStream(new File("en-sent.bin"))) {
            SentenceModel modelStream = new SentenceModel(inputStream);
            SentenceDetectorME detector = new SentenceDetectorME(modelStream);
            
            String sentences[] = detector.sentDetect(PARAGRAPH);
            double probablities[] = detector.getSentenceProbabilities();
            for (int i=0; i<sentences.length; i++) {
                out.println(sentences[i] + " - " + probablities[i]);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        
}
