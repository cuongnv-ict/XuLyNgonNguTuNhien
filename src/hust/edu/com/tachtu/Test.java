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
		 String []str =
		 tokenizer.tokenize("Trong_thế                          chiến thứ nhất, người Pháp vì muốn bảo vệ Paris tráng lệ của mình nên đã tạo ra một thành phố giả ở phía bắc của Paris thật. Họ tin rằng nếu máy bay Đức có muốn ném bom vào Paris thì họ sẽ bị lừa và ném bom nhầm vào thành phố giả này thay vì ném bom vào Kinh đô Ánh sáng của người Pháp");
		 System.out.println(str[0]);
	}
}
