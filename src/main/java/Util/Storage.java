package util;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * load an existing taskList from a filepath.
     *
     * @return reformat the file and add them to a new taskList
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                String[] commands = input.split(" \\| ");
                switch (input.charAt(0)) {
                case 'T':
                    tasks.add(new ToDo(Integer.parseInt(commands[1]), commands[2]));
                    break;
                case 'D':
                    tasks.add(new Deadline(Integer.parseInt(commands[1]), commands[2], commands[3]));
                    break;
                case 'E':
                    tasks.add(new Event(Integer.parseInt(commands[1]), commands[2], commands[3]));
                    break;
                default:
                    throw new DukeException("Error when parsing the taskList in " + filePath);
                }
            }
        } catch (DukeException | IOException e) {
            Ui.showErrorMsg(e);
        }
        return tasks;
    }

    /**
     * Save the current taskList to a file.
     *
     * @param tasks current taskList
     * @throws DukeException dukeException
     * @throws IOException IOException
     */
    public void save(List<Task> tasks) throws DukeException, IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks) {
            if (task instanceof ToDo) {
                bufferedWriter.append("T | ").append(String.valueOf(task.getDone()))
                        .append(" | ").append(task.getDesc());
                bufferedWriter.newLine();
            } else if (task instanceof Event) {
                bufferedWriter.append("E | ").append(String.valueOf(task.getDone()))
                        .append(" | ").append(task.getDesc()).append(" | ").append(((Event) task).getDate());
                bufferedWriter.newLine();
            } else if (task instanceof Deadline) {
                bufferedWriter.append("D | ").append(String.valueOf(task.getDone()))
                        .append(" | ").append(task.getDesc()).append(" | ").append(((Deadline) task).getDdl());
                bufferedWriter.newLine();
            } else {
                throw new DukeException("Error when saving data to taskList");
            }
        }
        bufferedWriter.close();

    }
}
