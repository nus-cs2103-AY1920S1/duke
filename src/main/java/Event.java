public class Event extends Task {

	protected String at;

	public Event(String description, String at) {

		super(description);
		this.at = at;
	}

	public String checkTime(String time) {
		DateAndTime eventDateTime = new DateAndTime();
		String[] timeAndDate = time.split(" ");
		String formatDeadline = "";
		System.out.println(timeAndDate.length);
		for(int i = 0; i < timeAndDate.length; i++) {
			String[] date = timeAndDate[i].split("/");
			if(date.length == 3) {
				formatDeadline += eventDateTime.formatDate(timeAndDate[i]);
			} else if(timeAndDate[i].length() == 4) {
				formatDeadline += eventDateTime.formatTime(timeAndDate[i]);
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

		return "[E]" + super.toString() + " (at: " + checkTime(at) + ")";
	}
}
