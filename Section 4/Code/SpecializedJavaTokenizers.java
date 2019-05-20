package packt.volume1section4;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenizerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class SpecializedJavaTokenizers {

    private static final String SENTENCE
            = "A simple  sentence,\twith\tembbed  white space.";

    public static void main(String[] args) {
        simpleTokenizer();
        tokenizerMEtokenizer();
        whitespaceTokenizer();
        lingPipeTokenizer();
    }

    private static void simpleTokenizer() {
        out.println("SimpleTokenizer");
        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
        String tokens[] = simpleTokenizer.tokenize(SENTENCE);
        for (String token : tokens) {
            out.println("[" + token + "]");
        }
        out.println();
        out.println();
    }

    private static void tokenizerMEtokenizer() {
        out.println("TokenizerME");
        try {
            InputStream modelInputStream = new FileInputStream(new File("en-token.bin"));
            TokenizerModel tokenizerModel = new TokenizerModel(modelInputStream);
            Tokenizer tokenizer = new TokenizerME(tokenizerModel);
            String tokens[] = tokenizer.tokenize(SENTENCE);
            for (String token : tokens) {
                out.println("[" + token + "]");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        out.println();
        out.println();
    }

    private static void whitespaceTokenizer() {
        out.println("WhitespaceTokenizer");
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(SENTENCE);
        for (String token : tokens) {
            out.println("[" + token + "]");
        }
        out.println();
        out.println();
    }

    private static void lingPipeTokenizer() {
        out.println("LingPipe Tokenizer");
        char text[] = SENTENCE.toCharArray();
        TokenizerFactory tokenizerFactory = IndoEuropeanTokenizerFactory.INSTANCE;
        com.aliasi.tokenizer.Tokenizer tokenizer = tokenizerFactory.tokenizer(
                text, 0, text.length);
        for (String token : tokenizer) {
            out.println("[" + token + "]");
        }
    }
}
