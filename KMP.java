/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public static int search(String pattern, String text) {
		// TODO fill this in.
		int[] m = getPartialMatchTable(pattern); // get the partial table

		int k = 0; // the beggining pos of the current match in the text
		int i = 0; // the pos of the current character in the pattern
		
		//  go through the text to find the pattern
		while (k + i < text.length()) {
			if (pattern.charAt(i) == text.charAt(k + i)) {
				i = i + 1; // move to next pattern character
				if (i == pattern.length()) {
					return k; // pattern found
				}
			}
			else if(m[i] == -1) {
				k = k + i + 1; // move to next starting place in the text
				i = 0; // reset patten position
			}
			else {
				k = k + i - m[i]; // use the partial match table to shift the pattern
				i = m[i]; // update the pattern pos using the partial match table
			}
		}

		return -1; // pattern not found
	}

	 /**
     * Constructs the partial match table (PMT) for the pattern.
     *
     * @param pattern The pattern for which to construct the PMT.
     * @return The partial match table.
     */
	public static int[] getPartialMatchTable(String pattern) {
		// initiate initial values
		int[] m = new int[pattern.length()];
		m[0] = -1;
		m[1] = 0;

		int pos = 2; // current position
		int j = 0; // length of the previous logest frefix/suffix

		// build the partial match table
		while (pos < pattern.length()) {
			if (pattern.charAt(pos - 1) == pattern.charAt(j)) {
				// if characters match, set the partial match value and move on
				m[pos] = j + 1;
				pos++;
				j++;
			}
			else if (j > 0) {
				// find the next prefix/sufix with PMT
				j = m[j];
			}
			else {
				m[pos] = 0; // no prefix/sufix found, set to 0
				pos++;
			}
		}
		return m; // return PMT
	}
}
