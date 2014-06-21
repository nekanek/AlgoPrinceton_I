/*
By convention, the indices i and j are integers between 1 and N, where (1, 1) is the upper-left site: 
Throw a java.lang.IndexOutOfBoundsException if either i or j is outside this range. 
The constructor should take time proportional to N^2; 
all methods should take constant time plus a constant number of calls to the union-find methods 
union(), find(), connected(), and count(). 
*/


    public class Percolation {
       // create N-by-N grid, with all sites blocked
        public Percolation(int N) {
            
            if (N <= 0) { throw new IllegalArgumentException(); }           
            else {               
                this.size = N;
                this.grid = new boolean[N^2 + 2];
                this.unf = new WeightedQuickUnionUF(N);
                
                // open virtual sites
                this.grid[0] = true;
                this.grid[grid.length-1] = true;
                
            }

        }             
       
       // open site (row i, column j) if it is not already
       public void open(int i, int j) {
                    
           
           if (i <= 0 || j <= 0 || i > this.size || j > this.size) 
                { throw new IndexOutOfBoundsException(); } 
           else {
               // compute array index
               int index = (i-1) * size + j;
               this.grid[index] = true;
               
               // connect upper cell
               if (i == 1) {
                   unf.union(index, 0);
               }
               else {
                   if (this.grid[(i-2) * size + j]) {
                       unf.union(index, (i-2) * size + j);
                   }
               }

               // connect lower cell
               if (i == this.size) {
                   unf.union(index, this.grid.length-1);
               }
               else {
                   if (this.grid[(i) * size + j]) {
                       unf.union(index, (i) * size + j);
                   }
               }               
               
               // connect left cell
               if (j != 1 && this.grid[index-1]) {
                   unf.union(index, index-1);
               }
               
               // connect right cell
               if (j != this.size && this.grid[index+1]) {
                   unf.union(index, index+1);
               }
           }
       }       
       
       // is site (row i, column j) open?
       public boolean isOpen(int i, int j) {
           if (i <= 0 || j <= 0 || i > this.size || j > this.size) 
                { throw new IndexOutOfBoundsException(); } 
           else {
               int index = (i-1) * size + j; 
               return this.grid[index];
           }           
       }    
       
        // is site (row i, column j) full?
       public boolean isFull(int i, int j) {
           if (i <= 0 || j <= 0 || i > this.size || j > this.size) 
                { throw new IndexOutOfBoundsException(); } 
           else {
               // check connection in union
               
           } 
           return true;
       }
       
       // does the system percolate?
       public boolean percolates()  {
           // check whether virtual sites are connected in union
           return true;
       }  
       
        private boolean[] grid; // holds information whether site is open
        /*
                  0        *       // vitrual site #1
                       * * * * *
                       * * * * * 
                       * * * * *
                       * * * * *
                       * * * * *
               N^2 + 1  `  *     // virtual site #2
        length N^2 + 2
        */
        private int size; // N
        private WeightedQuickUnionUF unf; // UF instance to hold connection information
    }