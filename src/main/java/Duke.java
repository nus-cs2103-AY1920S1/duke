public class Duke {

    private String filepath;
    private Storage storage;
    private TaskList taskList;
    private UI ui; 

    public Duke(String filepath){
        this.filepath = filepath;
        this.storage = new Storage(filepath);

    }

    public void run(){
    
        String startingMessage = "Hello! I'm Duke\nWhat can I do for you?" ;
        System.out.println(startingMessage);

        this.taskList = storage.loadStorage();
        ui = new UI(storage, taskList);
        ui.run();
        
    }
    
    public static void main(String[] args){
        new Duke("../resources/data/duke.txt").run();
    }
}
