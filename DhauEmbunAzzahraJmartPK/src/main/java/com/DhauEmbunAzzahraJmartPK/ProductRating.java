package com.DhauEmbunAzzahraJmartPK;

/**
 * This is class for representing a product rating
 *
 * @author Dhau' Embun Azzahra
 * */
public class ProductRating {
    private long total;
    private long count;
    public ProductRating(){
        this.total = 0;
        this.count = 0;
    }

    /**
     * Method to add rating of the product
     * @param rating The rating
     */
    public void insert(int rating){
        total = total + rating;
        count++;
    }

    /**
     * Method to count the average rating of the product.
     * @return rating number of the product.
     */
    public double getAverage(){
        if (count!=0){
            return total/count;
        }
        else{
            return 0.0;
        }
    }

    /**
     * Method to get how much time the product rating were added
     * @return number of count.
     */
    public long getCount(){
        return count;
    }

    /**
     * Method to get the total sum of product rating
     * @return the total sum of product rating
     */
    public long getTotal(){
        return total;
    }
}