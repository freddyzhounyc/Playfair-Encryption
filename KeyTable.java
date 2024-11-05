/**
 * The KeyTable class creates and stores the key which will be used for the encryption and decryption.
 *
 * @author Freddy Zhou
 *      Email: freddy.zhou@stonybrook.edu
 *      Stony Brook ID: 116580337
 *      HW #4 - Playfair Encryption
 *      CSE 214
 *      Recitation Number: R04 | TA Names: Veronica Oreshko, Anuj Sureshbhai, Alex Zuzow
 */

public class KeyTable{
    private char[][] key; // The 5x5 matrix which represents the key.

    /**
     * Instantiates an instance of the KeyTable class where it creates a 5x5 character 2d array.
     *
     * @custom.postcondition
     *  The key instance variable will be instantiated to be a 2d array with 5 rows and 5 columns.
     */
    public KeyTable(){
        key = new char[5][5];
    }

    // Getter

    /**
     * Gets the key table for the encryption.
     * @return
     *  Returns the key table to be used in the encryption.
     * @custom.postcondition
     *  The key table will be returned.
     */
    public char[][] getKeyTable(){
        return key;
    }

    // Setters

    /**
     * Sets the key 2d array for the encryption.
     * @param key
     *  The key what will be used for the encryption.
     * @custom.precondition
     *  Key is a non-null character array that is 5x5.
     * @custom.postcondition
     *  The key instance variable will be the key parameter.
     */
    public void setKey(char[][] key) {
        this.key = key;
    }

    /**
     * Builds a key table from a given parameter that is a string. The key table is built and set to the key instance variable.
     * @param keyPhrase
     *  A string that represents the key phrase that will be used to create the key table, given the playfair encryption rules.
     * @return
     *  Returns the key table that is created with the string input.
     * @throws IllegalArgumentException
     *  Thrown if the string input is null.
     * @custom.precondition
     *  The string is not null and contains a value of string type.
     * @custom.postcondition
     *  The key table will be created and will be returned.
     */
    public static KeyTable buildFromString(String keyPhrase) throws IllegalArgumentException{
        if (keyPhrase == null)
            throw new IllegalArgumentException("Key phrase is null!");

        keyPhrase = keyPhrase.toUpperCase();
        keyPhrase = keyPhrase.replaceAll("\\s", "");
        keyPhrase = keyPhrase.replaceAll("[^a-zA-Z]", "");
        KeyTable newKeyTable = new KeyTable();
        String plainKey = "";

        // Gets rid of duplicate letters in the key phrase.
        for (int i = 0; i < keyPhrase.length(); i++){ // FIRE
            if (!plainKey.contains(keyPhrase.substring(i, i+1)))
                plainKey += keyPhrase.substring(i,i+1);
        }

        // Adds A to I if it is not already present in plainKey. (Use of ASCII Table).
        for (int i = 65; i < 74; i++){
            if (!plainKey.contains(Character.toString((char)i)))
                plainKey += Character.toString((char)i);
        }
        // Adds K to Z if it is not already present in plainKey. (Use of ASCII Table).
        for (int i = 75; i <= 90; i++){
            if (!plainKey.contains(Character.toString((char)i)))
                plainKey += Character.toString((char)i);
        }

        int indexCounter = 0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                newKeyTable.key[i][j] = plainKey.charAt(indexCounter);
                indexCounter++;
            }
        }

        return newKeyTable;
    }

    /**
     * Finds the index of the row that the character given in the parameter is in.
     * @param c
     *  A character that is in the key table that wants its row to be found.
     * @return
     *  Returns the row that that character provided in the parameter is located.
     * @throws IllegalArgumentException
     *  Thrown if the character given in the parameter is not found in any row.
     * @custom.precondition
     *  The character c in the parameter must be in the key table.
     * @custom.postcondition
     *  The row of the character given in the parameter will be returned.
     */
    public int findRow(char c) throws IllegalArgumentException{
        c = Character.toUpperCase(c);
        boolean foundChar = false;
        int rowResult = 0;

        for (int row = 0; row < 5; row++){
            String charRow = new String(key[row]);
            if (charRow.contains(Character.toString(c))){
                rowResult = row;
                foundChar = true;
            }
        }
        if (!foundChar)
            throw new IllegalArgumentException("Not a valid character in the key matrix!");

        return rowResult;
    }

    /**
     * Finds the index of the column that the character given in the parameter is in.
     * @param c
     *  A character that is in the key table that wants its column to be found.
     * @return
     *  Returns the column that that character provided in the parameter is located.
     * @throws IllegalArgumentException
     *  Thrown if the character given in the parameter is not found in any column.
     * @custom.precondition
     *  The character c in the parameter must be in the key table.
     * @custom.postcondition
     *  The column of the character given in the parameter will be returned.
     */
    public int findCol(char c) throws IllegalArgumentException{
        c = Character.toUpperCase(c);
        boolean foundChar = false;
        int colResult = 0;

        for (int col = 0; col < 5; col++){
            for (int row = 0; row < 5; row++){
                if (key[row][col] == c){
                    colResult = col;
                    foundChar = true;
                }
            }
        }
        if (!foundChar)
            throw new IllegalArgumentException("Not a valid character in the key matrix!");

        return colResult;
    }

    /**
     * Creates a string version of the key table to be returned.
     * @return
     *  Returns the string version of the key table.
     * @custom.precondition
     *  The key character 2d array must be instantiated.
     * @custom.postcondition
     *  The string version of the key table will be created and returned.
     */
    @Override
    public String toString(){
        String result = "";
        for (int row = 0; row < 5; row++){
            result += new String(key[row]);
            if (row < 4)
                result += "\n";
        }
        return result;
    }

    // main method used to test.

    /**
     * Used to test the class.
     * @param args - Not used.
     */
    public static void main(String[] args) {
        KeyTable test = KeyTable.buildFromString("playfair example");
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                System.out.print(test.key[i][j]);
            }
            System.out.println();
        }

        System.out.println(test.findCol('a'));
    }
}