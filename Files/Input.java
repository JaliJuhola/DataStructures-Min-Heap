/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Input {
    public static String INPUTFILE = "input.txt";
    public static String[][] getInput() {
        String[][] commands = new String[5][];
        try {
            BufferedReader br = new BufferedReader(new FileReader(INPUTFILE));
            String line = br.readLine();
            int i = 0;
            while (line != null) {
                commands = resizeArray(commands);
                String[] values = line.split(" "); // (" ") or (",") ?? 
                commands[i] = values;
                line = br.readLine();
                i++;
            }
            Log.writeLog("Inputs " + i + " values succesfully readed");
        } catch (IOException e) {
            Log.writeLog("File (" + INPUTFILE+ ") not found." + "File: Input.java Method: getInput");
        }
        return commands;
    }

    private static String[][] resizeArray(String[][] array) {
        if (array[array.length - 2] != null) { // Checking if array is full
            String[][] resizedArray = new String[array.length * 2][]; // Array size doubled
            for (int i = 0; i < array.length; i++) { // Copying already existing values to new array
                resizedArray[i] = array[i];
            }
            return resizedArray;
        }
        return array;

    }
    public static void setFile(String fileName)
    {
        Log.writeLog("new input file set: " +  INPUTFILE);
        INPUTFILE = fileName;
    }

}
