/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Basma
 */
public class FinalInvoice {

    private int No;
    private String cust;
    private Date invDate;
    private ArrayList<InvoiceDetails> Data;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public FinalInvoice(int No, String cust, Date invDate) {
        this.No = No;
        this.cust = cust;
        this.invDate = invDate;
       
        
    }
  
    public int getNo() {
        return No;
    }

    public void setNo(int No) {
        this.No = No;
    }

    public String getCust() {
        return cust;
    }

    public void setCust(String cust) {
        this.cust = cust;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public ArrayList<InvoiceDetails> getData() {
          if (Data == null) {
            Data = new ArrayList<>();
        }
        return Data;
    }

    public void setData(ArrayList<InvoiceDetails> Data) {
        this.Data = Data;
    }

    public DateFormat getDf() {
        return df;
    }

    public void setDf(DateFormat df) {
        this.df = df;
    }
    
    public double getInvoiceTotal() {
        double total = 0.0;
        for (int i = 0; i < getData().size(); i++) {
            total +=  getData().get(i).getTotal();
        }
        
        return total;
    }
    @Override
    public String toString() {
        return No + "," + df.format(invDate) + "," + cust;
    }



}
