package org.uwo.eng.cmcken22;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

public class cmcken22_Lab04_WaitingList {
	int size = 5;
	cmcken22_Lab04_Passenger[] queue = new cmcken22_Lab04_Passenger[size];
	int tail = -1;
	int qCount = 0;
	int waitPosCount = 0;
	
	public void AddtoWaitingList(cmcken22_Lab04_Passenger pObject){
		enqueue(pObject);
		sortQueue();
	}
	
	public void sortQueue(){
	    for (int i = 1; i < queue.length; i++) {
	    	if (queue[i] != null){
	    		cmcken22_Lab04_Passenger valueToSort = queue[i];
	            int j = i;
	            int count = 0;
	            char char1 = queue[j - 1].name.charAt(0);
	            char char2 = valueToSort.name.charAt(0);
	            int ascii1 = (int) char1;
	            int ascii2 = (int) char2;
	            
	            while (ascii1 == ascii2){
	            	char1 = queue[j - 1].name.charAt(count);
	                char2 = valueToSort.name.charAt(count);
	                ascii1 = (int) char1;
	                ascii2 = (int) char2;
	                count++;
	            }
	            
	            while (j > 0 && char1 > char2){
	            	queue[j] = queue[j - 1];
	                j--;
	            }
	            queue[j] = valueToSort;
	    	}
	    }
	}
	
	public int determinePassenger(int IDnum){
		for (int i = 0; i<queue.length; i++){
			if (queue[i].ID == IDnum){
				return i;
			}
		}
		return -1;
	}
	
	public boolean isEmpty(){
		int count = 0;
		for (int i = 0; i < queue.length; i++){
			if (queue[i] != null){
				count = 1;
			}
		}
		if (count == 1){
			return false;
		}
		else{
			return true;
		}
	}
	
	public void printWaitingList(cmcken22_Lab04_Flight fObject){
		int count = 0;
		String list = "Passengers on waiting list for Flight: " + fObject.flightName + "\n";
		list += "ID:              Name: \n";
		list += "-----------------------------------------\n";

		for (int i = 0; i < queue.length; i++){
			if (queue[i] != null){
				count = 1;
			}
		}
		if(count == 0) {
			JOptionPane.showMessageDialog(null, "Waiting list is empty"); 
		}
		else {
			int Qsize = queue.length-1; 
			
			for (int i = 0; i<=Qsize; i++){
				if(queue[i] != null){
					list += queue[i].ID + "        " + queue[i].name + "(" + queue[i].waitPosition + ")" + "\n";
				}
			}
			JOptionPane.showMessageDialog(null, list);
		}
	}
	
	public void removeFromWait(cmcken22_Lab04_Passenger pObject){
		int atIndex = -1;
		int count = 0;
		int count1 = 0;
		int i = 0;
		
		while (queue[i] != null) {
			count1 += 1;
			i++; 
		}
		if (count1 == 0){
			JOptionPane.showMessageDialog(null, "Waiting list is empty"); 
		}
		else{
			cmcken22_Lab04_Passenger[] temp = queue;
			int Qsize = queue.length - 1; 
			System.out.println("pObject.ID = " + pObject.ID);
			for (i = 0; i < Qsize; i++){
				if(temp[i] != null){
					System.out.println("temp["+i+"].ID = " + temp[i].ID);
					if (pObject.ID == temp[i].ID){
						atIndex = i;
						count = 1;
					}
				}
			}
			if (count == 0){
				JOptionPane.showMessageDialog(null, "Passenger not found on waiting list!");
			}
			if (count == 1){
				for (i = 0; i < Qsize; i++){
					if(temp[i+1] != null){
						if(i < atIndex){
							queue[i] = temp[i];
							if (queue[i].waitPosition > pObject.waitPosition){
								queue[i].waitPosition = queue[i].waitPosition - 1;
							}
						}
						if (i >= atIndex){
							queue[i] = temp[i+1];
							if (queue[i].waitPosition > pObject.waitPosition){
								queue[i].waitPosition = queue[i].waitPosition - 1;
							}
						} // copy elements back into queue
					}
				}
				System.out.println("qCount = " + qCount);
				qCount--;
				queue[qCount] = null;
				JOptionPane.showMessageDialog(null, "Successfully removed from waiting list");
				waitPosCount--;
				tail--;
			}
		}
	}
	
