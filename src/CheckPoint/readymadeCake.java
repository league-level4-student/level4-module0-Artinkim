package CheckPoint;

public class readymadeCake extends Cake {
	double quantity;
	public readymadeCake(String n, double r,double q) {
		super(n, r);
		// TODO Auto-generated constructor stub
		quantity = q;
	}

	@Override
	public double calcPrice() {
		// TODO Auto-generated method stub
		return quantity*rate;
	}

}
