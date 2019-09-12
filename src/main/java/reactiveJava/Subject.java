package reactiveJava;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Subject<T> {
    ArrayList<Subscription<T>> subs = new ArrayList<>();

    public void next(T thing) {
        subs.forEach(subscription -> subscription.update(thing));
    }

    public Subscription<T> subscribe(Consumer<T> callback) {
        Subscription<T> sub = new Subscription<>(callback);
        subs.add(sub);
        return sub;
    }
}
