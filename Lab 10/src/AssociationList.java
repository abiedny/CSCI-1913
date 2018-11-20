public class AssociationList<Key, Value> {
    private class Node {
        private Key key;
        private Value value;
        private Node next;

        private Node(Key inKey, Value inValue, Node nextNode) {
            this.key = inKey;
            this.value = inValue;
            this.next = nextNode;
        }
    }

    private Node head;

    public AssociationList() {
        head = new Node(null, null, null);
    }

    public void delete(Key key) {
        Node thisNode = head.next;
        Node lastNode = head;

        for (;;) {
            if (isEqual(thisNode.key, key)) {
                //delet her
                lastNode.next = thisNode.next;
                return;
            }
            if (thisNode.next == null) return;

            lastNode = thisNode;
            thisNode = thisNode.next;
        }
    }

    public Value get(Key key) {
        Node thisNode = head.next;
        if (thisNode == null) throw new IllegalArgumentException();
        for(;;) {
            if (isEqual(thisNode.key, key)) return thisNode.value;
            if (thisNode.next == null) throw new IllegalArgumentException();
            thisNode = thisNode.next;
        }
    }

    private boolean isEqual(Key leftKey, Key rightKey) {
        if (leftKey == null || rightKey == null) {
            return leftKey == rightKey;
        }
        else return leftKey.equals(rightKey);
    }

    public boolean isIn(Key key) {
        Node thisNode = head.next;
        if (thisNode == null) return false;
        for(;;) {
            if (isEqual(thisNode.key, key)) return true;
            if (thisNode.next == null) return false;
            thisNode = thisNode.next;
        }
    }

    public void put(Key key, Value value) {
        Node thisNode = head.next;
        if (thisNode == null) {
            Node oldFirst = head.next;
            head.next = new Node(key, value, oldFirst);
            return;
        }
        for(;;) {
            if (isEqual(thisNode.key, key)) {
                thisNode.value = value;
                return;
            }
            if (thisNode.next == null) {
                Node oldFirst = head.next;
                head.next = new Node(key, value, oldFirst);
                return;
            }
            thisNode = thisNode.next;
        }
    }
}
/* Returns:
false
No null
true
false
true
true
true
false
Lavender
Ginny
null
Wormtail
No Joanne
false
true
null
Ginny
Ginny
Hermione
No Dean
 */
