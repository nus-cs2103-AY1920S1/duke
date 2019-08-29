import java.util.Scanner;
public class Ui {
    public String input = "";
    Scanner sc = new Scanner(System.in);
    public void setInput(){
        String newInput = sc.nextLine();
        this.input = newInput;
    }
    public String getInput(){
        return this.input;
    }

}
