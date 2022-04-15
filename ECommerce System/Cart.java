
import java.util.ArrayList;

public class Cart{
    ArrayList<CartItem> cartitems;      //arraylist cartitems which stores refferences of class CartItem

    //constructor
    Cart(){
        this.cartitems = new ArrayList<CartItem>();
    }

    //cunstructor
    Cart(ArrayList<CartItem> items){
        this.cartitems = items;
    }

    //method for adding an item to the cart
    public void addCartItem(CartItem ci){
        this.cartitems.add(ci);
    }

    //method for printing all the items of the cart
    public void printItems(){
        for(CartItem i: cartitems){
            System.out.println(i);
        }
    }

    //method for removing an item from the arrylist cartitems
    public void removeItem(String productId){
        for(CartItem ci: cartitems){
            if(ci.getProductId().equalsIgnoreCase(productId)){
                cartitems.remove(ci);
                break;
            }
        }
    }

    //boolean return type method to check is the item exists in teh arraylist of not
    public boolean containsItem(String productId){
        for(CartItem ci : cartitems){
            if(ci.getProductId().equalsIgnoreCase(productId)){
                return true;
            }
        }
        return false;
    }

    //method returning the arraylist cartitem
    public ArrayList<CartItem> getCartArray(){
        return this.cartitems;
    }

    //method to clear all the items from the arrayist
    public void removeAllItems(){
        cartitems.clear();
    }
}