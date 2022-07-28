/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import static jdk.nashorn.internal.runtime.regexp.joni.constants.AsmConstants.S;
import view.mainScreen;

/**
 *
 * @author Basma
 */
public class InvTableModel extends AbstractTableModel {
    private ArrayList<FinalInvoice> invArr;
    private String[] columns = {"Invoice Num", "Invoice Date", "Customer Name", "Invoice Total"};
    public InvTableModel(ArrayList<FinalInvoice> invArr) {
        this.invArr = invArr;
    }

    @Override
    public int getRowCount() {
        return invArr.size();
    }

    @Override
    public int getColumnCount() {
       return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FinalInvoice inv = invArr.get(rowIndex);
        switch(columnIndex){
            case 0:
                return inv.getNo();
            case 1:
                return mainScreen.dateFormat.format(inv.getInvDate());
            case 2:
                return inv.getCust();
            case 3:
                return inv.getInvoiceTotal();
        }
        return "";
    }
     @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
}
