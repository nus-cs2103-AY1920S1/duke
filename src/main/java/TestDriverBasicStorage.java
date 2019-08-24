class TestDriverBasicStorage {
    public static void main(String[] args) {
        TaskInterface task = 
            new ToDosImplementation("read bok", false);

        //System.out.println(task);
        //System.out.println(task.getSaveFormat());
        //System.out.println();
        TaskModelInterface model = new StubTaskModel();
        BasicStorage storage = new BasicStorage(model);
//        storage.update(model);
        storage.loadData();

    }
}
