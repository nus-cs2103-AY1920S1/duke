package rxjava;

import java.util.function.Consumer;
//Solution below adapted from https://rxjs-dev.firebaseapp.com/guide/subscription.

public class Subscription<T> {
    Consumer<T> callback;

    void update(T thing) {
        callback.accept(thing);
    }

    Subscription(Consumer<T> f) {
        this.callback = f;
    }
}
