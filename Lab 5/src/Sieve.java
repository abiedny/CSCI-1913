public class Sieve {
    private boolean[] numbers;

    public Sieve(int max) {
        if (max < 2 ) {
            throw new IllegalArgumentException();
        }
        numbers = new boolean[max];

        //initialize everything but first two elements to true
        for (int i = max - 1; i > 1; i--) {
            numbers[i] = true;
        }
    }

    public void findPrimes() {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i]) {
                for (int j = i + 1; j < numbers.length; j++) {
                    if (j % i == 0) {
                        numbers[j] = false;
                    }
                }
            }
        }
    }

    public String toString() {
        //returns index of trues
        StringBuilder retVal = new StringBuilder();

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i]) {
                retVal.append(i).append(" ");
            }
        }

        return retVal.toString();
    }
}
