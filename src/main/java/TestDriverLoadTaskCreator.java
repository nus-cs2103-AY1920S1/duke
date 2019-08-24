class TestDriverLoadTaskCreator {
    public static void main(String[] args) {
        TaskCreator taskCreator = new LoadTaskCreator();
        try {
            System.out.println(taskCreator.createTask("D | 1 | Summon Cthulhu | innsmouth"));
            System.out.println(taskCreator.createTask("E | 0 | Halloween Party | day after friday 13th"));
            System.out.println(taskCreator.createTask("T | 1 | Photocopy Necronomicon"));
            System.out.println(taskCreator.createTask(""));
        } catch (OWOException e) {
            System.out.println(e);
        }
    }
}
