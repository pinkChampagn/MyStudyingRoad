package LiCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;




class TreeNode {
   int val;
   TreeNode left;
   TreeNode right;
   TreeNode(int x) { val = x; }
}
/**
* @author 杨肖宁
* @version 创建时间：2019年7月23日 上午8:47:36
*/
public class day7_23 {

	public static void main(String[] args) {
		/*int[]a= {3,4,2,1,3};
		System.out.println(new day7_23().duplicate(a));*/
/*		String replaceSpace = new day7_23().replaceSpace(new StringBuffer("a b c "));
		System.out.println(replaceSpace);*/
		/*int[]c={1,2,4,3,5,6},d={4,2,1,5,3,6};
		int[]a= {1,2,3,4,5,6,7},b= {3,2,4,1,6,5,7};
		new day7_23().reConstructBinaryTree(c, d);*/
		//System.out.println((int)'c');
		//System.out.println(Math.pow(10, 2));
/*		String[]s= {"ABC","BCA"};
		int[] bestList = new day7_23().getBestList(s);
		for (int i : bestList) {
			System.out.println(i);
		}*/
		/*Map<String, Integer>map=new HashMap<>();
		String a = String.valueOf('A');
		map.put(a, 2);
		System.out.println(map.get("A"));*/
		String[]strings= {"ABCDGHJE","BCAEFDGJ"};
		int bestList = new day7_23().getBestList(strings);
		System.out.println(bestList);
	}
	/**
	 * 
	 * Last_Update 2019年7月23日上午9:05:23
	 * @param nums
	 * @return int
	 * 3. 数组中重复的数字
	 * 题目描述
	 *在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，
	 *也不知道每个数字重复几次。请找出数组中任意一个重复的数字
	 */
	public int duplicate(int[]nums) {
		if(nums.length==0||nums==null) 
			return -1;
		int[] a=new int[nums.length];
		for(int i=0;i<nums.length;i++) {
			while(nums[i]!=i) {
				if(nums[i]==nums[nums[i]]) {
					return nums[i];
				}
				{
					int t=nums[nums[i]];
					nums[nums[i]]=nums[i];
					nums[i]=t;
				}
			}
		}
		return -1;
	}
	/**
	 * 
	 * Last_Update 2019年7月23日上午9:08:44
	 * @return boolean
	 * 4. 二维数组中的查找
	 * 题目描述
	 * 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中
	 */
	public boolean SearchInTwo_Array(int s,int[][]Two_Array) {
		if(Two_Array.length==0||Two_Array==null||Two_Array[0].length==0)
            return false;
		int m=Two_Array[0].length;//行
		int n=Two_Array.length;//列
		int a=Two_Array[0][m-1];
		int i=0,j=m-1;
		while(Two_Array[i][j]!=s) {
			if(s>Two_Array[i][j])
				i++;
			else {
				j--;
			}
			if(i==n||j==-1)
				return false;
		}
		return true;
	}
	/**
	 * 
	 * Last_Update 2019年7月23日上午9:51:59
	 * @param str
	 * @return
	 * String
	 * 5. 替换空格
	 * 题目描述
	 * 将一个字符串中的空格替换成 "%20"
	 */
	public String replaceSpace(StringBuffer str) {
		int p1=str.length()-1;
    	for(int i=0;i<=p1;i++) {
    		if(str.charAt(i)==' ')
    			str.append("  ");
    	}
    	
    	int p2=str.length()-1;
    	while(p2>p1&&p1>=0) {
    		char c=str.charAt(p1--);
    		if(c==' ') {
    			str.setCharAt(p2--, '0');
    			str.setCharAt(p2--, '2');
    			str.setCharAt(p2--, '%');
    		}else {
				str.setCharAt(p2--, c);
			}
    	}
    	return str.toString();
    }
	/**
	 * 
	 * Last_Update 2019年7月23日下午2:32:20
	 * @param pre
	 * @param in
	 * @return
	 * TreeNode
	 * 7. 重建二叉树
	 * 题目描述
	 * 根据二叉树的前序遍历和中序遍历的结果，重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字
	 */
	/**
	 * Definition for binary tree
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
		if(pre==null||in==null||pre.length!=in.length||pre.length==0)
			return null;
		if(pre.length==1) {
			TreeNode treeNode=new TreeNode(pre[0]);//无引用则被GC
			return treeNode;
		}
			
		int head=pre[0],index=0;
		TreeNode hNode=new TreeNode(head);
		int leftchild=0,rightchild=0;
		//在中序数组中找到头的index
		for(int i=0;i<in.length;i++) {
			if(in[i]==head) {
				index=i;
				break;
			}
		}
		/*TreeNode left=new TreeNode(pre[1]);
		TreeNode right=new TreeNode(pre[index+1]);*/
		
		int[]leftchildPre=Arrays.copyOfRange(pre, 1, index+1);
		int[]rightchildPre=Arrays.copyOfRange(pre, index+1, pre.length);
		int[]leftchildIn=Arrays.copyOfRange(in, 0, index);
		int[]rightchildIn=Arrays.copyOfRange(in, index+1, in.length);
		
