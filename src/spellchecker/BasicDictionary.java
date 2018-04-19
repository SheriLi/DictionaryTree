package spellchecker;

import static sbcc.Core.*;
import java.util.*;

public class BasicDictionary implements Dictionary {
	ArrayList<String> dictionary = new ArrayList<String>();
	BinaryTree b = new BinaryTree();


	@Override
	public void importFile(String filename) throws Exception {
		List<String> words = readFileAsLines(filename);
		for (String w : words) {
			String wt = w.trim();
			StringBuilder sb = new StringBuilder(wt);
			String lword = sb.toString().toLowerCase();
			dictionary.add(lword);
		}
		Collections.sort(dictionary);
		String[] dicArray = dictionary.toArray(new String[dictionary.size()]);
		b.root = b.sortedArrayToBST(dicArray, 0, dicArray.length - 1);
	}


	@Override
	public void load(String filename) throws Exception {
		List<String> words = readFileAsLines(filename);
		for (String word : words) {
			add(word);
		}
	}


	@Override
	public void save(String filename) throws Exception {

		b.preOrder(b.root);
		writeFileAsLines(filename, b.dictionaryPreOrder);

	}


	@Override
	public String[] find(String word) {
		Stack<String> small = new Stack();
		Stack<String> big = new Stack();
		StringBuilder sb = new StringBuilder(word);
		String lword = sb.toString().toLowerCase();
		String[] returnArray = new String[2];
		BinaryTreeNode current = b.root;
		while (current != null) {
			int d = current.value.compareTo(lword);
			if (d == 0) {
				return null;
			} else if (d > 0) {
				big.push(current.value);
				current = current.left;
			} else {
				small.push(current.value);
				current = current.right;
			}
		}
		if (!small.isEmpty()) {
			returnArray[0] = small.pop();
		} else {
			returnArray[0] = "";
		}
		if (!big.isEmpty()) {
			returnArray[1] = big.pop();
		} else {
			returnArray[1] = "";
		}
		return returnArray;
	}


	@Override
	public void add(String word) {
		BinaryTreeNode newNode = new BinaryTreeNode(word);
		newNode.value = word;
		newNode.left = null;
		newNode.right = null;
		if (b.root == null) {
			b.root = newNode;
		} else {
			b.insert(b.root, word);
		}

	}


	@Override
	public BinaryTreeNode getRoot() {
		return b.root;
	}


	@Override
	public int getCount() {

		return dictionary.size();
	}

}
