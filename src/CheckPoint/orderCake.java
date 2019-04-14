package CheckPoint;

public class orderCake extends Cake{
	double weight;
	public orderCake(String n, double r,double w) {
		super(n, r);
		weight = w;
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcPrice() {
		return rate*weight;
	}

}
