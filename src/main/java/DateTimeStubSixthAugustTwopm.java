/**
 * Stub used for testing EventsTask
 *
 */
public class DateTimeStubSixthAugustTwopm extends DateTime{

    /**
     * Hard coded onstructor creates a specific instance of the
     * datetime class
     *
     */
    public DateTimeStubSixthAugustTwopm() {
        super(14, 00, 6, "Aug", 2019);
    }

    /**
     * toString method is hardcoded to return a specific string
     * for testing.
     * 
     * @return "6 aug 2pm".
     */

    @Override 
    public String toString(){
        return "6 Aug 2pm";
    }

}