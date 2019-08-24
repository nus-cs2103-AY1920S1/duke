import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.stream.Stream;
import java.lang.System;
import java.util.List;
import java.util.ArrayList;
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

    public void registerModel(TaskModelInterface model) {
        this.model.registerObserver(this);
    }

    public void update(TaskModelInterface model) {
        //System.out.println("Flag 1");
        String toWrite = saveFormat(model.getTaskStream());
        //System.out.println("Flag 2");
        writeData(toWrite);
    }

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
//        File f = new File(this.path);
 //       System.out.println("file exists?: " + f.exists());
        System.out.println("Attempting to write");
        try {

            FileWriter fw = new FileWriter(this.path);
            fw.write(textToAdd);
            fw.close();

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("you need to create a folder" 
                    + " called 'data' in 'duke' dir");
        }
                
    }

}
