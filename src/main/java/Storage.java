import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Storage {
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> arr = new ArrayList<>();

        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String s;

            while ((s = bufferedReader.readLine()) != null) {
                Task t;
                if (s.charAt(1) == 'T') {
                    t = new Todo(s.substring(7));
                    if (s.charAt(4) == '\u2713') {
                        t.markAsDone();
                    }
                    arr.add(t);
                } else if (s.charAt(1) == 'D') {
                    int index = s.indexOf(58);
                    String ss = s.substring(index);
                    String[] ssArr = ss.split(" ");

                    SimpleDateFormat parser = new SimpleDateFormat("dd-MMM-yyyy");
                    Date date = parser.parse(ssArr[1]);

                    String strTime = ssArr[2].substring(0, 7);
                    LocalTime time = LocalTime.parse(strTime, DateTimeFormatter.ofPattern("hh:mma"));

                    t = new Deadline(s.substring(7, index - 4), date, time);
                    if (s.charAt(4) == '\u2713') {
                        t.markAsDone();
                    }
                    arr.add(t);
                } else {
                    int index = s.indexOf(58);
                    String ss = s.substring(index);
                    String[] ssArr = ss.split(" ");

                    SimpleDateFormat parser = new SimpleDateFormat("dd-MMM-yyyy");
                    Date date = parser.parse(ssArr[1]);

                    String strTime = ssArr[2].substring(0, 7);
                    LocalTime time = LocalTime.parse(strTime, DateTimeFormatter.ofPattern("hh:mma"));

                    t = new Event(s.substring(7, index - 4), date, time);
                    if (s.charAt(4) == '\u2713') {
                        t.markAsDone();
                    }
                    arr.add(t);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

    public void save(ArrayList<Task> arr) {
        try {
            FileWriter writer = new FileWriter(fileName, false);
            BufferedWriter bufferedWriter =  new BufferedWriter(writer);

            for (Task t : arr) {
                bufferedWriter.write(t.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
