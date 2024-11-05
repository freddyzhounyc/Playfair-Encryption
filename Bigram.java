/**
 * The Bigram class represents a bigram which has two values where it will eventually be used for the encryption or decryption.
 *
 * @author Freddy Zhou
 */

public class Bigram{
    private char first, second; // The character variables that represent the two characters a part of a bigram.

    /**
     * Creates an instance of the Bigram class with no arguments.
     * @custom.postcondition
     *  An instance of the Bigram class will be created.
     */
    public Bigram(){
    }

    /**
     * Creates an instance of the Bigram class with two arguments: first and second.
     * @param first
     *  The first character a part of the bigram.
     * @param second
     *  The second character a part of the bigram.
     * @custom.precondition
     *  The first and second characters in the parameter must be of character type.
     * @custom.postcondition
     *  The first and second characters of the bigram will be filled.
     */
    public Bigram(char first, char second){
        this.first = first;
        this.second = second;
    }

    // Getters
    /**
     * Gets the first character a part of the bigram.
     * @return
     *  Returns the first character a part of the bigram.
     * @custom.postcondition
     *  The first character of the bigram will be returned.
     */
    public char getFirst() {
        return first;
    }

    /**
     * Gets the second character a part of the bigram.
     * @return
     *  Returns the second character a part of the bigram.
     * @custom.postcondition
     *  The second character of the bigram will be returned.
     */
    public char getSecond() {
        return second;
    }

    // Setters
    /**
     * The first character of the bigram will be set.
     * @param first
     *  The first character that wants to be set a part of the bigram.
     * @custom.precondition
     *  The character "first" must be of character type.
     * @custom.postcondition
     *  The instance variable "first" will be filled.
     */
    public void setFirst(char first) {
        this.first = first;
    }

    /**
     * The second character of the bigram will be set.
     * @param second
     *  The second character that wants to be set a part of the bigram.
     * @custom.precondition
     *  The character "second" must be of character type.
     * @custom.postcondition
     *  The instance variable "second" will be filled.
     */
    public void setSecond(char second) {
        this.second = second;
    }

    /**
     * The string representation of the bigram.
     * @return
     *  Returns the string representation of the bigram.
     * @custom.postcondition
     *  The string of the bigram will be returned.
     */
    @Override
    public String toString(){
        return first + Character.toString(second);
    }
}
