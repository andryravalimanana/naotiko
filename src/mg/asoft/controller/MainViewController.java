package mg.asoft.controller;

import java.awt.Font;
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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import mg.asoft.dateAndTime.Date;
import mg.asoft.model.Keyword;
import mg.asoft.model.Naoty;
import mg.asoft.model.NaotyTableModel;
import mg.asoft.structure.Config;
import mg.asoft.view.ConfigPanel;
import mg.asoft.view.EditNoteDialogue;
import static java.nio.file.StandardCopyOption.*;
import javax.swing.Action;
import javax.swing.ImageIcon;
import mg.asoft.database.Database;
import mg.asoft.dateAndTime.Time;
import mg.asoft.view.ChartViewFrame;
import org.jfree.chart.ChartPanel;

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

        // ========================= Developper button event ================================
        mainView.getToolBar().getAboutButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                aboutDeveloppers();
            }
        });

        // ======================== Statistique button event ===============================
        mainView.getToolBar().getStatisticButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new ChartViewFrame(naotyDAO);
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

        // ======================== Event export menu item ============================
        mainView.getMenuBarView().getExportMenuItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    exportDatabase();
                } catch (IOException ex) {
                    Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        // ========================= About menu item  ===========================
        mainView.getMenuBarView().getNaotikoMenuItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                aboutApplication();
            }
        });

        // ========================= About developpers =============================
        mainView.getMenuBarView().getDeveloppersMenuItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                aboutDeveloppers();
            }
        });

        // ========================= Event exit menu item ============================= 
        mainView.getMenuBarView().getExitMenuItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // ========================= Event statistika menu item ================================
        mainView.getMenuBarView().getStatisticMenuItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new ChartViewFrame(naotyDAO);
            }
        });

        mainView.getMenuBarView().getClearDatabaseItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(mainView, "Ho Voafafa daholo ny tahiry rehetra!\nHo tohizana?", "Hanadio", JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.OK_OPTION) {
                    naotyDAO.clearDataBase();
                    Naoty naoty = new Naoty("TONGA SOA, MISAOTRA ANAO MAMPIASA NY NAOTIKO.");
                    insertNewNaoty(naoty.getTitle());
                    ntm.upDateTable(naotyDAO.findByKeyword(""));
                }
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

    private void exportDatabase() throws IOException {
        Date date = Date.getNow();
        Time time = Time.getNow();
        String[] t = time.toString().split(":");
        String timeFomated = t[0] + "h" + t[1];
        String destinationPath = getDirectoryChooser("Toerana asina azy");
        if (!destinationPath.equals("NOSELECTION")) {
            Files.copy(new File(Config.pathDatabase+"Naoty.db").toPath(), new File(destinationPath + "Naoty_" + date.toString() + "_" + timeFomated + ".db").toPath(), StandardCopyOption.REPLACE_EXISTING);
            JOptionPane.showMessageDialog(mainView, "Voaondrana ny naoty", "Fanondranana naoty", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String getDirectoryChooser(String title) {
        JFileChooser chooser = new JFileChooser();
        String path = "";
        chooser.setCurrentDirectory(new java.io.File("System.getProperty(\"user.home\") + \"\\\\Documents\\\\\""));
        chooser.setDialogTitle(title);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(mainView) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    + chooser.getCurrentDirectory());
            path = chooser.getSelectedFile().getPath();
            System.out.println("getSelectedFile() : "
                    + chooser.getSelectedFile());
            return path + "\\";
        } else {
            System.out.println("No Selection ");
            return "NOSELECTION";
        }
    }

    private void aboutApplication() {
        JOptionPane.showMessageDialog(mainView, "Naotiko 1.0.1\nCopyright Asoft © 2017\nhttp://www.asoft.mg", "Naotiko", JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(getClass().getResource("/mg/asoft/img/logo.png")));
    }

    private void aboutDeveloppers() {
        JOptionPane.showMessageDialog(mainView, "RAVALIMANANA Andry Niaina\n+261 32 29 746 76\nandry-niaina@yandex.com\nfb/andry.ravalimanana", "Namorona", JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(getClass().getResource("/mg/asoft/img/Andry.png")));
    }
}
