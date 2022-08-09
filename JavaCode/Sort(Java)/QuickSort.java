package Sort;

import java.util.Arrays;

// 快速排序
public class QuickSort {
    static int[]arry={60,9,2,8,7,3,6,4,5,11,20,9};
    public static void main(String[] args) {
        int l,r,m;
        m=l=0;
        r=arry.length-1;
        quick(l,r);
    }
    public static void quick(int l,int r)
    {
        int right=r;
        int left=l;
        int temp=l;
        if (r<=l)
        {
            return;
        }
        while (r!=l)
        {
            if (arry[temp]>arry[r])
            {
                if (arry[temp]<arry[l])
                {
                    int num=arry[r];
                    arry[r]=arry[l];
                    arry[l]=num;
                }
                else
                {
                    l++;
                }
            }
            else
            {
                r--;
            }
        }
        // 交换temp和l；
        int num=arry[temp];
        arry[temp]=arry[l];
        arry[l]=num;
        System.out.println(Arrays.toString(arry));
        // 递归
        quick(left,l-1);
        quick(l+1,right);
    }
}
