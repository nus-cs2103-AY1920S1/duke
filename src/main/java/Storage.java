import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage object holds the file, in which writing and reading will happen.
 */
public class Storage {

  private File f;

  public Storage(String filePath) {
    File f = new File(filePath);
    this.f = f;
  }

  public File load() {
    return f;
  }

  /**
   * Adds text supplied to File
   * 
   * @param textToAdd.
   * @throws IOException If there's any input / output errors.
   */
  public void appendToFile(String textToAdd) throws IOException {
    FileWriter fw = new FileWriter(f.getAbsolutePath(), true);
    fw.write(textToAdd);
    fw.close();
  }
}