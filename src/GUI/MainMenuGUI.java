/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainMenuGUI.java
 *
 * Created on Mar 9, 2011, 9:09:04 PM
 */
package GUI;

import db.LoanManagerDAOFactory;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import loans.Lender;

/**
 *
 * @author bryan
 */
public class MainMenuGUI extends javax.swing.JFrame {

    private Lender lender;
    private LenderGUI lenderGUI;
    private StationGUI stationGUI;

    /** Creates new form MainMenuGUI */
    public MainMenuGUI() {
        try {
            lender = new Lender();

            if (!lender.getStation(1).isIsOpen()) {
                lender.logInOnStation(1, false);
            } else {
                lender.logInOnStation(1, true);
            }
            
            LoanManagerDAOFactory.commit();

        } catch (ParseException pe) {
        } catch (SQLException sqle) {
            String msg = "SQL Exception: Unable to Access Station Table: "
                    + "Error opening station";
            printErrorMsg(sqle, msg);
            try {
                LoanManagerDAOFactory.rollBack();
            } catch (SQLException sqle2) {
                String msg2 = "Unable to rollback SQL Transaction. "
                        + "Problem with the database: Contact Bryan\n\n"
                        + "Exiting Application";
                printErrorMsg(sqle2, msg2);
                System.exit(1);
            }
        } catch (ClassNotFoundException cnfe) {
            String msg = "Class Not Found Error: Station not opened";
            printErrorMsg(cnfe, msg);
            try {
                LoanManagerDAOFactory.rollBack();
            } catch (SQLException sqle2) {
                String msg2 = "Unable to rollback SQL Transaction. "
                        + "Problem with the database: Contact Bryan\n\n"
                        + "Exiting Application";
                printErrorMsg(sqle2, msg2);
                System.exit(1);
            }
        }

        // set the Nimbus look and feel
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                lenderGUI = new LenderGUI(lender, MainMenuGUI.this);
                lenderGUI.setVisible(false);
            }
        });

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                stationGUI = new StationGUI(lender, MainMenuGUI.this);
                stationGUI.setVisible(false);
            }
        });

        initComponents();
        // to find screen resolution:  --> Toolkit.getDefaultToolkit().getScreenSize()
        setSize(400, 300);
        setResizable(false);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocation(screenWidth / 4, screenHeight / 4);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        paydayLoanButton = new javax.swing.JButton();
        manageStationsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Loan Manager - Main Menu");

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        titleLabel.setText("Loan Manager");

        paydayLoanButton.setText("Manage Loans");
        paydayLoanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paydayLoanButtonActionPerformed(evt);
            }
        });

        manageStationsButton.setText("Manage Stations");
        manageStationsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageStationsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(manageStationsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(paydayLoanButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(titleLabel)))
                .addContainerGap(145, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(titleLabel)
                .addGap(49, 49, 49)
                .addComponent(paydayLoanButton)
                .addGap(18, 18, 18)
                .addComponent(manageStationsButton)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void paydayLoanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paydayLoanButtonActionPerformed
        setVisible(false);
        lenderGUI.setVisible(true);
    }//GEN-LAST:event_paydayLoanButtonActionPerformed

    private void manageStationsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStationsButtonActionPerformed
        setVisible(false);
        stationGUI.setVisible(true);
    }//GEN-LAST:event_manageStationsButtonActionPerformed

    private void printErrorMsg(Exception e, String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Error",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainMenuGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton manageStationsButton;
    private javax.swing.JButton paydayLoanButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}