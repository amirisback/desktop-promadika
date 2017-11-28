/*
 * Muhammad Faisal Amir
 * id.amirisback.bandung
 * Copyright 2017
 */
package promadika.calon;
import com.sun.glass.events.KeyEvent;
import promadika.Index;
import promadika.function;
import promadika.connection;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.FileInputStream;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author Faisal Amir
 */

public class Calon extends javax.swing.JFrame {
    connection conn = new connection();
    function func = new function();
    private String sql = "";
    private String id_calon, csuami_nik, csuami_nama, csuami_ttl, csuami_alamat, csuami_foto, cistri_nik, cistri_nama, cistri_ttl, cistri_alamat, cistri_foto;
    private String path_save_foto_csuami, path_save_foto_cistri, nama_file_csuami, nama_file_cistri, newfolder;
    private String id_random;
    private File foto_pria, foto_wanita;
    private FileInputStream inputFoto;
    private int lebar_cistri, tinggi_cistri, lebar_csuami, tinggi_csuami;
    
    /**
     * Creates new form Calon
     */

    public Calon() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        conn.ConnectToDB();
        id_random = "IDC" + func.getRandomChar();
        txt_id_nikah.setText(id_random);
    }
    
    public void saveFoto(){
        try {
            //create folder baru
            newfolder = conn.getFolder_Foto_Calon() + "/"+id_calon;
            File folder = new File(newfolder);
            if(!folder.exists()){
                folder.mkdir();
            }
            //menyimpan foto suami
            path_save_foto_csuami = newfolder + "/" + csuami_foto;
            BufferedImage save_csuami = ImageIO.read(foto_pria);
            ImageIO.write(save_csuami, "jpg", new File(path_save_foto_csuami));
            
            //menyimpan foto istri
            path_save_foto_cistri = newfolder + "/" + cistri_foto;
            BufferedImage save_cistri = ImageIO.read(foto_wanita);
            ImageIO.write(save_cistri, "jpg" , new File(path_save_foto_cistri));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void resizeImageCistri(){
        try {
            //Resize Foto Cistri
            BufferedImage imgUkuranAsli_cistri = ImageIO.read(foto_wanita);
            lebar_cistri = panel_foto_cistri.getWidth();
            tinggi_cistri = panel_foto_cistri.getHeight();
            BufferedImage imgResize_cistri = new BufferedImage(lebar_cistri, tinggi_cistri, BufferedImage.TYPE_INT_ARGB);
            Graphics2D imgTampil_cistri = imgResize_cistri.createGraphics();
            imgTampil_cistri.drawImage(imgUkuranAsli_cistri, 0, 0, lebar_cistri, tinggi_cistri, null);
            imgTampil_cistri.dispose();
            ImageIcon ImgOut_cistri = new ImageIcon(imgResize_cistri);
            foto_cistri.setIcon(ImgOut_cistri);
            foto_cistri.setText("");
        } catch (IOException ex) {
            Logger.getLogger(Calon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void resizeImageCsuami(){
        try {
            //Resize Foto Csuami
            BufferedImage imgUkuranAsli_csuami = ImageIO.read(foto_pria);
            lebar_csuami = panel_foto_csuami.getWidth();
            tinggi_csuami = panel_foto_csuami.getHeight();
            BufferedImage imgResize_csuami = new BufferedImage(lebar_csuami, tinggi_csuami, BufferedImage.TYPE_INT_ARGB);
            Graphics2D imgTampil_csuami= imgResize_csuami.createGraphics();
            imgTampil_csuami.drawImage(imgUkuranAsli_csuami, 0, 0, lebar_csuami, tinggi_csuami, null);
            imgTampil_csuami.dispose();
            ImageIcon ImgOut_csuami = new ImageIcon(imgResize_csuami);
            foto_csuami.setIcon(ImgOut_csuami);
            foto_csuami.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
         
    }
    
    public boolean isEmpty(){
        return 
                txt_id_nikah.getText().equals("") ||
                txt_csuami_nik.getText().equals("") ||
                txt_csuami_nama.getText().equals("") ||
                txt_csuami_ttl.getText().equals("") ||
                txt_csuami_alamat.getText().equals("") ||
                txt_cistri_nik.getText().equals("") ||
                txt_cistri_nama.getText().equals("") ||
                txt_cistri_ttl.getText().equals("") ||
                txt_cistri_alamat.getText().equals("");    
    }
    
    public void clear(){
        txt_id_nikah.setText("");
        txt_csuami_nik.setText("");
        txt_csuami_nama.setText("");
        txt_csuami_ttl.setText("");
        txt_csuami_alamat.setText("");      
        txt_cistri_nik.setText("");
        txt_cistri_nama.setText("");
        txt_cistri_ttl.setText("");
        txt_cistri_alamat.setText("");
        foto_csuami.setText("Foto 3x4");
        foto_csuami.setIcon(null);
        foto_cistri.setText("Foto 3x4");
        foto_cistri.setIcon(null);
        csuami_path_file.setText("");
        cistri_path_file.setText("");
    }

    public void insertDatatoDB() {
        try {
            id_calon = String.valueOf(txt_id_nikah.getText());
            csuami_nik = String.valueOf(txt_csuami_nik.getText());
            csuami_nama = String.valueOf(txt_csuami_nama.getText());
            csuami_ttl = String.valueOf(txt_csuami_ttl.getText());
            csuami_alamat = String.valueOf(txt_csuami_alamat.getText());
            csuami_foto = "FTCLN_" + func.getRandomChar() + "_" + id_calon + "S.jpg";
            cistri_nik = String.valueOf(txt_cistri_nik.getText());
            cistri_nama = String.valueOf(txt_cistri_nama.getText());
            cistri_ttl = String.valueOf(txt_cistri_ttl.getText());
            cistri_alamat = String.valueOf(txt_cistri_alamat.getText());
            cistri_foto = "FTCLN_" + func.getRandomChar() + "_" + id_calon + "I.jpg";

            if (isEmpty()) {
                txt_show_mesg.setText("DATA TIDAK BOLEH KOSONG");
                txt_show_mesg.setForeground(Color.orange);
            } else {
                sql = "insert into data_calon_nikah(id_calon, nik_csuami, nama_csuami, "
                        + "ttl_csuami, alamat_csuami, foto_csuami, nik_cistri, "
                        + "nama_cistri, ttl_cistri, alamat_cistri, foto_cistri)"
                        + " VALUES ('" + id_calon + "','" + csuami_nik + "','" + csuami_nama + "',"
                        + "'" + csuami_ttl + "','" + csuami_alamat + "','" + csuami_foto + "',"
                        + "'" + cistri_nik + "','" + cistri_nama + "','" + cistri_ttl + "',"
                        + "'" + cistri_alamat + "','" + cistri_foto + "')";
                conn.setStatement(conn.getConnect().createStatement());
                conn.getStatement().execute(sql);
                saveFoto();
                clear();
                txt_show_mesg.setText("DATA BERHASIL DI TAMBAH");
                txt_show_mesg.setForeground(Color.white);
            }
        } catch (Exception e) {
            txt_show_mesg.setText("GAGAL");
            txt_show_mesg.setForeground(Color.orange);
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

        b_index = new javax.swing.JScrollPane();
        index = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_id_nikah = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_csuami_nik = new javax.swing.JTextField();
        txt_csuami_nama = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_csuami_ttl = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_csuami_alamat = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        upload_fotoP = new javax.swing.JButton();
        panel_foto_csuami = new javax.swing.JPanel();
        foto_csuami = new javax.swing.JLabel();
        csuami_path_file = new javax.swing.JLabel();
        btn_tambah_calon_nikah = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_cistri_nik = new javax.swing.JTextField();
        txt_cistri_nama = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_cistri_ttl = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_cistri_alamat = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        upload_fotoW = new javax.swing.JButton();
        panel_foto_cistri = new javax.swing.JPanel();
        foto_cistri = new javax.swing.JLabel();
        cistri_path_file = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txt_show_mesg = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PROMADIKA - Program Informasi Pendataan Data Nikah");
        setAlwaysOnTop(true);
        setIconImages(null);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        b_index.setPreferredSize(new java.awt.Dimension(1026, 1000));

        index.setBackground(new java.awt.Color(46, 125, 50));
        index.setPreferredSize(new java.awt.Dimension(1024, 950));

        jPanel2.setBackground(new java.awt.Color(165, 214, 167));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconProperties/header.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1031, 1031, 1031))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(27, 94, 32));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(102, 187, 106));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconProperties/Calon_miniIcon.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(165, 214, 167));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconProperties/icons8_Contacts_25px.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Daftar Calon Nikah");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(232, 245, 233));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconProperties/icons8_Plus_25px.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Tambah Calon Nikah");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(165, 214, 167));
        jPanel8.setPreferredSize(new java.awt.Dimension(171, 47));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Menu Pilihan");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconProperties/icons8_Menu_2_25px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(52, 52, 52))
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tambah Data Calon Nikah");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel5.setBackground(new java.awt.Color(200, 230, 201));

        jLabel8.setFont(new java.awt.Font("Arial", 3, 15)); // NOI18N
        jLabel8.setText("ID CALON NIKAH    :");

        txt_id_nikah.setBackground(new java.awt.Color(200, 230, 201));
        txt_id_nikah.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        txt_id_nikah.setBorder(null);
        txt_id_nikah.setMargin(new java.awt.Insets(2, 10, 2, 10));
        txt_id_nikah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_nikahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel8)
                .addGap(35, 35, 35)
                .addComponent(txt_id_nikah, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_id_nikah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(200, 230, 201));

        jLabel9.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jLabel9.setText(" DATA CALON MEMPELAI PRIA");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N
        jLabel10.setText("_________________________________________________________________________________________________________________________________________________");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("NIK");

        txt_csuami_nik.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_csuami_nik.setMargin(new java.awt.Insets(10, 10, 10, 2));
        txt_csuami_nik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_csuami_nikKeyPressed(evt);
            }
        });

        txt_csuami_nama.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_csuami_nama.setMargin(new java.awt.Insets(10, 10, 10, 2));
        txt_csuami_nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_csuami_namaKeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Nama");

        txt_csuami_ttl.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_csuami_ttl.setMargin(new java.awt.Insets(10, 10, 10, 2));
        txt_csuami_ttl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_csuami_ttlKeyPressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("TTL");

        txt_csuami_alamat.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_csuami_alamat.setMargin(new java.awt.Insets(10, 10, 10, 2));
        txt_csuami_alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_csuami_alamatKeyPressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Alamat");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Foto");

        upload_fotoP.setText("Upload Foto");
        upload_fotoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upload_fotoPActionPerformed(evt);
            }
        });

        panel_foto_csuami.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        foto_csuami.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foto_csuami.setText("Foto 3x4");

        javax.swing.GroupLayout panel_foto_csuamiLayout = new javax.swing.GroupLayout(panel_foto_csuami);
        panel_foto_csuami.setLayout(panel_foto_csuamiLayout);
        panel_foto_csuamiLayout.setHorizontalGroup(
            panel_foto_csuamiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(foto_csuami, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
        );
        panel_foto_csuamiLayout.setVerticalGroup(
            panel_foto_csuamiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(foto_csuami, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txt_csuami_nama))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txt_csuami_ttl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txt_csuami_alamat))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txt_csuami_nik))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel_foto_csuami, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(upload_fotoP)
                                .addGap(18, 18, 18)
                                .addComponent(csuami_path_file, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(30, 30, 30))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_csuami_nik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_csuami_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txt_csuami_ttl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txt_csuami_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel19)
                    .addComponent(upload_fotoP)
                    .addComponent(csuami_path_file, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panel_foto_csuami, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        btn_tambah_calon_nikah.setBackground(new java.awt.Color(0, 102, 102));
        btn_tambah_calon_nikah.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btn_tambah_calon_nikah.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambah_calon_nikah.setText("TAMBAH CALON NIKAH");
        btn_tambah_calon_nikah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambah_calon_nikahActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(200, 230, 201));

        jLabel12.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jLabel12.setText(" DATA CALON MEMPELAI WANITA");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N
        jLabel13.setText("_________________________________________________________________________________________________________________________________________________");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setText("NIK");

        txt_cistri_nik.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_cistri_nik.setMargin(new java.awt.Insets(10, 10, 10, 2));
        txt_cistri_nik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cistri_nikKeyPressed(evt);
            }
        });

        txt_cistri_nama.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_cistri_nama.setMargin(new java.awt.Insets(10, 10, 10, 2));
        txt_cistri_nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cistri_namaKeyPressed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setText("Nama");

        txt_cistri_ttl.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_cistri_ttl.setMargin(new java.awt.Insets(10, 10, 10, 2));
        txt_cistri_ttl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cistri_ttlKeyPressed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel22.setText("TTL");

        txt_cistri_alamat.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txt_cistri_alamat.setMargin(new java.awt.Insets(10, 10, 10, 2));
        txt_cistri_alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cistri_alamatKeyPressed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel23.setText("Alamat");

        jLabel24.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel24.setText("Foto");

        upload_fotoW.setText("Upload Foto");
        upload_fotoW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upload_fotoWActionPerformed(evt);
            }
        });

        panel_foto_cistri.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        foto_cistri.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foto_cistri.setText("Foto 3x4");

        javax.swing.GroupLayout panel_foto_cistriLayout = new javax.swing.GroupLayout(panel_foto_cistri);
        panel_foto_cistri.setLayout(panel_foto_cistriLayout);
        panel_foto_cistriLayout.setHorizontalGroup(
            panel_foto_cistriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(foto_cistri, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
        );
        panel_foto_cistriLayout.setVerticalGroup(
            panel_foto_cistriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(foto_cistri, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txt_cistri_nama))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txt_cistri_ttl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txt_cistri_alamat))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txt_cistri_nik))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panel_foto_cistri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(upload_fotoW)
                                        .addGap(18, 18, 18)
                                        .addComponent(cistri_path_file, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txt_cistri_nik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txt_cistri_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txt_cistri_ttl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txt_cistri_alamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel24)
                    .addComponent(upload_fotoW)
                    .addComponent(cistri_path_file, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panel_foto_cistri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Copyright - Promadika 2017  ||  Telkom University");

        jLabel26.setFont(new java.awt.Font("Arial", 2, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("_______________________________________________________________________________________________________________________________________________________________________");

        txt_show_mesg.setBackground(new java.awt.Color(46, 125, 50));
        txt_show_mesg.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        txt_show_mesg.setForeground(new java.awt.Color(255, 255, 255));
        txt_show_mesg.setBorder(null);

        javax.swing.GroupLayout indexLayout = new javax.swing.GroupLayout(index);
        index.setLayout(indexLayout);
        indexLayout.setHorizontalGroup(
            indexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(indexLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(indexLayout.createSequentialGroup()
                .addGroup(indexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(indexLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(indexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(indexLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(indexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(indexLayout.createSequentialGroup()
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(indexLayout.createSequentialGroup()
                                        .addComponent(btn_tambah_calon_nikah)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_show_mesg, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, indexLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(indexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(indexLayout.createSequentialGroup()
                        .addGap(362, 362, 362)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        indexLayout.setVerticalGroup(
            indexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(indexLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(indexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(indexLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(indexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(indexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_tambah_calon_nikah, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_show_mesg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );

        b_index.setViewportView(index);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b_index, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b_index, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        Index index = new Index();
        index.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void txt_id_nikahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_nikahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_nikahActionPerformed

    private void btn_tambah_calon_nikahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambah_calon_nikahActionPerformed
        // TODO add your handling code here:
        insertDatatoDB();
    }//GEN-LAST:event_btn_tambah_calon_nikahActionPerformed

    private void upload_fotoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upload_fotoPActionPerformed
        // TODO add your handling code here:
        JFileChooser cari_foto_csuami = new JFileChooser();
        cari_foto_csuami.showOpenDialog(b_index);
        foto_pria = cari_foto_csuami.getSelectedFile();
        nama_file_csuami = foto_pria.getName();
        csuami_path_file.setText(nama_file_csuami);    
        resizeImageCsuami();

        
    }//GEN-LAST:event_upload_fotoPActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        Calon_List calonLs = new Calon_List();
        calonLs.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void upload_fotoWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upload_fotoWActionPerformed
        // TODO add your handling code here:
        JFileChooser cari_foto_cistri = new JFileChooser();
        cari_foto_cistri.showOpenDialog(b_index);
        foto_wanita = cari_foto_cistri.getSelectedFile();
        nama_file_cistri = foto_wanita.getName();
        cistri_path_file.setText(nama_file_cistri);
        resizeImageCistri();
        
    }//GEN-LAST:event_upload_fotoWActionPerformed

    private void txt_csuami_nikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_csuami_nikKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            insertDatatoDB();
        }
    }//GEN-LAST:event_txt_csuami_nikKeyPressed

    private void txt_csuami_namaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_csuami_namaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            insertDatatoDB();
        }
    }//GEN-LAST:event_txt_csuami_namaKeyPressed

    private void txt_csuami_ttlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_csuami_ttlKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            insertDatatoDB();
        }
    }//GEN-LAST:event_txt_csuami_ttlKeyPressed

    private void txt_csuami_alamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_csuami_alamatKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            insertDatatoDB();
        }
    }//GEN-LAST:event_txt_csuami_alamatKeyPressed

    private void txt_cistri_nikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cistri_nikKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            insertDatatoDB();
        }
    }//GEN-LAST:event_txt_cistri_nikKeyPressed

    private void txt_cistri_namaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cistri_namaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            insertDatatoDB();
        }
    }//GEN-LAST:event_txt_cistri_namaKeyPressed

    private void txt_cistri_ttlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cistri_ttlKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            insertDatatoDB();
        }
    }//GEN-LAST:event_txt_cistri_ttlKeyPressed

    private void txt_cistri_alamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cistri_alamatKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            insertDatatoDB();
        }
    }//GEN-LAST:event_txt_cistri_alamatKeyPressed

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
            java.util.logging.Logger.getLogger(Calon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane b_index;
    private javax.swing.JButton btn_tambah_calon_nikah;
    private javax.swing.JLabel cistri_path_file;
    private javax.swing.JLabel csuami_path_file;
    private javax.swing.JLabel foto_cistri;
    private javax.swing.JLabel foto_csuami;
    private javax.swing.JPanel index;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel panel_foto_cistri;
    private javax.swing.JPanel panel_foto_csuami;
    private javax.swing.JTextField txt_cistri_alamat;
    private javax.swing.JTextField txt_cistri_nama;
    private javax.swing.JTextField txt_cistri_nik;
    private javax.swing.JTextField txt_cistri_ttl;
    private javax.swing.JTextField txt_csuami_alamat;
    private javax.swing.JTextField txt_csuami_nama;
    private javax.swing.JTextField txt_csuami_nik;
    private javax.swing.JTextField txt_csuami_ttl;
    private javax.swing.JTextField txt_id_nikah;
    private javax.swing.JTextField txt_show_mesg;
    private javax.swing.JButton upload_fotoP;
    private javax.swing.JButton upload_fotoW;
    // End of variables declaration//GEN-END:variables
}
