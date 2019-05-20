package packt.volume1section5;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import static java.lang.System.out;

import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorFactory;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSample;
import opennlp.tools.sentdetect.SentenceSampleStream;

public class SentenceModelTrainingExample {

    private static final String PARAGRAPH
            = "We can start with a simple sentence. "
            + "Next we check for sentences ending with exclamation marks! "
            + "Within a sentence we may find real numbers like 3.14159. "
            + "A sentence may contain abbreviations such as found in Mr. Jones. "
            + "We may find ellipses within a sentence …, or at the end of a sentence….";

    private static String getTrainingText() {
        try (FileReader fileReader = new FileReader("sentence.train");
                BufferedReader bufferReader = new BufferedReader(fileReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferReader.readLine()) != null) {
                stringBuilder.
                        append(line).
                        append(System.lineSeparator());
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static SentenceModel getTrainingModel(final String trainText) {
        InputStreamFactory inputStreamfactory
                = () -> new ByteArrayInputStream(trainText.getBytes());
        try (ObjectStream<String> lineStream = new PlainTextByLineStream(
                    inputStreamfactory, Charset.forName("UTF-8"));
                ObjectStream<SentenceSample> sampleTextStream
                    = new SentenceSampleStream(lineStream)) {
            SentenceDetectorFactory sentenceDetectorFactory = 
                    new SentenceDetectorFactory("en", true, null, null);
            return SentenceDetectorME.train("en", sampleTextStream, 
                    sentenceDetectorFactory, 
                    TrainingParameters.defaultParams());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void testModel(SentenceModel sentenceModel) {
        SentenceDetector sentenceDetector = new SentenceDetectorME(sentenceModel);
        String[] sentences = sentenceDetector.sentDetect(PARAGRAPH);

        out.println();
        System.out.println("Number of Sentences: " + sentences.length);
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    }
    
    public static void main(String[] args) throws IOException {
        out.println("Training Text ------------");
        out.println(getTrainingText());
        out.println();

        out.println("Testing Model ------------");
        SentenceModel sentenceModel = getTrainingModel(getTrainingText());
        testModel(sentenceModel);
    }
}
