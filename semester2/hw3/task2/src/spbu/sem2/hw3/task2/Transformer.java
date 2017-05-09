package spbu.sem2.hw3.task2;

public class Transformer {
    public void transform(int[][] array, int[] result) {
        int resultPointer = 0;
        int i = 0;
        int j = 0;
        int currentStep = 2;

        int numberOfCircles = array.length / 2;
        result[resultPointer] = array[numberOfCircles][numberOfCircles];
        resultPointer++;
        i = numberOfCircles + 1;
        j = numberOfCircles;
        for (int k = 0; k < numberOfCircles; k++) {
            for (int counter = 0; counter < currentStep; counter++) {
                result[resultPointer] = array[i][j];
                resultPointer++;
                j--;
            }
            j++;
            i--;

            for (int counter = 0; counter < currentStep; counter++) {
                result[resultPointer] = array[i][j];
                resultPointer++;
                i--;
            }
            i++;
            j++;

            for (int counter = 0; counter < currentStep; counter++) {
                result[resultPointer] = array[i][j];
                resultPointer++;
                j++;
            }
            j--;
            i++;

            for (int counter = 0; counter < currentStep; counter++) {
                result[resultPointer] = array[i][j];
                resultPointer++;
                i++;
            }
            currentStep += 2;
        }
    }
}
