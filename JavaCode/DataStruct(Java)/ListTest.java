package DataStruct;

import java.util.Scanner;

public class ListTest {
    public static void main(String[] args) {
        // 头节点不存放数据
        list t1 = new list(0);
        Scanner s1 = new Scanner(System.in);
        int c;
        while (true) {
            System.out.println("******************************************");
            System.out.println("                1. 添加数据 ");
            System.out.println("                2. 删除数据 ");
            System.out.println("                3. 输出数据 ");
            System.out.println("                4. 判为空 ");
            System.out.println("                5. 退出 ");
            System.out.println("******************************************");
            c = s1.nextInt();
            int num;
            switch (c) {
                case 1:
                    System.out.println("请输入数值：");
                    num = s1.nextInt();
                    list.AddNode2(t1, num);
                    break;
                case 2:
                    System.out.println("请输入数值：");
                    num = s1.nextInt();
                    list.DeletNode(t1, num);
                    break;
                case 3:
                    list.Print(t1);
                    break;
                case 4:
                    if (list.IsNull(t1)) {
                        System.out.println("是空");
                    } else {
                        System.out.println("非空");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("重新选择");
                    continue;
            }
        }
    }
}

class list {
    int num;
    int length;
    list head;
    list last;
    list next;

    list() {
        this.next = null;
        this.num = 0;
        last = head = this;
    }

    list(int num) {
        this.next = null;
        this.num = num;
        last = head = this;
    }

    // 判为空
    public static boolean IsNull(list l1) {
        return l1.next==null?true:false;
    }

    // 头插法
    public static list AddNode(list l1, int num) {
        list newnode = new list(num);
        newnode.next = l1;
        // 更新l1的头节点
        l1.head = newnode;
        return newnode;
    }

    // 尾插法
    public static void AddNode2(list l1, int num) {
        list newnode = new list(num);
        // 更新最后一个节点

        // newnode.next = l1.next;
        l1.last.next = newnode;
        l1.last = newnode;
    }

    // 查找输出
    public static void FindNode(list l1, int num) {
        list temp = new list();
        temp = l1;
        while (temp != null) {
            if (temp.num == num) {
                System.out.println("该数值存在");
                return;
            }
            temp = temp.next;
        }
        System.out.println("没有找到该数值");
    }

    // 先找到指定节点，再跳跃过去
    public static void DeletNode(list l1, int num) {
        list temp = new list();
        temp = l1;
        while (temp.next != null) {
            if (temp.next.num == num) {
                System.out.println("\n该数值已删除" + num);
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
        System.out.println("没有找到该数值");
    }

    public static void Print(list l1) {
        list temp = new list();
        temp = l1.head.next;
        while (temp != null) {
            System.out.print(temp.num + " ");
            temp = temp.next;
        }
    }
}
