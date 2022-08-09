package Sort;

import java.util.Arrays;
// 插入排序用链表等来更适合，数组不方便
// 我这里用的是交换上去，本质还是冒泡，只是冒泡到合适位置就不冒了
// 插入也是同理，找到A>x>C的位置，把x插入到AC中间
// 如果要是用数组插入到合适位置，整个数组往后移动，那时间复杂度就高了
public class InsertionSort {
    public static void main(String[] args) {
        int[]arry={60,9,2,8,7,3,6,4,5,11,20,9};
        int temp;
        // 首两位有序
        if (arry[0]>arry[1])
        {
            temp=arry[0];
            arry[0]=arry[1];
            arry[1]=temp;
        }
        // 把剩余的数值插入到合适的位置
        for (int i=2;i<arry.length;i++)
        {
            int j=i;
            // 只要小于前面的数值，就与签名的交换
            // j==1的时候，说明已经交换到开头位置了，不能再继续交换
            while (arry[j]<arry[j-1])
            {
                temp=arry[j];
                arry[j]=arry[j-1];
                arry[j-1]=temp;
                if (j==1)
                {
                    break;
                }
                j--;
            }
        }
        System.out.println(Arrays.toString(arry));
    }
}

