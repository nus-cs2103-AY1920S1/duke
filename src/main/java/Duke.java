import java.util.ArrayList;

public class Duke {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    private ArrayList<String> tasks;

    public Duke() {
        tasks = new ArrayList<>();
    }
    
    private String makeSpace(int n) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < n; i++) {
            str.append(' ');
        }
        return str.toString();
    }
    
    public void greet() {
        System.out.print(HORIZONTAL_LINE);
        
        String logo = makeSpace(5) + " ____        _        \n"
                + makeSpace(5) + "|  _ \\ _   _| | _____ \n"
                + makeSpace(5) + "| | | | | | | |/ / _ \\\n"
                + makeSpace(5) + "| |_| | |_| |   <  __/\n"
                + makeSpace(5) + "|____/ \\__,_|_|\\_\\___|\n";

        String welcomeMessage = String.format("%s\n%sHello! I'm Duke!\n%sWhat can I do for you?", logo, makeSpace(5), 
                makeSpace(5));
        System.out.println(welcomeMessage);
        System.out.println(HORIZONTAL_LINE);
    }

    public void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.printf("%sBye! Hope to see you again soon! \u263A\n", makeSpace(5));
        System.out.println(HORIZONTAL_LINE);
    }

    public void evaluate(String action) {
        System.out.println(HORIZONTAL_LINE);

        if (action.equals("list")) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%s%d. %s\n", makeSpace(5), i + 1, tasks.get(i));
            }
        } else {
            System.out.printf("%sadded: %s\n", makeSpace(5), action);
            tasks.add(action);
        }
        
        System.out.println(HORIZONTAL_LINE);
    }
}
