package algorithms;

public class DoublyLinkedListNode<E> {
    DoublyLinkedListNode<E> prev;
    DoublyLinkedListNode<E> next;
    E element;

    public DoublyLinkedListNode(  E element) {
        this.prev = null;
        this.next = null;
        this.element = element;
    }

    public DoublyLinkedListNode<E> getPrev() {
        return prev;
    }

    public void setPrev(DoublyLinkedListNode<E> prev) {
        this.prev = prev;
    }

    public DoublyLinkedListNode<E> getNext() {
        return next;
    }

    public void setNext(DoublyLinkedListNode<E> next) {
        this.next = next;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }
}
