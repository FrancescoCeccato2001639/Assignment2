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
 public void testEmptyList() {
  List<EItem> empty = new ArrayList<>();
  double total = 0;
  try {
  total = bill.getOrderPrice(empty, userTest);
  }
  catch(BillException e) {}
  assertEquals(0,total, 0);
 }

 @Test(expected = BillException.class)
 public void testNullList() throws BillException {
  List<EItem> empty = null;
  try {
  bill.getOrderPrice(empty, userTest);
  }
  catch(NullPointerException e) {}
    throw new BillException();
 }

 @Test(expected = IllegalArgumentException.class)
 public void testNegativeAmount() throws BillException {
  List<EItem> mock = new ArrayList<>();
  mock.add(new EItemImpl("bad", EItemType.CPU, -2.5));
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
  assertEquals(420.99, total, 0);
}

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
  assertEquals(1250.9 *0.9, total, 0.001);
}

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
  assertEquals(1775.90 *0.9, total, 0.001);
}

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
  assertEquals(1260.90*0.9, total, 0.001);
}

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
  assertEquals(1785.90*0.9, total, 0.001);
}

@Test
public void calculatePrice10Mouses() {
  List<EItem> items = new ArrayList<>();
  for(int i = 0 ; i<10; i++)
  {
    items.add(new EItemImpl("mock",EItemType.MOUSE,50));
  }
  items.add(new EItemImpl("different",EItemType.KEYBOARD,37));
  double total = 0;
  try {
    total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(537, total, 0);
}

@Test
public void calculatePrice11Mouses() {
  List<EItem> items = new ArrayList<>();
  for(int i = 0 ; i<10; i++)
  {
    items.add(new EItemImpl("mock",EItemType.MOUSE,50));
  }
  items.add(new EItemImpl("cheapest", EItemType.MOUSE,40));
  items.add(new EItemImpl("different",EItemType.KEYBOARD,37));
  double total = 0;
  try {
    total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(537, total, 0);
}

@Test
public void calculatePrice2Mouses3Keyboards() {
  List<EItem> items = new ArrayList<>();
  EItem mouse1 = new EItemImpl("MOUSE1",EItemType.MOUSE,50);
  EItem mouse2 = new EItemImpl("MOUSE2",EItemType.MOUSE,150);
  EItem keyboard1 = new EItemImpl("KEYB1",EItemType.KEYBOARD,70);
  EItem keyboard2 = new EItemImpl("KEYB2",EItemType.KEYBOARD,170);
  EItem keyboard3 = new EItemImpl("KEYB3",EItemType.KEYBOARD,270);
  
  items.add(mouse1);
  items.add(mouse2);
  items.add(keyboard1);
  items.add(keyboard2);
  items.add(keyboard3);
  
  double total = 0;
  try {
  total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(710, total, 0);
}

@Test
public void calculatePrice3Mouses2Keyboards() {
  List<EItem> items = new ArrayList<>();
  EItem mouse1 = new EItemImpl("MOUSE1",EItemType.MOUSE,50);
  EItem mouse2 = new EItemImpl("MOUSE2",EItemType.MOUSE,150);
  EItem mouse3 = new EItemImpl("MOUSE3",EItemType.MOUSE,250);
  EItem keyboard1 = new EItemImpl("KEYB1",EItemType.KEYBOARD,70);
  EItem keyboard2 = new EItemImpl("KEYB2",EItemType.KEYBOARD,170);
    
  items.add(mouse1);
  items.add(mouse2);
  items.add(mouse3);
  items.add(keyboard1);
  items.add(keyboard2);
  
  double total = 0;
  try {
  total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(690, total, 0);
}

@Test
public void calculatePriceSameQtyMousesKeyboardsMouseLower() {
  List<EItem> items = new ArrayList<>();
  EItem mouse1 = new EItemImpl("MOUSE1",EItemType.MOUSE,50);
  EItem mouse2 = new EItemImpl("MOUSE2",EItemType.MOUSE,150);
  EItem mouse3 = new EItemImpl("MOUSE3",EItemType.MOUSE,250);
  EItem keyboard1 = new EItemImpl("KEYB1",EItemType.KEYBOARD,70);
  EItem keyboard2 = new EItemImpl("KEYB2",EItemType.KEYBOARD,170);
  EItem keyboard3 = new EItemImpl("KEYB3",EItemType.KEYBOARD,270);
    
  items.add(mouse1);
  items.add(mouse2);
  items.add(mouse3);
  items.add(keyboard1);
  items.add(keyboard2);
  items.add(keyboard3);
  
  double total = 0;
  try {
  total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(910,total, 0);
}

@Test
public void calculatePriceSameQtyMousesKeyboardsKeyboardLower() {
  List<EItem> items = new ArrayList<>();
  EItem mouse1 = new EItemImpl("MOUSE1",EItemType.MOUSE,50);
  EItem mouse2 = new EItemImpl("MOUSE2",EItemType.MOUSE,150);
  EItem mouse3 = new EItemImpl("MOUSE3",EItemType.MOUSE,250);
  EItem keyboard1 = new EItemImpl("KEYB1",EItemType.KEYBOARD,30);
  EItem keyboard2 = new EItemImpl("KEYB2",EItemType.KEYBOARD,170);
  EItem keyboard3 = new EItemImpl("KEYB3",EItemType.KEYBOARD,270);
    
  items.add(mouse1);
  items.add(mouse2);
  items.add(mouse3);
  items.add(keyboard1);
  items.add(keyboard2);
  items.add(keyboard3);
  
  double total = 0;
  try {
  total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(890,total, 0);
}

@Test
public void calculateDiscountMoreThan1000Euros() {
  List<EItem> items = new ArrayList<>();
  for(int i = 0; i<5; i++)
  {
    items.add(new EItemImpl("mock", EItemType.CPU, 200));
  }
  items.add(new EItemImpl("item",EItemType.KEYBOARD,50));

  double total = 0;
  try {
  total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(1050 *0.9, total, 0);
}

@Test(expected = BillException.class)
public void orderMore30Items() throws BillException {
  List<EItem> items = new ArrayList<>();
  for(int i = 0; i<31; i++)
  {
    items.add(new EItemImpl("mock", EItemType.CPU, 200));
  }
  
  bill.getOrderPrice(items, userTest);
 }

 @Test
 public void orderEqual30Items() throws BillException {
  List<EItem> items = new ArrayList<>();
  for(int i = 0; i<30; i++)
  {
    items.add(new EItemImpl("mock", EItemType.CPU, 200));
  }
  
  double total = 0;
  try {
  total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(5310, total, 0);
 }

 @Test
 public void testApplyFee() throws BillException {
  List<EItem> items = new ArrayList<>();
  items.add(new EItemImpl("mock",EItemType.MOUSE,5));
  double total = 0;
  try {
  total = bill.getOrderPrice(items, userTest);
  }
  catch(BillException e) {}
  assertEquals(7, total, 0);
 }
}