/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.edu.com.voc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hung
 */
public class MyFile {

    /**
     *
     * @param path
     * @return nội dung của file
     */
    public String readFile(File file) {
        String content = "";
        String currentLine;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            while ((currentLine = buffer.readLine()) != null) {
                content = content.concat(currentLine);
            }
            buffer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyFile.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(MyFile.class.getName()).log(Level.SEVERE, null, ex);

        }
        return content;
    }

}
