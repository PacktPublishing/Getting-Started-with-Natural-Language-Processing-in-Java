package packt.volume1section4;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.PorterStemmerTokenizerFactory;
import com.aliasi.tokenizer.Tokenization;
import com.aliasi.tokenizer.TokenizerFactory;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import static java.lang.System.out;
import java.util.List;
import java.util.Properties;
import opennlp.tools.stemmer.PorterStemmer;

public class StemmingAndLemmaztion {

    private static final String words[]
            = {"bank", "banking", "banks", "banker", "banked", "bankart"};

    public static void main(String[] args) {
        porterStemmer();
        lingPipeStemmer();
        stanfordLemmatizer();
    }

    private static void porterStemmer() {
        out.println("PorterStemmer Example");
        PorterStemmer porterStemmer = new PorterStemmer();
        for (String word : words) {
            String stem = porterStemmer.stem(word);
            out.println("Word: " + word + "  Stem: " + stem);
        }
        out.println();
    }
    private static void lingPipeStemmer() {
        out.println("LingPipe Stemmer Example");
        TokenizerFactory tokenizerFactory
                = IndoEuropeanTokenizerFactory.INSTANCE;
        TokenizerFactory porterFactory
                = new PorterStemmerTokenizerFactory(tokenizerFactory);

        for (String word : words) {
            Tokenization tokenizer = new Tokenization(word, porterFactory);
            String[] stems = tokenizer.tokens();
            out.print("Word: " + word);
            for (String stem : stems) {
                out.println("  Stem: " + stem);
            }
        }
        out.println();
    }

    private static void stanfordLemmatizer() {
        out.println("Stanford Lemmatizer Example");
        String sentences = "We have encountered numerous difficulties in our "
                + "travels. However, amoungst these difficulties "
                + "the most daunting has been maintaining perserverance.";

        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = new Annotation(sentences);
        pipeline.annotate(annotation);

        // Iterate over all of the sentences found
        //SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
        //SLF4J: Defaulting to no-operation (NOP) logger implementation
        //SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
        List<CoreMap> sentenceList = annotation.get(SentencesAnnotation.class);
        for (CoreMap sentence : sentenceList) {
            // Iterate over all tokens in a sentence
            for (CoreLabel word : sentence.get(TokensAnnotation.class)) {
                // Retrieve and add the lemma for each word into the list of lemmas
                out.println("Word: " + word.originalText() + 
                        "  Lemma: " + word.lemma());
            }
        }
        out.println();
    }
    
}
