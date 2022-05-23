package functions;

import interfaces.Operations;
import java.util.ArrayList;
import java.util.Collections;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;


import java.util.Scanner;
import java.util.Properties;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Dbfunctions implements Operations {
	static Logger log = Logger.getLogger(Dbfunctions.class.getName());
	
	public void listMed()
	{
		log.info("inside Dbfunctions -listMed()");
		
		Connection conn = null;
		//Properties config = new Properties();
		
		System.out.println(" -------------------------------");
    	System.out.println("| Medicine_Name\t\t|");
    	System.out.println(" -------------------------------");
		
		try {
			
			//FileReader fr = new FileReader("properties\\DBconfig.properties");
			//config.load(fr);
			
			//String url = config.getProperty("url");
			//String DBuser = config.getProperty("DBuser");
			//String passwd = config.getProperty("passwd");
			conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-N0BE3ENR;databaseName=Pqharmacy;", "root", "msis@123");
			
			if (conn != null) {
				Statement sta = conn.createStatement();
				
				ResultSet rs = sta.executeQuery("select * from Medicine");
            	while (rs.next()) {
            	
            		String mID = rs.getString("Medicine_ID");
            		String mName = rs.getString("Medicine_name");
            		System.out.println("| "  + mName + "\t\t|");
            		
            	} // end while
			} // end if
			System.out.println(" -------------------------------");
			
			 // calling function to search Medicine
		} // end try
		
		
		catch (SQLException ex) {
			System.out.println("Something went wrong try again later :(");
			log.error(ex + "SQL error");
			//ex.printStackTrace();
       
		} finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
            	System.out.println("Something went wrong try again later :(");
    			log.error(ex+ "SQL error");
    			//e.printStackTrace();
            }
        } // end finally 
		
	}
	
	public void searchMed(String medName)
	{
		
		
		log.info("inside Dbfunctions-searchMed()");
		
		Connection conn = null;
		//Properties config = new Properties();
		
		System.out.println(" ---------------------------------------");
    	System.out.println("| Medicine_ID\tMedicine_Name\tQuantity\t|");
    	System.out.println(" ----------------------------------------");
		
		try {
			
			//FileReader fr = new FileReader("properties\\DBconfig.properties");
			//config.load(fr);
			
			//String url = config.getProperty("url");
			//String DBuser = config.getProperty("DBuser");
			//String passwd = config.getProperty("passwd");
			conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-N0BE3ENR;databaseName=Pharmacy;", "root", "msis@123");
			
			if (conn != null) {
				Statement sta = conn.createStatement();
				
				ResultSet rs = sta.executeQuery("select * from Medicine where Medicine_Name ='"+medName+"'");
				//ResultSet rs = sta.executeQuery("select * from Medicine where Medicine_Name =' " +medName+"'");
            	while (rs.next()) {
            	
            		String mID = rs.getString("Medicine_ID");
            		String mName = rs.getString("Medicine_name");
            		int mquantity = rs.getInt("Quantity");
            		System.out.println("| " + mID + "\t\t" + mName + "\t\t" +mquantity+ "\t\t|");
            		
            	} // end while
			} // end if
			System.out.println(" ------------------------------------");
			
			 // calling function to show hall for a particular movie
		} // end try
		
		
		catch (SQLException ex) {
			System.out.println("Something went wrong try again later :(");
			log.error(ex + "SQL error");
			//ex.printStackTrace();
       
		} finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
            	System.out.println("Something went wrong try again later :(");
    			log.error(ex+ "SQL error");
    			//e.printStackTrace();
            }
        } // end finally
		
	}
	
	public void buyMed()
	{
		log.info("inside Dbfunctions-buyMed()");
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the medicine you want to buy");
		String med = s.next();
		String mID="";
		int Med_Amount=0;
		
		log.info("inside DB operation-searchMed()");
		
		Connection conn = null;
		//Properties config = new Properties();
		
		System.out.println(" -----------------------------");
    	System.out.println("| Medicine_ID\tMedicine_Name\t|");
    	System.out.println(" -----------------------------");
		
		try {
			
			//FileReader fr = new FileReader("properties\\DBconfig.properties");
			//config.load(fr);
			
			//String url = config.getProperty("url");
			//String DBuser = config.getProperty("DBuser");
			//String passwd = config.getProperty("passwd");
			conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-N0BE3ENR;databaseName=Pharmacy;", "root", "msis@123");
			
			if (conn != null) {
				Statement sta = conn.createStatement();
				
				ResultSet rs = sta.executeQuery("select * from Medicine where Medicine_Name ='"+med+"'");
				//ResultSet rs = sta.executeQuery("select * from Medicine where Medicine_Name =' " +medName+"'");
            	while (rs.next()) {
            	
            		mID = rs.getString("Medicine_ID");
            		String mName = rs.getString("Medicine_name");
            		Med_Amount = rs.getInt("Medicine_price");
            		System.out.println("| " + mID + "\t\t" + mName + "\t\t|");
            		
            	} // end while
			} // end if
			System.out.println(" -------------------------------");
			
			System.out.println("Want to confirm your purchase? (y/n)");
			char opt = s.next().charAt(0);
			
			if(opt == 'y') {
				
				System.out.println("Updation done in ORDERS table\n thank you :)");
				//System.exit(0);
				System.out.println("Are you an existing Customer? (y/n) ");
				char res = s.next().charAt(0);
				if(res=='n')
				{
					addCustomer(mID,Med_Amount);
					
				}
				
			}
			else {
				System.out.println("No problem ...");
				System.exit(0);
			}
			 // calling function to show hall for a particular movie
		} // end try
		
		
		catch (SQLException ex) {
			System.out.println("Something went wrong try again later :(");
			log.error(ex + "SQL error");
			//ex.printStackTrace();
       
		} finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
            	System.out.println("Something went wrong try again later :(");
    			log.error(ex+ "SQL error");
    			//e.printStackTrace();
            }
        } // end finally
	}
	
	
	public void addCustomer(String Medicine_ID,int Med_Amount) {
		log.info("inside Dbfunctions-addCustomers()");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the customer name");
		String Customer_name=sc.next();
		System.out.println("Enter the Phone Number");
		int Customer_phno=sc.nextInt();
		String Customer_ID="C"+Customer_phno;
		System.out.println(Customer_ID);

		Connection conn = null;
		//Properties config = new Properties();
		
		try {
			
			//FileReader fr = new FileReader("properties\\DBconfig.properties");
			//config.load(fr);
			
			//String url = config.getProperty("url");
			//String DBuser = config.getProperty("DBuser");
			//String passwd = config.getProperty("passwd");
			conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-N0BE3ENR;databaseName=Pharmacy;", "root", "msis@123");
			
			if (conn != null) {
				Statement sta = conn.createStatement();

				//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				//LocalDateTime conTime = LocalDateTime.now();



				String query = "insert into Customer (Customer_ID, Customer_name, Customer_phno)" +" values ('"+Customer_ID+"', '"+Customer_name+"', "+Customer_phno+")";
				//String query = "insert into Customer (Customer_ID, Customer_name, Customer_phno)" +" values ('"+Customer_ID+"', '"+Customer_name+"', "+ Customer_phno)";

				//ResultSet rs = sta.executeQuery(query);
				sta.executeUpdate(query);
			} // end if
			//System.out.println(" -------------------------------");
			
			//System.out.println(Med_Amount);
			updateOrders(Customer_ID,Medicine_ID, 1,Med_Amount); // calling function to search Medicine
		} // end try
		
		
		catch (SQLException ex) {
			System.out.println("Something went wrong try again later :(");
			log.error(ex + "SQL error");
			ex.printStackTrace();
       
		} finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
            	System.out.println("Something went wrong try again later :(");
    			log.error(ex+ "SQL error");
    			//e.printStackTrace();
            }
        } // end finally 
	}
	
	
	public int randomNum() {
		
		int rnum = 0 ;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i=1; i<10; i++) {
		list.add(i);
		}
		Collections.shuffle(list);
		for (int i=0; i<2; i++) {
		//System.out.print(list.get(i));
			rnum = list.get(i);
		}
		
		return rnum;
	}
	
	public void updateOrders(String Customer_ID,String Medicine_ID, int Quantity , int Amount )
	{
		Connection conn = null;
		//Properties config = new Properties();
		int rnum = randomNum();
		String Order_ID = "O" + rnum + Medicine_ID;
		
		try {
			
			//FileReader fr = new FileReader("properties\\DBconfig.properties");
			//config.load(fr);
			
			//String url = config.getProperty("url");
			//String DBuser = config.getProperty("DBuser");
			//String passwd = config.getProperty("passwd");
			conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-N0BE3ENR;databaseName=Pharmacy;", "root", "msis@123");
			
			if (conn != null) {
				Statement sta = conn.createStatement();

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				LocalDateTime conTime = LocalDateTime.now();



				String query = "insert into Orders (Order_ID,Customer_ID, Medicine_ID, Amount,PurchaseDate)" +" values ('"+Order_ID+"','"+Customer_ID+"', '"+Medicine_ID+"', "+Amount+",getdate())";
				//String query = "insert into Customer (Customer_ID, Customer_name, Customer_phno)" +" values ('"+Customer_ID+"', '"+Customer_name+"', "+ Customer_phno)";

				//ResultSet rs = sta.executeQuery(query);
				sta.executeUpdate(query);
			}
			System.out.println("The Payment Amount:" +Amount);
		
	}
		catch (SQLException ex) {
			System.out.println("Something went wrong try again later :(");
			log.error(ex + "SQL error");
			ex.printStackTrace();
       
		} finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
            	System.out.println("Something went wrong try again later :(");
    			log.error(ex+ "SQL error");
    			//e.printStackTrace();
            }
        } // end finally 
	}
	}


