package mg.asoft.controller;

import mg.asoft.database.KeywordDAO;
import mg.asoft.database.NaotyDAO;
import mg.asoft.event.FileParser;
import mg.asoft.file.FileManager;
import mg.asoft.view.MainView;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import mg.asoft.model.Keyword;
import mg.asoft.model.Naoty;
import mg.asoft.model.NaotyTableModel;
import mg.asoft.structure.Config;
import mg.asoft.view.ConfigPanel;
import mg.asoft.view.EditNoteDialogue;

/**
 *
 * @author Andry
 */
public class MainViewController {

    private MainView mainView;
    private NaotyDAO naotyDAO;
    private KeywordDAO keywordDAO;
    private NaotyTableModel ntm;
    private FileParser fileParser;

    public MainViewController(MainView mainView, NaotyDAO n, KeywordDAO k) {
        this.mainView = mainView;
        this.naotyDAO = n;
        this.keywordDAO = k;
        fileParser = new FileParser(keywordDAO);
        ntm = (NaotyTableModel) mainView.getTable().getModel();
        mainView.setNaotyDAO(naotyDAO);
        event();
    }

    private void event() {
        // ================================= TOOLBAR EVENT ============================================
        // ==============  New button event ================
        mainView.getToolBar().getNewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titre = JOptionPane.showInputDialog(mainView, "Ampidiro ny naoty vaovao.", "NAOTY VAOVAO", JOptionPane.INFORMATION_MESSAGE);
                if (titre != null) {
                    insertNewNaoty(titre);
                }

            }
        });

        // ================ Config button event ==============
        mainView.getToolBar().getConfigButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigPanel cp = new ConfigPanel();
                int response = JOptionPane.showConfirmDialog(mainView, cp, "Kaonfigy", JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (response == JOptionPane.OK_OPTION) {
                    try {
                        Config.defaultEditor = cp.getEditorPathTextField().getText();
                        Config.pathNoteFile = cp.getNotePathTextEditor().getText();
                        Config.updateConfig();
                    } catch (IOException ex) {
                        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        // ==================== Edit note event =======================
        mainView.getToolBar().getEditNoteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = getSelectedRow();
                Naoty naotyToEdit = naotyDAO.findById(id);
                EditNoteDialogue end = new EditNoteDialogue(naotyToEdit);
                int response = JOptionPane.showConfirmDialog(mainView, end, "Hanova Naoty", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (response == JOptionPane.OK_OPTION) {
                    naotyToEdit.setTitle(end.getTitleTextField1().getText());
                    naotyDAO.update(naotyToEdit);
                    ArrayList<Naoty> naotys = naotyDAO.findByKeyword("");
                    Collections.reverse(naotys);
                    ntm.upDateTable(naotys);
                }
            }
        });
        //========================================== TABLE EVENT ==========================================
        mainView.getTable().addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                int column = table.columnAtPoint(p);
                if (e.getClickCount() == 2) {
                    int id = (int) ntm.getValueAt(row, 0);
                    System.out.println("Open file Id: " + id);
                    FileManager.openFile(new Naoty(id, null, null), fileParser);
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        // ============================================= MENUBAR EVENT ===========================================
        // ================== event menu item ====================
        mainView.getMenuBarView().getNewMenuItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String titre = JOptionPane.showInputDialog(mainView, "Ampidiro ny naoty vaovao.", "NAOTY VAOVAO", JOptionPane.INFORMATION_MESSAGE);
                if (titre != null) {
                    insertNewNaoty(titre);
                }
            }
        });
        // ====================== Event config menu item ==========================
        mainView.getMenuBarView().getConfigMenuItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigPanel cp = new ConfigPanel();
                int response = JOptionPane.showConfirmDialog(mainView, cp, "Kaonfigy", JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (response == JOptionPane.OK_OPTION) {
                    try {
                        Config.defaultEditor = cp.getEditorPathTextField().getText();
                        Config.pathNoteFile = cp.getNotePathTextEditor().getText();
                        Config.updateConfig();
                    } catch (IOException ex) {
                        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        // ======================== Exit note menu item ==========================
        mainView.getMenuBarView().getEditNoteMenuItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        // ========================= Event exit menu item ============================= 
        mainView.getMenuBarView().getExitMenuItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // ======================================= SEARCH ============================================
        mainView.getSearchTextField().getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = mainView.getSearchTextField().getText();
                if (key.equals("")) {
                    ArrayList<Naoty> naotys = naotyDAO.findByKeyword("");
                    Collections.reverse(naotys);
                    ntm.upDateTable(naotys);
                } else {
                    ArrayList<Naoty> naotys = naotyDAO.findByKeyword(key);
                    ArrayList<Keyword> keywords = keywordDAO.findByKeyword(key);
                    for (Keyword keyword : keywords) {
                        Naoty n = naotyDAO.findById(keyword.getIdNaoty());
                        if (!keyword.getTitle().equals(n.getTitle())) {
                            n.setFirstKeyword(keyword.getTitle());
                            naotys.add(n);
                        }
                    }
                    ntm.upDateTable(naotys);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = mainView.getSearchTextField().getText();
                if (key.equals("")) {
                    ArrayList<Naoty> naotys = naotyDAO.findByKeyword("");
                    Collections.reverse(naotys);
                    ntm.upDateTable(naotys);
                } else {
                    ArrayList<Naoty> naotys = naotyDAO.findByKeyword(key);
                    ArrayList<Keyword> keywords = keywordDAO.findByKeyword(key);
                    for (Keyword keyword : keywords) {
                        Naoty n = naotyDAO.findById(keyword.getIdNaoty());
                        if (!keyword.getTitle().equals(n.getTitle())) {
                            n.setFirstKeyword(keyword.getTitle());
                            naotys.add(n);
                        }
                    }
                    ntm.upDateTable(naotys);
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    // ============================================  MEHOTDE ================================================= 
    // ++++++++++++++++++++ INSERT NOTE +++++++++++++++++++++++
    private void insertNewNaoty(String titre) {
        Naoty n = new Naoty(titre);
        naotyDAO.insert(n);
        n.setId(naotyDAO.nextNoteId() - 1);
        keywordDAO.insert(new Keyword(titre, naotyDAO.nextNoteId() - 1));
        FileManager.createFile(n);
        ArrayList<Naoty> naotys = naotyDAO.findByKeyword("");
        Collections.reverse(naotys);
        ntm.upDateTable(naotys);
        FileManager.openFile(n, fileParser);
        System.out.println("Tafiditra ny naoty vaovao.");
    }

    //++++++++++++++++++++++++ GET SELECTED ROW ++++++++++++++++++++++++++++++
    private int getSelectedRow() {
        int[] rows = mainView.getTable().getSelectedRows();
        //mainView.getTable().;
        Object[] rowData = new Object[6];
        Object[] data = new Object[6];
        if (rows.length == 1) {
            for (int i = 0; i < 6; i++) {
                rowData[i] = ((NaotyTableModel) mainView.getTable().getModel()).getValueAt(rows[0], i);
            }
        }
        return (int) rowData[0];
    }
}
