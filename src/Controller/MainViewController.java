package Controller;

import Database.KeywordDAO;
import Database.NaotyDAO;
import Event.FileParser;
import File.FileManager;
import View.MainView;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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

        mainView.getTable().addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                int column = table.columnAtPoint(p);
                if (e.getClickCount() == 2) {
                    int id = (int) ntm.getValueAt(row, 0);
                    System.out.println("Open file Id: "+id);
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

    private void insertNewNaoty(String titre) {
        Naoty n = new Naoty(titre);
        naotyDAO.insert(n);
        n.setId(naotyDAO.nextNoteId() - 1);
        keywordDAO.insert(new Keyword(titre, naotyDAO.nextNoteId() - 1));
        FileManager.createFile(n);
        ArrayList<Naoty> naotys = naotyDAO.findByKeyword("");
        ntm.upDateTable(naotys);
        FileManager.openFile(n, fileParser);
        System.out.println("Tafiditra ny naoty vaovao.");
    }

    private void openFile() {
        int[] rows = mainView.getTable().getSelectedRows();
        //mainView.getTable().;
        Object[] rowData = new Object[6];
        Object[] data = new Object[6];
        if (rows.length == 1) {
            for (int i = 0; i < 6; i++) {
                rowData[i] = ((NaotyTableModel) mainView.getTable().getModel()).getValueAt(rows[0], i);
            }
            int id = (int) rowData[0];
            FileManager.openFile(new Naoty(id, null, null), fileParser);
        }
    }

}
