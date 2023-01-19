import java.util.ArrayList;
import java.util.Objects;

class HashNode1<K,V>{
    K key;
    V value;
    int hashcode;
    HashNode1<K,V> next;
    HashNode1(K key, V value , int hashcode){
        this.key = key;
        this.value = value;
        this.hashcode = hashcode;
    }

}

public class HashMapSolution<K,V> {

    ArrayList<HashNode1<K,V>> mapBuckets;
    int numOfBuckets;
    int size;

    HashMapSolution(){
        mapBuckets = new ArrayList();
        numOfBuckets=8;
        size=0;

        for(int i = 0; i < numOfBuckets ; i++){
            mapBuckets.add(null);
        }
    }

    public int size(){return size;}

    public boolean isEmpty(){return size() == 0;}


    private final int hashCode(K key){
        return Objects.hashCode(key);
    }

    private int getBucketIndex(K key){
        int hashcode = hashCode(key);
        int index = Math.abs(hashcode % numOfBuckets);
        return index;
    }

    public V remove(K key){
        int index = getBucketIndex(key);
        int hashcode = hashCode(key);

        HashNode1<K,V> head = mapBuckets.get(index);
        HashNode1<K,V> prev =null;
        while(head != null){
            if(head.key.equals(key) && head.hashcode == hashcode){
                break;
            }

            prev = head;
            head = head.next;
        }

        if(head == null)
            return null;
        size--;
        if(prev != null ){
            prev.next = head.next;
        }else{
            mapBuckets.set(index,head.next);
        }
        return head.value;
    }

    public void add (K key, V value){

        int index = getBucketIndex(key);
        int hashcode = hashCode(key);
        HashNode1<K,V> head = mapBuckets.get(index);

        while(head != null) {
            if(head.key.equals(key) && head.hashcode == hashcode){
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        // System.out.println("size : " + size);
        head = mapBuckets.get(index);
        HashNode1<K,V> newNode = new HashNode1(key,value,hashcode);
        newNode.next = head;
        mapBuckets.set(index,newNode);

        if((1.0 * size / numOfBuckets ) > 0.7){
            ArrayList<HashNode1<K,V>> temp = mapBuckets;
            numOfBuckets = 2 * numOfBuckets;
            size = 0;
            for(int i =0 ; i< numOfBuckets ; i++){
                mapBuckets.add(null);
            }

            for(HashNode1<K,V> node :temp){
                while(node !=null){
                    add(node.key,node.value);
                    node = node.next;
                }

            }

        }

    }
    public static void main(String[] args) {
        HashMapSolution m = new HashMapSolution();
        m.add("one",1);
        m.add("two",2);
        m.add(3,2);
        System.out.println("Size of map : " + m.size());
        m.remove(3);
        System.out.println("Size of map : " + m.size());

    }
}
