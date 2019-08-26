import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Storage {
    private File saveFile;

    Storage (String saveLoadFilePath) {
        saveFile = new File(saveLoadFilePath);
    }

    private static int getPrepositionPos(String[] input_split) {
        for (int i = 0; i < input_split.length; i++) {
            if (input_split[i].charAt(0) == '/') {
                return i;
            }
        }
        return 0;
    }

    private static Task construct(String[] input_split)
            throws EmptyDescriptionException, EmptyTimeDueException {
        if (input_split.length < 2) {
            // System.out.println("what's the " + type);
            throw new EmptyDescriptionException();
        }
        Task task;
        int prepAt = getPrepositionPos(input_split);
        StringBuilder description = new StringBuilder();
        String preposition = input_split[prepAt].substring(1);
        StringBuilder memo = new StringBuilder();
        boolean prepRequired = input_split[0].equals("deadline") || input_split[0].equals("event");

        if (prepRequired && prepAt == 0) {
            // System.out.println("what's the date due?");
            throw new EmptyTimeDueException();
        }
        if (prepRequired) {
            for (int k = 1; k < prepAt; k++) {
                description.append(" ").append(input_split[k]);
            }
        } else {
            for (int k = 1; k < input_split.length; k++) {
                description.append(" ").append(input_split[k]);
            }
        }
        for (int i = prepAt + 1; i < input_split.length; i++) {
            memo.append(" ").append(input_split[i]);
        }

        switch (input_split[0]) {
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

    private static Task construct(String[] input_string, boolean completed)
            throws EmptyDescriptionException, EmptyTimeDueException{
        Task task = construct(input_string);
        task.isDone = completed;
        return task;
    }

    void save(String saveText) throws IOException {
//        how do you work around this? it always still exists
//        if (f.exists()) {
//            System.out.println(f.delete() ? "Previous File deleted" : "Previous File still exists");
//        }
//        System.out.println(f.createNewFile() ? "New file created" : "New file not created");
        FileWriter fw = new FileWriter(saveFile,   false);
        fw.write(saveText);
        fw.close();
//        System.out.println("Finished Saving");
    }

    TaskList load() throws FileNotFoundException, EmptyDescriptionException, EmptyTimeDueException {
        Scanner s = new Scanner(saveFile);
        TaskList tasks = new TaskList();
        while (s.hasNextLine()) {
            String[] task = s.nextLine().split(" ");
            boolean completed = task[0].charAt(1) == '1';
            switch (task[0].charAt(0)) {
            case 'T':
                // construct todo
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
            }
            tasks.add(construct(task, completed));
        }
        return tasks;
    }
}
