
public class Subset {
   public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> myDeque = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            myDeque.enqueue(item);
        }
        // System.out.println(k + " strings:");
        for (int i = 0; i < k; i++) {
            System.out.println(myDeque.dequeue());
        }
   }

}


