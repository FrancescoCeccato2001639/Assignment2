////////////////////////////////////////////////////////////////////
// FRANCESCO CECCATO 2001639
// MARTINA GARON 1170566
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.time.LocalDateTime;
import java.util.Random;

import it.unipd.mtss.model.User;

public class FreeOrder {
 int countFreeOrder=0;
 boolean activeFreeOrder=false;
 int fromHour = 0;
 int toHour = 0;
 int maxFreeOrders = 0;
 boolean random;

 public FreeOrder(int count, boolean active, int from, int to, int max) {
 this.countFreeOrder = count;
 this.activeFreeOrder = active;
 this.fromHour = from;
 this.toHour = to;
 this.maxFreeOrders = max;
 this.random=false;
 }

 public boolean checkFreeOrder(User user)
 {
  boolean isUserUnderage=false;
  boolean isTimeRange=false;
  boolean isLimitFreeOrderReached=false;
  if(activeFreeOrder)
  {
   isUserUnderage = !user.isAdult();
   isTimeRange = checkTimeInRange();
   isLimitFreeOrderReached=checkMaxQtyFreeOrderDone();
   
   if(isUserUnderage && isTimeRange && isLimitFreeOrderReached)
   {
    execRandom();
    if(random)
    {
     countFreeOrder++;
     return true;
    }
   }
  }
  return false;
 }

 private boolean checkTimeInRange()
 {
  int now=LocalDateTime.now().getHour();
  if(fromHour<=now && toHour>=now)
  {
    return true;
  }
  return false;
 }

 private boolean checkMaxQtyFreeOrderDone()
 {
  if(countFreeOrder<maxFreeOrders)
  {
   return true;
  }
  return false;
 }

private void execRandom() {
 Random rdm = new Random();
 random=rdm.nextBoolean();
}

public boolean getRandom() {
  return random;
 }

}
