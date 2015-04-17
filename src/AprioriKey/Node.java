package AprioriKey;

import java.util.ArrayList;

/*
 * - Buoc 1: Thuc hien viec anh xa tu xau => so
 * - Buoc 2: Thuc hien viec tao cay apriori
 * 		+ Khoi tao: Duyen danh sach chi muc nguoc, giu lai va tao node goc cho cac tu >= supmin
 * 		+ Buoc lap: Tai tang n chon 2 node con co cung cha va co thu tu tang dan, kiem tra xem co ton tai duong
 * di theo thu tu tang dan cua tat ca cac duong co chua hai node do ko, neu ko thi loai neu co thi kiem tra trong
 * danh sach chi muc nguoc xem co du tap n + 1 node do >= supmin neu co thi them vao vao cay.
 * 		+ Buoc lap ket thuc khi khong the them duoc bat ky node nao vao cay nua.
 * - Cach thuc thu thap lai tap key duyet tat ca cac cay theo tung muc, do sau cua muc tung ung voi
 * do dai cua khoa.
 */

public class Node {
	int value;
	int numChild;
	Node parent;
	ArrayList<Node> child = new ArrayList<Node>();
	int depth;
	char numElementPerCatalog[];
	
	public Node(int value,Node parent,int depth)
	{
		this.parent = parent;
		this.value = value;
		numChild = 0;
		this.depth = depth + 1;
		numElementPerCatalog = null;
	}
	
	public void addChild(int value,char weight[])
	{
		Node temp = new Node(value,this,this.depth);
		temp.numElementPerCatalog = weight;
		child.add(temp);
		numChild += 1;
	}
	
	public void returnPath(int path[])
	{
		int i = 0;
		Node temp = this;
		for(i = depth;i > 0;i --)
		{
			path[i - 1] = temp.value;
			temp = temp.parent;
		}
	}
	
	public Node containtChild(int value)
	{
		int i;
		Node temp;
		for(i = 0;i < numChild;i ++)
		{
			temp = child.get(i);
			if(temp.value == value)
				return temp;
		}
		return null;
	}
	
	
	public Node getChild(int pos)
	{
		if(pos < numChild)
			return child.get(pos);
		return null;
	}
}
