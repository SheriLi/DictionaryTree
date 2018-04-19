package spellchecker;

import static sbcc.Core.*;

import java.util.*;
import java.util.regex.*;

public class BasicSpellChecker implements SpellChecker {
	Dictionary dictionary = new BasicDictionary();
	BinaryTree b = new BinaryTree();
	String document;
	private int startIndex;


	@Override
	public void importDictionary(String filename) throws Exception {
		dictionary.importFile(filename);
	}


	@Override
	public void loadDictionary(String filename) throws Exception {
		dictionary.load(filename);

	}


	@Override
	public void saveDictionary(String filename) throws Exception {
		dictionary.save(filename);

	}


	@Override
	public void loadDocument(String filename) throws Exception {
		document = readFile(filename);
	}


	@Override
	public void saveDocument(String filename) throws Exception {

		writeFile(filename, document);
	}


	@Override
	public String getText() {

		return document;
	}


	@Override
	public String[] spellCheck(boolean continueFromPrevious) {
		String[] spellCheckResult = new String[4];
		Pattern p = Pattern.compile("\\b[\\w']+\\b");
		Matcher m = p.matcher(document);

		if (!continueFromPrevious) {
			startIndex = 0;
		}

		while (m.find(startIndex)) {
			startIndex = m.start();
			if (dictionary.find(m.group()) != null) {
				spellCheckResult[0] = m.group();
				spellCheckResult[1] = Integer.toString(m.start());
				spellCheckResult[2] = dictionary.find(m.group())[0];
				spellCheckResult[3] = dictionary.find(m.group())[1];
				continueFromPrevious = true;
				// startIndex = m.end() + 1;
				startIndex++;
				return spellCheckResult;
			} else {
				// startIndex = m.end() + 1;
				startIndex++;
			}
		}
		return null;
	}


	@Override
	public void addWordToDictionary(String word) {
		dictionary.add(word);

	}


	@Override
	public void replaceText(int startIndex, int endIndex, String replacementText) {
		String initialT = document.substring(startIndex, endIndex);
		document = document.replace(initialT, replacementText);
	}

}
