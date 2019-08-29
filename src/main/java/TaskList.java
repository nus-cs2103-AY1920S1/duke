import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private int count;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        count = taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void displayList() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            int taskListNumber = i + 1;
            System.out.println("    " + taskListNumber + "." + taskList.get(i));
        }
    }

    public void markItemComplete(int index, Ui ui) throws DukeException {
        if (index <= 0 || index > count) {
            throw new DukeException("Invalid task number!");
        }
        Task t = taskList.get(index - 1);
        t.complete();
        ui.drawLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     " + t);
        ui.drawLineNewLine();
    }

    public void deleteItem(int index, Ui ui) throws DukeException {

        if (index <= 0 || index > count) {
            throw new DukeException("Invalid task number!");
        }
        index--;
        Task t = taskList.get(index);
        taskList.remove(index);
        ui.drawLine();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("      " + t);
        count -= 1;
        String message =
                count == 1
                        ? "     Now you have 1 task in the list"
                        : "     Now you have " + count + " tasks in the list";
        System.out.println(message);
        ui.drawLineNewLine();
    }

    public void registerNewTask(String[] inputParts, Ui ui) throws DukeException {
        checkCommandValidity(inputParts[0]);
        Task t = addToList(inputParts[1], inputParts[0]);
        echo(t, ui);
    }

    static void checkCommandValidity(String type) throws DukeException {
        if (!type.equals("todo") && !type.equals("deadline") && !type.equals("event")) {
            throw new DukeException("I don't know what that means :(");
        }
    }

    public Task addToList(String s, String type) throws DukeException {
        String trimmed = s.replaceAll("^\\s+", "");
        if (trimmed.equals("")) {
            throw new DukeException("Description cannot be empty!");
        }
        if (type.equals("todo")) {
            taskList.add(new Todo(s, count + 1));
        } else if (type.equals("deadline")) {
            String[] parts = s.split("\\/" + "by");
            if (parts.length < 2) {
                String message = "Date required! ";
                message += "Format: deadline YOUR_TASK_NAME /by YOUR_DEADLINE";
                throw new DukeException(message);
            } else if (parts.length != 2) {
                String message = "Format: deadline YOUR_TASK_NAME /by YOUR_DEADLINE";
                throw new DukeException(message);
            }
            taskList.add(new Deadline(
                    parts[0].substring(0, parts[0].length() - 1),
                    count + 1,
                    createDateAndTime(parts[1].substring(1))
            ));
        } else if (type.equals("event")) {
            String[] parts = s.split("\\/" + "at");
            if (parts.length < 2) {
                String message = "Date required! ";
                message += "Format: event YOUR_TASK_NAME /at YOUR_EVENT_DATE";
                throw new DukeException(message);
            } else if (parts.length != 2) {
                String message = "Format: event YOUR_TASK_NAME /at YOUR_EVENT_DATE";
                throw new DukeException(message);
            }
            taskList.add(new Event(
                    parts[0].substring(0, parts[0].length() - 1),
                    count + 1,
                    createDateAndTime(parts[1].substring(1))
            ));
        }
        count += 1;
        return taskList.get(count - 1);
    }

    static String createDateAndTime(String s) {
        String[] parts = s.split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains("/")) {
                parts[i] = createDate(parts[i]);
            } else if (is24hrFormat(parts[i])) {
                parts[i] = createTime(parts[i]);
            }
        }
        String result = "";
        for (String part : parts) {
            result += " " + part;
        }
        return result.substring(1);
    }

    static boolean is24hrFormat(String time) {
        return isInteger(time) && time.length() == 4 && Integer.parseInt(time) < 2400;
    }

    static String createTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        String min = time.substring(2, 4);
        String timeOfDay = hour > 11 ? "pm" : "am";
        hour = (hour > 12)
                ? (hour - 12)
                : ((hour == 0) ? 12 : hour);
        return hour + ":" + min + timeOfDay;
    }

    static String createDate(String date) {
        String[] parts = date.split("/");
        String[] month = {
            "",
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        };
        boolean validDate = true;
        if (parts.length == 3) {
            for (int i = 0; i < 3; i++) {
                if (!isInteger(parts[i])) {
                    validDate = false;
                } else if (i == 1
                        && (Integer.parseInt(parts[i]) < 1 || Integer.parseInt(parts[i]) > 12)) {
                    validDate = false;
                }
            }
        } else {
            validDate = false;
        }
        if (validDate) {
            if (parts[2].length() == 4) {
                return parts[0] + " " + month[Integer.parseInt(parts[1])] + " " + parts[2];
            } else {
                return parts[2] + " " + month[Integer.parseInt(parts[1])] + " " + parts[0];
            }
        } else {
            return date;
        }
    }

    static boolean isInteger(String n) {
        try {
            Integer.parseInt(n);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void echo(Task t, Ui ui) {
        ui.drawLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + t);
        if (count == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else {
            String message = "     Now you have " + count + " tasks in the list.";
            System.out.println(message);
        }
        ui.drawLineNewLine();
    }
}
