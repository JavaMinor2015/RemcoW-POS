package Models;

/**
 * Created by Remco on 25-9-2015.
 */
public class PercentageDiscount extends Discount{

    private double percentage;

    public PercentageDiscount(Product product, double percentage) {
        super(product);
        this.percentage = percentage;
    }

    public double getDiscount(int quantityPurchased) {
        double discount = product.getPrice() * (percentage / 100);
        return discount * quantityPurchased;
    }
}