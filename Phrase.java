/**
 * The Phrase class implements a queue using a linked list where it represents the encrypted phrase or decrypted phrase.
 *
 * @author Freddy Zhou
 */

public class Phrase extends BigramQueue{
    private BigramQueue bigramQueue; // The queue implementation used in this class.
    private int size;

    /**
     * Creates an instance of the phrase class and implements a queue in the class by using a linked list.
     * @custom.postcondition
     *  An instance of the phrase class will be created and bigram queue will be instantiated with a linked list.
     */
    public Phrase(){
        bigramQueue = new BigramQueue(); // make front = head | rear = tail
        size = 0;
    }

    /**
     * Adds the Bigram given in the parameter to the rear of the queue.
     * @param b
     *  The bigram that will be added to the queue.
     * @custom.precondition
     *  The bigram b in the parameter must be of type Bigram.
     * @custom.postcondition
     *  The bigram given in the parameter will be added to the rear of the queue.
     */
    public void enqueue(Bigram b){
        bigramQueue.enqueue(b);
        size++;
    }

    /**
     * Removes the bigram that is in the front of the queue.
     * @return
     *  Returns the bigram that was removed from the front of the queue.
     * @custom.precondition
     *  The queue should not be empty.
     * @custom.postcondition
     *  The bigram at the front of the queue will be removed and returned.
     */
    public Bigram dequeue(){
        size--;
        return bigramQueue.dequeue();
    }

    /**
     * The front of the queue will be returned without being removed from the queue.
     * @return
     *  Returns the bigram that is at the front of the queue without removing it.
     * @custom.precondition
     *  The queue should not be empty.
     * @custom.postcondition
     *  The bigram at the front of the queue will be returned.
     */
    public Bigram peek(){
        return bigramQueue.peek();
    }

    /**
     * The size of the queue will be returned.
     * @return
     *  Returns the size of the queue.
     * @custom.postcondition
     *  The size will be returned.
     */
    public int size(){
        return size;
    }

    /**
     * The result of whether the queue is empty or not will be returned.
     * @return
     *  Returns whether the queue is empty or not.
     * @custom.postcondition
     *  True will be returned if the size is 0 and false will be returned otherwise.
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * This encrypts the bigrams/phrase that is provided and uses the playfair rules to encrypt it using the key.
     * @param key
     *  The key table that will be used to encrypt the phrase/bigram provided in the object using the playfair rules.
     * @return
     *  Returns the phrase that contains the encrypted bigrams.
     * @throws IllegalArgumentException
     *  Thrown if the key that is provided is null.
     * @custom.precondition
     *  The key is not null and the key can be used to be able to encrypt the provided bigrams.
     * @custom.postcondition
     *  The phrase will be encrypted and the encrypted phrase of bigrams will be returned.
     */
    public Phrase encrypt(KeyTable key) throws IllegalArgumentException{
        if (key == null)
            throw new IllegalArgumentException("The key is null!");
        Phrase encryptedPhrase = new Phrase();

        for (int i = 0; i < size(); i++) {
            Bigram currentBigram = dequeue();

            if (key.findRow(currentBigram.getFirst()) == key.findRow(currentBigram.getSecond())) {
                char first, second;
                if (key.findCol(currentBigram.getFirst()) == 4)
                    first = key.getKeyTable()[key.findRow(currentBigram.getFirst())][0];
                else
                    first = key.getKeyTable()[key.findRow(currentBigram.getFirst())][key.findCol(currentBigram.getFirst()) + 1];
                if (key.findCol(currentBigram.getSecond()) == 4)
                    second = key.getKeyTable()[key.findRow(currentBigram.getSecond())][0];
                else
                    second = key.getKeyTable()[key.findRow(currentBigram.getSecond())][key.findCol(currentBigram.getSecond()) + 1];

                encryptedPhrase.enqueue(new Bigram(first, second));
            } else if (key.findCol(currentBigram.getFirst()) == key.findCol(currentBigram.getSecond())) {
                char first, second;
                if (key.findRow(currentBigram.getFirst()) == 4)
                    first = key.getKeyTable()[0][key.findCol(currentBigram.getFirst())];
                else
                    first = key.getKeyTable()[key.findRow(currentBigram.getFirst()) + 1][key.findCol(currentBigram.getFirst())];
                if (key.findRow(currentBigram.getSecond()) == 4)
                    second = key.getKeyTable()[0][key.findCol(currentBigram.getSecond())];
                else
                    second = key.getKeyTable()[key.findRow(currentBigram.getSecond()) + 1][key.findCol(currentBigram.getSecond())];

                encryptedPhrase.enqueue(new Bigram(first, second));
            } else {
                char first, second;

                int colFirstNonEnc = key.findCol(currentBigram.getFirst());
                int colSecondNonEnc = key.findCol(currentBigram.getSecond());

                first = key.getKeyTable()[key.findRow(currentBigram.getFirst())][colSecondNonEnc];
                second = key.getKeyTable()[key.findRow(currentBigram.getSecond())][colFirstNonEnc];

                encryptedPhrase.enqueue(new Bigram(first, second));
            }

            enqueue(currentBigram);
        }

        return encryptedPhrase;
    }

