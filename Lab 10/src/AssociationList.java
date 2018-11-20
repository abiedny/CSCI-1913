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
        Node thisNode = head;
        for(;;) {
            if (isEqual(thisNode.key, key)) {
                //TODO: Deletey-bois
            }
            thisNode = thisNode.next;
        }
    }

    public Value get(Key key) {

    }

    private boolean isEqual(Key leftKey, Key rightKey) {]
        if (leftKey == null || rightKey == null) {
            return leftKey == rightKey;
        }
        else return leftKey.equals(rightKey);
    }

    public boolean isIn(Key key) {

    }

    public void put(Key key, Value value) {

    }
}
