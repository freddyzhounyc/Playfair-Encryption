/**
 * The PlayfairEncryptionEngine class is the actual runner which combines the operations of Bigram, Phrase, and KeyTable to create an encryption engine.
 *
 * @author Freddy Zhou
 *      Email: freddy.zhou@stonybrook.edu
 *      Stony Brook ID: 116580337
 *      HW #4 - Playfair Encryption
 *      CSE 214
 *      Recitation Number: R04 | TA Names: Veronica Oreshko, Anuj Sureshbhai, Alex Zuzow
 */
import java.util.Scanner;

public class PlayfairEncryptionEngine{
    /**
     * Used to run the actual playfair encryption engine that combines the operations of Bigram, Phrase, and KeyTable.
     * @param args - Not Used.
     */
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in); // Used to get input
        boolean quit = false;

        // Done before loop to create the key phrase for all menu operations.
        System.out.print("Enter key phrase: ");
        String keyPhraseInput = cin.nextLine();
        KeyTable newKeyTable = KeyTable.buildFromString(keyPhraseInput);
        Phrase newPhrase = new Phrase();
        System.out.println("Generation success!\n");

        String menuInput = "";
        // Loop used to go until the user requests to quit the program.
        while (!quit){
            // Prints out the menu options and takes user input (Case Insensitive).
            System.out.println("Menu:\n(CK) - Change key\n(PK) - Print key\n(EN) - Encrypt\n(DE) - Decrypt\n(Q) - Quit\n");
            System.out.print("Please select an option: ");
            menuInput = cin.nextLine().toLowerCase();
            System.out.println();

            // Menu Option to change the key.
            if (menuInput.equals("ck")){
                System.out.print("Enter key phrase: ");
                keyPhraseInput = cin.nextLine();
                newKeyTable = KeyTable.buildFromString(keyPhraseInput);
                System.out.println("Generation success!\n");
            }
            // Menu Option to print out the key table.
            if (menuInput.equals("pk")){
                for (int i = 0; i < 5; i++){
                    for (int j = 0; j < 5; j++){
                        System.out.print(newKeyTable.getKeyTable()[i][j]);
                    }
                    System.out.println();
                }
                System.out.println();
            }
            // Menu Option to encrypt the phrase that is provided.
            if (menuInput.equals("en")){
                System.out.print("Please enter a phrase to encrypt: ");
                String encryptionWord = cin.nextLine();
                newPhrase = Phrase.buildPhraseFromStringforEnc(encryptionWord);
                newPhrase = newPhrase.encrypt(newKeyTable);
                System.out.println(newPhrase + "\n");
            }
            // Menu Option to decrypt the phrase that is provided.
            if (menuInput.equals("de")){
                System.out.print("Please enter a phrase to decrypt: ");
                String decryptionWord = cin.nextLine().toUpperCase();
                Phrase decryptionPhrase = new Phrase();
                for (int i = 0; i < decryptionWord.length()-1; i+=2){
                    Bigram decryptionBigram = new Bigram(decryptionWord.charAt(i), decryptionWord.charAt(i+1));
                    decryptionPhrase.enqueue(decryptionBigram);
                }
                decryptionPhrase = decryptionPhrase.decrypt(newKeyTable);
                System.out.println(decryptionPhrase + "\n");
            }
            // Menu Option to quit the program.
            if (menuInput.equals("q"))
                quit = true;
        }
    }
}
