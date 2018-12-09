class PriorityQueue<Base> {
    private class Node {
        private Base object;
        private int rank;
        private Node left;
        private Node right;

        private Node(Base object, int rank) {
            this.object = object;
            this.rank = rank;
            left = null;
            right = null;
        }
    }

    private Node root;

    public PriorityQueue() {
        this.root = new Node(null, -1);
    }

    public Base dequeue() {

    }

    public void enqueue(Base object, int rank) {

    }

    public boolean isEmpty() {

    }
}
