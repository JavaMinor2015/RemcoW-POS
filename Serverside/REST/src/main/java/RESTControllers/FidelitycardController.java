package RESTControllers;

import RESTModels.Customer;
import RESTModels.FidelityCard;
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

/**
 * Created by Remco on 30-9-2015.
 */
@Path("/fidelitycards")
public class FidelitycardController {

    @GET
    @Path("/{cardcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFidelitycard(@PathParam("cardcode") int cardcode){
        DataHandler dh = new DataHandler();
        String query = "SELECT c.ID, c.FIRST_NAME, c.LAST_NAME, c.EMAIL FROM FIDELITYCARD f, CUSTOMER c WHERE f.CUSTOMER_ID = c.ID AND f.CODE = " + cardcode;
        ResultSet resultSet = dh.executeStatement(query);

        FidelityCard fidelityCard = null;
        try{
            resultSet.next();
            int customerID = resultSet.getInt("ID");
            String customerFirstName = resultSet.getString("FIRST_NAME");
            String customerLastName = resultSet.getString("LAST_NAME");
            String customerMail = resultSet.getString("EMAIL");

            Customer customer = new Customer(customerID, customerFirstName, customerLastName, customerMail);
            fidelityCard = new FidelityCard(cardcode, customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return Response.ok(gson.toJson(fidelityCard)).build();
    }
}
