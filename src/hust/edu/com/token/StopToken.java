package hust.edu.com.token;

import java.util.HashMap;

public class StopToken {
	// Document http://xltiengviet.wikia.com/wiki/Danh_sach_stop_word :
	// JVNTextPro
	private String stopWord_one[] = { "nhận", "rằng", " cao", "nhà", "quá ",
			"riêng", "gì", "muốn", "rồi", "số", "thấy", "hay", "lên", "lần",
			"nào", "qua", "bằng", "điều", "biết", "lớn", "khác", "vừa", "nếu",
			"thời_gian", "họ", "từng", "đây", "tháng", "trước", "chính", "cả",
			"việc", "chưa", "do", "nói", "ra", "nên", "đều", "đi", "tới",
			"tôi", "có_thể", "cùng", "vì", "làm", "lại", "mới", "ngày", "đó",
			"vẫn", "mình", "chỉ", "thì", "đang", "còn", "bị", "mà", "năm",
			"nhất", "hơn", "sau", "ông", "rất", "anh", "phải", "như", "trên",
			"tại", "theo", "khi", "nhưng,vào", ",", ".", "?", "{", "}", "(",
			")", ";", ":", "[", "]", "!","'" };
	// Document http://seo4b.com/thuat-ngu-SEO/stop-words-la-gi.html
	private String stopWord_two[] = { "a_ha", "à_ơi", "á", "à", "á_à", "ạ",
			"ai", "ái", "ái_chà", "alô", "ào", "ắt", "ắt_hẳn", "ắt_là", "ấy",
			"bao_giờ", "bao_lâu", "bao_nhiêu", "bất_chợt", "bất_cứ",
			"bất_giác", "bất_kể", "bất_kì", "bất_kỳ", "bất_luận", "bất_quá",
			"bất_thình_lình", "bấy_giờ", "bấy_lâu", "bấy_lâu_nay", "bấy_nhiêu",
			"biết_bao", "biết_bao_nhiêu", "biết_chừng_nào", "biết_đâu",
			"biết_đâu_chừng", "biết_đâu_đấy", "bỗng", "bỗng_chốc", "bỗng_dưng",
			"bỗng_nhiên", "bỏ_mẹ", "bớ", "bởi", "bởi_thế", "bởi_vậy", "bởi_vì",
			"cả_thể", "cha", "cha_chả", "chao_ôi", "chắc", "chắc_hẳn",
			"chăn_chắn", "chẳng_lẽ", "chẳng_những", "chẳng_phải", "chết_tiệt",
			"chết_thật", "chính_là", "chỉ_do", "chỉ_là", "chỉ_tại", "chỉ_vì",
			"chiếc", "cho_đến", "cho_đến_khi", "cho_nên", "cho_tới",
			"cho_tới_khi", "chốc_chốc", "chợt", "có_chăng_là", "có_vẻ",
			"coi_bộ", "còn", "cơ_hồ", "cơ_mà", "của", "cùng", "cùng_cực",
			"cùng_nhau", "cùng_với", "cũng", "cũng_như", "cũng_vậy",
			"cũng_vậy_thôi", "cứ_việc", "cực_kì", "cực_kỳ", "cuộc", "dạ",
			"dần_dà", "dần_dần", "dẫu_sao", "dễ_sợ", "dễ_thường", "do",
			"do_vì", "do_đó", "do_vậy", "dù_cho", "dù_rằng", "đại_để",
			"đại_loại", "đáng_lẽ", "đáng_lí", "đáng_lý", "nếu_như", "ngay_cả",
			"ngay_lập_tức", "ngay_lúc", "ngay_khi", "ngay_từ", "ngay_tức_khắc",
			"ngày_càng", "nhân_dịp", "nhân_tiện", "nhất_định", "nhất_loạt",
			"nhất_luật", "nhất_mực", "nhất_nhất", "nhất_quyết", "nhất_tề",
			"nhất_thiết", "nhé", "nhỉ", "nhiệt_liệt", "như", "như_chơi",
			"như_không", "như_thể", "như_vậy", "nhưng", "nhưng_mà", "những",
			"những_ai", "ô_hay", "ô_kìa", "ôi_chao", "ôi_thôi", "ối_dào",
			"ối_giời", "ối_giời_ơi", "ơ", "ơ_hay", "ơ_kìa", "ờ", "ớ", "ơi",
			"quả_đúng", "quả_là", "quả_thật", "quả_vậy", "quá", "quá_chừng",
			"quá_độ", "quá_lắm", "quá_thể", "quá_trời", "ra_phết", "ra_trò",
			"rằng", "rằng_là", "rất", "rất_chi_là", "rồi", "rốt_cục",
			"rốt_cuộc", "sau_cùng", "sau_đó", "sắp", "sẽ", "số_là", "sốt_sột",
			"sở_dĩ", "suýt", "sự", "tà_tà", "tại", "tại_vì", "tất_cả",
			"tha_hồ", "thà", "thà_là", "thà_rằng", "than_ôi", "thành_ra",
			"thành_thử", "thảo_nào", "thậm_chí", "thật_vậy", "thật_ra", "thế",
			"thế_à", "thế_là", "thế_mà", "thế_nào", "thế_nên", "thế_thì",
			"thì", "thoạt_nhiên", "thôi", "thốt_nhiên", "thực_ra", "thực_vậy",
			"thương_ôi", "tiện_thể", "tiếp_đó", "tiếp_theo", "tỏ_ra", "tỏ_vẻ",
			"trời_đất_ơi", "trước", "trước_đây", "trước_đó", "trước_kia",
			"trước_nay", "trước_tiên", "trừ_phi", "tuy", "tuy_nhiên",
			"tuy_rằng", "tuy_thế", "tuy_vậy", "tuyệt_nhiên", "từng", "tức_thì",
			"tức_tốc", "ủa", "úi", "úi_chà", "úi_dào", "ư", "ứ_ừ", "ử", "ừ",
			"và", "vả_chăng", "vả_lại", "vẫn", "vâng", "vậy", "vậy_là",
			"vậy_thì", "vì", "vì_thế", "vì_vậy", "với", "với_lại" };
	private HashMap<String, Integer> map;

	public StopToken() {
		map = new HashMap<String, Integer>();
		createHashMap();
	}

	private void createHashMap() {
		for (int i = 0; i < stopWord_one.length; i++) {
			map.put(stopWord_one[i], i);
		}
		for (int i = 0; i < stopWord_two.length; i++) {
			map.put(stopWord_two[i], i);
		}
	}

	/**
	 * @param contentFile
	 *            : Noi dung file can xu ly
	 * @return {@link HashMap<String,Integer>} Key : tu duoc tach Value : so lan
	 *         xuat hien
	 */
	public HashMap<String, Integer> accessStopWord(String contentFile) {
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		contentFile = contentFile.toLowerCase().trim();
		String[] letter = contentFile.split(" ");
		for (int i = 0; i < letter.length; i++) {
			if (letter[i].equalsIgnoreCase(" ")
					|| letter[i].equalsIgnoreCase("")) {
				continue;
			}
			if (map.get(letter[i]) == null) {
				if (hmap.get(letter[i]) == null) {
					hmap.put(letter[i], 1);
				} else {
					hmap.put(letter[i],hmap.get(letter[i])+1);
				}
			}
		}
		return hmap;
	}
}
