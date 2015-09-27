package RESTModels;

import java.util.Date;

/**
 * Created by Remco on 25-9-2015.
 */
public class QuantityDiscount extends Discount{

    private int quantity;

    public QuantityDiscount(int id, Product product, int quantity, Date startDate, Date endDate) {
        super(id, product, startDate, endDate);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDiscount(int quantityPurchased) {
        int amount = quantityPurchased / (quantity + 1);
        return amount * product.getPrice();
    }
}