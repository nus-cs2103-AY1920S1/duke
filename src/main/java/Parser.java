import java.io.IOException;

class Parser {
    /**
     * Returns the index of the preposition in the String array
     * If preposition is not found, return 0
     * @param inputSplit given String array
     * @return index position or 0
     */
    private int getPrepositionPos(String[] inputSplit) {
        for (int i = 0; i < inputSplit.length; i++) {
            if (inputSplit[i].charAt(0) == '/') {
                return i;
            }
        }
        return 0;
    }

    /**
     * Returns the Task constructed from the input String array
     * @param inputSplit given String array
     * @return Task generated
     * @throws InvalidInputFormatException If the input is not in the required format
     */
    private Task construct(String[] inputSplit) throws InvalidInputFormatException {
        if (inputSplit.length < 2) {
            throw new InvalidInputFormatException();
        }
        checkValidSpacing(inputSplit);
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

    void checkValidTaskIndex(String[] inputSplit) throws InvalidInputFormatException {
        if (inputSplit.length < 2) {
            throw new InvalidInputFormatException();
        }
        try {
            Integer.parseInt(inputSplit[1]);
        } catch (NumberFormatException e) {
            throw new InvalidInputFormatException();
        }
    }

    void checkValidSpacing(String[] inputSplit) throws InvalidInputFormatException {
        for (String s : inputSplit) {
            if (s.length() == 0) {
                throw new InvalidInputFormatException();
            }
        }
    }

    boolean parse(String input, TaskList tasks, Ui ui, Storage storage) {
        String[] inputSplit = input.split(" ");
        String command = inputSplit[0];

        try {
            switch (command) {
            case "exit":
                return false;

            case "bye":
                storage.save(tasks.saveFormat());
                ui.printBye();
                return false;

            case "list":
                ui.printList(tasks);
                return true;

            case "done":
                checkValidTaskIndex(inputSplit);
                Task theTask = tasks.get(Integer.parseInt(inputSplit[1]) - 1);
                theTask.isDone = true;
                ui.printDone(theTask);
                return true;

            case "todo":
            case "deadline":
            case "event":
                Task task = construct(inputSplit);
                if (task != null) {
                    tasks.add(task);
                    ui.printCreated(task, tasks);
                }
                return true;

            case "delete":
                checkValidTaskIndex(inputSplit);
                Task removedTask = tasks.remove(Integer.parseInt(inputSplit[1]) - 1);
                ui.printRemoved(removedTask, tasks);
                return true;

            case "formats":
                ui.printFormatHelp();
                return true;

            case "find":
                checkValidSpacing(inputSplit);
                StringBuilder sb = new StringBuilder();
                for (String s : inputSplit) {
                    sb.append(s).append(" ");
                }
                ui.printFind(sb.substring(5).trim(), tasks);
                return true;

            default:
                throw new InputUnknownException();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("unable to save " + e.getMessage());
        }
        return true;
    }
}
