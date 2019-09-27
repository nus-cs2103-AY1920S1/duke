package duke.utility;


import duke.errands.Deadline;
import duke.errands.Event;
import duke.errands.Task;
import duke.errands.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method to read .txt file and load its contents into Duke's TaskList on startup.
     *
     * @return ArrayList of Tasks with all past inputted tasks.
     */
    public ArrayList<Task> load()  {
        //Solution below adapted from Joshua Seet
        ArrayList<Task> list = new ArrayList<>();
        FileReader fileReader;
        try {
            File currentFile = new File(this.filePath);
            fileReader = new FileReader(currentFile);
            Scanner sc = new Scanner(currentFile);
            while (sc.hasNext()) {
                String current = sc.nextLine();
                char type = current.charAt(0);
                int isDone = Character.getNumericValue(current.charAt(4));
                String description = current.substring(8).trim();
                switch (type) {
                case 'T':
                    Todo newTask = new Todo(description);
                    if (isDone == 1) {
                        newTask.markAsDone();
                    }
                    list.add(newTask);
                    break;

                case 'D':
                    int index = description.indexOf('|');
                    String deadlineName = description.substring(0, index).trim();
                    String by = description.substring(index + 1).trim();
                    Deadline newDeadline = new Deadline(deadlineName, by.trim());
                    if (isDone == 1) {
                        newDeadline.markAsDone();
                    }
                    list.add(newDeadline);
                    break;

                case 'E':
                    int index1 = description.indexOf('|');
                    String eventName = description.substring(0, index1).trim();
                    String at = description.substring(index1 + 1).trim();
                    Event newEvent = new Event(eventName, at.trim());
                    if (isDone == 1) {
                        newEvent.markAsDone();
                    }
                    list.add(newEvent);
                    break;

                default:
                }
            }
        } catch (FileNotFoundException err) {
            File currentFile = new File(this.filePath);
            currentFile.createNewFile();
        } finally {
            return list;
        }
        
    }

    /**
     * Method to save contents of Duke's TaskList into .txt file for future loads.
     *
     * @param taskList Duke's current updated taskList.
     */
    public void write(TaskList taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            for (Task task: taskList.list) {
                String taskData = task.getStatus();
                writer.write(taskData);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}