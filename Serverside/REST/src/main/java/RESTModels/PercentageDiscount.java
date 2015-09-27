package RESTModels;

import java.util.Date;

/**
 * Created by Remco on 25-9-2015.
 */
public class PercentageDiscount extends Discount{

    private double percentage;

    public PercentageDiscount(int id, Product product, double percentage, Date startDate, Date endDate) {
        super(id, product, startDate, endDate);
        this.percentage = percentage;
    }

    public double getDiscount(int quantityPurchased) {
        double discount = product.getPrice() * (percentage / 100);
        return discount * quantityPurchased;
    }
}