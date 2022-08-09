package Sort;
import java.util.Arrays;
// 桶排序
public class BucketSort {
    public static void main(String[] args) {
        int[]arry={60,9,2,80,70,3,6,4,5,11,20,9};
        // 确定桶的数量，最好让元素平均分散到每一个桶里
        // 确定桶的容量
        // 确定桶的范围
        int min,max;
        min=max=arry[0];
        for (int i=1;i<arry.length;i++)
        {
            max=max<arry[i]?arry[i] : max;
            min=min>arry[i]?arry[i] : max;
        }
        // 单个桶容量=max-min+1
        //min~30
        int[]bucket1=new int[max-min+1];
        //31~max
        int[]bucket2=new int[max-min+1];
        // 桶分类，这里可以优化一下，加入到桶中的时候直接插入排序
        int a,b;
        a=b=0;
        for (int i=0;i<arry.length;i++)
        {
            if (arry[i]<31)
            {
                // 入到桶1
                bucket1[a]=arry[i];
                a++;
            }
            else
            {
                // 入到桶2
                bucket2[b]=arry[i];
                b++;
            }
        }
        // 对桶1，桶2进行排序
        Arrays.sort(bucket1);
        Arrays.sort(bucket2);

        // 输出桶1，桶2
        for (int i=0;i<bucket1.length;i++)
        {
            if (bucket1[i]!=0)
            {
                System.out.print(bucket1[i]+" ");
            }
        }
        for (int i=0;i<bucket2.length;i++)
        {
            if (bucket2[i]!=0)
            {
                System.out.print(bucket2[i]+" ");
            }
        }
    }
}
