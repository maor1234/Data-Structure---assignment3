
import java.util.Random;
class BTNode{
	int data;
	BTNode left, right;

	BTNode(int data){
		this.data = data;
		this.left = null;
		this.right = null;
	}
	public String toString(){return ""+data;}
}// Node

public class BinaryTree {

	public static Random generator = new Random(19580427);
	public BTNode root;
	BinaryTree prev;

	public BTNode getRoot() {
		return root;
	}
	public void setRoot(BTNode root) {
		this.root = root;
	}
	// constructors
	public BinaryTree(){
		root = null;
	}
	//////// add a new node
	public void add(int data){
		if (root==null) root = new BTNode(data);
		else{
			BTNode n = root;
			while( n!=null){
				double select = generator.nextDouble();
				if (select < 0.5){
					if (n.left != null) n = n.left;
					else{
						n.left = new BTNode(data);
						n = null;
					}
				}
				else{// select >= 0.5
					if (n.right != null) n = n.right;
					else{
						n.right = new BTNode(data);
						n = null;
					}
				}
			}
		}
	}
	/////////
	public boolean contains(int data){
		return contains(data, root);
	}
	public boolean contains(int data, BTNode node){
		boolean ans = false;
		if (node==null){
			ans = false;
		}
		else{
			ans = node.data == (data) || contains(data, node.left) || contains(data, node.right);
		}
		return ans;
	} 
	/////////
	public boolean isEmpty(){
		return this.root == null;
	}
	// print all tree nodes
	// PreOrder
	public void printPreOrder() {
		printPreOrder(root);
		System.out.println();
	}
	void printPreOrder(BTNode node) {//PreOrder
		if (node != null) {
			System.out.print(node.data+", ");
			printPreOrder(node.left);
			printPreOrder(node.right);
		}
	}
	public void printPreorderPlus(){
		printPreorderPlus("", root);
	}
	public void printPreorderPlus(String Path, BTNode node){
		if (node != null){
			System.out.println(node.data + ": " + Path);
			printPreorderPlus(Path+"L", node.left);
			printPreorderPlus(Path+"R", node.right);
		}
	}

	public boolean isBinarySearchTree(BinaryTree tree){
		return isBinarySearchTree(root, Integer.MIN_VALUE,Integer.MAX_VALUE);
	}

	boolean isBinarySearchTree(BTNode node, int min, int max){
		if (node == null)
			return true;

		if (node.data > max || node.data < min)
			return false;

		return (isBinarySearchTree(node.left, min, node.data-1) &&
				isBinarySearchTree(node.right, node.data+1, max));
	}


	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		int nodes[]={20,10,11,33,3,16,120,5,1};
		for (int i=0; i<nodes.length; i++) {
			bt.add(nodes[i]); 
			System.out.print(nodes[i]+ ", ");
		}
		System.out.println();
		System.out.println("by pre-order");
		bt.printPreOrder();
		System.out.println();
		System.out.println("by pre-order-plus");
		bt.printPreorderPlus();
		System.out.println();
		System.out.println(bt.isBinarySearchTree(bt));

	}

}
