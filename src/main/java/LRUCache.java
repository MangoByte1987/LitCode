import java.util.LinkedHashMap;

public class LRUCache {
    private class Node {
        int key;
        int val;
        Node prev;
        Node next;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void add(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private int size;
    private LinkedHashMap<Integer, Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int size) {
        this.size = size;
        map = new LinkedHashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        remove(node);
        add(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        if (map.size() > size) {
            Node last = tail.prev;
            remove(last);
            map.remove(last.key);
        }
        Node node = new Node();
        node.key = key;
        node.val = value;
        add(node);
        map.put(key, node);

    }

}
