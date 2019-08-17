import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	String[] itemList = new String[100];
    	int itemCounter = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
        	if (userInput.equals("list")) {
        		String prettifiedList = listConverter(itemList);
        		System.out.println(prettifiedList);
			} else {
        		itemList[itemCounter] = userInput;
        		itemCounter++;
				System.out.println("added: " + userInput);
			}
        	userInput = scanner.nextLine();
		}
        System.out.print("Bye. Hope to see you again soon!");
    }

    private static String listConverter(String[] list) {
    	StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.length; i++)
		{
			if (list[i] != null) {
				sb.append(i + 1 + ". " + list[i]);
				sb.append("\n");
			}
		}
		return sb.toString().trim();
	}
}
