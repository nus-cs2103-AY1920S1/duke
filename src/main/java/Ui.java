import java.util.Scanner;
public class Ui {
    public String input = "";
    public String[] inputArr;
    Scanner sc = new Scanner(System.in);
    public void setInput(){
        String newInput = sc.nextLine();
        this.input = newInput;
        this.inputArr = newInput.split(" ");
    }
    public String getInput(){
        return this.input;
    }
    public String[] getInputArr(){
        return this.inputArr;
    }
    public String getTodoDesc(){
        return this.input.substring(4).trim();
    }

}
