public class MergeSorting implements Runnable {

    private double[] array;
    long endTime = 0;

    public MergeSorting(double[] array) {
        this.array = array;
    }

    public void run() {
        sort(array, 0, array.length-1);
        endTime = System.currentTimeMillis();
    }

    public void merge(double[] array, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        double L[] = new double[n1];
        double R[] = new double[n2];

        for(int i=0; i<n1; i++) {
            L[i] = array[l+i];
        }
        for(int i=0; i<n2; i++) {
            R[i] = array[m + 1 + i];
        }

        int i=0;
        int j=0;
        int k = l;
        while(i < n1 && j < n2) {
            if(L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            }
            else {
                array[k] = R[j];
                j++;
            }
            k++;
        }
        while(i<n1) {
            array[k] = L[i];
            i++;
            k++;
        }
        while(j<n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    public void sort(double[] array, int l, int n) {
        if(l < n) {
            int m = (l+n)/2;

            sort(array, l, m);
            sort(array, m+1, n);

            merge(array, l, m, n);
        }
    }

    public long getEndTime() {
        return endTime;
    }
}
