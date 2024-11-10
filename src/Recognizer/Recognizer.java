package Recognizer;

import Capture.Capture;
import Util.ConnectDB;
import java.sql.Array;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

public class Recognizer extends javax.swing.JFrame {

    private Recognizer.DaemonThread myThread = null;
    
    private int consecutiveRecognitionTime = 0;
    private final int REQUIRED_RECOGNITION_TIME = 5;
    
    private boolean isRecognitionComplete = false;
    
    //JavaCV
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("C:\\photos\\haarcascade_frontalface_alt.xml");
    FaceRecognizer recognizer = LBPHFaceRecognizer.create();
    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();
    
    //Vars
    String root, firstNamePerson, lastNamePerson, officePerson, nascDatePerson;
    int idPerson;
    
    //Utils
    ConnectDB connect = new ConnectDB();

    public Recognizer() {
        initComponents();
        
        recognizer.read("C:\\photos\\classifierLBPH.yml");
        recognizer.setThreshold(80);
        
        startCamera();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label_photo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        label_office = new javax.swing.JLabel();
        label_name = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Identificar Pessoa");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_photo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(label_photo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 490, 380));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_office.setBackground(new java.awt.Color(34, 94, 179));
        label_office.setFont(new java.awt.Font("Source Code Pro", 1, 16)); // NOI18N
        label_office.setForeground(new java.awt.Color(255, 255, 255));
        label_office.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_office.setText("Cargo");
        label_office.setOpaque(true);
        jPanel2.add(label_office, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 490, 30));

        label_name.setBackground(new java.awt.Color(34, 94, 179));
        label_name.setFont(new java.awt.Font("Source Code Pro", 1, 16)); // NOI18N
        label_name.setForeground(new java.awt.Color(255, 255, 255));
        label_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_name.setText("Nome - Sobrenome");
        label_name.setOpaque(true);
        jPanel2.add(label_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 490, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 490, 90));

        jButton1.setBackground(new java.awt.Color(34, 94, 179));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setText("FECHAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 520, 100, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 580));

        setSize(new java.awt.Dimension(528, 558));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recognizer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recognizer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label_name;
    private javax.swing.JLabel label_office;
    private javax.swing.JLabel label_photo;
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

                            Mat imageGray = new Mat();
                            cvtColor(cameraImage, imageGray, COLOR_BGRA2GRAY);

                            RectVector detectedFaces = new RectVector();
                            // Ajuste de parâmetros para detectar faces com mais precisão
                            cascade.detectMultiScale(imageGray, detectedFaces, 1.2, 10, 0, new Size(30, 30), new Size(500, 500));


                            for (int i = 0; i < detectedFaces.size(); i++) {
                                Rect dadosFace = detectedFaces.get(i);
                                rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 0));
                                Mat faceCapturada = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(faceCapturada, faceCapturada, new Size(160, 160));

                                IntPointer rotulo = new IntPointer(1);
                                DoublePointer confidence = new DoublePointer(1);
                                recognizer.predict(faceCapturada, rotulo, confidence);
                                int prediction = rotulo.get(0);

                                // Ajuste do critério de confiança para evitar falsos positivos
                                if (confidence.get(0) < 50.0) {
                                    prediction = -1;
                                }

                                if (prediction != -1) {
                                    idPerson = prediction;
                                    consecutiveRecognitionTime++;

                                    if (consecutiveRecognitionTime >= REQUIRED_RECOGNITION_TIME && !isRecognitionComplete) {
                                        rec();
                                        OpenDados();
                                        isRecognitionComplete = true;
                                        consecutiveRecognitionTime = 0;
                                        stopCamera();
                                    }
                                } else {
                                    consecutiveRecognitionTime = 0;
                                }
                            }

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;

                            if (g.drawImage(buff, 0, 0, getWidth(), getHeight() - 100, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                if (!runnable) {
                                    System.out.println("Salve Foto");
                                    this.wait();
                                }
                            }
                        }
                    } catch (IOException | InterruptedException ex) {
                        System.err.println("Erro ao processar imagem ou thread interrompida: " + ex.getMessage());
                    }
                }
            }
        }
    }

    
    private void OpenDados() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                connect.conectar();
                String cargo = "Nível 1";
                String nomeCompleto = "Desconhecido";

                try {
                    String SQL = "SELECT * FROM person WHERE id = " + String.valueOf(idPerson);
                    connect.executaSQL(SQL);

                    if (connect.rs.next()) {
                        nomeCompleto = connect.rs.getString("first_name") + " " + connect.rs.getString("last_name");
                        cargo = connect.rs.getString("office");
                    }
                } catch (Exception e) {
                    
                } finally {
                    connect.desconectar();
                }

                
                final String finalNomeCompleto = nomeCompleto;
                final String finalCargo = cargo;

                SwingUtilities.invokeLater(() -> {
                    InfoWindow infoWindow = new InfoWindow(finalNomeCompleto, finalCargo);
                    infoWindow.setVisible(true);
                });

                return null;
            }
        };
        worker.execute();
    }
    
    private void rec() {
        SwingWorker worker = new SwingWorker(){
            @Override
            protected Object doInBackground() throws Exception {
                connect.conectar();
                try{
                    String SQL = "SELECT * FROM person WHERE id = "+ String.valueOf(idPerson);
                    connect.executaSQL(SQL);
                    while(connect.rs.next()){
                        label_name.setText(connect.rs.getString("first_name") +" "+ connect.rs.getString("last_name"));
                        label_office.setText(connect.rs.getString("office"));
                        
                        System.out.println("Person: "+ connect.rs.getString("id"));
                        
                        Array ident = connect.rs.getArray("first_name");
                        String[] person = (String[]) ident.getArray();
                        
                        for(int i=0; i< person.length; i++){
                            System.out.println(person[i]);
                            
                        }
                    }
                }
                catch (Exception e) {
                    
                }
                connect.desconectar();
                return null;
            }
        };
        worker.execute();
    }

    public void stopCamera(){
        myThread.runnable = false;
        webSource.release();
        dispose();
    }

    public void startCamera(){
        webSource = new VideoCapture(0);
        myThread = new Recognizer.DaemonThread();
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();
    }
}
