import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.stream.Stream;
import java.lang.System;
import java.util.List;
import java.util.ArrayList;

/**
 *  Deals with loading data onto file or reading from file
 *  this class is called by TaskList to save list of tasks locally
 *  for the next session
 */
class Storage implements TaskObserver, StorageInterface {


    private TaskModelInterface model;
    private String path = "../../../../data/duke.txt";
    private TaskCreator taskCreator;

    public Storage(TaskModelInterface model) {
        this.taskCreator = new LoadTaskCreator();
        this.model = model;
        Stream<TaskInterface> taskStream = loadData();
        model.loadData(taskStream);
        model.registerObserver(this);
    }

    /**
     * Returns vpid, paired with TaskModelInterface
     *  the "Pbservable" pattern such that any changes
     *  this classw ill be notified
     */
    public void registerModel(TaskModelInterface model) {
        this.model.registerObserver(this);
    }

    /**
     * Returns void, method for tasklist to call each time
     *  a new task is added, this prompts Storage to write
     *  any changes in the list to file.
     *  @param model basically TaskList to pull info to save
     */
    public void update(TaskModelInterface model) {
        //System.out.println("Flag 1");
        String toWrite = saveFormat(model.getTaskStream());
        //System.out.println("Flag 2");
        writeData(toWrite);
    }


    /**
     * Returns stream of tasks saved in previous session
     * @return Stream of Tasks, you can collect this into
     * a List and update TaskModel with this
     */
    public Stream<TaskInterface> loadData() {
        List<TaskInterface> taskList = new ArrayList<>();
        try {
            File f = new File(this.path);
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                try {
                    String loadLine = fileScanner.nextLine();
                    TaskInterface loadTask = this.taskCreator
                        .createTask(loadLine);
                    taskList.add(loadTask);
                } catch (OWOException e) {
                    System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            //System.out
            String loadFileErrorMsg = "OHNyO ;;w;; >w<\n"
                //.println("OHNyO ;;w;; >w<\n"
                + "owo cant woad the save fiwe!!\n" 
                + "taskwist fwom this session cannyot be saved";
            System.out.println(e);

            Ui.printErrorSection(loadFileErrorMsg);
        } 
        return taskList.stream();
    }

    /**
     * Returns a string to be written into file
     * @param taskStream tasks to be converted to string
     *  to be written into save file
     * @return String formatted for save file that can be loaded
     */
    public static String 
        saveFormat(Stream<TaskInterface> taskStream) {
        return taskStream
            .map(x -> x.getSaveFormat())
            .reduce("", (x, accu) -> 
                    //x + System.lineSeperator() 
                    x + "\n"
                    + accu);
    }

    private void writeData(String textToAdd) {
        //File f = new File(this.path);
        // System.out.println("file exists?: " + f.exists());
        //System.out.println("Attempting to write");
        try {

            FileWriter fw = new FileWriter(this.path);
            fw.write(textToAdd);
            fw.close();

        } catch (IOException e) {
            String writeFileErrorMsg = "OHNyO OMO owo ^w^\n"
                + "OwO cant wwite taskwist to fiwe,\n"
                + "you nyeed to cweate a fowdew cawwed 'data'\n"
                + "in the 'woot' pwoject diwectowy";
            System.out.println(e);
            Ui.printErrorSection(writeFileErrorMsg);
            //System.out.println("you need to create a folder" 
            //       + " called 'data' in 'duke' dir");
        }
                
    }

}
