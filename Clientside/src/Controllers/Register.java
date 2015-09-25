package Controllers;

import Models.Discount;
import Models.Product;
import Models.QuantityDiscount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remco on 25-9-2015.
 */
public class Register {

    private Inventory inventory;
    private PaymentHandler paymentHandler;

    public Register(){
        inventory = new Inventory();
        paymentHandler = new PaymentHandler();

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

        //Dummy data
        List<Discount> discountList = new ArrayList<Discount>();
        discountList.add(new QuantityDiscount(inventory.searchProduct(123), 3));
        //End dummy data

        paymentHandler.setDiscountList(discountList);
    }

    private void startSale(){
        SaleHandler sale = new SaleHandler(inventory, paymentHandler);
    }

    public static void main(String[] args){
        new Register();
    }
}
