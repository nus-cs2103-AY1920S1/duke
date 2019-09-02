import java.util.ArrayList;

public class Parser {
    private String inputString;


    protected ArrayList<String> parse(String s) {
        ArrayList<String> output = new ArrayList<String>();
        String[] separated = s.split(" ");
        if(separated.length == 1) {
            output.add(separated[0]);
            //System.out.println("end of parser");
            return output;
        } else {
            int index = 1;
            StringBuilder sb = new StringBuilder();
            output.add(separated[0]);
            while(index < separated.length) {
                if(!separated[index].equals("/by")) {
                    if(!separated[index].equals("/at")) {
                        sb.append(" ");
                        sb.append(separated[index]);
                        index++;
                    } else {
                        break;
                    }
                } else {
                    //System.out.println("breaking");
                    break;
                }
            }
            //System.out.println("out of first loop");
            output.add(sb.toString());
            sb.setLength(0);
            //System.out.println(sb.length());
            while(index < separated.length) {
                if(index != separated.length - 1) {
                    sb.append(separated[index]);
                    sb.append(" ");
                    index++;
                } else {
                    sb.append(separated[index]);  
                    index++;
                }
            }
            if(sb.length() != 0) {
                output.add(sb.toString());
            }
        }
        //System.out.println("end of parser");
        //System.out.println(output.get(1));
        return output;
    } 

}