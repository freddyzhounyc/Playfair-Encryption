/**
 * Implements a linked list of bigrams with attributes of data and next.
 *
 * @author Freddy Zhou
 *      Email: freddy.zhou@stonybrook.edu
 *      Stony Brook ID: 116580337
 *      HW #4 - Playfair Encryption
 *      CSE 214
 *      Recitation Number: R04 | TA Names: Veronica Oreshko, Anuj Sureshbhai, Alex Zuzow
 */

public class BigramNode{
    private Bigram data; // The bigram data that is in the node.
    private BigramNode next; // The next reference to the next BigramNode.

    /**
     * Creates an instance of the BigramNode class with no arguments.
     */
    public BigramNode(){
    }

    /**
     * Creates an instance of the BigramNode class with the parameter: data.
     * @param data
     *  The Bigram data value that is going to be a part of this BigramNode.
     * @custom.precondition
     *  Data is of Bigram type.
     * @custom.postcondition
     *  The data of BigramNode will be filled and next will be set to null.
     */
    public BigramNode(Bigram data){
        this.data = data;
        next = null;
    }

    // Getters
    /**
     * Gets the Bigram data of the BigramNode.
     * @return
     *  Returns the Bigram data of the BigramNode.
     * @custom.postcondition
     *  The Bigram data of the BigramNode will be returned.
     */
    public Bigram getData() {
        return data;
    }

    /**
     * Gets the next BigramNode reference.
     * @return
     *  Returns the next BigramNode reference.
     * @custom.postcondition
     *  The next BigramNode reference will be returned.
     */
    public BigramNode getNext() {
        return next;
    }

    // Setters
    /**
     * Sets the data of the BigramNode
     * @param data
     *  The data that will be in the BigramNode.
     * @custom.precondition
     *  Data is of Bigram type.
     * @custom.postcondition
     *  The data of the BigramNode will be returned.
     */
    public void setData(Bigram data) {
        this.data = data;
    }

    /**
     * Sets the next reference of the BigramNode.
     * @param next
     *  The next reference of the BigramNode will be set.
     * @custom.precondition
     *  The next parameter will be of BigramNode type.
     * @custom.postcondition
     *  The next reference parameter is set to a BigramNode.
     */
    public void setNext(BigramNode next) {
        this.next = next;
    }
}
