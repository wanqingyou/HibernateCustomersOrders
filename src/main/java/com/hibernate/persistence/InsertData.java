package com.hibernate.persistence;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.hibernate.entity.Customers;
import com.hibernate.entity.Orders;

/**
 * This is to intilize the database table with testing data.
 * @author WYou
 * @since 2016.05.08
 */
public class InsertData {

	public static void main(String[] args) throws ParseException {
		Customers customer1 = new Customers();
		Customers customer2 = new Customers();
		Set<Orders> ordersList = new HashSet<Orders>();

		DateFormat df = new SimpleDateFormat("MM/DD/YYYY");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// Populate customer records
		customer1.setCustomerName("John Jones");
		customer1.setContactInformation("1234567890");
		customer1.setAddress("123 Main St.");

		customer2.setCustomerName("David Smith");
		customer2.setContactInformation("9876543210");
		customer2.setAddress("234 Broad Way");

		// populate order1 
		Orders order1 = new Orders();
		order1.setOrderDate(df.parse("10/21/2016"));
		order1.setAmount(120);
		order1.setCustomer(customer1);
		ordersList.add(order1);

		// populate order4
		Orders order4 = new Orders();
		order4.setOrderDate(df.parse("2/13/2016"));
		order4.setAmount(170);
		order4.setCustomer(customer1);
		ordersList.add(order4);

		customer1.setOrder(ordersList);
		session.save(customer1);
		ordersList.clear();

		// populate order2
		Orders order2 = new Orders();
		order2.setOrderDate(df.parse("10/21/2016"));
		order2.setAmount(100);
		order2.setCustomer(customer2);
		ordersList.add(order2);

		// populate order3
		Orders order3 = new Orders();
		order3.setOrderDate(df.parse("12/1/2016"));
		order3.setAmount(80);
		order3.setCustomer(customer2);
		ordersList.add(order3);


		// populate order5
		Orders order5 = new Orders();
		order5.setOrderDate(df.parse("2/13/2016"));
		order5.setAmount(200);
		order5.setCustomer(customer2);
		ordersList.add(order5);

		customer2.setOrder(ordersList);
		session.save(customer2);
		ordersList.clear();
		//Commit transaction
		session.getTransaction().commit();
		//terminate session factory, otherwise program won't end
		session.close();

		System.exit(1);
	}

}
