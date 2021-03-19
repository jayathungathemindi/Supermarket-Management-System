
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public final class employeedetails extends javax.swing.JFrame {

    /**
     * Creates new form employeeDetails
     */
    public employeedetails() {
        initComponents();
        showAll();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ephone = new javax.swing.JTextField();
        eaddress = new javax.swing.JTextField();
        esearch = new javax.swing.JTextField();
        imageemployee = new javax.swing.JLabel();
        deletebtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        Browsebtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        eid = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        eemail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ename = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableemp = new javax.swing.JTable();
        resetbtn = new javax.swing.JButton();
        searchbtn = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Britannic Bold", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Employee phone:");
        panel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 190, 30));

        jLabel5.setFont(new java.awt.Font("Britannic Bold", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Employee address:");
        panel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 200, 30));

        jLabel6.setFont(new java.awt.Font("Britannic Bold", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Employee Username:");
        panel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 250, 30));

        jLabel7.setFont(new java.awt.Font("Britannic Bold", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Employee ID:");
        panel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 150, 30));

        ephone.setBackground(new java.awt.Color(255, 255, 255));
        ephone.setFont(new java.awt.Font("Footlight MT Light", 0, 18)); // NOI18N
        ephone.setForeground(new java.awt.Color(0, 153, 153));
        panel1.add(ephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 180, -1));

        eaddress.setBackground(new java.awt.Color(255, 255, 255));
        eaddress.setFont(new java.awt.Font("Footlight MT Light", 0, 18)); // NOI18N
        eaddress.setForeground(new java.awt.Color(0, 153, 153));
        panel1.add(eaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 180, -1));

        esearch.setBackground(new java.awt.Color(255, 255, 255));
        esearch.setFont(new java.awt.Font("Footlight MT Light", 0, 18)); // NOI18N
        esearch.setForeground(new java.awt.Color(204, 204, 255));
        esearch.setText("Search");
        esearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                esearchMouseClicked(evt);
            }
        });
        esearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esearchActionPerformed(evt);
            }
        });
        panel1.add(esearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 180, -1));

        imageemployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/download.png"))); // NOI18N
        panel1.add(imageemployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 240, 260));

        deletebtn.setBackground(new java.awt.Color(255, 255, 255));
        deletebtn.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        deletebtn.setForeground(new java.awt.Color(51, 51, 51));
        deletebtn.setText("Delete");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });
        panel1.add(deletebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 100, 190, -1));

        updatebtn.setBackground(new java.awt.Color(255, 255, 255));
        updatebtn.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        updatebtn.setForeground(new java.awt.Color(51, 51, 51));
        updatebtn.setText("Update");
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });
        panel1.add(updatebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 190, 190, -1));

        Browsebtn.setBackground(new java.awt.Color(0, 0, 0));
        Browsebtn.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        Browsebtn.setForeground(new java.awt.Color(255, 255, 255));
        Browsebtn.setText("Browse");
        Browsebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowsebtnActionPerformed(evt);
            }
        });
        panel1.add(Browsebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, -1, -1));

        jLabel9.setFont(new java.awt.Font("Britannic Bold", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Employee image:");
        panel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 190, 30));

        eid.setBackground(new java.awt.Color(255, 255, 255));
        eid.setFont(new java.awt.Font("Footlight MT Light", 0, 18)); // NOI18N
        eid.setForeground(new java.awt.Color(0, 153, 153));
        panel1.add(eid, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 180, -1));

        jLabel10.setFont(new java.awt.Font("Britannic Bold", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Employee e-mail:");
        panel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 190, 30));

        eemail.setBackground(new java.awt.Color(255, 255, 255));
        eemail.setFont(new java.awt.Font("Footlight MT Light", 0, 18)); // NOI18N
        eemail.setForeground(new java.awt.Color(0, 153, 153));
        panel1.add(eemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 180, -1));

        jLabel8.setFont(new java.awt.Font("Britannic Bold", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Employee name:");
        panel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 190, 30));

        ename.setBackground(new java.awt.Color(255, 255, 255));
        ename.setFont(new java.awt.Font("Footlight MT Light", 0, 18)); // NOI18N
        ename.setForeground(new java.awt.Color(0, 153, 153));
        panel1.add(ename, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 180, -1));

        tableemp.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tableemp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address", "Phone", "E-mail"
            }
        ));
        tableemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableempMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableemp);

        panel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 770, 360));

        resetbtn.setBackground(new java.awt.Color(255, 255, 255));
        resetbtn.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        resetbtn.setForeground(new java.awt.Color(51, 51, 51));
        resetbtn.setText("Reset");
        resetbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetbtnActionPerformed(evt);
            }
        });
        panel1.add(resetbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 280, 190, -1));

        searchbtn.setBackground(new java.awt.Color(0, 0, 0));
        searchbtn.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        searchbtn.setForeground(new java.awt.Color(255, 255, 255));
        searchbtn.setText("Search");
        searchbtn.setToolTipText("");
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });
        panel1.add(searchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 20, -1, 40));

        back.setBackground(new java.awt.Color(255, 255, 255));
        back.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        back.setForeground(new java.awt.Color(51, 51, 51));
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        panel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 810, 190, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1355, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 934, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void esearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_esearchMouseClicked
        esearch.setText("");
    }//GEN-LAST:event_esearchMouseClicked

    private void esearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esearchActionPerformed

    }//GEN-LAST:event_esearchActionPerformed
