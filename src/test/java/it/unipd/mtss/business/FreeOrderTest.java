////////////////////////////////////////////////////////////////////
// FRANCESCO CECCATO 2001639
// MARTINA GARON 1170566
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;
import org.junit.Test;
import it.unipd.mtss.model.User;

import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FreeOrderTest
{
	private User underageUser = new User(0, "test", LocalDate.parse("2017-02-13"));
	private User adultUser = new User(1, "test", LocalDate.parse("1980-02-13"));

 @Test
 public void testFreeOrderNotActive() {
  FreeOrder freeOrder= new FreeOrder(0, false, 21,22, 10);
  boolean isFree=freeOrder.checkFreeOrder(underageUser);
  assertTrue(!isFree);
 }

 @Test
 public void testAdultUser() {
  int now=LocalDateTime.now().getHour();
  FreeOrder freeOrder= new FreeOrder(0, true, now, now+1, 10);
  boolean isFree=freeOrder.checkFreeOrder(adultUser);
  assertTrue(!isFree);
 }

 @Test
 public void testUnderageOrderOverTimeRange() {
  int now=LocalDateTime.now().getHour();
  FreeOrder freeOrder= new FreeOrder(0, true, now-3,now-2, 10);
  boolean isFree=freeOrder.checkFreeOrder(underageUser);
  assertTrue(!isFree);
 }

 @Test
 public void testAdultOrderOverTimeRange() {
  int now=LocalDateTime.now().getHour();
  FreeOrder freeOrder= new FreeOrder(0, true, now-3, now-2, 10);
  boolean isFree=freeOrder.checkFreeOrder(adultUser);
  assertTrue(!isFree);
 }

 @Test
 public void testmaxFreeOrderDone() {
  FreeOrder freeOrder= new FreeOrder(0, true, 21,22, 0);
  boolean isFree=freeOrder.checkFreeOrder(underageUser);
  assertTrue(!isFree);
 }

 @Test
 public void testUnderageOrderInTimeRange() {
  int now=LocalDateTime.now().getHour();
  FreeOrder freeOrder= new FreeOrder(0, true, now,now+1, 10);
  boolean isFree=freeOrder.checkFreeOrder(underageUser);
  boolean randomFree=freeOrder.getRandom();
  if(randomFree)
   assertTrue(isFree);
  else
   assertTrue(!isFree);
 }

}