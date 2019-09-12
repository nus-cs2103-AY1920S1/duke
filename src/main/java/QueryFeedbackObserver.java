//tag
import java.util.stream.Stream;
public interface QueryFeedbackObserver {
    public <T,E> void queryFeedbackUpdate(String header, 
            T searchTerm, String mid, Stream<E> stream,
            String footer);
}
