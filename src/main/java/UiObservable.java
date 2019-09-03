public interface UiObservable {
    void registerObserver(UiObserver u);
    void removeObserver(UiObserver u);
    String getReply();
}
    
