package duke.component;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File inputFile;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.inputFile = new File(filePath);
    }

    public List<Task> load() throws FileNotFoundException {
        List<Task> taskList = new ArrayList<>();
        Scanner txtSC = new Scanner(inputFile);

        while (txtSC.hasNext()) {
            String[] historicalInputs = Parser.breakDownString(txtSC.nextLine(), "\\|");
            boolean taskIsCompleted;
            Task oldTask = null;

            if (historicalInputs[1].charAt(1) == '1') {
                taskIsCompleted = true;
            } else {
                taskIsCompleted = false;
            }

            switch (historicalInputs[0].charAt(0)) {
                case 'T':
                    oldTask = new Todo(historicalInputs[2].substring(1), taskIsCompleted);
                    break;
                case 'D':
                    String[] time = historicalInputs[3].substring(1).split(" ");
                    oldTask = new Deadline(
                            historicalInputs[2].substring(1, historicalInputs[2].length() - 1),
                            Parser.getDateAndTimeFromString(historicalInputs[3].substring(1)),
                            taskIsCompleted);
                    break;
                case 'E':
                    oldTask = new Event(
                            historicalInputs[2].substring(1, historicalInputs[2].length() - 1),
                            Parser.getDateAndTimeFromString(historicalInputs[3].substring(1)),
                            taskIsCompleted);
                    break;
                default:
            }

            taskList.add(oldTask);
        }

        txtSC.close();

        return taskList;
    }

    public void save(TaskList taskList) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter fileWriter = new PrintWriter(filePath, "UTF-8");

        for (int i = 0; i < taskList.getSize(); i++) {
            fileWriter.println(taskList.getAtIndex(i).toIndicationInsideFile());
        }

        fileWriter.close();
    }
}