String photopath="";
    public ImageIcon resetImageSize(String photopath,byte[] photo){
        ImageIcon myphoto;
        if(photopath!=null){
        myphoto=new ImageIcon(photopath);
    }
        else{
            myphoto=new ImageIcon(photo); 
        }
        Image img=myphoto.getImage();
        Image img1=img.getScaledInstance(imageemployee.getWidth(),imageemployee.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon ph=new ImageIcon(img1);
        return ph;
        
    
    }

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        int row = tableemp.getSelectedRow();
        if (row > -1) {
            String value = tableemp.getValueAt(row, 0).toString();

            Connection con;
            try {
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/testdb", "admin1234"," ADMIN1234");// taking the connection
                String delete = "DELETE FROM Login WHERE ID=?";
                PreparedStatement st = con.prepareStatement(delete);
                st.setString(1, value);
                boolean b = st.execute();
                if (b) {
                    JOptionPane.showMessageDialog(this, "Employee Deleted Successfully");
                }
                showAll();
            } catch (HeadlessException | SQLException ex) {
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select valid row");
        }
    }//GEN-LAST:event_deletebtnActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
     if(ename.getText().equals("")||eemail.getText().equals("")||ephone.getText().equals("")||eid.getText().equals("")){
           System.out.println("null");
           JOptionPane.showMessageDialog(this,"Sorry! please enter the details as asked!");        
    }
    else{
           System.out.println("null function suceeded");     
           Connection con;
       PreparedStatement ps;
    try {
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/testdb","test","test");// taking the connection
            Statement st = con.createStatement();  
            st.execute("Update LOGIN SET NAME='"+ename.getText()+"',ADDRESS='"+eaddress.getText()+"',EMAIL='"+eaddress.getText()+"',PHONE="+ephone.getText()+") where ID=?");
               System.out.println("updated");
            showAll();
        }
       catch(SQLException e){
                      System.out.println(e);
           System.out.println("error in updating");
       }
   }        
    }//GEN-LAST:event_updatebtnActionPerformed

    private void BrowsebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowsebtnActionPerformed
        JFileChooser chooser=new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter fnef=new  FileNameExtensionFilter ("*.images","jpg","png");
        chooser.addChoosableFileFilter(fnef);
        int ans=chooser.showSaveDialog(null);
        if(ans==JFileChooser.APPROVE_OPTION){
            File selectedPhoto=chooser.getSelectedFile();
            String path=selectedPhoto.getAbsolutePath();
            imageemployee.setIcon(resetImageSize(path,null));
            this.photopath=path;
        }
    }//GEN-LAST:event_BrowsebtnActionPerformed

    private void tableempMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableempMouseClicked
 int row = tableemp.getSelectedRow();
        if (row > -1) {
            String value = tableemp.getValueAt(row, 0).toString();

            Connection con;
            try {
              con = DriverManager.getConnection("jdbc:derby://localhost:1527/testdb","test","test");// taking the connection
                String select = "SELECT * FROM login WHERE ID=?";
                PreparedStatement st = con.prepareStatement(select);
                st.setString(1, value);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {

                    eid.setText(rs.getString("ID"));
                    ename.setText(rs.getString("NAME"));
                    eaddress.setText(rs.getString("ADDRESS"));
                    eemail.setText(rs.getString("EMAIL"));
                    ephone.setText(rs.getString("PHONE"));
                }
                con.close();
            

            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_tableempMouseClicked

    private void resetbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetbtnActionPerformed
        eid.setText("");
        ename.setText("");
        eaddress.setText("");
        ephone.setText("");
        imageemployee.setIcon(null);
        eemail.setText("");
    }//GEN-LAST:event_resetbtnActionPerformed

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
       Connection con;  
      
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/testdb","test","test");// taking the connection
            Statement st=con.createStatement();
            String user=esearch.getText();
            String search= "select * from LOGIN where (NAME='"+user+"')";//retriving data
            ResultSet rs=st.executeQuery (search);

            if(rs.next()){
                String id=rs.getString("ID");
                eid.setText(id);
                String username = rs.getString("name");
                ename.setText(username);
                String address = rs.getString("ADDRESS");
                eaddress.setText(address);
                String phone = rs.getString("PHONE");
                ephone.setText(phone);
                String email = rs.getString("EMAIL");
                eemail.setText(email);
                byte[] img = rs.getBytes("PHOTO");
                //Resize The ImageIcon
                ImageIcon image = new ImageIcon(img);
                Image im = image.getImage();
                Image myImg = im.getScaledInstance(imageemployee.getWidth(), imageemployee.getHeight(),Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myImg);
                imageemployee.setIcon(newImage);
                System.out.println("data fetched");
            }
            else{
                JOptionPane.showMessageDialog(this,"Invalid user id or name.");
                System.out.println("data fetch error");
            }
        }
        catch (SQLException ex) {
            System.out.println("connection error (employeedetail)");
            System.out.println(ex);
        }
    }//GEN-LAST:event_searchbtnActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
      menu Menu=new menu();
      Menu.show();
      dispose();
    }//GEN-LAST:event_backActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(employeedetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new employeedetails().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Browsebtn;
    private javax.swing.JButton back;
    private javax.swing.JButton deletebtn;
    private javax.swing.JTextField eaddress;
    private javax.swing.JTextField eemail;
    private javax.swing.JTextField eid;
    private javax.swing.JTextField ename;
    private javax.swing.JTextField ephone;
    private javax.swing.JTextField esearch;
    private javax.swing.JLabel imageemployee;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Panel panel1;
    private javax.swing.JButton resetbtn;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTable tableemp;
    private javax.swing.JButton updatebtn;
    // End of variables declaration//GEN-END:variables

    public void showAll() {
        Connection con;
        try {
          con = DriverManager.getConnection("jdbc:derby://localhost:1527/testdb","test","test");// taking the connection
            Statement st = con.createStatement();
            String show = "SELECT * FROM login";
            ResultSet rs = st.executeQuery(show);

            DefaultTableModel model = (DefaultTableModel) tableemp.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("ID"));
                String name = rs.getString("NAME");
                String address = rs.getString("ADDRESS");
                String phone = String.valueOf(rs.getString("PHONE"));
                String email = rs.getString("EMAIL");
               
                String empdata[] = {id, name, address, phone, email};

                model.addRow(empdata);
            }

            con.close();
        } catch (SQLException ex) {
            System.out.println("database connection issue-showall");
            System.out.println(ex);
        }
    }}