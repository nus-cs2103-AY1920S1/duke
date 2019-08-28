public class Parser {

    public static Command parse(String str) {
        String[] arr = str.split(" ");
        String next = arr[0];
        Command c;
        switch (next) {
            case "bye":
                c = new ExitCommand();
                break;
            case "list":
                c = new ListCommand();
                break;
            case "done":
                c = new DoneCommand(Integer.parseInt(arr[1]) - 1);
                break;
            case "delete":
                c = new DeleteCommand(Integer.parseInt(arr[1]) - 1);
                break;
            default:
                    c = new AddCommand(arr);
                    break;
                }
                return c;
    }
}
