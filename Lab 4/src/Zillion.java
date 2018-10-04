public class Zillion {
    private int[] number;

    public Zillion(int size) {
        this.number = new int[size];
    }
    public void increment() {
        int index = number.length - 1;
        while (true) {
            if (number[index] < 9) {
                number[index]++;
                break;
            }
            else if (number[index] == 9) {
                number[index] = 0;
                index--;
                if (index < 0) break;
            }
        }
    }
    public String toString() {
        String retVal = "";

        for (int eachVal : number) {
            retVal += eachVal;
        }

        return retVal;
    }
}