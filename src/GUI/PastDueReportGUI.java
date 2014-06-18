/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PastDueReportGUI.java
 *
 * Created on Apr 7, 2011, 5:50:48 PM
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
public class PastDueReportGUI extends javax.swing.JFrame {

    private Comparator sort;
    private Lender lender;
    private NumberFormat nf;
    private DateFormat df;
    private DefaultTableModel pastDueTableModel;

    /** Creates new form PastDueReportGUI */
    public PastDueReportGUI(Lender lender) {
        this.lender = lender;

        df = DateFormat.getDateInstance(DateFormat.SHORT);
        nf = NumberFormat.getCurrencyInstance();
        nf.setMinimumFractionDigits(2);

        initPastDueLoanTableModel();
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

    private void initPastDueLoanTableModel() {
        pastDueTableModel = new javax.swing.table.DefaultTableModel(
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
        pastDueTableModel.setRowCount(0);
        try {
            ArrayList<Loan> pastDueLoanList = lender.getPastDueLoanList();
            Collections.sort(pastDueLoanList, sort);

            boolean addRow;
            double totPrin = 0;
            double totFee = 0;
            double totBal = 0;
            int row = 0;
            for (Loan l : pastDueLoanList) {
                addRow = false;

                if ((fifthLoanCheckBox.isSelected() && LoanType.FIFTH_LOAN.equals(l.getType()))) {
                    addRow = true;
                }

                if ((personalCheckBox.isSelected() && LoanType.PERSONAL.equals(l.getType()))) {
                    addRow = true;
                }

                if (addRow) {
                    pastDueTableModel.addRow(new Object[][]{null, null, null, null, null, null, null});

                    pastDueTableModel.setValueAt(lender.getBorrower(l.getCustNum()).getFullName(), row, 0);
                    pastDueTableModel.setValueAt(nf.format(l.getPrinciple()), row, 1);
                    pastDueTableModel.setValueAt(nf.format(l.getTotalFee()), row, 2);
                    pastDueTableModel.setValueAt(nf.format(l.getBalance()), row, 3);
                    pastDueTableModel.setValueAt(df.format(l.getOrigDate()), row, 4);
                    pastDueTableModel.setValueAt(df.format(l.getDueDate()), row, 5);
                    pastDueTableModel.setValueAt(l.getType(), row, 6);

                    totPrin += l.getPrinciple();
                    totFee += l.getTotalFee();
                    totBal += l.getBalance();

                    row++;
                }
            }

            pastDueTableModel.addRow(new Object[][]{null, null, null, null, null, null, null});
            pastDueTableModel.addRow(new Object[][]{null, null, null, null, null, null, null});

            pastDueTableModel.setValueAt("Total", row + 1, 0);
            pastDueTableModel.setValueAt(nf.format(totPrin), row + 1, 1);
            pastDueTableModel.setValueAt(nf.format(totFee), row + 1, 2);
            pastDueTableModel.setValueAt(nf.format(totBal), row + 1, 3);
            pastDueTableModel.setValueAt("", row + 1, 4);
            pastDueTableModel.setValueAt("", row + 1, 5);
            pastDueTableModel.setValueAt("", row + 1, 6);

        } catch (SQLException sqle) {
            String msg = "SQL Error: Error Accessing Database.";
            printErrorMsg(sqle, msg);
        } catch (ClassNotFoundException cnfe) {
            String msg = "Class Not Found: Error Accessing Database.";
            printErrorMsg(cnfe, msg);
        }
    }

    private void printErrorMsg(Exception e, String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Error",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sortByGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        pastDueLoanTable = new javax.swing.JTable();
        printButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        dueDateRadioButton = new javax.swing.JRadioButton();
        origDateRadioButton = new javax.swing.JRadioButton();
        nameRadioButton = new javax.swing.JRadioButton();
        fifthLoanCheckBox = new javax.swing.JCheckBox();
        personalCheckBox = new javax.swing.JCheckBox();
        includeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pastDueLoanTable.setModel(pastDueTableModel);
        pastDueLoanTable.setColumnSelectionAllowed(false);
        pastDueLoanTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pastDueLoanTable.getTableHeader().setReorderingAllowed(false);
        pastDueLoanTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(pastDueLoanTable);

        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Sort By:");

        sortByGroup.add(dueDateRadioButton);
        dueDateRadioButton.setSelected(true);
        dueDateRadioButton.setText("Due Date");
        dueDateRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dueDateRadioButtonActionPerformed(evt);
            }
        });

        sortByGroup.add(origDateRadioButton);
        origDateRadioButton.setText("Orig Date");
        origDateRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                origDateRadioButtonActionPerformed(evt);
            }
        });

        sortByGroup.add(nameRadioButton);
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
        personalCheckBox.setText("PERSONALS");
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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
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
                            .addComponent(personalCheckBox))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(printButton)
                    .addComponent(jLabel1)
                    .addComponent(dueDateRadioButton)
                    .addComponent(origDateRadioButton)
                    .addComponent(nameRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fifthLoanCheckBox)
                    .addComponent(personalCheckBox)
                    .addComponent(includeLabel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        try {
            pastDueLoanTable.print();
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton dueDateRadioButton;
    private javax.swing.JCheckBox fifthLoanCheckBox;
    private javax.swing.JLabel includeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton nameRadioButton;
    private javax.swing.JRadioButton origDateRadioButton;
    private javax.swing.JTable pastDueLoanTable;
    private javax.swing.JCheckBox personalCheckBox;
    private javax.swing.JButton printButton;
    private javax.swing.ButtonGroup sortByGroup;
    // End of variables declaration//GEN-END:variables
}
