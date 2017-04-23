package org.uwo.eng.cmcken22;
import java.util.Scanner;

import javax.swing.JOptionPane;
public class cmcken22_Lab04_Airline {
	
	cmcken22_Lab04_Passenger[] passengerList = new cmcken22_Lab04_Passenger[0];
	cmcken22_Lab04_Flight[] flights = new cmcken22_Lab04_Flight[0];
	
	cmcken22_Lab04_Airline(cmcken22_Lab04_Passenger[] p, cmcken22_Lab04_Flight[] f){
		passengerList = p;
		flights = f;
	}
	
	public void addFlight(cmcken22_Lab04_Flight fObject){
		//System.out.println("fObject = " + fObject.flightName);
		int count = 0;
		for (int i = 0; i < flights.length; i++){
			//System.out.println("Flight[ "+ i + "] = " + flights[i].flightName);
			if (flights[i].flightName.equals(fObject.flightName)){
				count = 1;
			}
		}
		if (count == 0){
			cmcken22_Lab04_Flight[] tempArray = flights;
			flights = new cmcken22_Lab04_Flight[1 + tempArray.length];
			for (int index = 0; index < tempArray.length; index++){
				flights[index] = tempArray[index];
			}
			flights[flights.length-1] = fObject;
			JOptionPane.showMessageDialog(null, "Flight Added!");
		}
		else {
			JOptionPane.showMessageDialog(null, "Error! Flight already exists!");
		}
	}
	
	public void addPassenger(cmcken22_Lab04_Passenger pObject){
		System.out.println("Name of new Passenger: " + pObject.name);
		cmcken22_Lab04_Passenger[] tempArray = passengerList;
		passengerList = new cmcken22_Lab04_Passenger[1 + tempArray.length];
		for (int index = 0; index < tempArray.length; index++){
			passengerList[index] = tempArray[index];
		}
		passengerList[passengerList.length-1] = pObject;
		System.out.println("Passneger succesfully added!");
		System.out.println("Passenger Length: " + passengerList.length);
	}
	public int determineFlight(String flight){
		System.out.println("looking for: " + flight);
		for (int i = 0; i < flights.length; i++){
			System.out.println(flights[i].flightName);
			if (flights[i].flightName.equals(flight)){
				return i;
			}
		}
		return -1; //there is no flight associated with the number -1
		//this return will be used to determine if the flight entered actually exists or not
	}
	
	public void printPassengerAirlineDataBase(){
		//System.out.println("Passenger Length inside print function: " + passengerList.length);
		System.out.print("List of all Passengers \n");
		System.out.print("Name       ID \n");
		System.out.print("----------------- \n");
		for (int i = 0; i<passengerList.length; i++){
			//the name with is least letters is 3 the name with the most letters is 7 
			//these if statements adjust the spacing accordingly
			//System.out.println("INDEX = " + i);
			 if (passengerList[i].name.length() == 3){
				 System.out.print(passengerList[i].name + "        ");
				 System.out.print(passengerList[i].ID + " \n");
			 }
			 if (passengerList[i].name.length() == 4){
				 System.out.print(passengerList[i].name + "       ");
				 System.out.print(passengerList[i].ID + " \n");
			 }
			 if (passengerList[i].name.length() == 5)
			 {
				 System.out.print(passengerList[i].name + "      ");
				 System.out.print(passengerList[i].ID + " \n");
			 }
			 if (passengerList[i].name.length() == 6){
				 System.out.print(passengerList[i].name + "     ");
				 System.out.print(passengerList[i].ID + " \n");
			 }
			 if (passengerList[i].name.length() == 7){
				 System.out.print(passengerList[i].name + "    ");
				 System.out.print(passengerList[i].ID + " \n");
			 }
		}
		//System.out.print(passengerList[10].name + "    ");
		//System.out.print(passengerList[10].ID + " \n");
		
	}
	
	public int getFlightSize(){
		return flights.length;
	}
	
	public int getPassengerSize(){
		return passengerList.length;
	}
	
	public int determinePassenger(int IDnum){
		System.out.println("Looking for: " + IDnum);
		for (int i = 0; i<passengerList.length; i++){
			System.out.println(passengerList[i].ID);
			if(passengerList[i].ID == IDnum){
				return i;
			}
		}
		return -1;
	}
}