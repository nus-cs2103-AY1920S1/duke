import java.util.ArrayList;

/**
 * TaskList is a class to process all the commands and adds them into
 * the tasklist. It is to split the Strings and allocate them accordingly
 * to the different Task classes.
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * Instantiate the TaskList class by passing an Arraylist as a parameter.
     * @param taskList A list to store all the tasks written by the User.
     */
    protected TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Instantiate the TaskList class when there are no existing tasks in the .txt file.
     * A new Arraylist is created to store the tasks.
     */
    protected TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Add tasks to the tasks list.
     * @param command The description of task and date/time(if applicable) from the User
     * @throws IllegalCommandException If the User inputs a wrong/invalid command.
     */
    protected void addTask(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("The description of a todo cannot be empty.");
        } else if (command.contains("todo")) {
            String[] splitCommand = command.split(" ", 2);
            taskList.add(new ToDos(splitCommand[1]));
        } else if (command.contains("event")) {
            String[] splitCommand = command.split(" ", 2);
            String[] splitEvent = (splitCommand[1].split("/", 2));
            if (validDateTime(splitEvent[1])) {
                taskList.add(new Events(splitEvent[0], splitEvent[1]));
            } else {
                throw new IllegalCommandException("Wrong date! Enter again");
            }
        } else if (command.contains("deadline")) {
            String[] splitCommand = command.split(" ", 2);
            String[] splitDeadline = (splitCommand[1].split("/", 2));
            if (validDateTime(splitDeadline[1])) {
                taskList.add(new Deadline(splitDeadline[0], splitDeadline[1]));
            } else {
                throw new IllegalCommandException("Wrong date! Enter again");
            }
        }
    }


    /**
     * Check if the date is valid.
     * @param dateTime the date and time input by User.
     * @return true if the date is valid.
     */
    protected boolean validDateTime(String dateTime) {
        String[] getDateTime = dateTime.split(" ");
        String[] getDate = getDateTime[1].split("/");
        String day = getDate[0];
        String month = getDate[1];
        String time = getDateTime[2];
        if (Integer.parseInt(day) > 31
                || Integer.parseInt(month) > 12
                || Integer.parseInt(time) > 2359) {
            return false;
        }
        return true;
    }

    /**
     * Check if the date is valid.
     * @param date the date input by User.
     * @return true if the date is valid.
     */
    protected boolean validDate(String date) {
        String[] getDate = date.split("/");
        String day = getDate[0];
        String month = getDate[1];
        if (Integer.parseInt(day) > 31 || Integer.parseInt(month) > 12) {
            return false;
        }
        return true;
    }


    /**
     * Delete the task that the User inputs in number. Eg. (User types "delete 3" means to
     * delete the task 3 from the tasks list).
     * @param command The command delete and the number of the task User wants to delete
     * @return the updated tasks list after deletion
     * @throws IllegalCommandException if User did not enter a number after delete or invalid command.
     */
    protected String deleteTask(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("Give a task to delete.");
        } else  {
            String[] splitCommand = command.split(" ");
            int taskDelete = Integer.parseInt(splitCommand[1]);
            if (taskDelete < taskList.size()) {
                throw new IllegalCommandException("No such task to delete!");
            }
            String deleted = taskList.get(taskDelete - 1).getDescription();
            taskList.remove(taskDelete - 1);
            return deleted;
        }
    }

    /**
     * Set the status of the selected task to be done. Eg. (User inputs "done 3" means User
     * wants to indicate that task 3 in the list is completed).
     * @param command The command done and the number of the task User set as done.
     * @return Updated tasks list with the selected task being marked done.
     * @throws IllegalCommandException if User did not enter a number after delete or invalid command.
     */
    protected String doneTask(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("There must be a number after done.");
        } else {
            String[] splitString = command.split(" ");
            int taskDone = Integer.parseInt(splitString[1]);
            if (taskDone < taskList.size()) {
                throw new IllegalCommandException("No task to delete!");
            } else {
                taskList.get(taskDone - 1).markAsDone();
                return taskList.get(taskDone - 1).getDescription();
            }

        }
    }

    /**
     * Other classes can retrieve the tasks list.
     * @return The tasks list.
     */
    protected ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    protected ArrayList<Task> findTasks(String command) throws IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("There must be an input to find.");
        } else {
            String[] splitString = command.split(" ",  2);
            ArrayList<Task> foundTasks = new ArrayList<>();
            for (Task task : this.taskList) {
                if (task.toString().contains(splitString[1])) {
                    foundTasks.add(task);
                }
            }
            return foundTasks;
        }
    }

    /**
     * A method to view date and add the tasks to a list when the date matches.
     * @param command the date input by user.
     * @return A list of tasks that matches the date.
     * @throws IllegalCommandException When user did not input a date.
     */
    protected ArrayList<Task> viewTasks(String command) throws  IllegalCommandException {
        if (!command.contains(" ")) {
            throw new IllegalCommandException("There must be a date input");
        } else {
            String[] splitString = command.split(" ", 2);
            if (validDate(splitString[1])) {
                ArrayList<Task> viewTasks = new ArrayList<>();
                for (Task task : this.taskList) {
                    processViewTasks(task, viewTasks, splitString[1]);
                }
                return viewTasks;
            } else {
                throw new IllegalCommandException("Invalid date to search!");
            }

        }
    }

    /**
     * A method to add task when the task date matches the input date.
     * @param task Selected task.
     * @param viewTasks List of tasks that matches the input date
     * @param date date input by User.
     */
    protected static void processViewTasks(Task task, ArrayList<Task> viewTasks, String date) {
        if (task.getNumericalDate().contains(date)) {
            viewTasks.add(task);
        }
    }

}
