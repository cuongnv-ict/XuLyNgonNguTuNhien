/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.edu.com.token;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cuongnv
 */
public class ReadResult {

    private String pathFile;
    private boolean Flags;
    private HashMap<Integer,Integer> infoKey;

    public ReadResult() {
        Flags = false;
        infoKey = new HashMap<>();
    }

    public int setPathFile(String path) {
        File file = new File(path);
        if (file.isFile()) {
            Flags = true;
            pathFile = path;
            return 1;
        } else {
            Flags = false;
            return -1;
        }
    }

    private void readFile() {
        try {
            BufferedReader read = new BufferedReader(new FileReader(pathFile));
            String sCurrentLine;
            int value;
            while ((sCurrentLine = read.readLine()) != null) {
                value = getNumberWordLetter(sCurrentLine);
                if(infoKey.get(value) != null){
                    infoKey.put(value, infoKey.put(value,1)+1);
                }else{
                    infoKey.put(value,1);
                }
            }
            read.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadResult.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadResult.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static int getNumberWordLetter(String letter){
        String []temp = letter.split(":")[0].split("_");
        int count =0;
        for(int i= 0;i<temp.length;i++){
            count += temp[i].split("-").length;
        }
        return count;
    }
    public HashMap<Integer,Integer> getInfoKey(){
        readFile();
        return infoKey;
    }
    public static void main (String argv[]){
        String letter = "nhận_định-ê:4:5:1:2:5";
        String[] temp = letter.split(":");
        System.out.println(ReadResult.getNumberWordLetter(letter));
    }
}
