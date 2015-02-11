package hust.edu.com.token;

import java.util.HashMap;

public class AprioriInfomation {
	private String words;
	private HashMap<String, Integer> listWorld;
	private HashMap<String, Integer> listLaws;
	private HashMap<String, Integer> listSports;
	private HashMap<String, Integer> listEducation;
	private HashMap<String, Integer> listEntertainment;
	public final int WORLD = 1;
	public final int LAWS = 2;
	public final int SPORTS = 3;
	public final int EDUCATION = 4;
	public final int ENTERTAINMENT = 5;

	public AprioriInfomation(String word) {
		this.words = word;
		listWorld = new HashMap<String, Integer>();
		listLaws = new HashMap<String, Integer>();
		listSports = new HashMap<String, Integer>();
		listEducation = new HashMap<String, Integer>();
		listEntertainment = new HashMap<String, Integer>();
	}

	public void addWorld(String nameFile, int number) {
		listWorld.put(nameFile, number);
	}

	public void addLaws(String nameFile, int number) {
		listLaws.put(nameFile, number);
	}

	public void addSports(String nameFile, int number) {
		listSports.put(nameFile, number);
	}

	public void addEducation(String nameFile, int number) {
		listEducation.put(nameFile, number);
	}

	public void addEntertainment(String nameFile, int number) {
		listEntertainment.put(nameFile, number);
	}
	public HashMap<String, Integer> getInfomation(int type){
		switch (type) {
		case WORLD:
			return listWorld;
		case LAWS:
			return listLaws;
		case SPORTS:
			return listSports;
		case EDUCATION:	
			return listEducation;
		case ENTERTAINMENT:
			return listEntertainment;
		default:
			return null;
		}	
	}
	public String getWords(){
		return words;
	}
}
