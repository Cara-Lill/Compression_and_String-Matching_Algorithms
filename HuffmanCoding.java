/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */

import java.util.*;
public class HuffmanCoding {
	/**
     * Node class representing a node in the Huffman tree.
     */
	private static class Node {
		char symbol;
		int f; // frequency
		Node left, right;

		 /**
         * Constructor for creating a leaf node with a symbol and frequency.
         *
         * @param symbol   The character symbol.
         * @param frequency The frequency of the character.
         */
		Node(char symbol, int frequency) {
			this.symbol = symbol;
			this.f = frequency;
		}

		/**
         * Constructor for creating an internal node with left and right children.
         *
         * @param left  The left child node.
         * @param right The right child node.
         */
		Node(Node left, Node right) {
			this.symbol = '\0'; // doesn't need a symbol
			this.f = left.f + right.f;
			this.left = left;
			this.right = right;
		}
	}

	private Node root;
	private Map<Character, String> charToCode;

	/**
	 * This would be a good place to compute and store the tree.
	 */
	public HuffmanCoding(String text) {
		// TODO fill this in.
		Map<Character, Integer> freqMap = getFrequencies(text);
		root = buildTree(freqMap);
		charToCode = new HashMap<>();
		setCodes(root, "");
	}

	/**
     * Computes the frequency of each character in the input text.
     *
     * @param text The input text.
     * @return A map of characters to their frequencies.
     */
	private Map<Character, Integer> getFrequencies(String text) {
		Map<Character, Integer> fMap = new HashMap<>();

		// add each character to the map and increase the frequency of characters already listed
        for (char c : text.toCharArray()) {
            fMap.put(c, fMap.getOrDefault(c, 0) + 1);
        }

        return fMap;
	}

	/**
     * Builds the Huffman tree using the frequency map.
     *
     * @param fs The frequency map of characters.
     * @return The root node of the Huffman tree.
     */
	private Node buildTree(Map<Character, Integer> fs) {
		// make a priority queue (frequency low -> high)
		PriorityQueue<Node> priQue = new PriorityQueue<>(
			(n1, n2) -> {
				int i = Integer.compare(n1.f, n2.f);
				if (i != 0) {
					return i;
				} else {
					return Character.compare(n1.symbol, n2.symbol);
				}
			}
		);
		
		// add all nodes to the priority queue
		for (Map.Entry<Character, Integer> entry : fs.entrySet()) {
			priQue.add(new Node(entry.getKey(), entry.getValue()));
		}

		// build the tree
		while (priQue.size() > 1) {
			Node a = priQue.poll();
			Node b = priQue.poll();
			priQue.add(new Node(a, b));
		}

		return priQue.poll();
	}

	/**
     * Sets the binary codes for each character.
     *
     * @param n    The current node in the Huffman tree.
     * @param code The current binary code.
     */
	private void setCodes(Node n, String code) {
		if (n == null) { return; }

		// if the node is one of the characters, put it and the code into the map
		if (n.left == null && n.right == null) {
			charToCode.put(n.symbol, code);
		}
		else {
			// recursively call for the left and right child
			setCodes(n.left, code + "0");
			setCodes(n.right, code + "1");
		}
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {
		// TODO fill this in.
		StringBuilder encoded = new StringBuilder();
		// get the code for each character and append it to the string
		for (char c : text.toCharArray())
			encoded.append(charToCode.get(c));
	
		return encoded.toString();
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		// TODO fill this in.
		StringBuilder decoded = new StringBuilder();
		Node curNode = root;
		// for each character in the encoded string
		for (char c : encoded.toCharArray()) {
			// if it is a 0, get the left node, else get the right node
			if (c == '0') { curNode = curNode.left; } 
			else { curNode = curNode.right; }

			if (curNode.left == null && curNode.right == null) {
				// coded character has been found
				decoded.append(curNode.symbol);
				curNode = root; // reset for next character
			}
		}
		return decoded.toString();
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print
	 * out the encoding tree.
	 */
	public String getInformation() {
		return "";
	}
}
