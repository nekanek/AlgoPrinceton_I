

//package AlgoPrinceton_I.Week_1;
//import AlgoPrinceton_I.libs.*;

/*

*/


public class Test {
    
   public Test(int N, int T) {
        if (N <= 0 || T <= 0) { throw new IllegalArgumentException(); }
        else {
            // perform experiments
            //this.pExp = new double[T];
            int sitesCount = N^2;
                        System.out.println("sites count " + sitesCount);
            int openSites = 0;
            int row = 1 + StdRandom.uniform(N);
                        System.out.println("row " + row);
            int col = 1 + StdRandom.uniform(N); 
                        System.out.println("col " + col);
            for (int i = 0; i < T; i++) { 
                Percolation exp = new Percolation(N);
                        System.out.println("Percolates: " + exp.percolates());
                while (!exp.percolates()) {
                        System.out.println("is Open: " + exp.isOpen(row,col));
                    while (exp.isOpen(row, col)) {
                        row = 1 + StdRandom.uniform(N);
                                    System.out.println("row " + row);
                        col = 1 + StdRandom.uniform(N);
                                    System.out.println("col " + col);
                    } 
                    
                    System.out.println("exit row-column loop");
                    System.out.println("is Open: " + exp.isOpen(row,col));
                    exp.open(row, col);
                    openSites++;
                }
                //pExp[i] = openSites/sitesCount;
            }
        }
   }  
    public static void main(String[] args)  {
        Test experiment = new Test(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        
    }
   
   
   
}    