package packt.volume1section5;

import com.aliasi.sentences.IndoEuropeanSentenceModel;
import com.aliasi.sentences.SentenceModel;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;

public class LingPipeExample {
    private static final String PARAGRAPH
            = "We can start with a simple sentence. "
            + "Next we check for sentences ending with exclamation marks! "
            + "Within a sentence we may find real numbers like 3.14159. "
            + "A sentence may contain abbreviations such as found in Mr. Jones. "
            + "We may find ellipses within a sentence …, or at the end of a sentence….";
    
    public static void main(String[] args) {
        TokenizerFactory TOKENIZER_FACTORY
                = IndoEuropeanTokenizerFactory.INSTANCE;
        SentenceModel sentenceModel = new IndoEuropeanSentenceModel();

        List<String> tokenList = new ArrayList();
        List<String> whiteSpaceList = new ArrayList();
        Tokenizer tokenizer = TOKENIZER_FACTORY.tokenizer(
                PARAGRAPH.toCharArray(),0, PARAGRAPH.length());
        tokenizer.tokenize(tokenList, whiteSpaceList);

        String[] tokens = new String[tokenList.size()];
        String[] whiteSpaces = new String[whiteSpaceList.size()];
        tokenList.toArray(tokens);
        whiteSpaceList.toArray(whiteSpaces);
        int[] sentenceBoundaries
                = sentenceModel.boundaryIndices(tokens, whiteSpaces);
        
        int start = 0;
        for (int boundary : sentenceBoundaries) {
            while (start <= boundary) {
                out.print(tokenList.get(start) + whiteSpaceList.get(start + 1));
                start++;
            }
            out.println();
        }
    }
}
