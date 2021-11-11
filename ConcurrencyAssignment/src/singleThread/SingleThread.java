package singleThread;

import java.util.ArrayList;
import java.util.Random;

public class SingleThread {
	private static int randNum = 0;
	private static int sum = 0;
	
	public static synchronized void loadRandNumArr(ArrayList<Integer> array) {
		//generate a random number
		Random rand = new Random();  
		randNum = rand.nextInt(10) + 1;
		
		//add the random number to array
		array.add(randNum);
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
				for(int i = 0; i < 200000000; i++) {
					loadRandNumArr(array);
					calcSum(array.get(i));
				}				
			}//end run method
		});//end Thread constructor
		
		//perform the threads
		startTime = System.nanoTime();	//starting time
		t1.start();
		
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//output
		System.out.println("The sum: " + sum);
		endTime = System.nanoTime(); //ending time
		System.out.println("Time took: " + (endTime - startTime) + " nanoseconds");
	}//end main method
}//end SingleThread Class
