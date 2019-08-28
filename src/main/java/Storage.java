import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;

class Storage {
    private final String fileName;

    Storage(String s) {
        this.fileName = s;
    }

    void save(TodoList todoList) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(todoList);
            oos.close();
        } catch (IOException e) {
            System.out.println("Something has gone wrong.");
        }

    }

    TodoList load() {
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
        return todoList;
    }
}