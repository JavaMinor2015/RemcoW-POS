package Controllers;

import Models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Remco on 25-9-2015.
 */
public class PaymentHandler {

    private List<Discount> discountList;

    public PaymentHandler(){
        discountList = new ArrayList<Discount>();
    }

    public void setDiscountList(List<Discount> discountList) {
        this.discountList = discountList;
    }

    public boolean handlePayment(Sale sale){
        //TODO Handle the payment
        sale.setDiscount(checkForDiscount(sale.getProductList()));
        System.out.println(sale.getDiscount());
        return true;
    }

    private double checkForDiscount(HashMap<Product, Integer> products){
        double totalDiscount = 0;

        for (Map.Entry<Product, Integer> entry : products.entrySet()){
            for (Discount discount : discountList){
                if (discount.getProduct().getProductcode() == entry.getKey().getProductcode()){
                    totalDiscount += discount.getDiscount(entry.getValue());
                }
                else{
                    continue;
                }
            }
        }

        return totalDiscount;
    }
}
