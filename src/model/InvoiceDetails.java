/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Basma
 */
public class InvoiceDetails {
    private String name;
    private double price;
    private int count;
    private FinalInvoice inv;

    public InvoiceDetails(String name, double price, int count, FinalInvoice inv) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.inv = inv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public FinalInvoice getInv() {
        return inv;
    }

    public void setInv(FinalInvoice inv) {
        this.inv = inv;
    }
    
    public double getTotal() {
        return price * count;
    }
    
}
