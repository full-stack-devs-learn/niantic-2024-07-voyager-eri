package com.niantic.exercises;

import com.niantic.models.LineItem;

import java.util.List;

public class Reducers
{

    /*
    1) using one or more Java Stream functions calculate the total sales amount
       for all line items in the given list.

       hint: use the getLineTotal() method to calculate the sales total

     */
    public double totalSales(List<LineItem> lineItems)
    {
        return lineItems.stream()
                        .map(LineItem::getLineTotal)
                        .reduce(0.0, Double::sum);
    }

    /*
    2) using one or more Java Stream functions calculate the average sales amount
       per line items in the given list.

     */
    public double averageSalesPerLineItem(List<LineItem> lineItems)
    {
        return totalSales(lineItems) / lineItems.size();
    }

    /*
    3) using one or more Java Stream functions calculate the average sales amount
       per items in the given list.

       hint: unlike problem number 2, we are not looking for the average of line totals
       we are looking for the average of each item (line items can have multiple quantities
       of a single item)

     */
    public double averageSalesPerItem(List<LineItem> lineItems)
    {
        var quantityOfItems = lineItems.stream()
                                       .map(LineItem::getQuantity)
                                       .reduce(0, Integer::sum);

        return totalSales(lineItems) / quantityOfItems;
    }

    /*
    4) using one or more Java Stream functions calculate the total number
       of items that were purchased.

       hint: line items can have multiple quantities of an item

     */
    public int totalItemCount(List<LineItem> lineItems)
    {
        return lineItems.stream()
                        .map(LineItem::getQuantity)
                        .reduce(0, Integer::sum);
    }

    /*
    5) using one or more Java Stream functions calculate the average number
       of items that were purchased per line item.

     */
    public double averageItemCount(List<LineItem> lineItems)
    {
        return (double)totalItemCount(lineItems) / lineItems.size();
    }

    /*
    6) using one or more Java Stream functions find the most expensive line item.

     */
    public double maxLineItem(List<LineItem> lineItems)
    {
        var maxLineItem = lineItems.stream()
                                   .map(LineItem::getLineTotal)
                                   .max(Double::compare);

        return (maxLineItem.isPresent()) ? maxLineItem.get() : 0;
    }

    /*
    7) using one or more Java Stream functions find the least expensive line item.

        hint: the least expensive line item is not $0.00

     */
    public double minLineItem(List<LineItem> lineItems)
    {
        var minLineItem = lineItems.stream()
                                   .map(LineItem::getLineTotal)
                                   .min(Double::compare);

        return (minLineItem.isPresent()) ? minLineItem.get() : 0;
    }

}
