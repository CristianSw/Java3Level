package homework6.homework;


public class IntArray {

    public static int[] checkArrayForFours(int[] array) {
        boolean isFour = false;
        int[] result = null;


        for (int i = 0; i < array.length; i++) {
            if (array[i] == 4) {
                isFour = true;
            }
            if (isFour) {
                result = new int[array.length - i - 1];
                for (int j = i + 1, counter = 0; j < array.length; j++, counter++) {
                    if (array[j] == 4) continue;
                    result[counter] = array[j];
                }
            }
        }
        if (!isFour) {
            throw new RuntimeException("Provided array dont have 4's");
        }
        return result;
    }


    public static boolean checkArrayForFourAndOne(int[] array){
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1 || array[i] ==4){
                return true;
            }
        }
        return false;
    }
}
