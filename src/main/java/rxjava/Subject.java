package rxjava;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * A class that pushes notifications.
 * @param <T> the generic type of the data that this class should push to listening objects.
 */
public class Subject<T> {
    ArrayList<Subscription<T>> subs = new ArrayList<>();

    public void next(T thing) {
        subs.forEach(subscription -> subscription.update(thing));
    }

    /**
     * Creates a subscription that listens to the latest value pushed by Subject.
     * @param callback called when new value is received by subject.
     * @return a subscription that can run a Consumer on receiving a new value.
     */
    public Subscription<T> subscribe(Consumer<T> callback) {
        Subscription<T> sub = new Subscription<>(callback);
        subs.add(sub);
        return sub;
    }
}
