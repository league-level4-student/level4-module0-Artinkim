package CheckPoint;

public abstract class Cake { 
    protected String name; 
    protected double rate; 
    public Cake (String n, double r) { 
        name = n; 
        rate = r; 
    } 
    
    public abstract double calcPrice(); 
    
    public String toString() {
        return name + "\t" + rate;
    } 
}
