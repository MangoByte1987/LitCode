import java.util.Arrays;
import java.util.List;

public class AddNumLinkList {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){
            this.val=val;
        }
    }
    ListNode head;
    public void add(ListNode node){
            node.next = head;
            head = node;
    }
    public static void printList(ListNode node){
        ListNode temp = node;
        while(node !=null){
            System.out.print(temp.val + "-> ") ;
            node = node.next;
        }
        System.out.println("\n");
        node = temp;
    }
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode tmp = result;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            carry = sum / 10;
            int reminder = sum % 10;
            ListNode ll = new ListNode();
            ll.val = reminder;
            tmp.next = ll;
            tmp = tmp.next;
        }
        return result.next;
    }
    public static void main(String [] args){
        AddNumLinkList list1 = new AddNumLinkList();
        list1.add(new ListNode(3));
        list1.add(new ListNode(4));
        list1.add(new ListNode(2));
        printList(list1.head);
    }
}

