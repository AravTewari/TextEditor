package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete
 * ADT
 * 
 * @author Arav Tewari
 *
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete
{

	private TrieNode root;
	private int size;

	public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}

	/**
	 * Insert a word into the trie. For the basic part of the assignment (part 2),
	 * you should convert the string to all lower case before you insert it.
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes into
	 * the trie, as described outlined in the videos for this week. It should
	 * appropriately use existing nodes in the trie, only creating new nodes when
	 * necessary. E.g. If the word "no" is already in the trie, then adding the word
	 * "now" would add only one additional node (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 *         in the dictionary.
	 */
	public boolean addWord(String word)
	{
		word = word.toLowerCase();
		char[] arr = word.toCharArray();
		TrieNode current = root;

		for (int i = 0; i < arr.length; i++)
		{
			if (current.getValidNextCharacters().contains(arr[i]))
				current = current.getChild(arr[i]);
			else
				current = current.insert(arr[i]);
		}
		if (!(current.endsWord()))
		{
			current.setEndsWord(true);
			size++;
			return true;
		}
		return false;
	}

	/**
	 * Return the number of words in the dictionary.
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Returns whether the string is a word in the trie
	 */
	@Override
	public boolean isWord(String s)
	{
		s = s.toLowerCase();
		int length = s.length();
		char c;
		TrieNode curr = root;

		for (int i = 0; i < length; i++)
		{
			c = s.charAt(i);
			if (curr.getChild(c) == null)
				return false;
			
			curr = curr.getChild(c);
		}
		return (curr != null && curr.endsWord());
	}

	/**
	 * Return a list, in order of increasing (non-decreasing) word length,
	 * containing the numCompletions shortest legal completions of the prefix
	 * string. All legal completions must be valid words in the dictionary. If the
	 * prefix itself is a valid word, it is included in the list of returned words.
	 * 
	 * The list of completions must contain all of the shortest completions, but
	 * when there are ties, it may break them in any order. For example, if there
	 * the prefix string is "ste" and only the words "step", "stem", "stew", "steer"
	 * and "steep" are in the dictionary, when the user asks for 4 completions, the
	 * list must include "step", "stem" and "stew", but may include either the word
	 * "steer" or "steep".
	 * 
	 * If this string prefix is not in the trie, it returns an empty list.
	 * 
	 * @param prefix         The text to use at the word stem
	 * @param numCompletions The maximum number of predictions desired.
	 * @return A list containing the up to numCompletions best predictions
	 */
	@Override
	public List<String> predictCompletions(String prefix, int numCompletions)
	{		
		String s = prefix.toLowerCase();
		List<String> result = new LinkedList<String>();
		TrieNode node = root;
		
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			
			if (node.getValidNextCharacters().contains(c))
				node = node.getChild(c);
			else
				return result;
		}
		
		int counter = 0;
		List<TrieNode> queue = new LinkedList<TrieNode>();
		List<Character> childChars = new LinkedList<Character>(node.getValidNextCharacters());
		
		if (node.endsWord())
		{
			result.add(node.getText());
			counter++;
		}
		for (int i = 0;  i < childChars.size(); i++)
		{
			char c = childChars.get(i);
			queue.add(node.getChild(c));
		}
		
		while (!queue.isEmpty() && counter < numCompletions)
		{
			TrieNode current = queue.remove(0);
			if (current.endsWord())
			{
				result.add(current.getText());
				counter++;
			}
			
			List<Character> chars = new LinkedList<Character>(current.getValidNextCharacters());
			for (int i = 0; i < chars.size(); i++)
			{
				char c = chars.get(i);
				queue.add(current.getChild(c));
			}
		}
		return result;
	}

	// For debugging
	public void printTree()
	{
		printNode(root);
	}

	/** Do a pre-order traversal from this node down */
	public void printNode(TrieNode curr)
	{
		if (curr == null)
			return;

		System.out.println(curr.getText());

		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters())
		{
			next = curr.getChild(c);
			printNode(next);
		}
	}

}
