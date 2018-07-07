/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.coder;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.TextAction;

/**
 *
 * @author DELL
 */
public class TextCoder {
    String prevText="";

    public TextCoder() {
        String[] option = {"Select", "Encode", "Decode"};
        JFrame f = new JFrame();
        JLabel l1 = new JLabel("Input: ");
     
        JLabel l2 = new JLabel("Output: ");
        JComboBox choiser = new JComboBox(option);
        JButton btnCopy = new JButton("Copy Text");

        JTextField tfInputeText = new JTextField();
        JTextField tfOutpur = new JTextField();

        choiser.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                String selected = e.getItem().toString();

                if (selected.equals("Encode")) {
                    
                    
                   String text = tfInputeText.getText();
                            tfOutpur.setText(encoder(text));
                    tfInputeText.getDocument().addDocumentListener(new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            String text = tfInputeText.getText();
                            tfOutpur.setText(encoder(text));
                            
                            prevText=tfInputeText.getText();
                            System.out.println(prevText);

                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            String text = tfInputeText.getText();
                            tfOutpur.setText(encoder(text));
                            
                            
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            String text = tfInputeText.getText();
                            tfOutpur.setText(encoder(text));
                            
                            if (tfInputeText.getText().toString().equals(prevText)) {
                                
                                choiser.setSelectedItem("Decode");
                            }
                        }
                    });
                    
                    
                } 
                if (selected.equals("Decode")) {
                   String text = tfInputeText.getText();
                            tfOutpur.setText(decoder(text));
                    tfInputeText.getDocument().addDocumentListener(new DocumentListener() {
                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            String text = tfInputeText.getText();
                            tfOutpur.setText(decoder(text));

                        }

                        @Override
                        public void removeUpdate(DocumentEvent e) {
                            String text = tfInputeText.getText();
                            tfOutpur.setText(decoder(text));
                        }

                        @Override
                        public void changedUpdate(DocumentEvent e) {
                            String text = tfInputeText.getText();
                            tfOutpur.setText(decoder(text));
                        }
                    });
                }

            }
        });

        try {
            f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("back.png")))));
        } catch (IOException ex) {
            Logger.getLogger(TextCoder.class.getName()).log(Level.SEVERE, null, ex);
        }

        f.setSize(700, 600);
        
        f.setResizable(false);
        l1.setBounds(50, 100, 100, 30);
        tfInputeText.setBounds(175, 100, 300, 100);
        choiser.setBounds(480, 100, 100, 30);

        l2.setBounds(50, 225, 100, 30);
        tfOutpur.setBounds(175, 225, 300, 100);
        btnCopy.setBounds(480, 225, 100, 30);

        f.add(l1);
        f.add(l2);
        f.add(choiser);
        f.add(btnCopy);
        f.add(tfInputeText);

        f.add(tfOutpur);
        f.setLayout(null);
        f.setVisible(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TextCoder coder = new TextCoder();
        // TODO code application logic here
    }

    public static String encoder(String text) {
        String encoded = "";
        try {
            encoded = Base64.getEncoder().encodeToString(text.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encoded;
    }

    public static String decoder(String text) {

        String decoded = "";
        byte[] decodedArray = Base64.getDecoder().decode(text);
        try {
            decoded = new String(decodedArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return decoded;
    }

}
