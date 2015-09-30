package Models;

/**
 * Created by Remco on 25-9-2015.
 */
public class Product {

    private int productcode;
    private String name;
    private double price;

    public Product(int productcode, String name, double price) {
        this.productcode = productcode;
        this.name = name;
        this.price = price;
    }
    public int getProductcode(){
        return productcode;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
