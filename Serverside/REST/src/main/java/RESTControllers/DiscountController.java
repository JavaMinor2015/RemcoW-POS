package RESTControllers;

import RESTModels.Discount;
import RESTModels.PercentageDiscount;
import RESTModels.Product;
import RESTModels.QuantityDiscount;
import RESTUtils.DataHandler;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        DataHandler dh = new DataHandler();
        String query = "select d.ID, d.DISCOUNT_TYPE, d.PRODUCT_ID, d.VALUE, d.START_DATE, d.END_DATE, p.NAME, p.PRICE FROM DISCOUNT d, PRODUCT p WHERE  d.PRODUCT_ID = p.CODE";
        ResultSet resultSet = dh.executeStatement(query);

        ArrayList<Discount> discounts = new ArrayList<Discount>();
        try {
            while (resultSet.next()) {
                Product product = new Product(resultSet.getInt("PRODUCT_ID"), resultSet.getString("NAME"), resultSet.getDouble("PRICE"));
                int id = resultSet.getInt("ID");
                int value = resultSet.getInt("VALUE");
                Date startDate = resultSet.getDate("START_DATE");
                Date endDate = resultSet.getDate("END_DATE");
                switch (resultSet.getInt("DISCOUNT_TYPE")){
                    case 1:
                        discounts.add(new QuantityDiscount(id, product, value, startDate, endDate));
                        break;
                    case 2:
                        discounts.add(new PercentageDiscount(id, product, value, startDate, endDate));
                        break;
                }
            }
            dh.closeDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return Response.ok(gson.toJson(discounts)).build();
    }

    @Path("/today")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToday(){
        DataHandler dh = new DataHandler();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String query = "select d.ID, d.DISCOUNT_TYPE, d.PRODUCT_ID, d.VALUE, d.START_DATE, d.END_DATE, p.NAME, p.PRICE FROM DISCOUNT d, PRODUCT p WHERE d.START_DATE <= '" + df.format(date) + "' AND d.END_DATE >= '" + df.format(date) + "' AND d.PRODUCT_ID = p.CODE";
        ResultSet resultSet = dh.executeStatement(query);

        ArrayList<Discount> discounts = new ArrayList<Discount>();

        try {
            while (resultSet.next()){
                Product product = new Product(resultSet.getInt("PRODUCT_ID"), resultSet.getString("NAME"), resultSet.getDouble("PRICE"));
                int id = resultSet.getInt("ID");
                int value = resultSet.getInt("VALUE");
                Date startDate = resultSet.getDate("START_DATE");
                Date endDate = resultSet.getDate("END_DATE");
                switch (resultSet.getInt("DISCOUNT_TYPE")){
                    case 1:
                        discounts.add(new QuantityDiscount(id, product, value, startDate, endDate));
                        break;
                    case 2:
                        discounts.add(new PercentageDiscount(id, product, value, startDate, endDate));
                        break;
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return Response.ok(gson.toJson(discounts)).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscount(@PathParam("id") int id){
        DataHandler dh = new DataHandler();
        String query = "select d.DISCOUNT_TYPE, d.PRODUCT_ID, d.VALUE, d.START_DATE, d.END_DATE, p.NAME, p.PRICE FROM DISCOUNT d, PRODUCT p WHERE d.ID = " + id + " AND d.PRODUCT_ID = p.CODE";
        ResultSet resultSet = dh.executeStatement(query);

        Discount discount = null;
        try {
            resultSet.next();
            Product product = new Product(resultSet.getInt("PRODUCT_ID"), resultSet.getString("NAME"), resultSet.getDouble("PRICE"));
            int value = resultSet.getInt("VALUE");
            Date startDate = resultSet.getDate("START_DATE");
            Date endDate = resultSet.getDate("END_DATE");
            switch (resultSet.getInt("DISCOUNT_TYPE")){
                case 1:
                    discount = new QuantityDiscount(id, product, value, startDate, endDate);
                    break;
                case 2:
                    discount = new PercentageDiscount(id, product, value, startDate, endDate);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return Response.ok(gson.toJson(discount)).build();
    }
}
