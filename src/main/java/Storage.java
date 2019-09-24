import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Represents the file to load-up and save the TaskList.
 */

public class Storage {

    private String filepath;
    private String[] funFacts;
    private String helpInfo;
    private final String FUN_FACT_FILEPATH = "/funFacts/FunFacts.txt";
    private final String HELP_INFO_FILEPATH = "/helpInfo/HelpInfo.txt";

    /**
     * Constructs a storage object.
     * @param filepath refers to the file path of TaskList
     */

    public Storage (String filepath) {
        this.filepath = filepath;
        try {
            this.funFacts = loadFunFacts(FUN_FACT_FILEPATH);
            this.helpInfo = loadHelpInfo(HELP_INFO_FILEPATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loading up helpInfo from the hard disc
     * @param filePath
     * @return A string that displays the help info to the user
     */

    public String loadHelpInfo(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder("");
        InputStream inputStream = this.getClass().getResourceAsStream(filePath);
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputReader);
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Getter method to retrieve the help info
     * @return A String of help info
     */
    public String getHelpInfo() {
        return this.helpInfo;
    }

    /**
     * Loading up the funfacts from the hard disk
     * @param filePath
     * @return A string array of fun facts
     */
    public String[] loadFunFacts(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder("");
        InputStream inputStream = this.getClass().getResourceAsStream(filePath);
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputReader);
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        String[] funFacts = sb.toString().split("-", 20);
        return funFacts;
    }

    /**
     * Getting a random fun-fact
     * @param index
     * @return random fun fact
     */
    public String getFunFact(int index) {
        return funFacts[index];
    }

    /**
     * WriteToFile method to only be used by Storage class.
     * Saves the taskslist to the file.
     * @param filePath the directory of the TaskList file, in the form of a String
     * @param textToAdd text that is meant to be appended
     * @throws IOException
     */
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // boolean param to append and not overwrite
        fw.append(textToAdd);
        fw.close();
    }
    /**
     * Loads up the tasklist from the saved file in the harddisk.
     * @return ArrayList of tasks.
     * @throws DukeException if the file is not found.
     */

    public ArrayList<Task> load() throws DukeException, IOException{
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(this.filepath);
        if (!f.exists()) {
            new File("./taskList").mkdir();
            new File("./taskList/TaskList.txt").createNewFile();
        }
        try {
            File loadUpFile = new File(this.filepath);
            Scanner scLoad = new Scanner(loadUpFile);
            while (scLoad.hasNextLine()) {
                String sentence = scLoad.nextLine();
                String[] taskInfo = sentence.split(" \\u007C ");
                if (taskInfo[0].equals("T")) {
                    ToDo taskT = new ToDo(taskInfo[2]);
                    if (taskInfo[1].equals("1")) {
                        taskT.markAsDone();
                    }
                    tasks.add(taskT);
                } else if (taskInfo[0].equals("D")) {
                    Deadline taskD = new Deadline(taskInfo[2]);
                    try {
                        taskD.parseTime(taskInfo[3]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (taskInfo[1].equals("1")) {
                        taskD.markAsDone();
                    }
                    tasks.add(taskD);
                } else if (taskInfo[0].equals("E")) {
                    Event taskE = new Event(taskInfo[2]);
                    try {
                        taskE.parseTime(taskInfo[3]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (taskInfo[1].equals("1")) {
                        taskE.markAsDone();
                    }
                    tasks.add(taskE);
                } else {
                    throw new DukeException("File has invalid contents!!");
                }
            }
            scLoad.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Error, File Not Found.");
        }
        return tasks;
    }

    public void writeToHardDisk(TaskList tasks) throws DukeException{
        try {
            String taskListFilePath = this.filepath;
            FileWriter fw = new FileWriter (taskListFilePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                if (tasks.getTask(i) instanceof Deadline) {
                    writeToFile(taskListFilePath, "D | " + tasks.getTask(i).isDone() + " | "
                            + tasks.getTask(i).getTask() + " | " + ((Deadline) tasks.getTask(i)).getDateToBeStored());
                } else if (tasks.getTask(i) instanceof Event) {
                    writeToFile(taskListFilePath, "E | " + tasks.getTask(i).isDone() + " | "
                            + tasks.getTask(i).getTask() + " | " + ((Event) tasks.getTask(i)).getDateToBeStored());
                } else {
                    writeToFile(taskListFilePath, "T | " + tasks.getTask(i).isDone() + " | "
                            + tasks.getTask(i).getTask());
                }
                // no newline for last line.
                if (i != tasks.getSize() - 1) {
                    writeToFile(taskListFilePath, "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred " + e.getMessage());
        }
    }
}
