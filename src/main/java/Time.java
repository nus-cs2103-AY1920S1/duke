public class Time {

	protected String rawTime;
	protected boolean isPastNoon;
	protected int hour;
	protected int minutes;
	protected boolean isNull = true;

	public Time(String rawTime) throws DukeException {
		this.rawTime = rawTime;
		if (rawTime != null) {
			isNull = false;
			processTime();
		}
	}

	protected void processTime() throws DukeException {
		if (isValidTime(rawTime)) {
			hour = Integer.parseInt(rawTime.substring(0, 2));
			if (hour > 11) {
				isPastNoon = true;
				hour = hour % 12;
				if (hour == 0) {
					hour = 12;
				}
			} else {
				isPastNoon = false;
			}
			minutes = Integer.parseInt(rawTime.substring(2));
		} else {
			throw new DukeException(
					"\u2639 OOPS!!! Please specify a valid time. " +
							"Also ensure that you have specified the time in 24-hour clock convention E.g. 1800 ");
		}
	}

	protected boolean isValidTime(String rawTime) {
		if (rawTime.length() < 4) {
			return false;
		} else {
			int inputHour  = Integer.parseInt(rawTime.substring(0, 2));
			int inputMinutes = Integer.parseInt(rawTime.substring(2));
			if (inputHour > 23 || inputMinutes > 59) {
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean isNull() {
		return isNull;
	}

	@Override
	public String toString() {
		if (minutes != 0) {
			return hour + (isPastNoon ? "pm" : "am");
		} else {
			return hour + ":" + (minutes < 10 ? "0" + minutes : minutes) + (isPastNoon ? "pm" : "am");
		}
	}

}
