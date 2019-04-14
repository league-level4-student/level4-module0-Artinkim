package CheckPoint;

import java.util.Random;

public class Runner {
	public static void main(String[] args) {
		Random rand = new Random();
		Cake[] ca = new Cake[20];
		for(int i = 0;i<ca.length;i++) {
			if(rand.nextInt(2) == 0) {
				ca[i] = new readymadeCake("ReadymadeBerry",rand.nextDouble()+2,rand.nextDouble()+2);
			} else {
				ca[i] = new orderCake("OrderBeery",rand.nextDouble()+2,rand.nextDouble()+2);
			}
		}
		double price = 0;
		for(Cake c:ca) {
			price+=c.calcPrice();
		}
		System.out.println("Total caked price:"+price);
		double rmcp = 0;
		for(Cake c:ca) {
			if(c instanceof readymadeCake) {
				rmcp+=c.calcPrice();
			}
		}
		System.out.println("Ready Made cake price:"+rmcp);
		double l = 0;
		readymadeCake rmc;
		for(Cake c:ca) {
			if(c instanceof readymadeCake) {
				 rmc = (readymadeCake) c;
				 l+=rmc.quantity;
			}	
		}
		System.out.println("Quantity for ready made cakes:"+l);
	} 

}
