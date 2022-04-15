//Shoes have two additional information - e.g. size, color

//the shoes come in different Sizes 6, 7, 8, 9, 10

//and different colors i.e. Black, Brown;

public class Shoes extends Product {

    // initializeing the stock count for different sizes in black color
    // sizes for black
    int[] black = new int[5];

    // initializing the stock count for different sizes in brown color
    // sizes for brown
    int[] brown = new int[5];

    // defining the constructor for the class
    Shoes(String name, String id, double price, int[] blackstock, int[] brownstock) {
        super(name, id, price, 0, Product.Category.SHOES);
        for (int i = 0; i < 5; i++) {
            this.black[i] = blackstock[i];
            this.brown[i] = brownstock[i];
        }
    }

    public boolean validOptions(String productOptions) {
        if (productOptions.equals("")) {
            return false;
        }
        if (productOptions.equalsIgnoreCase("6Black") || productOptions.equalsIgnoreCase("7Black")
                || productOptions.equalsIgnoreCase("8Black")
                || productOptions.equalsIgnoreCase("9Black") || productOptions.equalsIgnoreCase("10Black")
                || productOptions.equalsIgnoreCase("6Brown") || productOptions.equalsIgnoreCase("7Brown")
                || productOptions.equalsIgnoreCase("8Brown")
                || productOptions.equalsIgnoreCase("9Brown") || productOptions.equalsIgnoreCase("10Brown")) {
            return true;
        }
        return false;
    }

    public int getStockCount(String productOptions) {
        int size = Character.getNumericValue(productOptions.charAt(0));
        String color = productOptions.substring(1, productOptions.length()).toLowerCase();
        if (color.equalsIgnoreCase("black")) {
            return this.black[size - 6];
        } else if (color.equalsIgnoreCase("brown")) {
            return this.brown[size - 6];
        }
        return 1;
    }

    // Function to set the stock count of a particular shoes option
    public void setStockCount(int stockCount, String productOptions) {
        if (productOptions.substring(0, productOptions.length()).equalsIgnoreCase("Black")) {
            this.black[Character.getNumericValue(productOptions.charAt(0) - 6)] = stockCount;
        } else if (productOptions.substring(0, productOptions.length()).equalsIgnoreCase("Brown")) {
            this.brown[Character.getNumericValue(productOptions.charAt(0) - 6)] = stockCount;
        }
    }

    // Function to reduce the stock count of a particula shoes option
    public void reduceStockCount(String productOptions) {
        if (productOptions.substring(0, productOptions.length()).equalsIgnoreCase("Black")) {
            this.black[Character.getNumericValue(productOptions.charAt(0) - 6)]--;
        } else if (productOptions.substring(0, productOptions.length()).equalsIgnoreCase("Brown")) {
            this.brown[Character.getNumericValue(productOptions.charAt(0) - 6)]--;
        }
    }

    public void print() {
        // Replace the line below.
        // Make use of the super class print() method and append the title and author
        // info. See the video
        super.print();
    }

}
