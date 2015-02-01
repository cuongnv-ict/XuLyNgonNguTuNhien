package hust.edu.com.tachtu;

import jvntextpro.JVnTextPro;
import vn.hus.nlp.tokenizer.VietTokenizer;

public class Test {
	public static void main(String[] args){
		VietTokenizer tokenizer = new VietTokenizer();
		String []str = tokenizer.tokenize("Ha Noi      pho, gio thu");
		JVnTextPro textPro = new JVnTextPro();
		textPro.initSenSegmenter("JVnTextPro-v.2.0/models/jvnsensegmenter");
		textPro.initSenTokenization();
		textPro.initSegmenter("JVnTextPro-v.2.0/models/jvnsegmenter");
		textPro.initPosTagger("JVnTextPro-v.2.0/models/jvnpostag/maxent");
		System.err.println(textPro.process("chim cu"));
		for(int i =0;i<str.length;i++){
			System.err.println(str[i]);
		}
	}
}
