package RESTModels;

import RESTUtils.Paths;

/**
 * Created by Remco on 25-9-2015.
 */
public class Product {

    private int productcode;
    private String name;
    private double price;
    private String url;

    public Product(int productcode, String name, double price) {
        this.productcode = productcode;
        this.name = name;
        this.price = price;
        this.url = Paths.BASE_URL + Paths.PRODUCT_URI + productcode;
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
