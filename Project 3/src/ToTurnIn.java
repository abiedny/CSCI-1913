import java.util.Random;

class ShuffleTree<Value> {
    private class Node {
        private String key;
        private Value value;
        private Node left, right;

        private Node(String inKey, Value inValue, Node inLeft, Node inRight) {
            key = inKey;
            value = inValue;
            left = inLeft;
            right = inRight;
        }
    }

    private String[] keys;
    private Value[] values;
    private int count;
    private int height;
    private Node root;
    private Random generator;

    public ShuffleTree(int size) {
        if (size < 0) throw new IllegalArgumentException();

        this.keys = new String[size];
        this.values = (Value[]) new Object[size];
        this.count = 0;
        this.root = new Node("zzzzzzzzzzzzz", null, null, null);
        this.generator = new Random();
        this.height = 0; //just the root
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
        //also keep track of tree height its a weird way but I promise it works
        int localHeight;
        for (int i = 0; i < count; i++) {
            localHeight = this.add(root, keys[i], values[i], 1);
            this.height = (localHeight > height) ? localHeight : height;
        }
        //empty arrays so all elements are null
        //just reinitialize cuz neither are primitive types lol
        keys = new String[keys.length];
        values = (Value[]) new Object[keys.length];
        this.count = 0; //and then reset count
    }

    private int add(Node inRoot, String inKey, Value inVal, int localHeight) {
        //also we keep track of the height placing each value
        int cval = inKey.compareToIgnoreCase(inRoot.key);

        if (cval == 0 ) throw new IllegalStateException(); //throw illegalstate if key is on BST when u r to add it.
        else if (cval < 0) {
            //key should go left, is before the current node alphabetically
            if (inRoot.left == null) inRoot.left = new Node(inKey, inVal, null, null);
            else {
                localHeight++;
                return this.add(inRoot.left, inKey, inVal, localHeight);
            }
        }
        else if (cval > 0) {
            //key should go right, is after the current node alphabetically
            if (inRoot.right == null) inRoot.right = new Node(inKey, inVal, null, null);
            else {
                localHeight++;
                return this.add(inRoot.right, inKey, inVal, localHeight);
            }
        }
        return localHeight;
    }

    public Value get(String key) {
        if (count != 0) this.flush();
        try {
            return this.get(key, this.root);
        }
        catch (NullPointerException e) {
            //if the helper throws a null pointer it means that the helper tried to access the left or right or a null node, meaning key doesn't exist
            throw new IllegalArgumentException();
        }
    }

    private Value get(String key, Node inRoot) {
        int cval = key.compareToIgnoreCase(inRoot.key);
        if (cval == 0) return inRoot.value; //found the matching key
        else if (cval < 0) return this.get(key, inRoot.left); //key will be left
        else if (cval > 0) return this.get(key, inRoot.right); //key will be right
        else return null; //just so intellij will shut up. function will throw nullptr before getting here
    }

    public int height() {
        if (count != 0) this.flush();
        return this.height;
    }

    public void put(String key, Value value) {
        if (key == null) throw new IllegalArgumentException();
        if (keys.length == count) this.flush();

        keys[count] = key;
        values[count] = value;
        count++;
    }

}

//  SHUFFLE BORED. Test the SHUFFLE TREE class.

class ShuffleBored
{

//  RESERVED. A sorted array of some Java reserved names.

    private final static String[] reserved =
            { "abstract",     "assert",    "boolean",     "break",
                    "byte",         "case",      "catch",       "char",
                    "class",        "const",     "continue",    "default",
                    "do",           "double",    "else",        "extends",
                    "final",        "finally",   "float",       "for",
                    "goto",         "if",        "implements",  "import",
                    "instanceof",   "int",       "interface",   "long",
                    "native",       "new",       "package",     "private",
                    "protected",    "public",    "return",      "short",
                    "static",       "super",     "switch",      "synchronized",
                    "this",         "throw",     "throws",      "transient",
                    "try",          "void",      "volatile",    "while" };

//  MAIN. Main program. Make a SHUFFLE TREE, whose keys are RESERVED names, and
//  whose values are INTs. Print its height, keys, and their values.

    public static void main(String[] args)
    {
        ShuffleTree<Integer> tree = new ShuffleTree<Integer>(30);

        for (int index = 0; index < reserved.length; index += 1)
        {
            tree.put(reserved[index], index);
        }

        System.out.println(tree.height());

        for (int index = 0; index < reserved.length; index += 1)
        {
            System.out.format("%02d %s", tree.get(reserved[index]), reserved[index]);
            System.out.println();
        }
    }
}
/*OUTPUT: (This is the output from one run. The first line showing the height of the tree is usually different
every time the program is ran.
10
00 abstract
01 assert
02 boolean
03 break
04 byte
05 case
06 catch
07 char
08 class
09 const
10 continue
11 default
12 do
13 double
14 else
15 extends
16 final
17 finally
18 float
19 for
20 goto
21 if
22 implements
23 import
24 instanceof
25 int
26 interface
27 long
28 native
29 new
30 package
31 private
32 protected
33 public
34 return
35 short
36 static
37 super
38 switch
39 synchronized
40 this
41 throw
42 throws
43 transient
44 try
45 void
46 volatile
47 while
 */