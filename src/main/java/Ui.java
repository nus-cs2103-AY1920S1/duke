import java.util.Scanner;

/**
 * User interaction class to take care of all user interactions in the program.
 */
public class Ui {
    public String input = "";
    public String[] inputArr;
    Scanner sc = new Scanner(System.in);

    /**
     * Sets the input from the user.
     */
    public void setInput() {
        String newInput = sc.nextLine();
        this.input = newInput;
        this.inputArr = newInput.split(" ");
    }

    /**
     * Returns input from the user.
     *
     * @return input from the user.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Returns input of user as a String array.
     *
     * @return input of user as a String array.
     */
    public String[] getInputArr() {
        return this.inputArr;
    }

    /**
     * Returns description of Todo task.
     *
     * @return description of Todo task.
     */
    public String getTodoDesc() {
        return this.input.substring(4).trim();
    }

}
