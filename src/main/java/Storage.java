import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {

    private String filePath;

    /**
     * Creates a Storage instance with the appropriate attributes.
     * @param filePath The filepath to the storage text document.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a list from the hard drive into a task list.
     * @return The task list, loaded with information from the hard drive.
     */
    public TaskList loadList() {
        TaskList taskList = new TaskList();

        transferContentsToListWrapper(taskList);

        return taskList;
    }

    /**
     * Stores a task list into a file from the hard drive.
     * @param taskList The task list to be stored into the hard drive.
     */
    public void storeList(TaskList taskList) {
        writeContentsToFile(taskList);
    }

    /**
     * Writes the current task list contents to the hard drive.
     */
    private void writeContentsToFile(TaskList taskList) {
        String formattedTask = "";

        ArrayList<Task> arrayList = taskList.getArrayList();

        for(Task task : arrayList) {
            formattedTask += task.formatForFile() + "\n";
        }

        writeToFileWrapper(formattedTask);
    }


    /**
     * Hides the try catch block whilst executing the method
     * writeToFile.
     * @param textToAdd The text to be added to the task list.
     */
    private void writeToFileWrapper(String textToAdd) {
        try {
            writeToFile(filePath, textToAdd);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Writes given input to given file path.
     * @param filePath The path to the text file to be written to.
     * @param textToAdd The text to be added to the text file.
     * @throws IOException The IO Exception.
     */
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(textToAdd + "\n");
        fileWriter.close();
    }

    /**
     * Hides the try catch block whilst executing the method
     * transferContentsToList.
     */
    private void transferContentsToListWrapper(TaskList taskList) {
        try {
            transferContentsToList(filePath, taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Transfers the contents of the file to a static array list.
     * @param filePath The path to the source text file.
     * @throws FileNotFoundException The exception to be thrown if no such file is found.
     */
    private void transferContentsToList(String filePath, TaskList taskList)
            throws FileNotFoundException {
        File file = new File(filePath);
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNext()) {
            processNextLine(fileReader.nextLine(), taskList);
        }
    }

    /**
     * Processes the next line into proper format.
     * @param nextLine The line to be processed.
     */
    private void processNextLine(String nextLine, TaskList taskList) {
        String[] splitString = nextLine.split(" ");

        String taskType = splitString[0];
        String doneStatus = splitString[2];

        if(taskType.equals("T")) {

            String taskDescription = splitString[4];

            for(int i = 5; i < splitString.length; i++) {
                taskDescription += " " + splitString[i];
            }

            ToDo toDo = new ToDo(taskDescription);

            if(doneStatus.equals("1")) {
                toDo.setDone();
            }

            taskList.addTask(toDo);

        } else {

            int counter = 5;
            String taskDescription = splitString[4];

            for(int i = 5; i < splitString.length && !splitString[i].equals("|"); i++) {
                taskDescription += " " + splitString[i];
                counter++;
            }

            String dateTime = splitString[++counter];

            for(int j = counter + 1; j < splitString.length; j++) {
                dateTime += " " + splitString[j];
            }


            if(taskType.equals("D")) {
                Deadline deadline = new Deadline(taskDescription, dateTime);

                if(doneStatus.equals("1")) {
                    deadline.setDone();
                }

                taskList.addTask(deadline);
            } else {
                Event event = new Event(taskDescription, dateTime);

                if(doneStatus.equals("1")) {
                    event.setDone();
                }

                taskList.addTask(event);
            }

        }
    }

}
