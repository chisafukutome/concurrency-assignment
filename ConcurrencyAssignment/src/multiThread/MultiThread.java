package multiThread;

import java.util.ArrayList;
import java.util.Random;

/*
 * Created by: Chisa Fukutome
 * Date Created: 11/9/2021
 * Purpose: Concurrency
 */

/*
 * Implement a parallel array sum, and compare performance with single thread sum.
 * DONE: Make an array of 200 million random numbers between 1 and 10.
 * DONE: Compute the sum in parallel using multiple threads.
 * DONE: Then compute the sum with only one thread, and display the sum and times for both cases.
 */

public class MultiThread {
	private static int randNum = 0;
	private static int sum = 0;
	
	public static synchronized void loadRandNumArr(ArrayList<Integer> array) {
		//generate a random number
		Random rand = new Random();  
		randNum = rand.nextInt(10) + 1;
		
		//add the random number to array
		array.add(randNum);
		
		//calculate sum
		calcSum(randNum);
	}//end loadRandNumArr method
	
	public static synchronized void calcSum(int num) {
		sum += num;
	}//end calcSum method

	public static void main(String[] args) {
		ArrayList<Integer> array = new ArrayList<>();
		long startTime, endTime;
		
		Thread t1 = new Thread (new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 100000000; i++) {
					loadRandNumArr(array);
				}				
			}//end run method
		});//end Thread constructor
		
		Thread t2 = new Thread (new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 100000000; i++) {
					loadRandNumArr(array);
				}				
			}//end run method
		});//end Thread constructor
		
		//perform the threads
		startTime = System.nanoTime();	//starting time
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("The sum: " + sum);
		endTime = System.nanoTime();	//ending time
		System.out.println("Time took: " + (endTime - startTime) + " nanoseconds");
	}//end main method

}//end MultiThread Class
