package weomucat.duke.command;

import java.util.HashMap;
import weomucat.duke.exception.DukeException;

public interface Command {

  String PARAMETER_AT = "/at";
  String PARAMETER_BY = "/by";

  String[] getParameterOptions();

  void setParameters(String body, HashMap<String, String> parameters) throws DukeException;

  void run() throws DukeException;
}
