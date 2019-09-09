/**
 * Command class of Duke.
 * Represents a command that the program executes depending on user input.
 */
public class Command {
    String[] info;
    boolean add;
    boolean delete;
    boolean done;
    boolean isExit = false;

    /**
     * Constructor that contains information from the user input
     * @param info information given by user
     * @param add if this is an add command
     * @param delete if this is a delete command
     * @param done if this is a done command
     */
    public Command(String[] info, boolean add, boolean delete, boolean done){
        this.info = info;
        this.add = add;
        this.delete = delete;
        this.done = done;
    }

    /**
     * Carries out the command by editing TaskManager, FileManager and using UserInterface objects
     * @param tasks TaskManager of the Duke instance
     * @param ui UserInterface object
     * @param fileManager FileManager of the Duke instance
     * @throws DukeException
     */
    protected void execute(TaskManager tasks, UserInterface ui, FileManager fileManager) throws DukeException{
        if(add){
            Task task = tasks.addNewTask(info);
            fileManager.saveToFile(task);
            ui.printAdd(task, tasks.getSize());
            return;
        }else if(delete){
            Task task = tasks.delete(info[1]);
            fileManager.fileDelete(Integer.parseInt(info[1]) - 1);
            ui.printDelete(task, tasks.getSize());
            return;
        }else if(done){
            Task task = tasks.done(info[1]);
            fileManager.fileSetDone(Integer.parseInt(info[1]) - 1);
            ui.printDone(task);
            return;
        }
        if(info[0].equals("list")){
            ui.printList(tasks.generateList());
        }else if(info[0].equals("bye")){
            this.isExit = true;
        }
    }

}
