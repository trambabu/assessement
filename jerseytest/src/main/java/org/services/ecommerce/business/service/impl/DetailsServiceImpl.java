package org.services.ecommerce.business.service.impl;

import java.util.List;

import org.services.ecommerce.business.service.DetailsService;
import org.services.ecommerce.entity.Details;
import org.services.ecommerse.dao.service.UserEntityService;
import org.services.ecommerse.dao.service.impl.UserEntityServiceImpl;

public class DetailsServiceImpl implements DetailsService {

	 public boolean createUser(String username, String password)
	  {
		 UserEntityService service = new UserEntityServiceImpl();
	    Details details = new Details();
	    details.setUsername(username);
	    details.setPassword(password);
	    return service.insertDetails(details);
	  }
	  
	  public Details readUserByUsername(String username)
	  {
		  UserEntityService service = new UserEntityServiceImpl();
	    return service.findByUsername(username);
	  }
	  
	  public boolean updateUser(String username, String password)
	  {
		  UserEntityService service = new UserEntityServiceImpl();
	    Details details = new Details();
	    details.setUsername(username);
	    details.setPassword(password);
	    return service.updateDetails(details);
	  }
	  
	  public boolean deleteUser(String username)
	  {
		  UserEntityService service = new UserEntityServiceImpl();
	    Details details = new Details();
	    details.setUsername(username);
	    return service.deleteDetails(details);
	  }
	  
	  public List<Details> getAllDetails()
	  {
		  UserEntityService service = new UserEntityServiceImpl();
	    return service.getAllDetails();
	  }

}
