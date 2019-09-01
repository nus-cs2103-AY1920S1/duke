import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser object handles all input reading and prints the respective output.
 */
public class Parser {

  /**
   * A method to determine the suffix, given the date
   * 
   * @param dateTime
   * @return String representing the suffix: "st", "nd", "rd" or "th"
   */
  private static String getSuffix(LocalDateTime dateTime) {

    int day = dateTime.getDayOfMonth();
    int remainder = day % 10;

    if (remainder == 1) {
      return "st";
    } else if (remainder == 2) {
      return "nd";
    } else if (remainder == 3) {
      return "rd";
    } else {
      return "th";
    }
  }

  /**
   * A method to convert dates into the correct format of type: "1st November
   * 2019, 2.30pm"
   * 
   * @param dateTimeString
   * @return String representing dates in the format: "1st November 2019, 2.30pm"
   * @throws DateTimeParseException
   */
  private static String getDate(String dateTimeString) throws DateTimeParseException {

    // Create formatter to recognise input pattern and convert to LocalDateTime
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
    String suffix = getSuffix(dateTime) + " of";

    // Format LocalDateTime
    DateTimeFormatter wantedFormat = DateTimeFormatter.ofPattern(" d'" + suffix + "' MMMM yyyy, h.mm a");
    String formattedDate = wantedFormat.format(dateTime);
    return formattedDate;
  }

  public static Command parse(String fullCommand) {
    String[] arr = fullCommand.split(" ", 2);
    String keyCommand = arr[0].trim();

    try {
      if (keyCommand.equals("list")) {
        return new ListCommand();
      } else if (keyCommand.equals("bye")) {
        return new ExitCommand();
      } else if (keyCommand.equals("find")) {
        String description = arr[1].trim();
        return new FindCommand(description);
      } else if (keyCommand.equals("done")) {
        int index = Integer.parseInt(arr[1].trim());
        return new DoneCommand(index);
      } else if (keyCommand.equals("delete")) {
        int index = Integer.parseInt(arr[1].trim());
        return new DeleteCommand(index);
      } else if (keyCommand.equals("todo")) {

        if (arr.length != 2) {
          return new InvalidCommand("Description for todo should not be empty!");
        } else {
          String description = arr[1].trim();
          return new AddCommand(keyCommand, description);
        }
      } else if (keyCommand.equals("deadline")) {

        String info = arr[1].trim();
        String[] wordArr = info.split("/by", 2);

        if (wordArr.length != 2) {
          return new InvalidCommand("Deadlines should be followed with a /by.");
        }

        String formattedDate = getDate(wordArr[1].trim());
        return new AddCommand(keyCommand, wordArr[0].trim(), formattedDate);
      } else if (keyCommand.equals("event")) {

        String info = arr[1].trim();
        String[] wordArr = info.split("/at", 2);

        if (wordArr.length != 2) {
          return new InvalidCommand("Events should be followed with a /at.");
        }

        String formattedDate = getDate(wordArr[1].trim());
        return new AddCommand(keyCommand, wordArr[0].trim(), formattedDate);

      } else {
        return new InvalidCommand("Sorry! I don't understand what that means :(");
      }
    } catch (NumberFormatException e) {
      return new InvalidCommand("An integer should be followed by done / delete.");
    } catch (DateTimeParseException e) {
      return new InvalidCommand("Dates should be supplied in the format: dd/mm/yyyy hhmm.");
    }

  }

}