package org.services.ecommerce.business.service;

import java.util.List;

import org.services.ecommerce.entity.Details;

public interface DetailsService {
	 public abstract boolean createUser(String paramString1, String paramString2);
	  
	  public abstract Details readUserByUsername(String paramString);
	  
	  public abstract boolean updateUser(String paramString1, String paramString2);
	  
	  public abstract boolean deleteUser(String paramString);
	  
	  public abstract List<Details> getAllDetails();
}
