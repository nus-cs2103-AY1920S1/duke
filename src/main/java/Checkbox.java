public enum Checkbox {

    TICK("[✓]"),
    CROSS("[✗]");

    public final String icon;
    private Checkbox (String icon) {
        this.icon = icon;
    }

}
