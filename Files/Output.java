package Files;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Output {

    private static String OUTPUTFILE = "output.txt"; // "output.txt is default"

    public static void setFile(String name) // sets new input file
    {
        OUTPUTFILE = name;
        Log.writeLog("Output file set: " + OUTPUTFILE);
    }

    private static void writeUniqueOutput(String line) throws IOException {
        PrintWriter bw = new PrintWriter(new FileWriter(OUTPUTFILE, true));
        bw.write(line + "\r\n");
        bw.close();

    }

    public static void priorityQueueEmpty() {
        try {
            writeUniqueOutput("Jono on tyhjä.");
        } catch (IOException e) {
            Log.writeLog("File (" + OUTPUTFILE + ") not found. Location: Output.java Method: WriteAddOutput ->PriorityQueueEmpty");
        }
    }

    public static void smallestPair(int key, Object value) {
        try {
            writeUniqueOutput("Pienin alkio on (" + key + "," + value.toString() + ").");
        } catch (IOException e) {
            Log.writeLog("File (" + OUTPUTFILE + ") not found. Location: Output.java Method: WriteAddOutput ->smallestpair");
        }
    }

    {

    }

    public static void invalidInput() {
        try {
            writeUniqueOutput("Virheellinen syöte.");
        } catch (IOException e) {
            Log.writeLog("File (" + OUTPUTFILE + ") not found. Location: Output.java Method: WriteAddOutput ->invalidoutput");
        }

    }

    public static void writeOutput(String s) {
        try {
            writeUniqueOutput(s);
        } catch (IOException e) {
            Log.writeLog("File (" + OUTPUTFILE + ") not found. Location: Output.java Method: WriteAddOutput ->WriteUniqueOutput");
        }
    }

    public static void writeAddOutput(int key, String value) {
        try {
            writeUniqueOutput("(" + key + "," + value + ")" + " lis.");
        } catch (IOException e) {
            Log.writeLog("File (" + OUTPUTFILE + ") not found. Location: Output.java Method: WriteAddOutput ->WriteUniqueOutput");
        }
    }

    public static void WriteDeleteOutput(int key, String value) {
        try {
            writeUniqueOutput("(" + key + "," + value + ") poistettu.");
        } catch (IOException e) {
            Log.writeLog("File (" + OUTPUTFILE + ") not found. Location: Output.java Method: WriteDeleteOutput->WriteUniqueOutput");
        }
    }

    public static void writeClose() {
        try {
            writeUniqueOutput("Ohjelma lopetettu.");

        } catch (IOException e) {
            Log.writeLog("File (" + OUTPUTFILE + ") not found. Location: Output.java Method: WriteClose->WriteUniqueOutput");
        }
        Log.writeLog("Program ended whit code 0");
    }

    public static void writeSize(int size) {
        try {
            writeUniqueOutput("Koko: " + size);

        } catch (IOException e) {
            Log.writeLog("File (" + OUTPUTFILE + ") not found. Location: Output.java Method: WriteSize->WriteUniqueOutput");
        }
    }

    public static void outputClear() {
        try {
            PrintWriter bw = new PrintWriter(new FileWriter(OUTPUTFILE, false));
            bw.write("");
            bw.close();

        } catch (IOException e) {
            Log.writeLog("File (" + OUTPUTFILE + ") not found. Location: Output.java Method: WriteSize->WriteUniqueOutput");
        }
    }

}
