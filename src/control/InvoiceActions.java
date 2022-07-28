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
import model.FinalInvoice;
import model.InvTableModel;
import model.InvoiceDetails;
import view.mainScreen;
import view.newInvoiceScreen;
import view.newItemScreen;

/**
 *
 * @author Basma
 */
public class InvoiceActions implements ActionListener {

    private mainScreen frame;
    private newInvoiceScreen inv;
    private newItemScreen item;

    public InvoiceActions(mainScreen frame) {
        this.frame = frame;
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

            case "New Line":
                createNewLine();
                break;

            case "Delete Line":
                deleteLine();
                break;

            case "newInvoiceOK":
                newInvoiceDialogOK();
                break;

            case "newInvoiceCancel":
                newInvoiceDialogCancel();
                break;

            case "newLineCancel":
                newLineDialogCancel();
                break;

            case "newLineOK":
                newLineDialogOK();
                break;
        }
    }

    private void newLineDialogOK() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void newLineDialogCancel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void newInvoiceDialogCancel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void newInvoiceDialogOK() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createNewLine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteInvoice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createNewInvoice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
