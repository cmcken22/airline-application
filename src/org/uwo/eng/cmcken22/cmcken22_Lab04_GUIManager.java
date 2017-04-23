package org.uwo.eng.cmcken22;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class cmcken22_Lab04_GUIManager {

	private JFrame frame;
	private JFrame frame1;
	private JTextField textField;
	static int pCount = 1;
	
	static int fIndex = 7;
	
	cmcken22_Lab04_Passenger p1 = new cmcken22_Lab04_Passenger("Larry", 1111);
	cmcken22_Lab04_Passenger p2 = new cmcken22_Lab04_Passenger("Jill", 2222);
	cmcken22_Lab04_Passenger p3 = new cmcken22_Lab04_Passenger("John", 3333);
	cmcken22_Lab04_Passenger p4 = new cmcken22_Lab04_Passenger("Mike", 4444);
	cmcken22_Lab04_Passenger p5 = new cmcken22_Lab04_Passenger("Sarah", 5555);
	cmcken22_Lab04_Passenger p6 = new cmcken22_Lab04_Passenger("Dan", 6666);
	cmcken22_Lab04_Passenger p7 = new cmcken22_Lab04_Passenger("Julia", 7777);
	cmcken22_Lab04_Passenger p8 = new cmcken22_Lab04_Passenger("Bradley", 8888);
	cmcken22_Lab04_Passenger p9 = new cmcken22_Lab04_Passenger("Rita", 9999);
	cmcken22_Lab04_Passenger p10 = new cmcken22_Lab04_Passenger("Bill", 1010);
	
	//hard code flight data
	cmcken22_Lab04_Flight flight1 = new cmcken22_Lab04_Flight("WS1413");
	cmcken22_Lab04_Flight flight2 = new cmcken22_Lab04_Flight("WS1050");
	cmcken22_Lab04_Flight flight3 = new cmcken22_Lab04_Flight("WS1411");
	cmcken22_Lab04_Flight flight4 = new cmcken22_Lab04_Flight("WS1021");
	cmcken22_Lab04_Flight flight5 = new cmcken22_Lab04_Flight("WS1036");
	
	//organizes the passenger objects into an array
	cmcken22_Lab04_Passenger[] passengerArray = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10};
	
	//organizes the flight objects into an array
	cmcken22_Lab04_Flight[] flightArray = {flight1,flight2,flight3,flight4,flight5};
	
	cmcken22_Lab04_Airline airCanada = new cmcken22_Lab04_Airline(passengerArray, flightArray);
	cmcken22_Lab04_WaitingList waiting = new cmcken22_Lab04_WaitingList();
	private JTextField textField_1;
	private JTextField txtEnterText;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cmcken22_Lab04_GUIManager window = new cmcken22_Lab04_GUIManager();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*	
	public int determinePassenger(int IDnum)
	{
		for (int i = 0; i<passengerArray.length; i++)
		{
			if (passengerArray[i].ID == IDnum)
			{
				return i;
			}
		}
		return -1;
	}
	*/
	
	public void storeFlightIndex(int flightIndex){
		fIndex = flightIndex;
	}
	
	public int getFlightIndex(){
		return fIndex;
	}
	
	public void CreatePassenger(String name, int ID, cmcken22_Lab04_Airline airline){
		cmcken22_Lab04_Passenger newPassenger = new cmcken22_Lab04_Passenger(name, ID);
		airline.addPassenger(newPassenger);
	}
	
	public boolean validateID(int ID){
		for (int i = 0; i<passengerArray.length; i++){
			if (passengerArray[i].ID == ID){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Create the application.
	 */
	public cmcken22_Lab04_GUIManager() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 428, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCreatePassenger = new JButton("New Passenger");
		btnCreatePassenger.addActionListener(new ActionListener() // Create passenger button*******************************************
		{
			public void actionPerformed(ActionEvent arg0) {
				//pCount++;
				String pName = null;
				pName = JOptionPane.showInputDialog("Enter Passenger Name: ", " ");
				if (pName != null){
					String strID = null;
					strID = JOptionPane.showInputDialog("Enter Passenger ID: ", " ");
					if (strID != null){
						try {
							int pID = Integer.parseInt(strID);
							while (!validateID(pID)){
								strID = JOptionPane.showInputDialog("Error! ID Taken. " + "\n" + "Enter Passenger ID: ", " ");
								pID = Integer.parseInt(strID);
							}
							CreatePassenger(pName, pID, airCanada);
							airCanada.printPassengerAirlineDataBase();
						}
						catch (Exception e1){
							JOptionPane.showMessageDialog(null, "Invalid Entry!");
						}
					}
				}
			}
		});
		btnCreatePassenger.setBounds(213, 6, 215, 55);
		frame.getContentPane().add(btnCreatePassenger);
		
		JButton btnListPassengers = new JButton("List Passengers");
		btnListPassengers.addActionListener(new ActionListener() // List Passengers Button***********************************************************  
		{
			public void actionPerformed(ActionEvent e) {
				String list = "List of Passengers: " + "\n";
				list += "ID:       Passenger: \n";
				list += "-------------------\n";
				System.out.print("CLICKED \n"); 
				
				for (int i = 0; i<airCanada.getPassengerSize(); i++){
					list += airCanada.passengerList[i].ID +"       " + airCanada.passengerList[i].name +"\n"; 
				}
				JOptionPane.showMessageDialog (null, list);
			}
		});
		btnListPassengers.setBounds(213, 61, 215, 55);
		frame.getContentPane().add(btnListPassengers);
		
		JButton btnListFlights = new JButton("List Flights");
		btnListFlights.addActionListener(new ActionListener() // List Flights button clicked***********************************************
		{
			public void actionPerformed(ActionEvent e) {
				String list = "List of Flights: " + "\n";
				System.out.print("CLICKED \n"); 
				
				for (int i = 0; i<airCanada.getFlightSize(); i++){
					list += airCanada.flights[i].flightName + "\n";
				}
				JOptionPane.showMessageDialog(null, list);
			}
		});
		
		btnListFlights.setBounds(0, 61, 215, 55);
		frame.getContentPane().add(btnListFlights);
		
		JButton btnAddPassenger = new JButton("Add a Passenger to a Flight");
		btnAddPassenger.addActionListener(new ActionListener() // add passenger to flight button clicked***********************************************
		{
			public void actionPerformed(ActionEvent e) {
				System.out.print("ADD PASS CLICKED \n");
				int flightIndex = 7;
				String flightName = null;
				flightName = JOptionPane.showInputDialog("Enter Flight: ", " ");
				if (flightName != null){
					
					flightIndex = airCanada.determineFlight(flightName);
					
					if (flightIndex == -1){ 
						JOptionPane.showMessageDialog (null, "Flight does not exist!");
					}
					
					else if (flightIndex != -1){
						int isFlightFull = airCanada.flights[flightIndex].isFlightFull();
						if (isFlightFull == 1){
							try{
							    int ID;
							    int IDindex;
							    String strID = null;
							    strID = JOptionPane.showInputDialog("Enter Passenger ID: ", " ");
							    if (strID != null){
							    	ID = Integer.parseInt(strID);
									IDindex = airCanada.determinePassenger(ID);
										
									if (IDindex == -1){
										JOptionPane.showMessageDialog(null, "Passenger does not exist!");
									}
									else if (IDindex != -1){
										System.out.print("flight = " + airCanada.flights[flightIndex].flightName + "\n");
										System.out.print(airCanada.passengerList[IDindex].name + "\n");
										airCanada.flights[flightIndex].addPassenger(airCanada.passengerList[IDindex], 1);
									}
							    }
							}
							catch(Exception e1){
								JOptionPane.showMessageDialog(null, "Error! Invlaid entry!");
							}
						}
						if (isFlightFull == 2){
							int ID;
							int IDindex;
							String strID = null;
							strID = JOptionPane.showInputDialog("Enter Passenger ID for waiting list: ", " ");
							try{
								if (strID != null){
									ID = Integer.parseInt(strID);
									IDindex = airCanada.determinePassenger(ID);
									
									if (IDindex == -1){
										JOptionPane.showMessageDialog(null, "Passenger does not exist!");
									}
									if (IDindex != -1){
										System.out.print("flight = " + airCanada.flights[flightIndex].flightName + "\n");
										System.out.print(airCanada.passengerList[IDindex].name + "\n");
										boolean onBoard = airCanada.flights[flightIndex].checkDuplicate(airCanada.passengerList[IDindex]);
										if (onBoard == false){
											airCanada.flights[flightIndex].flightWaitingList().AddtoWaitingList(airCanada.passengerList[IDindex]);
										}
										else{
											JOptionPane.showMessageDialog(null, "Passenger already on board!");
										}
									}
								}
							}
							catch(Exception e1){
								JOptionPane.showMessageDialog(null, "Error! Invlaid entry!");
							}
						}
					}
				}
			}
		});
		btnAddPassenger.setBounds(0, 122, 215, 55);
		frame.getContentPane().add(btnAddPassenger);
		
		JButton btnDropPassengerFrom = new JButton("Drop Passenger from a Flight");
		btnDropPassengerFrom.addActionListener(new ActionListener() // drop a passenger button clicked***********************************************
		{
			public void actionPerformed(ActionEvent e) {
				int flightIndex = 7;
				String flightName = null;
				flightName = JOptionPane.showInputDialog("Enter Flight: ", " ");
				if (flightName != null){
					flightIndex = airCanada.determineFlight(flightName);
					
					if (flightIndex == 7){
						JOptionPane.showMessageDialog(null, "Flight does not exist!");
					}
					if (flightIndex != 7){
						if (airCanada.flights[flightIndex].getSize() == 0){
							JOptionPane.showMessageDialog(null, "Flight is empty!");
						}
						if (airCanada.flights[flightIndex].getSize() != 0){
							int ID;
							int IDindex;
							String strID = null;
							strID = JOptionPane.showInputDialog("Enter Passenger ID: ", " ");
							try {
								if (strID != null){
									ID = Integer.parseInt(strID);
									//System.out.println("ID of passenger to be removed = " + ID);
									IDindex = airCanada.flights[flightIndex].determinePassenger(ID);
									
									if (IDindex == -1){
										JOptionPane.showMessageDialog(null, "Passenger not found on Flight!");
									}
									if (IDindex != -1){
										//System.out.println("ID of passenger to be removed after IDindex is determined = " + airCanada.flights[flightIndex].passengers[IDindex].ID);
										airCanada.flights[flightIndex].removePassenger(airCanada.flights[flightIndex].passengers[IDindex]);
										JOptionPane.showMessageDialog (null, "Passenger dropped successfully");
										boolean result = airCanada.flights[flightIndex].flightWaitingList().isEmpty();
										System.out.println("Result = " + result);
										// if the flight waiting list is not empty, continue
										if(result == false) {
											cmcken22_Lab04_Passenger passenger = airCanada.flights[flightIndex].flightWaitingList().getFirstinLine();
											System.out.println("Passenger = " + passenger.name);
											airCanada.flights[flightIndex].updateFlight(passenger);
										}
									}
								}
							}
							catch(Exception e1){
								JOptionPane.showMessageDialog(null, "Error! Invlaid entry!");
							}
						}
					}
				}
			}
		});
		btnDropPassengerFrom.setBounds(0, 177, 215, 55);
		frame.getContentPane().add(btnDropPassengerFrom);
		
		JButton btnListPassengersOn = new JButton("List Passengers on a Flight");
		btnListPassengersOn.addActionListener(new ActionListener() // list passengers on flight button clicked***********************************************
		{
			public void actionPerformed(ActionEvent e) {
				int flightIndex = 7;
				String flightName = null;
				flightName = JOptionPane.showInputDialog("Enter Flight: ", " ");
				
				if(flightName != null) {
					flightIndex = airCanada.determineFlight(flightName);

					if (flightIndex == 7){
						JOptionPane.showMessageDialog (null, "Flight does not exist!");
					}
					if (flightIndex != 7){
						if (airCanada.flights[flightIndex].passengers.length == 0){
							JOptionPane.showMessageDialog (null, "Flight is empty!");
						}
						if (airCanada.flights[flightIndex].passengers.length != 0){
							String list = "Flight: " + airCanada.flights[flightIndex].flightName + "\n";
							list += "ID:       Passenger: \n";
							list += "-------------------\n";
							for(int i = 0; i<airCanada.flights[flightIndex].passengers.length; i++)
							{
								list += airCanada.flights[flightIndex].passengers[i].ID + "       " + airCanada.flights[flightIndex].passengers[i].name + " (" + airCanada.flights[flightIndex].passengers[i].position + ")\n";
							}
							JOptionPane.showMessageDialog (null, list);
						}
					}
				}
			}
		});
		btnListPassengersOn.setBounds(0, 232, 215, 55);
		frame.getContentPane().add(btnListPassengersOn);
		
		JButton btnChangeFlightCapacity = new JButton("Change Flight Capacity");
		btnChangeFlightCapacity.addActionListener(new ActionListener() // Change flight capacity**********************************************************
		{
			public void actionPerformed(ActionEvent e){
				int flightIndex = 7;
				String flightName = null;
				flightName = JOptionPane.showInputDialog("Enter Flight: ", " ");
				if(flightName != null) {
					flightIndex = airCanada.determineFlight(flightName);
					
					if (flightIndex == 7){
						JOptionPane.showMessageDialog (null, "Flight does not exist!");
					}
					if (flightIndex != 7){
						try{
							int newCap;
							String strCap = null;
							strCap = JOptionPane.showInputDialog("Enter flight capacity: ", " ");
							if (strCap != null){
								newCap = Integer.parseInt(strCap);
								int capChangeResult = airCanada.flights[flightIndex].changeCapacity(newCap);
								if (capChangeResult == 1){
									while (!airCanada.flights[flightIndex].isFull()){
										if(!airCanada.flights[flightIndex].flightWaitingList().isEmpty()){
											airCanada.flights[flightIndex].updateFlight(airCanada.flights[flightIndex].flightWaitingList().getFirstinLine());
										}
									}
								}
								if (capChangeResult == 2){
									//size has been changed to a smaller size. Some passengers must be kicked off the flight and placed on the waiting list
									airCanada.flights[flightIndex].addToQueue();
								}
							}
						}
						catch(Exception e1){
							JOptionPane.showMessageDialog(null, "Error! Invlaid entry!");
						}
					}
				}
			}
		});
		btnChangeFlightCapacity.setBounds(0, 297, 215, 55);
		frame.getContentPane().add(btnChangeFlightCapacity);
		
		JButton btnPrintWaitingList = new JButton("Print Waiting List");
		btnPrintWaitingList.addActionListener(new ActionListener() // Print waiting list button**************************************************
		{
			public void actionPerformed(ActionEvent e) {
				int flightIndex = 7;
				String flightName = null;
				flightName = JOptionPane.showInputDialog("Enter Flight: ", " ");
				if (flightName != null) {
					flightIndex = airCanada.determineFlight(flightName);
					if (flightIndex == 7){
						JOptionPane.showMessageDialog (null, "Flight does not exist!");
					}
					if (flightIndex != 7){
						airCanada.flights[flightIndex].flightWaitingList().printWaitingList(airCanada.flights[flightIndex]);
					}
				}
			}
		});
		btnPrintWaitingList.setBounds(213, 232, 215, 55);
		frame.getContentPane().add(btnPrintWaitingList);
		
		JButton btnRemoveFromWaiting = new JButton("Remove from Waiting List");
		btnRemoveFromWaiting.addActionListener(new ActionListener() //Remove from Waiting list*******************************************
		{
			public void actionPerformed(ActionEvent e) {
				int flightIndex = 7;
				String flightName = null;
				flightName = JOptionPane.showInputDialog("Enter Flight: ", " ");
				if (flightName != null){
					flightIndex = airCanada.determineFlight(flightName);
					if (!airCanada.flights[flightIndex].flightWaitingList().isEmpty()){
						if (flightIndex == 7){
							JOptionPane.showMessageDialog (null, "Flight does not exist!");
						}
						if (flightIndex != 7){
							try{
								int ID;
								int IDindex;
								String strID = null;
								strID = JOptionPane.showInputDialog("Enter Passenger ID: ", " ");
								if (strID != null){
									ID = Integer.parseInt(strID);
									IDindex = airCanada.flights[flightIndex].flightWaitingList().determinePassenger(ID);
									System.out.println("IDindex = " + IDindex);
									
									if (IDindex == -1){
										JOptionPane.showMessageDialog(null, "Passenger does not exist!");
									}
									if (IDindex != -1){
										airCanada.flights[flightIndex].flightWaitingList().removeFromWait(airCanada.flights[flightIndex].flightWaitingList().queue[IDindex]);
									}
								}
							}
							catch(Exception e1){
								JOptionPane.showMessageDialog(null, "Error! Invlaid entry!");
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Waiting List is empty!");
					}
				}
			}
		});
		btnRemoveFromWaiting.setBounds(213, 177, 215, 55);
		frame.getContentPane().add(btnRemoveFromWaiting);
		
		JButton btnAddAPassenger = new JButton("Add a Passenger to Waiting List");
		btnAddAPassenger.addActionListener(new ActionListener() // add passenger to waiting list*******************************************************************
		{
			public void actionPerformed(ActionEvent arg0) {
				int flightIndex = 7;
				String flightName = null;
				flightName = JOptionPane.showInputDialog("Enter Flight for Waiting List: ", " ");
				if (flightName != null){
					flightIndex = airCanada.determineFlight(flightName);
					
					if (flightIndex == -1){
						JOptionPane.showMessageDialog (null, "Flight does not exist!");
					}
					if (flightIndex != -1){
						try{
							int ID;
							int IDindex = -1;
							int doubleCheck = -1;
							String strID = null;
							strID = JOptionPane.showInputDialog("Enter Passenger ID: ", " ");
							if (strID != null){
								ID = Integer.parseInt(strID);
								IDindex = airCanada.determinePassenger(ID);
								doubleCheck = airCanada.flights[flightIndex].determinePassenger(ID);
								if (doubleCheck != -1){
									JOptionPane.showMessageDialog(null, "Passenger is already on flight and cannot be added to the waiting list!");
								}
								if (doubleCheck == -1){
									if (IDindex == -1){
										JOptionPane.showMessageDialog(null, "Passenger does not exist!");
									}
									if (IDindex != -1){
										System.out.print("flight = " + airCanada.flights[flightIndex].flightName + "\n");
										System.out.print(airCanada.passengerList[IDindex].name + "\n");
										airCanada.flights[flightIndex].flightWaitingList().AddtoWaitingList(airCanada.passengerList[IDindex]);
									}
								}
							}
						}
						catch(Exception e1){
							JOptionPane.showMessageDialog(null, "Error! Invlaid entry!");
						}
					}
				}
			}
		});
		btnAddAPassenger.setBounds(213, 122, 215, 55);
		frame.getContentPane().add(btnAddAPassenger);
		
		JButton btnNewFlight = new JButton("New Flight");
		btnNewFlight.addActionListener(new ActionListener() // New Flight Button********************************************************************************
		{
			public void actionPerformed(ActionEvent e) {
				String fName = null;
				fName = JOptionPane.showInputDialog("Enter Name of Flight: ");
				if (fName != null){
					cmcken22_Lab04_Flight newFlight = new cmcken22_Lab04_Flight(fName);
					airCanada.addFlight(newFlight);
				}
			}
		});
		btnNewFlight.setBounds(0, 6, 215, 55);
		frame.getContentPane().add(btnNewFlight);
		
		JButton btnHandleLuggage = new JButton("Handle Luggage");
		btnHandleLuggage.addActionListener(new ActionListener() // Handle Luggage Button***********************************************************************
		{
			
			public void actionPerformed(ActionEvent e) {
				frame1 = new JFrame();
				frame1.setBounds(300, 200, 428, 150);
				frame1.getContentPane().setLayout(null);
				frame1.setVisible(true);
				
				JButton btnCheckIN = new JButton("Check in Luggage");
				btnCheckIN.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) //Check in Luggage******************************************************************************
					{ 
						int flightIndex = -1;
						String flightName = null;
						flightName = JOptionPane.showInputDialog("Enter Flight: ", " ");
						if (flightName != null){
							flightIndex = airCanada.determineFlight(flightName);
							
							if (flightIndex == -1){
								JOptionPane.showMessageDialog (null, "Flight does not exist!");
							}
							if (flightIndex != -1){
								try{
								    int ID;
								    int IDindex;
								    String strID = null;
								    strID = JOptionPane.showInputDialog("Enter Passenger ID: ", " ");
								    if (strID != null){
								    	ID = Integer.parseInt(strID);
										IDindex = airCanada.determinePassenger(ID);
											
										if (IDindex == -1){
											JOptionPane.showMessageDialog(null, "Passenger does not exist!");
										}
										if (IDindex != -1){
											System.out.print("flight = " + airCanada.flights[flightIndex].flightName + "\n");
											System.out.print(airCanada.passengerList[IDindex].name + "\n");
											airCanada.flights[flightIndex].luggage().AddtoStack(airCanada.passengerList[IDindex]);
										}
								    }
								}
								catch(Exception e1){
									JOptionPane.showMessageDialog(null, "Error! Invlaid entry!");
								}
							}
						}
					}
				});
				btnCheckIN.setBounds(0, 6, 215, 55);
				frame1.getContentPane().add(btnCheckIN);
				
				JButton btnLoadLuggage = new JButton("Load Luggage");
				btnLoadLuggage.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) //Load Luggage******************************************************************************
					{ 
						int flightIndex = -1;
						String flightName = null;
						flightName = JOptionPane.showInputDialog("Enter Flight: ", " ");
						if (flightName != null){
							flightIndex = airCanada.determineFlight(flightName);
							
							if (flightIndex == -1){
								JOptionPane.showMessageDialog (null, "Flight does not exist!");
							}
							if (flightIndex != -1){
								airCanada.flights[flightIndex].luggage().loadLuggage(airCanada.flights[flightIndex]);
							}
						}

					}
				});
				btnLoadLuggage.setBounds(215, 6, 215, 55);
				frame1.getContentPane().add(btnLoadLuggage);
				
				JButton btnPrintLuggage = new JButton("See Waiting Luggage");
				btnPrintLuggage.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) // See Waiting Luggage******************************************************************************
					{ 
						int flightIndex = -1;
						String flightName = null;
						flightName = JOptionPane.showInputDialog("Enter Flight: ", " ");
						if (flightName != null){
							flightIndex = airCanada.determineFlight(flightName);
							
							if (flightIndex == -1){
								JOptionPane.showMessageDialog (null, "Flight does not exist!");
							}
							if (flightIndex != -1){
								int isFlightFull = airCanada.flights[flightIndex].isFlightFull();
								if (isFlightFull == 1){
									airCanada.flights[flightIndex].luggage().printLuggage(airCanada.flights[flightIndex]);
								}
							}
						}

					}
				});
				btnPrintLuggage.setBounds(107, 61, 215, 55);
				frame1.getContentPane().add(btnPrintLuggage);
			}
		});
		btnHandleLuggage.setBounds(213, 297, 215, 55);
		frame.getContentPane().add(btnHandleLuggage);
		
		JLabel label = new JLabel("-----------------------------------------------------------------------------------------");
		label.setBounds(0, 110, 488, 16);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("-----------------------------------------------------------------------------------------");
		lblNewLabel.setBounds(0, 283, 496, 16);
		frame.getContentPane().add(lblNewLabel);
	}
}
