
public class hw3
{		
	public static boolean isBinarySearchTree(BinaryTree tree){
		return isBinarySearchTree(tree.root, Integer.MIN_VALUE,Integer.MAX_VALUE);
	}

	public static boolean isBinarySearchTree(BTNode node, int min, int max){
		if (node == null)
			return true;

		if (node.data > max || node.data < min)
			return false;

		return (isBinarySearchTree(node.left, min, node.data-1) &&
				isBinarySearchTree(node.right, node.data+1, max));
	}


	public static boolean isAVLTree(BinarySearchTree tree){
		BSTNode num = tree.root;
		return Balanced(num);
	}

	public static boolean Balanced(BSTNode node) 
	{
		int heightl;
		int heightr;

		if (node == null)
			return true;

		heightl = height(node.left);
		heightr = height(node.right);

		if (Math.abs(heightl - heightr) <= 1
				&& Balanced(node.left)
				&& Balanced(node.right)) 
			return true;
		return false;
	}

	public static int height(BSTNode node) 
	{
		if (node == null)
			return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}
}
