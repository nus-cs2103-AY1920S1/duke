package rxjava;

import java.util.function.Consumer;

public class Subscription<T> {
    Consumer<T> callback;

    void update(T thing) {
        callback.accept(thing);
    }

    Subscription(Consumer<T> f) {
        this.callback = f;
    }
}
