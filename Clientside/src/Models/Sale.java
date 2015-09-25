package Models;

import java.util.HashMap;

/**
 * Created by Remco on 25-9-2015.
 */
public class Sale {

    private HashMap<Product, Integer> productList;

    public Sale(){
        productList = new HashMap<Product, Integer>();
    }

    public void addProduct(Product product){
        if (!productList.containsKey(product)) {
            productList.put(product, 1);

        } else {
            productList.put(product, productList.get(product) + 1);
        }
    }

    public HashMap<Product, Integer> getProductList() {
        return productList;
    }
}
