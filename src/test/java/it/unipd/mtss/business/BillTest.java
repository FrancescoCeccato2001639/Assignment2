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


//prezzo per 5 Processori, lo sconto non deve essere applicato
@Test
public void calculatePrice5Processors() {
  List<EItem> items = new ArrayList<>();
  EItem proc1 = new EItemImpl("CPU_TEST",EItemType.CPU,50);
  EItem proc2 = new EItemImpl("CPU_TEST",EItemType.CPU,150.90);
  EItem proc3 = new EItemImpl("CPU_TEST",EItemType.CPU,250);
  EItem proc4 = new EItemImpl("CPU_TEST",EItemType.CPU,350);
  EItem proc5 = new EItemImpl("CPU_TEST",EItemType.CPU,450);
  
  items.add(proc1);
  items.add(proc2);
  items.add(proc3);
  items.add(proc4);
  items.add(proc5);
  
  double total = 0;
  try {
  total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(total, 1250.9, 0);
}


//prezzo per 6 processori, lo sconto deve essere applicato
@Test
public void calculatePrice6Processors() {
  List<EItem> items = new ArrayList<>();
  EItem proc1 = new EItemImpl("CPU_TEST",EItemType.CPU,50);
  EItem proc2 = new EItemImpl("CPU_TEST",EItemType.CPU,150.90);
  EItem proc3 = new EItemImpl("CPU_TEST",EItemType.CPU,250);
  EItem proc4 = new EItemImpl("CPU_TEST",EItemType.CPU,350);
  EItem proc5 = new EItemImpl("CPU_TEST",EItemType.CPU,450);
  EItem proc6 = new EItemImpl("CPU_TEST",EItemType.CPU,550);
  
  items.add(proc1);
  items.add(proc2);
  items.add(proc3);
  items.add(proc4);
  items.add(proc5);
  items.add(proc6);
  
  double total = 0;
  try {
  total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(total, 1775.90, 0);
}

//prezzo per 5 Processori con altra merce, lo sconto non deve essere applicato
@Test
public void calculatePrice5ProcessorsOtherItem() {
  List<EItem> items = new ArrayList<>();
  EItem proc1 = new EItemImpl("CPU_TEST",EItemType.CPU,50);
  EItem proc2 = new EItemImpl("CPU_TEST",EItemType.CPU,150.90);
  EItem proc3 = new EItemImpl("CPU_TEST",EItemType.CPU,250);
  EItem proc4 = new EItemImpl("CPU_TEST",EItemType.CPU,350);
  EItem proc5 = new EItemImpl("CPU_TEST",EItemType.CPU,450);
  EItem mouse = new EItemImpl("MOUSE_TEST",EItemType.MOUSE,10);
  
  items.add(proc1);
  items.add(proc2);
  items.add(proc3);
  items.add(proc4);
  items.add(proc5);
  items.add(mouse);
  
  double total = 0;
  try {
  total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(total, 1260.9, 0);
}

//prezzo per 6 processori con altra merce, lo sconto deve essere applicato
@Test
public void calculatePrice6ProcessorsOtherItem() {
  List<EItem> items = new ArrayList<>();
  EItem proc1 = new EItemImpl("CPU_TEST",EItemType.CPU,50);
  EItem proc2 = new EItemImpl("CPU_TEST",EItemType.CPU,150.90);
  EItem proc3 = new EItemImpl("CPU_TEST",EItemType.CPU,250);
  EItem proc4 = new EItemImpl("CPU_TEST",EItemType.CPU,350);
  EItem proc5 = new EItemImpl("CPU_TEST",EItemType.CPU,450);
  EItem proc6 = new EItemImpl("CPU_TEST",EItemType.CPU,550);
  EItem mouse = new EItemImpl("MOUSE_TEST",EItemType.MOUSE,10);
  
  items.add(proc1);
  items.add(proc2);
  items.add(proc3);
  items.add(proc4);
  items.add(proc5);
  items.add(proc6);
  items.add(mouse);
  
  double total = 0;
  try {
  total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(total, 1785.90, 0);

}

}