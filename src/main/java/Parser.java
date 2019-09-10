import java.util.Scanner;
import java.util.NoSuchElementException;

public class Parser {

    public static Command parse(String fullCmd) {
        Scanner sc = new Scanner(fullCmd);
        String cmd = sc.next().trim();
        String remaining;
        try {
            remaining = sc.nextLine().trim();
        } catch(NoSuchElementException e) {
            remaining = "";
        }
        sc.close();
        return new Command(cmd, remaining);
    }


}