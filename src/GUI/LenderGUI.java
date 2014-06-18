/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LenderGUI.java
 *
 * Created on Aug 24, 2010, 9:51:12 PM
 */
package GUI;

import enums.LoanType;
import enums.PayFrequency;
import db.LoanManagerDAOFactory;
import enums.LoanStatus;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import loans.*;
import org.freixas.jcalendar.*;

/**
 *
 * @author bryan
 */
public class LenderGUI extends JFrame {

    private Lender lender;
    private ArrayList<Borrower> bList;
    private ArrayList<Loan> loanList;
    private ArrayList<Payment> paymentList;
    private DateFormat df;
    private NumberFormat nf;
    private DefaultTableModel searchResultTableModel;
    private DefaultTableModel loanListTableModel;
    private DefaultTableModel paymentListTableModel;
    private JFrame mainMenuGUI;
    private JTable openLoansListTable;
    private JTable paymentListTable;
    private JScrollPane jScrollPane3;

    /** 
     * Default Constructor
     * 
     * @param lender
     */
    public LenderGUI(Lender lender, JFrame mainMenuGUI) {
        df = DateFormat.getDateInstance(DateFormat.SHORT);
        nf = NumberFormat.getCurrencyInstance();
        nf.setMinimumFractionDigits(2);

        jScrollPane3 = new javax.swing.JScrollPane();
        paymentListTable = new JTable();

        this.lender = lender;
        this.mainMenuGUI = mainMenuGUI;

        initSearchResultTableModel();
        initLoanListTableModel();
        initPaymentListTableModel();

        initComponents();

        searchResultTable.getColumnModel().getColumn(0).setPreferredWidth(225);
        loanListTable.getColumnModel().getColumn(0).setPreferredWidth(90);
        loanListTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        loanListTable.getColumnModel().getColumn(4).setPreferredWidth(80);

        displayGOP();

        // to find screen resolution:  --> Toolkit.getDefaultToolkit().getScreenSize()
        //setSize(800, 650);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(((int) (screenWidth * .75f)), ((int) (screenHeight * .90f)));
        //setLocation(screenWidth / 6, screenHeight / 6);
    }

    private void displayGOP() {
        try {
            double gop = lender.loadGOP();

            gopValLabel.setText(nf.format(gop));
        } catch (SQLException sqle) {
            String msg = "SQL Error: Error Accessing database. GOP Not Available";
            printErrorMsg(sqle, msg);
        } catch (ClassNotFoundException cnfe) {
            String msg = "Class Not Found Error: GOP Not Available";
            printErrorMsg(cnfe, msg);
        }
    }

