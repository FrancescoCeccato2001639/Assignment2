////////////////////////////////////////////////////////////////////
// FRANCESCO CECCATO 2001639
// MARTINA GARON 1170566
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class EItemImpl implements EItem {
    
 private String name;

 private EItemType type;

 private double price;

 public EItemImpl(String name, EItemType type, double price) {
 super();
 this.name = name;
 this.type = type;
 this.price = price;
 }
 
 @Override
 public double getPrice() {
 return price;
 }

}