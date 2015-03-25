package hust.edu.com.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import hust.edu.com.token.AprioriInfomation;
import hust.edu.com.token.Classify;
import hust.edu.com.token.VietToken;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
//		VietToken vt = new VietToken();
//		int so_file = vt.process("Resource/Training/World", VietToken.WORLD);
//		vt.process("Resource/Training/Laws", VietToken.LAWS);
//		vt.process("Resource/Training/Sports", VietToken.SPORTS);
//		vt.process("Resource/Training/Entertainment", VietToken.ENTERTAINMENT);
//		vt.process("Resource/Training/Education", VietToken.EDUCATION);
//		HashMap<String, AprioriInfomation> info = vt.getInfomation();
		/*for(String key : info.keySet()){
			AprioriInfomation ap = info.get(key);
			HashMap<String, Integer> list = ap.getInfomation(AprioriInfomation.WORLD);
			System.out.println("So lan suat hien cua \""+key +"\": World");
			for(String key_1 : list.keySet()){
				System.out.println("Ten file : "+ key_1+ "-"+list.get(key_1));
			}
			list = ap.getInfomation(AprioriInfomation.LAWS);
			System.out.println("So lan suat hien cua \""+key +"\": Laws");
			for(String key_1 : list.keySet()){
				System.out.println("Ten file : "+ key_1+ "-"+list.get(key_1));
			}
			System.out.println("************************************************");
		}*/
//		System.out.println("\nSo file WORLD tham gia huan luyen la : "+ so_file);
//		System.out.println("Number of token is "+info.size());
//		FileOutputStream temp = new FileOutputStream("result.txt",false);
//		PrintWriter pw = new PrintWriter(temp);
//		int count;
//		for(String key:info.keySet())
//		{
//			count = 0;
//			AprioriInfomation ap = info.get(key);
//			for(int i = 1;i <= 5;i ++)
//				count += ap.getInfomation(i).size();
//			pw.println(key+":"+count);
//		}
//		pw.close();
		Classify sify =  new Classify("Resource/Training/World");
		String name = sify.nextFile();
		System.out.println("So file trong thu muc : "+sify.getNumberFile());
		while(name != null){
			HashMap<String, Integer> map = sify.processFile();
			System.out.println("Ten file doc : "+ name);		
			for(String key:map.keySet()){
				System.out.println(key+ "-" + map.get(key));
			}
			name = sify.nextFile();
		}
		
		sify.setUrlDir("Resource/Training/Laws");
		name = sify.nextFile();
		System.out.println("So file trong thu muc : "+sify.getNumberFile());
		while(name != null){
			HashMap<String, Integer> map = sify.processFile();
			System.out.println("Ten file doc : "+ name);		
			for(String key:map.keySet()){
				System.out.println(key+ "-" + map.get(key));
			}
			name = sify.nextFile();
		}
		
		HashMap<String, Integer> map = sify.processFile(5);
		System.out.println("Ten file doc : "+ name);		
		for(String key:map.keySet()){
			System.out.println(key+ "-" + map.get(key));
		}
		name = sify.nextFile();
		
	}
}