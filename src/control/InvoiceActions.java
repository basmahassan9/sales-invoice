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
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
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
                saveFile();
                break;

            case "Load Files":
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

  
    
      private void openFile(String num){
        String path = "C:\\Users\\Basma\\Documents\\test.csv";
        File file = new File(path);
         try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            
           // String firstLine = br.readLine().trim();
           // String[] columnsName = firstLine.split(",");
            DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
            //model.setColumnIdentifiers(columnsName);
            
            // get lines from txt file
            Object[] tableLines = br.lines().toArray();
            
            // extratct data from lines
            // set data to jtable model
           

        for (int row = 0; row < jTable2.getRowCount(); row++) {
            model.removeRow(row);
        }
        model.setRowCount(0);
            for(int i = 0; i < tableLines.length; i++)
            {
                String line = tableLines[i].toString().trim();
                String[] dataRow = new String[5];
                  String[] dataRow1  = line.split(",");
               
                 for(int j=0;j<4;j++){
                     dataRow[j]=dataRow1[j];
                 }
                 dataRow[4]= String.valueOf(Integer.valueOf(dataRow[2])*Integer.valueOf(dataRow[3]));
               if(num.equals(dataRow[0])){
                  
                model.addRow(dataRow);}
         }
            
            
        } catch (Exception ex) {
           // Logger.getLogger(TextFileDataToJTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 


    
     private void loadFile() {
       /* JFileChooser fc = new JFileChooser();
       int result=  fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
          String path =  fc.getSelectedFile().getPath();
           File fis = null;
          try{
             fis = new File(path);
 
            BufferedReader br;
              br = new BufferedReader(new FileReader(fis));
  
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
           
             Object[] tableLines = br.lines().toArray();
             for(int i = 0; i < tableLines.length; i++)
            {
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split(",");
                model.addRow(dataRow);
            }
              
          }catch(FileNotFoundException e){
              e.printStackTrace();
          } catch(IOException e){
                            e.printStackTrace();

          }
          
        }*/
    }
     private void saveFile(){
        /*JFileChooser fc = new JFileChooser();
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
        }*/
    }
}
