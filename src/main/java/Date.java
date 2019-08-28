public class Date {
    String[] monthsInAYear = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December"};
    int day;
    String month;
    int year;
    Time time;

    // Takes in the date in the form dd/mm/yyyy tttt
    public Date(String date){
        String[] parts = date.split(" ");
        String[] subparts = parts[0].split("/");
        this.day = Integer.parseInt(subparts[0]);
        this.month = monthsInAYear[Integer.parseInt(subparts[1])-1];
        this.year = Integer.parseInt(subparts[2]);
        this.time = new Time(parts[1]);
    }

    public String toString(){
        return day + " of " + month + " " + year + ", " + time;
    }
}
