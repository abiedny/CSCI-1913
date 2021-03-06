import com.sun.istack.internal.Nullable;

public class RunnyStack<Base> {
    private class Run {
        private Base base;
        private int length;
        private @Nullable Run next;

        private Run(Base inBase, int inLength, Run inNext) {
            this.base = inBase;
            this.length = inLength;
            this.next = inNext;
        }
    }

    private @Nullable Run currentTop;
    private int depth; //total objects
    private int runs; //total runs

    public RunnyStack() {
        this.currentTop = null;
        this.depth = 0;
        this.runs = 0;
    }
    public int depth() {
        return this.depth;
    }
    public boolean isEmpty(){
        if (depth == 0) return true;
        else return false;
    }
    public Base peek() {
        if ( this.isEmpty() ) throw new IllegalStateException();
        return currentTop.base;
    }
    public void pop() {
        if ( this.isEmpty() ) throw new IllegalStateException();
        if (currentTop.length > 1) {
            currentTop.length--;
        }
        else {
            currentTop = currentTop.next;
            this.runs--;
        }
        this.depth--;
    }
    public void push(Base base) {
        if ( this.isEmpty()) {
            currentTop = new Run(base, 1, null);
            this.runs++;
        }
        else {
            //there's already a base on the stack
            if (isEqual(currentTop.base, base)) {
                currentTop.length++;
            }
            else {
                currentTop = new Run(base, 1, currentTop);
                this.runs++;
            }
        }
        this.depth++;
    }
    public int runs() {
        //this should be the number of Runs on the stack, ignore length of them
        return this.runs;
    }

    private boolean isEqual(Base base1, Base base2) {
        if (base1 == null || base2 == null) return base1 == base2;
        return base1.equals(base2);
    }
}
/* Returns:
true
0
0
No pop
No peek
A
1
1
false
B
2
2
B
3
2
B
4
2
C
5
3
C
6
3
C
5
3
B
4
2
B
3
2
A
1
1
true
0
0
No peek
 */
