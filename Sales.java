/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Windows 10
 */
public class Sales extends javax.swing.JFrame {

    /**
     * Creates new form Sales
     */
    public Sales() {
        initComponents();

    }

    public Connection getConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            return DriverManager.getConnection("jdbc:derby://localhost:1527/testdb", "admin1234"," ADMIN1234");
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    //searching the pname, retailprice by adding just the pcode AND PRESSING ENTER
    // a method. called in
    public void code() {

        try {
            Connection con = getConnection();
            String pcode = txtcode.getText();
            String code = "SELECT * FROM PRODUCT WHERE UPPER(CODE)  =?";
            PreparedStatement st = con.prepareStatement(code);
            st.setString(1, pcode.toUpperCase());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                String pname = rs.getString("NAME");
                String price = rs.getString("RETAILPRICE");

                txtpname.setText(pname.trim());
                txtprice.setText(price.trim());
                txtquantity.requestFocus();

            } else {
                JOptionPane.showMessageDialog(this, "Code not found ");
                txtcode.setText("");
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void sales() {
        Connection con;
        //check availability of item before adding it to bill
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/testdb", "test", "test");
            String pcode = txtcode.getText();
            String code = "select * from PRODUCT where PCODE  = ?";
            PreparedStatement st = con.prepareStatement(code);
            st.setString(1, pcode);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int currentquantity;
                currentquantity = rs.getInt("QUANTITY");
                int price = Integer.parseInt(txtprice.getText());
                int quantity = Integer.parseInt(txtquantity.getText());
                int total = price * quantity;

                if (quantity >= currentquantity) {
                    JOptionPane.showMessageDialog(this, "Not Enough Quantity! ");
                } else {
                    DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
                    df.addRow(new Object[]{
                        txtcode.getText(),
                        txtpname.getText(),
                        txtprice.getText(),
                        txtquantity.getText(),
                        total
                    });
                }

            }

            // display total at j table
            int sum = 0;
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                //get values for 4-total in jtable1 in purchase
                sum = sum + Integer.parseInt(jTable1.getValueAt(i, 4).toString());
            }
            //display final total (total cost lable)
            txttotal.setText(String.valueOf(sum));

            //clear textboxes after adding
            txtcode.setText("");
            txtpname.setText("");
            txtprice.setText("");
            txtquantity.setText("");
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void empty() {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        txtcode.setText("");
        txtpname.setText("");
        txtprice.setText("");
        txtquantity.setText("");
        txttotal.setText("");
        txtpayment.setText("");
        txtbalance.setText("");
    }

    //add function for add btn2
    public void add(double total, double payment, double balance) {
        try {
            //date and time
            DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            String date = dt.format(now);
            int lastid = 0;
            String query1 = "INSERT INTO SALE(DATE,TOTAL,PAYMENT,BALANCE) VALUES(?,?,?,?)";
            //Derby 
            Connection con = getConnection();
            PreparedStatement st = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            st.setDate(1, new Date(new java.util.Date().getTime()));
            st.setDouble(2, total);
            st.setDouble(3, payment);
            st.setDouble(4, balance);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                lastid = rs.getInt(1);
            }

            //PURCHASEITEM table insert
            String query2 = "INSERT INTO SALE_ITEM(PRODUCTID,SALEID,PRICE,QUANTITY,TOTAL) VALUES(?,?,?,?,?)";
            PreparedStatement st1 = con.prepareStatement(query2);

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int productid = Integer.parseInt(jTable1.getValueAt(i, 5).toString());
                double price = Double.parseDouble(jTable1.getValueAt(i, 2).toString());
                double quantity = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
                double total_amount = Double.parseDouble(jTable1.getValueAt(i, 4).toString());

                st1.setInt(1, productid);
                st1.setInt(2, lastid);
                st1.setDouble(3, price);
                st1.setDouble(4, quantity);
                st1.setDouble(5, total_amount);
                st1.executeUpdate();

                // increse quantity of product table after a purchase
                String query3 = "UPDATE PRODUCT SET QUANTITY = QUANTITY-? WHERE PRODUCTID = ?";
                PreparedStatement st2 = con.prepareStatement(query3);

                for (int j = i; j < jTable1.getRowCount(); j++) {
                    int pid = Integer.parseInt(jTable1.getValueAt(j, 5).toString());
                    double qt = Double.parseDouble(jTable1.getValueAt(j, 3).toString());

                    st2.setDouble(1, qt);
                    st2.setInt(2, pid);
                    st2.executeUpdate();

                }
                JOptionPane.showMessageDialog(this, "Sale Completed");

            }

            empty();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtcode = new javax.swing.JTextField();
        txtpname = new javax.swing.JTextField();
        txtprice = new javax.swing.JTextField();
        txtquantity = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        txtpayment = new javax.swing.JTextField();
        txtbalance = new javax.swing.JTextField();
        btnadd2 = new javax.swing.JButton();
        btnadd1 = new javax.swing.JButton();
        backbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pay = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Total Cost");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Product Code");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Product Name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Price");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Quantity");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Balance");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Payment");

        txtcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcodeKeyPressed(evt);
            }
        });

        btnadd2.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        btnadd2.setText("SAVE");
        btnadd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadd2ActionPerformed(evt);
            }
        });

        btnadd1.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        btnadd1.setText("ADD");
        btnadd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadd1ActionPerformed(evt);
            }
        });

        backbtn.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        backbtn.setText("Back");
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Code", "Product Name", "Price", "Quantity", "Total", "Product ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(5).setMinWidth(0);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        jButton1.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jButton1.setText("Remove Row");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        pay.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        pay.setText("Payments");
        pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnadd1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                    .addComponent(txtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtpname, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(165, 165, 165)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtbalance, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                        .addComponent(txtpayment)
                                        .addComponent(txttotal))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(btnadd2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                                .addGap(23, 23, 23))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(pay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(97, 97, 97)
                        .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(103, 103, 103))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(pay)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnadd1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtpayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(128, 128, 128))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnadd2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115))))
        );

        jLabel8.setFont(new java.awt.Font("Sylfaen", 3, 24)); // NOI18N
        jLabel8.setText("SALES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(769, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        menu inventory = new menu();
        inventory.show();
        dispose();
    }//GEN-LAST:event_backbtnActionPerformed

    private void btnadd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadd1ActionPerformed
        Connection con;
        if (txtcode.getText().equals("") || txtpname.getText().equals("") || txtprice.getText().equals("") || txtquantity.getText().equals("")) {
            System.out.println("null");
            JOptionPane.showMessageDialog(this, "Sorry! please enter the details as asked!");
        } else {

            String productcode = txtcode.getText();
            String productid = "";
            con = getConnection();
            try {
                PreparedStatement st1 = con.prepareStatement("SELECT * FROM PRODUCT WHERE UPPER(CODE)=?");
                st1.setString(1, productcode.toUpperCase());
                ResultSet rs = st1.executeQuery();
                if (rs.next());
                productid = rs.getString(1);
                con.close();
            } catch (SQLException e) {
            }

            String productname = txtpname.getText();
            double quantity = Double.parseDouble(txtquantity.getText());
            double price = Double.parseDouble(txtprice.getText());

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

            Object productdata[] = {productcode, productname, price, quantity, price * quantity, productid};

            dtm.addRow(productdata);

            txttotal.setText(calculateTotal() + "");

        }

        //clear textboxes after adding
        txtcode.setText("");
        txtpname.setText("");
        txtprice.setText("");
        txtquantity.setText("");
    }//GEN-LAST:event_btnadd1ActionPerformed

    private void btnadd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadd2ActionPerformed
        // payment part and complete salse

        double payment = Double.parseDouble(txtpayment.getText());
        double total = calculateTotal();
        double balance = payment - total;

        txtbalance.setText(String.valueOf(balance));

        add(total, payment, balance);
    }//GEN-LAST:event_btnadd2ActionPerformed

    private void txtcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            code();
        }
    }//GEN-LAST:event_txtcodeKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row > -1) {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.removeRow(row);
            double total = calculateTotal();
            txttotal.setText(total + "");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        empty();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payActionPerformed
        PaymentForm payment=new PaymentForm();
        payment.show();
    }//GEN-LAST:event_payActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Sales().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn;
    private javax.swing.JButton btnadd1;
    private javax.swing.JButton btnadd2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton pay;
    private javax.swing.JTextField txtbalance;
    private javax.swing.JTextField txtcode;
    private javax.swing.JTextField txtpayment;
    private javax.swing.JTextField txtpname;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtquantity;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
    public double calculateTotal() {
        double total = 0;

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            String s = jTable1.getValueAt(i, 4).toString();
            double price = Double.parseDouble(s);
            total += price;
        }
        return total;
    }
}
