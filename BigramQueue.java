/**
 * Implementation of a queue that uses a linked list of BigramNodes.
 *
 * @author Freddy Zhou
 *      Email: freddy.zhou@stonybrook.edu
 *      Stony Brook ID: 116580337
 *      HW #4 - Playfair Encryption
 *      CSE 214
 *      Recitation Number: R04 | TA Names: Veronica Oreshko, Anuj Sureshbhai, Alex Zuzow
 */

public class BigramQueue {
    private BigramNode head; // head of the linked list acting as a queue.
    private BigramNode tail; // tail of the linked list acting as a queue.
    private int size; // size of the queue.

    // Getters
    /**
     * Gets the head of the linked list acting as a queue.
     * @return
     *  Returns the head of the linked list acting as a queue.
     * @custom.postcondition
     *  The head of the linked list will be returned.
     */
    public BigramNode getHead() {
        return head;
    }

    /**
     * Gets the tail of the linked list acting as a queue.
     * @return
     *  Returns the tail of the linked list acting as a queue.
     * @custom.postcondition
     *  The tail of the linked list will be returned.
     */
    public BigramNode getTail() {
        return tail;
    }

    // Setters
    /**
     * Sets the head of the linked list to the parameter.
     * @param head
     *  Head of the linked list.
     * @custom.precondition
     *  The parameter, head, is of BigramNode type.
     * @custom.postcondition
     *  The head instance variable will be filled with parameter.
     */
    public void setHead(BigramNode head) {
        this.head = head;
    }

    /**
     * Sets the tail of the linked list to the parameter.
     * @param tail
     *  Tail of the linked list.
     * @custom.precondition
     *  The paramter, tail, is of BigramNode type.
     * @custom.postcondition
     *  The tail instance variable will be filled with parameter.
     */
    public void setTail(BigramNode tail) {
        this.tail = tail;
    }

    /**
     * Adds the bigram to the end of the linked list.
     * @param bigram
     *  The bigram that is to be added to the end of the linked list.
     * @custom.precondition
     *  The bigram parameter should be of bigram type.
     * @custom.postcondition
     *  The bigram parameter will be added to the list.
     */
    public void enqueue(Bigram bigram){
        if (head == null){
            BigramNode newBigramNode = new BigramNode(bigram);
            head = newBigramNode;
            tail = newBigramNode;
            size = 1;
        } else{
            BigramNode newBigramNode = new BigramNode(bigram);
            tail.setNext(newBigramNode);
            tail = newBigramNode;
            size++;
        }
    }

    /**
     * The front of the queue will be removed from the linked list and returned.
     * @return
     *  The bigram that was at the front of the queue will be returned.
     * @throws IllegalArgumentException
     *  Thrown if the list is empty and contains no BigramNodes.
     * @custom.precondition
     *  The list is not empty.
     * @custom.postcondition
     *  The front of the queue will be removed and front will be adjusted accordingly and the removed node will be returned.
     */
    public Bigram dequeue() throws IllegalArgumentException{
        if (size == 0)
            throw new IllegalArgumentException();
        BigramNode result = head;
        head = head.getNext();
        size--;
        return result.getData();
    }

    /**
     * The front node of the queue will be returned, but not removed.
     * @return
     *  Returns the front node of the queue.
     * @custom.postcondition
     *  The front node will be returned.
     */
    public Bigram peek(){
        return head.getData();
    }
}
