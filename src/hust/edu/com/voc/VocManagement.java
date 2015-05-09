/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hust.edu.com.voc;

import hust.edu.com.token.StopToken;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import vn.hus.nlp.tokenizer.VietTokenizer;

/**
 *
 * @author Hung
 */
public class VocManagement {

    private MyFile myFile;

    public VocManagement() {
        myFile = new MyFile();
        
    }

    /**
     *
     * @param directory
     * @return xây dựng bộ từ vựng cho từng chủ đề theo thư mục
     */
    public HashMap<String, Integer> build(File directory) {
        HashMap<String, Integer> vocOfDir = new HashMap<String, Integer>();
        StopToken stopToken=new StopToken();
        VietTokenizer vietTokenizer=new VietTokenizer();
        File[] listFiles = directory.listFiles();
        HashMap<String, Integer> vocOfFile = null;
        for (int i = 0; i < listFiles.length; i++) {
            System.out.println(listFiles[i].getName());
            vocOfFile =stopToken.accessStopWord(vietTokenizer.tokenize(myFile.readFile(listFiles[i]))[0]);
            Set keySet = vocOfFile.keySet();
            Iterator iterator = keySet.iterator();
            while(iterator.hasNext()){
                String key=iterator.next().toString();
                if(vocOfDir.containsKey(key)) vocOfDir.put(key,vocOfDir.get(key)+vocOfFile.get(key));
                else vocOfDir.put(key,vocOfFile.get(key));
            }
            //vocOfFile.clear();
        }

        return vocOfDir;
    }
  
}
