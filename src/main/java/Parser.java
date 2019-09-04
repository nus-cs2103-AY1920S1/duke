import java.util.Scanner;

public class Parser {

    public Command parse(String fullCmd) {
        Scanner sc = new Scanner(fullCmd);
        String cmd = sc.next().trim();
        String remaining = sc.nextLine().trim();

        return new Command(cmd, remaining);
    }


}