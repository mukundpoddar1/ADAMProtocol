package adamInterface;

import adamProtocol.BloodCounts;
import adamProtocol.Dose;
import adamProtocol.Patient;
import adamProtocol.Prediction;
import adamProtocol.Predictor;
import adamProtocol.exceptions.IndivisibleDoseException;
import adamProtocol.exceptions.OutOfBoundsDoseException;
import java.awt.Dimension;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class MainWindow extends javax.swing.JFrame {
    private Patient patient;
    private String patientFilePath;
    private Prediction prediction;
    private final SimpleDateFormat dateFormatter;
    private javax.swing.ImageIcon imageIcon;
    
    public MainWindow() {
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        initComponents();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane1.getHorizontalScrollBar().setUnitIncrement(16);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectorPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        patientIdText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientPanel = new javax.swing.JPanel();
        lastDosePanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        currentVisitPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        neutrophilText = new javax.swing.JTextField();
        plateletText = new javax.swing.JTextField();
        suggestDoseButton = new javax.swing.JButton();
        dateChooser = new com.toedter.calendar.JDateChooser();
        hundredDosePanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        highestDosePanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        predictionPane = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        graphLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Enter Patient Id");

        jButton1.setText("Select");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectPatient(evt);
            }
        });

        javax.swing.GroupLayout selectorPanelLayout = new javax.swing.GroupLayout(selectorPanel);
        selectorPanel.setLayout(selectorPanelLayout);
        selectorPanelLayout.setHorizontalGroup(
            selectorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(patientIdText, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addContainerGap(336, Short.MAX_VALUE))
        );
        selectorPanelLayout.setVerticalGroup(
            selectorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectorPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(selectorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(patientIdText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(31, 31, 31))
        );

        jScrollPane1.setPreferredSize(new java.awt.Dimension(852, 702));

        patientPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        lastDosePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Last Visit"));

        jLabel5.setText("6MP");

        jLabel6.setText("MTX");

        jTextField4.setEditable(false);

        jTextField5.setEditable(false);

        javax.swing.GroupLayout lastDosePanelLayout = new javax.swing.GroupLayout(lastDosePanel);
        lastDosePanel.setLayout(lastDosePanelLayout);
        lastDosePanelLayout.setHorizontalGroup(
            lastDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastDosePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(lastDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(lastDosePanelLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lastDosePanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        lastDosePanelLayout.setVerticalGroup(
            lastDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastDosePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lastDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lastDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        currentVisitPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Current Visit"));
        currentVisitPanel.setInheritsPopupMenu(true);

        jLabel3.setText("Enter Neutrohil Count (in unit)");

        jLabel4.setText("Enter Platelet Count (in unit)");

        suggestDoseButton.setText("Suggest Dose");
        suggestDoseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suggestDoseButtonPressed(evt);
            }
        });

        javax.swing.GroupLayout currentVisitPanelLayout = new javax.swing.GroupLayout(currentVisitPanel);
        currentVisitPanel.setLayout(currentVisitPanelLayout);
        currentVisitPanelLayout.setHorizontalGroup(
            currentVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentVisitPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(currentVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suggestDoseButton)
                    .addGroup(currentVisitPanelLayout.createSequentialGroup()
                        .addGroup(currentVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(currentVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(neutrophilText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plateletText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        currentVisitPanelLayout.setVerticalGroup(
            currentVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentVisitPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(currentVisitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(currentVisitPanelLayout.createSequentialGroup()
                        .addComponent(neutrophilText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(plateletText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(currentVisitPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(suggestDoseButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        hundredDosePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Hundred Percent Dose"));
        hundredDosePanel.setInheritsPopupMenu(true);

        jLabel9.setText("6MP");

        jLabel10.setText("MTX");

        jTextField8.setEditable(false);

        jTextField9.setEditable(false);

        javax.swing.GroupLayout hundredDosePanelLayout = new javax.swing.GroupLayout(hundredDosePanel);
        hundredDosePanel.setLayout(hundredDosePanelLayout);
        hundredDosePanelLayout.setHorizontalGroup(
            hundredDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hundredDosePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hundredDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hundredDosePanelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(hundredDosePanelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        hundredDosePanelLayout.setVerticalGroup(
            hundredDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hundredDosePanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(hundredDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(hundredDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        highestDosePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Highest Tolerated Dose"));

        jLabel7.setText("6MP");

        jLabel8.setText("MTX");

        jTextField6.setEditable(false);

        jTextField7.setEditable(false);

        javax.swing.GroupLayout highestDosePanelLayout = new javax.swing.GroupLayout(highestDosePanel);
        highestDosePanel.setLayout(highestDosePanelLayout);
        highestDosePanelLayout.setHorizontalGroup(
            highestDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(highestDosePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(highestDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(highestDosePanelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField7))
                    .addGroup(highestDosePanelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        highestDosePanelLayout.setVerticalGroup(
            highestDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(highestDosePanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(highestDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(highestDosePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        predictionPane.setInheritsPopupMenu(true);

        jLabel12.setText("Suggested 6MP");

        jTextField10.setEditable(false);

        jLabel13.setText("Suggested MTX");

        jTextField11.setEditable(false);

        jLabel2.setText("Reason for Change");

        jTextField1.setEditable(false);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout predictionPaneLayout = new javax.swing.GroupLayout(predictionPane);
        predictionPane.setLayout(predictionPaneLayout);
        predictionPaneLayout.setHorizontalGroup(
            predictionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(predictionPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(predictionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(predictionPaneLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(predictionPaneLayout.createSequentialGroup()
                        .addGroup(predictionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(predictionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(predictionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        predictionPaneLayout.setVerticalGroup(
            predictionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, predictionPaneLayout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addGroup(predictionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(predictionPaneLayout.createSequentialGroup()
                        .addGroup(predictionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(predictionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(predictionPaneLayout.createSequentialGroup()
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)))
                .addGap(16, 16, 16)
                .addGroup(predictionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        graphLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Previous Visit Graph"));
        graphLabel.setName(""); // NOI18N

        javax.swing.GroupLayout patientPanelLayout = new javax.swing.GroupLayout(patientPanel);
        patientPanel.setLayout(patientPanelLayout);
        patientPanelLayout.setHorizontalGroup(
            patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(patientPanelLayout.createSequentialGroup()
                        .addComponent(lastDosePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hundredDosePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(highestDosePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(graphLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(patientPanelLayout.createSequentialGroup()
                        .addComponent(currentVisitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(predictionPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        patientPanelLayout.setVerticalGroup(
            patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, patientPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lastDosePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(highestDosePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hundredDosePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(graphLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(patientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currentVisitPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(predictionPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(254, 254, 254))
        );

        jScrollPane1.setViewportView(patientPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(selectorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void graphVisit(String patId,Patient pat) throws IOException, InterruptedException {
        Dose dosage= pat.getHundredPercentDose();
        double hundredMtx=dosage.getMtx();
        double hundredSmp=dosage.getSmp();
        ProcessBuilder pythonPB=new ProcessBuilder("python", "./graph_visit.py", patId,Double.toString(hundredMtx),Double.toString(hundredSmp)).inheritIO();
        Process pythonProcess=pythonPB.start();
        pythonProcess.waitFor();
    }
    
    private void selectPatient(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectPatient
        String patId = patientIdText.getText();

        patientFilePath = "./Patients/" + patId + ".csv";
        try {
            patient = PatientInformationLoader.createPatient(patientFilePath);
            graphVisit(patId,patient);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(patientPanel, "Cannot find patient with Id: "+patId, "Patient Not Found", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        catch (InterruptedException e){
            JOptionPane.showMessageDialog(patientPanel, "Something went wrong, please try again.","System Interrupted", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        clearTextFields(patientPanel);
        populateFields(patient);
    }//GEN-LAST:event_selectPatient

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        jTextField10.setEditable(true);
        jTextField11.setEditable(true);
        jTextField1.setEditable(true);
    }//GEN-LAST:event_editButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            Dose newDose = new Dose(Double.parseDouble(jTextField11.getText()), Double.parseDouble(jTextField10.getText()));
            patient.setCurrentDose(newDose);
            saveVisitToFile();
        } catch (IndivisibleDoseException | OutOfBoundsDoseException ex) {
            JOptionPane.showMessageDialog(patientPanel, "Please check the dose suggested: "+ex.getMessage(), "Erroneous Dose Prescribed", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void suggestDoseButtonPressed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suggestDoseButtonPressed
        BloodCounts bloodCount = new BloodCounts(Double.parseDouble(neutrophilText.getText())*BloodCounts.NEUTROPHIL_UNIT,
            Double.parseDouble(plateletText.getText())*BloodCounts.PLATELET_UNIT);
        patient.addVisit(dateChooser.getCalendar().getTime(), bloodCount);
        prediction = new Predictor().predictFor(patient);
        jTextField10.setText(Double.toString(prediction.getDose().getSmp()));
        jTextField11.setText(Double.toString(prediction.getDose().getMtx()));
    }//GEN-LAST:event_suggestDoseButtonPressed

    public void clearTextFields(java.awt.Container container) {
        graphLabel.setIcon(null);
        if(imageIcon!=null)
            imageIcon.getImage().flush();
        graphLabel.setIcon(imageIcon);
        for (java.awt.Component c : container.getComponents()) {
            if (c instanceof javax.swing.JTextField) {
                javax.swing.JTextField f = (javax.swing.JTextField) c;
                f.setText("");
            } else if (c instanceof java.awt.Container) {
                clearTextFields((java.awt.Container) c);
            }
        }
    }
    
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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel currentVisitPanel;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel graphLabel;
    private javax.swing.JPanel highestDosePanel;
    private javax.swing.JPanel hundredDosePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel lastDosePanel;
    private javax.swing.JTextField neutrophilText;
    private javax.swing.JTextField patientIdText;
    private javax.swing.JPanel patientPanel;
    private javax.swing.JTextField plateletText;
    private javax.swing.JPanel predictionPane;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel selectorPanel;
    private javax.swing.JButton suggestDoseButton;
    // End of variables declaration//GEN-END:variables

    
    private void populateFields(Patient patient) {
        if (patient.getNumberOfVisits() == 0) {
            return;
        }
        jLabel11.setText(dateFormatter.format(patient.getLastVisitDate()) + ", Condition: "+patient.getBloodCountsAt(-1).condition);
        jTextField4.setText(Double.toString(patient.getDoseAt(-1).getSmp()));
        jTextField5.setText(Double.toString(patient.getDoseAt(-1).getMtx()));
        jTextField8.setText(Double.toString(patient.getHundredPercentDose().getSmp()));
        jTextField9.setText(Double.toString(patient.getHundredPercentDose().getMtx()));
        if (patient.getToleratedDose() != null) {
            jTextField6.setText(Double.toString(patient.getToleratedDose().getSmp()));
            jTextField7.setText(Double.toString(patient.getToleratedDose().getMtx()));
        }
        graphLabel.setPreferredSize(new Dimension(500, 350));
        imageIcon = new javax.swing.ImageIcon("./Patients/"+patient.getId()+".jpg");
        graphLabel.setIcon(imageIcon);
    }

    private void saveVisitToFile() {
        try {
            FileWriter outFileWriter = new FileWriter(patientFilePath, true);
            String newEntry = String.join(",", String.valueOf(patient.getCycle()), 
                            dateFormatter.format(patient.getCurrentDate().getTime()),
                            String.valueOf(patient.getBloodCounts().neutrophilCount/BloodCounts.NEUTROPHIL_UNIT),
                            String.valueOf(patient.getBloodCounts().plateletCount/BloodCounts.PLATELET_UNIT), 
                            String.valueOf(patient.getDoseAt(-1).getSmp()), String.valueOf(patient.getDoseAt(-1).getMtx()),
                            String.valueOf(prediction.getDose().getSmp()), String.valueOf(prediction.getDose().getMtx()),
                            jTextField1.getText());
            outFileWriter.append(newEntry+'\n');
            outFileWriter.close();
        } catch (IOException e) {
        }
    }
}
