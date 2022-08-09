package Sort;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[]arry={60,9,2,8,7,3,6,4,5,11,20,9};
        // 每次选择最大或者最小与首位交换
        int minset;
        for (int i=0;i<arry.length;i++)
        {
            minset=i;
            for (int j=i;j<arry.length;j++)
            {
                if (arry[minset]>arry[j])
                {
                    minset=j;
                }
            }
            int temp=arry[i];
            arry[i]=arry[minset];
            arry[minset]=temp;
            //System.out.println(Arrays.toString(arry));
        }
        System.out.println(Arrays.toString(arry));
    }
}
