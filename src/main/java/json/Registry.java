package json;
import java.lang.reflect.Type;
import java.io.*;
import java.util.*;
import java.util.function.*;

public class Registry {
	private static HashMap<Class<?>, BiConsumer<JsonWriter.ValueContext, ?>> encoderMap
		= new HashMap<>();
	public static <T> void register(
			Class<T> clazz,
			BiConsumer<JsonWriter.ValueContext, T> encoder) {
		encoderMap.put(clazz, encoder);
			}

	static {
		register(List.class, (ctx, list) ->
				ctx.writeValues(
					((List<?>) list).iterator(),
					JsonWriter.ValueContext::writeValue));
		register(Iterable.class, (ctx, itb) ->
				ctx.writeValues(
					((Iterable<?>) itb).iterator(),
					JsonWriter.ValueContext::writeValue));
		register(Map.class, (ctx, map) ->
				ctx.writeDict(
					(Map<?,?>) map,
					JsonWriter.ValueContext::writeValue));
		register(Number.class, (ctx, val) -> ctx.writeNumber(val.doubleValue()));
		register(String.class, (ctx, str) -> ctx.writeString(str));
		register(Boolean.class, (ctx, b) -> ctx.writeBoolean(b));
		register(Object.class, (ctx, obj) -> ctx.writeString(obj.toString()));
	}

	static <T> BiConsumer<JsonWriter.ValueContext, T> getEncoder(Class<? extends T> clazz) {
		System.out.printf("Finding encoder for %s\n", clazz);
		Class<?> current = clazz;
		while(!Object.class.equals(current)) {
			System.out.printf("Checking superclass %s\n", current);
			BiConsumer<JsonWriter.ValueContext, ?> encoder = encoderMap.get(current);
			if(encoder != null) {
				System.out.printf("Using encoder for %s\n", current);
				return (BiConsumer<JsonWriter.ValueContext, T>) encoder;
			}
			current = current.getSuperclass();
		}
		current = clazz;
		while(!Object.class.equals(current)) {
		for(Type iface : current.getInterfaces()) {
			System.out.printf("Checking iface %s\n", iface);
			if(iface instanceof Class) {
				Class<?> c = (Class<?>) iface;
				BiConsumer<JsonWriter.ValueContext, ?> encoder = encoderMap.get(c);
				if(encoder != null) {
					System.out.printf("Using encoder for %s\n", c);
					return (BiConsumer<JsonWriter.ValueContext, T>) encoder;
				}
			}
		}
			current = current.getSuperclass();
		}

		BiConsumer<JsonWriter.ValueContext, ?> encoder = encoderMap.get(Object.class);
		if(encoder != null) {
			System.out.printf("Using default encoder for Object\n");
			return (BiConsumer<JsonWriter.ValueContext, T>) encoder;
		}
		throw new JsonException("No handler for class %s", clazz);
	}
}
