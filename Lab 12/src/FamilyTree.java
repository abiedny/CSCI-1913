class FamilyTree {
    private class Node {
        private String name;
        private Node father, mother;

        private Node(String inName, Node inMom, Node inDad) {
            this.name = inName;
            this.mother = inMom;
            this.father = inDad;
        }
    }

    private Node root;

    public FamilyTree(String ego) {
        this.root = new Node(ego, null, null);
    }

    private Node find(String name) {
        return this.find(name, this.root);
    }

    //spaghetti alert
    private Node find(String name, Node root) {
        if (root.name.equals(name)) return root; //the first thing on a node we do is a check so don't worry bout that
        //this is the end of the tree
        // if it's the end and we've found nothing, throw a null down the call stack, and the previous recursion will deal with it
        if (root.father == null) return null;
        Node shifted = find(name, root.father);
        //if the previous recursion returns null, check the mother stuff from this node
        if (shifted == null) return find(name, root.mother);
        //shifted returns something, which means a match was found. get that boi back to the original call
        else return shifted;
    }

    public void addParents(String ego, String father, String mother) {
        Node theEgo = this.find(ego);
        if (theEgo == null) throw new IllegalArgumentException();
        else {
            theEgo.father = new Node(father, null, null);
            theEgo.mother = new Node(mother, null, null);
        }
    }

    public boolean isDescendant(String ego, String ancestor) {
        Node egoNode = find(ego);
        Node anNode = find(ancestor);
        if (egoNode == null || anNode == null) return false;
        return isDescendant(egoNode, anNode);
    }

    private boolean isDescendant(Node root, Node ancestor) {
        Node desc = find(ancestor.name, root);
        return desc != null;
    }
}
/*Will return:
No Joanne.
false
true
true
true
true
true
true
true
false
true
false
true
true
false
false
false
false
true
false
*/