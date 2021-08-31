package com.example.testofcliantandserverjavafx;

public class InterestData {
    private double interest, years,Loan;

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getYears() {
        return years;
    }

    public InterestData(double interest, double years, double loan) {
        this.interest = interest;
        this.years = years;
        Loan = loan;
    }

    public void setYears(double years) {
        this.years = years;
    }

    public double getLoan() {
        return Loan;
    }

    public void setLoan(double loan) {
        Loan = loan;
    }
}
