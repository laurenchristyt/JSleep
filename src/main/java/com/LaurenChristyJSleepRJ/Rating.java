package com.LaurenChristyJSleepRJ;

/**
 * The `Rating` class is used to track the total and count of ratings for the room.
 *
 * @author  Lauren Christy Tanudjaja
 */
public class Rating {
    /**
     *  The total rating from the users.
     */
    private long total;

    /**
     *  The number of how many users make ratings.
     */
    private long count;

    /**
     * This method is used to declare the first rating of a room
     */
    public Rating() {
        this.total = 0;
        this.count = 0;
    }

    /**
     * This method is used to get a total number of how many rating has been added.
     *
     * @return String of count
     */
    public long getCount() { return count;}

    /**
     * This method is used to get a total number of rating.
     *
     * @return String of total
     */
    public long getTotal() { return total;}
    /**
     * This method is used to add a rating from the user
     *
     * @param rating The rate that the user add
     */
    public void insert(int rating) {
        this.total = this.total + rating;
        count++;
    }

    /**
     * This method is used to get the average from total and count
     *
     * @return String of total per count
     */
    public double getAverage() {
        if ( this.count == 0 ){
            return 0;
        } else {
            return this.total / this.count;
        }
    }



    /**
     * This method is used to show the rating of a room.
     *
     * @return String of total and count
     */
    public String toString() {
        return "\nTotal: " + total + "\nCount: " + count;
    }
}
