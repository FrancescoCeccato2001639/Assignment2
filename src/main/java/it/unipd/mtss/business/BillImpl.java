////////////////////////////////////////////////////////////////////
// FRANCESCO CECCATO 2001639
// MARTINA GARON 1170566
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;

import java.util.ArrayList;
import java.util.List;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.EItemType;
import it.unipd.mtss.model.User;

public class BillImpl implements Bill {
 List<Long> userGotFreeOrder=new ArrayList<>();
 FreeOrder checkIsFreeOrder= new FreeOrder(0, false, 18,19, 10);
 

@Override
public double getOrderPrice(List <EItem> itemsOrdered, User user)
 throws BillException {

 double total = 0;
 boolean isFreeOrNotDone=true;
 checkMaxQtyItemsOrdered(itemsOrdered);

 if(!userGotFreeOrder.contains(user.getId()))
 {
  isFreeOrNotDone=checkIsFreeOrder.checkFreeOrder(user);
 }

 if(!isFreeOrNotDone)
 {
  for(EItem i: itemsOrdered)
  {
   total += i.getPrice();
  }

  total -= getDiscountMore5Processors(itemsOrdered);
  EItem i1 = getDiscountMoreThan10Mouses(itemsOrdered);
  EItem i2 = getDiscountSameQtyMousesKeyboard(itemsOrdered);
  if(i1 != null)
  {
   total -= i1.getPrice();
  }
  if(i2 != null && i2 != i1)
  {
   total -= i2.getPrice();
  }

  if(total > 1000)
  {
   total *= 0.9;
  }
  else if(!itemsOrdered.isEmpty() && total < 10)
  {
   total += 2;
  }
 }
 else
 {
  userGotFreeOrder.add(user.getId());
 }
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

private EItem getDiscountMoreThan10Mouses(List <EItem> itemsOrdered)
 throws BillException {

 int counter = 0;
 double min = -1;

 int i = 0;
 EItem minItem = null;
 while(counter <= 10 && i<itemsOrdered.size())
 {
  EItem item = itemsOrdered.get(i);
  if(item.getType() == EItemType.MOUSE)
  {
   counter++;
   if(min == -1 || item.getPrice() < min)
   {
    min = item.getPrice();
    minItem = item;
   }
  }
  i++;
 }
 return counter > 10? minItem : null;
}

private EItem getDiscountSameQtyMousesKeyboard(List <EItem> itemsOrdered)
 throws BillException {
 double lowerPrice = -1;
 EItem minItem = null;
 double countMouses = 0;
 double countKeyboards = 0;
 
 for(EItem i: itemsOrdered)
 {
  switch(i.getType())
  {
   case MOUSE:
   countMouses++;
    if(lowerPrice==-1 || i.getPrice()<lowerPrice)
    {
     minItem = i;
     lowerPrice=i.getPrice();
    }
    break;
    case KEYBOARD:
    countKeyboards++;
    if(lowerPrice==-1 || i.getPrice()<lowerPrice)
    {
     minItem = i;
     lowerPrice=i.getPrice();
    }
    break;
   default:
  }
 }

 if(countKeyboards==countMouses && countKeyboards+countMouses!=0)
 {
  return minItem;
 }
 else
 {
  return null;
 }
}


private void checkMaxQtyItemsOrdered(List <EItem> itemsOrdered)
 throws BillException {
 if(itemsOrdered.size()>30)
 {
  throw new BillException("Non è possibile ordinare più di 30 elementi!");
 }
}


}
