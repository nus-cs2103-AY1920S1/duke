public class Event extends Task {

	protected String at;

	public Event(String description, String at) {

		super(description);
		this.at = at;
	}


	public String getVenue() {

		return checkTime(at);
	}

	public String checkTime(String time) {
		DateAndTime eventDateTime = new DateAndTime();
		String[] timeAndDate = time.split(" ");
		String formatDeadline = "";
		for(int i = 0; i < timeAndDate.length; i++) {
			String[] date = timeAndDate[i].split("/");
			if(date.length == 3) {
				if (i == 0) {
					formatDeadline += eventDateTime.formatDate(timeAndDate[i] + ", ");
				} else {
					formatDeadline += ", " + eventDateTime.formatDate(timeAndDate[i]);
				}

			} else if(timeAndDate[i].length() == 4) {
				if( i == 0) {
					formatDeadline += eventDateTime.formatTime(timeAndDate[i]);
				} else {
					formatDeadline += " " + eventDateTime.formatTime(timeAndDate[i]);
				}

			} else {
				formatDeadline += timeAndDate[i] + " ";
			}

		}

		return formatDeadline;
	}

	@Override
	public String toString() {

		return "[E]" + super.toString() + " (at: " + checkTime(at) + ")";
	}
}
