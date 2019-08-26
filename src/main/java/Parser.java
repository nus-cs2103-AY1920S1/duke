public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.length() >= 6 && fullCommand.substring(0, 4).equals("done")) {
            try {
                int taskNumber = Integer.parseInt(fullCommand.substring(5)) - 1;
                return new DoneCommand(taskNumber);
            } catch (NumberFormatException e) {
                throw new InvalidCommandDukeException("☹ OOF!!! Please enter a task number!!");
            }

        } else if (fullCommand.length() >= 8 && fullCommand.substring(0, 6).equals("delete")) {
            try {
                int taskNumber = Integer.parseInt(fullCommand.substring(7)) - 1;
                return new DeleteCommand(taskNumber);
            } catch (NumberFormatException e) {
                throw new InvalidCommandDukeException("☹ OOF!!! Please enter a task number!!");
            }

        } else if (fullCommand.length() >= 6 && fullCommand.substring(0, 4).equals("find")) {
            String keyWord = fullCommand.substring(5);
            if (keyWord.trim().equals("")) {
                throw new InvalidCommandDukeException("☹ OOF!!! Please enter a keyword!!");
            } else {
                return new FindCommand(keyWord);
            }


        } else if (fullCommand.trim().equals("")) {
            throw new InvalidCommandDukeException("☹ OOF!!! I'm sorry, but I don't know what that means :-(");
        } else {
            String[] splitCommand = fullCommand.split(" ");
            Task currentTask = null;
            String description = "";

            if (splitCommand[0].equals("todo")) {
                description = fullCommand.substring(5).trim();

                if (!description.equals("")) {
                    currentTask = new ToDo(description);
                    return new AddCommand(currentTask);
                } else {
                    throw new InvalidTaskDescriptionDukeException("☹ OOF!!! The description of a todo cannot be empty!!");
                }

            } else if (splitCommand[0].equals("deadline")) {
                String by = "";
                boolean descriptionRecorded = false;

                for (int i = 1; i < splitCommand.length; i++) {
                    if (!splitCommand[i].equals("/by") && !descriptionRecorded) {
                        description += splitCommand[i];
                        if (i + 1 < splitCommand.length && !splitCommand[i + 1].equals("/by")) {
                            description += " ";
                        }
                    } else if (splitCommand[i].equals("/by") && !descriptionRecorded){
                        descriptionRecorded = true;
                    } else if (!splitCommand[i].equals("/by") && descriptionRecorded){
                        by += splitCommand[i];
                        if (i != splitCommand.length - 1) {
                            by += " ";
                        }
                    }
                }

                if (!description.trim().equals("") && !by.equals("")) {
                    currentTask = new Deadline(description, by);
                    return new AddCommand(currentTask);
                } else {
                    throw new InvalidTaskDescriptionDukeException("☹ OOF!!! The description/timing of a deadline cannot be empty!!");
                }

            } else if (splitCommand[0].equals("event")) {
                String at = "";
                boolean descriptionRecorded = false;

                for (int i = 1; i < splitCommand.length; i++) {
                    if (!splitCommand[i].equals("/at") && !descriptionRecorded) {
                        description += splitCommand[i];
                        if (i + 1 < splitCommand.length && !splitCommand[i + 1].equals("/at")) {
                            description += " ";
                        }
                    } else if (splitCommand[i].equals("/at") && !descriptionRecorded){
                        descriptionRecorded = true;
                    } else if (!splitCommand[i].equals("/at") && descriptionRecorded){
                        at += splitCommand[i];
                        if (i != splitCommand.length - 1) {
                            at += " ";
                        }
                    }
                }

                if (!description.trim().equals("") && !at.equals("")) {
                    currentTask = new Event(description, at);
                    return new AddCommand(currentTask);
                } else {
                    throw new InvalidTaskDescriptionDukeException("☹ OOF!!! The description/timing of an event cannot be empty!!");
                }

            } else {
                throw new InvalidCommandDukeException("☹ OOF!!! I'm sorry, but I don't know what that means :-(");
            }

        }
    }
}
