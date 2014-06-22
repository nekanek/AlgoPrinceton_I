



public class PercolationStats {

    // perform T independent computational experiments on an N-by-N grid
    // The constructor should throw a java.lang.IllegalArgumentException if either N ≤ 0 or T ≤ 0.
    //Use standard random from our standard libraries to generate random numbers; 
   public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) { throw new IllegalArgumentException(); }
        else {
            this.pExp = new double[T];
            double sitesCount = Math.pow(N, 2.0);
            int openSites;
            int row = 1 + StdRandom.uniform(N);
            int col = 1 + StdRandom.uniform(N); 
            for (int i = 0; i < T; i++) { 
                Percolation exp = new Percolation(N);
                openSites = 0;
                while (!exp.percolates()) {
                    while (exp.isOpen(row, col)) {
                        row = 1 + StdRandom.uniform(N);
                        col = 1 + StdRandom.uniform(N);
                    }         
                    exp.open(row, col);
                    openSites++;
                }
                pExp[i] = ((double) openSites)/sitesCount;
            }
        }
   }   
   
    // sample mean of percolation threshold
   // use standard statistics to compute the sample mean and standard deviation.
    public double mean()  {
        return StdStats.mean(this.pExp);
       
    }

   // sample standard deviation of percolation threshold                   
   public double stddev() {
       return StdStats.stddev(this.pExp);
   }   

   // returns lower bound of the 95% confidence interval               
   public double confidenceLo() {
       return (this.mean() - 1.96 * Math.sqrt(this.stddev()) / Math.sqrt(this.pExp.length));
   } 

   // returns upper bound of the 95% confidence interval          
   public double confidenceHi()  {
       return (this.mean() + 1.96 * Math.sqrt(this.stddev()) / Math.sqrt(this.pExp.length));       
   }    
   

   private double[] pExp; // % of fullness after which system percolates in experiment i
    
   
   // test client, described below     
   /*
   Also, include a main() method that takes two command-line arguments N and T, 
   performs T independent computational experiments (discussed above) on an N-by-N grid, 
   and prints out the mean, standard deviation, and the 95% confidence interval for the percolation threshold. 
   Use standard random from our standard libraries to generate random numbers; 
   use standard statistics to compute the sample mean and standard deviation.

   */
    public static void main(String[] args)  {
        PercolationStats experiment = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("Mean: " + experiment.mean());
        System.out.println("StDev: " + experiment.stddev());
        System.out.println("95% interval: " + experiment.confidenceLo() + " - " + experiment.confidenceHi()); 
    } 
}