package homework1;

public class ElementChanger<T> {

    public T[] changeTwoElementsPlaces(final T[] array) {
        T intermediateValue = null;
        for (int i = 0; i < array.length; i += 2) {
            intermediateValue = array[i];
            array[i] = array[i + 1];
            array[i] = intermediateValue;
        }
        return array;
    }

}

