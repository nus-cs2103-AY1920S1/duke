public class GooeyBridge implements UiObserver {
    private ControllerInterface friday;

    private String response;

    public GooeyBridge(ControllerInterface control){
        this.friday = control; 
    }

    public void update(UiObservable u) {
        this.response = u.getReply();
    }

    public String getText() {
        return this.response;
    }

    public void inputText(String input) {
        this.friday.whatsGoingOn(input);
    }
}
