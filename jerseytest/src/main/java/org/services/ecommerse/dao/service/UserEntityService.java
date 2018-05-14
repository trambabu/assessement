package org.services.ecommerse.dao.service;

import java.util.List;

import org.services.ecommerce.entity.Details;

public interface UserEntityService {

  public abstract List<Details> getAllDetails();
  
  public abstract boolean insertDetails(Details paramDetails);
  
  public abstract Details findByUsername(String paramString);
  
  public abstract boolean updateDetails(Details paramDetails);
  
  public abstract boolean deleteDetails(Details paramDetails);

}
