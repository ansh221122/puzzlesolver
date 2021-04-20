package tree;

public class Inorder {
	
	

	 static class Node
	{
		 
		 static Node getInstance()
		 {
			 return new Node();
		 }
		Node left;
		Node right;
		int data;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Node root=new Node();
		
		root.left=Node.getInstance();
		root.right=Node.getInstance();
		root.data=1;
		root.left.data=2;
		root.left.left=Node.getInstance();
		root.left.right=Node.getInstance();
		root.left.left.data=4;
		root.left.right.data=5;
		root.right.data=3;
		inorderTraversal(root);
		
	}


	private static void inorderTraversal(Node root) {
		// TODO Auto-generated method stub
		
		if(root.left!=null)
		inorderTraversal(root.left);
		System.out.println(root.data);
		if(root.right!=null)
		inorderTraversal(root.right);
	}

}
