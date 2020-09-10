package duke.parser;

/**
 * Exception thrown when file read has incorrect format.
 */
public class IncorrectFileFormatException extends Exception {
	
	/**
	 * Constructor to create an exception when incorrect format in file detected.
	 *
	 * @param loadingError String message when loading error occurs when loading file.
	 */
	public IncorrectFileFormatException(String loadingError) {
		super();
	}
}
