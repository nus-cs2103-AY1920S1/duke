/*
package tagModule;

public class TagTestDriver {
    static class TagAbstractStub extends TagAbstractWrapper {
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

    public static void main(String[] args) {

        TagStore store1 = new TagStore();
        store1.insertTag("#alive", new TagAbstractStub("loki"));          
        store1.insertTag("#alive", new TagAbstractStub("thor"));          
        store1.insertTag("#asgard", new TagAbstractStub("loki"));         
        store1.insertTag("#asgard", new TagAbstractStub("thor"));         
        store1.insertTag("#tessaract", new TagAbstractStub("loki"));
        store1.insertTag("#tessaract", new TagAbstractStub("thor")); 

        store1.queryAll();
        System.out.println();

        boolean rep1 = store1.replaceTaggedObject(new TagAbstractStub("thor"), new TagAbstractStub("heimdall"));
        System.out.println(rep1);
        System.out.println(store1.queryAll());

        boolean del1 = store1.deleteTaggedObject(new TagAbstractStub("loki"));
        System.out.println(del1);
        System.out.println(store1.queryAll());
    }
}

*/
