import java.io.FileNotFoundException;
import java.util.List;
import java.io.File;
//import java.io.FileNotFoundException;


public class ReadAndWrite {
    public static String projectDir;
    public static String txtFileLocation;

    //    public class  GetParentDir{
//        private static void dirlist(String fname){
//            File dir = new File(fname);
//            String parentpath = dir.getParent();
//            System.out.println("Current Directory : "+ dir);
//            System.out.println("parent Directory : "+ parentpath);
//        }
//
//        public static void main(String[] args){
//            String currentdir = System.getProperty("user.dir");
//            dirlist(currentdir);
//        }
//    }
    public static void updateProjectDir() {
        String current = System.getProperty("user.dir");
        File currentFile = new File(current);
        projectDir = currentFile.getParent();
    }

    public static void readFile(List<Task> myList) throws FileNotFoundException{
        txtFileLocation = projectDir + "/data/duke.txt";
        try{
            File dataFile = new File(txtFileLocation);

        } catch (FileNotFoundException ex) {
            System.out.println("[Warning]: Could find DB File");
        }
    }
}

