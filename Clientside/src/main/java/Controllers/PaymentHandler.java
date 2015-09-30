package Controllers;

import Models.*;
import Utils.HttpRequest;
import Utils.URL;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.*;

/**
 * Created by Remco on 25-9-2015.
 */
public class PaymentHandler {

    private List<Discount> discountList;
    private Scanner scanner;

    public PaymentHandler(){
        discountList = new ArrayList<Discount>();
        scanner  = new Scanner(System.in);
    }

    public void setDiscountList(List<Discount> discountList) {
        this.discountList = discountList;
    }

    public boolean handlePayment(Sale sale){
        //TODO Handle the payment

        //Get customer discount
        FidelityCard fidelityCard;
        double customerDiscount = 0;
        String input;
        System.out.println("Enter fidelitycard code. If you don't have one leave this empty.");
        if (!(input = scanner.nextLine()).isEmpty()){
              fidelityCard = getCustomer(Integer.parseInt(input));
            if (fidelityCard != null){
                customerDiscount = sale.getTotal() * 0.05;
            }
        }

        //Get product discount
        double productDiscount = checkForProductDiscount(sale.getProductList());

        //Set total discount
        sale.setDiscount(productDiscount + customerDiscount);

        return true;
    }

    private FidelityCard getCustomer(int fidelityCardCode){
        HttpRequest httpRequest = new HttpRequest();
        String response = httpRequest.makeGetReqeust(URL.BASE_URL + URL.FIDELITYCARD_URI + "/" + fidelityCardCode);
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(response);

        Gson gson = new Gson();
        FidelityCard fidelityCard;
        try {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            fidelityCard = gson.fromJson(jsonObject, FidelityCard.class);
        }
        catch (IllegalStateException e){
            System.out.println("Unknown customer");
            fidelityCard = null;
        }

        return fidelityCard;
    }

    private double checkForProductDiscount(HashMap<Product, Integer> products){
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
