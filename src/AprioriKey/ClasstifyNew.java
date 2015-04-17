package AprioriKey;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import hust.edu.com.token.Classify;
import hust.edu.com.token.StopToken;
import vn.hus.nlp.tokenizer.VietTokenizer;

public class ClasstifyNew {

    HashMap<String, Node> tableLookUp;
    String dirTest[] = {"Resource/Test/World",
        "Resource/Test/Laws",
        "Resource/Test/Sports",
        "Resource/Test/Education",
        "Resource/Test/Entertainment"
    };
    Classify sify;
    public int[] sumFile = new int[5];
    public int[] correctFile = new int[5];
    private double[] pointClass = new double[5];

    /*
     * Ham thuc hien qua trinh kiem thu kha nang phan loai cua he thong tren tap test
     */
    public ClasstifyNew(double minPercent) {
        AprioriKeyWord keySquence = new AprioriKeyWord(minPercent);
        tableLookUp = keySquence.makeTree();
        keySquence.showSequenceKey();
        int i;
        ArrayList<Node> TempKey = new ArrayList<Node>();
        ArrayList<Integer> setChild = new ArrayList<Integer>();
        HashMap<String, Integer> map;
        Node tKey;
        String nameF;
        int max;
        int vTemp;
        double pTemp;
        FileOutputStream temp = null;
        try {
            temp = new FileOutputStream("testResult.txt", false);
            PrintWriter pw = new PrintWriter(temp);
            Arrays.fill(correctFile, 0);
            for (i = 0; i < 5; i++) {
                sify = new Classify(dirTest[i]);
                nameF = sify.nextFile();
                sumFile[i] = sify.getNumberFile();
                while (nameF != null) {
                    Arrays.fill(pointClass, 0);
                    TempKey.clear();
                    setChild.clear();
                    map = sify.processFile();
                    for (String key : map.keySet()) {
                        if ((tKey = tableLookUp.get(key)) != null) {
                            TempKey.add(tKey);
                            counterPoint(tKey);
                        }
                    }
                    int sizeT = TempKey.size();
                    int t;
                    for (t = 0; t < sizeT; t++) {
                        tKey = TempKey.get(t);
                        setChild.add(tKey.value);
                    }
                    for (t = 0; t < sizeT; t++) {
                        vTemp = setChild.remove(t);
                        searchDepth(TempKey.get(t), setChild);
                        setChild.add(t, vTemp);
                    }
                    pTemp = pointClass[0];
                    max = 0;
                    //pw.print(sify.getNameFile()+" ");
                    pw.print(nameF + " ");
                    pw.print("| " + pointClass[0] + " ");
                    for (t = 1; t < 5; t++) {
                        pw.print("| " + pointClass[t] + " ");
                        if (pointClass[t] > pTemp) {
                            pTemp = pointClass[t];
                            max = t;
                        }
                    }
                    pw.println();
                    if (max == i && pTemp != 0) {
                        correctFile[i] += 1;
                    }
                    nameF = sify.nextFile();
                }
            }
            pw.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /*
     * Ham tinh cac keyword cua mot file va dua ra du doan phan loai cua no
     * Neu khong thuoc cac 5 tap thi tra lai -1
     */
    @SuppressWarnings("null")
    public int checkOneNew(String New) {
        ArrayList<Node> TempKey = new ArrayList<Node>();
        ArrayList<Integer> setChild = new ArrayList<Integer>();
        HashMap<String, Integer> map = null;
        StopToken token = new StopToken();
        VietTokenizer tokenizer = new VietTokenizer();
        map = token.accessStopWord(tokenizer.tokenize(New)[0]);
		//map = sify.processFile(New); thay dong nay bang ham lay ra cac tu da loc stop word cua mot van ban hay
        //mot xau
        Node tKey;
        int i, vTemp;
        int max;
        double pTemp;
        Arrays.fill(pointClass, 0);
        for (String key : map.keySet()) {
            if ((tKey = tableLookUp.get(key)) != null) {
                TempKey.add(tKey);
                counterPoint(tKey);
            }
        }
        int sizeT = TempKey.size();
        for (i = 0; i < sizeT; i++) {
            tKey = TempKey.get(i);
            setChild.add(tKey.value);
        }
        for (i = 0; i < sizeT; i++) {
            vTemp = setChild.remove(i);
            searchDepth(TempKey.get(i), setChild);
            setChild.add(i, vTemp);
        }
        pTemp = pointClass[0];
        max = 0;
        for (i = 1; i < 5; i++) {
            if (pointClass[i] > pTemp) {
                pTemp = pointClass[i];
                max = i;
            }
        }
        if (pTemp == 0) {
            return -1;
        }
        return (max + 1);
    }

    /*
     * Ham duyet theo chieu sau de lay cac keyword ghep cua file
     */
    public void searchDepth(Node input, ArrayList<Integer> tempChild) {
        if (input.numChild > 0) {
            ArrayList<Node> TempKey = new ArrayList<Node>();
            ArrayList<Integer> setChild = new ArrayList<Integer>();
            int size = tempChild.size();
            int i;
            Node tempC;
            int myValue = input.value;
            int tempV;
            for (i = 0; i < size; i++) {
                int tempValue = tempChild.get(i);
                if (myValue < tempValue) {
                    if ((tempC = input.containtChild(tempValue)) != null) {
                        TempKey.add(tempC);
                        counterPoint(tempC);
                    }
                }
            }
            size = TempKey.size();
            if (size > 1) {
                for (i = 0; i < size; i++) {
                    setChild.add(TempKey.get(i).value);
                }
                for (i = 0; i < size; i++) {
                    tempV = setChild.remove(i);
                    searchDepth(TempKey.get(i), setChild);
                    setChild.add(i, tempV);
                }
            }
        }
    }

    /*
     * Ham tinh trong so cua keyword doi voi tung lop, phu thuoc vao do dai cua keyword ghep
     */
    public void counterPoint(Node input) {
        int i;
        double anpha = 0.3;
        int size = input.depth;
        for (i = 0; i < 5; i++) {
            pointClass[i] += Math.log((size * anpha) * ((double) ((int) input.numElementPerCatalog[i])) / 50 + 1);
        }
    }

    public double[] getMark() {
        return pointClass;
    }

    public static void main(String[] args) {
        ClasstifyNew test = new ClasstifyNew(0.03);
        int i;
        int sum = 0, correct = 0;
        for (i = 0; i < 5; i++) {
            sum += test.sumFile[i];
            correct += test.correctFile[i];
            System.out.println("Ti le phan loai chinh xac cho phan lop " + i + " la " + (double) test.correctFile[i] / test.sumFile[i]);
        }
        System.out.println("Ti le phan loai chinh xac toan cuc la " + (double) correct / sum);
        System.out.println(test.checkOneNew("HLV Miura chưa loại Công Phượng, Tuấn Anh khỏi đội Olympic Việt Nam\n"
                + "Trưởng phòng các đội tuyển quốc gia của VFF Mai Đức Chung khẳng định HLV Toshiya Miura chưa chốt danh sách.\n"
                + "\"HLV Toshiya Miura chưa chốt danh sách đội tuyển Olympic Việt Nam chuẩn bị cho vòng loại U23 châu Á diễn ra vào cuối tháng ba tại Malaysia. Ngày 24/2 đội mới tập trung, nên dự kiến sau vòng bảy V-League 2015 diễn ra vào ngày 7/2 và 8/2, chiến lược gia người Nhật mới đưa ra quyết định. Do vậy, chuyện Công Phượng, Tuấn Anh hay Xuân Trường của Hoàng Anh Gia Lai không được gọi là không chính xác\", ông Chung chia sẻ với VnExpress sáng nay 30/1.\n"
                + "\n"
                + "Trước đó, có thông tin rằng VFF đã gửi giấy báo tới HAGL về việc triệu tập Đức Lương và Văn Tiến. Các cầu thủ khác như Công Phượng, Tuấn Anh, Xuân Trường…không được đả động tới.\n"
                + "\n"
                + "“Chuyện Đức Lương, Văn Tiến được nhắc tới là do họ thiếu giấy tờ, chứ không phải HAGL chỉ có hai cầu thủ này được gọi. Chúng tôi chọn lựa 50 cầu thủ trong độ tuổi có thể vào Olympic để HLV Miura đi xem. Các cầu thủ khác đủ giấy tờ, nên chúng tôi không nhắc tới, nhưng hai cầu thủ trên thiếu, nên chúng tôi đề nghị HAGL cung cấp thêm. Việc chọn ai, loại ai là do HLV Miura toàn quyền quyết định, VFF không can thiệp. Tuy nhiên, theo cá nhân tôi, Tuấn Anh, Xuân Trường, Công Phượng tốt thế sao lại không gọi?\", ông Chung nói thêm.\n"
                + "\n"
                + "Theo kế hoạch của VFF, đội tuyển U22 Việt Nam sẽ tập trung vào ngày 24/2. Thầy trò Miura sẽ tham dự vòng loại giải U23 châu Á năm 2016 tại Malaysia  diễn ra từ 23/3 tới 31/3. Ở giải đấu này, Olympic Việt Nam nằm cùng bảng với Nhật Bản, Macau và chủ nhà Malaysia. Trong quá trình chuẩn bị cho giải, thầy trò HLV Miura sẽ có ba trận giao hữu, gặp Indonesia (ngày 9/3 tại sân Mỹ Đình), Uzebekistan (ngày 14/3 tại sân Thống Nhất) và Thái Lan (22/3 tại Bangkok)."));
        double []point = test.getMark();
        for(i=0;i<point.length;i++){
            System.err.println(point[i]+"\n");
        }
    }
}
