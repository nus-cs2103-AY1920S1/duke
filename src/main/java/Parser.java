import java.util.LinkedList;

public class Parser {
    private String[] split;
    private String type;

    public Parser(String s){
        this.split = s.split(" ");
        this.type = split[0];
    }

    public String getType(){
        return this.type;
    }

    public LinkedList<String> getDesc(){
        //String actual = "";
        LinkedList<String> copy = new LinkedList<>();
        for(int i = 1; i < split.length; i++){
            copy.add(split[i]);
        }

        return copy;
        // if (type.equals("delete") || type.equals("list") || type.equals("bye") || type.equals("done")){
        //     return split[1];
        // } else if (type.equals("todo")){
        //     actual =  String.join(" ", copy);
        // } else if(type.equals("deadline")){
        //     String help = String.join(" ", copy);

        //     String task = "";
        //     String time = "";


        // }
            
    }

}