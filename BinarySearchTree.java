class BSTNode {
	public int data;
	public BSTNode left;
	public BSTNode right;
	BSTNode(int newdata) {
		data = newdata;
		left = null;
		right = null;
	}
	BSTNode(int data, BSTNode left, BSTNode right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	public String toString(){
		return "data: "+data+" ";
	}
}

public class BinarySearchTree {
	// tree root
	public BSTNode root;
	public BSTNode getRoot() {
		return root;
	}

	public void setRoot(BSTNode root) {
		this.root = root;
	}

	public BinarySearchTree(){
		root = null;
	}

	public void insert(int elem) {
		BSTNode newNode = new BSTNode(elem);
		if (root == null){
			root = newNode;
		}
		else{
			BSTNode n = root;
			boolean flag = true;
			while (flag){
				if (elem > n.data){
					if (n.right != null) n = n.right;
					else{
						n.right = newNode;
						flag = false;;
					}
				}
				else{
					if (n.left != null) n = n.left;
					else{
						n.left = newNode;
						flag = false;;
					}
				}
			}

		}
	}
 
	// search for element elem
	public boolean find(int elem) {
		return find(root,elem);
	}
	boolean find(BSTNode tree, int elem) {
		if (tree == null)
			return false;
		if (elem == tree.data) 
			return true;
		if (elem < tree.data)
			return find(tree.left, elem);
		else
			return find(tree.right, elem);
	}

	// print all tree nodes
	public void print() {
		if (root == null) System.out.println("empty tree");
		else{
			print(root);
			System.out.println();
		}
	}
	void print(BSTNode tree) {//Inorder
		if (tree != null) {
			print(tree.left);
			System.out.print(tree.data+", ");
			print(tree.right);
		}
	}
	///////////////////////////
	public void remove(int elem) {
		root = remove(root, elem);
	}
	public static BSTNode remove(BSTNode node, int elem){
		if(node != null){
			if(elem > node.data){
				node.right = remove(node.right,elem);
			}
			else if(elem < node.data){
				node.left = remove(node.left,elem);
			}
			else{//the node that should be deleted is found
				if(node.left == null && node.right == null){
					node = null;
				}
				else if(node.left != null && node.right == null){//the node has only one child (left)
					node = node.left;
				}
				else if(node.right != null && node.left == null){//the node has only one child (right)
					node = node.right;
				}
				else{//node "tree" has two children
					if(node.right.left == null){// his right node has only one child (right)
						node.right.left = node.left;
						node = node.right;
					}
					else{// remove the smallest element
						BSTNode q, p = node.right;
						while(p.left.left != null)
							p = p.left;
						q = p.left;
						p.left = q.right;
						node.data = q.data;
					}
				}
			}
		}
		return node;
	}
	public boolean isEmpty(){
		return this.root == null;
	}
	public void printPreorderPlus(){
		printPreorderPlus("", root);
	}
	public void printPreorderPlus(String Path, BSTNode node){
		if (node != null){
			System.out.println(node.data + ": " + Path);
			printPreorderPlus(Path+"L", node.left);
			printPreorderPlus(Path+"R", node.right);
		}
	}
	
	public boolean isAVLTree(BinarySearchTree tree){
		BSTNode num=tree.root;
		return balanced(num);
	}
	
	 boolean balanced(BSTNode node) 
	    {
	        int heightl;
	        int heightr;

	        if (node == null)
	            return true;

	        heightl = height(node.left);
	        heightr = height(node.right);
	  
	        if (Math.abs(heightl - heightr) <= 1
	                && balanced(node.left)
	                && balanced(node.right)) 
	            return true;
	        return false;
	    }
	  
	    int height(BSTNode node) 
	    {
	        if (node == null)
	            return 0;
	        return 1 + Math.max(height(node.left), height(node.right));
	    }
	    
	    public static void main(String[] args) {
			BinarySearchTree bt = new BinarySearchTree();
			int nodes[]={7,54,32,5,67,8,4,3,2,7,9,6};
			for (int i=0; i<nodes.length; i++) {
				bt.insert(nodes[i]); 
				System.out.print(nodes[i]+ ", ");
			}
			
			System.out.println();
			bt.print();
			System.out.println(bt.isAVLTree(bt));

		}
}