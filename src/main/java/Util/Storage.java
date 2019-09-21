package Util;

import Exception.DukeException;
import Tasks.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class to handle all interactions between saving, reading and writing
 * the file onto local disk. The interactions will be based on what is stored on
 * TaskList
 */
public class Storage {

    private Scanner scanner;
    private PrintWriter pw;
    private BufferedWriter bw;
    private FileWriter fw;
    private ArrayList<Task> storedList = new ArrayList<>();
    private String filePath;

    /**
     * Constructs a Storage object to interact with given file
     *
     * @param filePath represents the file path/directory for which the
     * writer/reader interacts with
     */
    public Storage(String filePath) throws Exception{
        this.filePath = filePath;
        File file = new File(filePath);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            scanner = new Scanner(new File(filePath));
        } catch (Exception e) {
            System.out.println("Error in reading file");
        }
        fw = new FileWriter(file, true);
        bw = new BufferedWriter(fw);
        pw = new PrintWriter(bw);
    }

    /**
     * Reads all data from the file filePath
     *
     * @return ArrayList of tasks to be stored under TaskList
     */
    public ArrayList<Task> readFile() {
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] strArr = str.split(" \\| ");
            for(int i = 0; i<strArr.length; i++){
                if (i == 0) {
                    //for Todo
                    if (strArr[i].equals("T")) {
                        Todo task = new Todo(strArr[2], "by");
                        if(strArr[1].equals("1")) task.doneJob();
                        storedList.add(task);
                    }
                    //for Deadline
                    if (strArr[i].equals("D")) {
                        Deadline task = new Deadline(strArr[2], strArr[3]);
                        if(strArr[1].equals("1")) task.doneJob();
                        storedList.add(task);
                    }
                    //for Event
                    if (strArr[i].equals("E")) {
                        Event task = new Event(strArr[2], strArr[3]);
                        if(strArr[1].equals("1")) task.doneJob();
                        storedList.add(task);
                    }
                }
            }
        }
        return storedList;
    }

    /**
     * Closes off both the scanner and printwrite instances.
     */
    public void closeFile() {
        scanner.close();
        pw.close();
    }

    /**
     * Deletes the file in filePath and rewrites the entire file through iterating
     * the inputted TaskList
     *
     * @param taskList is the object for which the method will get data from
     */

    public void rewriteWriter(TaskList taskList) throws Exception{
        String str = "";
        for(int i = 0; i < taskList.getTaskList().size(); i++) {
            Task task = taskList.getTaskList().get(i);
            String taskType = taskList.getTaskList().get(i).toString().substring(1,2) +" | ";
            String doneType = "";
            if(taskList.getTaskList().get(i).toString().substring(4,5).equals("O")) doneType = "1 | ";
            else doneType = "0 | ";
            String after = taskList.getTaskList().get(i).toString().substring(7);
            String desc = taskList.getTaskList().get(i).getDescription();
            String atby = "";
            if(taskList.getTaskList().get(i) instanceof Todo) {
                atby = "";
            } else if(taskList.getTaskList().get(i) instanceof Deadline) {
                String[] strArr = after.split(" \\(by:");
                atby = " |" + strArr[1].replace(")", "");
            } else if(taskList.getTaskList().get(i) instanceof Event) {
                String[] strArr = after.split( " \\(at:");
                atby = " |" + strArr[1].replace(")", "");
            } else {
                throw new DukeException("task instance not declared yet");
            }
            if(i != 0) str += "\n";
            str += taskType + doneType + desc + atby;

        }
        fw = new FileWriter(new File(this.filePath), false);
        pw.print(str);
        pw.flush();
        fw.close();
        fw = new FileWriter(new File(this.filePath), true);
    }

    /**
     * Write a Todo task into the file from filePath
     *
     * @param task the Todo task which will be analysed
     */
    public void writeTodo(Task task) {
        String status = "";
        if(task.getStatusIcon().equals("O")) status = "1";
        else status = "0";
        pw.println();
        pw.print("T | ");
        pw.print(status + " | ");
        pw.print(task.getDescription());
        pw.flush();
    }

    /**
     * Write a Deadline task into the file from filePath
     *
     * @param task the Deadline task which will be analysed
     */
    public void writeDeadline(Deadline task) {
        String status = "";
        if(task.getStatusIcon().equals("O")) status = "1";
        else status = "0";
        pw.println();
        pw.print("D | ");
        pw.print(status + " | ");
        pw.print(task.getDescription() + " | ");
        pw.print(task.getBy());
        pw.flush();
    }

    /**
     * Write a Event task into the file from filePath
     *
     * @param task the Event task which will be analysed
     */
    public void writeEvent(Event task) {
        String status = "";
        if(task.getStatusIcon().equals("O")) status = "1";
        else status = "0";
        pw.println();
        pw.print("D | ");
        pw.print(status + " | ");
        pw.print(task.getDescription() + " | ");
        pw.print(task.getAt());
        pw.flush();
    }

}
