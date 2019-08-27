public class DukeException extends Exception {

    protected DukeExceptionType type;

    public DukeException(String error, DukeExceptionType type){
        super(error);
        this.type = type;
    }

    public void printError(){
        String horizontalLine = "    ____________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(horizontalLine);
    }

    public String getMessage() {
    	switch (type) {
    		case INVALIDCOMMAND:
    			return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    		case NOTINTEGER:
    			return "☹ Invalid input, must be an integer!!";
    		case MISSINGTASK:
    			return "☹ No such task";
    		case GENERALMISTAKE:
    			return super.getMessage();
    		default:
    			return "Unknown error! Please try again.";
    	}
    }
}
