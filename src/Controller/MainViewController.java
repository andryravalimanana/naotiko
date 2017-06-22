package Controller;

import Database.KeywordDAO;
import Database.NaotyDAO;
import File.FileManager;
import View.MainPanel;
import View.MainView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Keyword;
import model.Naoty;
import model.NaotyTableModel;

/**
 *
 * @author Andry
 */
public class MainViewController {

    private MainView mainView;
    private NaotyDAO naotyDAO;
    private KeywordDAO keywordDAO;
    private NaotyTableModel ntm;

    public MainViewController(MainView mainView, NaotyDAO n, KeywordDAO k) {
        this.mainView = mainView;
        this.naotyDAO = n;
        this.keywordDAO = k;
        ntm = (NaotyTableModel) mainView.getTable().getModel();
        mainView.setNaotyDAO(naotyDAO);
        event();
    }

    private void event() {
        // TOOLBAR EVENT
        mainView.getToolBar().getNewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titre = JOptionPane.showInputDialog(mainView, "Ampidiro ny naoty vaovao.", "NAOTY VAOVAO", JOptionPane.INFORMATION_MESSAGE);
                if (titre != null) {
                    insertNewNaoty(titre);
                }

            }
        });

        // OPEN FILE
        mainView.getToolBar().getAboutButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        // MENUBAR EVENT
        mainView.getMenuBarView().getNewMenuItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 String titre = JOptionPane.showInputDialog(mainView, "Ampidiro ny naoty vaovao.", "NAOTY VAOVAO", JOptionPane.INFORMATION_MESSAGE);
                if (titre != null) {
                    insertNewNaoty(titre);
                }
            }
        });

        mainView.getMenuBarView().getExitMenuItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // SEARCH
        mainView.getSearchTextField().getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = mainView.getSearchTextField().getText();
                if (key.equals("")) {
                    ArrayList<Naoty> naotys = naotyDAO.findByKeyword("");
                    ntm.upDateTable(naotys);
                } else {
                    ArrayList<Naoty> naotys = naotyDAO.findByKeyword(key);
                    ArrayList<Keyword> keywords = keywordDAO.findByKeyword(key);
                    for (Keyword keyword : keywords) {
                        naotys.add(naotyDAO.findById(keyword.getIdNaoty()));
                    }
                    ntm.upDateTable(naotys);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = mainView.getSearchTextField().getText();
                if (key.equals("")) {
                    ArrayList<Naoty> naotys = naotyDAO.findByKeyword("");
                    ntm.upDateTable(naotys);
                } else {
                    ArrayList<Naoty> naotys = naotyDAO.findByKeyword(key);
                    ArrayList<Keyword> keywords = keywordDAO.findByKeyword(key);
                    for (Keyword keyword : keywords) {
                        naotys.add(naotyDAO.findById(keyword.getIdNaoty()));
                    }
                    ntm.upDateTable(naotys);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = mainView.getSearchTextField().getText();
                if (key.equals("")) {
                    ArrayList<Naoty> naotys = naotyDAO.findByKeyword("");
                    ntm.upDateTable(naotys);
                } else {
                    ArrayList<Naoty> naotys = naotyDAO.findByKeyword(key);
                    ArrayList<Keyword> keywords = keywordDAO.findByKeyword(key);
                    for (Keyword keyword : keywords) {
                        naotys.add(naotyDAO.findById(keyword.getIdNaoty()));
                    }
                    ntm.upDateTable(naotys);
                }
            }
        });

        //
    }

    private void insertNewNaoty(String titre) {
        titre.replace("'", "");
        titre.replace("\"", "");
        Naoty n = new Naoty(titre);
        naotyDAO.insert(n);
        n.setId(naotyDAO.nextNoteId() - 1);
        FileManager.createFile(n);
        ArrayList<Naoty> naotys = naotyDAO.findByKeyword("");
        ntm.upDateTable(naotys);
        FileManager.openFile(n);
        System.out.println("Tafiditra ny naoty vaovao.");
    }

    private void openFile() {
        int[] rows = mainView.getTable().getSelectedRows();
        Object[] rowData = new Object[6];
        Object[] data = new Object[6];
        if (rows.length == 1) {
            for (int i = 0; i < 6; i++) {
                rowData[i] = ((NaotyTableModel) mainView.getTable().getModel()).getValueAt(rows[0], i);
            }
            int id = (int) rowData[0];
            System.out.println("eto izy: " + id);
            FileManager.openFile(new Naoty(id, null, null));
        }
    }

}
