import java.util.ArrayList;
import java.util.Arrays;

public class Output {
	private static String[] headerLines = new String[]{};
	private static String[] footerLines = new String[]{};
	private static String leftBorder = "";
	private static int leftIndentWidth = 0;
	
	private ArrayList<String> outputLines;

	public Output(String... outputLines) {
		this.outputLines = new ArrayList<String>(Arrays.asList(outputLines));
	}

	public static void setHeader(String... headerLines) {
		Output.headerLines = headerLines;
	}

	public static void setFooter(String... footerLines) {
		Output.footerLines = footerLines;
	}

	public static void setLeftBorder(String leftBorder) {
		Output.leftBorder = leftBorder;
	}

	public static void setLeftIndent(int count) {
		Output.leftIndentWidth = count;
	}

	public Output addLines(String... lines) {
		for (String line : lines) {
			outputLines.add(line);
		}
		return this;
	}

	public void print() {
		for (String header : headerLines) {
			System.out.println(leftBorder + header);
		}
		for (String output : outputLines) {
			System.out.println(leftBorder + " ".repeat(leftIndentWidth) + output);
		}
		for (String footer : footerLines) {
			System.out.println(leftBorder + footer);
		}
		System.out.println();
	}
}
