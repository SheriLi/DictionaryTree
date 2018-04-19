package spellchecker;

import java.io.*;
import java.util.*;

public class BinaryTree {
	public BinaryTreeNode root;
	public List<String> dictionaryPreOrder = new ArrayList<String>();


	public BinaryTree() {
		root = null;
	}


	public void insert(BinaryTreeNode node, String word) {
		if (root == null) {
			root = new BinaryTreeNode(word);
		} else {
			if (word.compareTo(node.value) < 0) {
				if (node.left == null) {
					node.left = new BinaryTreeNode(word);
				} else {
					insert(node.left, word);
				}
			} else if (word.compareTo(node.value) > 0) {
				if (node.right == null) {
					node.right = new BinaryTreeNode(word);
				} else {
					insert(node.right, word);
				}
			} else
				return;
		}
	}


	BinaryTreeNode sortedArrayToBST(String arr[], int start, int end) {

		/* Base Case */
		if (start > end) {
			return null;
		}

		/* Get the middle element and make it root */
		int mid = (start + end) / 2;
		BinaryTreeNode node = new BinaryTreeNode(arr[mid]);

		/*
		 * Recursively construct the left subtree and make it left child of root
		 */
		node.left = sortedArrayToBST(arr, start, mid - 1);

		/*
		 * Recursively construct the right subtree and make it right child of root
		 */
		node.right = sortedArrayToBST(arr, mid + 1, end);

		return node;
	}


	public void preOrder(BinaryTreeNode node) throws IOException {
		if (node != null) {
			dictionaryPreOrder.add(node.value);
			preOrder(node.left);
			preOrder(node.right);
		}
	}

}
