package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remco on 25-9-2015.
 */
public class Sale {

    private List<Product> productList;

    public Sale(){
        productList = new ArrayList<Product>();
    }

    public void addProduct(Product p){
        productList.add(p);
    }
}
