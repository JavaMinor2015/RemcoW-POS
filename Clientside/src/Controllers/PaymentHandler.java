package Controllers;

import Models.Discount;
import Models.Sale;

import java.util.ArrayList;
import java.util.List;

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
        return true;
    }
}
