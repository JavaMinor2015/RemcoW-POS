package Models;

import java.util.Date;

/**
 * Created by Remco on 25-9-2015.
 */
public abstract class Discount {

    public Product product;
    public Date startDate, endDate;



    public Discount(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public abstract double getDiscount(int quantityPurchased);
}