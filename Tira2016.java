
import Files.*;

public class Tira2016 {

    public static void main(String[] args) {
        System.out.println("first commandline argument input file and second one is output file");
        System.out.println("i 1 name -> adds item to priorityQueue");
        System.out.println("s        -> returns size of priorityqueue");
        System.out.println("p        -> prints priorityqueue as preorder");
        System.out.println("r        -> removes smallest key from priorityqueue");
        System.out.println("r        -> removes smallest key from priorityqueue");
        System.out.println("m        -> retrieves smalles key from priorityqueue");
        System.out.println("pl       -> prints tree to log ");
        System.out.println("");
        System.out.println("Unit test are avaible for testing different aspects of priorityqueue");
        Log.logClear(); // Log cleared every run.

        if (args.length == 2) {
            Output.setFile(args[1]);
            Input.setFile(args[0]);
        }
        Output.outputClear(); // clears output file 
        Log.writeLog("Program started");
        String[][] commands = Input.getInput();
        App program = new App();
        program.excecute(commands); // excecutes program whit INPUTFILE commands
        Log.writeLog("Program ended whitout exit(0) command.");
    }

}
