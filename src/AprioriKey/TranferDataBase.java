package AprioriKey;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import hust.edu.com.token.AprioriInfomation;
import hust.edu.com.token.VietToken;


public class TranferDataBase {
	
	public String TKey[];
	private HashMap<String, AprioriInfomation> info;
	public int numFile;
	public int minSup;
	public static final int MAX_SUP = 50;
	
	public TranferDataBase(double minPercent)
	{
		numFile = 0;
		VietToken vt = new VietToken();
		numFile += vt.process("Resource/Training/World", VietToken.WORLD);
		numFile += vt.process("Resource/Training/Laws", VietToken.LAWS);
		numFile += vt.process("Resource/Training/Sports", VietToken.SPORTS);
		numFile += vt.process("Resource/Training/Entertainment", VietToken.ENTERTAINMENT);
		numFile += vt.process("Resource/Training/Education", VietToken.EDUCATION);
		/*numFile += vt.process("Resource/mTest/World", VietToken.WORLD);
		numFile += vt.process("Resource/mTest/Laws", VietToken.LAWS);
		numFile += vt.process("Resource/mTest/Sports", VietToken.SPORTS);
		numFile += vt.process("Resource/mTest/Entertainment", VietToken.ENTERTAINMENT);
		numFile += vt.process("Resource/mTest/Education", VietToken.EDUCATION);*/
		info = vt.getInfomation();
		minSup = (int)(minPercent * numFile);
		TKey = info.keySet().toArray(new String[0]);
		showParameter();
	}
	
	public void showParameter()
	{
		FileOutputStream temp;
		try {
			temp = new FileOutputStream("result2.txt",false);
			PrintWriter pw = new PrintWriter(temp);
			int count;
			int countT;
			for(String key:info.keySet())
			{
				count = 0;
				AprioriInfomation ap = info.get(key);
				pw.print(key);
				for(int i = 1;i <= 5;i ++)
				{
					countT = ap.getInfomation(i).size();
					count += countT;
					pw.print(":"+countT);
				}
				pw.println(":"+count);
			}
			/*for(String key : info.keySet()){
				AprioriInfomation ap = info.get(key);
				pw.print(key+"\t");
				for(int i = 1;i <= 5;i ++)
				{
					HashMap<String, Integer> list = ap.getInfomation(i);
					for(String key_1 : list.keySet()){
						pw.print(":"+key_1+ " ");
					}
					pw.print("\t");
				}
				pw.println();
			}*/
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getString(int pos)
	{
		if(pos <= TKey.length)
			return TKey[pos - 1];
		return null;
	}
	
	/*
	 * Ham lay do ung ho cua mot token
	 */
	public char[] getSupport(int pos)
	{
		int num = 0;
		char numElementPerCatalog[] = new char[5];
		String temp = TKey[pos - 1];
		AprioriInfomation ap = info.get(temp);
		for(int i = 1;i <= 5;i ++)
		{
			numElementPerCatalog[i - 1] = (char)ap.getInfomation(i).size();
			num += (int)numElementPerCatalog[i - 1];
		}
		if(num >= minSup && num <= MAX_SUP)
			return numElementPerCatalog;
		return null;
	}
	
	/*
	 * Ham tra ve gia false/true khi xet do ung ho cua mot chuoi token
	 */
	public char[] getSequentSupport(int pos[])
	{
		int num = 0;
		int sizeArray = pos.length;
		String TempS[] = new String[sizeArray];
		int i;
		char numElementPerCatalog[] = new char[5];
		int count;
		AprioriInfomation ap, ap1;
		HashMap<String,Integer> listT, listT1;
		for(i = 0;i < sizeArray;i ++)
			TempS[i] = TKey[pos[i] - 1];
		ap = info.get(TempS[0]);
		for(i = 1;i <= 5;i ++)
		{
			listT = ap.getInfomation(i);
			count = 0;
			for(String key_1:listT.keySet())
			{
				int j = 1;
				while(j < sizeArray)
				{
					ap1 = info.get(TempS[j]);
					listT1 = ap1.getInfomation(i);
					if(listT1.containsKey(key_1))
						j += 1;
					else
						break;
				}
				if(j == sizeArray)
					count += 1;
			}
			num += count;
			numElementPerCatalog[i-1] = (char)count;
		}
		if(num >= minSup)
			return numElementPerCatalog;
		return null;
	}
}