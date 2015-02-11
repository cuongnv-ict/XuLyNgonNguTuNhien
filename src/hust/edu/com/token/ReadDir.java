package hust.edu.com.token;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadDir {
	public ReadDir(){
		
	}
	public String readFile(String nameFile){
		String content = "";
		String sCurrentLine;
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(nameFile));
			while ((sCurrentLine = buffer.readLine()) != null) {
				content = content.concat(sCurrentLine);
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
		return content;
	}
	public ArrayList<String> listFilesForFolder(File folder) {
		ArrayList<String> listFiles = new ArrayList<>();
		if(!folder.exists()){
			System.err.println("Folder khong ton tai");
			return listFiles;
		}
		if(!folder.isDirectory()){
			System.err.println("Day la File khong phai Folder");
			return listFiles;
		}
	    for (File fileEntry : folder.listFiles()) {
	    	if(fileEntry.isFile()){
	    		listFiles.add(fileEntry.getName());
	    	}  	
	    }
	    return listFiles;
	}
	public ArrayList<String> readFilesDirectory(String urlDir){
		ArrayList<String> contentFile = new ArrayList<String>();
		ArrayList<String> listFiles = listFilesForFolder(new File(urlDir));
		for(int i = 0;i<listFiles.size();i++){
			contentFile.add(readFile(urlDir.concat("/").concat(listFiles.get(i))));			
		}
		return contentFile;
	}
}
