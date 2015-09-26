package Controllers;

import Models.Discount;
import Models.Product;
import Models.QuantityDiscount;
import Utils.HttpRequest;
import Utils.URL;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remco on 25-9-2015.
 */
public class Register {

    private Inventory inventory;
    private PaymentHandler paymentHandler;
    private HttpRequest httpRequest;
    private Gson gson;

    public Register(){
        inventory = new Inventory();
        paymentHandler = new PaymentHandler();
        httpRequest = new HttpRequest();
        gson = new Gson();

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
        //TODO collect discounts of today from backend

        //Dummy data
        List<Discount> discountList = new ArrayList<Discount>();
        discountList.add(new QuantityDiscount(inventory.searchProduct(123), 3));
        //End dummy data

        paymentHandler.setDiscountList(discountList);
    }

    private void startSale(){
        SaleHandler sale = new SaleHandler(inventory, paymentHandler);
    }

    public static void main(String[] args){
        new Register();
    }
}
