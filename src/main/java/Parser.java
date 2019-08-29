import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Parser {
    private BufferedReader br;
    private TaskList listOfTasks;
    private Storage storage;
    private UI ui;

    public Parser(TaskList tasks, Storage storage, UI ui) {
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.listOfTasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    public void parse() throws Exception, DukeException {
        String[] inputMessage = br.readLine().split(" ");
        switch (inputMessage[0]) {
            case "list":
                if (listOfTasks.size() == 0) {
                    throw new DukeException("     The list is empty!");
                }
                ui.printList();
                break;
            case "bye":
                ui.printBye();
                System.exit(0);
                break;
            case "done":
                int index = Integer.parseInt(inputMessage[1]);
                if (index > listOfTasks.size() || index <= 0) {
                    throw new DukeException("     Such task does not exist!");
                }
                listOfTasks.get(index - 1).completeTask();
                storage.updateTaskList(listOfTasks.getTasks());
                storage.writeToFile();
                ui.printTaskDone(listOfTasks.get(index - 1));
                break;
            case "todo":
                if (inputMessage.length == 1) {
                    throw new DukeException("     OOPS!! The description of a todo cannot be empty");
                }
                String item = "";
                for (int i = 1; i < inputMessage.length; i++) {
                    if (i == inputMessage.length - 1) {
                        item += inputMessage[i];
                    } else {
                        item += inputMessage[i];
                        item += " ";
                    }
                }
                Todo newTodo = new Todo(item);
                listOfTasks.addTodo(newTodo);
                storage.updateTaskList(listOfTasks.getTasks());
                storage.writeToFile();
                ui.printTaskAdd(newTodo);
                break;
            case "deadline":
            case "event":
                String input = "";
                int marker = 0;
                for (int i = 1; i < inputMessage.length; i++) {
                    if (i + 1 >= inputMessage.length) {
                        throw new DukeException("     Please provide more information");
                    }
                    if (inputMessage[i + 1].equals("/by") || inputMessage[i + 1].equals("/at")) {
                        input += inputMessage[i];
                        marker = i + 1;
                        break;
                    } else {
                        input += inputMessage[i];
                        input += " ";
                    }
                }
                String extraInfo = "";
                String tempInfo = "";
                for (int i = marker + 1; i < inputMessage.length; i++) {
                    if (i == inputMessage.length - 1) {
                        tempInfo += inputMessage[i];
                    } else {
                        tempInfo += inputMessage[i];
                        tempInfo += " ";
                    }
                }
                String[] dateAndTime = tempInfo.split(" ");
                String[] date = dateAndTime[0].split("/");
                if (date.length != 3 || dateAndTime.length < 2) {
                    throw new DukeException("     Invalid date and time format!");
                }
                if (date[0].equals("") || date[1].equals("") || date[2].equals("")) {
                    throw new DukeException("     Invalid date and time format!");
                }
                int day = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);
                Date inputDate = new Date(day, month, year);
                Time inputTime = new Time(Integer.parseInt(dateAndTime[1]));
                if (!inputDate.isValid()) {
                    throw new DukeException("     Sorry! Invalid date format");
                }
                if (!inputTime.isValid()) {
                    throw new DukeException("     Sorry! Invalid time format");
                }
                extraInfo = inputDate + ", " + inputTime;
                if (extraInfo.equals("")) {
                    throw new DukeException("     Please provide more information");
                }
                if (inputMessage[0].equals("deadline")) {
                    if (!inputMessage[marker].equals("/by")) {
                        throw new DukeException("     Wrong syntax, should be using /by for deadline");
                    }
                    listOfTasks.addDeadline(new Deadline(input, extraInfo));
                } else if (inputMessage[0].equals("event")) {
                    if (!inputMessage[marker].equals("/at")) {
                        throw new DukeException("     Wrong syntax, should be using /at for event");
                    }
                    listOfTasks.addEvent(new Event(input, extraInfo));
                }
                storage.updateTaskList(listOfTasks.getTasks());
                storage.writeToFile();
                ui.printTaskAdd(listOfTasks.get(listOfTasks.size() - 1));
                break;
            case "delete":
                int indexToDelete = Integer.parseInt(inputMessage[1]);
                if (indexToDelete > listOfTasks.size() || indexToDelete <= 0) {
                    throw new DukeException("     Such task does not exist!");
                }
                Task toBeDeleted = listOfTasks.get(indexToDelete - 1);
                listOfTasks.removeTask(toBeDeleted);
                storage.updateTaskList(listOfTasks.getTasks());
                storage.writeToFile();
                ui.printTaskDelete(toBeDeleted);
                break;
            default:
                throw new DukeException("     OOPS!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
