/**
 * Stub used for testing EventsTask
 *
 */
public class DateTimeStubSixthAugustFourpm extends DateTime{

    /**
     * Hard coded onstructor creates a specific instance of the
     * datetime class
     *
     */
    public DateTimeStubSixthAugustFourpm() {
        super(16, 00, 6, "Aug", 2019);
    }

    /**
     * toString method is hardcoded to return a specific string
     * for testing.
     * 
     * @return "6 aug 4pm".
     */
    @Override 
    public String toString(){
        return "6 Aug 4pm";
    }

}