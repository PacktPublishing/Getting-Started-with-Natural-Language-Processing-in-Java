package packt;

import static java.lang.System.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class DataValidationExamples {

    public static void main(String[] args) {
        out.println(validateInt("23"));
        out.println(validateInt("23.5"));
        out.println(validateInt("cat"));
        // validateFloat can be used in a similar fashion

        String dateFormat = "MM/dd/yyyy";
        out.println(validateDate("Friday", dateFormat));
        out.println(validateDate("3/12/1990", dateFormat));
        out.println(validateDate("03/12/1990", dateFormat));

        out.println(validateEmail("name@company.com"));
        out.println(validateEmail("name@company"));
        out.println(validateEmail("name"));

        out.println(validateZipCode("12345"));
        out.println(validateZipCode("12345-1234"));
        out.println(validateZipCode("name"));

    }

    public static String validateInt(String input) {
        try {
            int validInt = Integer.parseInt(input);
            return validInt + " is a valid integer";
        } catch (NumberFormatException ex) {
            return input + " is not a valid integer";
        }
    }

    public static String validateDate(String dateString, String dateFormat) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            Date date = simpleDateFormat.parse(dateString);
            if (simpleDateFormat.format(date).equals(dateString)) {
                return dateString.toString() + " is a valid date";
            } else {
                return dateString.toString() + " is not a valid date";
            }
        } catch (ParseException ex) {
            return dateString.toString() + " is not a valid date";
        }

    }

    public static String validateEmail(String emailString) {
        try {
            InternetAddress testEmail = new InternetAddress(emailString);
            testEmail.validate();
            return emailString + " is a valid email address";
        } catch (AddressException ex) {
            return emailString + " is not a valid email address";
        }
    }

    public static String validateZipCode(String zipCodeString) {
        String zipRegex = "^[0-9]{5}(?:-[0-9]{4})?$";
        Pattern pattern = Pattern.compile(zipRegex);
        Matcher matcher = pattern.matcher(zipCodeString);
        if (matcher.matches()) {
            return zipCodeString + " is a valid zip code";
        } else {
            return zipCodeString + " is not a valid zip code";
        }

    }
    
}
