package json;

import java.io.*;
import java.util.regex.Pattern;

public class JsonParser {
	private final PushbackReader reader;

	private JsonParser(Reader reader) {
		this.reader = new PushbackReader(new BufferedReader(reader));
	}

	private char next() {
		try {
			int c = this.reader.read();
			if(c == -1) {
				throw new ParserException("Unexpected end of input");
			}
			return (char) c;
		} catch(IOException e) {
			throw new ParserException("Unexpected end of input", e);
		}
	}

	private void unread(char c) {
		try {
			this.reader.unread(c);
		} catch(IOException e) {
			throw new ParserException("Unable to pushback character, this should not happen!", e);
		}
	}

	private void eatWhitespace() {
		while(true) {
			char c = this.next();
			if(!Character.isWhitespace(c)) {
				this.unread(c);
				break;
			}
		}
	}

	private static enum CommaState {
		Empty,
		ReadElement,
		ReadComma
	}

	private <T> T readObjectFields(ObjectHandler<T> handler) {
		CommaState commaState = CommaState.Empty;
		boolean reachedEnd = false;
		while(!reachedEnd) {
			this.eatWhitespace();
			char first = this.next();
			switch(first) {
				case '"':
					if(commaState == CommaState.ReadElement) {
						throw new ParserException("Missing comma between fields");
					}
					String fieldName = this.readString();
					this.eatWhitespace();
					this.expect(":");
					this.eatWhitespace();
					handler.handleField(fieldName, this::readValue);
					this.eatWhitespace();

					commaState = CommaState.ReadElement;
					break;
				case ',':
					if(commaState != CommaState.ReadElement) {
						throw new ParserException("Extra commas in object");
					}
					commaState = CommaState.ReadComma;
					break;
				case '}':
					if(commaState == CommaState.ReadComma) {
						throw new ParserException("Extra commas in object");
					}
					reachedEnd = true;
					break;
				default:
					throw new ParserException("Unknown character when reading object");
			}
		}
		return handler.handleEnd();
	}
	private <T> T readArrayElems(ArrayHandler<T> handler) {
		CommaState commaState = CommaState.Empty;
		boolean reachedEnd = false;
		while(!reachedEnd) {
			this.eatWhitespace();
			char first = this.next();
			switch(first) {
			case ',':
				if(commaState != CommaState.ReadElement) {
					throw new ParserException("Extra commas in array");
				}
				commaState = CommaState.ReadComma;
				break;
			case ']':
				if(commaState == CommaState.ReadComma) {
					throw new ParserException("Extra commas in array");
				}
				reachedEnd = true;
				break;
			default:
				this.unread(first);
				handler.handleElement(this::readValue);
				commaState = CommaState.ReadElement;
			}
		}
		return handler.handleEnd();
	}

	private void readEscape(StringBuilder sb) {
		char c = this.next();
		switch(c) {
			case '\\':
			case '\"':
			case '/':
				sb.append(c);
				break;
			case 'b':
				sb.append('\b');
				break;
			case 'f':
				sb.append('\f');
				break;
			case 'n':
				sb.append('\n');
				break;
			case 'r':
				sb.append('\r');
				break;
			case 't':
				sb.append('\t');
				break;
			case 'u':
				char[] code = new char[4];
				for(int i=0;i<4;i++) {
					code[i] = this.next();
				}
				String codeString = new String(code);
				try {
					int codePoint = Integer.parseInt(codeString, 16);
					sb.append(Character.toChars(codePoint));
				} catch(NumberFormatException e) {
					throw new ParserException("Bad unicode escape %s", e, codeString);
				}
				break;
			default:
				throw new ParserException("Unknown escape %c", c);
		}
	}

	private String readString() {
		StringBuilder sb = new StringBuilder();
		while(true) {
			char c = this.next();
			if(c == '"') {
				break;
			}
			switch(c) {
				case '\\':
					this.readEscape(sb);
					break;
				default:
					sb.append(c);
			}
		}
		return sb.toString();
	}

	private double readNumber() {
		//TODO proper handling
		StringBuilder sb = new StringBuilder();

		while(true) {
			char c = this.next();
			if(Character.isWhitespace(c)) {
				break;
			}
			if(!(Character.isDigit(c) || c == '.' || c == '-')) {
				this.unread(c);
				break;
			}
			sb.append(c);
		}
		return Double.parseDouble(sb.toString());
	}

	private void expect(String target) {
		this.eatWhitespace();
		for(int i=0;i<target.length();i++) {
			char c = this.next();
			if(c != target.charAt(i)) {
				throw new ParserException("Unexpected character %c, expected %c",c,target.charAt(i));
			}
		}
	}

	private <T> T readValue(ValueHandler<T> handler) {
		this.eatWhitespace();
		char first = this.next();
		switch(first) {
			case '{':
				return this.readObjectFields(handler.handleObject());
			case '[':
				return this.readArrayElems(handler.handleArray());
			case '"':
				return handler.handleString(this.readString());
			case 't':
				this.expect("rue");
				return handler.handleBoolean(true);
			case 'f':
				this.expect("alse");
				return handler.handleBoolean(false);
			case 'n':
				this.expect("ull");
				return handler.handleNull();
			default:
				//TODO: Avoid unicode points
				if(!Character.isDigit(first)) {
					throw new ParserException("Unknown character %c reading JSON value!", first);
				}
				this.unread(first);
				return handler.handleNumber(this.readNumber());
		}
	}

	public static <T> T parse(Reader r, ValueHandler<T> handler) {
		JsonParser p = new JsonParser(r);
		return p.readValue(handler);
	}
}
