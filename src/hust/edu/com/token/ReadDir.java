package hust.edu.com.token;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadDir {
	/**
	 * @param nameFile ten file can doc noi dung
	 * @return Tra ve noi dung file
	 **/
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
	/**
	 * @param folder Thu muc chua cac file can doc noi dung, khong doc cac thu muc con
	 * @return Tra ve danh sach cac file 
	 **/
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
	/**
	 * @param urlDir Dia chi thu muc chua file
	 * @return {@link HashMap} Key : ten file  Value : Noi dung file
	 **/
	public HashMap<String,String> readFilesDirectory(String urlDir){
		HashMap<String,String> contentFile = new HashMap<String, String>();
		ArrayList<String> listFiles = listFilesForFolder(new File(urlDir));
		for(int i = 0;i<listFiles.size();i++){
			contentFile.put(listFiles.get(i),readFile(urlDir.concat("/").concat(listFiles.get(i))));
		}
		return contentFile;
	}
}
