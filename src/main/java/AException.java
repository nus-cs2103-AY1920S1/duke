public class AException{
    
    public void emptyToDoException(){
        System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public void emptyDeadlineException(){
        System.out.println("\t☹ OOPS!!! The description of a deadline cannot be empty.");
    }

    public void emptyByException(){
        System.out.println("\t☹ OOPS!!! The date and time of deadline not specified.");
    }

    public void emptyEventException(){
        System.out.println("\t☹ OOPS!!! The description of a event cannot be empty.");
    }

    public void emptyAtException(){
        System.out.println("\t☹ OOPS!!! The date and time of event not specified.");
    }

    public void dontUnderstand(){
        System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}