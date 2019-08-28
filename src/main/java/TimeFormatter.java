import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class TimeFormatter {

   public static Date convertToDate(String str) {
       SimpleDateFormat myFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
       try {
           return myFormatter.parse(str);

       } catch (ParseException err) {
           System.out.println(err);

       }
       return null;
   }

   public static String convertToString(Date idea) {
       SimpleDateFormat myFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
       return myFormatter.format(idea);

   }

   }



