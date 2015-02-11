package hust.edu.com.tachtu;

import hust.edu.com.token.ReadDir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import jvntextpro.JVnTextPro;
import vn.hus.nlp.tokenizer.VietTokenizer;

public class Test {
	public static void main(String[] args) {
		VietTokenizer tokenizer = new VietTokenizer();
		// String []str =
		// tokenizer.tokenize("Trong thế chiến thứ nhất, người Pháp vì muốn bảo vệ Paris tráng lệ của mình nên đã tạo ra một thành phố giả ở phía bắc của Paris thật. Họ tin rằng nếu máy bay Đức có muốn ném bom vào Paris thì họ sẽ bị lừa và ném bom nhầm vào thành phố giả này thay vì ném bom vào Kinh đô Ánh sáng của người Pháp");
		// JVnTextPro textPro = new JVnTextPro();
		// textPro.initSenSegmenter("JVnTextPro-v.2.0/models/jvnsensegmenter");
		// textPro.initSenTokenization();
		// textPro.initSegmenter("JVnTextPro-v.2.0/models/jvnsegmenter");
		// textPro.initPosTagger("JVnTextPro-v.2.0/models/jvnpostag/maxent");
		// System.err.println(textPro.process("Trong thế chiến thứ nhất, người Pháp vì muốn bảo vệ Paris tráng lệ của mình nên đã tạo ra một thành phố giả ở phía bắc của Paris thật. Họ tin rằng nếu máy bay Đức có muốn ném bom vào Paris thì họ sẽ bị lừa và ném bom nhầm vào thành phố giả này thay vì ném bom vào Kinh đô Ánh sáng của người Pháp"));

		File folder = new File("Resource/Training/World");
		if (folder.exists()) {
			ReadDir dir = new ReadDir();
			System.out.println(dir
					.readFilesDirectory("Resource/Training/World").get(0));
			String[] str = tokenizer.tokenize(dir.readFilesDirectory(
					"Resource/Training/World").get(0));
			for (int i = 0; i < str.length; i++) {
				System.err.println(str[i]);
			}
			tokenizer.segment(arg0)
		} else {
			System.out.println("ok");
		}
	}
}
