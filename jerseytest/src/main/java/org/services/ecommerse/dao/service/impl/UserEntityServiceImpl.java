package org.services.ecommerse.dao.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.services.ecommerce.entity.Details;
import org.services.ecommerse.dao.service.UserEntityService;

public class UserEntityServiceImpl implements UserEntityService{
	private static final String PERSISTENCE_UNIT_NAME = "testservice";
	  private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	  
	  public static EntityManager getEntityManager()
	  {
	    return emfactory.createEntityManager();
	  }
	  
	  public List<Details> getAllDetails()
	  {
	    EntityManager entitymanager = getEntityManager();
	    try
	    {
	      entitymanager.getTransaction().begin();
	      Query query = entitymanager.createQuery("Select sm from Details sm");
	      List<Details> list = (List<Details>)query.getResultList();
	      entitymanager.getTransaction().commit();
	      return list;
	    }
	    finally
	    {
	      entitymanager.close();
	    }
	  }
	  
	  public boolean insertDetails(Details details)
	  {
	    System.out.println("insertDetails **>>");
	    EntityManager entitymanager = getEntityManager();
	    try
	    {
	      entitymanager.getTransaction().begin();
	      entitymanager.persist(details);
	      entitymanager.getTransaction().commit();
	      return true;
	    }
	    finally
	    {
	      entitymanager.close();
	    }
	  }
	  
	  public Details findByUsername(String username)
	  {
	    EntityManager entitymanager = getEntityManager();
	    try
	    {
	      entitymanager.getTransaction().begin();
	      Query query = entitymanager.createQuery("Select sm from Details sm where sm.username= '" + username + "'");
	      Details details = (Details) query.getSingleResult();
	      entitymanager.getTransaction().commit();
	      return details;
	    }
	    finally
	    {
	      entitymanager.close();
	    }
	  }
	  
	  public boolean updateDetails(Details dt)
	  {
	    EntityManager entitymanager = getEntityManager();
	    try
	    {
	      entitymanager.getTransaction().begin();
	      Query query = entitymanager.createQuery("Select sm from Details sm where sm.username= '" + dt.getUsername() + "'");
	      Details details = (Details) query.getSingleResult();
	      details.setUsername(dt.getUsername());
	      details.setPassword(dt.getPassword());
	      entitymanager.merge(details);
	      entitymanager.getTransaction().commit();
	      return true;
	    }
	    finally
	    {
	      entitymanager.close();
	    }
	  }
	  
	  public boolean deleteDetails(Details dt)
	  {
	    EntityManager entitymanager = getEntityManager();
	    try
	    {
	      entitymanager.getTransaction().begin();
	      Query query = entitymanager.createQuery("Delete from Details sm where sm.username= '"+dt.getUsername()+"'");
	        int rows = query.executeUpdate();
	      entitymanager.getTransaction().commit();
	      return true;
	    }
	    finally
	    {
	      entitymanager.close();
	    }
	  }
}
