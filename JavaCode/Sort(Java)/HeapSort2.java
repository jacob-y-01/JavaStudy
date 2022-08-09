package Sort;

import javax.swing.*;
import java.util.Arrays;

public class HeapSort2 {
    public static void main(String[] args) {
        int[]arry={60,9,2,8,7,3,6,4,5,11,20,9};

        //通过新插入的数上升
        for (int i=0;i<arry.length;i++) {
            int t1=i;
            int t2=(i-1)/2;
            while (arry[t1]>arry[t2])
            {
                // 大根堆，如果子大于父,则交换
                int t=arry[t1];
                arry[t1]=arry[t2];
                arry[t2]=t;

                //***************************************
                // 然后再把当前节点指向父，看父以上还有没有比它小的
                // 为什么是-1除2，而不是-2/2？因为奇数/2
                // 两个子节点-1/2得到的是同一个父节点
                //***************************************
                t1=t2;
                t2=(t1-1)/2;
            }
        }
        System.out.println(Arrays.toString(arry));


        // 将根节点与最后一个数值交换,再与倒数第二个数交换............数组逐渐有序
        // 取出最大的放到数组尾部，再把剩下的重新构堆，重复
        int size = arry.length;
        while (size > 1) {
            int t=arry[0];
            arry[0]= arry[size - 1];
            arry[size - 1]=t;

            size--;
            //重新构造构造大根堆

            // index和其的左右儿子下标
            int index=0;
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            // 最后一个节点肯定是左节点，它不可以越界
            while (left < size) {
                int largestIndex;
                // 根的左右节点谁更大？把它放到largestIndex中
                if (arry[left] < arry[right] && right < size) {
                    largestIndex = right;
                } else {
                    largestIndex = left;
                }

                //比较父结点的值与孩子中较大的值，并确定最大值的索引
                if (arry[index] > arry[largestIndex]) {
                    largestIndex = index;
                }
                //如果父结点索引是最大值的索引，那已经是大根堆了，则退出循环
                if (index == largestIndex) {
                    break;
                }


                //父结点不是最大值，与孩子中较大的值交换
                int t1= arry[largestIndex];
                arry[largestIndex]=arry[index];
                arry[index]=t1;

                //将索引指向孩子中较大的值的索引
                index = largestIndex;
                //重新计算交换之后的孩子的索引
                left = 2 * index + 1;
                right = 2 * index + 2;
            }
        }
        System.out.println(Arrays.toString(arry));
    }
}
