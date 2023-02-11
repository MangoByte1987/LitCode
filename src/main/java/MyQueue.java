public class MyQueue<T> {
    private static class MyQueueNode<T>{
        private T data;
        private MyQueueNode<T> next;
       public MyQueueNode(T data){
            this.data = data;
       }
    }

    MyQueueNode<T> first;
    MyQueueNode<T> last;

    public void add(T node){
        MyQueueNode<T> tmp = new MyQueueNode<T>(node);
        if(last!= null){
            last.next = tmp;
        }
        last = tmp;
        if(first == null){
            first = last;
        }
    }

    public T remove(){
        if(first == null){
            System.out.println("Queue is empty");
        }
        T data = first.data;;
        first = first.next;
        if(first == null ){
            last = null;
        }
        return  data;
    }

    public T peek(){
        if(first == null){
            System.out.println("Queue is empty");
        }
        return  first.data;
    }
    public boolean isEmpty(){
        return first == null;
    }

    public void printQueue(){
        MyQueueNode temp = first;
        while(first.next != null){
            System.out.print(first.data + "<- ");
            first = first.next;
        }
        System.out.print(first.data + " \n");
        first = temp;
    }
    public static void main(String [] args){
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.add(1);
        myQueue.add(2);
        myQueue.add(3);
        myQueue.add(4);
        myQueue.printQueue();
        myQueue.remove();
        myQueue.printQueue();
        System.out.println("Peek : " + myQueue.peek());

    }
}
