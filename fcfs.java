FCFS
package org.cloudbus.cloudsim.examples;
import java.text.ParseException;
class fcfs {
	static void findWaitingTime(int processes[], int n,
			int bt[], int wt[]) {
            wt[0] = 0;
		for (int i = 1; i < n; i++) {
			wt[i] = bt[i - 1] + wt[i - 1];
		}
	}
	static void findTurnAroundTime(int processes[], int n,
			int bt[], int wt[], int tat[]) {
		for (int i = 0; i < n; i++) {
			tat[i] = bt[i] + wt[i];
		}
	}
	static void findavgTime(int processes[], int n, int bt[]) {
		int wt[] = new int[n], tat[] = new int[n];
		int total_wt = 0, total_tat = 0;
		findWaitingTime(processes, n, bt, wt);
		findTurnAroundTime(processes, n, bt, wt, tat);
		System.out.printf("Processes Burst time Waiting"
					+" time Turn around time\n");
		for (int i = 0; i < n; i++) {
			total_wt = total_wt + wt[i];
			total_tat = total_tat + tat[i];
			System.out.printf(" %d ", (i + 1));
			System.out.printf("	 %d ", bt[i]);
			System.out.printf("	 %d", wt[i]);
			System.out.printf("	 %d\n", tat[i]);
		}
		float s = (float)total_wt /(float) n;
		int t = total_tat / n;
		System.out.printf("Average waiting time = %f", s);
		System.out.printf("\n");
		System.out.printf("Average turn around time = %d ", t);
	}
	public static void main(String[] args) throws ParseException {
		int processes[] = {1, 2, 3};
		int n = processes.length;
		int burst_time[] = {10, 5, 8};
		findavgTime(processes, n, burst_time);
	}
}
==============================================================================
RR
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cloudbus.cloudsim.examples;

/**
 *
 * @author Student
 */
// Java program for implementation of RR scheduling

public class rr 
{
	// Method to find the waiting time for all
	// processes
	static void findWaitingTime(int processes[], int n,
				int bt[], int wt[], int quantum)
	{
		// Make a copy of burst times bt[] to store remaining
		// burst times.
		int rem_bt[] = new int[n];
		for (int i = 0 ; i < n ; i++)
			rem_bt[i] = bt[i];
	
		int t = 0; // Current time
	
		// Keep traversing processes in round robin manner
		// until all of them are not done.
		while(true)
		{
			boolean done = true;
	
			// Traverse all processes one by one repeatedly
			for (int i = 0 ; i < n; i++)
			{
				// If burst time of a process is greater than 0
				// then only need to process further
				if (rem_bt[i] > 0)
				{
					done = false; // There is a pending process
	
					if (rem_bt[i] > quantum)
					{
						// Increase the value of t i.e. shows
						// how much time a process has been processed
						t += quantum;
	
						// Decrease the burst_time of current process
						// by quantum
						rem_bt[i] -= quantum;
					}
	
					// If burst time is smaller than or equal to
					// quantum. Last cycle for this process
					else
					{
						// Increase the value of t i.e. shows
						// how much time a process has been processed
						t = t + rem_bt[i];
	
						// Waiting time is current time minus time
						// used by this process
						wt[i] = t - bt[i];
	
						// As the process gets fully executed
						// make its remaining burst time = 0
						rem_bt[i] = 0;
					}
				}
			}
	
			// If all processes are done
			if (done == true)
			break;
		}
	}
	
	// Method to calculate turn around time
	static void findTurnAroundTime(int processes[], int n,
							int bt[], int wt[], int tat[])
	{
		// calculating turnaround time by adding
		// bt[i] + wt[i]
		for (int i = 0; i < n ; i++)
			tat[i] = bt[i] + wt[i];
	}
	
	// Method to calculate average time
	static void findavgTime(int processes[], int n, int bt[],
										int quantum)
	{
		int wt[] = new int[n], tat[] = new int[n];
		int total_wt = 0, total_tat = 0;
	
		// Function to find waiting time of all processes
		findWaitingTime(processes, n, bt, wt, quantum);
	
		// Function to find turn around time for all processes
		findTurnAroundTime(processes, n, bt, wt, tat);
	
		// Display processes along with all details
		System.out.println("PN " + "\tB " +
					" \tWT " + " \t TAT");
	
		// Calculate total waiting time and total turn
		// around time
		for (int i=0; i<n; i++)
		{
			total_wt = total_wt + wt[i];
			total_tat = total_tat + tat[i];
			System.out.println(" " + (i+1) + "\t" + bt[i] +"\t " +
							wt[i] +"\t " + tat[i]);
		}
	
		System.out.println("Average waiting time = " +
						(float)total_wt / (float)n);
		System.out.println("Average turn around time = " +
						(float)total_tat / (float)n);
	}
	
	// Driver Method
	public static void main(String[] args)
	{
		// process id's
		int processes[] = { 1, 2, 3};
		int n = processes.length;
	
		// Burst time of all processes
		int burst_time[] = {10, 5, 8};
	
		// Time quantum
		int quantum = 2;
		findavgTime(processes, n, burst_time, quantum);
	}
}
