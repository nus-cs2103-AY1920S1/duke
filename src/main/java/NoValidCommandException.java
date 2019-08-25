public class NoValidCommandException extends Exception {

    private String message;


    /**
     * @param message a custom message given by user for NoValidCommandException
     *
     */

    public NoValidCommandException (String message){
        this.message = message;
    }


    /**
     * prints customised error message for NoValidCommandException
     *
     */

    public void printErrorMessage(){
        System.out.println(message);
    }
}
