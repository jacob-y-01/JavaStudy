package DataStruct;

public class stacktest {
    public static void main(String[] args) {
        Stack stack1=new Stack();
        Stack stack2;
        if ((stack2=Stack.pop(stack1))!=null)
        {
            System.out.println(stack2.num);
        }
        stack1.push(stack1,10);
        stack1.push(stack1,20);
        stack1.push(stack1,30);
        stack1.print(stack1);
        stack1.pop(stack1);
        System.out.println();
        stack1.print(stack1);

    }
}
// 头删，头插法
class Stack
{
    int num;
    private Stack top;
    private Stack buttom;
    private Stack next;
    Stack() {
        num = 0;
        next=null;
        top = buttom = this;
    }
    Stack(int num) {
        this.num = num;
        next=null;
        top = buttom = this;
    }
    // 出栈
    public static Stack pop(Stack stack1)
    {
        if (stack1.top==stack1.buttom) {
            System.out.println("无法出栈，栈已空");
            return null;
        }
        else
        {
            Stack s1=stack1.next;
            stack1.next=stack1.next.next;
            stack1.top =stack1.next;
            return s1;
        }

    }
    // 入栈
    public static void push(Stack stack1,int num)
    {
        Stack NewNum=new Stack(num);
        NewNum.next=stack1.next;
        stack1.next=NewNum;
        stack1.top= stack1.next;
        System.out.println(num+":已经入栈");
    }
    // 输出栈，从top到buttom
    public static void print(Stack stack1)
    {
        Stack temp=stack1.top;
        while (temp!=null)
        {
            System.out.print(temp.num+" ");
            temp=temp.next;
        }
    }



}

