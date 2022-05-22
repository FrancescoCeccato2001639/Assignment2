////////////////////////////////////////////////////////////////////
// FRANCESCO CECCATO 2001639
// MARTINA GARON 1170566
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalDate;
import java.time.Period;

public class User {
 private long id;

 private String name;

 private LocalDate birthday;

 public User(long id, String name, LocalDate birthday) {
  this.id = id;
  this.name = name;
  this.birthday = birthday;
 }

 public LocalDate getBirthday()
 {
  return birthday;
 }

 public long getId()
 {
  return id;
 }

public boolean isAdult()
{
 int age=0;
 LocalDate curDate = LocalDate.now();  
 if ((birthday != null) && (curDate != null))   
 {  
  age=Period.between(birthday, curDate).getYears();  
 }  
 if(age>=18)
 {
  return true;
 }
 return false;
}

}
