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
        count = 0;
    }

    public Value get(Key key) {
        int ind = this.where(key);
        if (ind == -1) throw new IllegalArgumentException();
        else return values[ind];
    }

    private boolean isEqual(Key leftKey, Key rightKey) {
        if (leftKey == null || rightKey == null) { //for nulls
            if (leftKey == rightKey) {
                return true;
            }
            return false;
        }
        else if (leftKey.equals(rightKey)) { //now we can safely do equals method
            return true;
        }
        return false;
    }

    public boolean isIn(Key key) {
        if (this.where(key) != -1) return true;
        else return false;
    }

    public void put(Key key, Value value) {
        //see if the key already exists
        int index = this.where(key);

        if (index >= 0) {
            values[index] = value;
        }
        else if (index == -1 && this.count < keys.length) {
            keys[count] = key;
            values[count] = value;
            count++;
        }
        else if (this.count >= keys.length) {
            //no more room!
            throw new IllegalStateException();
        }
    }

    private int where(Key key) {
        for (int i = 0; i < count; i++) {
            if (keys[i] == key) return i;
        }
        return -1;
    }
}
