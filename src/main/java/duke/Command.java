package java;

public interface Command {
    String getName();
    String execute(String[] args);
}
