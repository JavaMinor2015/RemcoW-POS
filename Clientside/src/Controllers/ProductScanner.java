package Controllers;

import Models.Product;

import java.util.Scanner;

/**
 * Created by Remco on 25-9-2015.
 */
public class ProductScanner {

    private Scanner scanner;
    private Inventory inventory;

    public ProductScanner(Inventory inventory){
        this.inventory = inventory;
        scanner = new Scanner(System.in);
    }

    public Product scanProduct(){
        System.out.println("Enter productcode. Type checkout if done.");
        if (scanner.hasNextInt()){
            int productcode = scanner.nextInt();
            return inventory.searchProduct(productcode);
        }
        else if (scanner.next().equals("checkout")){
            return null;
        }
        else {
            System.out.println("Input invalid");
            return scanProduct();
        }
    }
}
