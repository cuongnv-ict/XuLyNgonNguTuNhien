package hust.edu.com.token;

import java.util.HashMap;

public class StopToken {
	// Document http://xltiengviet.wikia.com/wiki/Danh_sach_stop_word : JVN TextPro
	private String stopWord_one[] = 
		{"nhận","rằng","cao","nhà","quá ","riêng","gì","muốn","rồi","số","thấy","hay","lên","lần","nào","qua","bằng","điều","biết","lớn","khác","vừa","nếu","thời_gian","họ","từng","đây","tháng","trước","chính","cả","việc","chưa","do","nói","ra","nên","đều","đi","tới","tôi","có_thể","cùng","vì","làm","lại","mới","ngày","đó","vẫn","mình","chỉ","thì","đang","còn","bị","mà","năm","nhất","hơn","sau","ông","rất","anh","phải","như","trên","tại","theo","khi","nhưng","vào"};
	// Document http://seo4b.com/thuat-ngu-SEO/stop-words-la-gi.html
	private String stopWord_two[] = {};
	
	/**
	 * @param contentFile : Noi dung file can xu ly
	 * @return {@link HashMap<String,Integer>} Key : tu duoc tach  Value : so lan xuat hien*/
	public HashMap<String,Integer> accessStopWord(String contentFile){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		return map;
	}
}
