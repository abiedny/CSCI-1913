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
        int middle;

        while (low <= high) {
            middle = (low + high)/2;
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
        return -1;
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

/* Program will output:
1 1.0 1.0
2 1.5 1.5
3 2.0 1.6666666666666667
4 2.5 2.0
5 3.0 2.2
6 3.5 2.3333333333333335
7 4.0 2.4285714285714284
8 4.5 2.625
9 5.0 2.7777777777777777
10 5.5 2.9
11 6.0 3.0
12 6.5 3.0833333333333335
13 7.0 3.1538461538461537
14 7.5 3.2142857142857144
15 8.0 3.2666666666666666
16 8.5 3.375
17 9.0 3.4705882352941178
18 9.5 3.5555555555555554
19 10.0 3.6315789473684212
20 10.5 3.7
21 11.0 3.761904761904762
22 11.5 3.8181818181818183
23 12.0 3.869565217391304
24 12.5 3.9166666666666665
25 13.0 3.96
26 13.5 4.0
27 14.0 4.037037037037037
28 14.5 4.071428571428571
29 15.0 4.103448275862069
30 15.5 4.133333333333334
 */