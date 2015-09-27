package RESTControllers;

import RESTModels.Discount;
import RESTModels.PercentageDiscount;
import RESTModels.Product;
import RESTModels.QuantityDiscount;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Remco on 27-9-2015.
 */
@Path("/discounts")
public class DiscountController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        //TODO get all discounts from DB and return them as JSON

        //Dummy data
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Discount> discounts = new ArrayList<Discount>();
        try {
            Date start1 = df.parse("20/09/2015");
            Date end1 = df.parse("30/09/2015");
            Date start2 = df.parse("25/09/2015");
            Date end2 = df.parse("30/09/2015");

            discounts.add(new QuantityDiscount(1, new Product(100, "Chips", 1.99), 2, start1, end1));
            discounts.add(new PercentageDiscount(2, new Product(150, "Peanuts", 0.99), 25, start2, end2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //End dummy data

        Gson gson = new Gson();
        return Response.ok(gson.toJson(discounts)).build();
    }

    @Path("/today")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToday(){
        //TODO get daily discounts from DB and return them as JSON

        //Dummy data
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Discount> discounts = new ArrayList<Discount>();
        try {
            Date start1 = df.parse("20/09/2015");
            Date end1 = df.parse("30/09/2015");
            Date start2 = df.parse("25/09/2015");
            Date end2 = df.parse("30/09/2015");

            discounts.add(new QuantityDiscount(1, new Product(100, "Chips", 1.99), 2, start1, end1));
            discounts.add(new PercentageDiscount(2, new Product(150, "Peanuts", 0.99), 25, start2, end2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //End dummy data

        ArrayList<Discount> dailyDiscounts = new ArrayList<Discount>();
        Date date = new Date();
        for (Discount discount : discounts){
            if (date.after(discount.getStartDate()) && date.before(discount.getEndDate())){
                dailyDiscounts.add(discount);
            }
        }

        Gson gson = new Gson();
        return Response.ok(gson.toJson(dailyDiscounts)).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscount(@PathParam("id") int id){
        //TODO get specific discount from DB by id and return it as JSON

        //Dummy data
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Discount> discounts = new ArrayList<Discount>();
        try {
            Date start1 = df.parse("20/09/2015");
            Date end1 = df.parse("30/09/2015");
            Date start2 = df.parse("25/09/2015");
            Date end2 = df.parse("30/09/2015");

            discounts.add(new QuantityDiscount(1, new Product(100, "Chips", 1.99), 2, start1, end1));
            discounts.add(new PercentageDiscount(2, new Product(150, "Peanuts", 0.99), 25, start2, end2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //End dummy data

        Gson gson = new Gson();

        for (Discount discount : discounts){
            if (discount.getId() == id){
                return Response.ok(gson.toJson(discount)).build();
            }
        }
        return Response.ok(gson.toJson("Discount not found")).build();
    }
}
