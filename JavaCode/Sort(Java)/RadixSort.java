package Sort;

import java.util.Arrays;
import java.util.List;

// 基数排序
public class RadixSort {
    public static void main(String[] args) {
        int[]arry={60,9,2,8,7,3,6,4,5,11,20,9};
        // 个位，十位，百位，.....桶排序的变种
        int [][]temp=new int[arry.length][arry.length];

        // 按个位排序，入桶
        for (int i=0;i<arry.length;i++)
        {
            int j=0;
            while (temp[arry[i]%10][j]!=0)
            {
                j++;
            }
            temp[arry[i]%10][j]=arry[i];
        }

        // 放回原数组
        int x=0;
        for (int i=0;i<arry.length;i++)
        {
            for (int j=0;j<arry.length;j++)
            {
                if (temp[i][j]!=0)
                {
                    arry[x]=temp[i][j];
                    x++;
                }
            }
        }
        System.out.println(Arrays.toString(arry));

        temp=new int[arry.length][arry.length];
        // 按10位排序，入桶
        for (int i=0;i<arry.length;i++)
        {
            int j=0;
            while (temp[arry[i]/10][j]!=0)
            {
                j++;
            }
            temp[arry[i]/10][j]=arry[i];
        }

        // 放回原数组
        x=0;
        for (int i=0;i<arry.length;i++) {
            for (int j = 0; j < arry.length; j++) {
                if (temp[i][j] != 0) {
                    arry[x] = temp[i][j];
                    x++;
                }
            }
        }
        System.out.println(Arrays.toString(arry));
    }
}
