public interface Manager {
    public void readFile();
    public void writeFile();
    public void add(String type, String arg1, String arg2);
    public void delete(int arg);
    public void done(int arg);
    public void list();
}
