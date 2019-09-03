public class Duke {
    private UserInterface ui;
    private TaskManager tasks;
    private FileManager fileManager;

    private Duke(String pathname) {
        this.ui = new UserInterface();
        ui.printWelcome();
        this.tasks = new TaskManager();
        try{
            this.fileManager = new FileManager();
            if(this.fileManager.initialize(pathname, tasks)){
                ui.printNewFile();
            }else {
                ui.printLoadSave();
            }
        }catch(DukeException e){
            ui.printError(e.getMessage());
        }
    }

    private void run(){
        boolean isExit = false;
        while(!isExit){
            try {
                String userCommand = ui.readLine();
                Command c = Parser.parse(userCommand);
                c.execute(tasks, ui, fileManager);
                isExit = c.isExit;
            }catch(DukeException e){
                ui.printError(e.getMessage());
            }
        }
        ui.printExit();
    }

    public static void main(String[] args) {
        Duke process = new Duke("src/main/data/list.txt");
        process.run();
    }
}
