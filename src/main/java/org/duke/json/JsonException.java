package org.duke.json;

public class JsonException extends RuntimeException {
	public JsonException(String message) {
		super(message);
	}
	public JsonException(String message, Throwable cause) {
		super(message, cause);
	}
	public JsonException(String format, Object... args) {
		super(String.format(format, args));
	}
	public JsonException(String format, Throwable cause, Object... args) {
		super(String.format(format, args), cause);
	}
}
