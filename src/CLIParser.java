import java.util.Objects;

public class CLIParser {
    private Integer c_flag = -1;
    private boolean a_flag;
    private String current_Parameter;


    public CLIParser(String[] args){ // todo: move this code to be insude the control.
        try {
            for (String arg : args) {

                if (current_Parameter == null) {
                    current_Parameter = arg;
                    continue;
                }

                if (current_Parameter.equals("-c")) {
                    c_flag = Integer.parseInt(arg);
                    current_Parameter = null;
                    continue;
                }

                if(current_Parameter.equals("-a")) {
                    a_flag = true;
                    current_Parameter = null;
                }
            }
        } catch(NumberFormatException e) {
           assert true;
        }

        // due to the implementation of this parser, it can not determine if just a '-c' was used.
        // for this edge case, we have this code to correct the behavior:
        // Note, this whole parser probably needs a refactor, but I am not sure of a way to do it better atm.
        if (c_flag == -1) {
            for (String arg : args){
                if (Objects.equals(arg, "-c")){
                    c_flag = 0;
                    break;
                }
            }
        }
    }

    public int getCFlag(){
        return (c_flag == null ? -1 : c_flag);
    }

    public boolean getAFlag(){
        return this.a_flag;
    }

    public static void main(String[] args){
        CLIParser parser = new CLIParser(args);
        System.out.println(parser.c_flag);

    }

}
