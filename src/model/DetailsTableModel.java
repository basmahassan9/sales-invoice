/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Basma
 */
public class DetailsTableModel extends AbstractTableModel {
    private ArrayList<InvoiceDetails> lineArr;
    private String[] columns = {"Item Name", "Unit Price", "Count", "Line Total"};
    public DetailsTableModel(ArrayList<InvoiceDetails> lineArr) {
        this.lineArr = lineArr;
    }

    @Override
    public int getRowCount() {
       return lineArr == null ? 0 : lineArr.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      if(lineArr == null){
          return "";
      }
      else{
          InvoiceDetails line = lineArr.get(rowIndex);
          switch(columnIndex){
              case 0:
                  return line.getName();
              case 1:
                 return line.getPrice();
              case 2:
                  return line.getCount();
              case 3:
                  return line.getTotal();
            
              default:
                  return "";
              
          }
      }
    }
      @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
