package DataStruct;

public class QueueTest {
    public static void main(String[] args) {
        queue q1=new queue();
        q1.pop(q1);
        q1.push(q1,1);
        q1.pop(q1);
        q1.print(q1);
    }
}


// 头删,尾插,双向链表
class queue
{
    int num=0;
    queue next,parent;
    queue head,end;
    queue()
    {
        head=end=this;
        next=parent=null;
    }
    queue(int num)
    {
        this.num=num;
        head=end=this;
        next=parent=null;
    }

    public static void pop(queue q1)
    {
        if (q1.end==q1.head)
        {
            System.out.println("队列为空");
        }
        else
        {
            q1.end.parent.next=null;
            q1.end=q1.end.parent;
        }
    }
    public static void push(queue q1,int num)
    {
        queue newq=new queue(num);
        newq.parent=q1.end;
        q1.end.next=newq;

        q1.end=newq;
    }
    public static void print(queue q1) {
        queue temp = q1.next;
        while (temp != null) {
            System.out.println(temp.num);
            temp = temp.next;
        }
    }
}
