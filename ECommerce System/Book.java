
/* A book IS A product that has additional information - e.g. title, author

 	 A book also comes in different formats ("Paperback", "Hardcover", "EBook")
 	 
 	 The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Book extends Product {
    private String author;
    private String title;
    private int year;

    // Stock related information NOTE: inherited stockCount variable is used for
    // EBooks
    int paperbackStock;
    int hardcoverStock;

    public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title,
            String author, int year) {
        // Make use of the constructor in the super class Product. Initialize additional
        // Book instance variables.
        // Set category to BOOKS
        super(name, id, price, 0, Product.Category.BOOKS);
        this.paperbackStock = paperbackStock;
        this.hardcoverStock = hardcoverStock;
        this.author = author;
        this.title = title;
        this.year = year;
    }

    // Check if a valid format
    public boolean validOptions(String productOptions) {
        // check productOptions for "Paperback" or "Hardcover" or "EBook"
        // if it is one of these, return true, else return false
        if (productOptions == null) {
            return false;
        }
        if (productOptions.equalsIgnoreCase("Paperback") || productOptions.equalsIgnoreCase("Hardcover")
                || productOptions.equalsIgnoreCase("EBook")) {
            return true;
        }
        return false;
    }

    // Override getStockCount() in super class.
    public int getStockCount(String productOptions) {
        // Use the productOptions to check for (and return) the number of stock for
        // "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top.
        // For "EBook", use the inherited stockCount variable.

        if (productOptions.equalsIgnoreCase("Paperback")) {
            return paperbackStock;
        }
        if (productOptions.equalsIgnoreCase("Hardcover")) {
            return hardcoverStock;
        }
        if (productOptions.equalsIgnoreCase("EBook")) {
            super.getStockCount(productOptions);
        }
        return 1;
    }

    public void setStockCount(int stockCount, String productOptions) {
        // Use the productOptions to check for (and set) the number of stock for
        // "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top.
        // For "EBook", set the inherited stockCount variable.
        if (productOptions.equalsIgnoreCase("Paperback")) {
            this.paperbackStock = stockCount;
        }
        if (productOptions.equalsIgnoreCase("Hardcover")) {
            this.hardcoverStock = stockCount;
        }
        if (productOptions.equalsIgnoreCase("EBook")) {
            super.setStockCount(stockCount, productOptions);
        }
    }

    /*
     * When a book is ordered, reduce the stock count for the specific stock type
     */
    public void reduceStockCount(String productOptions) {
        // Use the productOptions to check for (and reduce) the number of stock for
        // "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top.
        // For "EBook", set the inherited stockCount variable.
        if (productOptions.equalsIgnoreCase("Paperback")) {
            this.paperbackStock--;
        }
        if (productOptions.equalsIgnoreCase("Hardcover")) {
            this.hardcoverStock--;
        }
        if (productOptions.equalsIgnoreCase("EBook")) {
            super.reduceStockCount("");
        }
    }

    /*
     * Print product information in super class and append Book specific information
     * title and author
     */
    public void print() {
        // Replace the line below.
        // Make use of the super class print() method and append the title and author
        // info. See the video
        super.print();
        System.out.printf(" Title: %-5s Author: %-20s  Year: %-7d", this.title, this.author, this.year);
    }

    // BONUS feature
    // returning the year of the book
    public int getBookYear() {
        return this.year;
    }

    // getting the name of the author
    public String getAuthorName() {
        return this.author;
    }

}