    /**
     * The phrase in the object will be decrypted and the new phrase with the decrypted phrase bigrams will be returned.
     * @param key
     *  The key table that will be used in order ot decrypt the encrypted phrase provided in the phrase object.
     * @return
     *  Returns the decrypted phrase of bigrams.
     * @throws IllegalArgumentException
     *  Thrown if the key table that is provided is null and does not contain values.
     * @custom.precondition
     *  The key table provided will not be null.
     * @custom.postcondition
     *  The decrypted phrase of bigrams will be returned.
     */
    public Phrase decrypt(KeyTable key) throws IllegalArgumentException{
        if (key == null)
            throw new IllegalArgumentException("The key is null!");
        Phrase decryptedPhrase = new Phrase();

        for (int i = 0; i < size(); i++) {
            Bigram currentBigram = dequeue();

            if (key.findRow(currentBigram.getFirst()) == key.findRow(currentBigram.getSecond())) {
                char first, second;
                if (key.findCol(currentBigram.getFirst()) == 0)
                    first = key.getKeyTable()[key.findRow(currentBigram.getFirst())][4];
                else
                    first = key.getKeyTable()[key.findRow(currentBigram.getFirst())][key.findCol(currentBigram.getFirst()) - 1];
                if (key.findCol(currentBigram.getSecond()) == 0)
                    second = key.getKeyTable()[key.findRow(currentBigram.getSecond())][4];
                else
                    second = key.getKeyTable()[key.findRow(currentBigram.getSecond())][key.findCol(currentBigram.getSecond()) - 1];

                decryptedPhrase.enqueue(new Bigram(first, second));
            } else if (key.findCol(currentBigram.getFirst()) == key.findCol(currentBigram.getSecond())) {
                char first, second;
                if (key.findRow(currentBigram.getFirst()) == 0)
                    first = key.getKeyTable()[4][key.findCol(currentBigram.getFirst())];
                else
                    first = key.getKeyTable()[key.findRow(currentBigram.getFirst()) - 1][key.findCol(currentBigram.getFirst())];
                if (key.findRow(currentBigram.getSecond()) == 0)
                    second = key.getKeyTable()[4][key.findCol(currentBigram.getSecond())];
                else
                    second = key.getKeyTable()[key.findRow(currentBigram.getSecond()) - 1][key.findCol(currentBigram.getSecond())];

                decryptedPhrase.enqueue(new Bigram(first, second));
            } else {
                char first, second;

                int colFirstEnc = key.findCol(currentBigram.getFirst());
                int colSecondEnc = key.findCol(currentBigram.getSecond());

                first = key.getKeyTable()[key.findRow(currentBigram.getFirst())][colSecondEnc];
                second = key.getKeyTable()[key.findRow(currentBigram.getSecond())][colFirstEnc];

                decryptedPhrase.enqueue(new Bigram(first, second));
            }

            enqueue(currentBigram);
        }

        return decryptedPhrase;
    }

    /**
     * A string representation of the phrase will be returned.
     * @return
     *  Returns the string representation of the phrase.
     * @custom.postcondition
     *  The string of the phrase of bigrams will be returned.
     */
    @Override
    public String toString(){
        String result = "";
        for (int i = 0; i < size(); i++){
            Bigram currentBigram = dequeue();

            result += currentBigram.getFirst();
            result += currentBigram.getSecond();

            enqueue(currentBigram);
        }
        return result;
    }

    /**
     * A static method that does not operate according to an object and will build a phrase object with the given string parameter.
     * @param s
     *  The string that will be used to build a phrase object.
     * @return
     *  Returns the string phrase that was built using the string in the parameter.
     * @custom.precondition
     *  The parameter s must be of String type.
     * @custom.postcondition
     *  The built phrase will be built with the string s and returned.
     */
    public static Phrase buildPhraseFromStringforEnc(String s){
        // removes all spaces, special characters, numbers, and Js from the string
        s = s.replaceAll("\\s", ""); // hello world --> helloworld | rejject
        s = s.replaceAll("[^a-zA-Z]", "");
        s = s.toUpperCase();
        String plainS = "";
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) != 'J')
                plainS += s.charAt(i);
            else
                plainS += 'I';
        }
        Phrase newPhrase = new Phrase();

        // gets rid of consecutive letters
        String newS = "";
        int counter = 0;
        while (counter < plainS.length()){ // hi | hey | hello | (10) helloworld - helxloworldx
            try {
                if (plainS.charAt(counter) == plainS.charAt(counter + 1)) {
                    newS += plainS.charAt(counter) + "X";
                    counter++;
                } else {
                    newS += plainS.charAt(counter);
                    newS += plainS.charAt(counter + 1);
                    counter += 2;
                }
            } catch (StringIndexOutOfBoundsException e){
                newS += plainS.charAt(counter) + "X";
                counter++;
            }
        }

        for (int i = 0; i < newS.length()-1; i+=2){
            Bigram newBigram = new Bigram(newS.charAt(i), newS.charAt(i+1));
            newPhrase.enqueue(newBigram);
        }

        return newPhrase;
    }

    // used for testing purposes.

    /**
     * Used to test the Phrase class.
     * @param args - Not used.
     */
    public static void main(String[] args) {
        Phrase test = buildPhraseFromStringforEnc("hello world");
        KeyTable testKey = KeyTable.buildFromString("playfair example");

        Phrase testEncrypted = test.encrypt(testKey);
        System.out.println(testEncrypted);
        /*
        for (int i = 0; i < test.size(); i++) {
            Bigram testDequeue = test.dequeue();
            test.enqueue(testDequeue);
            System.out.println(testDequeue.getFirst() + "" + testDequeue.getSecond());
        }

         */
    }
}
