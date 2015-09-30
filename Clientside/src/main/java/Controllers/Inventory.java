package Controllers;

import Models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remco on 25-9-2015.
 */
public class Inventory {

    private List<Product> productList;

    public Inventory(){
        productList = new ArrayList<Product>();
    }

    public Product searchProduct(int productcode){
        for (Product p : productList){
            if (p.getProductcode() == productcode){
                return p;
            }
        }
        return null;
    }

    public void setProductList(List<Product> productList){
        this.productList = productList;
    }
}
