package Controllers;

import Models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remco on 25-9-2015.
 */
public class Register {

    private Inventory inventory;

    public Register(){
        inventory = new Inventory();

        getProducts();
        getDiscounts();
        startSale();
    }

    private void getProducts(){
        //TODO collect products from backend

        //Dummy data
        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product(123, "Chips", 1.50));
        //End dummy data

        inventory.setProductList(productList);
    }

    private void getDiscounts(){
        //TODO collect discounts of today from backend
    }

    private void startSale(){
        SaleHandler sale = new SaleHandler(inventory);
    }

    public static void main(String[] args){
        new Register();
    }
}
