package place;

import java.util.HashMap;
import java.util.TreeSet;

public class Place {
    private static HashMap<String, Place> refLatLong = new HashMap<>();
    private static HashMap<String, Place> refAlias = new HashMap<>();
    private TreeSet<String> aliases = new TreeSet<>();
    private double latitude;
    private double longitude;

    private Place(String alias, double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        refLatLong.put(String.valueOf(latitude) + (char) 31 + longitude, this);
        refAlias.put(alias, this);
        aliases.add(alias);
    }

    /**
     * Adds Place if does not exists.
     * Then add relevant missing references for future lookup.
     * And finally returns newly initialised or existing place.
     *
     * @param alias String alias for the place
     * @param latitude Latitude as double
     * @param longitude Longitude as double
     * @return Existing place Object if found, otherwise add a new Place
     */
    public static Place addIfAbsent(String alias, double latitude, double longitude) {
        Place p = of(alias);
        if (p == null) {
            p = of(latitude, longitude);
        }
        if (p != null) {
            refLatLong.put(String.valueOf(latitude) + (char) 31 + longitude, p);
            refAlias.put(alias, p);
            p.aliases.add(alias);
        } else {
            p = new Place(alias, latitude, longitude);
        }
        return p;
    }

    public static Place of(double latitude, double longitude) {
        return refLatLong.get(String.valueOf(latitude) + (char) 31 + longitude);
    }

    public static Place of(String alias) {
        return refAlias.get(alias.trim());
    }

    @Override
    public String toString() {
        return " is at " + latitude + ", " + longitude + '.';
    }

    /**
     * Returns all aliases of the Place object.
     *
     * @return String for Ui
     */
    public String aliases() {
        StringBuilder sb = new StringBuilder("Alias found:\n");
        for (String s : aliases) {
            sb.append(s)
                .append('\n');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
