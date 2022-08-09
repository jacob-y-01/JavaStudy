package Sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[]arry={60,9,2,8,7,3,6,4,5,11,20,9};
        for (int i=0;i<arry.length;i++)
        {
            for (int j=0;j<arry.length-1;j++)
            {
                if (arry[j]>arry[j+1])
                {
                    int temp=arry[j];
                    arry[j]=arry[j+1];
                    arry[j+1]=temp;
                }
            }
            System.out.println(Arrays.toString(arry));
        }

    }
}
