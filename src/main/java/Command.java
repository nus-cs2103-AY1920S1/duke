public class Command {
    String[] info;
    boolean isAdd;
    boolean isDelete;
    boolean isDone;
    boolean isExit = false;

    public Command(String[] info, boolean add, boolean delete, boolean done){
        this.info = info;
        this.isAdd = add;
        this.isDelete = delete;
        this.isDone = done;
    }

    protected void execute(TaskManager tasks, UserInterface ui, FileManager fileManager) throws DukeException{
        if(isAdd){
            Task task = tasks.addNewTask(info);
            fileManager.saveToFile(task);
            ui.printAdd(task, tasks.getSize());
            return;
        }else if(isDelete){
            Task task = tasks.delete(info[1]);
            fileManager.fileDelete(Integer.parseInt(info[1]) - 1);
            ui.printDelete(task, tasks.getSize());
            return;
        }else if(isDone){
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
