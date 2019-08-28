import java.io.*;

class Storage {
    private static final String fileName = "/home/dingyuchen/cs2103/duke/src/main/data/duke.ser";

    static void save(TodoList todoList) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(todoList);
            oos.close();
        } catch (IOException e) {
            System.out.println("Something has gone wrong.");
        }

    }

    static TodoList load() {
        TodoList todoList = new TodoList();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            todoList = (TodoList) objectInputStream.readObject();

            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        } catch (ClassNotFoundException e) {
            System.out.println("Classes are not loaded");
        }
        return  todoList;
    }

//    private static Task parse(String[] args) {
//        Task task = null;
//        switch (args[0]) {
//            case "T":
//                task = new Todo(args[1]);
//                if (Boolean.parseBoolean(args[2])) task.markAsDone();
//                break;
//            case "D":
//                task = new Deadline(args[1], args[2]);
//                if (Boolean.parseBoolean(args[3])) task.markAsDone();
//                break;
//            case "E":
//                task = new Event(args[1], args[2]);
//                if (Boolean.parseBoolean(args[3])) task.markAsDone();
//                break;
//            default:
//                System.out.println("Unexpected value: " + args[0]);
//        }
//
//        return  task;
//    }
}