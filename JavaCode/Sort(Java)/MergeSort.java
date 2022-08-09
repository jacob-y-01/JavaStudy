package Sort;

import java.util.Arrays;

// 归并排序
public class MergeSort {
    static int[]arry={60,9,2,8,7,3,6,4,5,11,20,90};
    public static void main(String[] args) {
        //
        int arr[]= Merge(arry,arry.length);
        System.out.println(Arrays.toString(arr));
    }
    public static int[] Merge(int[] arry1, int ArryLength)
    {
        // 已经被拆分成1个的小单元
        if (ArryLength==1)
        {
            return arry1;
        }
        // 要归并的长度
        int length=ArryLength/2;


        int []t2;
        // 有没有余数，会不会出现，5/2=2，2，1；我们需要，3，2
        if (ArryLength%2!=0)
        {
            //有余数
            t2=new int[length+1];
        }
        else
        {
            // 没有余数
            t2=new int[length];
        }

        int []t1=new int[length];
        // 数组切片，将一个大数组分成两个小数组
        for (int i=0;i<length;i++)
        {
            t1[i]=arry1[i];
        }
        for (int i=0;i<t2.length;i++)
        {
            t2[i]=arry1[i+t1.length];
        }

        // 这里需要注意一下顺序，两个数组中较大的，先递归
        t2=Merge(t2,t2.length);
        t1=Merge(t1,length);
        // t1,t2分别装上上层返回的数组，再把t1,t2组装起来
        // 这个组装过程，就叫做归并

        // 归并后的新数组
        int []t3=new int[ArryLength];
        // 用于表示t1,t2下标
        int a1,a2;
        a1=a2=0;
        // 对新数组进行归并
        for (int i=0;i < t3.length;i++)
        {
            if (a1<t1.length&&a2<t2.length)
            {
                // 比较，较小的先进入t3
                // 这个结构很像栈，pop出来，谁小谁先进新容器
                if (t2[a2]>t1[a1])
                {
                    t3[i]=t1[a1];
                    a1++;
                }
                else
                {
                    t3[i]=t2[a2];
                    a2++;
                }
            }
            else
            {
                // 有一个数组已经访问完了，为空
                if (a1==t1.length)
                {
                    // 如果是t1数组访问完了，就直接把t2数组逐个加入到t3新数组
                    t3[i]=t2[a2];
                    a2++;
                }
                else
                {
                    // 否则就是t2数组访问完了，把t1加入t3
                    t3[i]=t1[a1];
                    a1++;
                }
            }
        }
        // 返回合并后的数组
        return t3;
    }
}
