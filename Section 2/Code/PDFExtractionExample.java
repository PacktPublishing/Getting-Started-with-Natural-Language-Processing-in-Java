package packt.pdfextractionexample;

import java.io.File;
import static java.lang.System.out;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFExtractionExample {
    
    public static void main(String[] args) {
        try {
            PDDocument document = PDDocument.load(new File("JEETT.pdf"));
            PDDocumentInformation pdd = document.getDocumentInformation();

            out.println("Author: " + pdd.getAuthor());
            out.println("Number of Pages: " + document.getNumberOfPages());
            out.println("Title: " + pdd.getTitle());
            out.println("Subject: " + pdd.getSubject());
            out.println();

            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String documentText = pdfTextStripper.getText(document);
            System.out.println(documentText);

            pdfTextStripper.setStartPage(50);
            pdfTextStripper.setEndPage(50);
            out.println(pdfTextStripper.getText(document));           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
