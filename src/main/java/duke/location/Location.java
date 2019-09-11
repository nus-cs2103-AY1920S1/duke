package duke.location;

public class Location {
    private String name;

    public static LocationList getLocations() {
        return locations;
    }

    private static LocationList locations = new LocationList();

    public Location(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
