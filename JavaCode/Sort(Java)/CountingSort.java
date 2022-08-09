package Sort;

import java.util.Arrays;

// 计数排序,适合大量区间的数值，例如，同班级年龄，身高，通常差的不多
//（1）找出待排序的数组中最大和最小的元素
//（2）统计数组中每个值为i的元素出现的次数，存入数组C的第i项
//（3）对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）
//（4）反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1
public class CountingSort {
    public static void main(String[] args) {
        int[] arry = {60, 9, 2, 8, 7, 3, 6, 4, 5, 11, 20, 9};
        // 找到最大数最小数
        int max, min;
        max = min = arry[0];
        for (int i = 1; i < arry.length; i++) {
            max = max < arry[i] ? arry[i] : max;
            min = min > arry[i] ? arry[i] : min;
        }
        // 根据区间开辟新数组
        int[] temp = new int[max+1];
        for (int i = 0; i < arry.length; i++) {
            // 进行计数
            temp[arry[i]]++;
        }

        // 再把统计好的放回到原数组
        int j=0;
        for (int i = 0; i < arry.length; i++) {
            while (temp[j]==0)
            {
                j++;
            }
            arry[i] = j;
            temp[j]--;
        }
        System.out.println(Arrays.toString(arry));
    }
}
