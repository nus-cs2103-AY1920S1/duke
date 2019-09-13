//tag
import java.util.stream.Stream;
public interface QueryFeedbackObserver {
    public <T,E> void queryFeedbackUpdate(T searchTerm, 
            Stream<E> stream, String msg);
}
