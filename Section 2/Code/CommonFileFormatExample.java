package packt.commonfileformatexample;

import au.com.bytecode.opencsv.CSVReader;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;

public class CommonFileFormatExample {

    public static void main(String[] args) {
        try (FileReader file = new FileReader("sample.csv");
                BufferedReader br = new BufferedReader(file);) {
            String line;
            while (((line = br.readLine()) != null)) {
                String[] values = line.split(",");
                for (String element : values) {
                    out.print(element + " ");
                }
                out.println();
            }
            out.println();

            CSVReader csvReader = new CSVReader(new FileReader("sample.csv"));
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                for (String element : values) {
                    out.print(element + " ");
                }
                out.println();
            }

            processPerson();
            processPersons();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void displayPerson(JsonParser parser) {
        try {
            while (parser.nextToken() != null) {
                parser.nextToken();
                if ("id".equals(parser.getCurrentName())) {
                    parser.nextToken();
                    out.println("ID: " + parser.getIntValue());
                }
                parser.nextToken();
                if ("firstname".equals(parser.getCurrentName())) {
                    parser.nextToken();
                    out.println("First Name: " + parser.getText());
                }
                parser.nextToken();
                if ("lastname".equals(parser.getCurrentName())) {
                    parser.nextToken();
                    out.println("Last Name: " + parser.getText());
                }
                parser.nextToken();
                out.println();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void processPerson() {
        try {
            out.println("---Person---");
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(new File("sample1.json"));
            displayPerson(parser);
            parser.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
        public static void processPersons() {
        try {
            out.println("---People---");
            JsonFactory jsonfactory = new JsonFactory();
            JsonParser parser = jsonfactory.createParser(new File("sample2.json"));
            parser.nextToken();
            parser.nextToken();
            if ("people".equals(parser.getCurrentName())) {
                parser.nextToken();
                parser.nextToken();
                String token = parser.getCurrentName();
                if ("persons".equals(token)) {
                    parser.nextToken();
                    displayPerson(parser);
                }
            }
            parser.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
