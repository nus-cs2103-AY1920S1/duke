import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

    public Parser(){}

    public void readTask(String s, TaskList t) throws ParseException {
        DateFormat df = new SimpleDateFormat("E, MMM dd yyyy HH:mm");

        if(s.contains("[T]")){
            if(s.contains( "["+"\u2713"+"]")){
                t.Add(new ToDo(s.substring(7), true));
            }
            else{
                t.Add(new ToDo(s.substring(7)));
            }
        }
        else if(s.contains("[E]")){

            int start = s.indexOf('(');
            String e = s.substring(7,start-1);
            String dl = s.substring(start+5, start+27);
            Date at = df.parse(dl);
            if(s.contains( "["+"\u2713"+"]")){
                t.Add(new Event(e,at, true));
            }
            else{
                t.Add(new Event(e,at));
            }
        }
        else{
            int sl = s.indexOf('(');
            String d = s.substring(7,sl-1);
            String by = s.substring(sl+4);
            if(s.contains( "["+"\u2713"+"]")){
                t.Add(new Deadline(d,by,true));
            }
            else
                t.Add(new Deadline(d,by));
        }
    }


}
