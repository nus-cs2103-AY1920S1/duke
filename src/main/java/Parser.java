public class Parser {
    public Actions type = Actions.NONE;
    public void parse(String input){
        String[] inputArr = input.split(" ");
        if (input.equals("bye")){
            this.type = Actions.BYE;
        } else if (inputArr[0].equals("done")){
            this.type = Actions.DONE;
        } else if (input.equals("list")){
            this.type = Actions.LIST;
        } else if (inputArr[0].equals("todo")){
            this.type = Actions.TODO;
        } else if (inputArr[0].equals("deadline")){
            this.type = Actions.DEADLINE;
        } else if (inputArr[0].equals("event")){
            this.type = Actions.EVENT;
        }else if (inputArr[0].equals("delete")){
            this.type = Actions.DELETE;
        } else {
            this.type = Actions.NONE;
        }
    }
    public Actions getType(){
        return this.type;
    }

//method takes input, returns type of output --> compare to java enums
}
