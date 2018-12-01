import com.sun.istack.internal.Nullable;

class Poly {
    private class Term {
        private int coef;
        private int expo;
        private Term next;

        private Term(int inCo, int inExp, Term inNext) {
            this.coef = inCo;
            this.expo = inExp;
            this.next = inNext;
        }
    }

    private Term first;
    private Term last;

    public Poly() {
        first = new Term(42, Integer.MAX_VALUE, null);
        last = first;
    }

    public boolean isZero() {
        if (first.next == null) return true;
        return false;
    }

    public Poly minus() {
        Poly result = new Poly();
        Term temp = this.first.next; //the first real term
        while (temp != null) {
            result.plus(temp.coef * -1, temp.expo);
            temp = temp.next;
        }
        return result;
    }

    public Poly plus(Poly that) {
        Poly result = new Poly();
        @Nullable Term left = this.first.next;
        @Nullable Term right = that.first.next;
        while (left != null && right != null) {
            if (left.expo > right.expo) {
                result.plus(left.coef, left.expo); //Uhh is this too lazy??
                left = left.next;
            }
            else if (right.expo > left.expo) {
                result.plus(right.coef, right.expo);
                right = right.next;
            }
            else if (left.expo == right.expo) {
                int coSum = left.coef + right.coef;
                if (coSum != 0) {
                    result.plus(coSum, left.expo);
                }
                left = left.next;
                right = right.next;
            }
        }
        //we need to add the rest of the not null term to result
        if (left != null) {
            result.last.next = left;
        }
        if (right != null) {
            result.last.next = right;
        }
        return result;
    }

    public Poly plus(int coef, int expo) {
        if (coef == 0 || expo < 0 || expo >= last.expo) throw new IllegalArgumentException();
        //ok now we can make a new term
        Term tt = new Term(coef, expo, null);
        last.next = tt; //current last
        last = tt; //the new last
        return this;
    }

    //TODO: Make this function not be spaghetti plz
    public String toString() {
        StringBuilder retVal = new StringBuilder();

        if (this.isZero()) {
            retVal.append("0");
            return retVal.toString();
        }

        Term interTerm = first.next;
        Term lastTerm = null;
        while (interTerm != null) {
            if (lastTerm != null && interTerm.coef > 0) {
                retVal.append(" + ");
                retVal.append(interTerm.coef);
            }
            else if (lastTerm != null && interTerm.coef < 0) {
                retVal.append(" - ");
                retVal.append(interTerm.coef * -1);
            }
            else if (lastTerm == null) {
                retVal.append(interTerm.coef);
            }
            retVal.append("x");
            this.appendExpo(retVal, interTerm.expo);

            lastTerm = interTerm;
            interTerm = interTerm.next;
        }
        return retVal.toString();
    }

    private void appendExpo(StringBuilder builder, int expo) {
        if (expo == 0) {
            builder.append('⁰');
        }
        else {
            appendingExpo(builder, expo);
        }
    }

    private void appendingExpo(StringBuilder builder, int expo) {
        if (expo > 0) {
            appendingExpo(builder, expo / 10);
            builder.append("⁰¹²³⁴⁵⁶⁷⁸⁹".charAt(expo % 10));
        }
    }
}

class Driver {
    public static void main(String[] args) {
        Poly p = new Poly().plus(3,5).plus(2,4).plus(2,3).plus(-1,2).plus(5,0);
        Poly q = new Poly().plus(7,4).plus(1,2).plus(-4,1).plus(-3,0);
        Poly z = new Poly();
        Poly m = new Poly().plus(9, 8);

        System.out.println(p);                 // 3x⁵ + 2x⁴ + 2x³ - 1x² + 5x⁰
        System.out.println(q);                 // 7x⁴ + 1x² - 4x¹ - 3x⁰
        System.out.println(z);                 // 0

        System.out.println(p.minus());         // -3x⁵ - 2x⁴ - 2x³ + 1x² - 5x⁰
        System.out.println(q.minus());         // -7x⁴ - 1x² + 4x¹ + 3x⁰
        System.out.println(z.minus());         // 0

        System.out.println(p.plus(q));         // 3x⁵ + 9x⁴ + 2x³ - 4x¹ + 2x⁰
        System.out.println(p.plus(z));         // 3x⁵ + 2x⁴ + 2x³ - 1x² + 5x⁰
        System.out.println(p.plus(q.minus())); // 3x⁵ - 5x⁴ + 2x³ - 2x² + 4x¹ + 8x⁰

        System.out.println(m.minus());         //-9x8
        System.out.println(m);                 //9x8

        System.out.println(m.plus(m));         //18x8
        System.out.println(m.plus(m).minus()); //-18x8
    }
}
/* Output:
3x⁵ + 2x⁴ + 2x³ - 1x² + 5x⁰
7x⁴ + 1x² - 4x¹ - 3x⁰
0
-3x⁵ - 2x⁴ - 2x³ + 1x² - 5x⁰
-7x⁴ - 1x² + 4x¹ + 3x⁰
0
3x⁵ + 9x⁴ + 2x³ - 4x¹ + 2x⁰
3x⁵ + 2x⁴ + 2x³ - 1x² + 5x⁰
3x⁵ - 5x⁴ + 2x³ - 2x² + 4x¹ + 8x⁰
-9x⁸
9x⁸
18x⁸
-18x⁸
 */