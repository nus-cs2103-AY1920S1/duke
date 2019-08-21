public class Task {

    String _name;
    String _status;

    public Task(String name) {
        _name = name;
        _status = "✗";
    }

    @Override
    public String toString() {  return "[" + _status + "] " + _name; }

    public void finishTask() {_status = "✓";}

}