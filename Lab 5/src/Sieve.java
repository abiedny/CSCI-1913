public class Sieve {
    private boolean[] numbers;

    public Sieve(int max) {
        if (max < 2 ) {
            throw new IllegalArgumentException();
        }
        numbers = new boolean[max];

        //initialize everything but first two elements to true
        for (int i = max; i > 1; i--) {
            numbers[i] = true;
        }
    }

    public void findPrimes() {
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i]) {

            }
        }
    }

    public String toString() {
        //returns index of trues
        String retVal = "";

        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i]) {
                retVal += i + " ";
            }
        }

        return retVal;
    }
}
