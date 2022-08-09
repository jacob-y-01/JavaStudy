package DataStruct;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;


public class maptest {
    public static void main(String[] args) {
        MapNode node1=new MapNode(1);// 为1相连2，3，4
        node1.AddNode(node1,2,2);
        node1.AddNode(node1,3,3);
        node1.AddNode(node1,4,4);

        node1.AddNode(node1.LinkNode.get(0),5,5);//2-5相连
        node1.AddNode(node1.LinkNode.get(0),6,6);//2-6相连

        node1.AddLink(node1.LinkNode.get(0),node1.LinkNode.get(1),7);//将2和3相连，加上权值


        // 以下操作一次只能执行一种，因为执行后，flag会被标记为true
        //node1.print2(node1);//打印邻接表
        Queue<MapNode> queue = new LinkedList();
        if (node1.FindNumber(node1,1)!=null)// 广度优先搜索
        {
            System.out.println("找到了该数值");
        }
        else
        {
            System.out.println("该图中不存在该数值");
        }
    }
}
class MapNode
{
    // 存放数据
    int num;
    // 有无被访问
    boolean flag;
    // 本节点可访问的节点
    ArrayList<MapNode> LinkNode=new ArrayList<MapNode>();
    // 用于存放权值，与next相对应，
    ArrayList<Integer> Weight =new ArrayList<Integer>();

    MapNode()
    {
        flag=false;
    }
    MapNode(int num)
    {
        this.num=num;
        flag=false;
    }

    // 为图中某一节点添加节点
    public static void AddNode(MapNode node,int num,int weight)
    {
        MapNode NewNode=new MapNode(num);
        // 无向图，互联
        node.LinkNode.add(NewNode);
        node.Weight.add(weight);

        NewNode.LinkNode.add(node);
        NewNode.Weight.add(weight);
    }
    // 为俩节点加链
    public static void AddLink(MapNode node1,MapNode node2,int weight)
    {
        // 先判断是否已经有链，重置权值，还是取消
        for (int i=0;i<node1.LinkNode.size();i++)
        {
            if (node1.LinkNode.get(i)==node2)
            {
                System.out.println("已经存在链接");
                return;
            }
        }
        // 没有发现有链接，创建链接
        node1.LinkNode.add(node2);
        node1.Weight.add(weight);
        node2.LinkNode.add(node1);
        node2.Weight.add(weight);

    }
    // 打印邻接表
    public  static void print2(MapNode node)
    {
        System.out.println("打印邻接表");
        ArrayList<MapNode> MapQueue=new ArrayList<MapNode>();
        IntoQueue(node,MapQueue);
        for (int i=0;i<MapQueue.size();i++)
        {
            System.out.print(MapQueue.get(i).num+"->");
            for (int j=0;j<MapQueue.get(i).LinkNode.size();j++)
            {
                System.out.print(MapQueue.get(i).LinkNode.get(j).num+"->");
            }
            System.out.println();
        }
    }
    // 打印邻接表先把节点存入队列
    public static void IntoQueue(MapNode node, ArrayList<MapNode> MapQueue)
    {
        for (int i=0;i<node.LinkNode.size();i++) {
            if (!node.flag)
            {
                // 遍历后加入队列
                MapQueue.add(node);
                node.flag=true;
            }
            // 判断该节点是否已经访问，防止闭环
            if (!((MapNode)node.LinkNode.get(i)).flag) {
                IntoQueue(node.LinkNode.get(i),MapQueue);
            }
        }
        return;
    }
    // 深度优先搜索
    public static MapNode FindNumber(MapNode node,int num) {
        MapNode temp;
        if (!node.flag)
        {
            if (node.num==num)
            {
                return node;
            }
            node.flag=true;
            for (int i=0;i<node.LinkNode.size();i++)
            {
                if((temp=FindNumber(node.LinkNode.get(i),num))!=null)
                {
                    return temp;
                }
            }
        }
        return null;
    }
    //广度优先
    public static MapNode FindNumber2(MapNode node,int num,Queue<MapNode> queue)
    {
        // 本结点未被访问过，将子加入到队列
        if (!node.flag)
        {
            if (node.num==num)
            {
                return node;
            }
            node.flag=true;
            //System.out.println(node.num);
            for (int i=0;i<node.LinkNode.size();i++)
            {
                queue.offer(node.LinkNode.get(i));
            }
        }
        // 自己就是最后一个数值
        if (queue.isEmpty())
        {
            return null;
        }
        MapNode temp;
        if((temp=FindNumber2(queue.poll(),num,queue))!=null)
        {
            return temp;
        }
        return temp;
    }
    // 删除节点
    public static void DeletNode(MapNode node)
    {
        // 获取与node链接的节点
        for (int i=0;i< node.LinkNode.size();i++)
        {
            // 再从子中找到与node的关联，把与node的关联删掉
            for (int j=0;i<node.LinkNode.get(i).LinkNode.size();j++)
            {
                if (node.LinkNode.get(i).LinkNode.get(j)==node)
                {
                    // 移除掉与node的关联
                    node.LinkNode.get(i).LinkNode.remove(j);
                    node.LinkNode.get(i).Weight.remove(j);
                }
            }
        }
        // 删除掉node与其他节点的关联
        for (int i=0;i< node.LinkNode.size();i++)
        {
            node.LinkNode.remove(i);
            node.Weight.remove(i);
        }
        node=null;
    }
}

