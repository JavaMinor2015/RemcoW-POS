package RESTModels;

import RESTUtils.Paths;

import java.util.Date;

/**
 * Created by Remco on 25-9-2015.
 */
public abstract class Discount {

    private int id;
    public Product product;
    public Date startDate, endDate;
    private String url;

    public Discount(int id, Product product, Date startDate, Date endDate) {
        this.id = id;
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
        this.url = Paths.BASE_URL + Paths.DISCOUNT_URI + id;
    }

    public int getId() {
        return id;
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