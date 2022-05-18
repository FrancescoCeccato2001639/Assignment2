////////////////////////////////////////////////////////////////////
// FRANCESCO CECCATO 2001639
// MARTINA GARON 1170566
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import java.util.List;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillImpl implements Bill {

@Override
public double getOrderPrice(List <EItem> itemsOrdered, User user)
 throws BillException {

 double total = 0;
 for(EItem i: itemsOrdered)
 {
  total += i.getPrice();
 }

 total=total-getDiscountMore5Processors(itemsOrdered);
 return total;
}

private double getDiscountMore5Processors(List <EItem> itemsOrdered)
 throws BillException {
 double lowerPrice = -1;
 double discount = 0;
 double countProcessor = 0;
 
 for(EItem i: itemsOrdered)
 {
  switch(i.getType())
  {
   case CPU:
    countProcessor++;
    if(lowerPrice==-1)
    {
     lowerPrice=i.getPrice();
    }
    else if(i.getPrice()<lowerPrice)
    {
     lowerPrice=i.getPrice();
    }
    break;
   default:
  }
 }

 if(countProcessor>5)
 {
  discount=lowerPrice/2;
 }
 return discount;
}

}
