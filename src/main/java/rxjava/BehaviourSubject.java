package rxjava;

import java.util.ArrayList;
import java.util.function.Consumer;

public class BehaviourSubject<T> {
    ArrayList<Subscription<T>> subs = new ArrayList<>();
    private T current;

    void next(T thing) {
        current = thing;
        subs.forEach(subscription -> subscription.update(thing));
    }

    public BehaviourSubject(T current) {
        this.current = current;
    }

    <R> Subscription<T> subscribe(Consumer<T> callback) {
        Subscription<T> sub = new Subscription<>(callback);
        sub.update(current);
        subs.add(sub);
        return sub;
    }
}
