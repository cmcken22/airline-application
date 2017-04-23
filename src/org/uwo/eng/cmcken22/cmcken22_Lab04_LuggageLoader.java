package org.uwo.eng.cmcken22;

import javax.swing.JOptionPane;

public class cmcken22_Lab04_LuggageLoader {
	int size = 5;
	cmcken22_Lab04_Passenger[] stack = new cmcken22_Lab04_Passenger[size];
	cmcken22_Lab04_Passenger[] luggageOnBoard = new cmcken22_Lab04_Passenger[0];
	int top = -1;
	int qCount = 0;
	
	public void AddtoStack(cmcken22_Lab04_Passenger pObject){
		for (int i = 0; i < stack.length; i++){
			if (stack[i] == pObject){
				JOptionPane.showMessageDialog(null, "Passenger luggage already checked in!");
				return; //passenger is already on the waiting list
			}
		}
		int count1 = 0;
		for (int i = 0; i < luggageOnBoard.length; i++){
			if (luggageOnBoard[i].ID == pObject.ID){
				count1 = 1;
			}
		}
		if (count1 == 1){
			JOptionPane.showMessageDialog(null, "Passenger luggage already loaded on to flight!");
		}
		else{
			//once the waiting list is full the array size is doubled
			if(qCount == size) {
				cmcken22_Lab04_Passenger[] temp = stack;
				stack = new cmcken22_Lab04_Passenger[size*2];
				for(int i = 0; i < temp.length; i++){
					stack[i] = temp[i];
				}
				size = size*2;
			}
			if (qCount < size){
				stack[qCount] = pObject;
				JOptionPane.showMessageDialog(null, "Passenger luggage added to stack");
				qCount++;
				top++;
			}
		}
	}
	
	public void printLuggage(cmcken22_Lab04_Flight fObject){
		int count = 0;
		String list = "Luggage waiting for flight: " + fObject.flightName + "\n"; 
		list += "----------------------------------\n";
		System.out.println("stack.length = " + stack.length);
		for (int i = top; i >= 0; i--){
			if (stack[i] != null){
				list += stack[i].name + " " + stack[i].ID + " Luggage\n";
				count = 1;
			}
		}
		if (count == 0){
			JOptionPane.showMessageDialog(null, "No luggage in stack!");
		}
		else{
			JOptionPane.showMessageDialog(null, list);
		}
	}
	
	public void loadLuggage(cmcken22_Lab04_Flight fObject){
		int count = 0;
		for (int i = top; i >= 0; i--){
			if (stack[i] != null){
				count = 1;
			}
		}
		if (count == 0){
			JOptionPane.showMessageDialog(null, "No luggage in stack!");
		}
		else{
			JOptionPane.showMessageDialog(null, stack[top].name + " " + stack[top].ID + " Luggage loaded on to flight: " + fObject.flightName);
			cmcken22_Lab04_Passenger[] tempArray = luggageOnBoard;
			luggageOnBoard = new cmcken22_Lab04_Passenger[1 + tempArray.length];
			for (int index = 0; index < tempArray.length; index++){
				luggageOnBoard[index] = tempArray[index];
			}
			luggageOnBoard[luggageOnBoard.length-1] = stack[top];
			stack[top] = null;
			top--;
			printLuggage(fObject);
		}
	}
}
