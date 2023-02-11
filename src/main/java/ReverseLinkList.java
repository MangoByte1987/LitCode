public class ReverseLinkList {

    public static void main(String args []){

        LinkList list = new LinkList();

        list = LinkList.add(list, 1);
        list = LinkList.add(list, 2);
        list = LinkList.add(list, 3);
        list = LinkList.add(list, 4);
        list = LinkList.add(list, 5);
        list = LinkList.add(list, 6);
        list = LinkList.add(list, 7);
        list = LinkList.add(list, 8);

        LinkList.printList(list);
      //  LinkList.reverselist(list);
       // System.out.print("\n");
        //LinkList.printList(list);
       // LinkList.deleteNode(list,5);
       // LinkList.deleteNode(list,8);
        System.out.print("\n");
        LinkList.reverseListRecursion(list);
        LinkList.printList(list);
    }
}

class LinkList<D> {
    LinkNode head;

    static class LinkNode <D> {
        private D data;
        public LinkNode next;

        public LinkNode(D data){
            this.data=data;
            this.next = null;
        }
    }

    public static LinkList add(LinkList list , int data){
        LinkNode new_node = new LinkNode(data);
        if(list.head == null){
            list.head = new_node;
        }else{
            LinkNode last = list.head;
            while(last.next !=null){
                last = last.next;
            }
            last.next = new_node;

        }
        return list;
    }

    public static void deleteNode(LinkList list, int data) {
        LinkNode current = list.head;
        if (current.data.equals(data)) {
            list.head = current.next;
        }
        LinkNode prev= null;
        while (current != null && !current.data.equals(data) ){
            prev = current;
            current = current.next;
        }
        if(current == null) {
            System.out.println("\nNode not found");
            return;
        }else {
            prev.next = current.next;
        }
    }

    public static void reverselist(LinkList list) {
        LinkNode current = list.head;
        LinkNode nextNode = null;
        LinkNode prev = null;

        while(current != null){
            nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        list.head = prev;
    }

    public static void reverseListRecursion(LinkList list){
        LinkNode head = list.head;

        LinkNode reverseHead = reverse(head);
        list.head = reverseHead;
    }
    public static LinkNode reverse(LinkNode head){
        System.out.println("head : " + head.data);
        if(head == null || head.next == null)
                return head;

        LinkNode current = reverse(head.next);
        head.next.next = head;
        head.next=null;
        return current;
    }

   public static void printList(LinkList list) {
        LinkNode head = list.head;
       while(head !=null) {
           System.out.print(head.data+ "-> ");
           head = head.next;
       }
        System.out.print( "NULL" );
    }

}


