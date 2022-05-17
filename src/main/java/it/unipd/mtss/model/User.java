////////////////////////////////////////////////////////////////////
// FRANCESCO CECCATO 2001639
// MARTINA GARON 1170566
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.util.Date;

public class User {
 private long id;

 private String name;

 private Date birthday;

 public User(long id, String name, Date birthday) {
  this.id = id;
  this.name = name;
  this.birthday = birthday;
 }
}
