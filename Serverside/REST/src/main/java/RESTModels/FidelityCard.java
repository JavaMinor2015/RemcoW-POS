package RESTModels;

/**
 * Created by Remco on 25-9-2015.
 */
public class FidelityCard {

    private int cardCode;
    private Customer customer;

    public FidelityCard(int cardCode, Customer customer) {
        this.cardCode = cardCode;
        this.customer = customer;
    }

    public int getCardCode() {
        return cardCode;
    }

    public Customer getCustomer() {
        return customer;
    }
}
