package duke.ui;

public class Response {
    private boolean isExit = false;
    private String message;

    public Response(String message, boolean isExit){
        this.message = message;
        this.isExit = isExit;
    }

    public Response(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public boolean isExitResponse(){
        return isExit;
    }
}
