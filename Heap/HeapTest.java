package Heap;

public class HeapTest {
    public static void main(String[] args) {

        Integer[] array = { 3, 15, 7, 1, 99, 200, 4, 25, 999, 0, 29 };

        Heap<Integer> pq = new Heap<Integer>(5);
        for (Integer integer : array) {
            pq.insertKey(integer);
        }
        System.out.println(pq.deleteKey(0));
        System.out.println(pq.deleteKey(200));

        while (pq.getSize() != 0) {
            System.out.println(pq.extractMin());
        }
        System.out.printf("Heap size: %d", pq.getSize());
    }
}
