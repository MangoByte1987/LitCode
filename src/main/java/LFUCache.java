import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class LFUCache {
    private Map<Integer,CNode> valueMap = new ConcurrentHashMap<>();
    private Map<Integer,Integer> freqCntMap =  new ConcurrentHashMap<>();
    private Map<Integer,DoubleLinkList> freqMap = new ConcurrentSkipListMap<>();
    int capacity;
    public LFUCache(int capacity){
        this.capacity = capacity;
    }
    private class CNode{
        int key;
        int val;
        CNode prev;
        CNode next;
        public CNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    private class DoubleLinkList{
        CNode head;
        CNode tail;
        int size;
        public void add(CNode node){
            if(head == null) {
                head = node;
            }else{
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
            size++;

        }

        public void remove(CNode node){
            if(node.next == null)   tail = node.prev;
            else node.next.prev = node.prev;

            if(node.prev == null)   head = node.next;
            else node.prev.next = node.next;

            size--;
        }
        public int getLength(){
            return size;
        }
    }

    public void put (int key,int val) {
        if (capacity > 0) {
            if (!valueMap.containsKey(key)) {
                if (valueMap.size() >= capacity) {

                    CNode node = new CNode(key, val);
                    valueMap.put(key, node);
                    freqCntMap.put(key, 1);
                    freqMap.computeIfAbsent(1, k -> new DoubleLinkList()).add(node);
                } else {


                }
            }
        }
    }
}
