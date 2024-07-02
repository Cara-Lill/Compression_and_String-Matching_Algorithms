
import java.util.*;

public class LempelZiv {
    /**
     * Take uncompressed input as a text string, compress it, and return it as a
     * text string.
     */
    public static String compress(String input) {
        // TODO fill this in.
        // initiate method fields
        StringBuilder output = new StringBuilder();
        int cursor = 0, windowSize = 100; 

        // while cursor is within the text
        while (cursor < input.length() - 1 ) {
            int length = 1, prevMatch = 0;
            while(true) {
                // make sure cursor + the length doesn't go past the text 
                if (cursor + length > input.length()) { break; }

                // find the index of the current substring within the sliding window
                int match = stringMatch(
                        input.substring(cursor, Math.min(cursor + length, input.length())), // substring to match
                        input.substring(Math.max(0, cursor - windowSize), cursor) // window
                );
                
                // if a match is found, update previous match position and increment match length
                if (match != -1) { // match succeeded
                    prevMatch = match;
                    length++;
                } else {
                    if (length == 1) { 
                        // no match found on first char check:
                        output.append("[0|0|" + input.charAt(cursor) + "]"); 
                    } else {
                        // no match found with a found prefix
                        int offset = cursor - (Math.max(0, cursor - windowSize) + prevMatch); // calc offset
                        // output with format
                        output.append("[" + offset + "|" + (length - 1) + "|" + input.charAt(cursor + length - 1) + "]");
                    }
                    cursor += length; // move the cursor forward by the length of the match
                    break;
                }
            }
        }
        return output.toString(); // return compressed string
    }

    /**
 * Finds the index of the first occurrence of a substring within a window of text.
 * 
 * @param substring The substring to match.
 * @param window The sliding window view of text.
 * @return The index of the substring within the window, or -1 if not found.
 */
private static int stringMatch(String substring, String window) {
    // iterate through the window 
    // (ensures that you don't check for a substring extending beyond the window's end)
    for (int i = 0; i <= window.length() - substring.length(); i++) {
        // check if the substring matches the portion of the window starting at index 'i'
        if (window.substring(i, i + substring.length()).equals(substring)) {
            // if there's a match, return the index
            return i;
        }
    }
    // if no match is found, return -1
    return -1;
}

    /**
     * Take compressed input as a text string, decompress it, and return it as a
     * text string.
     */
    public static String decompress(String compressed) {
        // TODO fill this in.
        // initilise method fields
        StringBuilder decompressed = new StringBuilder();
        int cursor = 0;

        // iterate through each tuplet (using ] as the delimiter)
        for (String token : compressed.split("(?<=])")) {
            // split a tuplet into each segment
            String[] segments = token.substring(1, token.length() - 1).split("\\|");

            // get each segment individually
            int offset = Integer.parseInt(segments[0]);
            int length = Integer.parseInt(segments[1]);
            char character = segments[2].charAt(0);

            // if it isn't a first occurance at the first char
            if (offset != 0 || length != 0) {
                for (int j = 0; j < length; j++)
                    // go backwards for the offset and add each letter of the reused substring
                    decompressed.append(decompressed.charAt(cursor - offset + j));
            }
            // add the new letter and move cursor to new position
            decompressed.append(character);
            cursor = decompressed.length();
        }
        return decompressed.toString(); // return decompressed string
    }

    /**
     * The getInformation method is here for your convenience, you don't need to
     * fill it in if you don't want to. It is called on every run and its return
     * value is displayed on-screen. You can use this to print out any relevant
     * information from your compression.
     */
    public String getInformation() {
        return "";
    }
}
