import java.io.IOException;

public class Parser {

    public static Command parse(String input) throws IOException{

        if (input.equals("list")) {
            return new ListCommand();
        } else {
            String[] temp = input.split(" ");
            if (temp[0].equals("done")) {
                return new DoneCommand(temp);

            } else if (temp[0].equals("delete")) { //command to delete task
                return new DeleteCommand(temp);

            } else { //command to add task to list
                return new AddCommand(input, temp);
            }
        }

    }
}