    private void initSearchResultTableModel() {
        searchResultTableModel = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Name", "", "Cust. No."
                }) {

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.Long.class
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

    private void initLoanListTableModel() {
        loanListTableModel = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Principle", "Fee", "Orig Date","Due Date", "Status", "Type", "Loan No."
                }) {

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class,
                java.lang.Long.class, java.lang.String.class,
                java.lang.Long.class, java.lang.Long.class, java.lang.String.class
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

    private void initPaymentListTableModel() {
        paymentListTableModel = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Date", "Amount", "Payment No."
                }) {

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class,
                java.lang.String.class
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

        paymentListTable.setModel(paymentListTableModel);
        paymentListTable.setColumnSelectionAllowed(false);
        paymentListTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        paymentListTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(paymentListTable);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newCustomerDialog = new javax.swing.JDialog();
        nameLabel = new javax.swing.JLabel();
        searchLastNameField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        balanceLabel = new javax.swing.JLabel();
        principleLabel = new javax.swing.JLabel();
        feeLabel = new javax.swing.JLabel();
        dueDateLabel = new javax.swing.JLabel();
        dueDateValLabel = new javax.swing.JLabel();
        makePaymentButton = new javax.swing.JButton();
        principleValLabel = new javax.swing.JLabel();
        balanceValLabel = new javax.swing.JLabel();
        feeValLabel = new javax.swing.JLabel();
        searchLabel = new javax.swing.JLabel();
        newBorrowerButton = new javax.swing.JButton();
        newLoanButton = new javax.swing.JButton();
        selectButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        searchResultTable = new javax.swing.JTable();
        curDateLabel = new javax.swing.JLabel();
        curDateTxtLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        loanListTable = new javax.swing.JTable();
        custNumTextField = new javax.swing.JTextField();
        custNumLabel = new javax.swing.JLabel();
        changeDueDateButton = new javax.swing.JButton();
        paymentHistoryButton = new javax.swing.JButton();
        netInLabel = new javax.swing.JLabel();
        netInValLabel = new javax.swing.JLabel();
        gopLabel = new javax.swing.JLabel();
        gopValLabel = new javax.swing.JLabel();
        changePrincipleButton = new javax.swing.JButton();
        changeFeeButton = new javax.swing.JButton();
        typeLabel = new javax.swing.JLabel();
        openLoansButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        pastDueReportButton = new javax.swing.JButton();
        feesReportButton = new javax.swing.JButton();
        gopHistoryButton = new javax.swing.JButton();
        editCustomerButton = new javax.swing.JButton();
        setStatusButton = new javax.swing.JButton();
        markReturnedButton = new javax.swing.JButton();
        depositCheckButton = new javax.swing.JButton();
        mainMenuButton = new javax.swing.JButton();
        reUpButton = new javax.swing.JButton();

        javax.swing.GroupLayout newCustomerDialogLayout = new javax.swing.GroupLayout(newCustomerDialog.getContentPane());
        newCustomerDialog.getContentPane().setLayout(newCustomerDialogLayout);
        newCustomerDialogLayout.setHorizontalGroup(
            newCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        newCustomerDialogLayout.setVerticalGroup(
            newCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Loan Manager - Manage Loans");
        setForeground(java.awt.Color.white);

        nameLabel.setForeground(Color.BLUE);
        nameLabel.setText(" ");

        searchLastNameField.setColumns(10);
        searchLastNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchLastNameFieldActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.setSelected(true);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        balanceLabel.setText("Balance");

        principleLabel.setText("Principle");

        feeLabel.setText("Fee");

        dueDateLabel.setText("Due Date");

        makePaymentButton.setText("Make Payment");
        makePaymentButton.setEnabled(false);
        makePaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makePaymentButtonActionPerformed(evt);
            }
        });

        searchLabel.setText("Last Name");

        newBorrowerButton.setText("New Customer");
        newBorrowerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBorrowerButtonActionPerformed(evt);
            }
        });

        newLoanButton.setText("New Loan");
        newLoanButton.setEnabled(false);
        newLoanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newLoanButtonActionPerformed(evt);
            }
        });

        selectButton.setText("Select");
        selectButton.setEnabled(false);
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectButtonActionPerformed(evt);
            }
        });

        searchResultTable.setModel(searchResultTableModel);
        searchResultTable.setColumnSelectionAllowed(false);
        searchResultTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        searchResultTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(searchResultTable);
        searchResultTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        curDateLabel.setText(df.format(lender.getCurDate()));

        curDateTxtLabel.setText("Today's Date: ");

        loanListTable.setModel(loanListTableModel);
        loanListTable.setColumnSelectionAllowed(false);
        loanListTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        loanListTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(loanListTable);
        loanListTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        loanListTable.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                loanListTableValueChanged(evt);
            }
        });

        custNumTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custNumTextFieldActionPerformed(evt);
            }
        });

        custNumLabel.setText("Cust. No.");

        changeDueDateButton.setText("...");
        changeDueDateButton.setEnabled(false);
        changeDueDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeDueDateButtonActionPerformed(evt);
            }
        });

        paymentHistoryButton.setText("Payment History");
        paymentHistoryButton.setEnabled(false);
        paymentHistoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentHistoryButtonActionPerformed(evt);
            }
        });
        paymentDeleteButton = new JButton();
        paymentDeleteButton.setText("Delete");
        paymentDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentDeleteButtonActionPerformed(evt);
            }
        });

        netInLabel.setText("Net In:");

        gopLabel.setText("GOP: ");

        changePrincipleButton.setText("...");
        changePrincipleButton.setEnabled(false);
        changePrincipleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePrincipleButtonActionPerformed(evt);
            }
        });

        changeFeeButton.setText("...");
        changeFeeButton.setEnabled(false);
        changeFeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeFeeButtonActionPerformed(evt);
            }
        });

        typeLabel.setForeground(Color.RED);

        openLoansButton.setText("Open Loans");
        openLoansButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openLoansButtonActionPerformed(evt);
            }
        });
        printButton = new JButton();
        printButton.setText("Delete");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        pastDueReportButton.setText("Past Due Loans");
        pastDueReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pastDueReportButtonActionPerformed(evt);
            }
        });

        feesReportButton.setText("Fees Report");
        feesReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feesReportButtonActionPerformed(evt);
            }
        });

        gopHistoryButton.setText("GOP History");
        gopHistoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gopHistoryButtonActionPerformed(evt);
            }
        });

        editCustomerButton.setText("Edit Customer");
        editCustomerButton.setEnabled(false);
        editCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCustomerButtonActionPerformed(evt);
            }
        });

        setStatusButton.setText("Set Status");
        setStatusButton.setEnabled(false);
        setStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setStatusButtonActionPerformed(evt);
            }
        });

        markReturnedButton.setText("Mark Returned");
        markReturnedButton.setEnabled(false);
        markReturnedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markReturnedButtonActionPerformed(evt);
            }
        });

        depositCheckButton.setText("Deposit Check");
        depositCheckButton.setEnabled(false);
        depositCheckButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositCheckButtonActionPerformed(evt);
            }
        });

        mainMenuButton.setText("Main Menu");
        mainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuButtonActionPerformed(evt);
            }
        });

        reUpButton.setText("Re-up");
        reUpButton.setEnabled(false);
        reUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reUpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(balanceLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(280, 280, 280)
                                        .addComponent(curDateTxtLabel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(gopLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(gopValLabel)))
                                .addGap(18, 18, 18)
                                .addComponent(curDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(netInLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(netInValLabel))
                                    .addComponent(nameLabel)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(custNumLabel)
                                                    .addComponent(searchLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(custNumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(searchLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(principleLabel)
                                                    .addComponent(feeLabel)
                                                    .addComponent(dueDateLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(dueDateValLabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(changeDueDateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(feeValLabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(changeFeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(principleValLabel)
                                                        .addGap(21, 21, 21)
                                                        .addComponent(changePrincipleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(balanceValLabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 344, Short.MAX_VALUE)
                                                    .addComponent(typeLabel)
                                                    .addGap(65, 65, 65))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(18, 18, 18)
                                                    .addComponent(searchButton)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(reUpButton)
                                                .addGap(207, 207, 207)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(setStatusButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(paymentHistoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(markReturnedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(newLoanButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(newBorrowerButton, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(editCustomerButton))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(feesReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(openLoansButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(pastDueReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(gopHistoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(mainMenuButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(depositCheckButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(makePaymentButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(selectButton)
                            .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(293, 293, 293))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(curDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(curDateTxtLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gopLabel)
                            .addComponent(gopValLabel))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchLabel)
                            .addComponent(searchLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(custNumLabel)
                            .addComponent(custNumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(typeLabel)
                        .addGap(5, 5, 5)
                        .addComponent(nameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(paymentHistoryButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(setStatusButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(changePrincipleButton)
                                        .addComponent(reUpButton))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(principleValLabel)
                                        .addComponent(principleLabel)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(changeFeeButton)
                                    .addComponent(feeLabel)
                                    .addComponent(feeValLabel)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(selectButton, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(newBorrowerButton)
                                    .addComponent(openLoansButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(newLoanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pastDueReportButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(editCustomerButton)
                                    .addComponent(feesReportButton))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gopHistoryButton)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(balanceLabel)
                            .addComponent(balanceValLabel))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dueDateLabel)
                            .addComponent(changeDueDateButton)
                            .addComponent(dueDateValLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(netInLabel)
                            .addComponent(netInValLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(makePaymentButton)
                            .addComponent(exitButton)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(markReturnedButton)
                        .addComponent(mainMenuButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(depositCheckButton)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchLastNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchLastNameFieldActionPerformed

        String lastName = searchLastNameField.getText();

        if (!lastName.isEmpty()) {
            try {

                // clear any existing borrower info first
                unloadBorrower();
                bList = lender.getBorrowerList(lastName);

                loadBorrowerList();
                searchLastNameField.setText("");
            } catch (SQLException sqle) {
                String msg = "SQL Error: Error Accessing database. Coulb Not "
                        + "Access Borrower Table";
                printErrorMsg(sqle, msg);
            } catch (ClassNotFoundException cnfe) {
                String msg = "Class Not Found Error: Error Searching Customers";
                printErrorMsg(cnfe, msg);
            }
        }// end if
    }//GEN-LAST:event_searchLastNameFieldActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        searchLastNameFieldActionPerformed(evt);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void newBorrowerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBorrowerButtonActionPerformed
        JTextField fNameTxtField = new JTextField();
        JTextField mNameTxtField = new JTextField();
        JTextField lNameTxtField = new JTextField();

        JComboBox freqComboBox = new JComboBox(PayFrequency.values());
        freqComboBox.setSelectedItem(PayFrequency.BI_WEEKLY);

        // goes into the JOptionPane
        javax.swing.JComponent[] inputs = new javax.swing.JComponent[]{
            new JLabel("FIRST: "), fNameTxtField,
            new JLabel("MIDDLE: "), mNameTxtField,
            new JLabel("LAST: "), lNameTxtField,
            new JLabel("PAY FREQUENCY"), freqComboBox,};

        int result = JOptionPane.showOptionDialog(rootPane, inputs,
                "New Customer", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result != JOptionPane.CLOSED_OPTION
                && result != JOptionPane.CANCEL_OPTION) {
            String fName = fNameTxtField.getText();
            String mName = mNameTxtField.getText();
            String lName = lNameTxtField.getText();
            PayFrequency payFreq = (PayFrequency) freqComboBox.getSelectedItem();

            if (!fName.isEmpty() && !lName.isEmpty()) {
                try {
                    unloadBorrower();
                    lender.newBorrower(fName, mName, lName, payFreq);
                    LoanManagerDAOFactory.commit();

                    loadBorrower();

                    // clear the tables
                    searchResultTableModel.setRowCount(0);
                    loanListTableModel.setRowCount(0);

                } catch (SQLException sqle) {
                    String msg = "SQL Exception: Coult Not Access Borrower Table"
                            + "/nError creating Customer";
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
                    String msg = "Class Not Found: Error creating Customer";
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

            } else {

                JOptionPane.showMessageDialog(rootPane, "Enter First AND "
                        + "Last Name. Customer Not Created", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_newBorrowerButtonActionPerformed

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonActionPerformed
        int selectedRow = 0;

        if ((selectedRow = searchResultTable.getSelectedRow()) != -1) {
            long selectedBorrowerNum = (Long) searchResultTable.getValueAt(selectedRow,
                    2);

            // clear the table
            searchResultTableModel.setRowCount(0);
            try {
                Borrower curCust = lender.getBorrower(selectedBorrowerNum);
                lender.setCurBorrower(curCust);

                if (curCust != null) {
                    loadBorrower();
                }

            } catch (SQLException sqle) {
                String msg = "SQL Exception: Error Accessing Database";
                printErrorMsg(sqle, msg);
            } catch (ClassNotFoundException cnfe) {
                String msg = "Class Not Found: Error selecting Customer";
                printErrorMsg(cnfe, msg);
            }
        }
    }//GEN-LAST:event_selectButtonActionPerformed

    private void newLoanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newLoanButtonActionPerformed
        JCalendar origDateCal = new JCalendar();
        JCalendar dueDateCal = new JCalendar();
        JTextField loanTxtField = new JTextField();
        JTextField rateTxtField = new JTextField();

        JComboBox typeComboBox = new JComboBox(LoanType.values());
        typeComboBox.setSelectedItem(LoanType.FIFTH_LOAN);

        // set default due date based on the customers pay frequency and assuming
        // a fifth loan.
        Calendar tmpCal = Calendar.getInstance();
        PayFrequency frequency = lender.getCurBorrower().getPayFreq();
        tmpCal.add(Calendar.DAY_OF_MONTH, frequency.getLoanPeriod());
        dueDateCal.setDate(tmpCal.getTime());

        // goes into the JOptionPane
        JComponent[] inputs = new JComponent[]{
            new JLabel("PRINCIPLE:"), loanTxtField,
            new JLabel("ORIGINATION DATE:"), origDateCal,
            new JLabel("DUE DATE:"), dueDateCal,
            new JLabel("RATE (OLNY FOR PERSONAL LOANS)"), rateTxtField,
            new JLabel("Loan Type:"), typeComboBox};

        int result = JOptionPane.showOptionDialog(rootPane, inputs,
                "New Loan", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result != JOptionPane.CLOSED_OPTION
                && result != JOptionPane.CANCEL_OPTION) {
            String principleStr = loanTxtField.getText();

            String rateStr = Util.rateStr(rateTxtField.getText());

            if ((!principleStr.isEmpty()) && (origDateCal != null)
                    && (dueDateCal != null)) {
                try {
                    LoanType type = (LoanType) typeComboBox.getSelectedItem();
                    double amount = Double.parseDouble(principleStr);
                    double rate = 0;

                    if (type.equals(LoanType.PERSONAL)) {
                        rate = Double.parseDouble(rateStr);
                    }

                    Date dueDate = dueDateCal.getDate();
                    Date origDate = origDateCal.getDate();

                    Loan newLoan = lender.newLoan(lender.getCurBorrower().getCustNum(), amount,
                            origDate, dueDate, rate, type);
                    LoanManagerDAOFactory.commit();

                    principleValLabel.setText(nf.format(newLoan.getPrinciple()));
                    feeValLabel.setText(nf.format(newLoan.getTotalFee()));
                    balanceValLabel.setText(nf.format(newLoan.getBalance()));
                    dueDateValLabel.setText(df.format(newLoan.getDueDate()));

                    gopValLabel.setText(nf.format(lender.loadGOP()));

                    makePaymentButton.setEnabled(true);
                    changePrincipleButton.setEnabled(true);
                    changeFeeButton.setEnabled(true);
                    changeDueDateButton.setEnabled(true);

                    // refresh the list
                    loadLoanList();

                } catch (NumberFormatException nfe) {
                    String msg = "Principle is in Incorrect Format.";
                    printErrorMsg(nfe, msg);
                } catch (SQLException sqle) {
                    String msg = "SQL Exception: Unable to Access Loan Table: "
                            + "Error creating loan";
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
                    String msg = "Class Not Found Error: Loan not created";
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

            } else {
                JOptionPane.showMessageDialog(rootPane, "Enter Amount AND "
                        + "Due Date. Loan Not Added", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_newLoanButtonActionPerformed

    private void makePaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makePaymentButtonActionPerformed
        JCalendar paymentCal = new JCalendar();
        JTextField paymentTxtField = new JTextField();
        JCheckBox PIFCheckBox = new JCheckBox();
        PIFCheckBox.setText("PIF early");

        // goes into the JOptionPane
        JComponent[] inputs = new JComponent[]{
            new JLabel("Payment Date"), paymentCal,
            new JLabel("Payment Amount"), paymentTxtField,
            PIFCheckBox};

        int result = JOptionPane.showOptionDialog(rootPane, inputs,
                "Loan Payment", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result != JOptionPane.CLOSED_OPTION
                && result != JOptionPane.CANCEL_OPTION) {
            String payAmountStr = paymentTxtField.getText();
            boolean isEarlyPIF = PIFCheckBox.isSelected();

            if ((paymentCal != null) && (!payAmountStr.isEmpty())) {
                try {
                    double payment = Double.parseDouble(payAmountStr);
                    Date payDate = paymentCal.getDate();

                    if (payment > 0) {
                        lender.makePayment(payment, payDate, isEarlyPIF);
                        LoanManagerDAOFactory.commit();

                        gopValLabel.setText(nf.format(lender.loadGOP()));
                        netInValLabel.setText(nf.format(lender.updateNetIn()));

                        // reload the borrower with new values
                        loadBorrower();
                    }

                } catch (NumberFormatException nfe) {
                    String msg = "Invalid Number";
                    printErrorMsg(nfe, msg);
                } catch (SQLException sqle) {
                    String msg = "SQL Error: payment not made";
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
                    String msg = "Class Not Found: payment not made";
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
            } else {
                JOptionPane.showMessageDialog(rootPane, "Enter both "
                        + "payment and date!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_makePaymentButtonActionPerformed

    private void custNumTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custNumTextFieldActionPerformed
        String custNumStr = custNumTextField.getText();

        if (!custNumStr.isEmpty()) {
            try {
                // clear any existing borrower info first
                unloadBorrower();
                long custNum = Long.parseLong(custNumStr);

                searchResultTableModel.setRowCount(0);
                Borrower curCust = lender.getBorrower(custNum);
                lender.setCurBorrower(curCust);

                // load the borrower info if borrower with custNum exists
                if (curCust != null) {
                    loadBorrower();
                    selectButton.setEnabled(false);
                }

                custNumTextField.setText("");
            } catch (SQLException sqle) {
                String msg = "SQL Error: Error Accessing database. Coulb Not "
                        + "Access Borrower Table";
                printErrorMsg(sqle, msg);
            } catch (ClassNotFoundException cnfe) {
                String msg = "Class Not Found Error: Error Searching Customers";
                printErrorMsg(cnfe, msg);
            } catch (NumberFormatException nfe) {
                String msg = "Not a Valid Number";
                printErrorMsg(nfe, msg);
            }
        }// end if
    }//GEN-LAST:event_custNumTextFieldActionPerformed

    private void paymentDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // goes into the JOptionPane
        JComponent[] inputs = new JComponent[]{
            new JLabel("Don't delete a payment unless it was from today's date (i.e. "
            + "don't delete a payment from yesterday even if that was the last "
            + "payment done in the system). Click OK to continue or "
            + "Cancel if you don't want to delete.")};

        int result = JOptionPane.showOptionDialog(rootPane, inputs,
                "Delete Payment Confirmation", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (result != JOptionPane.CLOSED_OPTION
                && result != JOptionPane.CANCEL_OPTION) {
            int selectedRow;
            if ((selectedRow = paymentListTable.getSelectedRow()) != -1) {
                long paymentID = (Long) paymentListTable.getValueAt(selectedRow, 2);
                try {
                    lender.deletePayment(paymentID);
                    LoanManagerDAOFactory.commit();

                    loadPaymentList();
                } catch (SQLException sqle) {
                    String msg = "SQL Exception: Error Accessing Database. ";
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
                    String msg = "Class Not Found: Database Error";
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
            }
        }
    }

    private void paymentHistoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentHistoryButtonActionPerformed
        paymentListTableModel.setRowCount(0);
        paymentDeleteButton.setEnabled(true);

        // goes into the JOptionPane
        JComponent inputs[] = new JComponent[]{jScrollPane3, paymentDeleteButton};

        if (lender.getCurLoan().getStatus().equals(LoanStatus.PAID)) {
            paymentDeleteButton.setEnabled(false);
        }

        try {
            loadPaymentList();
            JOptionPane.showMessageDialog(rootPane, inputs);
            loadLoan();
        } catch (SQLException sqle) {
            String msg = "SQL Error: Error Accessing Payment Table.";
            printErrorMsg(sqle, msg);
        } catch (ClassNotFoundException cnfe) {
            String msg = "Class Not Found: Error Accessing Payment Table.";
            printErrorMsg(cnfe, msg);
        }
    }//GEN-LAST:event_paymentHistoryButtonActionPerformed

    private void changeDueDateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDueDateButtonActionPerformed
        JCalendar dueDateCal = new JCalendar();

        // goes into the JOptionPane
        JComponent[] inputs = new JComponent[]{
            new JLabel("New Due Date"), dueDateCal};

        int result = JOptionPane.showOptionDialog(rootPane, inputs,
                "Change Due Date", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result != JOptionPane.CLOSED_OPTION
                && result != JOptionPane.CANCEL_OPTION) {
            try {
                lender.updateDueDate(dueDateCal.getDate());
                lender.updateLoan();
                LoanManagerDAOFactory.commit();

                loadBorrower();
            } catch (SQLException sqle) {
                String msg = "SQL Error: payment not made";
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
                String msg = "Class Not Found: payment not made";
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
        }
    }//GEN-LAST:event_changeDueDateButtonActionPerformed

    private void changePrincipleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePrincipleButtonActionPerformed
        JTextField newPrincipleTxtField = new JTextField();

        // goes into the JOptionPane
        JComponent[] inputs = new JComponent[]{
            new JLabel("New Principle"), newPrincipleTxtField};

        int result = JOptionPane.showOptionDialog(rootPane, inputs,
                "Change Principle", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result != JOptionPane.CLOSED_OPTION
                && result != JOptionPane.CANCEL_OPTION) {
            try {
                double newPrinciple = Double.parseDouble(newPrincipleTxtField.getText());
                lender.updatePrinciple(newPrinciple);
                lender.updateLoan();
                LoanManagerDAOFactory.commit();

                loadBorrower();
            } catch (NumberFormatException nfe) {
                String msg = "Invalid Number";
                printErrorMsg(nfe, msg);
            } catch (SQLException sqle) {
                String msg = "SQL Error: Loan Not Updated";
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
                String msg = "Class Not Found: Loan Not Updated";
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
        }
    }//GEN-LAST:event_changePrincipleButtonActionPerformed

    private void changeFeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFeeButtonActionPerformed
        JTextField newFeeTxtField = new JTextField();

        // goes into the JOptionPane
        JComponent[] inputs = new JComponent[]{
            new JLabel("New Fee"), newFeeTxtField};

        int result = JOptionPane.showOptionDialog(rootPane, inputs,
                "Change Fee", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result != JOptionPane.CLOSED_OPTION
                && result != JOptionPane.CANCEL_OPTION) {
            try {
                double newFee = Double.parseDouble(newFeeTxtField.getText());
                lender.updateFee(newFee);
                lender.updateLoan();
                LoanManagerDAOFactory.commit();

                loadBorrower();
            } catch (NumberFormatException nfe) {
                String msg = "Invalid Number";
                printErrorMsg(nfe, msg);
            } catch (SQLException sqle) {
                String msg = "SQL Error: Loan Not Updated";
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
                String msg = "Class Not Found: Loan Not Updated";
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
        }
    }//GEN-LAST:event_changeFeeButtonActionPerformed

    private void openLoansButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openLoansButtonActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                //new OpenLoanReportDialog(LenderGUI.this, false, lender).setVisible(true);
                new OpenLoanReportGUI(lender).setVisible(true);
            }
        });
    }//GEN-LAST:event_openLoansButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void pastDueReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pastDueReportButtonActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                //new OpenLoanReportDialog(LenderGUI.this, false, lender).setVisible(true);
                new PastDueReportGUI(lender).setVisible(true);
            }
        });
    }//GEN-LAST:event_pastDueReportButtonActionPerformed

    private void feesReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feesReportButtonActionPerformed
        JCalendar fromDateCal = new JCalendar();
        JCalendar toDateCal = new JCalendar();

        // goes into the JOptionPane
        JComponent[] inputs = new JComponent[]{
            new JLabel("From: "), fromDateCal,
            new JLabel("To: "), toDateCal
        };

        int result = JOptionPane.showOptionDialog(rootPane, inputs,
                "Fee Report", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result != JOptionPane.CLOSED_OPTION
                && result != JOptionPane.CANCEL_OPTION) {

            if ((fromDateCal != null) && (toDateCal != null)) {
                final Date begDate = fromDateCal.getDate();
                final Date endDate = toDateCal.getDate();

                java.awt.EventQueue.invokeLater(new Runnable() {

                    public void run() {

                        try {
                            new FeesReportGUI(lender, begDate, endDate).setVisible(true);
                        } catch (ParseException pe) {
                            JOptionPane.showMessageDialog(rootPane, "Enter To AND "
                                    + "From Date.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });


            } else {
                JOptionPane.showMessageDialog(rootPane, "Enter To AND "
                        + "From Date.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_feesReportButtonActionPerformed

    private void gopHistoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gopHistoryButtonActionPerformed
        JCalendar gopDateCal = new JCalendar();

        // goes into the JOptionPane
        JComponent[] inputs = new JComponent[]{
            new JLabel("Enter Date: "), gopDateCal};

        int result = JOptionPane.showOptionDialog(rootPane, inputs,
                "GOP History", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result != JOptionPane.CLOSED_OPTION
                && result != JOptionPane.CANCEL_OPTION) {
            try {
                Date gopDate = gopDateCal.getDate();
                Double gop = lender.getGOPByDate(gopDate);

                if ((gop == null) && (gopDate.after(df.parse("04/14/2011")) && !gopDate.after(lender.getCurDate()))) {
                    long prevDay = 86400000;
                    while ((gop =
                            lender.getGOPByDate(new Date(gopDate.getTime() - prevDay))) == null) {
                        prevDay += 86400000;
                    }
                }

                JOptionPane.showMessageDialog(rootPane, (gop != null) ? "GOP: " + nf.format(gop) : "No GOP data before 4/15/2011", "GOP on "
                        + DateFormat.getDateInstance(DateFormat.MEDIUM).format(gopDate),
                        JOptionPane.PLAIN_MESSAGE);

            } catch (ParseException pe) {
                String msg = "Incorrect Date Format. \n\nUse: MM/DD/YYYY";
                printErrorMsg(pe, msg);
            } catch (SQLException sqle) {
                String msg = "SQL Error: Loan Not Updated";
                printErrorMsg(sqle, msg);

            } catch (ClassNotFoundException cnfe) {
                String msg = "Class Not Found: Loan Not Updated";
                printErrorMsg(cnfe, msg);
            }
        }
    }//GEN-LAST:event_gopHistoryButtonActionPerformed

    private void editCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCustomerButtonActionPerformed
        JTextField fNameTxtField = new JTextField();
        JTextField mNameTxtField = new JTextField();
        JTextField lNameTxtField = new JTextField();

        JComboBox freqComboBox = new JComboBox(PayFrequency.values());
        freqComboBox.setSelectedItem(lender.getCurBorrower().getPayFreq());

        fNameTxtField.setText(lender.getCurBorrower().getFirstName().toUpperCase());
        mNameTxtField.setText(lender.getCurBorrower().getMiddleName().toUpperCase());
        lNameTxtField.setText(lender.getCurBorrower().getLastName().toUpperCase());

        // goes into the JOptionPane
        javax.swing.JComponent[] inputs = new javax.swing.JComponent[]{
            new JLabel("FIRST: "), fNameTxtField,
            new JLabel("MIDDLE: "), mNameTxtField,
            new JLabel("LAST: "), lNameTxtField,
            new JLabel("PAY FREQUENCY"), freqComboBox,};

        int result = JOptionPane.showOptionDialog(rootPane, inputs,
                "Edit Customer", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result != JOptionPane.CLOSED_OPTION
                && result != JOptionPane.CANCEL_OPTION) {
            String fName = fNameTxtField.getText();
            String mName = mNameTxtField.getText();
            String lName = lNameTxtField.getText();
            PayFrequency payFreq = (PayFrequency) freqComboBox.getSelectedItem();

            if (!fName.isEmpty() && !lName.isEmpty()) {
                try {
                    // save the borrower number
                    long custNum = lender.getCurBorrower().getCustNum();

                    lender.updateBorrower(fName, mName, lName, payFreq);
                    LoanManagerDAOFactory.commit();

                    unloadBorrower();
                    lender.setCurBorrower(lender.getBorrower(custNum));
                    loadBorrower();

                } catch (SQLException sqle) {
                    String msg = "SQL Exception: Coult Not Access Borrower Table"
                            + "/nError Editing Customer";
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
                    String msg = "Class Not Found: Error Editing Customer";
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

            } else {

                JOptionPane.showMessageDialog(rootPane, "Enter First AND "
                        + "Last Name. Customer Not Edited", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_editCustomerButtonActionPerformed

    private void setStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setStatusButtonActionPerformed
        ButtonGroup statBtnGrp = new ButtonGroup();
        JRadioButton chgOffStatRadioBtn = new JRadioButton(LoanStatus.CHARGED_OFF.name());
        JRadioButton bankruptcyStatRadioBtn = new JRadioButton(LoanStatus.BANKRUPTCY.name());
        JRadioButton collStatRadioBtn = new JRadioButton(LoanStatus.COLLECTIONS.name());
        JRadioButton pdStatRadioBtn = new JRadioButton(LoanStatus.PAID.name());
        JRadioButton openStatRadioBtn = new JRadioButton(LoanStatus.OPEN.name());

        statBtnGrp.add(chgOffStatRadioBtn);
        statBtnGrp.add(bankruptcyStatRadioBtn);
        statBtnGrp.add(collStatRadioBtn);
        statBtnGrp.add(pdStatRadioBtn);
        statBtnGrp.add(openStatRadioBtn);

        // goes into the JOptionPane
        JComponent[] inputs = new JComponent[]{
            chgOffStatRadioBtn, bankruptcyStatRadioBtn, collStatRadioBtn,
            pdStatRadioBtn, openStatRadioBtn
        };

        int result = JOptionPane.showOptionDialog(rootPane, inputs,
                "Change Status", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result != JOptionPane.CLOSED_OPTION
                && result != JOptionPane.CANCEL_OPTION) {
            try {
                if (chgOffStatRadioBtn.isSelected()) {
                    lender.changeStatus(LoanStatus.CHARGED_OFF);

                } else if (bankruptcyStatRadioBtn.isSelected()) {
                    lender.changeStatus(LoanStatus.BANKRUPTCY);

                } else if (collStatRadioBtn.isSelected()) {
                    lender.changeStatus(LoanStatus.COLLECTIONS);

                } else if (pdStatRadioBtn.isSelected()) {
                    lender.changeStatus(LoanStatus.PAID);

                } else if (openStatRadioBtn.isSelected()) {
                    lender.changeStatus(LoanStatus.OPEN);
                }

                LoanManagerDAOFactory.commit();

                // reload the borrower with new values
                loadBorrower();
                gopValLabel.setText(nf.format(lender.loadGOP()));


            } catch (SQLException sqle) {
                String msg = "SQL Error: Loan Not Updated";
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
                String msg = "Class Not Found: Loan Not Updated";
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
        }

    }//GEN-LAST:event_setStatusButtonActionPerformed

    private void markReturnedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markReturnedButtonActionPerformed
        try {
            // goes into the JOptionPane
            JComponent[] inputs = new JComponent[]{
                new JLabel("Warning! This will reopen loan.\nClick OK to continue")};

            int result = JOptionPane.showOptionDialog(rootPane, inputs,
                    "Mark Check Returned", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (result != JOptionPane.CLOSED_OPTION
                    && result != JOptionPane.CANCEL_OPTION) {
                lender.markCheckReturned();

                LoanManagerDAOFactory.commit();
                loadLoanList();
            }

        } catch (SQLException sqle) {
            String msg = "SQL Exception: Error Accessing Database. ";
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
            String msg = "Class Not Found: Database Error";
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
    }//GEN-LAST:event_markReturnedButtonActionPerformed

    private void depositCheckButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositCheckButtonActionPerformed
        try {
            JCalendar depositCal = new JCalendar();

            // goes into the JOptionPane
            JComponent[] inputs = new JComponent[]{
                new JLabel("Deposit Date"), depositCal};

            int result = JOptionPane.showOptionDialog(rootPane, inputs,
                    "Deposit Check", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (result != JOptionPane.CLOSED_OPTION
                    && result != JOptionPane.CANCEL_OPTION) {

                if (depositCal != null) {
                    Date payDate = depositCal.getDate();

                    lender.depositPDLCheck(payDate);

                    LoanManagerDAOFactory.commit();

                    gopValLabel.setText(nf.format(lender.loadGOP()));
                    netInValLabel.setText(nf.format(lender.updateNetIn()));

                    loadBorrower();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Enter a date!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException sqle) {
            String msg = "SQL Exception: Error Accessing Database. ";
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
            String msg = "Class Not Found: Database Error";
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
    }//GEN-LAST:event_depositCheckButtonActionPerformed

    private void mainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuButtonActionPerformed
        mainMenuGUI.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_mainMenuButtonActionPerformed

    private void reUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reUpButtonActionPerformed
        JCalendar reUpCal = new JCalendar();
        JTextField newPrincipleTxtField = new JTextField();

        // goes into the JOptionPane
        JComponent[] inputs = new JComponent[]{
            new JLabel("Deposit Date"), reUpCal,
            new JLabel("Principal Added: "), newPrincipleTxtField};

        int result = JOptionPane.showOptionDialog(rootPane, inputs,
                "Deposit Check", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result != JOptionPane.CLOSED_OPTION
                && result != JOptionPane.CANCEL_OPTION) {
            if (reUpCal != null) {
                try {
                    Date payDate = reUpCal.getDate();
                    double newPrinciple = Double.parseDouble(newPrincipleTxtField.getText());

                    lender.reUpLoan(newPrinciple, payDate);

                    LoanManagerDAOFactory.commit();

                    gopValLabel.setText(nf.format(lender.loadGOP()));
                    netInValLabel.setText(nf.format(lender.updateNetIn()));

                    loadBorrower();
                } catch (NumberFormatException nfe) {
                    String msg = "Invalid Number";
                    printErrorMsg(nfe, msg);
                } catch (SQLException sqle) {
                    String msg = "SQL Error: Loan Not Updated";
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
                    String msg = "Class Not Found: Loan Not Updated";
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
            } else {
                JOptionPane.showMessageDialog(rootPane, "Enter a date!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_reUpButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            openLoansListTable.print();
        } catch (PrinterException ex) {
            printErrorMsg(ex, "unable to print");
        }
    }

    private void loanListTableValueChanged(javax.swing.event.ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting()) {
            return;
        }
        try {
            int row;
            if ((row = loanListTable.getSelectedRow()) != -1) {
                Loan curLoan = lender.getLoan((Long) loanListTable.getValueAt(row, 6));
                lender.setCurLoan(curLoan);

                if (curLoan != null) {
                    principleValLabel.setText(nf.format(curLoan.getPrinciple()));
                    feeValLabel.setText(nf.format(curLoan.getTotalFee()));
                    balanceValLabel.setText(nf.format(curLoan.getBalance()));
                    dueDateValLabel.setText(df.format(curLoan.getDueDate()));
                    netInValLabel.setText(nf.format(lender.updateNetIn()));

                    if (curLoan.getStatus().equals(LoanStatus.PAID)) {
                        makePaymentButton.setEnabled(false);
                        changeDueDateButton.setEnabled(false);
                        changePrincipleButton.setEnabled(false);
                        changeFeeButton.setEnabled(false);
                        depositCheckButton.setEnabled(false);
                        markReturnedButton.setEnabled(false);
                        reUpButton.setEnabled(false);
                    } else if (curLoan.getStatus().equals(LoanStatus.DEPOSITED)) {
                        makePaymentButton.setEnabled(false);
                        changeDueDateButton.setEnabled(false);
                        changePrincipleButton.setEnabled(false);
                        changeFeeButton.setEnabled(false);
                        depositCheckButton.setEnabled(false);
                        markReturnedButton.setEnabled(true);
                        reUpButton.setEnabled(false);
                    } else {
                        makePaymentButton.setEnabled(true);
                        changeDueDateButton.setEnabled(true);
                        changePrincipleButton.setEnabled(true);
                        changeFeeButton.setEnabled(true);
                        depositCheckButton.setEnabled(true);
                        markReturnedButton.setEnabled(false);
                        reUpButton.setEnabled(true);
                    }

                    paymentHistoryButton.setEnabled(true);
                }
            }
        } catch (SQLException sqle) {
            String msg = "SQL Error: Unable to Access Loan Table";
            printErrorMsg(sqle, msg);
        } catch (ClassNotFoundException cnfe) {
            String msg = "Class Not Found: Unable to Access Loan Table";
            printErrorMsg(cnfe, msg);
        }
    }

    private void loadPaymentList() throws SQLException, ClassNotFoundException {
        paymentListTableModel.setRowCount(0);
        paymentList = lender.getPaymentList(lender.getCurLoan().getLoanNum());

        int row = 0;
        for (Payment pmt : paymentList) {
            paymentListTableModel.addRow(new Object[][]{null, null, null});

            paymentListTable.setValueAt(pmt.getDate(), row, 0);
            paymentListTable.setValueAt(nf.format(pmt.getPrincipleAmount()
                    + pmt.getFeeAmount() + pmt.getMiscAmount()), row, 1);
            paymentListTable.setValueAt(pmt.getPaymentID(), row, 2);

            row++;
        }
    }

    private void loadBorrowerList() throws SQLException, ClassNotFoundException {
        searchResultTableModel.setRowCount(0);
        if (!bList.isEmpty()) {

            int row = 0;
            for (Borrower b : bList) {
                searchResultTableModel.addRow(new Object[][]{null, null, null});

                searchResultTable.setValueAt(b.getFullName(), row, 0);
                searchResultTable.setValueAt("", row, 1);
                searchResultTable.setValueAt(b.getCustNum(), row, 2);

                row++;
            }

            selectButton.setEnabled(true);
        }
    }

    private void loadLoanList() throws SQLException, ClassNotFoundException {
        loanListTableModel.setRowCount(0);
        loanList = lender.getLoanList(lender.getCurBorrower().getCustNum());

        int row = 0;
        for (Loan l : loanList) {
            loanListTableModel.addRow(new Object[][]{null, null, null, null});

            loanListTable.setValueAt(nf.format(l.getOrigPrinciple()), row, 0);
            loanListTable.setValueAt(nf.format(l.getOrigTotalFee()), row, 1);
            loanListTable.setValueAt(l.getOrigDate(), row, 2);
            loanListTable.setValueAt(l.getDueDate(), row, 3);
            loanListTable.setValueAt(l.getStatus(), row, 4);
            loanListTable.setValueAt(l.getType().name(), row, 5);
            loanListTable.setValueAt(l.getLoanNum(), row, 6);

            row++;
        }
    }

    /**
     * Loads the borrower info on the screen. Takes care of loading the list of
     * loans and enabling associated buttons.
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void loadBorrower() throws SQLException, ClassNotFoundException {
        Borrower curCust = lender.getCurBorrower();

        nameLabel.setText(curCust.getFullName());

        loadLoanList();
        if (!loanList.isEmpty()) {
            lender.setCurLoan(loanList.get(0));
            loadLoan();
        }

        newLoanButton.setEnabled(true);
        editCustomerButton.setEnabled(true);
    }

    private void loadLoan() throws SQLException, ClassNotFoundException {
        String principle = "";
        String fee = "";
        String balance = "";
        String dueDate = "";
        String net = "";

        Loan curLoan = lender.getCurLoan();
        if (curLoan != null) {
            principle = nf.format(curLoan.getPrinciple());
            fee = nf.format(curLoan.getTotalFee());
            balance = nf.format(curLoan.getBalance());
            dueDate = df.format(curLoan.getDueDate());

            principleValLabel.setText(principle);
            feeValLabel.setText(fee);
            balanceValLabel.setText(balance);
            dueDateValLabel.setText(dueDate);
            netInValLabel.setText(nf.format(lender.updateNetIn()));


            if (curLoan.getStatus().equals(LoanStatus.PAID)) {
                makePaymentButton.setEnabled(false);
                changeDueDateButton.setEnabled(false);
                changePrincipleButton.setEnabled(false);
                changeFeeButton.setEnabled(false);
                depositCheckButton.setEnabled(false);
                markReturnedButton.setEnabled(false);
                reUpButton.setEnabled(false);

            } else if (curLoan.getStatus().equals(LoanStatus.DEPOSITED)) {
                makePaymentButton.setEnabled(false);
                changeDueDateButton.setEnabled(false);
                changePrincipleButton.setEnabled(false);
                changeFeeButton.setEnabled(false);
                depositCheckButton.setEnabled(false);
                markReturnedButton.setEnabled(true);
                reUpButton.setEnabled(false);

            } else {
                makePaymentButton.setEnabled(true);
                changeDueDateButton.setEnabled(true);
                changePrincipleButton.setEnabled(true);
                changeFeeButton.setEnabled(true);
                depositCheckButton.setEnabled(true);
                markReturnedButton.setEnabled(false);
                reUpButton.setEnabled(true);
            }

            paymentHistoryButton.setEnabled(true);
            setStatusButton.setEnabled(true);
        }
    }

    /**
     * clears the borrower info from the screen and set necessary objects to null
     */
    private void unloadBorrower() {
        lender.clearBorrower();
        loanListTableModel.setRowCount(0);

        nameLabel.setText("");
        principleValLabel.setText("");
        feeValLabel.setText("");
        balanceValLabel.setText("");
        dueDateValLabel.setText("");
        netInValLabel.setText("");
        typeLabel.setText("");

        makePaymentButton.setEnabled(false);
        newLoanButton.setEnabled(false);
        changeDueDateButton.setEnabled(false);
        changePrincipleButton.setEnabled(false);
        changeFeeButton.setEnabled(false);
        editCustomerButton.setEnabled(false);
        reUpButton.setEnabled(false);
        paymentHistoryButton.setEnabled(false);
        markReturnedButton.setEnabled(false);
        depositCheckButton.setEnabled(false);
        setStatusButton.setEnabled(false);
    }

    private void printErrorMsg(Exception e, String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Error",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JLabel balanceValLabel;
    private javax.swing.JButton changeDueDateButton;
    private javax.swing.JButton changeFeeButton;
    private javax.swing.JButton changePrincipleButton;
    private javax.swing.JLabel curDateLabel;
    private javax.swing.JLabel curDateTxtLabel;
    private javax.swing.JLabel custNumLabel;
    private javax.swing.JTextField custNumTextField;
    private javax.swing.JButton depositCheckButton;
    private javax.swing.JLabel dueDateLabel;
    private javax.swing.JLabel dueDateValLabel;
    private javax.swing.JButton editCustomerButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel feeLabel;
    private javax.swing.JLabel feeValLabel;
    private javax.swing.JButton feesReportButton;
    private javax.swing.JButton gopHistoryButton;
    private javax.swing.JLabel gopLabel;
    private javax.swing.JLabel gopValLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable loanListTable;
    private javax.swing.JButton mainMenuButton;
    private javax.swing.JButton makePaymentButton;
    private javax.swing.JButton markReturnedButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel netInLabel;
    private javax.swing.JLabel netInValLabel;
    private javax.swing.JButton newBorrowerButton;
    private javax.swing.JDialog newCustomerDialog;
    private javax.swing.JButton newLoanButton;
    private javax.swing.JButton openLoansButton;
    private javax.swing.JButton printButton;
    private javax.swing.JButton pastDueReportButton;
    private javax.swing.JButton paymentHistoryButton;
    private javax.swing.JButton paymentDeleteButton;
    private javax.swing.JLabel principleLabel;
    private javax.swing.JLabel principleValLabel;
    private javax.swing.JButton reUpButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchLastNameField;
    private javax.swing.JTable searchResultTable;
    private javax.swing.JButton selectButton;
    private javax.swing.JButton setStatusButton;
    private javax.swing.JLabel typeLabel;
    // End of variables declaration//GEN-END:variables
}