		TreeNode left1 = reConstructBinaryTree(leftchildPre, leftchildIn);
		hNode.left=left1;
		TreeNode right1 = reConstructBinaryTree(rightchildPre, rightchildIn);
		hNode.right=right1;
		return hNode;
	}
	/**
	 * 
	 * Last_Update 2019年7月23日下午4:04:36
	 * @param pNode
	 * @return
	 * TreeLinkNode
	 * 8. 二叉树的下一个结点
	 * 题目描述
	 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
	 */
	
	public TreeLinkNode GetNext(TreeLinkNode pNode) {
		if(pNode.right!=null)
		{
			TreeLinkNode node=pNode.right;
			while(node.left!=null)
				node=node.left;
			return node;
		}
		//重点： 如果没有右子节点，则中序 后一位  存在两种可能： 节点是父节点的左子树：则父节点就是中序后一位。
		//。如果节点是父节点的右子树，则一直向上找，如果有一个节点是父节点的左子树，则该父节点即为当前节点的后一位，没有 则当前节点为中序最后一位
		else {
			while(pNode.next!=null) {
				TreeLinkNode parent=pNode.next;
				if(parent.left==pNode)
					return parent;
				pNode=pNode.next;
			}
		}
		return null;
	}
	
	public int getBestList(String ...s) {
		int[]result=new int[10];
		Arrays.fill(result, 0);
		for (String i : s) {
			char[] c =i.toCharArray();
			int length=c.length;
			for(int j=0;j<length;j++) {
				result[(int)c[j]-65]+=(int) Math.pow(10, length-j-1);
			}
		}
		int sum=0,notNull=0;//sum=总和
		for(int j=0;j<10;j++) {
			if(result[j]!=0)
				notNull++;
		}
		//存 权重结果的集合
		Map<String,Integer> map=new HashMap();
		int k=10;
		for(int j=0;j<notNull;j++) {
				
				int maxIndex = getMax(result);
				char key=(char)(maxIndex+65);
				String ss=String.valueOf(key);
				map.put(ss, k-j-1);
				result[maxIndex]=0;
				k=k;
				//sum+=
			
		}
		
		for (String string : s) {
			for(int i=0;i<string.length();i++) {
				if(map.containsKey(String.valueOf('A'))&&string.charAt(i)=='A')
					sum+= Math.pow(10,string.length()-i-1 )*map.get(String.valueOf('A'));
				else if (map.containsKey(String.valueOf('B'))&&string.charAt(i)=='B') {
					sum+= Math.pow(10,string.length()-i-1 )*map.get(String.valueOf('B'));
				}
				else if (map.containsKey(String.valueOf('C'))&&string.charAt(i)=='C') {
					sum+= Math.pow(10,string.length()-i-1 )*map.get(String.valueOf('C'));
				}
				else if (map.containsKey(String.valueOf('D'))&&string.charAt(i)=='D') {
					sum+= Math.pow(10,string.length()-i-1 )*map.get(String.valueOf('D'));
				}
				else if (map.containsKey(String.valueOf('E'))&&string.charAt(i)=='E') {
					sum+= Math.pow(10,string.length()-i-1 )*map.get(String.valueOf('E'));
				}
				else if (map.containsKey(String.valueOf('F'))&&string.charAt(i)=='F') {
					sum+= Math.pow(10,string.length()-i-1 )*map.get(String.valueOf('F'));
				}
				else if (map.containsKey(String.valueOf('G'))&&string.charAt(i)=='G') {
					sum+= Math.pow(10,string.length()-i-1 )*map.get(String.valueOf('G'));
				}
				else if (map.containsKey(String.valueOf('H'))&&string.charAt(i)=='H') {
					sum+= Math.pow(10,string.length()-i-1 )*map.get(String.valueOf('H'));
				}else if (map.containsKey(String.valueOf('I'))&&string.charAt(i)=='I') {
					sum+= Math.pow(10,string.length()-i-1 )*map.get(String.valueOf('I'));
				}else if (map.containsKey(String.valueOf('J'))&&string.charAt(i)=='J') {
					sum+= Math.pow(10,string.length()-i-1 )*map.get(String.valueOf('J'));
				}
/*				int m=0;
				while(map.containsKey(String.valueOf((char)(65+m)))&&string.charAt(i)==(char)(65+m))
					sum+= Math.pow(10,string.length()-i-1 )*map.get(String.valueOf((char)(65+i)));*/
			}
		}
		
		
		
		
		return sum;
		
	}
	public int getMax(int[]a) {
		int max=a[0];
		int MaxIndex=0;
		for(int i=1;i<10;i++)
		{
			if(a[i]!=0&&max<a[i])
			{
				MaxIndex=i;
			}
		}
		return MaxIndex;
	}
}
class TreeLinkNode {

    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
