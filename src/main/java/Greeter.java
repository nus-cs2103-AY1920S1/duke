public class Greeter {

    Formatter formatter = new Formatter();

    public Greeter() {}
    public String greeting() {
        return "Hello I'm Duke\n" + "What can I do for you?";
    }

    public String byeString() {
        return "Bye. Hope to see you again soon!";
    }

    public void greet() {
        formatter.printLine();
        formatter.printFormat(greeting());
        formatter.printLine();
    }

    public void bye() {
        formatter.printLine();
        formatter.printFormat(byeString());
        formatter.printLine();
    }

}
