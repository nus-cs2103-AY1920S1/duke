/**
 * Conversion for date and time from dd/mm/yyyy to [date] of
 * [month] and 24 hour to 12 hour format respectively.
 */
public class DateAndTime {

	protected String date;
	protected String time;
	String border = "____________________________________________________________";

	public DateAndTime() {
		date = "";
		time = "";
	}


	/**
	 * Changing the format of the date from dd/mm/yyyy to
	 * [date] of [month] [year]
	 *
	 * @param date Date of the deadline or event.
	 * @return Formatted date.
	 */
	public String formatDate(String date) {
		String formattedDate = "";

		try {
			String[] dateFormat = date.split("/");

			int day = Integer.parseInt(dateFormat[0]);
			String month = dateFormat[1];
			String year = dateFormat[2];

			String formattedDay = formatDay(day);
			String formattedMonth = formatMonth(month);
			formattedDate = formattedDay + " of " + formattedMonth + " " + year;


		} catch (NumberFormatException e) {
			System.out.println(border + "\n" + e + "\n" + border);
		}
		return formattedDate;
	}


	/**
	 * Changing the format of time from 24-hour clock
	 * to 12-hour clock.
	 *
	 * @param time Time of the deadline or event.
	 * @return Formatted time.
	 */
	public String formatTime(String time) {
		boolean isString = false;
		String formattedTime = "";
		try {
			int twentyFourTime = Integer.parseInt(time);
			int twelveHour = (twentyFourTime / 100) % 12;
			int twelveMin = twentyFourTime % 100;
			if (twelveMin > 59 || (twentyFourTime / 100) > 24) {
				throw new DukeException("Wrong time");
			}
			if (twelveMin == 0) {
				if (twentyFourTime < 1200) {
					formattedTime = twelveHour + "am";
				} else {
					formattedTime = twelveHour + "pm";
				}

			} else {
				if (twelveHour == 0 && twentyFourTime < 1200) {
					twelveHour = 12;
					formattedTime = twelveHour + ":" + twelveMin + "am";
				} else {
					twelveHour = 12;
					formattedTime = twelveHour + ":" + twelveMin + "pm";
				}

			}
		} catch (NumberFormatException e) {
			isString = true;
		} catch (DukeException e) {
			System.out.println(border + "\n" + e + "\n" + border);

		} finally {
			if (isString) {
				formattedTime = time;
			}
		}

		return formattedTime;

	}

	/**
	 * Formatting of the date.
	 *
	 * @param day day of the date.
	 * @return Formatted day.
	 */
	public String formatDay(int day) {
		String dayFormat = "";

		if (day % 10 == 1) {
			if (day == 11) {
				dayFormat = day + "th";
			} else {
				dayFormat = day + "st";
			}

		} else if (day % 10 == 2) {
			if (day == 12) {
				dayFormat = day + "th";
			} else {
				dayFormat = day + "nd";
			}

		} else if (day % 10 == 3) {
			if (day == 13) {
				dayFormat = day + "th";
			} else {
				dayFormat = day + "rd";
			}

		} else if (day > 31) {
			try {
				throw new DukeException("wrong day!");
			} catch (DukeException e) {
				System.out.println(border + "\n" + e + "\n" + border);
			}

		} else {
			dayFormat = day + "th";
		}

		return dayFormat;
	}


	/**
	 * Formatting of the month from number form to the name of the month.
	 *
	 * @param month
	 * @return Formatted month.
	 */
	public String formatMonth(String month) {

		String translatedMonth = "";
		switch (month) {

			case "1":
				translatedMonth = "January";
				break;
			case "2":
				translatedMonth = "February";
				break;
			case "3":
				translatedMonth = "March";
				break;
			case "4":
				translatedMonth = "April";
				break;
			case "5":
				translatedMonth = "May";
				break;
			case "6":
				translatedMonth = "June";
				break;
			case "7":
				translatedMonth = "July";
				break;
			case "8":
				translatedMonth = "August";
				break;
			case "9":
				translatedMonth = "September";
				break;
			case "10":
				translatedMonth = "October";
				break;
			case "11":
				translatedMonth = "November";
				break;
			case "12":
				translatedMonth = "December";
				break;
			default:
				System.out.println("Wrong month");
				break;
		}
		return translatedMonth;
	}


}
