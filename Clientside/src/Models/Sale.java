package Models;

import java.util.HashMap;

/**
 * Created by Remco on 25-9-2015.
 */
public class Sale {

    private HashMap<Product, Integer> productList;
    private double total, discount;

    public Sale(){
        total = 0;
        discount = 0;
        productList = new HashMap<Product, Integer>();
    }

    public void addProduct(Product product){
        if (!productList.containsKey(product)) {
            productList.put(product, 1);

        } else {
            productList.put(product, productList.get(product) + 1);
        }
        total += product.getPrice();
    }

    public HashMap<Product, Integer> getProductList() {
        return productList;
    }

    public double getTotal() {
        return total;
    }

    public double getDiscount() {
        return discount;
    }
}
