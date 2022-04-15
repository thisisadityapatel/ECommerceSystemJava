
import java.util.ArrayList;
import java.util.Scanner;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface {
    public static void main(String[] args) {
        // Create the system
        ECommerceSystem amazon = new ECommerceSystem();

        Scanner scanner = new Scanner(System.in);
        System.out.print(">");

        // Process keyboard actions
        while (scanner.hasNextLine()) {
            String action = scanner.nextLine();

            if (action == null || action.equals("")) {
                System.out.print("\n>");
                continue;
            } else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
                return;

            else if (action.equalsIgnoreCase("PRODS")) // List all products for sale
            {
                amazon.printAllProducts();
            } else if (action.equalsIgnoreCase("BOOKS")) // List all books for sale
            {
                amazon.printAllBooks();
            } else if (action.equalsIgnoreCase("SHOES")) {
                amazon.printAllShoes();
            } else if (action.equalsIgnoreCase("CUSTS")) // List all registered customers
            {
                amazon.printCustomers();
            } else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
            {
                amazon.printAllOrders();
            } else if (action.equalsIgnoreCase("SHIPPED")) // List all orders that have been shipped
            {
                amazon.printAllShippedOrders();
            } else if (action.equalsIgnoreCase("NEWCUST")) // Create a new registered customer
            {
                String name = "";
                String address = "";

                System.out.print("Name: ");
                if (scanner.hasNextLine())
                    name = scanner.nextLine();

                System.out.print("\nAddress: ");
                if (scanner.hasNextLine())
                    address = scanner.nextLine();

                try{
                    amazon.createCustomer(name, address);
                    System.out.println("Customer Details Added.");
                }
                catch(InvalidCustomerName x){
                    System.out.println(x.getMessage());
                }
                catch(InvalidAddress y){
                    System.out.println(y.getMessage());
                }
            }


            
            else if (action.equalsIgnoreCase("SHIP")) // ship an order to a customer
            {
                String orderNumber = "";

                System.out.print("Order Number: ");
                // Get order number from scanner
                if (scanner.hasNextLine()) {
                    orderNumber = scanner.nextLine();
                }

                // Ship order to customer (see ECommerceSystem for the correct method to use
                try{
                    amazon.shipOrder(orderNumber);
                }
                catch(InvalidOrderNumber x){
                    System.out.println(x.getMessage());
                }
                

            } else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this
                                                              // customer id
            {
                String customerId = "";

                System.out.print("Customer Id: ");
                // Get customer Id from scanner
                if (scanner.hasNextLine()) {
                    customerId = scanner.nextLine();
                }

                // Print all current orders and all shipped orders for this customer
                try{
                    amazon.printOrderHistory(customerId);
                }
                catch(UnknownCustomer x){
                    System.out.println(x.getMessage());
                }

            } else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
            {
                String productId = "";
                String customerId = "";
                System.out.print("Product Id: ");
                // Get product Id from scanner
                if (scanner.hasNextLine()) {
                    productId = scanner.nextLine();
                }

                System.out.print("\nCustomer Id: ");
                // Get customer Id from scanner
                if (scanner.hasNextLine()) {
                    customerId = scanner.nextLine();
                }

                // Order the product. Check for valid orderNumber string return and for error
                // message set in ECommerceSystem
                try{
                    amazon.orderProduct(productId, customerId, null);
                }
                catch(UnknownCustomer x){
                    System.out.println(x.getMessage());
                }
                catch(UnknownProduct y){
                    System.out.println(y.getMessage());
                }
                catch(ProductOutOfStock z){
                    System.out.println(z.getMessage());
                }

            } 
            else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format
            {
                String productId = "";
                String customerId = "";
                String options = "";

                System.out.print("Product Id: ");
                // get product id
                if (scanner.hasNextLine()) {
                    productId = scanner.nextLine();
                }

                System.out.print("\nCustomer Id: ");
                // get customer id
                if (scanner.hasNextLine()) {
                    customerId = scanner.nextLine();
                }

                System.out.print("\nFormat [Paperback Hardcover EBook]: ");
                // get book forma and store in options string
                if (scanner.hasNextLine()) {
                    options = scanner.nextLine();
                }

                // Order product. Check for error mesage set in ECommerceSystem
                try{
                    amazon.orderProduct(productId, customerId, options);
                }
                catch(UnknownCustomer w){
                    System.out.println(w.getMessage());
                }
                catch(UnknownProduct x){
                    System.out.println(x.getMessage());
                }
                catch(InvalidProductOptions y){
                    System.out.println(y.getMessage());
                }
                catch(ProductOutOfStock z){
                    System.out.println(z.getMessage());
                }

            } else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color
            {
                String productId = "";
                String customerId = "";
                String options = "";
                String size = "";
                String color = "";

                System.out.print("Product Id: ");
                // get product id
                if (scanner.hasNextLine()) {
                    productId = scanner.nextLine();
                }

                System.out.print("\nCustomer Id: ");
                // get customer id
                if (scanner.hasNextLine()) {
                    customerId = scanner.nextLine();
                }

                // Getting the size and the Color of the shoe pair required
                // getting the size of the shoe
                System.out.print("\nSize /6 /7 /8 /9 /10 : ");
                if (scanner.hasNextLine()) {
                    size = scanner.nextLine();
                }
                // getting the color of shoes
                System.out.print("\nColor /Black /Brown : ");
                if (scanner.hasNextLine()) {
                    color = scanner.nextLine();
                }

                options = size.trim() + color.trim();

                // order shoes
                try{
                    amazon.orderProduct(productId, customerId, options);
                }
                catch(UnknownCustomer w){
                    System.out.println(w.getMessage());
                }
                catch(UnknownProduct x){
                    System.out.println(x.getMessage());
                }
                catch(InvalidProductOptions y){
                    System.out.println(y.getMessage());
                }
                catch(ProductOutOfStock z){
                    System.out.println(z.getMessage());
                }

            }
            else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
            {
                String orderNumber = "";

                System.out.print("Order Number: ");
                // get order number from scanner
                if (scanner.hasNextLine()) {
                    orderNumber = scanner.nextLine();
                }

                // cancel order. Check for error
                try{
                    amazon.cancelOrder(orderNumber);
                }
                catch(InvalidOrderNumber x){
                    System.out.println(x.getMessage());
                }
                
            } 
            
            else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort products by price
            {
                amazon.printByPrice();
            } 
            
            else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort products by name (alphabetic)
            {
                amazon.printByName();
            } 
            
            else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
            {
                amazon.sortCustomersByName();
            }

            // making the BooksByAuthor class BONUS
            else if (action.equalsIgnoreCase("BOOKSBYAUTHOR")) {
                String searchAuthor = "";
                // Prompt the user for Author name input
                System.out.print("Author Name: ");

                // making sure that the user has enetered the name of the author
                if (scanner.hasNextLine()) {
                    searchAuthor = scanner.nextLine();
                }

                amazon.searchbyauthor(searchAuthor);
            }

            //ASSIGNMENT 2 TASKS
            
            //ADDTOCART
            //Adding an item to the cart of the customer
            //Taking the input of productId customerId and the product options.
            else if (action.equalsIgnoreCase("ADDTOCART")) {

                String productId = "";
                String customerId = "";
                String options = "";
                String size = "";
                String color = "";

                System.out.print("Product Id: ");
                // Get product Id from scanner
                if (scanner.hasNextLine()) {
                    productId = scanner.nextLine();
                }

                System.out.print("\nCustomer Id: ");
                // Get customer Id from scanner
                if (scanner.hasNextLine()) {
                    customerId = scanner.nextLine();
                }

                //checking if the productId entered is a book, shoes or other
                //if it is book or shoes then prompting the user to enter the details accordingly.
                int flag = 0;
                flag = amazon.getProductCategory(productId);

                if(flag == 2){
                    System.out.print("\nSize /6 /7 /8 /9 /10 : ");
                    if (scanner.hasNextLine()) {
                        size = scanner.nextLine();
                    }
                    // getting the color of shoes
                    System.out.print("\nColor /Black /Brown : ");
                    if (scanner.hasNextLine()) {
                        color = scanner.nextLine();
                    }
                    options = size.trim() + color.trim();
                }

                else if(flag == 1){
                    System.out.print("\nFormat [Paperback Hardcover EBook]: ");
                    // get book forma and store in options string
                    if (scanner.hasNextLine()) {
                        options = scanner.nextLine();
                    }
                }
                
                //adding the product to the cart of the customer
                try{
                    amazon.addToCart(productId, customerId, options);
                }
                catch(UnknownCustomer x){
                    System.out.println(x.getMessage());
                }
                catch(UnknownProduct y){
                    System.out.println(y.getMessage());
                }
            }

            //PRINTCART:- Printing all the items in the cart
            else if(action.equalsIgnoreCase("PRINTCART")){
                String customerId = "";

                //Prompt the user to enter the customer ID
                System.out.print("Customer Id: ");

                //taking input from the user
                if(scanner.hasNextLine()){
                    customerId = scanner.nextLine();
                }
                
                try{
                    amazon.printCustomerCart(customerId);
                }
                catch(UnknownCustomer x){
                    System.out.println(x.getMessage());
                }
            }

            //REMCARTITEM:- Removing the item from the cart
            else if(action.equalsIgnoreCase("REMCARTITEM")){
                String productId = "";
                String customerId = "";

                System.out.print("Product Id: ");
                // Get product Id from scanner
                if (scanner.hasNextLine()) {
                    productId = scanner.nextLine();
                }

                System.out.print("\nCustomer Id: ");
                // Get customer Id from scanner
                if (scanner.hasNextLine()) {
                    customerId = scanner.nextLine();
                }

                //adding the product to the cart of the customer
                try{
                    amazon.removeFromCart(productId, customerId);
                }
                catch(UnknownCustomer x){
                    System.out.println(x.getMessage());
                }
                catch(UnknownProduct y){
                    System.out.println(y.getMessage());
                }
                catch(ProductNotInCart z){
                    System.out.println(z.getMessage());
                }
            }

            //ORDERITEMS:- Ordering all the items in the cart of the customer
            else if(action.equalsIgnoreCase("ORDERITEMS")){
                //prompting the user to enter the customerID
                String customerId = "";
                System.out.print("Customer ID: ");
                if(scanner.hasNextLine()){
                    customerId = scanner.nextLine();
                }

                try{
                    amazon.orderCart(customerId);
                }
                catch(UnknownCustomer x){
                    System.out.println(x.getMessage());
                }
                catch(UnableToPlaceOrder y){
                    System.out.println(y.getMessage());
                }
            }

            //STATS of the products
            else if(action.equalsIgnoreCase("STATS")){
                //printing all the stats of all the products 
                amazon.printStats();
            }

            System.out.print("\n>");
        }
    }
}
