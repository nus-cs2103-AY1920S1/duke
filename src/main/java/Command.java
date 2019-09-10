public class Command {
    String[] info;
    boolean add;
    boolean delete;
    boolean done;
    boolean isFind;
    boolean isExit = false;

    public Command(String[] info, boolean add, boolean delete, boolean done, boolean find){
        this.info = info;
        this.add = add;
        this.delete = delete;
        this.done = done;
        this.isFind = find;
    }

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
        }else if(isFind){
            String foundList = tasks.findMatch(info[1]);
            ui.printFind(foundList);
        }
        if(info[0].equals("list")){
            ui.printList(tasks.generateList());
        }else if(info[0].equals("bye")){
            this.isExit = true;
        }
    }

}
