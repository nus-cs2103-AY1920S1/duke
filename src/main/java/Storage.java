import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    /**
     * variable is the filename to output to
     */
    private String filename;

    /**
     * constructor
     * @param filename is the name of hard drive file to output to
     */
    public Storage(String filename){
        this.filename = filename;
    }

    /**
     * to load the list from output
     * @return ArrayList of tasks to create TaskList
     * @throws Exception in case BufferedReader is unable to read the filename
     */
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

    /**
     * prints list on the output text file
     * @param tasks is the list of tasks to be printed
     * @throws FileNotFoundException in case filename is not found
     */
    public void printToOutput(TaskList tasks) throws FileNotFoundException {
        PrintStream outputTo = new PrintStream(filename);
        outputTo.println(tasks.printForOutput());
        outputTo.close();
    }

    /**
     * empties out the output if required, but not the TaskList
     * @throws FileNotFoundException in case the filename is not found
     */
    public void emptyOutput() throws FileNotFoundException {
        PrintStream outputTo = new PrintStream(filename);
        outputTo.println("");
        outputTo.close();
    }
}
