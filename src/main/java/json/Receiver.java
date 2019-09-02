package json;
@FunctionalInterface
public interface Receiver {
	<T> T receive(ValueHandler<? extends T> handler);
}
