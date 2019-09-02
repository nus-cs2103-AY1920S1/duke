public class Duke  {

    private String filepath;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;


    public Duke(){
        this.filepath = "../resources/data/duke.txt";
        this.storage = new Storage(filepath);
        this.taskList = storage.loadStorage();
        this.parser = new Parser(storage, taskList);
    }

    public Parser getParser(){
        return parser;
    }
    

}
