public class Logic {
    private static final String BYE_COMMAND = "bye";
    private static final String spaces = "     ";

    int process(String command) {
        switch (command) {
            case BYE_COMMAND:
                return -1;
            default:
                print(command);
        }
        return 0;
    }

    private void print(String s) {
        System.out.println(spaces + s);
    }
}
