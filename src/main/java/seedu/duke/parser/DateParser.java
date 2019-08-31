package seedu.duke.parser;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateParser {
    public Date parseDate(String strDate){
        try {
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HHmm");
            return formatter1.parse(strDate);
        }catch(ParseException e){
            return null;
        }
    }

}
