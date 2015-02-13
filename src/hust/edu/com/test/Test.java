package hust.edu.com.test;

import java.util.HashMap;

import hust.edu.com.token.AprioriInfomation;
import hust.edu.com.token.VietToken;

public class Test {
	public static void main(String[] args) {
		VietToken vt = new VietToken();
		vt.process("Resource/Training/World", VietToken.WORLD);
		vt.process("Resource/Training/Laws", VietToken.LAWS);
		HashMap<String, AprioriInfomation> info = vt.getInfomation();
		for(String key : info.keySet()){
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
		}
	}
}
