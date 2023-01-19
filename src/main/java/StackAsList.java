public class StackAsList {
    StackNode root;
    class StackNode{
        int data;
        StackNode next;
        StackNode(int data){
            this.data=data;
        }
    }

    public void push(int data){
        StackNode node = new StackNode(data);
        if(root == null){
            root = node;
        }else{
            StackNode temp = root;
            root = node;
            node.next = temp;
        }
    }

    public int pop(){
        int top = -1;
        if(root == null){
            System.out.println("Stack is Empty");
            return top;
        }
        top = root.data;
        root= root.next;
        return  top;
    }

    public int peek(){
        int top = -1;
        if(root == null){
            System.out.println("Stack is Empty");
            return top;
        }
        top = root.data;
        return  top;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public  void print(){
        StackNode temp = root;
        while(root != null){
            System.out.println(root.data);
            root = root.next;
        }
        root = temp;
    }

    public static void main(String[] args)
    {

        StackAsList sll = new StackAsList();

        sll.push(10);
        sll.push(20);
        sll.push(30);
        sll.print();
        System.out.println(sll.pop()
                + " popped from stack");

        System.out.println("Top element is " + sll.peek());
    }
}
