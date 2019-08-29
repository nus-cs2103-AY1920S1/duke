class Parser {

    private static final String ERROR_DATE_FORMAT = "☹ OOPS!!! Date must be in MM/DD/YYYY HHMM format.";

    static DukeDate parseToDate(String date) throws DukeDateFormatException {
        // Date format : MM/DD/YYYY HHMM
        String[] dateAndTime = date.split(" ");
        String[] dateFormat = dateAndTime[0].split("/");
        try {
            return new DukeDate(Integer.parseInt(dateFormat[2]),
                                Integer.parseInt(dateFormat[0]),
                                Integer.parseInt(dateFormat[1]),
                                Integer.parseInt(dateAndTime[1].substring(0, 2)),
                                Integer.parseInt(dateAndTime[1].substring(2)));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeDateFormatException(ERROR_DATE_FORMAT);
        }
    }

}
