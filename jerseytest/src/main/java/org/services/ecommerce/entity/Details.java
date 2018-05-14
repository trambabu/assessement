package org.services.ecommerce.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="details")
public class Details
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;
  @Column(name="username")
  private String username;
  @Column(name="password")
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
