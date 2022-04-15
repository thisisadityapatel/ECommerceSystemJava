
import java.io.File;
import java.io.IOException;
import java.util.*;


/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem {
    //Product TreeMap in accordance to Assignment2
    private TreeMap<String, Product> products = new TreeMap<String, Product>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    private ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders = new ArrayList<ProductOrder>();

    //STATS TreeMap which keeps track of the number of times a product is ordered. 
    TreeMap<String, Integer> stats = new TreeMap<String, Integer>();

    // These variables are used to generate order numbers, customer id's, product id's
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;

    // Random number generator
    Random random = new Random();

    public ECommerceSystem() {
        // NOTE: do not modify or add to these objects!! - the TAs will use for testing

        //reading the "products.txt" file and collecting all the product details from it
        // using try-catch method to make sure the files opens and is readable. 
        try{
            //Extracting data from the file.
            extractDataFromFile("products.txt");
        }
        //catching errors, if any.
        catch(IOException io){
            System.out.println(io.getMessage());
            System.exit(1);
        }

        // Adding a multiple pairs of SHOES to the products HashMap.
        int[] blackStock = { 120, 120, 120, 120, 120 };  //temporary variables used for stock initialization
        int[] brownStock = { 120, 120, 120, 120, 120 };

        String pId = "";
        pId = generateProductId();
        products.put(pId, new Shoes("Nike Air Jordans 1", pId, 400.0, blackStock, brownStock));
        
        pId = generateProductId();
        products.put(pId, new Shoes("Puma Gravity Freelace", pId, 112.0, blackStock, brownStock));
       
        pId = generateProductId();
        products.put(pId, new Shoes("Sketchers Memoryform", pId, 164.0, blackStock, brownStock));
        
        pId = generateProductId();
        products.put(pId, new Shoes("Adidas NMDs", pId, 140.0, blackStock, brownStock));
        
        pId = generateProductId();
        products.put(pId, new Shoes("Nike Air Force 1", pId, 220.0, blackStock, brownStock));

        //Adding all the products into the STATS TreeMap to initialise the number of times ordered to Zero.
        for(String i: products.keySet()){
            stats.put(i, 0);
        }

        // Create some customers. Notice how generateCustomerId() method is used
        customers.add(new Customer(generateCustomerId(), "Inigo Montoya", "1 SwordMaker Lane, Florin"));
        customers.add(new Customer(generateCustomerId(), "Prince Humperdinck", "The Castle, Florin"));
        customers.add(new Customer(generateCustomerId(), "Andy Dufresne", "Shawshank Prison, Maine"));
        customers.add(new Customer(generateCustomerId(), "Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    }

    //Making a private method to extract the data from the file and appending it to the arraylist products.
    private void extractDataFromFile(String filename) throws IOException{

        File file = new File(filename);  //Opening the file
        Scanner in = new Scanner(file);  //Setting a scanner, to read the file.

        while(in.hasNextLine()){
    
            String category = in.nextLine();
            String name = "";
            double price = 0.0;

            //If the product is BOOOKS then reading the file accordingly
            if(category.equals("BOOKS")){
                name = in.nextLine();                                           //reading the name.
                price = Double.parseDouble(in.nextLine());                      //reading the price
                int paperbackCount = Integer.parseInt(in.next());               //reading the stock for PaperBack    
                int hardcoverCount = Integer.parseInt(in.nextLine().strip());   //reading the stock for hardcover.
                
                String[] details = in.nextLine().split(":");                    //reading the title and the author name.
                String title = details[0];      
                String author = details[1];

                int date = Integer.parseInt(details[2]);                        //reading the date of the book.
                String pId = generateProductId();                               //generating the product ID
                //Adding the product into the products TreeMap
                products.put(pId, new Book(name, pId, price, paperbackCount, hardcoverCount, title, author, date));
            }

            else{
                name = in.nextLine();                              //Reading the name
                price = Double.parseDouble(in.nextLine());         //Reading the price
                int quantity = Integer.parseInt(in.nextLine());    //Reading the Quantity
                in.nextLine();
                String pId = generateProductId();
                //Adding the product to the products TreeMap according to the product Category.
                if(category.equals("GENERAL")){
                    products.put(pId, new Product(name, pId, price, quantity, Product.Category.GENERAL));
                }

                else if(category.equals("CLOTHING")){
                    products.put(pId, new Product(name, pId, price, quantity, Product.Category.CLOTHING));
                }

                else if(category.equals("FURNITURE")){
                    products.put(pId, new Product(name, pId, price, quantity, Product.Category.FURNITURE));
                }
                
                else if(category.equals("COMPUTER")){
                    products.put(pId, new Product(name, pId, price, quantity, Product.Category.COMPUTERS));
                }
            }
        }
    }
    //Generate order number
    private String generateOrderNumber() {
        return "" + orderNumber++;
    }

    //Generate order ID
    private String generateCustomerId() {
        return "" + customerId++;
    }

    //Generate productID
    private String generateProductId() {
        return "" + productId++;
    }

    //Printing all the products
    public void printAllProducts() {
        for (String pId : products.keySet())
            products.get(pId).print();
        System.out.print("\n");
    }

    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks() {
        // Loops through the arraylist product, and then checking if its category is
        // Books,
        // if so. then printing the output.
        for(String pId : products.keySet()) {
            if (products.get(pId).getCategory() == Product.Category.BOOKS) {
                products.get(pId).print();
            }
        }
        System.out.print("\n");
    }

    // printing all the product that are SHOES.
    public void printAllShoes() {
        // Loops through the arraylist product, and then checking if its category is
        // Shoes,
        // if so. then printing the output.
        for (String pId : products.keySet()) {
            if (products.get(pId).getCategory() == Product.Category.SHOES) {
                products.get(pId).print();
            }
        }
        System.out.print("\n");
    }

    // Print all current orders
    public void printAllOrders() {
        // Looping through every element in the arraylist orders and then printing it.
        for (ProductOrder o : orders) {
            o.print();
        }
        System.out.print("\n");
    }

    // Print all shipped orders
    public void printAllShippedOrders() {
        // Looping though every element in the arraylist shippedOrders and then printing
        // it.
        for (ProductOrder o : shippedOrders) {
            o.print();
        }
    }

    // Print all customers
    public void printCustomers() {
        // Looping through every element in the arraylist customers and then printing
        // it.
        for (Customer c : customers) {
            c.print();
        }
        System.out.print("\n");
    }

    /*
     * Given a customer id, print all the current orders and shipped orders for them
     * (if any)
     */
    public void printOrderHistory(String customerId) {
        // Make sure customer exists - check using customerId
        // If customer does not exist, set errMsg String and return false
        // see video for an appropriate error message string
        // ... code here
        boolean flag = false; // flag to keep track if the customer exists (Initially False, assuming that no
                              // customer exists).
        Customer customer = null; // Initialising an object to use it as a refference in case we find the
                                  // customer.
        for (Customer c : customers) { // Looping through each customer in the arraylist
            if (c.getId().equals(customerId)) { // Condition, if we find the right customer by comparing their ID's
                customer = c; // Pointing the object (initialized earlier) to refference this customer.
                flag = true; // Setting flag = true, meaning we found the right customer.
                break; // breaking out of the loop
            }
        }
        if (flag == false) { // if the flag is still false, meaning we did not find the specified customer.
            throw new UnknownCustomer("Customer " + customerId + " Not Found");// Setting an error message.
        }

        // Print current orders of this customer
        System.out.println("Current Orders of Customer " + customerId); // printing all the current orders of the
                                                                        // customer
        // enter code here
        for (ProductOrder o : orders) { // Looping through every order in the ArrayList orders
            if (o.getCustomer().getId().equals(customer.getId())) { // if the order was placed by the same customer, by
                                                                    // comparing their ID's
                o.print(); // Then printing the order using print() function.
            }
        }

        // Print shipped orders of this customer
        System.out.println("\nShipped Orders of Customer " + customerId); // printing the shipped orders of the customer
        // enter code here
        for (ProductOrder o : shippedOrders) { // looping through everyshipped order in the arralist shippedOrders
            if (o.getCustomer().getId().equals(customer.getId())) { // if the order was place by the customer, by
                                                                    // comparing their ID's
                o.print(); // Then printing the shipped order using the print() function.
            }
        }
    }

    public void orderProduct(String productId, String customerId, String productOptions) {
        // First check to see if customer object with customerId exists in array list
        // customers
        // if it does not, set errMsg and return null (see video for appropriate error
        // message string)
        // else get the Customer object
        boolean flag = false;
        Customer customer = null;
        for (Customer c : customers) {
            if (c.getId().equals(customerId)) {
                flag = true;
                customer = c;
                break;
            }
        }
        if (flag == false) {
            throw new UnknownCustomer("Customer " + customerId + " Not Found"); // Setting an error message if the product is not found in arraylist                                                                           
        }

        // Check to see if product object with productId exists in array list of
        // products
        // if it does not, set errMsg and return null (see video for appropriate error
        // message string)
        // else get the Product object
        boolean flag2 = false;
        Product product = null;
        for (String pId : products.keySet()) {
            if (products.get(pId).getId().equals(productId)) {
                product = products.get(pId);
                flag2 = true;
                break;
            }
        }
        if (flag2 == false) {
            throw new UnknownProduct("Product " + productId + " Not Found");
        }

        // Check if the options are valid for this product (e.g. Paperback or Hardcover
        // or EBook for Book product)
        // See class Product and class Book for the method vaidOptions()
        // If options are not valid, set errMsg string and return null;
        if (product.getCategory() == Product.Category.BOOKS) {
            if (!product.validOptions(productOptions)) {
                throw new InvalidProductOptions("Product " + product.getCategory() + " ProductId " + product.getId() + " Invalid Options: "
                + productOptions);
            }
        }

        // if the category is SHOES then checking if the product option entered is
        // correct or not
        // if the input is not valid then setting the error message accordingly.
        if (product.getCategory() == Product.Category.SHOES) {
            if (!product.validOptions(productOptions)) {
                throw new InvalidProductOptions("Product " + product.getCategory() + " ProductId " + product.getId() + " Invalid Options: "
                + productOptions);
            }
        }

        // Check if the product has stock available (i.e. not 0)
        // See class Product and class Book for the method getStockCount()
        // If no stock available, set errMsg string and return null
        if (product.getStockCount(productOptions) == 0) {
            throw new ProductOutOfStock("No Stock Available");
        }

        // Create a ProductOrder, (make use of generateOrderNumber() method above)
        // reduce stock count of product by 1 (see class Product and class Book)
        // Add to orders list and return order number string
        ProductOrder ord = new ProductOrder(generateOrderNumber(), product, customer, productOptions);
        product.reduceStockCount(productOptions);
        orders.add(ord);

        //updating the stat treeMap and incrementing the number of time the product is ordered.
        stats.put(productId, stats.get(productId) + 1);
        System.out.println("#" + ord.getOrderNumber());
    }

    /*
     * Create a new Customer object and add it to the list of customers
     */

    public void createCustomer(String name, String address) {
        // Check name parameter to make sure it is not null or ""
        // If it is not a valid name, set errMsg (see video) and return false
        // Repeat this check for address parameter

        // Create a Customer object and add to array list
        if (name.equals("")) {
            throw new InvalidCustomerName("Invalid Customer Name");
        }
        if (address.equals("")) {
            throw new InvalidAddress("Invalid Customer Address");
        }
        Customer c = new Customer(generateCustomerId(), name, address);
        customers.add(c);
    }

    public void shipOrder(String orderNumber) {
        // Check if order number exists first. If it doesn't, set errMsg to a message
        // (see video)
        // and return false
        // Retrieve the order from the orders array list, remove it, then add it to the
        // shippedOrders array list
        // return a reference to the order
        boolean flag = false;
        for (ProductOrder o : orders) {
            if (o.getOrderNumber().equals(orderNumber)) {
                flag = true;
                break;
            }
        }
        if (flag == false) {
            throw new InvalidOrderNumber("Order " + orderNumber + " Not Found");
        }
        ProductOrder ans = null;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderNumber().equals(orderNumber)) {
                shippedOrders.add(orders.get(i));
                ans = orders.get(i);
                orders.remove(i);
                break;
            }
        }
    }

    /*
     * Cancel a specific order based on order number
     */
    public void cancelOrder(String orderNumber) {
        // Check if order number exists first. If it doesn't, set errMsg to a message
        // (see video)
        // and return false
        boolean flag = false;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderNumber().equals(orderNumber)) {
                flag = true;
                orders.remove(i);
                break;
            }
        }
        if (flag == false) {
            throw new InvalidOrderNumber("Order " + orderNumber + " Not Found");
        }
    }

    // Class that implements the Comparator interface
    public class CustomComparator1 implements Comparator<Product>{
        public int compare(Product p1, Product p2){
            if(p1.getPrice() > p2.getPrice()){
                return -1;
            }
            else if(p1.getPrice() == p2.getPrice()){
                return 0;
            }
            else{
                return +1;
            }
        }
    }

    //Printing the all the products, sorted in INCREASING ORDER of their PRINCE
    public void printByPrice(){
        //converting the TreeMap to an ArrayList
        ArrayList<Product> temp = new ArrayList<Product>();
        for(String i : products.keySet()){
            temp.add(products.get(i));
        } 

        //Sorting the arraylist temp
        Collections.sort(temp, new CustomComparator1());

        //Printing the sorted arraylist temp
        for(Product i : temp){
            i.print();
        }
        System.out.print("\n");
    }
    


    // Print products alphabetically sorted by their name
    public class CustomComparator2 implements Comparator<Product>{
        public int compare(Product p1, Product p2){
            return p1.getName().compareTo(p2.getName());
        }
    }

    //printing all the products
    public void printByName(){
        //converting the TreeMap to an ArrayList
        ArrayList<Product> temp = new ArrayList<Product>();
        for(String i : products.keySet()){
            temp.add(products.get(i));
        } 
        
        //Sorting the arraylist temp
        Collections.sort(temp, new CustomComparator2());

        //Printing the sorted arraylist temp
        for(Product i : temp){
            i.print();
        }
        System.out.println("\n");
    }

    

    // Sort customers alphabetically by customer name
    public class CustomComparator3 implements Comparator<Customer> {
        @Override
        public int compare(Customer p1, Customer p2) {
            return p1.getName().compareToIgnoreCase(p2.getName());
        }
    }

    public void sortCustomersByName() {
        Collections.sort(customers, new CustomComparator3());
    }

    // BONUS PROGRAM PART - Returning the books by a particular author in Sorted
    // Order(on basis of year published).
    // Making the function method SEARCHBYAUTHOR.

    // Class CustomComparator4 that implements the interface Comparator to sort the
    // book for a specific author
    // on basis of the date they were published at.
    public class CustomComparator4 implements Comparator<Product> {
        @Override
        public int compare(Product p1, Product p2) {
            return Integer.compare(p1.getBookYear(), p2.getBookYear());
        }
    }

    public void searchbyauthor(String author) {
        ArrayList<Product> temp = new ArrayList<Product>();
        for (String pId : products.keySet()) {
            if (products.get(pId).getCategory() == Product.Category.BOOKS) {
                temp.add(products.get(pId));
            }
        }
        // sorting the product books that were in arralist temp
        Collections.sort(temp, new CustomComparator4());

        for (Product b : temp) {
            if (b.getAuthorName().equalsIgnoreCase(author)) {
                b.print();
            }
        }
    }

    //ASSIGNMENT 2 STUFF
    //------------------

    //Method to get the product category based on the productID
    public int getProductCategory(String productId){
        if(products.containsKey(productId)){
            if(products.get(productId).getCategory() == Product.Category.BOOKS){
                return 1;  //Returning 1 if the product category is BOOKS
            }
            else if(products.get(productId).getCategory() == Product.Category.SHOES){
                return 2;   //Returning 2 if the product category is SHOES
            }
            else{
                return 0;   //Returning 0 if none.
            }
        }
        return 0;
    }


    //Adding a product to the cart of the customer
    public void addToCart(String productId, String customerId, String productOption){
        //making sure that the productId entered is valid or not.
        boolean flag1 = false;
        Product p = null;
        for(String pId : products.keySet()){
            if(products.get(pId).getId().equals(productId)){
                flag1 = true;
                p = products.get(pId);
                break;
            }
        }

        //if invalid productid then throwing an error 
        if(flag1 == false){
            throw new UnknownProduct("Product " + productId + " Not Found.");
        }

        //making sure that the customerId entered is valid or not
        boolean flag2 = false;
        Customer c = null;
        for(Customer cu : customers){
            if(cu.getId().equals(customerId)){
                c = cu;
                flag2 = true;
                break;
            }
        }

        //if invalid customer id then throwing an error
        if(flag2 == false){
            throw new UnknownCustomer("Customer " + customerId + " Not Found.");
        }

        //making the item that is to be added into the cart;
        CartItem item = new CartItem(p, productOption);

        //adding the cartItem into the cart of the customer
        c.getCart().addCartItem(item);
        
        //if everything goes well returning string.
        System.out.println("Product " + productId + " Added to Cart of Customer " + customerId);
    }

    public void printCustomerCart(String customerId){
        //making sure that the customer exists and if so getting the refference to that customer
        boolean flag = false;
        Customer c = null;

        for(Customer cu : customers){
            if(cu.getId().equals(customerId)){
                flag = true;
                c = cu;
                break;
            }
        }

        //if invalid customer id then throwing an error
        if(flag == false){
            throw new UnknownCustomer("Customer " + customerId + " Not Found.");
        }

        Cart crt = c.getCart();
        crt.printItems();
        System.out.println("\n");
    }

    //removing a product from the cart of the customer
    public void removeFromCart(String productId, String customerId){
        //making sure that the productId entered is valid or not.
        boolean flag1 = false;
        Product p = null;
        for(String pId : products.keySet()){
            if(products.get(pId).getId().equals(productId)){
                flag1 = true;
                p = products.get(pId);
                break;
            }
        }

        //if invalid product id then throwing an error
        if(flag1 == false){
            throw new UnknownProduct("Product " + productId + " Not Found.");
        }

        //making sure that the customerId entered is valid or not
        boolean flag2 = false;
        Customer c = null;
        for(Customer cu : customers){
            if(cu.getId().equals(customerId)){
                c = cu;
                flag2 = true;
                break;
            }
        }

        //if invalid customer id then throwing an error
        if(flag2 == false){
            throw new UnknownCustomer("Customer " + customerId + " Not Found.");
        }

        //searching for the cart item in the cart of the customer
        Cart ct = c.getCart();
        if(!ct.containsItem(p.getId())){
            throw new ProductNotInCart("Product " + productId + " Not in Customer " + customerId + " Cart");
        }

        ct.removeItem(productId);
        System.out.println("Product " + productId + " Removed From Cart.");

    }
    
    //Ordering all the items in the cart.
    public void orderCart(String customerId){
        boolean flag = false;
        Customer c = null;
        for(Customer cu : customers){
            if(cu.getId().equalsIgnoreCase(customerId)){
                flag = true;
                c = cu;
                break;
            }
        }
        if(flag == false){
            throw new UnknownCustomer("Customer " + customerId + " Not Found.");
        }
        
        Cart customerCart = c.getCart();

        for(CartItem ci : customerCart.getCartArray()){
            try{
                this.orderProduct(ci.getProductId(), customerId, ci.getProductOptions());
            }
            catch(UnknownCustomer w){
                throw new UnableToPlaceOrder("Unable To Place the Order");
            }
            catch(UnknownProduct x){
                throw new UnableToPlaceOrder("Unable To Place the Order");
            }
            catch(InvalidProductOptions y){
                throw new UnableToPlaceOrder("Unable To Place the Order");
            }
            catch(ProductOutOfStock z){
                throw new UnableToPlaceOrder("Unable To Place the Order");
            }
        }
        //removing all the items from the cart after placing the order
        c.getCart().removeAllItems();
        System.out.println("Order Placed from Cart");
   
    }

    //making a custom class to sort the STATS of the orders
    public static <K, V extends Comparable<V> > Map<K, V>
    valueSort(final Map<K, V> map)
    {
        // Static Method with return type Map and
        // extending comparator class which compares values
        // associated with two keys
        Comparator<K> valueComparator = new Comparator<K>() {
            
                  // return comparison results of values of
                  // two keys
                  public int compare(K k1, K k2)
                  {
                      int comp = map.get(k1).compareTo(
                          map.get(k2));
                      if (comp == 0)
                          return 1;
                      else
                          return comp*-1;
                  }
            
              };
        
        // SortedMap created using the comparator
        Map<K, V> sorted = new TreeMap<K, V>(valueComparator);
        
        sorted.putAll(map);
        
        return sorted;
    }

    public void printStats(){
        //copying all the data from the TreeMap stats into a temporary arralist.
        //And then printing the arraylist after sorting it.
        Map<String, Integer> sortedStats = valueSort(stats);

        for(String i: sortedStats.keySet()){
            System.out.println(String.format("Id: %-7s Name: %-25s Count: %9d", i, products.get(i).getName(), stats.get(i)));
        }

    }
}

