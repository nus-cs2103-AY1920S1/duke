public class Bot {
    public String response;
    public Bot() {

    }

    public void echo(String s) {
        this.response = s;
    }

    public void exit() {
        this.response = "Bye. Hope to see you again soon!";
    }

    public void greet() {
        this.response = "Hello! I'm Duke\n" +
                "     What can I do for you?";
    }

    public String toString() {
        return "    ____________________________________________________________\n     "
                + this.response
                + "\n    ____________________________________________________________\n";
    }
}
