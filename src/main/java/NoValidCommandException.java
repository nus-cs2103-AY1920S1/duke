public class NoValidCommandException extends Exception {

    private String message;
    public NoValidCommandException (String message){
        this.message = message;
    }
    public void printErrorMessage(){
        System.out.println(message);
    }
}
