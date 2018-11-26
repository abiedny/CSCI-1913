public class Deque<Base> {
    private class Node {
        private Base object;
        private Node left;
        private Node right;

        private Node(Base inObj, Node inLeft, Node inRight) {
            this.object = inObj;
            this.left = inLeft;
            this.right = inRight;
        }
    }

    private Node head;

    public Deque() {
        this.head = new Node(null, null, null);
        this.head.left = this.head;
        this.head.right = this.head;
    }

    public void enqueueFront(Base object) {
        Node currentFront = head.left;
        currentFront.right = new Node(object, currentFront, head);
        currentFront.right.right.left = currentFront.right;
        return;
    }

    public void enqueueRear(Base object) {
        Node currentRear = head.right;
        currentRear.left = new Node(object, head, currentRear);
        currentRear.left.left.right = currentRear.left;
        return;
    }

    public Base dequeueFront() {
        if (isEmpty()) throw new IllegalStateException();
        Node currentFront = head.left;
        Base retVal = currentFront.object;
        currentFront.left.right = head;
        currentFront.right.left = currentFront.left;
        return retVal;
    }

    public Base dequeueRear() {
        if (isEmpty()) throw new IllegalStateException();
        Node currentRear = head.right;
        Base retVal = currentRear.object;
        currentRear.right.left = head;
        currentRear.left.right = currentRear.right;
        return retVal;
    }

    public boolean isEmpty() {
        return (head.left == head || head.right == head);
    }
}
