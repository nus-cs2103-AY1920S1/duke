public class Todos extends Task {

    Todos(String name) {
        super(name);
    }

    @Override
    protected String getTypeSymbol() {
        return "[T]";
    }
}
