package Controllers;

import Models.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by @author Matthijs van der Meijden on 30-9-2015.
 */
public class InventoryTest {

    private Inventory inventory;
    private ArrayList<Product> productList;
    private Product testProduct;

    @Before
    public void insertDummyData(){
        inventory = new Inventory();
        productList = new ArrayList<Product>();
        testProduct = new Product(123, "TestProduct", 1.50);
        productList.add(testProduct);
        inventory.setProductList(productList);
    }

    @Test
    public void testSearchProductExistingProduct() throws Exception {
        Product product = inventory.searchProduct(123);

        assertThat(product, equalTo(testProduct));
    }

    @Test
    public void testSearchProductNonExistingProduct() throws Exception {
        Product product = inventory.searchProduct(321);

        assertThat(product, equalTo(null));
    }
}