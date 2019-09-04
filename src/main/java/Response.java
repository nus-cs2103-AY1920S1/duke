/**
 * A wrapper class for the responses of each task (e.g. deleteTask returns the string "Task deleted")
 */
public class Response {

    private String response;

    public Response (String response){
        this.response = response;
    }

    @Override
    public String toString(){
        return response;
    }
}