package Controllers;

import Models.Product;
import Models.Sale;
import Utils.SingleLog;

/**
 * Created by Remco on 25-9-2015.
 */
public class SaleHandler {

    private Sale sale;
    private ProductScanner productScanner;
    private PaymentHandler paymentHandler;

    public SaleHandler(Inventory inventory, PaymentHandler paymentHandler){
        sale = new Sale();
        productScanner = new ProductScanner(inventory);
        this.paymentHandler = paymentHandler;
    }

    public void newSale(){
        SingleLog.getLog().addInfo("New sale started");
        waitForProducts();
    }

    private void waitForProducts(){
        Product product = productScanner.scanProduct();
        while (product != null){
            sale.addProduct(product);
            SingleLog.getLog().addInfo("Product scanned: " + product.getName());
            product = productScanner.scanProduct();
        }
        endSale();
    }

    private void endSale(){
        //TODO Save sale in backend

        if (paymentHandler.handlePayment(sale)) {
            ReceiptPrinter rp = new ReceiptPrinter(sale);
            rp.printReceipt();
            SingleLog.getLog().addInfo("End of sale");
        }
        else {
            SingleLog.getLog().addWarning("Payment failed");
        }
    }
}
