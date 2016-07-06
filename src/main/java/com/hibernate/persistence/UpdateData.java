package com.hibernate.persistence;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.entity.Orders;

/**
 * This class provides abilities for user to update database entries.
 * @author WYou
 * @since 16.5.0
 */
public class UpdateData {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.println("** Update Order Amount **");
			System.out.println("----------------------------------------");
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Input order ID: ");
			int orderID = Integer.parseInt(keyboard.nextLine());
			System.out.println("Input new amount value: ");
			double amount = Double.parseDouble(keyboard.nextLine());
			Query updateOrder = session.createQuery("UPDATE Orders SET amount =:amount WHERE orderID = :orderID");
			updateOrder.setParameter("amount", amount);
			updateOrder.setParameter("orderID", orderID);
			int rowsAffected = updateOrder.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Updated " + rowsAffected + " rows.");
			}
			Orders order = (Orders) session.get(Orders.class, orderID);
			System.out.println("Order ID: " + order.getOrderID());
			System.out.println("Order Date: " + order.getOrderDate());
			System.out.println("Amount: " + order.getAmount());
			System.out.println("----------------------------------------");
			tx.commit();
			keyboard.close();
		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		}
		session.close();
		System.exit(1);
	}

}
