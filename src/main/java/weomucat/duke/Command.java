package weomucat.duke;

import java.util.HashMap;

public interface Command {
	// Default parameter is ""
	String PARAMETER_DEFAULT = "";
	String PARAMETER_AT = "/at";
	String PARAMETER_BY = "/by";

	String[] getParameterOptions();

	void setParameters(HashMap<String, String> parameters) throws DukeException;

	void run() throws DukeException;
}
