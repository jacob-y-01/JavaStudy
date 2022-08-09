package DataStruct;

public class tree {
    // 初始化，构建一棵树
    public static TreeNode Init()
    {
        TreeNode node1=new TreeNode(0);
        node1.AddNode(node1,1);
        node1.AddNode(node1,2);
        node1.AddNode(node1.LeftSon,3);node1.AddNode(node1.LeftSon,4);
        node1.AddNode(node1.RightSon,5);node1.AddNode(node1.RightSon,6);
        node1.AddNode(node1.RightSon.RightSon,7);
        return node1;
        //          0
        //         1  2
        //       3 4  5 6
        //                7
    }

    public static void main(String[] args) {
        TreeNode root=Init();

        System.out.print("\n先序：");
        root.print0(root);
        System.out.print("\n中序：");
        root.print1(root);
        System.out.print("\n后续：");
        root.print2(root);
        System.out.println("查找节点");
        TreeNode node;
        if((node=TreeNode.FindNode(root,7))==null) {
            System.out.println("没找到这个数值");
        }else
        {
            System.out.println("树中包含该数值");
        }
    }
}
class TreeNode {
    TreeNode LeftSon;
    TreeNode RightSon;
    int num;
    TreeNode() {
        this.LeftSon = null;
        this.RightSon = null;
    }
    TreeNode(int num) {
        this.LeftSon = null;
        this.RightSon = null;
        this.num=num;
    }
    // 为某节点插入子节点
    public static void  AddNode(TreeNode node,int num)
    {
        if (node.LeftSon!=null&&node.RightSon!=null)
        {
            System.out.println("不可插入，该节点左右子树非空");
        }
        else
        {
            TreeNode NewNode=new TreeNode(num);
            if(node.LeftSon==null)
            {
                node.LeftSon=NewNode;
            } else
            {
                node.RightSon=NewNode;
            }
        }
    }
    // 先序遍历,查找节点
    public static TreeNode FindNode(TreeNode root,int num)
    {
        if (root==null)
        {
            return null;
        }
        // 找到了该数值，向上返回
        if (root.num==num)
        {
            return root;
        }
        TreeNode node;
        if (!((node=FindNode(root.LeftSon,num))==null))
        {
            return node;
        }
        if (!((node=FindNode(root.RightSon,num))==null))
        {
            return node;
        }
        // 这一行我也不想写，但语法要求必须写
        return null;
    }
    // 三序遍历输出
    public static void print0(TreeNode root)
    {
        // 先序：根左右
        if (root==null)
        {
            return;
        }
        System.out.print(root.num+" ");
        print0(root.LeftSon);
        print0(root.RightSon);
    }
    public static void print1(TreeNode root)
    {
        // 中序：左根右
        if (root==null)
        {
            return;
        }
        print1(root.LeftSon);
        System.out.print(root.num+" ");
        print1(root.RightSon);
    }
    public static void print2(TreeNode root)
    {
        // 后续，左右根
        if (root==null)
        {
            return;
        }
        print2(root.LeftSon);
        print2(root.RightSon);
        System.out.print(root.num+" ");
    }
}

