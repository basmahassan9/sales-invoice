/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.DetailsTableModel;
import model.FinalInvoice;
import model.InvoiceDetails;
import view.mainScreen;

/**
 *
 * @author Basma
 */
public class ListActions implements ListSelectionListener{
    
    private mainScreen frame;
     private ArrayList<InvoiceDetails> linesArray;
    public ListActions(mainScreen frame) {
        this.frame = frame;
    }
   
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
       int invIndex = frame.getjTable1().getSelectedRow();
       //DefaultTableModel model = (DefaultTableModel)frame.getjTable2().getModel();
       
       if(invIndex != -1){
           FinalInvoice inv = frame.getInvoiceArr().get(invIndex);
           ArrayList<InvoiceDetails> lines = inv.getData();
//           String[] arr=null;
//           InvoiceDetails invData = linesArray.get(inv.getNo());
//           arr[0]= String.valueOf(inv.getNo());
//           arr[1] = invData.getName();
//           arr[2] = String.valueOf(invData.getPrice());
//           arr[3] = String.valueOf(invData.getCount());
//           arr[4] = String.valueOf(invData.getTotal());
//           model.addRow(arr);

           DetailsTableModel lineModel = new DetailsTableModel(lines);
           frame.setLinesArr(lines);
           frame.getjTable2().setModel(lineModel);
           frame.getjLabel2().setText(""+ inv.getNo());
           frame.getjTextField1().setText(mainScreen.dateFormat.format(inv.getInvDate()));
           frame.getjTextField2().setText(inv.getCust());
           frame.getjLabel6().setText(""+ inv.getInvoiceTotal());
           
       }
    }
    
}
