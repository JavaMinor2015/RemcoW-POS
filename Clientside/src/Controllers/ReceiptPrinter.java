package Controllers;

import Models.Product;
import Models.Sale;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Remco on 25-9-2015.
 */
public class ReceiptPrinter {

    private Sale sale;
    private DecimalFormat df;

    public ReceiptPrinter(Sale sale) {
        this.sale = sale;
        df = new DecimalFormat("#0.00");
    }

    public void printReceipt(){
        HashMap<Product, Integer> products = sale.getProductList();

        System.out.println("------------------------------");
        for (Map.Entry<Product, Integer> entry : products.entrySet()){
            Product p = entry.getKey();
            System.out.println(p.getName() + " " + entry.getValue() + "x\t$" + df.format(p.getPrice() * entry.getValue()));
        }
        System.out.println("\nTotal: \t\t$" + df.format(sale.getTotal()));
        System.out.println("Discount: \t$" + df.format(sale.getDiscount()));
        System.out.println("New price: \t$" + df.format(sale.getTotal() - sale.getDiscount()));
        System.out.println("------------------------------");
    }
}
