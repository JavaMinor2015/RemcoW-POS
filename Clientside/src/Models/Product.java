package Models;

/**
 * Created by Remco on 25-9-2015.
 */
public class Product {

    private int productcode;
    private String name;

    public Product(int productcode, String name){
        this.productcode = productcode;
        this.name = name;
    }

    public int getProductcode(){
        return productcode;
    }

    public String getName() {
        return name;
    }
}
