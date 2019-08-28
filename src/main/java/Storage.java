import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Storage of data locally
 */
public class Storage {
    File file;

    private Storage(){

    }

    private Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        this.file.getParentFile().mkdirs();
        this.file.createNewFile();
    }

    /**
     * Create new storage with filepath location
     * @param filePath save location
     * @return new Storage
     */
    public static Storage newStorage (String filePath){
        try {
            Storage rtv = new Storage(filePath);
            return rtv;
        } catch (IOException e){
            return null;
        }
    }

    /**
     * Load said object from location
     * @return object loaded
     */
    public Object load(){
        Object rtv = null;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            rtv = in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            // e.printStackTrace();
        } finally {

        }
        return rtv;
    }

    /**
     * Save object to location
     * @param obj object to be saved
     * @return if successful
     */
    public boolean save(Object obj) {
        try {
            FileOutputStream fileOut;
            fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