//Creating Custom Exception Class for different kind of exceptions.

//if the customer id is invalid
class UnknownCustomer extends RuntimeException{
    public UnknownCustomer(String errorMessage){
        super(errorMessage);
    }
}

//if the product id is invalid
class UnknownProduct extends RuntimeException{
    public UnknownProduct(String errorMessage){
        super(errorMessage);
    }
}

//if the product option is invalid
class InvalidProductOptions extends RuntimeException{
    public InvalidProductOptions(String errorMessage){
        super(errorMessage);
    }
}

//if the product item is out of stock
class ProductOutOfStock extends RuntimeException{
    public ProductOutOfStock(String errorMessage){
        super(errorMessage);
    }
}

//if the customer name is invalid
class InvalidCustomerName extends RuntimeException{
    public InvalidCustomerName(String errorMessage){
        super(errorMessage);
    }
}

//if the customer address is invalid
class InvalidAddress extends RuntimeException{
    public InvalidAddress(String errorMessage){
        super(errorMessage);
    }
}

//if the order number if invalid
class InvalidOrderNumber extends RuntimeException{
    public InvalidOrderNumber(String errorMessage){
        super(errorMessage);
    }
}

//if the product is not in the customer's cart
class ProductNotInCart extends RuntimeException{
    public ProductNotInCart(String errorMessage){
        super(errorMessage);
    }
}

//if unable to place the order
class UnableToPlaceOrder extends RuntimeException{
    public UnableToPlaceOrder(String errorMessage){
        super(errorMessage);
    }
}





