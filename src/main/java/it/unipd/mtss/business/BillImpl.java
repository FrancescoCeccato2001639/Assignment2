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
 return total;
}

}
