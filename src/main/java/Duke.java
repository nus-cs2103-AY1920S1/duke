import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        String border = "    ____________________________________________________________";
        System.out.println(border + "\n" + "     Hello! I'm Duke\n     What can i do for you?" + "\n" + border);

        ToDoList work = new ToDoList();

        try{
            work.run();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }

    }
}
