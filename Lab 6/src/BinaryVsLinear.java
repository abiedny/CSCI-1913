public class BinaryVsLinear
{

    private static int linearSearch(int key, int[] keys)
    {
        int i = 0;

        for (int eachKey : keys) {
            if (eachKey == key) {
                break;
            }
            i++;
        }
        return i + 1;
    }

    private static int binarySearch(int key, int[] keys)
    {
        int low = 0;
        int high = keys.length - 1;
        int i = 0;

        while (high > low) {
            int middle = (low + high)/2;
            if (keys[middle] == key) {
                return i + 1;
            }
            if (keys[middle] < key) {
                low = middle + 1;
            }
            if (keys[middle] > key) {
                high = middle - 1;
            }
            i++;
        }
        return 0;
    }

    public static void main(String[] args)
    {
        for (int length = 1; length <= 30; length += 1)
        {
            int[] array = new int[length];
            for (int index = 0; index < length; index += 1)
            {
                array[index] = index;
            }

            double linearTotal = 0.0;
            double binaryTotal = 0.0;
            for (int element = 0; element < length; element += 1)
            {
                linearTotal += linearSearch(element, array);
                binaryTotal += binarySearch(element, array);
            }

            double linearAverage = linearTotal / length;
            double binaryAverage = binaryTotal / length;
            System.out.println(length + " " + linearAverage + " " + binaryAverage);
        }
    }
}