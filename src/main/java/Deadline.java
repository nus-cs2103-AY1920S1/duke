public class Deadline extends Task {

	protected String by;

	public Deadline(String description, String by) {

		super(description);
		this.by = by;
	}

	public String checkTime(String time) {
		DateAndTime dateTime = new DateAndTime();
		String[] timeAndDate = time.split(" ");
		String formatDeadline = "";
		System.out.println(timeAndDate.length);
		for(int i = 0; i < timeAndDate.length; i++) {
			String[] date = timeAndDate[i].split("/");
			if(date.length == 3) {
				formatDeadline += dateTime.formatDate(timeAndDate[i]);
			} else if(timeAndDate[i].length() == 4) {
				formatDeadline += dateTime.formatTime(timeAndDate[i]);
			} else {
				formatDeadline += timeAndDate[i];
			}
			if((timeAndDate.length - 1) != i) {
				formatDeadline += ", ";
			} else {

			}
		}

		return formatDeadline;
	}

	@Override
	public String toString() {

		return "[D]" + super.toString() + " (by: " + checkTime(by) + ")";
	}
}
