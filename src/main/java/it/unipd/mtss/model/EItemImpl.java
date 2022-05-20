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

 if(price <= 0)
 {
   throw new IllegalArgumentException(
     "Il prezzo non può essere pari o inferiore a 0!");
 }

 this.price = price;
 }
 
 @Override
 public double getPrice() {
 return price;
 }

 @Override
 public EItemType getType() {
 return type;
 }

}