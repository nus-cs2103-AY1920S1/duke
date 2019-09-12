import javax.swing.text.View;

/**
 * Makes sense of user input into a Duke Object.
 */
public class Parser {

    /**
     * Parses the input command and then returns the corresponding command object.
     * If the input is invalid, a Duke exception is thrown.
     *
     * @param fullCommand String input into a Duke object
     * @return Command object based on the input parsed.
     * @throws DukeException thrown when input entered is invalid and does not correspond to any command.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String trimmedCommand = fullCommand.trim();

        if (trimmedCommand.equals("")) {
            throw new InvalidCommandDukeException("OOF!!! I'm sorry, but I don't know what that means :-(");
        }

        String[] splitCommand = trimmedCommand.split(" ");
        switch (splitCommand[0]) {
        case "bye":
            if (splitCommand.length != 1) {
                throw new InvalidCommandDukeException("OOF!!! I'm sorry, no characters after 'bye' allowed!!");
            }
            return new ExitCommand();
        case "list":
            if (splitCommand.length != 1) {
                throw new InvalidCommandDukeException("OOF!!! I'm sorry, no characters after 'list' allowed!!");
            }
            return new ListCommand();
        case "help":
            if (splitCommand.length != 1) {
                throw new InvalidCommandDukeException("OOF!!! I'm sorry, no characters after 'help' allowed!!");
            }
            return new HelpCommand();
        case "done":
            try {
                if (splitCommand.length > 2) {
                    throw new InvalidCommandDukeException("OOF!!! Please enter only one task number!!");
                }

                int taskNumber = Integer.parseInt(splitCommand[1]) - 1;
                // Precondition for DoneCommand
                assert taskNumber >= 0 : "Task number entered can only be non-negative";
                return new DoneCommand(taskNumber);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandDukeException("OOF!!! Please enter a task number!!");
            }
        case "delete":
            try {
                if (splitCommand.length > 2) {
                    throw new InvalidCommandDukeException("OOF!!! Please enter only one task number!!");
                }

                int taskNumber = Integer.parseInt(splitCommand[1]) - 1;
                // Precondition for DeleteCommand
                assert taskNumber >= 0 : "Task number entered can only be non-negative";
                return new DeleteCommand(taskNumber);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandDukeException("OOF!!! Please enter a task number!!");
            }
        case "find":
            String keyWord = joinStringArrayFromIndex(splitCommand, 1);
            String trimmedKeyWord = keyWord.trim();

            if (trimmedKeyWord.equals("")) {
                throw new InvalidCommandDukeException("OOF!!! I'm sorry, but I don't know what that means :-(");
            }

            return new FindCommand(trimmedKeyWord);
        case "todo":
            String todoDescription = joinStringArrayFromIndex(splitCommand, 1);
            String trimmedTodoDescription = todoDescription.trim();

            if (trimmedTodoDescription.equals("")) {
                throw new InvalidTaskDescriptionDukeException("OOF!!! "
                        + "The description of a todo cannot be empty!!");
            }

            return new AddCommand(new ToDo(trimmedTodoDescription));
        case "deadline":
            String[] deadlineDescriptionDate = extractDescriptionDate(splitCommand, "/by");
            String deadlineDescription = deadlineDescriptionDate[0].trim();
            String deadlineDate = deadlineDescriptionDate[1].trim();

            if (!deadlineDescription.equals("") && !deadlineDate.equals("")) {
                return new AddCommand(new Deadline(deadlineDescription, deadlineDate));
            } else {
                throw new InvalidTaskDescriptionDukeException("OOF!!! "
                        + "The description/timing of a deadline cannot be empty!!");
            }
        case "event":
            String[] eventDescriptionDate = extractDescriptionDate(splitCommand, "/at");
            String eventDescription = eventDescriptionDate[0].trim();
            String eventDate = eventDescriptionDate[1].trim();

            if (!eventDescription.equals("") && !eventDate.equals("")) {
                return new AddCommand(new Event(eventDescription, eventDate));
            } else {
                throw new InvalidTaskDescriptionDukeException("OOF!!! "
                        + "The description/timing of an event cannot be empty!!");
            }
        case "archive":
            if (splitCommand.length == 1) {
                throw new InvalidCommandDukeException("OOF!!! No archive instruction entered!!");
            }

            if (splitCommand[1].equals("create")) {
                String newArchiveName = joinStringArrayFromIndex(splitCommand, 2);
                return new CreateArchiveCommand(newArchiveName);
            }

            if (splitCommand[1].equals("add")) {
                try {
                    int taskNumber = Integer.parseInt(splitCommand[2]) - 1;
                    String archiveName = joinStringArrayFromIndex(splitCommand, 3);
                    return new AddArchiveTaskCommand(taskNumber, archiveName);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidCommandDukeException("OOF!!! Please enter a task number!!");
                }

            }

            if (splitCommand[1].equals("view")) {
                String archiveName = joinStringArrayFromIndex(splitCommand, 2);
                return new ViewArchiveCommand(archiveName);
            }

            if (splitCommand[1].equals("viewall") && splitCommand.length == 2) {
                return new ViewAllArchiveCommand();
            }

            if (splitCommand[1].equals("list") && splitCommand.length == 2) {
                return new ListArchiveCommand();
            }

            if (splitCommand[1].equals("delete")) {
                try {
                    int taskNumber = Integer.parseInt(splitCommand[2]) - 1;
                    String archiveName = joinStringArrayFromIndex(splitCommand, 3);
                    return new DeleteArchiveTaskCommand(taskNumber, archiveName);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidCommandDukeException("OOF!!! Please enter a task number!!");
                }
            }

            if (splitCommand[1].equals("deleteall")) {
                String archiveName = joinStringArrayFromIndex(splitCommand, 2);
                return new DeleteAllArchiveCommand(archiveName);
            }

            if (splitCommand[1].equals("revert")) {
                try {
                    int taskNumber = Integer.parseInt(splitCommand[2]) - 1;
                    String archiveName = joinStringArrayFromIndex(splitCommand, 3);
                    return new RevertArchiveTaskCommand(taskNumber, archiveName);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new InvalidCommandDukeException("OOF!!! Please enter a task number!!");
                }
            }

            throw new InvalidCommandDukeException("OOF!!! Please enter a valid archive instruction :-(");

        default:
            throw new InvalidCommandDukeException("OOF!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Concatenates the elements in a string array, starting from the given index.
     *
     * @param arr String array to join elements from.
     * @param startIndex index of the array to start joining elements.
     * @return String made of the concatenated elements in the given array.
     */
    private static String joinStringArrayFromIndex(String[] arr, int startIndex) {
        String output = "";
        for (int i = startIndex; i < arr.length; i++) {
            output += arr[i];
            if (i != arr.length - 1) {
                output += " ";
            }
        }

        return output;
    }

    /**
     * Separates the elements in a String array that is divided by the given regex.
     * Concatenates each element into two strings - description and date.
     *
     * @param arr String array with elements to divide and concatenate.
     * @param regex The element in the array that divides the two outputs.
     * @return String array of size 2 that contains the description and date in each index.
     */
    private static String[] extractDescriptionDate(String[] arr, String regex) {
        String[] descriptionDate = new String[2]; // description stored in index 0, date stored in index 1.
        String description = "";
        String date = "";
        boolean isRecordingDescription = true;

        for (int i = 1; i < arr.length; i++) {
            if (!arr[i].equals(regex) && isRecordingDescription) {
                description += arr[i];
                if (i + 1 < arr.length && !arr[i + 1].equals(regex)) {
                    description += " ";
                }
            } else if (arr[i].equals(regex) && isRecordingDescription) {
                isRecordingDescription = false;
            } else if (!arr[i].equals(regex) && !isRecordingDescription) {
                date += arr[i];
                if (i != arr.length - 1) {
                    date += " ";
                }
            }
        }

        descriptionDate[0] = description;
        descriptionDate[1] = date;
        return descriptionDate;
    }
}
