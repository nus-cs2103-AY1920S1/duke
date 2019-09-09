import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Storage {
    private static final String saveLoadFilePath = "listSaveData.txt";
    private static File saveFile = new File(saveLoadFilePath);

    private static int getPrepositionPos(String[] inputSplit) {
        for (int i = 0; i < inputSplit.length; i++) {
            if (inputSplit[i].charAt(0) == '/') {
                return i;
            }
        }
        return 0;
    }

    private static Task construct(String[] inputSplit) throws InvalidInputFormatException {
        if (inputSplit.length < 2) {
            // System.out.println("what's the " + type);
            throw new InvalidInputFormatException();
        }
        Task task;
        int prepAt = getPrepositionPos(inputSplit);
        StringBuilder description = new StringBuilder();
        String preposition = inputSplit[prepAt].substring(1);
        StringBuilder memo = new StringBuilder();
        boolean prepRequired = inputSplit[0].equals("deadline") || inputSplit[0].equals("event");

        if (prepRequired && prepAt == 0) {
            // System.out.println("what's the date due?");
            throw new InvalidInputFormatException();
        }
        if (prepRequired) {
            for (int k = 1; k < prepAt; k++) {
                description.append(" ").append(inputSplit[k]);
            }
        } else {
            for (int k = 1; k < inputSplit.length; k++) {
                description.append(" ").append(inputSplit[k]);
            }
        }
        for (int i = prepAt + 1; i < inputSplit.length; i++) {
            memo.append(" ").append(inputSplit[i]);
        }

        switch (inputSplit[0]) {
        case "todo":
            task = new ToDo(description.deleteCharAt(0).toString());
            break;
        case "deadline":
            task = new Deadline(description.deleteCharAt(0).toString(), preposition, memo.deleteCharAt(0).toString());
            break;
        case "event":
            task = new Event(description.deleteCharAt(0).toString(), preposition, memo.deleteCharAt(0).toString());
            break;
        default:
            task = null;
        }

        return task;
    }

    private static Task construct(String[] inputSplit, boolean completed)
            throws InvalidInputFormatException {
        Task task = construct(inputSplit);
        task.isDone = completed;
        return task;
    }

    /**
     * Writes to saveFile the given text
     * Given text should have been formatted to make loading correct
     * @param saveText Text to be written on the file
     * @throws IOException If the file cannot be written
     */
    static void save(String saveText) throws IOException {
        FileWriter fw = new FileWriter(saveFile, false);
        fw.write(saveText);
        fw.close();
    }

    /**
     * Returns a TaskList filled with Tasks generated from the saveFile
     * @return TaskList
     * @throws FileNotFoundException If the File is not found
     * @throws InvalidInputFormatException If the File is corrupted / not parsed correctly
     */
    static TaskList load() throws FileNotFoundException, InvalidInputFormatException {
        Scanner s;
        s = new Scanner(saveFile);

        TaskList tasks = new TaskList();
        while (s.hasNextLine()) {
            String[] task = s.nextLine().split(" ");
            boolean completed = task[0].charAt(1) == '1';
            switch (task[0].charAt(0)) {
            case 'T':
                // construct todos
                task[0] = "todo";
                break;
            case 'D':
                // construct deadline
                task[0] = "deadline";
                break;
            case 'E':
                // construct event
                task[0] = "event";
                break;
            default:
            }
            tasks.add(construct(task, completed));
        }
        return tasks;
    }
}
