import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/* TODO Fix case when task loaded is done */
public class Storage {

    private WriteFile txtFile;
    private String path;
    private TaskList tasks;

    //private TaskList tasks;
    public Storage(WriteFile data, String path, TaskList currTasks) {
            /**
             *  constructor for storage
             *  saves writefile class to write into text file
             *  saves path where file is
             *  and tasklist to write to and call methods from it
             *  @params WriteFile, String, TaskList
             * @returns none
             */
        txtFile = data;
        this.path = path;
        tasks = currTasks;
    }
    public void initStorage() {
        /**
         *  reads text file from path saved
         *  this is the reading method, it calls
         *  load task method to load the task into tasklist
         *  uses filereader and buffer reader to read file
         *  line by line
         *  handles both file not found and
         *  IO exceptions.
         *  @params WriteFile, String, TaskList
         * @returns none
         */
        String line = "";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(path);
            // always need to wrap file reader in buffer reader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                loadTask(line);
            }
            // close file
            bufferedReader.close();
        } catch(FileNotFoundException ex) {
            System.out.println("I can't see");
        } catch(IOException ex) {
            System.out.println("That sign will stop me, cos I can't read!");
        }
    }
    public void loadTask(String task) {
        /**
         *   string task is a line loaded from text file
         *   line is unparsed, use scanner to parse it
         *   function separates task info from
         *   task done, task type, and date to do task by
         *   @params String task: line of un-parsed task
         *   @returns none
         */
        // TODO Make parser class
        ArrayList<Task> taskArr = tasks.getList();
        // converts tasks in string from storage to taskarr
        if (task.equals("")) {
            return;
        }
        Scanner stringSc = new Scanner(task);
        String type = stringSc.next();
        stringSc.next();
        int isDone = stringSc.nextInt();
        stringSc.next();
        String mainInfo = "";
        String info = stringSc.next();
        while (!info.equals("|")) {

            mainInfo = mainInfo + info;
            info = stringSc.next();
        }
        if (type.equals("D")) {
            // take up empty input
            String by = "";
            String curr = stringSc.next();
            while (!curr.equals("|")) {
                by = by + " " + curr;
                curr = stringSc.next();
            }
            tasks.addDeadline(mainInfo,by);

        } else if (type.equals("E")) {

            String by = "";
            String curr = stringSc.next();
            while (!curr.equals("|")) {
                by = by + " " + curr;
                curr = stringSc.next();
            }
            tasks.addEvent(mainInfo,by);
        } else {
            ToDo newTask = new ToDo(mainInfo, "T","");
            taskArr.add(newTask);
        }
    }
    public void saveToTextFile() {
        /**
         *   saves all tasks in taskArr to textfile
         *   as specified in tasklist
         *   for each task parses to get diff parts
         *   of task, info, do by and type.
         *   writes to main textfile one at a time.
         *   setappend of writefile to true only after firstline
         *   since want to overwrite old file
         *   @params none
         *   @returns none
         *
         */
        ArrayList<Task> taskArr = tasks.getList();
        for (Task t: taskArr) {
            String mainTxt = "";
            String type = t.getType();
            String status = (t.getDone() ? "1" : "0");
            String info = t.getTaskInfo();
            String by = t.getByOrig();
            if (type == "T") {
                mainTxt = mainTxt + type + " | " + status + " | "
                        + info + " | " + by + " | ";
            } else {
                mainTxt = mainTxt + type + " | " + status + " | "
                        + info + " | " + by + " | ";
            }
            txtFile.writeToFile(mainTxt);
            txtFile.setAppend(true);
        }
        txtFile.setAppend(false);
    }
}
