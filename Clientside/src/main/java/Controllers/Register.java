package Controllers;

import Models.Discount;
import Models.PercentageDiscount;
import Models.Product;
import Models.QuantityDiscount;
import Utils.HttpRequest;
import Utils.URL;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remco on 25-9-2015.
 */
public class Register {

    private Inventory inventory;
    private PaymentHandler paymentHandler;
    private SaleHandler saleHandler;
    private HttpRequest httpRequest;
    private Gson gson;

    public Register(){
        inventory = new Inventory();
        paymentHandler = new PaymentHandler();
        saleHandler = new SaleHandler(inventory, paymentHandler);
        httpRequest = new HttpRequest();
        gson = new GsonBuilder().setDateFormat("MMM dd, yyyy").create();

        getProducts();
        getDiscounts();

        startSale();
    }

    private void getProducts(){
        List<Product> productList = new ArrayList<Product>();

        //Collect products from REST webservice and convert it too products
        String response = httpRequest.makeGetReqeust(URL.BASE_URL + URL.PRODUCT_URI);
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(response);
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        for (int i = 0; i < jsonArray.size(); i++){
            Product product = gson.fromJson(jsonArray.get(i), Product.class);
            productList.add(product);
        }

        inventory.setProductList(productList);
    }

    private void getDiscounts(){
        List<Discount> discountList = new ArrayList<Discount>();

        //Collect todays discounts from REST webservice and convert it too discounts
        String response = httpRequest.makeGetReqeust(URL.BASE_URL + URL.DISCOUNT_TODAY_URI);
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(response);
        JsonArray jsonArray = jsonElement.getAsJsonArray();

        Discount discount;
        for (int i = 0; i < jsonArray.size(); i++){
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            if (jsonObject.has("quantity")){
                discount = gson.fromJson(jsonObject, QuantityDiscount.class);
            }
            else if (jsonObject.has("percentage")){
                discount = gson.fromJson(jsonObject, PercentageDiscount.class);
            }
            else {
                continue;
            }
            discountList.add(discount);
        }

        paymentHandler.setDiscountList(discountList);
    }

    private void startSale(){
        saleHandler.newSale();
    }

    public static void main(String[] args){
        new Register();
    }
}
