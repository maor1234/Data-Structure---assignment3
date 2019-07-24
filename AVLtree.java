
class Node {
	int key; // the key
	int balance; // the balance factor
	int height;// height of the node
	Node left, right, parent;
	int num;
	int desc;


	// the constructor
	Node(int key, Node parent) {
		this.key = key;
		this.parent = parent;
		left =	right;
		left = null;
		right = null;

		height = 0;
		desc = 1;
		num = 1;
	}

	public String toString(){
		return "key: "+key + ", (height="+height+", balance="+balance+")";
	}
}
/**
 * This class represents the AVL tree.
 */

public class AVLtree {
	// the root
	public Node root;

	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	// the constructor
	public AVLtree(){
		root = null;
	}
	// The function to insert key in subtree rooted
	// with node and returns true if this is a new key, otherwise  returns false 
	public boolean insert(int data) {
		/* Perform the normal BST insertion */
		if (root == null){
			root = new Node(data, null);}
		else {
			Node n = root;
			Node parent;


			boolean check = true;
			while (check) {


				// equal keys are not allowed in this implementation
				if (n.key == data){

					n.num=n.num+1;

					n.desc=n.desc+1;

					children(n);


					if (check==true)
						return true;

				}
				parent = n;

				boolean goLeft = n.key > data;

				if (goLeft) n = n.left;
				else n = n.right;
				//n = goLeft ? n.left : n.right;
				if (n == null) {
					if (goLeft){
						parent.left = new Node(data, parent);
						children(parent.left);
					} 
					else{
						parent.right = new Node(data, parent);
						children(parent.right);

					}


					rebalance(parent);
					check = false;;
				}
			}
		}
		return true;
	}
	public void rebalance(Node n) {
		setBalance(n);

		if (n.balance == -2) {
			if (height(n.left.left) >= height(n.left.right))
				n = rotateRight(n);
			else
				n = rotateLeftThenRight(n);

		} 
		else if (n.balance == 2) {
			if (height(n.right.right) >= height(n.right.left))
				n = rotateLeft(n);
			else
				n = rotateRightThenLeft(n);
		}

		if (n.parent != null) {
			rebalance(n.parent);
		} 
		else {
			root = n;
		}
	}

	public void children(Node c){

		if (c.parent != null){

			c.parent.desc++;;

			children(c.parent);	
		}

		c.desc =c.desc;
	}

	// A function to left rotation of subtree rooted with a
	public Node rotateLeft(Node a) {

		Node b = a.right;
		b.parent = a.parent;
		int ab = b.desc;
		b.desc = a.desc;

		a.desc = a.desc - ab;

		if (b.left != null){
			a.desc = a.desc + b.left.desc;
		}

		a.right = b.left;


		if (a.right != null)
			a.right.parent = a;

		b.left = a;
		a.parent = b;

		if (b.parent != null) {

			if (b.parent.right == a) {

				b.parent.right = b;

			} 
			else {
				b.parent.left = b;
			}
		}

		setBalance(a, b);

		return b;
	}

	// A function to right rotation of subtree rooted with a
	public Node rotateRight(Node a) {

		Node b = a.left;
		b.parent = a.parent;

		int ab = b.desc;

		b.desc = a.desc;
		a.desc = a.desc - ab;

		if (b.right != null){
			a.desc +=  b.right.desc;
		}

		a.left = b.right;

		if (a.left != null)
			a.left.parent = a;

		b.right = a;
		a.parent = b;

		if (b.parent != null) {

			if (b.parent.right == a) {

				b.parent.right = b;

			} 
			else {
				b.parent.left = b;
			}

		}

		setBalance(a, b);

		return b;
	}
	public int searchByPlace(int place){

		Node r = root;
		
		boolean flag = true;

		while(flag){
			if (r.left == null && r.right == null){
				return r.key;

			}
			else if (r.left == null){
				
				if (place <= r.num) return r.key;

				return r.right.key;

			}
			else if (r.right == null){
				if (place <= r.left.num) return r.left.key;
					
				return r.key;
				
			}
			
			else{
				int temp = Math.abs(r.left.desc - place) ;

				if (temp <= 0) r = r.left;


				else if (r.num >= temp)
					return r.key;

				else{
					
					place = place - r.left.desc + r.num;
					r = r.right;

				}
			}

		}
		return -1;
	}





	// A function performs left then right rotations
	public Node rotateLeftThenRight(Node n) {
		n.left = rotateLeft(n.left);
		return rotateRight(n);
	}

	// A function performs right then left rotations
	public Node rotateRightThenLeft(Node n) {
		n.right = rotateRight(n.right);
		return rotateLeft(n);
	}
	// updates the height of the node
	public int height(Node p){
		if (p == null) return -1;
		return p.height;
	}
	//  set balance factor of the nodes
	public void setBalance(Node... nodes) {
		for (Node n : nodes){
			int hLeft = height(n.left);
			int hRight = height(n.right);
			n.balance = hRight - hLeft;
			if (hLeft > hRight) n.height = hLeft + 1;
			else n.height = hRight + 1;
			//n.height = (hLeft > hRight ?  hLeft : hRight) + 1;
		}
	}
	// print the AVL Tree
	public void printTree() {
		if( root != null) {
			printTree(root.right, true, "");
			System.out.println(root.key + "  (" + root.balance + " " + root.height + " " + " " +root.num +" "+ " " + root.desc +")");
			printTree(root.left, false, "");
		}
	}

	public void printTree(Node node, boolean isRight, String indent) {
		if(node != null) {
			printTree(node.right, true, indent + (isRight ? "        " : " | "));
			System.out.print(indent);
			if (isRight) System.out.print(" /");
			else System.out.print(" \\");
			System.out.print("----- ");
			System.out.println(node.key + "  (" + node.balance + " " + node.height +" "+ node.num + " " +" "+ node.desc + ")");
			printTree(node.left, false, indent + (isRight ? " |      " : "  "));
		}
	}
	public void printPreorderPlus(){
		printPreorderPlus("", root);
	}
	public void printPreorderPlus(String Path, Node node){
		if (node != null){

			System.out.println(node.key + ": " + Path);
			printPreorderPlus(Path+"L", node.left);
			printPreorderPlus(Path+"R", node.right);
		}
	}








	public static void main(String[] args) {
		AVLtree tree = new AVLtree();
		int[] a = {10,5,20,15,40,80};
		//	int[] a1 = {5,2,10,1,3};
		int [] a1 = {4,1,15,53,5};
		int[] a2 = {10,20,15,40,80,30};
		int[] a3 = {1,2,6,3,4,5};
		System.out.println("Inserting values");
		for (int i = 0; i < a2.length; i++){
			tree.insert(a2[i]);
		}
		tree.printTree();

		System.out.println(tree.searchByPlace(2));
	}
}

