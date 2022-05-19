////////////////////////////////////////////////////////////////////
// FRANCESCO CECCATO 2001639
// MARTINA GARON 1170566
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import java.util.List;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.EItemType;
import it.unipd.mtss.model.User;

public class BillImpl implements Bill {

@Override
public double getOrderPrice(List <EItem> itemsOrdered, User user)
 throws BillException {

 double total = 0;
 for(EItem i: itemsOrdered)
 {
  if(i.getPrice() < 0)
  {
   throw new BillException();
  }
  total += i.getPrice();
 }

 total -= getDiscountMore5Processors(itemsOrdered);
 total -= getDiscountMoreThan10Mouses(itemsOrdered);
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

private double getDiscountMoreThan10Mouses(List <EItem> itemsOrdered)
 throws BillException {

 int counter = 0;
 double min = -1;

 int i = 0;
 while(counter <= 10 && i<itemsOrdered.size())
 {
  EItem item = itemsOrdered.get(i);
  if(item.getType() == EItemType.MOUSE)
  {
   counter++;
   if(min == -1 || item.getPrice() < min)
   {
    min = item.getPrice();
   }
  }
  i++;
 }
 return counter > 10? min : 0;
}

}
