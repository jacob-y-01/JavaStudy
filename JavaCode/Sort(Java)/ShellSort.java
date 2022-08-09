package Sort;
// 希尔排序是插入排序的变种
// 插入排序：数据有序度越高，时间复杂度越低。完全有序的数组，就根本不赋值
// 一是插入排序在对近似有序的数列进行排序时，排序性能会比较好；二是插入排序的性能比较低效，即每次只能将数据移动一位。

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[]arry={60,90,2,8,7,3,6,4,5,11,20,9};
        int gap;
        // gap不为1，且gap每次除2
        // gap组
        for (gap=arry.length/2;gap>0;gap=gap/2)
        {
            if (gap==1)
            {
                System.out.println("gap==1，进行最后一次插入排序");
            }
            // gap组
            for (int i=0; i<gap;i++) {

                // 每组中有多少个数
                // 每组中数值下标
                int b=i;
                for (int j = 0; j < arry.length / gap-1; j++,b=b+gap) {

                    //单趟排
                    int t=b;
                    while (b >= 0)
                    {
                        //比插入的数大就向后移
                        if (arry[b + gap] < arry[b])
                        {
                            System.out.println(Arrays.toString(arry));
                            System.out.println(b+"和"+(b+gap)+"互换");
                            System.out.println(arry[b]+"和"+arry[b+gap]+"互换");
                            int temp= arry[b+gap];
                            arry[b + gap] = arry[b];
                            arry[b]=temp;
                            b=b-gap;
                            System.out.println(Arrays.toString(arry));
                        }
                        else
                        {
                            break;
                        }
                    }
                    b=t;
                }
            }
        }
    }
}
