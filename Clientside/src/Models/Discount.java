package Models;

/**
 * Created by Remco on 25-9-2015.
 */
public abstract class Discount {

    public Product product;

    public Discount(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public abstract double getDiscount(int quantityPurchased);
}