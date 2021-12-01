package homework1;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayToArraylist<T>{

    public ArrayList<T> convertArrayToArrayList(final T[] initArray){
        return new ArrayList<>(Arrays.asList(initArray));
    }
}

