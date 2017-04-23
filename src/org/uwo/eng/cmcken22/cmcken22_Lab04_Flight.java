package org.uwo.eng.cmcken22;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;
public class cmcken22_Lab04_Flight 
{
	Scanner scan = new Scanner(System.in);
	String flightName;
	int maxCap = 0;
	int maxTemp = maxCap;
	int head = 0;
	int tail = -1;
	int positionCount = 0;
	cmcken22_Lab04_Passenger[] passengers = new cmcken22_Lab04_Passenger[0];
	cmcken22_Lab04_Passenger[] bootList = new cmcken22_Lab04_Passenger[0];
	cmcken22_Lab04_WaitingList wait = new cmcken22_Lab04_WaitingList();
	cmcken22_Lab04_LuggageLoader luggage = new cmcken22_Lab04_LuggageLoader();
	
	cmcken22_Lab04_Flight(String name)
	{
		flightName = name;
		maxCap = 5;
	}
	
	public void addPassenger(cmcken22_Lab04_Passenger object, int callerFunction)
	{// I want to know what function is calling this function.. the callerFunction variable takes care of this issue
		int count = 0;
		for (int i = 0; i<passengers.length; i++)
		{
			if(passengers[i] == object)
			{
				count = 1; //if passenger is already on flight count will equal 1 and skip over the next if statement
			}// otherwise count will remain 0 as passenger is not on flight 
		}
		if (count == 0) //the passenger has not been added multiple times.. continue
		{
			//System.out.println("Size of flight = " + passengers.length);
			cmcken22_Lab04_Passenger[] tempArray = passengers;
			passengers = new cmcken22_Lab04_Passenger[1 + tempArray.length];
			for (int index = 0; index < tempArray.length; index++)
			{
				passengers[index] = tempArray[index];
			}

			object.position = ++positionCount; // each passenger added to the flight is assigned their position here
			
			passengers[passengers.length-1] = object;
			if (callerFunction == 1)
			{
				JOptionPane.showMessageDialog(null, "Passenger successfully added!");
			}
			if (callerFunction == 2)
			{
				JOptionPane.showMessageDialog(null, "Passenger added from waiting List.");
			}
			System.out.println("Passenger succesfully added!");
			tail++;
			sortArray();
		}
		if (count == 1)
		{
			System.out.print("Passenger is already on flight and was not re-added! \n");
			JOptionPane.showMessageDialog(null, "Passenger is already on flight and was not re-added!");
		}
	}
	
	public void sortArray()
	{
		int count = 0;
	    for (int i = 1; i < passengers.length; i++) 
	    {
            cmcken22_Lab04_Passenger valueToSort = passengers[i];
            int j = i;
            char char1 = passengers[j - 1].name.charAt(0);
            char char2 = valueToSort.name.charAt(0);
            int ascii1 = (int) char1;
            int ascii2 = (int) char2;
            
            while (ascii1 == ascii2)
            {
            	//System.out.println("While loop 1 Executed!");
            	char1 = passengers[j - 1].name.charAt(count);
                char2 = valueToSort.name.charAt(count);
                ascii1 = (int) char1;
                ascii2 = (int) char2;
                count++;
            }
            
            while (j > 0 && char1 > char2) 
            {
            	//System.out.println("While loop 2 Executed!");
            	passengers[j] = passengers[j - 1];
                j--;
            }
            passengers[j] = valueToSort;
	    }
	}
	
	public int determinePassenger(int IDnum)
	{
		for (int i = 0; i<passengers.length; i++)
		{
			if (passengers[i].ID == IDnum)
			{
				return i;
			}
		}
		return -1;
	}
	
