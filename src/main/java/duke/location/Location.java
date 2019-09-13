package duke.location;

public class Location {
    private static LocationList locations = new LocationList();

    private String name;

    public static LocationList getList() {
        return locations;
    }

    public Location(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String store() {
        return "P|" + name;
    }
}
