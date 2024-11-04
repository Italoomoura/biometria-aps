package Capture;

import Util.ConnectDB;

public class RegisterPerson extends javax.swing.JFrame {

    ConnectDB db = new ConnectDB();

    public RegisterPerson() {
        initComponents();
        showIdUser();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_last_name = new javax.swing.JTextField();
        txt_first_name = new javax.swing.JTextField();
        txt_office = new javax.swing.JTextField();
        txt_nasc_date = new javax.swing.JFormattedTextField();
        txt_id_label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Pessoa");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Source Code Pro", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Sobrenome:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Source Code Pro", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Data de nascimento:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Source Code Pro", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Cargo:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        jLabel5.setFont(new java.awt.Font("Source Code Pro", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nome:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txt_last_name.setBackground(new java.awt.Color(34, 94, 179));
        txt_last_name.setForeground(new java.awt.Color(255, 255, 255));
        txt_last_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_last_nameActionPerformed(evt);
            }
        });
        jPanel3.add(txt_last_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 200, 25));

        txt_first_name.setBackground(new java.awt.Color(34, 94, 179));
        txt_first_name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(txt_first_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 200, 25));

        txt_office.setBackground(new java.awt.Color(34, 94, 179));
        txt_office.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(txt_office, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 200, 25));

        txt_nasc_date.setBackground(new java.awt.Color(34, 94, 179));
        txt_nasc_date.setForeground(new java.awt.Color(255, 255, 255));
        try {
            txt_nasc_date.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_nasc_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nasc_dateActionPerformed(evt);
            }
        });
        jPanel3.add(txt_nasc_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 200, 25));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 450, 150));

        txt_id_label.setFont(new java.awt.Font("Source Code Pro", 0, 24)); // NOI18N
        txt_id_label.setForeground(new java.awt.Color(0, 0, 0));
        txt_id_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_id_label.setText("1");
        jPanel1.add(txt_id_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, -1));

        jButton1.setBackground(new java.awt.Color(34, 94, 179));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("AVANÇAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, 100, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 320));

        setSize(new java.awt.Dimension(479, 287));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String fName = txt_first_name.getText();
        String lName = txt_last_name.getText();
        String office = txt_office.getText();
        String nDate = txt_nasc_date.getText();
        int id = Integer.parseInt(txt_id_label.getText().replace("ID: ", ""));
        
        new Capture(id, fName, lName, office, nDate).setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_nasc_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nasc_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nasc_dateActionPerformed

    private void txt_last_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_last_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_last_nameActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegisterPerson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterPerson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterPerson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterPerson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterPerson().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txt_first_name;
    private javax.swing.JLabel txt_id_label;
    private javax.swing.JTextField txt_last_name;
    private javax.swing.JFormattedTextField txt_nasc_date;
    private javax.swing.JTextField txt_office;
    // End of variables declaration//GEN-END:variables
    
    private void showIdUser() {
        db.conectar();
        db.executaSQL("SELECT * FROM person ORDER BY id DESC LIMIT 1");
        try {
            db.rs.first();
            txt_id_label.setText(String.valueOf(db.rs.getInt("id")));
            int id = Integer.parseInt(txt_id_label.getText());
            id++;
            txt_id_label.setText("ID: " + String.valueOf(id));

        } catch (Exception e){
            
        }
    }

}