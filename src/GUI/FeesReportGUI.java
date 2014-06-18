/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OpenLoanReportGUI.java
 *
 * Created on Apr 6, 2011, 5:37:57 PM
 */
package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import loans.*;

/**
 *
 * @author bryan
 */
public class FeesReportGUI extends javax.swing.JFrame {

    private Lender lender;
    private Date begDate;
    private Date endDate;
    private DateFormat df;
    private NumberFormat nf;
    private DefaultTableModel FeeTableModel;

    /** Creates new form OpenLoanReportGUI */
    public FeesReportGUI(Lender lender, Date begDate, Date endDate) throws ParseException {
        this.lender = lender;

        df = DateFormat.getDateInstance(DateFormat.MEDIUM);

        String begDateStr = df.format(begDate);
        String endDateStr = df.format(endDate);

        this.begDate = df.parse(begDateStr);
        this.endDate = df.parse(endDateStr);
        
        nf = NumberFormat.getCurrencyInstance();
        nf.setMinimumFractionDigits(2);

        initFeeTableModel();
        initComponents();

        // to find screen resolution:  --> Toolkit.getDefaultToolkit().getScreenSize()
        setSize(800, 650);
        setResizable(false);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocation(screenWidth / 4, screenHeight / 4);

        loadFeeTable();
    }

    private void initFeeTableModel() {
        FeeTableModel = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Date", "Principle Issued", "Fees Collected"
                }) {

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
    }

    private void loadFeeTable() {
        FeeTableModel.setRowCount(0);
        try {
            double totalPrinc = 0.0;
            double totalFees = 0.0;

            Calendar begCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();
            begCal.setTime(begDate);
            endCal.setTime(endDate);
            
            ArrayList<Loan> loanList = lender.getLoansByDateRange(begDate, endDate);
            ArrayList<Payment> pmtList = lender.getPaymentsByDateRange(begDate, endDate);

            FeeTableModel.addRow(new Object[][]{null, null, null});
            
            double principle;
            double fees;
            int row = 1;
            while (!begCal.after(endCal)) {
                principle = 0.0;
                fees = 0.0;

                for (Loan l : loanList) {
                    if (l.getOrigDate().equals(begCal.getTime())) {
                        principle += l.getOrigPrinciple();
                    }
                }

                totalPrinc += principle;

                for (Payment p : pmtList) {
                    if (p.getDate().equals(begCal.getTime())) {
                        fees += p.getFeeAmount() + p.getMiscAmount();
                    }
                }

                totalFees += fees;

                FeeTableModel.addRow(new Object[][]{null, null, null});

                FeeTableModel.setValueAt(df.format(begCal.getTime()), row, 0);
                FeeTableModel.setValueAt(nf.format(principle), row, 1);
                FeeTableModel.setValueAt(nf.format(fees), row, 2);

                row++;
                begCal.add(Calendar.DAY_OF_MONTH, 1);
            }

            FeeTableModel.addRow(new Object[][]{null, null, null});
            FeeTableModel.addRow(new Object[][]{null, null, null});

            FeeTableModel.setValueAt("Total", row+1, 0);
            FeeTableModel.setValueAt(nf.format(totalPrinc), row+1, 1);
            FeeTableModel.setValueAt(nf.format(totalFees), row+1, 2);

        } catch (SQLException sqle) {
            String msg = "SQL Error: Error Accessing Database.";
            printErrorMsg(sqle, msg);
        } catch (ClassNotFoundException cnfe) {
            String msg = "Class Not Found: Error Accessing Database.";
            printErrorMsg(cnfe, msg);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        FeeTable = new javax.swing.JTable();
        printButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Open Loans Report");
        setLocationByPlatform(true);
        setResizable(false);

        FeeTable.setModel(FeeTableModel);
        FeeTable.setColumnSelectionAllowed(false);
        FeeTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        FeeTable.getTableHeader().setReorderingAllowed(false);
        FeeTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(FeeTable);

        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(354, Short.MAX_VALUE)
                .addComponent(printButton)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(printButton))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        try {
            FeeTable.print();
        } catch (PrinterException ex) {
            printErrorMsg(ex, "unable to print");
        }
    }//GEN-LAST:event_printButtonActionPerformed

    private void printErrorMsg(Exception e, String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Error",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable FeeTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton printButton;
    // End of variables declaration//GEN-END:variables
}
