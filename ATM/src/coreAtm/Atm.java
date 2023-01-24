package coreAtm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Atm {
	static int balance=10000;
	static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true) {
			
			
			System.out.println("Enter 1 for create account. \nEnter 2 for check balance. \nEnter 3 for withdraw. \nEnter 4 for deposite.\nEnter 5 for show details \nEnter 6 for Exite");
			
			int choice=sc.nextInt();
		switch(choice) {
		case 1:
			Atm at4=new Atm();
			at4.createAccount();
			
			break;
			
		case 2:
			
			Atm at=new Atm();
			at.showBalance();
			break;
			 
		case 3:
			System.out.println("Enter your Account Number");
			
			Atm at1=new Atm();
			at1.withdrawBal();
			break;
			
			
		case 4:
			Atm at2=new Atm();
			at2.depositeAmt();
			break;
			
			
		case 5:
			Atm at5=new Atm();
			at5.showDetails();
			
			
		case 6:
			Atm at3=new Atm();
			at3.exite();
			
		
		}
		}
	}
	
//	****************All Methods************************
	
	public void createAccount() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","gatesmash","gatesmash");
			System.out.println("Enter name,mobileno,balance,gender,accno");
			String name=sc.next();
			long mob=sc.nextLong();
			double bal=sc.nextDouble();
			String gen=sc.next();
			int accno=sc.nextInt();
			
			PreparedStatement ps=con.prepareStatement("insert into bank values(?,?,?,?,?)");
			ps.setString(1,name);
			ps.setLong(2,mob);
			ps.setDouble(3,bal);
			ps.setString(4,gen);
			ps.setInt(5, accno);
			
			int i=ps.executeUpdate();
			System.out.println(i+" Account created successfully.........!\n");
			con.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void showBalance() {
		try {
			System.out.println("Enter your Account number.\n");
			int accno=sc.nextInt();
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","gatesmash","gatesmash");
			
			
			PreparedStatement ps=con.prepareStatement("select balance from bank where accno=?");
			
			ps.setInt(1,accno);
			ResultSet rs=ps.executeQuery();
			
			
			while(rs.next()) {
				for(int i=1;i<=1;i++) {
					System.out.println("Your Balance is : "+rs.getString(i)+"\n");
				}
			}
			con.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}

	}
	
	public void withdrawBal() {
		System.out.println("Enter account number");
		int accno=sc.nextInt();
		System.out.println("Enter withdraw amount");
		int withamt=sc.nextInt();
		int dbamt=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","gatesmash","gatesmash");
			
			PreparedStatement ps=con.prepareStatement("select balance from bank where accno=?");
			ps.setInt(1, accno);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				for(int i=1;i<=1;i++) {
					dbamt=rs.getInt(i);
					dbamt=dbamt-withamt;
					System.out.println(dbamt);
				}
			}
			PreparedStatement ps1=con.prepareStatement("update bank set balance='"+dbamt+"' where accno=?");
			ps1.setInt(1,accno);
			int i=ps1.executeUpdate();
			System.out.println(i+" withdraw succesfully...........\nplzz collect your cash");
			con.close();
			
			
			
		}catch (Exception e) {	
			System.out.println(e);
		}
		
		
	}
	
	public void depositeAmt() {
		System.out.println("enter acco number");
		int accno=sc.nextInt();
		System.out.println("Enter deposite amount");
		int deposite=sc.nextInt();
		int dbamt=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","gatesmash","gatesmash");
			
			PreparedStatement ps=con.prepareStatement("select balance from bank where accno=?");
			ps.setInt(1, accno);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				for(int i=1;i<=1;i++) {
					dbamt=rs.getInt(i);
					dbamt=dbamt+deposite;
					System.out.println(dbamt);
				}
			}
			PreparedStatement ps1=con.prepareStatement("update bank set balance='"+dbamt+"' where accno=?");
			ps1.setInt(1,accno);
			int i=ps1.executeUpdate();
			System.out.println(i+" deposite succesfully...........\nplzz collect your cash");
			con.close();
			
			
			
		}catch (Exception e) {	
			System.out.println(e);
		}
		
		
	}
	
	public void showDetails() {
		System.out.println("Enter Accountnumber : ");
		int accno=sc.nextInt();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","gatesmash","gatesmash");
			
			PreparedStatement ps=con.prepareStatement("select * from bank where accno=?");
			ps.setInt(1, accno);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				for(int i=1;i<=5;i++) {
					System.out.println(rs.getString(i));
				}
							
						}
			System.out.println();
			
			
			
			con.close();
			
			
			
		}catch (Exception e) {	
			System.out.println(e);
		}
		
	}
	
	public void exite() {
		System.out.println("Thank You!  :)");
		System.exit(0);
		
	}
	
	
}
