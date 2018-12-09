import java.util.Random;

class ShuffleTree<Value> {
    private class Node {
        private int key;
        private Value value;
        private Node left, right;

        private Node(int inKey, Value inValue, Node inLeft, Node inRight) {
            key = inKey;
            value = inValue;
            left = inLeft;
            right = inRight;
        }
    }

    private String[] keys;
    private Value[] values;
    private int count;
    private Node root;
    private Random generator;

    public ShuffleTree(int size) {
        if (size < 0) throw new IllegalArgumentException();

        this.keys = new String[size];
        this.values = (Value[]) new Object[size];
        this.count = 0;
        this.root = new Node(-1, null, null, null);
        this.generator = new Random();
    }

    private void flush() {
        //shuffle arrays using algo and generator
        for (int i = 0; i <= count - 2; i++) {
            int j = generator.nextInt(count - i);
            String tempK = keys[i];
            Value tempV = values[i];
            keys[i] = keys[i + j];
            values[i] = values[i + j];
            keys[i + j] = tempK;
            values[i + j] = tempV;
        }

        //add k-v pairs from the arrays in order of appearance to bst at root
        //throw illegalstate if key is on BST when u r to add it.
        //empty arrays so all elements are null
    }

    public Value get(String key) {
        if (count != 0) this.flush();
        //TODO: Search the tree for key and return that value;
    }

    public int height() {
        if (count != 0) this.flush();
        //TODO: Find and return height;
    }

    public void put(String key, Value value) {
        if (key == null) throw new IllegalArgumentException();
        if (keys.length == count) this.flush();

        keys[count] = key;
        values[count] = value;
    }

}
