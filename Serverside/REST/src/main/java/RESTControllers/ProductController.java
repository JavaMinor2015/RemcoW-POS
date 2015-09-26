package RESTControllers;

import RESTModels.Product;
import RESTUtils.Identifiers;
import RESTUtils.Paths;
import RESTUtils.URLAttacher;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by Remco on 26-9-2015.
 */
@Path("/products")
public class ProductController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        //TODO get all products from DB and return them as JSON

        //Dummy data
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product(100, "Chips", 1.99));
        products.add(new Product(123, "Beer", 1.50));
        products.add(new Product(150, "Peanuts", 0.99));
        //End dummy data

        Gson gson = new Gson();
        JsonElement element = URLAttacher.attachURLS(gson.toJsonTree(products), Paths.BASE_URL + Paths.PRODUCT_URI, Identifiers.Product);
        return Response.ok(gson.toJson(element)).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") int id){
        //TODO get product by id from DB and return it as JSON
        return null;
    }
}
