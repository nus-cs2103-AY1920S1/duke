public class Task {
    protected boolean completed = false;
    protected String name;

    public Task(String name) {
        this.name = name;
    }

    public void complete() {
        this.completed = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[+] " + this.name);
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        if (!this.completed) {
            return "[-] " + this.name;
        } else {
            return "[+] " + this.name;
        }
    }

}
