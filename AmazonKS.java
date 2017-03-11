

class AmazonKS {
	
	public static void main(String[] args) {
		AmazonKS obj = new AmazonKS();

		int numberOfItems = 5;
		int maxWeightAllowed = 11;
		int[] weightVector = { 0, 1, 2, 5, 6, 7 };
		int[] costVector = { 0, 1, 6, 18, 22, 28 };

		int[] itemsToTake = obj.knapsack( numberOfItems, costVector, weightVector, maxWeightAllowed );

		for ( int ele : itemsToTake ) {
			System.out.println(ele);
		}
	}

	// implements the indivisible knapsack algorithm using Dynamic Programming(with Memoization)
	int[] knapsack(int n, int[] c, int[] w, int mW) {
		
		int[][] subProblems = new int[n+1][mW+1];	// 0 to N and 0 to mW

		for ( int v = 1; v <= mW; v++ ) { 	// with available weight v
		 	for ( int k = 1; k <= n; k++ ) {		// with k items to fill
		 		
		 		// not including element k (setting default)
		 		subProblems[k][v] = subProblems[k-1][v];

		 		// check if we can include the kth element
		 		if ( w[k] <= v ) {

		 			// check if including element k gives more value than excluding it.
		 			if( c[k] + subProblems[k-1][v-w[k]] > subProblems[k][v] ) {		 				
		 				subProblems[k][v] = c[k] + subProblems[k-1][v-w[k]];

		 			}		 			
		 		
		 		}

		 	}
		 } 

		 for ( int[] x : subProblems ) {
		 	for ( int ele : x ) {
		 		System.out.print(ele + " ");
		 	}
		 	System.out.println();
		 	
		 }

		 // now we know the max value that can be filled in the sack.
		 // trace how we got here to get the items to put in sack.

		 int i = n;
		 int j = mW;

		 int[] trace = new int[n];
		 int traceArrIndex = 0;

		 while ( i > 0 && j > 0 ) {
		 	if( subProblems[i][j] == subProblems[i-1][j] ) {
		 		i--;
		 	} else {
		 		trace[traceArrIndex] = i;
		 		j = j - w[i];
		 		i--;
		 		traceArrIndex++;
		 	}
		 }


		 return trace;

	}

}