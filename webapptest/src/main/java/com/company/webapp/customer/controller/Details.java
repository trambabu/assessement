package com.company.webapp.customer.controller;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Details
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private long id;
  private String username;
  private String password;
  
  public long getId()
  {
    return this.id;
  }
  
  public void setId(long id)
  {
    this.id = id;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String param)
  {
    this.username = param;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String param)
  {
    this.password = param;
  }
  
  public String toString()
  {
    return "Details [username=" + this.username + ", password=" + this.password + "]";
  }
}
