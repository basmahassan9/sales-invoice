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
import java.io.FileWriter;
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
            case "Save file":
                saveFile();
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
            JOptionPane.showMessageDialog(frame, "Can't convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }

        try {
            price = Double.parseDouble(c1);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Can't convert price", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedItem = frame.getjTable1().getSelectedRow();
        if(selectedItem != -1){
            FinalInvoice inv = frame.getInvoiceArr().get(selectedItem);
            InvoiceDetails item = new InvoiceDetails(name, price, count, inv);
            frame.getLinesArr().add(item);
            DetailsTableModel model = (DetailsTableModel) frame.getjTable2().getModel();
            model.fireTableDataChanged();
            frame.getInvModel().fireTableDataChanged();
         
        }  else{
            JOptionPane.showMessageDialog(frame, "Please select Invoice", "Wrong entry", JOptionPane.ERROR_MESSAGE);
        }
                     
        frame.getjTable1().setRowSelectionInterval(selectedItem, selectedItem);
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
            JOptionPane.showMessageDialog(frame, "Can't parse date", "Invalid date format", JOptionPane.ERROR_MESSAGE);
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
        if (itemIndex !=-1){
            frame.getLinesArr().remove(itemIndex);
            DetailsTableModel model = (DetailsTableModel) frame.getjTable2().getModel();
            model.fireTableDataChanged();
            frame.getjLabel6().setText("" + frame.getInvoiceArr().get(invIndex).getInvoiceTotal());
            frame.getInvModel().fireTableDataChanged();
            frame.getjTable1().setRowSelectionInterval(invIndex, invIndex);
        }
        else{
            JOptionPane.showMessageDialog(frame, "Please select Item", "Wrong entry", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createNewLine() {

        ItemDialog = new newItemScreen(frame);
        ItemDialog.setVisible(true);

    }

    private void deleteInvoice() {
      int invIndex = frame.getjTable1().getSelectedRow();
      if(invIndex != -1){
          frame.getInvoiceArr().remove(invIndex);
          frame.getInvModel().fireTableDataChanged();
          frame.getjTable2().setModel(new DetailsTableModel((null)));
          frame.setLinesArr(null);
           frame.getjLabel2().setText("");
           frame.getjTextField1().setText("");
           frame.getjTextField2().setText("");
           frame.getjLabel6().setText("");
      }
      else{
          JOptionPane.showMessageDialog(frame, "Please select Invoice", "Wrong entry", JOptionPane.ERROR_MESSAGE);
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
            
                Path path = Paths.get(file.getAbsolutePath());



                List<String> lines = Files.readAllLines(path);
                ArrayList<FinalInvoice> invlines = new ArrayList<>();
                // DefaultTableModel model = (DefaultTableModel)frame.getjTable1().getModel();
                for (String x : lines) {
                    String[] arr = x.split(",");
                    String name = arr[2];
                    int invNum = Integer.valueOf(arr[0]);
                    Date invDate = mainScreen.dateFormat.parse(arr[1]);
                    FinalInvoice line = new FinalInvoice(invNum, name, invDate);



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
                    List<FinalInvoice> invoices = frame.getInvoiceArr();
                    for(FinalInvoice x : invoices){
                        System.out.println(x.getNo() );
                        System.out.println("{");
                        System.out.println(x.getInvDate()  + ", " + x.getCust());
                        List<InvoiceDetails> data = x.getData();
                        for(InvoiceDetails y: data){
                            System.out.println(y.getName() + ", " +  y.getPrice() + ", " + y.getCount());
                        }
                        System.out.println("}");
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


      private void saveFile() {
        ArrayList<FinalInvoice> invoicesArray = frame.getInvoiceArr();
        JFileChooser fc = new JFileChooser();
        try {
            int result = fc.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                FileWriter hfw = new FileWriter(headerFile);
                String inv = "";
                String item = "";
                for (FinalInvoice invoice : invoicesArray) {
                    inv += invoice.toString();
                    inv += "\n";
                    for (InvoiceDetails line : invoice.getData()) {
                        item += line.toString();
                        item += "\n";
                    }
                }
                
                inv = inv.substring(0, inv.length()-1);
                item = item.substring(0, item.length()-1);
                result = fc.showSaveDialog(frame);
                File lineFile = fc.getSelectedFile();
                FileWriter lfw = new FileWriter(lineFile);
                hfw.write(inv);
                lfw.write(item);
                hfw.close();
                lfw.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
