import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

  private File f;

  public Storage(String filePath) {
    File f = new File(filePath);
    this.f = f;
  }

  public File load() {
    return f;
  }

  public void appendToFile(String textToAdd) throws IOException {
    FileWriter fw = new FileWriter(f.getAbsolutePath(), true);
    fw.write(textToAdd);
    fw.close();
  }
}