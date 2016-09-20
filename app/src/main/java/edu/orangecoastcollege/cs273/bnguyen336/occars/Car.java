package edu.orangecoastcollege.cs273.bnguyen336.occars;

/**
 * Nguyen, Benjamin
 * CS273 - Mobile Development
 * C02253862
 */

/**
 * This class contains all logic of calculations
 * for Loan Report
 */
public class Car {
    private double mPrice; //Car's price
    private double mDownPayment;// Car's down payment
    private int mLoanTerm;//Loan term in years

    private final static double TAX_RATE = 0.08;// 8.00% tax rate
    private final static double APR_3_YEARS = 0.0462;// 4.62% 3 years rate
    private final static double APR_4_YEARS = 0.0419;// 4.19% 4 years rate
    private final static double APR_5_YEARS = 0.0416;// 4.16% 5 years rate

    /**
     * Default constructor
     * 3 years loan term button is checked by default
     */
    public Car() {
        mPrice = 0.0;
        mDownPayment = 0.0;
        mLoanTerm = 3;
    }

    /**
     * Return the price of current car
     * @return double
     */
    public double getPrice() {
        return mPrice;
    }

    /**
     * Set the price of the car
     * @param price
     */
    public void setPrice(double price) {
        mPrice = price;
    }

    /**
     * Return the down payment of current car
     * @return double
     */
    public double getDownPayment() {
        return mDownPayment;
    }

    /**
     * Set the down payment of current car
     * @param downPayment
     */
    public void setDownPayment(double downPayment) {
        mDownPayment = downPayment;
    }

    /**
     * Get the loan term of current car
     * @return int
     */
    public int getLoanTerm() {
        return mLoanTerm;
    }

    /**
     * Set the loan term of current car
     * @param loanTerm
     */
    public void setLoanTerm(int loanTerm) {
        mLoanTerm = loanTerm;
    }

    /**
     * Calculate and return total borrowed amount
     * @return double
     */
    public double calculateBorrowedAmount() {
        return calculateTotalCost() - mDownPayment;
    }

    /**
     * Calculate and return interest amount based on loan term (years)
     * @return double
     */
    public double calculateInterestAmount() {
        if (mLoanTerm == 3)
            return calculateBorrowedAmount() * APR_3_YEARS;
        else if (mLoanTerm == 4)
            return calculateBorrowedAmount() * APR_4_YEARS;
        else
            return calculateBorrowedAmount() * APR_5_YEARS;
    }

    /**
     * Calculate and return car's monthly payment
     * @return double
     */
    public double calculateMonthlyPayment() {
        return (calculateBorrowedAmount() + calculateInterestAmount()) / (mLoanTerm * 12);
    }

    /**
     * Calculate and return car purchase's tax amount
     * @return double
     */
    public double calculateTaxAmount() {
        return mPrice * TAX_RATE;
    }

    /**
     * Calculate and return total cost of the purchase
     * @return double
     */
    public double calculateTotalCost() {
        return mPrice + calculateTaxAmount();
    }
}
