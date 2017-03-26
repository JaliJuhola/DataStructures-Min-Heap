
import Files.Log;
import Files.Output;
import PriorityQueue.*;

public class App {

    private String[][] commands;
    private PriorityQueue pq;

    public App() {
        this.pq = new PriorityQueue();
    }

    public void excecute(String[][] commands) {
        this.commands = commands;
        Log.writeLog("App executed succesfully");
        int i = 0;
        while (this.commands[i] != null) {
            switch (this.commands[i][0]) {
                case "q": // quits program
                    Log.writeLog("Program succesfully shut down");
                    Output.writeClose();
                    System.exit(0);

                case "s": // priorityqueues size
                    if (pq.size() > 0) {
                        Output.writeSize(pq.size());
                    } else {
                        Output.priorityQueueEmpty();
                    }
                    Log.writeLog("command size executed whit return value: " + pq.size());
                    break;

                case "i": // inserts to priorityqueue
                    commandInsert(i);
                    break;
                case "r": // removed smallest item from heap
                    Pair deleted = this.pq.removeMinElement();
                    if (deleted == null) {
                        Output.priorityQueueEmpty();
                        break;
                    }
                    Output.WriteDeleteOutput(deleted.key(), deleted.data().toString());
                    break;
                case "p": // prints heap preordered
				    if(pq.size() < 1) {
						Output.priorityQueueEmpty();
						break;
					} 
                    Output.writeOutput(this.pq.preOrder(1));
                    pq.PrintTreeToLog(); // prints tree to log as tree form.					
                    break;
                case "pl": // Prints priorityqueue as tree
                    this.pq.PrintTreeToLog();
                    break;
                case "m": // gets smallest key from priorityqueue
                    Pair smallest = this.pq.extractMin();
                    if (smallest == null) {
                       Output.priorityQueueEmpty();
                       break;
                    }
                       Output.smallestPair(smallest.key(), smallest.data());
                    break;
                default:
                    Output.invalidInput();
            }

            i++;

        }
        this.pq.PrintTreeToLog();

    }

    public void commandInsert(int idx) {
        int key = castInteger(commands[idx][1]); // checks if genuine integer
        String value = commands[idx][2].replaceAll("^\\s+", "");
        int status = 0;
        if (this.commands[idx].length == 3) {
            if (key == Integer.MIN_VALUE) {
                status = 1;
            } else {
                status = pq.insert(key, value);
            }
            switch (status) {
                case 0:
                    Output.writeAddOutput(key, value);
                    break;
                case 1:
                    Output.invalidInput();
                    break;
                case 2:
                    Output.writeOutput("Avain " + value + " on jo jonossa.");
                    break;
            }
        } else {
            Output.invalidInput();
        }
    }

    public int castInteger(String s) {
        try {
            return Integer.parseInt(s.replaceAll("^\\s+", ""));
        } catch (Exception e) {
            Log.writeLog("Problem parsing integer Exception message: " + e.getMessage());
            return Integer.MIN_VALUE;
        }
    }

}
