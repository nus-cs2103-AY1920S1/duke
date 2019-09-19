import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

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
    public void initStorage() throws IOException, ParseException, DukeException {
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
        // FileReader reads text files in the default encoding.
        FileReader fileReader = new FileReader(path);
        System.out.println(path);
        // always need to wrap file reader in buffer reader
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while((line = bufferedReader.readLine()) != null) {
            loadTask(line);
        }
        bufferedReader.close();
    }
    public void loadTask(String task) throws ParseException, DukeException {
        /**
         *   string task is a line loaded from text file
         *   line is unparsed, use scanner to parse it
         *   function separates task info from
         *   task done, task type, and date to do task by
         *   @params String task: line of un-parsed task
         *   @returns none
         */
        System.out.println("hi");
        ArrayList<Task> taskArr = tasks.getList();
        // converts tasks in string from storage to taskarr
        if (task.equals("")) {
            return;
        }
        String[] splitTask = task.split("-");
        String type = splitTask[0];
        int isDone = Integer.parseInt(splitTask[1]);
        String mainInfo = splitTask[2];

        if (type.equals("D")) {
            // take up empty input
            String by = splitTask[3];
            tasks.addDeadline(mainInfo,by,isDone);
        } else if (type.equals("E")) {
            String by = splitTask[3];
            tasks.addEvent(mainInfo,by,isDone);
        } else {
            tasks.addTodo(mainInfo,isDone);
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
        txtFile.writeToFile(""); // overwrite any existing
        ArrayList<Task> taskArr = tasks.getList();
        for (Task t: taskArr) {
            String mainTxt = "";
            String type = t.getType();
            String status = (t.getDone() ? "1" : "0");
            String info = t.getTaskInfo();
            String by = t.getByOrig();
            if (type == "T") {
                mainTxt = mainTxt + type + "-" + status + "-"
                        + info + "-" + by;
            } else {
                mainTxt = mainTxt + type + "-" + status + "-"
                        + info + "-" + by;
            }
            txtFile.writeToFile(mainTxt);
            txtFile.setAppend(true);
        }
        txtFile.setAppend(false);
    }
}
