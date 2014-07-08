
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Subset {
   public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        Deque<String> myDeque = new Deque<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            myDeque.addLast(item);
        }     
   }

}


