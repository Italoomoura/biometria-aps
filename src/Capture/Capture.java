
package Capture;

import Util.ConnectDB;
import Util.ControlPerson;
import Util.ModelPerson;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.BytePointer;
import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;

public class Capture extends javax.swing.JFrame {
    
    private Capture.DaemonThread myThread = null;
    
    //JavaCV
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("C:\\photos\\haarcascade_frontalface_alt.xml");
    
    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();
    
    //Vars
    String root, firstNamePerson, lastNamePerson, officePerson, nascDatePerson;
    int numSamples = 100, sample = 1, idPerson;
    
    //Utils
    ConnectDB connect = new ConnectDB();

    public Capture(int id, String fName, String lName, String office, String nDate) {
        initComponents();
        
        idPerson = id;
        firstNamePerson = fName;
        lastNamePerson = lName;
        officePerson = office;
        nascDatePerson = nDate;
        
        startCamera();
    }

    private Capture() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label_photo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        counterLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Capturar Fotos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_photo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(label_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 380));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 540, 490, 110));

        jButton1.setBackground(new java.awt.Color(34, 94, 179));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setText("FECHAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 100, 30));

        counterLabel.setBackground(new java.awt.Color(34, 94, 179));
        counterLabel.setFont(new java.awt.Font("Source Code Pro", 1, 24)); // NOI18N
        counterLabel.setForeground(new java.awt.Color(255, 255, 255));
        counterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        counterLabel.setText("0/100");
        counterLabel.setOpaque(true);
        jPanel1.add(counterLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 100, 32));

        saveButton.setBackground(new java.awt.Color(34, 94, 179));
        saveButton.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        saveButton.setText("CAPTURAR");
        saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jPanel1.add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 100, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 430));

        setSize(new java.awt.Dimension(649, 469));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        stopCamera();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Capture.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Capture().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel counterLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label_photo;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    class DaemonThread implements Runnable {
        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = label_photo.getGraphics();
                            Mat imageColor = new Mat();
                            imageColor = cameraImage;

                            Mat imageGray = new Mat();
                            cvtColor(imageColor, imageGray, COLOR_BGRA2GRAY);

                            RectVector detectedFaces = new RectVector();
                            cascade.detectMultiScale(imageColor, detectedFaces, 1.1, 1, 0, new Size(150, 150), new Size(500, 500));

                            for(int i = 0; i < detectedFaces.size(); i++){
                                Rect dadosFace = detectedFaces.get(0);
                                rectangle(imageColor, dadosFace, new Scalar(255, 255, 255, 5));
                                Mat face = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(face, face, new Size(160, 160));

                                if(saveButton.getModel().isPressed()) {

                                    for(sample=0; sample <= numSamples; sample++){
                                        String cropped = "C:\\photos\\person." + idPerson + "." + sample + ".jpg";
                                        imwrite(cropped, face);

                                        counterLabel.setText(String.valueOf(sample) + "/100");
                                        
                                        Thread.sleep(10);
                                    }
                                    if( sample > 100 ){
                                        generate();
                                        insertDB();
                                        System.out.println("Arquivo Gerado");
                                        stopCamera();
                                    }
                                }
                            }

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;

                            if(g.drawImage(buff, 0, 0, getWidth(), getHeight() - 90, 0, 0, buff.getWidth(), buff.getHeight(), null)){
                                if (runnable == false){
                                    System.out.println("Salve Foto");
                                    this.wait();
                                }
                            }
                        }

                    } catch (IOException ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao iniciar camera (IOEx)\n"+ex);
                    } catch (InterruptedException ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao iniciar camera (Interrupted)\n"+ex);
                    }
                }
            }
        }
    }
    
    public void generate(){
        File directory = new File("C:\\photos\\");
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg") || name.endsWith(".png");
            }
        };
        
        File[] files = directory.listFiles(filter);
        MatVector photos = new MatVector(files.length);
        Mat labels = new Mat(files.length, 1, CV_32SC1);
        IntBuffer labelsBuffer = labels.createBuffer();
        
        int counter = 0;
        for(File image : files){
            Mat photo = imread(image.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);
            int idP = Integer.parseInt(image.getName().split("\\.")[1]);
            opencv_imgproc.resize(photo, photo, new Size(160, 160));
            
            photos.put(counter, photo);
            labelsBuffer.put(counter, idP);
            counter++;
        }
        
        FaceRecognizer lbph = LBPHFaceRecognizer.create();
        lbph.train(photos, labels);
        lbph.save("C:\\photos\\classifierLBPH.yml");
    }
    
    public void insertDB(){
        ControlPerson cod = new ControlPerson();
        ModelPerson mod = new ModelPerson();
    
        mod.setNome(firstNamePerson);
        mod.setSobrenome(lastNamePerson);
        mod.setCargo(officePerson);
        mod.setData_nasc(nascDatePerson);
        
        cod.inserir(mod);
    }

    public void stopCamera(){
        myThread.runnable = false;
        webSource.release();
        dispose();
    }

    public void startCamera(){
        webSource = new VideoCapture(0);
        myThread = new Capture.DaemonThread();
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
    }
}
