import java.util.ArrayList;
import java.util.Arrays;

public class Output {
	private String[] headerLines;
	private String[] footerLines;
	private String leftBorder;

	private int leftIndentWidth;

	private boolean printHeaderOn;
	private boolean printFooterOn;
	private boolean printLeftBorderOn;
	private boolean printIndentOn;
	private boolean wrapOn;

	private ArrayList<String> outputLines;

	public Output() {
		this.headerLines = new String[]{};
		this.footerLines = new String[]{};
		this.leftBorder = "";

		this.leftIndentWidth = 0;

		this.printHeaderOn = true;
		this.printFooterOn = true;
		this.printLeftBorderOn = true;
		this.printIndentOn = true;
		this.wrapOn = false;

		this.outputLines = new ArrayList<String>();
	}

	public void setHeader(String... headerLines) {
		this.headerLines = headerLines;
	}

	public void setFooter(String... footerLines) {
		this.footerLines = footerLines;
	}

	public void setLeftBorder(String leftBorder) {
		this.leftBorder = leftBorder;
	}

	public void setLeftIndent(int count) {
		this.leftIndentWidth = count;
	}

	public Output addLine(String... lineSegments) {
		StringBuffer line = new StringBuffer();
		for (String segment : lineSegments) {
			line.append(segment);
		}
		outputLines.add(line.toString());
		return this;
	}

	public void setHeaderOn(boolean on) {
		printHeaderOn = on;
	}

	public void setFooterOn(boolean on) {
		printFooterOn = on;
	}

	public void setLeftBorderOn(boolean on) {
		printLeftBorderOn = on;
	}

	public void setIndentOn(boolean on) {
		printIndentOn = on;
	}

	public void setWrapOn(boolean on) {
		wrapOn = on;
	}

	public void flush() {
		outputLines = new ArrayList<String>();
	}

	public void print() {
		if (printHeaderOn) {
			for (String header : headerLines) {
				if (printLeftBorderOn) {
					System.out.print(leftBorder);
				}
				System.out.println(header);
			}
		}

		outputLines = resolveNewLines(outputLines);

		for (String output : outputLines) {
			if (wrapOn) {
				for (String wrapped : wrap(outputWidth() - leftIndentWidth, output)) {
					if (printLeftBorderOn) {
						System.out.print(leftBorder);
					}
					if (printIndentOn) {
						System.out.print(" ".repeat(leftIndentWidth));
					}
					System.out.println(wrapped);
				}
			} else {
				if (printLeftBorderOn) {
					System.out.print(leftBorder);
				}
				if (printIndentOn) {
					System.out.print(" ".repeat(leftIndentWidth));
				}
				System.out.println(output);
			}
		}

		flush();

		if (printFooterOn) {
			for (String footer : footerLines) {
				if (printLeftBorderOn) {
					System.out.print(leftBorder);
				}
				System.out.println(footer);
			}
		}
		System.out.println();
	}

	private int outputWidth() {
		int width = 1;
		for (String line : headerLines) {
			width = Math.max(width, line.length());
		}
		for (String line : footerLines) {
			width = Math.max(width, line.length());
		}
		return width;
	}

	private static ArrayList<String> wrap(int width, String unwrapped) {
		ArrayList<String> wrapped = new ArrayList<>();
		String remaining = unwrapped;
		while (remaining.length() > width) {
			wrapped.add(remaining.substring(0, width));
			remaining = remaining.substring(width);
		}
		wrapped.add(remaining);
		return wrapped;

	}

	private static ArrayList<String> resolveNewLines(ArrayList<String> lines) {
		ArrayList<String> result = new ArrayList<>();
		for (String line : lines) {
			for (String lineSplit : line.split("[\\n\\r]")) {
				result.add(lineSplit);
			}
		}
		return result;
	}
}
