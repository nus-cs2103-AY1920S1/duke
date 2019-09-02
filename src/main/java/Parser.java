public class Parser {
    private String[] inputArr;

    public Parser(String input) {
        this.inputArr = input.split(" ", 2);
    }

    public String[] getInputArr() {
        return inputArr;
    }

    public String getCommand() {
        return inputArr[0];
    }

    public int getPointer() {
        return Integer.parseInt(inputArr[1]);
    }

    public String getDetail() {
        return inputArr[1];
    }
}
