package com.hibernate.persistence;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.entity.Customers;
import com.hibernate.entity.Orders;

/**
 * This class provides options to search data in the given criteria.
 * * 1. Search Customers by Order Date
 * * 2. Search Orders by Customer Name
 * @author WYou
 * @since 16.5.0
 */
public class SearchData {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			printInfo();
			int option = -1;
			Scanner keyboard = new Scanner(System.in);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//need to be yyyy-MM-dd (case sensitive)
			option = Integer.parseInt(keyboard.nextLine());
			String criteria = "";
			while (option != 3) {
				switch (option) {
				case 1:
					tx = session.beginTransaction();
					System.out.println("Input Order Date in YYYY-MM-DD format:");
					System.out.println();
					criteria = keyboard.nextLine();
					@SuppressWarnings("unchecked")
					List<Customers> getcustomers = session.createQuery(
							"SELECT customers FROM Customers as customers" + " JOIN  customers.order ord WHERE ord.orderDate=:orderDate").setParameter(
									"orderDate",
									df.parse(criteria)).list();
					for (Iterator<Customers> iterator = getcustomers.iterator(); iterator.hasNext();) {
						Customers cust = (Customers) iterator.next();
						System.out.println("Customer ID: " + cust.getCustomerID());
						System.out.println("Customer Name: " + cust.getCustomerName());
						System.out.println("Contact: " + cust.getContactInformation());
						System.out.println("Address: " + cust.getAddress());

						System.out.println("----------------------------------------");
					}
					tx.commit();
					break;
				case 2:
					tx = session.beginTransaction();
					System.out.println("Input Customer Name(Either first or last name):");
					System.out.println();
					criteria = keyboard.nextLine();
					@SuppressWarnings("unchecked")
					List<Orders> getorders = session.createQuery(
							"SELECT orders FROM Orders as orders" + " JOIN  orders.customer cust WHERE LOWER(cust.customerName) LIKE :name").setParameter(
									"name",
									"%" + criteria.toLowerCase() + "%").list();
					for (Iterator<Orders> iterator = getorders.iterator(); iterator.hasNext();) {
						Orders order = iterator.next();
						System.out.println("Order ID: " + order.getOrderID());
						System.out.println("Customer Name: " + order.getCustomer().getCustomerName());
						System.out.println("Order Date: " + order.getOrderDate());
						System.out.println("Order Amount: " + order.getAmount());

						System.out.println("----------------------------------------");
					}
					tx.commit();
					break;
				}
				printInfo();
				option = Integer.parseInt(keyboard.nextLine());
			}
			System.out.println("End searching.");
			keyboard.close();
		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		session.close();
		System.exit(1);
	}

	private static void printInfo() {
		System.out.println("** Search Data Utilities **");
		System.out.println("** 1. Search Customers by Order Date **");
		System.out.println("** 2. Search Orders by Customer Name **");
		System.out.println("** 3. Quit **");
		System.out.println("----------------------------------------");
	}
}
