package duke.place;

public class Place {
    private static PlaceList locations = new PlaceList();

    private String name;

    public static PlaceList getList() {
        return locations;
    }

    public Place(String name) {
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
