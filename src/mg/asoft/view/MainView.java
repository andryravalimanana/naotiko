package mg.asoft.view;

import mg.asoft.database.Database;
import mg.asoft.database.KeywordDAO;
import mg.asoft.database.NaotyDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import mg.asoft.model.Keyword;
import mg.asoft.model.Naoty;
import mg.asoft.model.NaotyTableModel;

/**
 *
 * @author Andry
 */
public class MainView extends JFrame {

    private MenuBar menuBar;
    private ToolBar toolBar;
    private JPanel mainPanel;
    public static JLabel stateLabel = new JLabel("Naoty");
    private JTable table;
    private JScrollPane jScrollPane;
    private NaotyDAO naotyDAO;
    private JTextField searchTextField;
    private KeywordDAO keywordDao;

    String header[] = {"ID", "DATY", "ORA", "LOHATENY", "TENY FANALAHIDY"};

    public MainView(KeywordDAO keywordDAO) {
        this.keywordDao = keywordDAO;
        initComponet();
        initView();
        initTableau();

        // NORTH PANEL
        JPanel northPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        northPanel.add(menuBar);
        northPanel.add(toolBar);
        northPanel.add(searchTextField);

        // SOUTH PANEL
        JPanel southPanel = new JPanel(new FlowLayout(10));
        southPanel.add(stateLabel);
        southPanel.setBorder(BorderFactory.createLineBorder(Color.gray));

        // add north panel into the main panel
        mainPanel.add(northPanel, BorderLayout.NORTH);
        // add south panel into the main panel
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // add center panel into the main panel
        initTabPane();

        // add main panel into the frame
        this.add(mainPanel);
    }

    private void initView() {
        this.setTitle("NAOTIKO");
        this.setIconImage(new ImageIcon(MainView.class.getResource("/mg/asoft/img/logo.png")).getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setPreferredSize(new Dimension(1200, 600));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponet() {
        menuBar = new MenuBar();
        toolBar = new ToolBar();
        mainPanel = new JPanel(new BorderLayout(5, 5));
        stateLabel.setFont(new Font("Test", Font.CENTER_BASELINE, 10));
        naotyDAO = new NaotyDAO(Database.getInstance());
        searchTextField = new JTextField();
    }

    private void initTabPane() {

        JPanel tpPanel = new JPanel(new BorderLayout());

        tpPanel.setBorder(BorderFactory
                .createTitledBorder("Fitantanana"));

        JTabbedPane tb = new JTabbedPane();

        jScrollPane = new JScrollPane(table);
        tb.addTab("Naoty", jScrollPane);

        tpPanel.add(tb, BorderLayout.CENTER);
        //p.setBackground(Color.cyan);

        mainPanel.add(tpPanel, BorderLayout.CENTER);
    }

    private void initTableau() {
        ArrayList<Naoty> listNaoty = naotyDAO.findByKeyword("");
        Collections.reverse(listNaoty);
        NaotyTableModel ntm = new NaotyTableModel(listNaoty, header);

        this.table = new JTable(ntm) {
            //Implement table cell tool tips.
            public String getToolTipText(MouseEvent e) {
                String tip = "<html>";
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 3 | realColumnIndex == 4) {
                    TableModel model = getModel();
                    int id = (int) model.getValueAt(rowIndex, 0);
                    ArrayList<Keyword> keywords = keywordDao.findByNoteID(id);
                    for (Keyword keyword : keywords) {
                        tip = tip + "<p>" + keyword.getTitle() + "</p>";
                    }
                }
                tip = tip + "</html>";
                return tip;
            }

            //Implement table header tool tips. 
            protected JTableHeader createDefaultTableHeader() {
                return new JTableHeader(columnModel) {
                    public String getToolTipText(MouseEvent e) {
                        String tip = "Andrana";
                        java.awt.Point p = e.getPoint();
                        int index = columnModel.getColumnIndexAtX(p.x);
                        int realIndex = columnModel.getColumn(index).getModelIndex();
                        return header[realIndex];
                    }
                };
            }
        };

        this.table.setFillsViewportHeight(true);
        this.table.setRowHeight(40);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(500);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(300);

        Font headerFont = new Font("Titre", Font.BOLD, 14);
        this.table.getTableHeader().setFont(headerFont);
        this.table.getTableHeader().setForeground(Color.BLACK);

        this.table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        this.table.setPreferredScrollableViewportSize(new Dimension(400, 200));
        this.table.setSelectionBackground(new Color(0, 162, 232));
        this.table.setGridColor(Color.BLUE);

        // setUpSexeColumn(table, table.getColumnModel().getColumn(3));
//		this.table.setShowVerticalLines(false);
//		this.table.setShowHorizontalLines(false);
        this.table.setColumnSelectionAllowed(false);//on interdit la selection de colonne

        //			this.table.setBackground(Color.LIGHT_GRAY);
        this.table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    int row[];

                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        row = table.getSelectedRows();
                        if (e.getValueIsAdjusting()) {
                            return;
                        }
                        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                        if (lsm.isSelectionEmpty()) {
                            stateLabel.setText("");
                        } else {
                            int selectedRow = lsm.getMaxSelectionIndex();
                            stateLabel.setText("Fanondro - Isan'ny kazy voatondro: " + row.length
                                    + " Tondro Mitsivalana: " + selectedRow);
                        }
                    }
                });
    }

    public MenuBar getMenuBarView() {
        return menuBar;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public NaotyDAO getNaotyDAO() {
        return naotyDAO;
    }

    public void setNaotyDAO(NaotyDAO naotyDAO) {
        this.naotyDAO = naotyDAO;
    }

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public JTable getTable() {
        return table;

    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }

    public void setSearchTextField(JTextField searchTextField) {
        this.searchTextField = searchTextField;
    }

}
