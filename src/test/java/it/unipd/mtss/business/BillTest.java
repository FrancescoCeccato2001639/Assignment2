////////////////////////////////////////////////////////////////////
// FRANCESCO CECCATO 2001639
// MARTINA GARON 1170566
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.EItemImpl;
import it.unipd.mtss.model.EItemType;
import it.unipd.mtss.model.User;


public class BillTest {

 private Bill bill = new BillImpl();
 private User userTest = new User(0, "test", new Date(100000));

 @Test
 public void testEmptyList() throws BillException {
  List<EItem> empty = new ArrayList<>();
  double total = 0;
  try {
  total = bill.getOrderPrice(empty, userTest);
  }
  catch(BillException e) {}
  assertEquals(total, 0,0);
  
 }


 @Test
 public void calculateTotalPrice() {
  List<EItem> items = new ArrayList<>();
  EItem proc = new EItemImpl("CPU_TEST",EItemType.CPU,250);
  EItem mobo = new EItemImpl("MB_TEST",EItemType.MOTHERBOARD,140);
  EItem kb = new EItemImpl("KEYBOARD_TEST",EItemType.KEYBOARD,30.99);
  EItem mouse = new EItemImpl("MOUSE_TEST",EItemType.MOUSE,10.99);

  items.add(proc);
  items.add(mobo);
  items.add(kb);
  items.add(mouse);

  double total = 0;
  try {
  total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(total, 431.98,0);
  }
}
