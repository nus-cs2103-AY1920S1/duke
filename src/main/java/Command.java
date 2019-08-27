public class Command {

    private String command;

    public Command(String command) {
        this.command = command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (command) {
        case "bye":
            break;
        case "list":
            ui.listTask(tasks);
            break;
        case "done":
            int number = ui.readNumber();
            tasks.markDone(number);
            ui.printDone(number, tasks);
            storage.updateDone(number);
            break;
        case "todo":
            String taskname = ui.readLine().trim();
            if (ui.checkValidity(taskname)) {
                Task t = new Todo(taskname);
                storage.addTodo(taskname);
                tasks.add(t);
                ui.printAdd(t);
            }
            break;
        case "deadline":
            String deadline = ui.readLine().trim();
            if (ui.checkValidity(deadline)) {
                String[] arrDeadline = deadline.split("/by");
                String timeDeadline = Parser.convertDateAndTime(arrDeadline[1].trim());
                Task taskDeadline = new Deadline(arrDeadline[0].trim(), timeDeadline);
                storage.addDeadline(deadline);
                tasks.add(taskDeadline);
                ui.printAdd(taskDeadline);
            }
            break;
        case "event":
            String event = ui.readLine().trim();
            if (ui.checkValidity(event)) {
                String[] arrEvent = event.split("/at");
                String time = Parser.convertDateAndTime(arrEvent[1].trim());
                Task taskEvent = new Event(arrEvent[0].trim(), time);
                storage.addEvent(event);
                tasks.add(taskEvent);
                ui.printAdd(taskEvent);
            }
            break;
        case "delete":
            int deletionNumber = ui.readNumber();
            storage.delete(deletionNumber);
            Task toDelete = tasks.getList().get(deletionNumber - 1);
            tasks.delete(deletionNumber);
            ui.printDelete(toDelete);
            break;
        default:
            try {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean isExit() {
        boolean flag = false;
        if (command.equals("bye")) {
            flag = true;
        }
        return flag;
    }

}