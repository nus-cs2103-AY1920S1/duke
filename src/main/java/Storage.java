import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private String filename;

    public Storage(String filename){
        this.filename = filename;
    }

    public ArrayList<Task> load() throws Exception  {
        ArrayList<Task>list = new ArrayList<>();

        BufferedReader br = Files.newBufferedReader(Paths.get(filename));
        String lineToRead;

        while ((lineToRead = br.readLine()) != null) {
            if( (!lineToRead.equals("")) && (lineToRead.charAt(0)=='T') ) {
                Task newTask = ToDo.outputAsToDo(lineToRead);
                list.add(newTask);
            }else if( (!lineToRead.equals("")) && (lineToRead.charAt(0)=='D')){
                Task newTask = Deadline.outputAsDeadline(lineToRead);
                list.add(newTask);
            }else if((!lineToRead.equals("")) && (lineToRead.charAt(0)=='E')){
                Task newTask = Event.outputAsEvent(lineToRead);
                list.add(newTask);
            }else{}
        }
        return list;

    }

    public void printToOutput(TaskList tasks) throws FileNotFoundException {
        PrintStream outputTo = new PrintStream(filename);
        outputTo.println(tasks.printForOutput());
        outputTo.close();
    }

    public void emptyOutput() throws FileNotFoundException {
        PrintStream outputTo = new PrintStream(filename);
        outputTo.println("");
        outputTo.close();
    }
}
