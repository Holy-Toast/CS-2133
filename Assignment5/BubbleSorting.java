public class BubbleSorting implements Runnable {

    private double[] array;
    long endTime = 0;

    public BubbleSorting(double[] array) {
        this.array = array;
    }

    public void run() {
        bubbleSort(array);
        endTime = System.currentTimeMillis();
    }

    public void bubbleSort(double[] array) {
        int n = array.length;
        for(int i=0; i<n-1; i++) {
            for(int j=0; j< n-i-1; j++) {
                if(array[j] > array[j+1]) {
                    double x = array[j];
                    array[j] = array[j+1];
                    array[j+1] = x;
                }
            }
        }
    }

    public long getEndTime() {
        return endTime;
    }
}
