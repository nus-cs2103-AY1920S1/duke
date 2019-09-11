//import tagModule.TagAbstractWrapper;

public class TagAbstractStub extends TagAbstractWrapper {
    private String containsStr;
    public TagAbstractStub(String str) {
        this.containsStr = str;
    } 

    @Override
    public String tagCode() {
        return this.toString();
    }

    @Override
    public String toString() {
        return this.containsStr;
    }
}
