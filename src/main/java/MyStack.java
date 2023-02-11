public class MyStack<T> {
    private static class MyStackNode<T>{
        T data;
        MyStackNode<T> next;
        MyStackNode(T data){
            this.data = data;
        }
    }
    public MyStackNode<T> top;

    public void push(T data){
        MyStackNode<T> tmp = new MyStackNode<>(data);
        if(top == null ){
            top = tmp;
        }else {
            MyStackNode<T> curr = top;
            top = tmp;
            tmp.next = curr;
        }
    }

    public T pop(){
        if(top == null){
            System.out.println("Stack is Empty");
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    public T peek(){
        if(top == null){
            System.out.println("Stack is Empty");
        }
        return top.data;
    }

    public void printStack(){
        MyStackNode<T> tmp = top;
        while (top != null){
            System.out.print(top.data + "-> ");
            top = top.next;
        }
        System.out.print(" \n");
        top = tmp;
    }

    public static void main(String [] args){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.printStack();
        System.out.println("Top : "+ myStack.top.data);
        System.out.println("Peek : "+ myStack.top.data);
        myStack.pop();
        myStack.printStack();
        System.out.println("Top : "+ myStack.top.data);
    }
}
