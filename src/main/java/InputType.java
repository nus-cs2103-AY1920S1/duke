public enum InputType {
    TODO,
    EVENT,
    DEADLINE,
    LIST,
    BYE,
    DONE,
    DELETE,
    ERROR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
