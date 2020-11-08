package com.customertracker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customertracker.entity.Customer;

@Repository // this help the DAO impl during the component scan when injected as a
			// dependency and also to communicate with the data source
public class CustomerDAOImpl implements CustomerDAO {
	// to set the session factory dependency
	@Autowired
	public SessionFactory sessionFactory;

	@Override // This is called by the customer service implementation
	public List<Customer> getCustomers() {

		// get the current session
		Session session = sessionFactory.getCurrentSession();

		// create a query to fetch the customers sorted by firstName
		Query<Customer> theQuery = session.createQuery("from Customer order by firstName", Customer.class);

		// get the customer list
		List<Customer> customers = theQuery.getResultList();

		// return the customers
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		// get the current session
		Session session = sessionFactory.getCurrentSession();
		// save data if no primary key or update data if primary key exists for the
		// customer in the data base
		session.saveOrUpdate(customer);

	}

	@Override
	public Customer getCustomer(int id) {
		// get the current session
		Session session = sessionFactory.getCurrentSession();

		Customer customer = session.get(Customer.class, id);

		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		// get the current session
		Session session = sessionFactory.getCurrentSession();

		Query theQuery = session.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", id);

		theQuery.executeUpdate();

	}

	@Override
	public List<Customer> searchCustomer(String name) {

		Session session = sessionFactory.getCurrentSession();
		Query<Customer> theQuery = null;
		if (name != null && name.trim().length() > 0) {
			theQuery = session.createQuery(
					"from Customer where lower(firstName) like :theName or lower(lastName) like :theName",
					Customer.class);

			theQuery.setParameter("theName", "%" + name.toLowerCase() + "%");

		}else {
			 theQuery = session.createQuery("from Customer order by firstName", Customer.class);
			
		}
		
		List<Customer> customers = theQuery.getResultList();

		return customers;
	}

}