	public void addToFrontOfQueue(cmcken22_Lab04_Passenger pObject){
		//System.out.println("Queue length = " + queue.length);
		// queue size must be increased
		if (tail == queue.length-1){
			cmcken22_Lab04_Passenger[] temp = queue;
			queue = new cmcken22_Lab04_Passenger[size*2];
			for(int i = 0; i < temp.length; i++){
				queue[i] = temp[i];
			}
			size = size*2;
		}
		//System.out.println("Queue length = " + queue.length);
		// shift existing elements one to the right to make way for priority passengers that were booted from flight
		if (tail < queue.length-1) {
			for (int i = tail; i >= 0; i--){
				queue[i+1] = queue[i];
				queue[i+1].waitPosition = queue[i+1].waitPosition + 1;
			}
			queue[0] = pObject; // add passenger to front of queue
			queue[0].waitPosition = 1;
			tail++;
			System.out.println("queue shifting results: ");
			for (int i = 0; i <= queue.length-1; i++){
				if (queue[i] != null)
				System.out.println(queue[i].ID + " " + queue[i].name + "(" + queue[i].waitPosition + ")");
			}
		}
		sortQueue();
	}
	
	public cmcken22_Lab04_Passenger getFirstinLine(){	
		int count = 0;
		int i = 0;
		while (queue[i] != null){
			count += 1;
			i++;
		}
		if(count == 0) {
			//JOptionPane.showMessageDialog(null, "Waiting list is empty");
			return null;
		}
		else{
			int lowest = 10000;
			cmcken22_Lab04_Passenger toBeRemoved = new cmcken22_Lab04_Passenger("no_name", 0);
			int ifCount = 0;
			System.out.println("count = " + count);
			System.out.println("queue[0].name = " + queue[0].name);
			System.out.println("queue[0].waitPosition = " + queue[0].waitPosition);
			for (int j = 1; j < count; j++){
				System.out.println("queue["+j+"].name = " + queue[j].name);  
				System.out.println("queue["+j+"].waitPosition = " + queue[j].waitPosition); 
				if (queue[0].waitPosition > queue[j].waitPosition){
					if (lowest > queue[j].waitPosition){
						lowest = queue[j].waitPosition;
						System.out.println("Lowest = " + lowest);
						toBeRemoved = queue[j];
						//System.out.println("toBeRemoved inside " +  j + " = " + toBeRemoved.name);
						ifCount = 1;
					}
				}
				//the passenger is the lowest and need to be removed
				if (ifCount == 0) {
					toBeRemoved = queue[0];
				}
			}
			System.out.println("First in line = " + toBeRemoved.name);
			removeFromWait(toBeRemoved);
			return toBeRemoved;
		}
	}
	
	public cmcken22_Lab04_Passenger addToFlight(){
		return null;
		/*
		int flight = determineFlight(fObject.flightName);
		Passenger tempPassenger = null;
		Passenger[] temp = new Passenger[queue.size()];
		int Qsize = queue.size(); 
		
		for (int i = 0; i<Qsize; i++)
		{temp[i] = queue.remove();}// copy elements from queue to array 
		
		for (int i = 0; i<Qsize; i++)
		{
			if (flight == temp[i].flightNum)
			{tempPassenger = temp[i];}
			if (flight != temp[i].flightNum)
			{queue.enqueue(temp[i]);} //add elements back into queue  
		}
		return tempPassenger;
		*/
	}
	
	public int getQueueSize(){
		System.out.print("Queue size = " + queue.length + "\n");
		return queue.length;
	}
	
	public int determineFlight(String flight){
		if (flight.equals("WS1413")){return 1;}
		if (flight.equals("WS1050")){return 2;}
		if (flight.equals("WS1411")){return 3;}
		if (flight.equals("WS1021")){return 4;}
		if (flight.equals("WS1036")){return 5;}
		return 7; //there is no flight associated with the number 7
		//this return will be used to determine if the flight entered actually exists or not
	}
	
	public void enqueue(cmcken22_Lab04_Passenger pObject){
		for (int i = 0; i < queue.length; i++){
			if (queue[i] == pObject){
				JOptionPane.showMessageDialog(null, "Passenger is already on waiting list!");
				return; //passenger is already on the waiting list
			}
		}
		//once the waiting list is full the array size is doubled
		if(qCount == size) {
			cmcken22_Lab04_Passenger[] temp = queue;
			queue = new cmcken22_Lab04_Passenger[size*2];
			for(int i = 0; i < temp.length-1; i++)
			{
				queue[i] = temp[i];
			}
			size = size*2;
		}
		if (qCount < size){
			queue[qCount] = pObject;
			pObject.waitPosition = ++waitPosCount;
			JOptionPane.showMessageDialog(null, "Pasenger added to waitingList: " + pObject.name);
			//JOptionPane.showMessageDialog(null, "Passenger added to waiting list");
			qCount++;
			tail++;
		}
	}
}