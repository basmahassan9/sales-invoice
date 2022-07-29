/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DetailsTableModel;
import model.FinalInvoice;
import model.InvTableModel;
import model.InvoiceDetails;
import view.newInvScreen;
import view.mainScreen;
import view.newInvScreen;

import view.newItemScreen;
import view.newItemScreen;

/**
 *
 * @author Basma
 */
public class InvoiceActions implements ActionListener {

    private mainScreen frame;
    private newInvScreen InvDialog;
    private newItemScreen ItemDialog ;

    public InvoiceActions(mainScreen frame) {
        this.frame = frame;
    }

    public InvoiceActions() {
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Save Files":
                // saveFile();
                break;

            case "Load file":
                loadFile();
                break;

            case "New Invoice":
                createNewInvoice();
                break;

            case "Delete Invoice":
                deleteInvoice();
                break;

            case "Create new item":
                createNewLine();
                break;

            case "Delete Item":
                deleteItem();
                break;

            case "newInvOK":
                newInvOK();
                break;

            case "newInvCancel":
                newInvCancel();
                break;

            case "newItemCancel":
                newItemCancel();
                break;

            case "newItemOk":
                newItemOK();
                break;
        }
    }

    private void newItemOK() {
        ItemDialog.setVisible(false);
        String name = ItemDialog.getjTextField1().getText(); 
        String p1 = ItemDialog.getjTextField2().getText();
        String c1 = ItemDialog.getjTextField3().getText();
        int count = 1;
        double price =1;
          try {
            count = Integer.parseInt(p1);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }

        try {
            price = Double.parseDouble(c1);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot convert price", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedItem = frame.getjTable1().getSelectedRow();
        if(selectedItem != -1){
            FinalInvoice inv = frame.getInvoiceArr().get(selectedItem);
            InvoiceDetails item = new InvoiceDetails(name, price, count, inv);
            frame.getLinesArr().add(item);
            DetailsTableModel model = (DetailsTableModel) frame.getjTable2().getModel();
            model.fireTableDataChanged();
            frame.getInvModel().fireTableDataChanged();
            System.out.println("done2");
        }
        System.out.println("done");
        //frame.getjTable1().setRowSelectionInterval(selectedItem, selectedItem);
        ItemDialog.dispose();
    }

    private void newItemCancel() {
        ItemDialog.setVisible(false);
        ItemDialog.dispose();
    }

    private void newInvCancel() {
        InvDialog.setVisible(false);
        InvDialog.dispose();
    }

    private void newInvOK() {
        InvDialog.setVisible(false);
        String name = InvDialog.getjTextField2().getText();
        String s = InvDialog.getjTextField1().getText();
        Date d = new Date();
        try {
            d = mainScreen.dateFormat.parse(s);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot parse date, resetting to today.", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }
        int num =0;
        for (FinalInvoice inv : frame.getInvoiceArr()){
            if(inv.getNo()> num){
                num = inv.getNo();
            }
        }
        num+=1;
        FinalInvoice newInv = new FinalInvoice(num, name, d);
        frame.getInvoiceArr().add(newInv);
        frame.getInvModel().fireTableDataChanged();
        InvDialog.dispose();
    }

    private void deleteItem() {
        int itemIndex = frame.getjTable2().getSelectedRow();
        int invIndex =frame.getjTable1().getSelectedRow();
        if (itemIndex >=0){
            frame.getLinesArr().remove(itemIndex);
            DetailsTableModel model = (DetailsTableModel) frame.getjTable2().getModel();
            model.fireTableDataChanged();
            frame.getjLabel6().setText("" + frame.getInvoiceArr().get(invIndex).getInvoiceTotal());
            frame.getInvModel().fireTableDataChanged();
            frame.getjTable1().setRowSelectionInterval(invIndex, invIndex);
        }
    }

    private void createNewLine() {

        ItemDialog = new newItemScreen(frame);
        ItemDialog.setVisible(true);

    }

    private void deleteInvoice() {
      int invIndex = frame.getjTable1().getSelectedRow();
      if(invIndex >=0){
          frame.getInvoiceArr().remove(invIndex);
          frame.getInvModel().fireTableDataChanged();
          frame.getjTable2().setModel(new DetailsTableModel((null)));
          frame.setLinesArr(null);
           frame.getjLabel2().setText("");
           frame.getjTextField1().setText("");
           frame.getjTextField2().setText("");
           frame.getjLabel6().setText("");
      }
    }

    private void createNewInvoice() {
        InvDialog = new newInvScreen(frame);
        InvDialog.setVisible(true);

    }

    private void loadFile() {
        JFileChooser fc = new JFileChooser();
        try {
            int result = fc.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                // fc.setMultiSelectionEnabled(true); 
                Path path = Paths.get(file.getAbsolutePath());

                BufferedReader br;
                br = new BufferedReader(new FileReader(file));

                List<String> lines = Files.readAllLines(path);
                ArrayList<FinalInvoice> invlines = new ArrayList<>();
                // DefaultTableModel model = (DefaultTableModel)frame.getjTable1().getModel();
                for (String x : lines) {
                    String[] arr = x.split(",");
                    String name = arr[2];
                    int invNum = Integer.valueOf(arr[0]);
                    Date invDate = mainScreen.dateFormat.parse(arr[1]);
                    FinalInvoice line = new FinalInvoice(invNum, name, invDate);
                    double total = line.getInvoiceTotal();
                    arr[3] = String.valueOf(total);
                    invlines.add(line);
                    // model.addRow(arr);
                }
                frame.setInvoiceArr(invlines);

                result = fc.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file2 = fc.getSelectedFile();
                    Path path2 = Paths.get(file2.getAbsolutePath());
                    List<String> invDetails = Files.readAllLines(path2);

                    for (String y : invDetails) {
                        String[] arr = y.split(",");
                        int invNum = Integer.valueOf(arr[0]);
                        double price = Double.valueOf(arr[2]);
                        int count = Integer.valueOf(arr[3]);
                        String name = arr[1];
                        FinalInvoice inv = frame.getinvObject(invNum);
                        InvoiceDetails line = new InvoiceDetails(name, price, count, inv);
                        inv.getData().add(line);

                    }
                }
                InvTableModel invModel = new InvTableModel(invlines);
                frame.setInvModel(invModel);
                frame.getjTable1().setModel(invModel);

            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}

/*  private void saveFile() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            String path = fc.getSelectedFile().getPath();
            FileOutputStream fos = null;
            try{
            fos = new FileOutputStream(path);
            String comma = ",";
             for(int i = 0; i < jTable1.getRowCount(); i++){//rows
                for(int j = 0; j < jTable1.getColumnCount(); j++){
         
                 
                   
              fos.write( jTable1.getValueAt(i, j).toString().getBytes());
              fos.write(comma.getBytes()) ; 
                }
             fos.write(10);
             }
            }catch(FileNotFoundException e){
              e.printStackTrace();
          } catch(IOException e){
                            e.printStackTrace();

          }finally{
                try {
                    fos.close();
                } catch (IOException ex) {
                    
                }
            }
        }
    }*/
//}
