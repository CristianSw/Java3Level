package homework6.homework;


public class IntArray {

    public   int[] checkArrayForFours(int[] array) {
        boolean isFour = false;
        int[] result = null;
        int exceptionHandler = 0;


        for (int i = 0; i < array.length; i++) {
            if (array[i] == 4) {
                exceptionHandler = array[i];
                result = new int[array.length - i - 1];
                for (int j = i + 1, counter = 0; j < array.length; j++, counter++) {
                    if (array[j] == 4) continue;
                    result[counter] = array[j];
                }
            }
        }
        try {
            exceptionHandler = 1 /exceptionHandler;
        }catch (ArithmeticException e){
            throw  new RuntimeException("There is not 4's ");
        }

        return result;
    }


    public  boolean checkArrayForFourAndOne(int[] array){
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1 || array[i] ==4){
                return true;
            }
        }
        return false;
    }
}
