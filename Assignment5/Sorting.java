// The bubble sorting method quickly ran out of time while the merge sorter was able to go another
// round before running out of time.

public class Sorting {
    private double array1[];
    private double array2[];

    public Sorting(int n) {
        array1 = new double[n];
        array2 = new double[n];

        for(int i=0; i<n; i++) {
            double randomDouble = Math.random();
            array1[i] = randomDouble;
            array2[i] = randomDouble;
        }

        long startTime;
        long endTime = 0;
        long timeElapsed;
        try {
            startTime = System.currentTimeMillis();
            long willEnd = startTime + 20000;
            BubbleSorting bs = new BubbleSorting(array1);
            Thread bubble = new Thread(bs);
            bubble.start();
            while(willEnd > System.currentTimeMillis() && endTime == 0) {
                endTime = bs.getEndTime();
            }
            try {
                bubble.join();
            } catch(Exception e) {
                e.printStackTrace();
            }
            timeElapsed = endTime - startTime;
            if(endTime == 0) {
                System.out.println("Ran out of time!");
            }
            else {
                System.out.println("Bubble sorting " + n + " numbers took " + timeElapsed + " ms");
            }
        } catch(OutOfMemoryError e) {
            System.out.println("Out of memory!");
        }

        try {
            startTime = System.currentTimeMillis();
            long willEnd = startTime + 20000;
            MergeSorting m = new MergeSorting(array2);
            Thread merge = new Thread(m);
            endTime = 0;
            merge.start();
            while(willEnd > System.currentTimeMillis() && endTime == 0) {
                endTime = m.getEndTime();
            }
            try {
                merge.join();
            } catch(Exception e) {
                e.printStackTrace();
            }
            timeElapsed = endTime - startTime;
            if(endTime == 0) {
                System.out.println("Ran out of time!");
            }
            else {
                System.out.println("Merge sorting " + n + " numbers took " + timeElapsed + " ms");
            }
        } catch(OutOfMemoryError e) {
            System.out.println("Out of memory!");
        }
    }

    public static void main(String[] args) {
        for(int i=10; i<100000; i*=10) {
            new Sorting(i);
        }
    }
}
