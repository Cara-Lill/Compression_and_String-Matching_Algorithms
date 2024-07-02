import java.util.*;

public class BoyerMoore{

	public static int search(String pattern, String text) {
		// TODO fill this in.
		int m = pattern.length(), n = text.length();

        // make sure neither text nor pattern is empty 
        if (m == 0 || n == 0) { return -1; }

        // get the bad character rule and good suffix rule tables
        int[] badCharRule = badCharacterRule(pattern);
        int[] goodSuffRule = goodSuffixRule(pattern);

        int i = 0; // index of current pos in the text
        while (i <= (n - m)) {
            int j = m - 1;

            // compare matching patterns
            while (j >= 0 && pattern.charAt(j) == text.charAt(i + j)) { j--; }

            if (j < 0) { return i; } // pattern found, so return the index
            // pattern isn't found, so move i to new pos using the good and bad rules
            else { 
                i += Math.max(goodSuffRule[j + 1], j - badCharRule[text.charAt(i + j)]);
            }
        }
        return -1; // no pattern found
    }

	/**
     * Constructs the bad character rule table for the given pattern.
     *
     * @param pattern The pattern for which to construct the bad character rule table.
     * @return The bad character rule table.
     */
	private static int[] badCharacterRule(String pattern) {
		int[] shiftVal = new int[256]; // assuming ASCII characters are used
		Arrays.fill(shiftVal, -1); // initilise the array

		// fill the front of the shiftVal array with the pattern characters 
		for (int i = 0; i < pattern.length(); i++) {
			shiftVal[(int) pattern.charAt(i)] = i; 
		}

		return shiftVal;
	}

	/**
     * Constructs the good suffix rule table for the given pattern.
     *
     * @param pattern The pattern for which to construct the good suffix rule table.
     * @return The good suffix rule table.
     */
	private static int[] goodSuffixRule(String pattern) {
        int m = pattern.length();
        int[] shift = new int[m + 1]; // shift table for good suffix rule
    	int[] border = new int[m + 1]; // table to store the border positions

        int i = m, j = m + 1;
        border[i] = j;

		// create the border array
        while (i > 0) {
			// if the characters don't match, update the shift value
            while (j <= m && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
                if (shift[j] == 0) { shift[j] = j - i; }
                j = border[j]; // update j to the next border position
            }
            i--;
            j--;
            border[i] = j; // set the border position for the current suffix
        }

        j = border[0]; // set j to the border position of the entire pattern
        for (i = 0; i <= m; i++) {
			// if the shift value isn't set, use the border value
            if (shift[i] == 0) { shift[i] = j; }
			// update j to the next border position
            if (i == j) { j = border[j]; }
        }

        return shift; // return the computed shift table
	}
}
