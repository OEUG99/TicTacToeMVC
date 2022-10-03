import java.util.Objects;



public class CLIParser {
    private Integer c_flag = -1;
    private boolean a_flag = false;
    private String current_Parameter;



    /**
     * <p>This class represents a parser</p>
     * it processes the list of arguments.
     * @param args command line arguments
     */
    public CLIParser(String[] args){
        /*
        This class is heavily based on Finite-State Machines, and is a primary driving force for this design pattern.
        reference: https://en.wikipedia.org/wiki/Finite-state_machine

        This is the only half-decent way that I've learned about that could parse arguments, without bringing in something like regex.
         */


        int c_pos = 0, a_pos = 0;
        for(int i = 0; i <= args.length -1; i++){
            if (args[i].equalsIgnoreCase("-c")){
                c_pos = i;
            }
            if (args[i].equalsIgnoreCase("-a")) {
                a_pos = i;
            }
        }

        if (a_pos < c_pos) {
            System.out.println("The -a flag must come before the -c flag");
            System.exit(0);
        }


        try {
            for (String arg : args) {

                if (current_Parameter == null) {
                    current_Parameter = arg;
                    continue;
                }

                if (current_Parameter.equals("-c")) {
                    c_flag = Integer.parseInt(arg);
                    current_Parameter = null;
                }
            }
        } catch(NumberFormatException e) {
            assert true;
        }

        // due to the implementation of this parser design pattern, it can not determine if just a '-c' was used.
        // for this edge case, we have this code to correct the behavior:
        if (c_flag == -1) {
            for (String arg : args){
                if (Objects.equals(arg, "-c")){
                    c_flag = 0;
                    break;
                }
            }
        }

        if (!a_flag) {
            for (String arg : args){
                if (Objects.equals(arg, "-a")){
                    a_flag = true;
                    break;
                }
            }
        }

    }

    /**
     * <p>Returns the CFLAG</p>s
     *
     */
    public int getCFlag(){
        return (c_flag == null ? -1 : c_flag);
    }

    /**
     * <p>Returns the AFLAG</p>s
     */
    public boolean getAFlag(){
        return this.a_flag;
    }
}