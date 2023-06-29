package com.pointtech.server.entities;

import com.pointtech.server.entities.enums.CurrencyType;

public class Country {
    String country;
    double salary;
    String reference;
    CurrencyType currency;

    public Country(){

    }

    public Country(String country, double salary, String reference, CurrencyType currency) {
        this.country = country;
        this.salary = salary;
        this.reference = reference;
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(salary);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Country other = (Country) obj;
        if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
            return false;
        return true;
    }

    public double convertToBRL(){
        switch (this.currency) {
            case USD:
                return this.salary * 5.25;
            case EUR:
                return this.salary * 6.20;
            case GBP:
                return this.salary * 7.20;
            case CAD:
                return this.salary * 4.20;
            case BRL:
                return this.salary;
            default:
                return 0;
        }
    }
}
