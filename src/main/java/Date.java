import java.time.Month;

public class Date {

	protected String rawDate;
	protected int day;
	protected int year;
	protected Month month;
	protected boolean isNull = true;

	public Date(String rawDate) throws DukeException {
		this.rawDate = rawDate;
		if (rawDate != null) {
			isNull = false;
			processDate();
		}
	}

	protected void processDate() throws DukeException {
		String[] dateComponents = rawDate.split("/");
		if (dateComponents.length < 3) {
			throw new DukeException("\u2639 OOPS!!! Please specify a date in the form day/month/year. E.g. 2/12/2019");
		} else {
			int inputDay = Integer.parseInt(dateComponents[0]);
			int inputYear = Integer.parseInt(dateComponents[2]);
			int inputMonthNum = Integer.parseInt(dateComponents[1]);
			year = inputYear;
			if (isValidMonth(inputMonthNum)) {
				month = Month.of(inputMonthNum);
			} else {
				throw new DukeException("\u2639 OOPS!!! Please specify a valid month.");
			}
			if (isValidDay(inputDay, inputMonthNum, inputYear)) {
				day = inputDay;
			} else {
				throw new DukeException("\u2639 OOPS!!! Please specify a valid day.");
			}
		}
	}

	protected boolean isValidMonth(int inputMonthNum) {
		if (inputMonthNum < 1 || inputMonthNum > 12) {
			return false;
		} else {
			return true;
		}
	}

	protected boolean isValidDay(int inputDay, int inputMonthNum, int inputYear) {
		switch (inputMonthNum) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return inputDay >= 1 && inputDay <= 31;
		case 2:
			if (isLeapYear(inputYear)) {
				return inputDay >= 1 && inputDay <= 29;
			} else {
				return inputDay >= 1 && inputDay <= 28;
			}
		case 4:
		case 6:
		case 9:
		case 11:
			return inputDay >= 1 && inputDay <= 30;
		default:
			return false;
		}
	}

	protected boolean isLeapYear(int inputYear) {
		if (inputYear % 400 == 0) {
			return true;
		} else if (inputYear % 100 == 0) {
			return false;
		} else if (inputYear % 4 == 0) {
			return true;
		} else {
			return true;
		}
	}

	public boolean isNull() {
		return isNull;
	}

	@Override
	public String toString() {
		if ((day % 10) == 1 && day != 11) {
			return day + "st of " + month.toString() + " " + year;
		} else if ((day % 10) == 2) {
			return day + "nd of " + month.toString() + " " + year;
		} else if ((day % 10) == 3) {
			return day + "rd of " + month.toString() + " " + year;
		} else {
			return day + "th of " + month.toString() + " " + year;
		}
	}

}
