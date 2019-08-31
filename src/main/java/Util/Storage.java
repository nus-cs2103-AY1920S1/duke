package Util;

import Tasks.*;

import java.io.*;

import java.util.*;

import java.util.Scanner;

import Exception.DukeException;

public class Storage {

    private Scanner scanner;
    private PrintWriter pw;
    private BufferedWriter bw;
    private FileWriter fw;

    private ArrayList<Task> storedList = new ArrayList<>();
    private String filePath;

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

    //returns arrayList of Tasks
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

    public void closeFile() {
        scanner.close();
        pw.close();
    }

    public void rewriteWriter(TaskList taskList) throws Exception{
        String str = "";
        for(int i = 0; i < taskList.getTaskList().size(); i++){
            Task task = taskList.getTaskList().get(i);
            String taskType = taskList.getTaskList().get(i).toString().substring(1,2) +" | ";
            String doneType = "";
            if(taskList.getTaskList().get(i).toString().substring(4,5).equals("O")) doneType = "1 | ";
            else doneType = "0 | ";
            String after = taskList.getTaskList().get(i).toString().substring(7);
            String desc = taskList.getTaskList().get(i).getDescription();
            String atby;

            if(taskList.getTaskList().get(i) instanceof Todo){
                atby = "";
            } else if(taskList.getTaskList().get(i) instanceof Deadline){
                String[] strArr = after.split( " \\(by:" );
                atby = " |" + strArr[1].replace(")", "");

            } else if(taskList.getTaskList().get(i) instanceof Event){
                String[] strArr = after.split( " \\(at:" );
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
//        bw = new BufferedWriter(fw);
//        pw = new PrintWriter(bw);
    }

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
