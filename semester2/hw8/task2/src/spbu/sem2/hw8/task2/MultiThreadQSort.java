package spbu.sem2.hw8.task2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/** represents multi-thread quick sorter */
public class MultiThreadQSort extends QuickSort {
    @Override
    public void sort(int[] array, int left, int right) {
        new ForkJoinPool().invoke(new MultiQSort(array, left, right));
    }

    private class MultiQSort extends RecursiveAction {
        private int[] array;
        private int left;
        private int right;

        private MultiQSort(int[] array, int left, int right) {
            this.array = array;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (left >= right) {
                return;
            }

            int pivot = partition(array, left, right);
            MultiQSort leftTask = new MultiQSort(array, left, pivot);
            MultiQSort rightTask = new MultiQSort(array, pivot + 1, right);

            leftTask.fork();
            rightTask.fork();

            leftTask.join();
            rightTask.join();
        }
    }
}
