/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author Basma
 */
public class newInvoiceScreen extends JFrame{

   private JButton b1;
   private JButton b2;
  
   private JTextField f1;
   private JTextField f2;
   
   
   

public newInvoiceScreen(){
    super();
    setLayout(new FlowLayout());
    b1 = new JButton("Ok");
    b2 = new JButton("Cancel");

    f1 = new JTextField(15);
    f2 = new JTextField(15);
    
    add(new JLabel("Invoice date:"));
    add(f1);
    add(new JLabel("Custtomer name:"));
    add(f2);
    
    add(b1);
    add(b2);
    
     setSize(300,200);
     setLocation(200,200);
}
  public static void main(String[] args){
       new newInvoiceScreen().setVisible(true);
   }
   
    
}
