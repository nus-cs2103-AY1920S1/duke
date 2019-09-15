package duke.database;

import duke.component.Ui;
import duke.task.Task;
import duke.exception.DukeException;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage will initialise the save file and obtain the data within
 * else it will create a new file and allow user to update it.
 *
 * @author TeoShyanJie
 *
 */
public class Storage {
    /** Declaration of file as File type. */
    private File file;

    /** Ui of the Duke Program. */
    private Ui ui;

    /**
     * Storage Constructor.
     * @param ui Ui of Duke Constructor.
     */
    public Storage(Ui ui) {
        this.ui = ui;
    }

    /**
     * To get the project root and path to create the txt file or check if text file is available.
     */
    public void initialise() {
        String projectRoot = new File(System.getProperty("user.dir")).getParentFile().getPath();
        StringBuilder path = new StringBuilder();
        path.append(projectRoot);

        File directory = new File(path + "/data");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        file = new File(directory + "/" + "data.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            ui.showLine();
            System.out.println(ui.INDENT_COMMENT + "OOPS !!! "
                    + "File is not able to be created");
            ui.showLine();
            System.out.println("");
        }
    }

    /**
     * Get the list of task from the text file to a List.
     * @return list of task as List.
     */
    public List<Task> getSavedTask() {
        List<Task> list = new ArrayList<>();

        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                try {
                    list.add(getEachTask(input.nextLine()));
                } catch (DukeException e) {
                    ui.showLine();
                    System.out.println(ui.INDENT_COMMENT + "OOPS !!! "
                            + "File is empty");
                    ui.showLine();
                    System.out.println("");
                }
            }
        } catch (FileNotFoundException e) {
            ui.showLine();
            System.out.println(ui.INDENT_COMMENT + "OOPS !!! "
                    + "File is not available");
            ui.showLine();
            System.out.println("");
        }

        return list;
    }

    /**
     * Get each of task from the text.
     * @param input List of task from the save text file.
     * @return Each line of task save in the text file.
     * @throws DukeException If the status of task is not an Integer.
     */
    public Task getEachTask(String input) throws DukeException {
        String[] data = input.split("\\|");

        Task task;

        switch (data[0].trim()) {
        case "T":
            task = new Todo(data[2].trim());
            try {
                if (Integer.parseInt(data[1].trim()) == 1) {
                    task.markAsDone();
                }
            } catch (NumberFormatException ex) {
                ui.showLine();
                System.out.println(ui.INDENT_COMMENT
                        + "OOPS !!! " + "Error in Database Data");
            }
            break;

        case "D":
            task = new Deadline(data[2].trim(), data[3].trim());
            task.setTime(data[3].trim());
            try {
                if (Integer.parseInt(data[1].trim()) == 1) {
                    task.markAsDone();
                }
            } catch (NumberFormatException ex) {
                ui.showLine();
                System.out.println(ui.INDENT_COMMENT
                        + "OOPS !!! " + "Error in Database Data");
            }
            break;

        case "E":
            task = new Event(data[2].trim(), data[3].trim());
            task.setTime(data[3].trim());
            try {
                if (Integer.parseInt(data[1].trim()) == 1) {
                    task.markAsDone();
                }
            } catch (NumberFormatException ex) {
                ui.showLine();
                System.out.println(ui.INDENT_COMMENT
                        + "OOPS !!! " + "Error in Database Data");
            }
            break;

        default:
            throw new DukeException(ui.INDENT_COMMENT
                    + "OOPS !!! " + "Error in reading data");
        }

        return task;
    }

    /**
     * Update the list of task in the text file.
     * @param task List of task.
     * @throws DukeException If file is not able to write.
     */
    public void updateFile(List<Task> task) throws DukeException {
        file.delete();

        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            for (Task item: task) {
                StringBuilder save = new StringBuilder();

                String temp = item.toString();

                temp = temp.replaceAll("[\\p{Ps}\\p{Pe}]","");
                temp.trim();

                String type = temp.substring(0, 1).trim();

                save.append(type);
                save.append(" | ");

                if (temp.substring(1, 2).trim().equals("\u2713")) {
                    save.append("1");
                } else {
                    save.append("0");
                }
                save.append(" | ");

                String rest = temp.substring(2).trim();

                if (rest.contains("at:")) {
                    String[] part = rest.split("at:");
                    save.append(part[0].trim());
                    save.append(" | ");
                    save.append(part[1].trim());
                } else if (rest.contains("by:")) {
                    String[] part = rest.split("by:");
                    save.append(part[0].trim());
                    save.append(" | ");
                    save.append(part[1].trim());
                } else {
                    save.append(rest.trim());
                }

                writer.write(save + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to Update File");
        }
    }
}
