package duke.io;

import duke.DukeException;

import java.util.ArrayList;
import java.io.PrintStream;

public class Output {
	// formatting fields
	private String[] headerLines;
	private String[] footerLines;
	private String leftBorder;
	private int leftIndentWidth;

	// formatting settings
	private boolean printHeaderOn;
	private boolean printFooterOn;
	private boolean printLeftBorderOn;
	private boolean printIndentOn;
	private boolean wrapOn;

	// output buffer
	private ArrayList<String> outputLines;

	//output destination
	private PrintStream printStream;

	public Output(PrintStream printStream) {
		this.printStream = printStream;

	    // no header, footer, or leftborder by default
		this.headerLines = new String[]{};
		this.footerLines = new String[]{};
		this.leftBorder = "";

		// indent width 0 by default
		this.leftIndentWidth = 0;

		// all true by default, no need to toggle boolean when the header footer etc are set
		this.printHeaderOn = true;
		this.printFooterOn = true;
		this.printLeftBorderOn = true;
		this.printIndentOn = true;

		// text wrapping off by default
		this.wrapOn = false;

		// empty buffer
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
		// string concatenation behind the scenes
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

	// clear output buffer
	public void flush() {
		outputLines = new ArrayList<String>();
	}


	// prints the output buffer to printstream
	public void print() {
		// prints header if enabled
		if (printHeaderOn) {
			for (String header : headerLines) {
				if (printLeftBorderOn) {
					printStream.print(leftBorder);
				}
				printStream.println(header);
			}
		}

		// split all the output into separate lines if there are still \n or \r left
		outputLines = resolveNewLines(outputLines);

		// appends each line to the left indent and left border if any and enabled
		for (String output : outputLines) {
			if (wrapOn) {
				// chops strings that are too long for the header/footer and appends the next line to the remaining
				for (String wrapped : wrap(outputWidth() - leftIndentWidth, output)) {
					if (printLeftBorderOn) {
						printStream.print(leftBorder);
					}
					if (printIndentOn) {
						printStream.print(" ".repeat(leftIndentWidth));
					}
					printStream.println(wrapped);
				}
			} else {
				if (printLeftBorderOn) {
					printStream.print(leftBorder);
				}
				if (printIndentOn) {
					printStream.print(" ".repeat(leftIndentWidth));
				}
				printStream.println(output);
			}
		}

		//clears the buffer so other output can be added without wrong output
		flush();

		// prints footer appended to leftborder if enabled
		if (printFooterOn) {
			for (String footer : footerLines) {
				if (printLeftBorderOn) {
					printStream.print(leftBorder);
				}
				printStream.println(footer);
			}
		}
		printStream.println();
	}

	// helper method to calculate the max width of output. minimum output width 1 otherwise infinite loops
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

	// helper method to wrap overlength strings
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

	// helper method to list of strings which may include new line characters with list of single line strings
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
