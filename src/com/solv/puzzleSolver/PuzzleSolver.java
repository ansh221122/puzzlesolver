package com.solv.puzzleSolver;

import java.util.HashMap;
import java.util.Iterator;

import com.trie.index.implement.util.CheckNull;

public class PuzzleSolver {
	
	static HashMap<String, node> allNodes = new HashMap(); 
	static CheckNull<PuzzleSolver.node> checkNull = (t)-> t!=null;
	static class node{
		node left;
		node right;
		node bottom;
		node top;
		boolean signal=true, parsed = false;
		int clear=1,posx,posy, connect =0;
		static node initialize()
		{
		return new node();	
		}
		
	}
	
	static void nodeFabricator(int n,int m,int[][] puzzle, node root)
	{
		if((n>puzzle.length-1 || m>puzzle[0].length-1 ))
		{
			return;
		}
		


		
		int sumHorizontal=(n-1<0?0:puzzle[n-1][m])
				+(n+1>puzzle.length-1?0:puzzle[n+1][m]);
		
		int sumVertical=(m-1<0?0:puzzle[n][m-1])+(m+1>puzzle[0].length-1?0:puzzle[n][m+1]);
		
		
		if((m==0 && n==0) || puzzle[n][m]==0 || (sumHorizontal==2 && sumVertical==0) || (sumHorizontal==0 && sumVertical==2)) // if first node or 0 at cell value or in the same line with no path possibility in another dimension 
		{
			nodeFabricator(n+1, m, puzzle, root);
			nodeFabricator(n, m+1, puzzle, root);
			return;
		}

			
			if(root.posx==n) // new path pssibility at same row
			{
				node newNode = null;
				if(allNodes.get("["+n+"]["+m+"]")!=null)
				{
					
					System.out.println("Node at "+"["+n+"]["+m+"]"+" repeated horizontaly");
				 newNode = allNodes.get("["+n+"]["+m+"]");
				}
				else
				{
					newNode = 	node.initialize();
					allNodes.put("["+n+"]["+m+"]",newNode);
				}
				root.right=newNode;
				newNode.left=root;
				newNode.posx=n;
				newNode.posy=m;
				root.connect++;
				newNode.connect++;
				System.out.println("Found path possibility at::::puzzle["+n+"]["+m+"]");
				nodeFabricator(n+1, m, puzzle, newNode);
				nodeFabricator(n, m+1, puzzle, newNode);
			}
			else if(root.posy==m)// new path possibility at same column
			{
				
				node newNode = null;
				if(allNodes.get("["+n+"]["+m+"]")!=null)
				{
					
				 newNode = allNodes.get("["+n+"]["+m+"]");
				 System.out.println("Node at "+"["+n+"]["+m+"]"+" repeated vertically");
				}
				else
				{
					newNode = 	node.initialize();
					allNodes.put("["+n+"]["+m+"]",newNode);
				}
				root.bottom=newNode;
				newNode.top=root;
				newNode.posx=n;
				newNode.posy=m;
				root.connect++;
				newNode.connect++;
				System.out.println("Found path possibility at::::puzzle["+n+"]["+m+"]");
				allNodes.put("["+n+"]["+m+"]",newNode);
				nodeFabricator(n+1, m, puzzle, newNode);
				nodeFabricator(n, m+1, puzzle, newNode);
				
			}
			
		}
	
	
	static void predict(node root)
	{
		if(root==null || root.parsed)
		{
			return;
		}
		
		root.parsed = true;
		
		predict(root.left);
		predict(root.right);
		predict(root.top);
		predict(root.bottom);
		if(root.connect==1 || (!root.signal && root.connect<=2))
		{
			System.out.println("got here");
			if(checkNull.notNull(root.left))
				root.left.signal=false;
			if(checkNull.notNull(root.right))
				root.right.signal=false;
			if(checkNull.notNull(root.top))
				root.top.signal=false;
			if(checkNull.notNull(root.bottom))
				root.bottom.signal=false;
			root.clear=0;
		}
		
		System.out.println("got here");
			/*if()
		{
				if(checkNull.notNull(root.left))
					root.left.signal=false;
				if(checkNull.notNull(root.right))
					root.right.signal=false;
				if(checkNull.notNull(root.top))
					root.top.signal=false;
				if(checkNull.notNull(root.bottom))
					root.bottom.signal=false;
				root.clear=0;
			
		}*/
			
		
		
	}
	
	static int[][] parsed= new int[6][6];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] puzzle= {{1,1,1,1,1,1},
				         {1,0,1,0,0,1},
				         {1,0,1,1,1,1},
				         {1,0,1,0,0,1},
				         {1,1,1,1,1,1},
				         {0,0,0,0,0,0}};
		System.out.println("puzzle dimensions "+puzzle.length+", "+puzzle[0].length);
		
		node root = node.initialize();
		root.posx=0;
		root.posy=0;
		node copy=root;
		allNodes.put("[0][0]",root );
		nodeFabricator(0,0,puzzle,copy);
		
		copy=root;
		predict(copy);
		
		Iterator<String> it= allNodes.keySet().iterator();
		while(it.hasNext())
		{
			String key=it.next(); 
					
			System.out.println("********"+key+"********");
			node current =allNodes.get(key);
			if(current.left!=null)
				System.out.println("has left");
			if(current.right!=null)
				System.out.println("has right");
			if(current.top!=null)
				System.out.println("has top");
			if(current.bottom!=null)
				System.out.println("has bottom");
			
			System.out.println("signal : "+current.signal);
			System.out.println("clear : "+current.clear);
			System.out.println("connect : "+current.connect);
			
		}
		
		

	}

}
