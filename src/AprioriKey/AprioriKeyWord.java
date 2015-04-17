package AprioriKey;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class AprioriKeyWord {
	
	private Node root = new Node(0,null,-1);
	private ArrayList<Node> candidate = new ArrayList<Node>();
	private TranferDataBase dict;
	
	public AprioriKeyWord(double minPercent)
	{
		dict = new TranferDataBase(minPercent);
		int size = dict.TKey.length;
		char result[];
		for(int i = 1;i <= size;i ++)
		{
			if((result = dict.getSupport(i)) != null)
				root.addChild(i,result);
		}
		candidate.add(root);
	}
	
	public HashMap<String, Node> makeTree()
	{
		boolean flags = true;
		ArrayList<Node> newCandidate = new ArrayList<Node>();
		ArrayList<Integer> sPath = new ArrayList<Integer>();
		int sizeCandidate = 0;
		int path[] = null;
		int sIPath[] = null;
		int shortCheck[] = new int[2];
		char result[];
		Node nowNode;
		int i,j;
		while(flags)
		{
			sizeCandidate = candidate.size();
			if(sizeCandidate > 0)
			{
				path = new int[candidate.get(0).depth + 2];
				sIPath = new int[path.length - 1];
			}
			for(i = 0;i < sizeCandidate;i ++)
			{
				nowNode = candidate.get(i);
				int numChild = nowNode.numChild - 1;
				for(j = 0;j < numChild;j ++)
				{
					Node checkNode = nowNode.getChild(j);
					int depth = checkNode.depth;
					for(int i1 = j + 1;i1 < nowNode.numChild;i1 ++)
					{
						if(depth == 1)
						{
							path[0] = checkNode.value;
							path[1] = nowNode.getChild(i1).value;
							if((result = dict.getSequentSupport(path)) != null)
								checkNode.addChild(path[1],result);
						}
						else
						{
							shortCheck[0] = checkNode.value;
							shortCheck[1] = nowNode.getChild(i1).value;
							if(checkPath(shortCheck))
							{
								checkNode.returnPath(path);
								path[depth] = shortCheck[1];
								sPath.clear();
								int j1;
								for(j1 = 0;j1 < path.length;j1 ++)
									sPath.add(path[j1]);
								for(j1 = 0;j1 < depth - 1;j1 ++)
								{
									int element = sPath.remove(j1);
									ArrayToInt(sPath,sIPath);
									if(checkPath(sIPath))
										sPath.add(j1, element);
									else
										break;
								}
								if(j1 == depth - 1)
								{
									if((result = dict.getSequentSupport(path)) != null)
										checkNode.addChild(shortCheck[1],result);
								}
							}
						}
					}
					if(checkNode.numChild > 1)
						newCandidate.add(checkNode);
				}
			}
			candidate.clear();
			if(newCandidate.size() == 0)
				flags = false;
			else
			{
				candidate.addAll(newCandidate);
				newCandidate.clear();
			}
		}
		return makeHashHeadTree();
	}
	
	public boolean checkPath(int path[])
	{
		Node temp = root;
		int size = path.length;
		int i;
		for(i = 0;i < size;i ++)
		{
			if((temp = temp.containtChild(path[i])) == null)
				return false;
		}
		return true;
	}
	
	public static boolean checkPath(int path[],Node head)
	{
		Node temp = head;
		int size = path.length;
		int i;
		for(i = 1;i < size;i ++)
		{
			if((temp = temp.containtChild(path[i])) == null)
				return false;
		}
		return true;
	}
	
	public void ArrayToInt(ArrayList<Integer> input,int[] spath)
	{
		int size = spath.length;
		for(int i = 0;i < size;i ++)
			spath[i] = input.get(i);
	}
	
	public HashMap<String, Node> makeHashHeadTree()
	{
		HashMap<String, Node> head = new HashMap<String, Node>();
		int size = root.numChild;
		Node temp;
		String result;
		for(int i = 0;i < size;i ++)
		{
			temp = root.getChild(i);
			result = dict.getString(temp.value);
			if(result != null)
				head.put(result, temp);
		}
		return head;
	}
	
	public void showSequenceKey()
	{
		FileOutputStream temp = null;
		try {
			temp = new FileOutputStream("result.txt",false);
			PrintWriter pw = new PrintWriter(temp);
			ArrayList<Node> newCandidate = new ArrayList<Node>();
			candidate.add(root);
			boolean flags = true;
			int i;
			int path[] = null;
			int size;
			while(flags)
			{
				size = candidate.size();
				if(size > 0)
					path = new int[candidate.get(0).depth + 1];
				for(i = 0;i < size;i ++)
				{
					Node temp1 = candidate.get(i);
					int numChild = temp1.numChild;
					for(int j = 0;j < numChild;j ++)
					{
						Node child = temp1.getChild(j);
						child.returnPath(path);
						int i1;
						for(i1 = 0;i1 < path.length - 1;i1 ++)
						{
							pw.print(dict.getString(path[i1])+"-");
						}
						pw.print(dict.getString(path[i1]));
						for(i1 = 0;i1 < 5;i1 ++)
							pw.print(":"+(int)child.numElementPerCatalog[i1]);
						pw.println();
						if(child.numChild > 0)
							newCandidate.add(child);
					}
					
				}
				candidate.clear();
				if(newCandidate.size() == 0)
					flags = false;
				else
				{
					candidate.addAll(newCandidate);
					newCandidate.clear();
				}
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
