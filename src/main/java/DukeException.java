public class DukeException extends Exception {
    String message;

    public DukeException(String errormsg){
        String modifiedMsg = "\u2639 " + errormsg;
        this.message = modifiedMsg;
    }

    public String getMessage(){
        return this.message;
    }
}
