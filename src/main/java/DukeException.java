public class DukeException extends Exception {

    String exceptionString;

    public DukeException(String exceptionString){
        this.exceptionString = exceptionString;
    }

    @Override
    public String toString(){
        return exceptionString;
    }

}
