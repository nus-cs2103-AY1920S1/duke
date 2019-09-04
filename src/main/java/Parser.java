import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;

public class Parser {
    // deals with making sense of the user command
    private Scanner sc;

    public Parser(Scanner sc) {
        this.sc = sc;
    }

    public String nextCommand() {
        return sc.nextLine();
    }

    public String[] breakDownCommand(String str) {
        return str.split(" ");
    }

    public String[] breakDownDescription(String str) {
        return str.split("/");
    }

    public String getCommand(String[] arr) {
        return arr[0];
    }

    public Todo getTodo(String line) {
        String task = line.substring(5);
        Todo todo = new Todo(task);
        return todo;
    }

    public Date getDate(String[] descArr, Storage storage) throws ParseException {
        return storage.convertToDate(descArr[1].substring(3));
    }

    public String getDeadlineDesc(String[] descArr) {
        return descArr[0].substring(9, descArr[0].length() - 1);
    }

    public String getEventDesc(String[] descArr) {
        return descArr[0].substring(6, descArr[0].length() - 1);
    }

    public int getTaskNum(String[] arr) {
        return Integer.parseInt(arr[1]) - 1;
    }
}