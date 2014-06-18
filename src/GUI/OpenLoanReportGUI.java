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

import enums.LoanType;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import loans.*;

/**
 *
 * @author bryan
 */
public class OpenLoanReportGUI extends javax.swing.JFrame {

    private Comparator sort;
    private Lender lender;
    private NumberFormat nf;
    private DateFormat df;
    private DefaultTableModel openLoanTableModel;

    /** Creates new form OpenLoanReportGUI */
    public OpenLoanReportGUI(Lender lender) {
        this.lender = lender;

        df = DateFormat.getDateInstance(DateFormat.SHORT);
        nf = NumberFormat.getCurrencyInstance();
        nf.setMinimumFractionDigits(2);

        initOpenLoanTableModel();
        initComponents();

        // to find screen resolution:  --> Toolkit.getDefaultToolkit().getScreenSize()
        setSize(800, 650);
        setResizable(false);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocation(screenWidth / 4, screenHeight / 4);

        sort = new DueDateSort();

        loadOpenLoanTable();
    }

    private void initOpenLoanTableModel() {
        openLoanTableModel = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Name", "Principle", "Fee", "Total", "Orig Date","Due Date", "Type"
                }) {

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
    }

    private void loadOpenLoanTable() {
        openLoanTableModel.setRowCount(0);
        try {
            ArrayList<Loan> openLoanList = lender.getOpenLoanList();

            Collections.sort(openLoanList, sort);

            boolean addRow;
            double totPrin = 0;
            double totFee = 0;
            double totBal = 0;
            int row = 0;
            for (Loan l : openLoanList) {
                addRow = false;

                if ((fifthLoanCheckBox.isSelected() && LoanType.FIFTH_LOAN.equals(l.getType()))) {
                    addRow = true;
                }

                if ((personalCheckBox.isSelected() && LoanType.PERSONAL.equals(l.getType()))) {
                    addRow = true;
                }

                if (addRow) {
                    openLoanTableModel.addRow(new Object[][]{null, null, null, null, null, null, null});

                    openLoanTableModel.setValueAt(lender.getBorrower(l.getCustNum()).getFullName(), row, 0);
                    openLoanTableModel.setValueAt(nf.format(l.getPrinciple()), row, 1);
                    openLoanTableModel.setValueAt(nf.format(l.getTotalFee()), row, 2);
                    openLoanTableModel.setValueAt(nf.format(l.getBalance()), row, 3);
                    openLoanTableModel.setValueAt(df.format(l.getOrigDate()), row, 4);
                    openLoanTableModel.setValueAt(df.format(l.getDueDate()), row, 5);
                    openLoanTableModel.setValueAt(l.getType(), row, 6);

                    totPrin += l.getPrinciple();
                    totFee += l.getTotalFee();
                    totBal += l.getBalance();

                    row++;
                }
            }

            openLoanTableModel.addRow(new Object[][]{null, null, null, null, null, null, null});
            openLoanTableModel.addRow(new Object[][]{null, null, null, null, null, null, null});

            openLoanTableModel.setValueAt("Total", row + 1, 0);
            openLoanTableModel.setValueAt(nf.format(totPrin), row + 1, 1);
            openLoanTableModel.setValueAt(nf.format(totFee), row + 1, 2);
            openLoanTableModel.setValueAt(nf.format(totBal), row + 1, 3);
            openLoanTableModel.setValueAt("", row + 1, 4);
            openLoanTableModel.setValueAt("", row + 1, 5);
            openLoanTableModel.setValueAt("", row + 1, 6);

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

        sortGroupButton = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        openLoanTable = new javax.swing.JTable();
        printButton = new javax.swing.JButton();
        sortByLabel = new javax.swing.JLabel();
        dueDateRadioButton = new javax.swing.JRadioButton();
        origDateRadioButton = new javax.swing.JRadioButton();
        nameRadioButton = new javax.swing.JRadioButton();
        fifthLoanCheckBox = new javax.swing.JCheckBox();
        personalCheckBox = new javax.swing.JCheckBox();
        includeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Open Loans Report");
        setLocationByPlatform(true);
        setResizable(false);

        openLoanTable.setModel(openLoanTableModel);
        openLoanTable.setColumnSelectionAllowed(false);
        openLoanTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        openLoanTable.getTableHeader().setReorderingAllowed(false);
        openLoanTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(openLoanTable);

        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        sortByLabel.setText("Sort By: ");

        sortGroupButton.add(dueDateRadioButton);
        dueDateRadioButton.setSelected(true);
        dueDateRadioButton.setText("Due Date");
        dueDateRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dueDateRadioButtonActionPerformed(evt);
            }
        });

        sortGroupButton.add(origDateRadioButton);
        origDateRadioButton.setText("Orig Date");
        origDateRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                origDateRadioButtonActionPerformed(evt);
            }
        });

        sortGroupButton.add(nameRadioButton);
        nameRadioButton.setText("Name");
        nameRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameRadioButtonActionPerformed(evt);
            }
        });

        fifthLoanCheckBox.setSelected(true);
        fifthLoanCheckBox.setText("FIFTH LOANS");
        fifthLoanCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fifthLoanCheckBoxActionPerformed(evt);
            }
        });

        personalCheckBox.setSelected(true);
        personalCheckBox.setText("PERSONAL");
        personalCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personalCheckBoxActionPerformed(evt);
            }
        });

        includeLabel.setText("Include:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sortByLabel)
                    .addComponent(includeLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dueDateRadioButton)
                    .addComponent(fifthLoanCheckBox))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(origDateRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(nameRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                        .addComponent(printButton))
                    .addComponent(personalCheckBox))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortByLabel)
                    .addComponent(dueDateRadioButton)
                    .addComponent(origDateRadioButton)
                    .addComponent(nameRadioButton)
                    .addComponent(printButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fifthLoanCheckBox)
                    .addComponent(personalCheckBox)
                    .addComponent(includeLabel))
                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        try {
            openLoanTable.print();
        } catch (PrinterException ex) {
            printErrorMsg(ex, "unable to print");
        }
    }//GEN-LAST:event_printButtonActionPerformed

    private void dueDateRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dueDateRadioButtonActionPerformed
        sort = new DueDateSort();
        loadOpenLoanTable();
    }//GEN-LAST:event_dueDateRadioButtonActionPerformed

    private void origDateRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_origDateRadioButtonActionPerformed
        sort = new OrigDateSort();
        loadOpenLoanTable();
    }//GEN-LAST:event_origDateRadioButtonActionPerformed

    private void nameRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameRadioButtonActionPerformed
        sort = new LoanNameSort();
        loadOpenLoanTable();
    }//GEN-LAST:event_nameRadioButtonActionPerformed

    private void fifthLoanCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fifthLoanCheckBoxActionPerformed
        loadOpenLoanTable();
    }//GEN-LAST:event_fifthLoanCheckBoxActionPerformed

    private void personalCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personalCheckBoxActionPerformed
        loadOpenLoanTable();
    }//GEN-LAST:event_personalCheckBoxActionPerformed

    private void printErrorMsg(Exception e, String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Error",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton dueDateRadioButton;
    private javax.swing.JCheckBox fifthLoanCheckBox;
    private javax.swing.JLabel includeLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton nameRadioButton;
    private javax.swing.JTable openLoanTable;
    private javax.swing.JRadioButton origDateRadioButton;
    private javax.swing.JCheckBox personalCheckBox;
    private javax.swing.JButton printButton;
    private javax.swing.JLabel sortByLabel;
    private javax.swing.ButtonGroup sortGroupButton;
    // End of variables declaration//GEN-END:variables
}
