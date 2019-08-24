class TestDriverBasicStorage {
    public static void main(String[] args) {
        TaskModelInterface model = new StubTaskModel();
        BasicStorage storage = new BasicStorage(model);
        storage.update(model);

    }
}
