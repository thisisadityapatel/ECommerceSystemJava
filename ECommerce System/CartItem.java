
//CLASS CARTITEM
public class CartItem extends Product{
    Product product;
    String productOption;

    CartItem(Product product, String productOption){
        this.product = product;
        this.productOption = productOption;
    }

    //method to return the string output
    public String toString(){
        return(String.format("Id: %-5s Category: %-9s Name: %-20s Price: %7.1f", product.getId(), product.getCategory(), product.getName(), product.getPrice()));
    }

    //mthod to get the product id of the product
    public String getProductId(){
        return this.product.getId();
    }

    //product to the the product option of the product
    public String getProductOptions(){
        return this.productOption;
    }
}