package mg.asoft.view;

import javax.swing.JLabel;
import javax.swing.JTextField;
import mg.asoft.model.Naoty;

/**
 *
 * @author Andry
 */
public class EditNoteDialogue extends javax.swing.JPanel {

    private Naoty naoty;

    public EditNoteDialogue(Naoty naoty) {
        initComponents();
        this.naoty = naoty;
        loadData();
    }

    public Naoty getNaoty() {
        return naoty;
    }

    public void setNaoty(Naoty naoty) {
        this.naoty = naoty;
    }

    public JLabel getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(JLabel dateLabel) {
        this.dateLabel = dateLabel;
    }

    public JLabel getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(JLabel idLabel) {
        this.idLabel = idLabel;
    }

    public JTextField getTitleTextField1() {
        return titleTexteField;
    }

    public void setTitleTextField1(JTextField jTextField1) {
        this.titleTexteField = jTextField1;
    }

    public JLabel getTimeLable() {
        return timeLable;
    }

    public void setTimeLable(JLabel timeLable) {
        this.timeLable = timeLable;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        timeLable = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        titleTexteField = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Naoty"));

        jLabel1.setText("Lohateny:");

        jLabel2.setText("Daty:");

        jLabel3.setText("Ora:");

        jLabel4.setText("ID:");

        titleTexteField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleTexteFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addGap(102, 102, 102)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idLabel)
                    .addComponent(timeLable)
                    .addComponent(dateLabel)
                    .addComponent(titleTexteField, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(titleTexteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(timeLable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(idLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void titleTexteFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleTexteFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titleTexteFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel timeLable;
    private javax.swing.JTextField titleTexteField;
    // End of variables declaration//GEN-END:variables

    private void loadData() {
        titleTexteField.setText(naoty.getTitle());
        dateLabel.setText(naoty.getDate().toString());
        timeLable.setText(naoty.getTime().toString());
        idLabel.setText(String.valueOf(naoty.getId()));
    }
}
