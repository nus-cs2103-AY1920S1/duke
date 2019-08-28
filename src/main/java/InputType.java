public enum InputType {
    TODO,
    EVENT,
    DEADLINE,
    LIST,
    BYE,
    DONE,
    DELETE,
    FIND,
    ERROR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