	public boolean checkDuplicate(cmcken22_Lab04_Passenger object)
	{
		int count = 0;
		for (int i = 0; i<passengers.length; i++)
		{
			if(passengers[i] == object)
			{
				count = 1; //if passenger is already on flight count will equal 1
			}// otherwise count will remain 0 as passenger is not on flight 
		}
		if (count == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public void printPassengersOnFlight(cmcken22_Lab04_Flight object)
	{
		System.out.print("Flight list for " + object.flightName + " \n");
		System.out.print("Number of passengers on board: " + object.getSize() + " \n");
		System.out.print("Name       ID \n");
		System.out.print("----------------- \n");
		for (int i = 0; i<passengers.length; i++)
		{
			//the name with is least letters is 3 the name with the most letters is 7 
			//these if statements adjust the spacing accordingly
			 if (passengers[i].name.length() == 3)
			 {
				 System.out.print(passengers[i].name + "        ");
				 System.out.print(passengers[i].ID + " \n");
			 }
			 if (passengers[i].name.length() == 4)
			 {
				 System.out.print(passengers[i].name + "       ");
				 System.out.print(passengers[i].ID + " \n");
			 }
			 if (passengers[i].name.length() == 5)
			 {
				 System.out.print(passengers[i].name + "      ");
				 System.out.print(passengers[i].ID + " \n");
			 }
			 if (passengers[i].name.length() == 7)
			 {
				 System.out.print(passengers[i].name + "    ");
				 System.out.print(passengers[i].ID + " \n");
			 }
		}
	}
	
	public int getSize()
	{
		return passengers.length;
	}
	
	public int isFlightFull()
	{
		if (passengers.length == maxCap)
		{
			JOptionPane.showMessageDialog(null,"The flight is full! Passengers will be added to waiting list until capacity is increased.");
			return 2;
			/*
			String input = JOptionPane.showInputDialog("Enter new capacity greater than " + maxCap + ": ", " ");
			 
			maxTemp = Integer.parseInt(input);
			if (maxTemp <= maxCap)
			{
				do	
				{
					String input1 = JOptionPane.showInputDialog("Error! Enter new capacity greater than " + maxCap + ": ", " ");
					maxTemp = Integer.parseInt(input1);
				}while (maxTemp <= maxCap);
			}
			maxCap = maxTemp;
			*/
		}
		return 1;
	}
	
	public int removePassenger(cmcken22_Lab04_Passenger pObject) // delete passenger function
	{
		//System.out.print("Queue size = " + wait.getQueueSize() + "\n");
		//JOptionPane.showMessageDialog (null, "YEEEE BISHH");
		
		int count1 = 0;
		int atIndex = 0;
		cmcken22_Lab04_Passenger tempObject;
		//System.out.println("ID of Passenger to be removed: " + pObject.ID);
		//System.out.println("Name of Passenger to be removed: " + pObject.name);
		for (int i = 0; i<passengers.length; i++)
		{
			//System.out.println("Passenger["+i+"]: " + passengers[i].name);
			if(passengers[i] == pObject)
			{
				count1 = 1;
				atIndex = i;
				//System.out.println("Passenger to be removed located at " + atIndex + " in flight array");
			}
		}
		if (count1 == 1)  //passenger is on board 
		{
			String list = "";
			list += "Passenger dropped from Flight: \n";
			list += "ID:              Name: \n";
			list += "-------------------------------------\n";
			list += passengers[atIndex].ID + "         " + passengers[atIndex].name + "\n";
			list += "-------------------------------------\n";
			cmcken22_Lab04_Passenger[] temp = passengers;
			passengers = new cmcken22_Lab04_Passenger[temp.length - 1];
			for (int i = 0; i < temp.length-1; i++)
			{
				if(i < atIndex)
				{
					passengers[i] = temp[i];
					if (passengers[i].position > pObject.position)
					{
						passengers[i].position = passengers[i].position - 1;
					}
					//System.out.println("Position of Passenger["+i+"] = " + passengers[i].position);
				}
				if (i >= atIndex)
				{
					passengers[i] = temp[i+1];
					if (passengers[i].position > pObject.position)
					{
						passengers[i].position = passengers[i].position - 1;
					}
					//System.out.println("Position of Passenger["+i+"] = " + passengers[i].position);
				} // copy elements back into queue
				
			}
			System.out.print("Passenger succesfully removed! \n");
			positionCount--;
			tail--;
			return 1;
		}
		if (count1 == 0){return 0;} //passenger is not on board
	
		return 0;
	}
	
	public int changeCapacity(int capacity)
	{
		int temp = maxCap;
		maxCap = capacity;
		if (temp < maxCap)
		{
			return 1; // passengers need to be added to the flight from the waiting list as there is now room on board
		}
		
		if (temp > maxCap)
		{
			if (passengers.length < maxCap)
			{
				return 2; // the max cap has been changed but no passengers need to be booted from flight
			}
			if (passengers.length > maxCap) // passengers exceeding the flight limit must be booted from flight
			{
				bootList = new cmcken22_Lab04_Passenger[passengers.length - maxCap]; // this bootlist is used to keep track of which passengers are to be booted, hence the name
				// this bootlist is then used in the addToFrontOfQueue function to add the booted passengers to the waiting list
				int highest = -1;
				int bootCount = 0;
				int ifCount = 0;
				cmcken22_Lab04_Passenger toBeRemoved = new cmcken22_Lab04_Passenger("no_name", 0);
				int bootLength = bootList.length;
				
				while(bootList.length > bootCount)
				{
					for (int j = 1; j < passengers.length; j++)
					{
						if (passengers[0].position < passengers[j].position)
						{
							if (highest < passengers[j].position)
							{
								highest = passengers[j].position;
								toBeRemoved = passengers[j];
								System.out.println("toBeRemoved inside " +  j + " = " + toBeRemoved.name);
								ifCount = 1;
							}
						}
						//System.out.println(j + " Highest = " + highest);
						if (ifCount == 0) //the passenger is the highest and need to be removed
						{
							toBeRemoved = passengers[0];
						}
					}
					System.out.println("toBeRemoved = " + toBeRemoved.name);
					bootList[bootCount] = toBeRemoved;
					bootCount++;
					removePassenger(toBeRemoved);
					System.out.println("Passengers.length = " + passengers.length);
					highest = -1;
				}
				
				System.out.print("Passenger succesfully added to bootList! \n");
				
				String list = "";
				list += "Passenger dropped from Flight and added to waiting list: \n";
				list += "ID:              Name: \n";
				list += "-------------------------------------\n";

				for (int i = 0; i < bootLength; i++)
				{
					list += bootList[i].ID + "           " + bootList[i].name + "\n";
				}
				list += "-------------------------------------\n";
				JOptionPane.showMessageDialog(null, list);
			}
			return 2; // passengers need to be removed from flight and added to waiting list
		}
		return 0; // flight remains the same
	}
	
	public void addToQueue()
	{
		int bootLength = bootList.length;
		for (int i = 0; i < bootList.length; i++)
		{
			wait.addToFrontOfQueue(bootList[i]);
		}
	}
	
	public boolean isFull()
	{
		if(passengers.length != maxCap)
		{
			return false; // flight has an open seat on board
		}
		else
		{
			return true;
		}
	}
	
	public cmcken22_Lab04_WaitingList flightWaitingList() 
	{
		return wait; // this function is used in the main window to connect the flight class and waiting list class
		             // each flight object has its own waiting list object that is returned to the main
		             // therefore, each flight object in the main can access the waiting list functions 
	}
	
	public cmcken22_Lab04_LuggageLoader luggage()
	{
		return luggage;
	}
	
	public void updateFlight(cmcken22_Lab04_Passenger pObject)
	{
		addPassenger(pObject, 2);
	}
}