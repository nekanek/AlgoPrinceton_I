



public class PercolationStats {

    // perform T independent computational experiments on an N-by-N grid
    // The constructor should throw a java.lang.IllegalArgumentException if either N ≤ 0 or T ≤ 0.
   public PercolationStats(int N, int T) {
        if (N <= 0) { throw new IllegalArgumentException(); }
        else {
           
           
           
       }
       


   }   
   
    // sample mean of percolation threshold
   public double mean()  {
       
   }

   // sample standard deviation of percolation threshold                   
   public double stddev() {
       
   }   

   // returns lower bound of the 95% confidence interval               
   public double confidenceLo() {
       
   } 

   // returns upper bound of the 95% confidence interval          
   public double confidenceHi()  {
       
   }    

   // test client, described below     
   /*
   Also, include a main() method that takes two command-line arguments N and T, 
   performs T independent computational experiments (discussed above) on an N-by-N grid, 
   and prints out the mean, standard deviation, and the 95% confidence interval for the percolation threshold. 
   Use standard random from our standard libraries to generate random numbers; 
   use standard statistics to compute the sample mean and standard deviation.

   */
   public static void main(String[] args)  {
       
   } 
}