import java.util.Arrays;

public class MergeKLists {

    public static ListNode mergeKLists(ListNode[] lists) {
        int min_index = 0;
        ListNode head = new ListNode(0);
        ListNode h = head;
        while (true) {
            boolean isBreak = true;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    if (lists[i].val < min) {
                        min_index = i;
                        min = lists[i].val;
                    }
                    isBreak = false;
                }
            }
            if (isBreak) {
                break;
            }
            h.next = lists[min_index];
            h = h.next;
            lists[min_index] = lists[min_index].next;
        }
        h.next = null;
        return head.next;
    }

    public static void main (String [] args){

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);
        printList(head1);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);
        printList(head2);

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);
        printList(head3);

        ListNode mergeList = mergeKLists(new ListNode[]{head1, head2, head3});

        printList(mergeList);
    }

    private static void printList(ListNode mergeList) {
        while (mergeList != null) {
            System.out.print(mergeList.val + " -> ");
            mergeList = mergeList.next;
        }
        System.out.print("NULL \n");
    }
}


class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

