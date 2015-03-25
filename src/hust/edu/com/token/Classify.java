package hust.edu.com.token;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import vn.hus.nlp.tokenizer.VietTokenizer;

public class Classify {

	private int num_file;
	private String urlDir;
	private String nameFile;
	private ReadDir dir;
	private StopToken stopword;
	private VietTokenizer tokenizer;
	private ArrayList<String> listFile;
	private int count;

	public Classify(String urlDir) {
		dir = new ReadDir();
		this.urlDir = urlDir;
		stopword = new StopToken();
		listFile = dir.listFilesForFolder(new File(urlDir));
		num_file = listFile.size();
		count = 0;
		tokenizer = new VietTokenizer();
		nameFile = null;
	}
	public int getNumberFile() {
		return num_file;
	}

	public String nextFile() {
		if (count < num_file) {
			nameFile = listFile.get(count);
			count++;
			return nameFile;
		}
		return null;
	}
	public HashMap<String, Integer> processFile(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map = stopword.accessStopWord(tokenizer.tokenize(dir.readFile(urlDir +"/"+nameFile))[0]);
		return map;
	}

	public HashMap<String, Integer> processFile(int count) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if (count < num_file) {
			map = stopword.accessStopWord(tokenizer.tokenize(dir
					.readFile(urlDir + "/" + listFile.get(count)))[0]);
		}
		return map;
	}

	public String getUrlDir() {
		return urlDir;
	}

	public int getCounting() {
		return count;
	}

	public void setUrlDir(String url) {
		this.urlDir = url;
		listFile = dir.listFilesForFolder(new File(url));
		num_file = listFile.size();
		nameFile = null;
		count = 0;
	}
}
