package com.niantic.exercises;

import com.niantic.models.OrderLineItem;

import java.util.ArrayList;


/*
The exercises in this class are optional - this is the challenge exercise
 */
public class Order
{
    // this ArrayList is the container (shopping cart) for all items that are being ordered
    private ArrayList<OrderLineItem> shoppingCart = new ArrayList<>();

    public ArrayList<OrderLineItem> getShoppingCart()
    {
        return shoppingCart;
    }

    /*
    1. Add logic to allow a user to add an Item to a shopping cart
        - if the item already exists in the cart, update the quantity
        - search for a line item by name, and add the new quantity to the original quantity
     */
    public void addItem(OrderLineItem item)
    {
        if (shoppingCart.contains(item))
        {
            // I thought this would be funny to do. Realistically I'd do the method below this.
            shoppingCart.get(shoppingCart.indexOf(item)).setQuantity(shoppingCart.get(shoppingCart.indexOf(item)).getQuantity() + item.getQuantity());

            /*
            for (var itemsInCart : shoppingCart)
            {
                if (itemsInCart.equals(item))
                {
                    itemsInCart.setQuantity(item.getQuantity() + itemsInCart.getQuantity());
                }
            }
            */
        }
        else
        {
            shoppingCart.add(item);
        }
    }

    /*
    2. Add logic to allow a user to add an Item to a shopping cart
        - search for a line item by name, and remove it from the list
     */
    public void removeItem(OrderLineItem item)
    {
        shoppingCart.remove(item);
    }

    /*
    3. Search for the highest priced item in the shopping cart and return the
        line item that contains that item.

        If the shopping cart is empty return null
     */
    public OrderLineItem findHighestPriceProduct()
    {
        var highest = shoppingCart.getFirst();

        if (shoppingCart.isEmpty())
        {
            return null;
        }

        for (var item : shoppingCart)
        {
            if (item.getPrice() > highest.getPrice())
            {
                highest = item;
            }
        }
        return highest;
    }

    /*
    4. Search for the most expensive line item in the shopping cart
        and return it

        If the shopping cart is empty return null
     */
    public OrderLineItem findMostExpensiveLineItem()
    {
        var mostExpensive = shoppingCart.getFirst();

        if (shoppingCart.isEmpty())
        {
            return null;
        }

        for (var item : shoppingCart)
        {
            if (item.getLineTotal() > mostExpensive.getLineTotal())
            {
                mostExpensive = item;
            }
        }
        return mostExpensive;
    }

    /*
    5. Calculate and return the order total
     */
    public double getOrderTotal()
    {
        double total = 0;

        for (var item : shoppingCart)
        {
            total += item.getLineTotal();
        }
        return total;
    }

    /*
    6. Return the total number of items in the cart
     */
    public int getTotalItemCount()
    {
        int total = 0;

        for (var item : shoppingCart)
        {
            total += item.getQuantity();
        }
        return total;
    }

    /*
    7. Calculate the average price for all items in the shopping cart
     */
    public double getAveragePricePerItem()
    {
        return getOrderTotal()/getTotalItemCount();
    }
}
