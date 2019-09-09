import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * Encapsulates the Storage object that is responsible of writing existing Tasks to the local storage.
 */
public class Storage {
    String filePath;

    /**
     * Constructs the Storage object.
     * @param filePath Provides the filepath to the local text file as the local storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File tempFile = new File(filePath);
        try {
            if (!tempFile.exists()) {
                tempFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failed to create a new file at the specified location.");
        }
    }

    /**
     *Loads data from the local file.
     * @return the existing tasks that are stored in the local file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskStorage = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String storedLine = s.nextLine();
                String[] analyseHolder = storedLine.split(", ", 4);
                String type = analyseHolder[0];
                String isDone = analyseHolder[1];
                String description = analyseHolder[2];
                Date due = new Date();
                Date start = new Date();
                Date end = new Date();
                if (analyseHolder.length == 4) {
                    String[] timeSplit = analyseHolder[3].split("-");
                    if (timeSplit.length == 2) {
                        SimpleDateFormat startFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                        SimpleDateFormat endFormat = new SimpleDateFormat("HHmm");
                        start = startFormat.parse(timeSplit[0].trim());
                        end = endFormat.parse(timeSplit[1].trim());
                    } else {
                        due = convertStringToDate(analyseHolder[3]);
                    }
                }

                Task task = new Task("dummy");
                if (type.equals("T")) {
                    task = new Todo(description);
                } else if (type.equals("D")) {
                    task = new Deadline(description, due);
                } else if (type.equals("E")) {
                    task = new Event(description, start, end);
                }

                if (isDone.equals("1")) {
                    task.markAsDone();
                }

                taskStorage.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The local file cannot be located. " +
                    "Please ensure that the local text file " +
                    "that stores all existing tasks is in the right folder. ");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is something wrong with the format of the file text. " +
                    "There might be necessary lines between tasks.");
        } catch (ParseException e) {
            e.printStackTrace();
//            System.out.println("There seems to be an error in the format of the date.");
        }
        return taskStorage;
    }

    /**
     * Converts user input in the form of String to the format of Date.
     * The Task Manager has the ability to read dates instead of taking dates as String.
     *
     * @param input The date/ time input in the form of String. It should follow either dd/MM/yyyy HHmm or HHmm format.
     * @return Date format of the input String.
     */
    public static Date convertStringToDate(String input) {
        Date date = new Date();
        try {
            if (input.length() <= 5) {
                SimpleDateFormat formatTimeOnly = new SimpleDateFormat("HHmm");
                date = formatTimeOnly.parse(input.trim());
                return date;
            } else {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm", Locale.ENGLISH);
                date = format.parse(input);
                assert date.compareTo(new Date(0)) < 0 : "The deadline cannot be in the past. Please update " +
                        "to a future time.";
                return date;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Updates the local list of tasks stored in a local text file. This ensures the continuous
     * maintenance of the same file through of the lifetime of duke.
     * @param taskStorage list of tasks that are stored in the local text file.
     */
    public void updateLocalFile(ArrayList<Task> taskStorage) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String textToAdd = new String();
            String taskType;
            String description;
            String isDoneRepresented;
            for (Task task : taskStorage) {
                taskType = task.getTypeOfTask();
                description = task.getDescription();
                Boolean isDone = task.isDone();
                if (isDone) {
                    isDoneRepresented = "1";
                } else {
                    isDoneRepresented = "0";
                }
                if (taskType.equals("T")) {
                    textToAdd = taskType + ", " + isDoneRepresented + ", " + description + System.lineSeparator();
                } else if (taskType.equals("D")) {
                    textToAdd = taskType + ", " + isDoneRepresented + ", "
                            + description + ", " + ((Deadline) task).getDueInString() + System.lineSeparator();
                } else if (taskType.equals("E")) {
                    textToAdd = taskType + ", " + isDoneRepresented + ", "
                            + description + ", " + ((Event) task).getDueInString() + System.lineSeparator();
                }
                fw.write(textToAdd);
            }

            fw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Write to a specified text file that is locally saved.

     * @param taskType Contains the type of the task that is going to be written.
     * @param isDone Contains the information of whether the task is done.
     * @param description Contains the description of the task that is going to be written.
     */
    public void writeToFile(String taskType,
                                    String isDone, String description, Task task) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String textToAdd = new String();
            if (task instanceof Todo) {
                textToAdd = taskType + ", " + isDone + ", " + description + System.lineSeparator();
            } else if (task instanceof Deadline){
                textToAdd = taskType + ", " + isDone + ", "
                        + description + ", " + ((Deadline) task).getDueInString() + System.lineSeparator();
            } else if (task instanceof Event) {
                textToAdd = taskType + ", " + isDone + ", "
                        + description + ", " + ((Event) task).getDueInString() + System.lineSeparator();
            }

            fw.write(textToAdd);
            fw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Clear all data written in the local text file.
     */
    public void clear() {
        try {
            FileWriter fwOb = new FileWriter(filePath, false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (IOException e) {
            System.out.println("There seems to be a problem clearing this file.");
        }
    }
}
