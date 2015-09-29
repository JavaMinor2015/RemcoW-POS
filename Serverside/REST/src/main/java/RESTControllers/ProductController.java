package RESTControllers;

import RESTModels.Product;
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
import java.util.ArrayList;

/**
 * Created by Remco on 26-9-2015.
 */
@Path("/products")
public class ProductController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        DataHandler dh = new DataHandler();
        String query = "SELECT * FROM PRODUCT";
        ResultSet resultSet = dh.executeStatement(query);

        ArrayList<Product> products = new ArrayList<Product>();
        try {
            while (resultSet.next()) {
                System.out.println( resultSet.getDouble("PRICE"));
                products.add(new Product(resultSet.getInt("CODE"), resultSet.getString("NAME"), resultSet.getDouble("PRICE")));
            }
            dh.closeDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return Response.ok(gson.toJson(products)).build();
    }

    @Path("/{productcode}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("productcode") int productcode){
        //TODO get product by id from DB and return it as JSON

        DataHandler dh = new DataHandler();
        String query = "SELECT * FROM PRODUCT WHERE CODE = " + productcode;
        ResultSet resultSet = dh.executeStatement(query);

        Gson gson = new Gson();
        Product product = null;
        try {
            resultSet.next();
            product = new Product(resultSet.getInt("CODE"), resultSet.getString("NAME"), resultSet.getDouble("PRICE"));
            dh.closeDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Response.ok(gson.toJson(product)).build();
    }
}
