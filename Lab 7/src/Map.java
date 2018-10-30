public class Map<Key, Value>
{
    private Key[] keys;
    private Value[] values;
    private int count;

    public Map(int length) {
        if (length < 0) {
            throw new IllegalArgumentException();
        }
        keys = (Key[]) new Object[length];
        values = (Value[]) new Object[length];
    }

    public Value get(Key key) {
        int ind = this.where(key);
        if (ind == -1) throw new IllegalArgumentException();
        else return values[ind];
    }

    private boolean isEqual(Key leftKey, Key rightKey) {
        if (leftKey == rightKey || leftKey.equals(rightKey)) { //null comparison check thingy first
            return true;
        }
        return false;
    }

    public boolean isIn(Key key) {
        if (this.where(key) != -1) return true;
        else return false;
    }

    public void put(Key key, Value value) {

    }

    private int where(Key key) {
        int i = 0;
        for (Key eachKey : keys) {
            if (this.isEqual(eachKey, key)) return i;
            i++;
        }
        return -1;
    }
}
