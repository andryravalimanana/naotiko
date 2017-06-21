package model;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class TestModel extends JFrame {

    private static final long serialVersionUID = 1L;

    public TestModel() {

        setTitle("JTable Training");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 300);
//		setVisible(true);

        //Les donn�es du tableau
        Object[][] datat = {
            {"Curie", new JButton("28 ans"), "1.80 m",
                new JComboBox<String>(new String[]{"FootBall", "BasketBall", "Volley"}), new Boolean(true)},
            {"Eddy", new JButton("28 ans"), "1.80 m", new JComboBox<String>(new String[]{"FootBall", "BasketBall", "Volley"}), new Boolean(false)},
            {"Edison", new JButton("24 ans"), "1.90 m", new JComboBox<String>(new String[]{"FootBall", "BasketBall", "Volley"}), new Boolean(false)},
            {"Flash", new JButton("32 ans"), "1.85 m", new JComboBox<String>(new String[]{"FootBall", "BasketBall", "Volley"}), new Boolean(true)}
        };

        //Les titres des colonnes
        String title[] = {"ID",
            "DATY",
            "ORA", "LOHATENY", "TENY FANALAHIDY"};
        ArrayList<Naoty> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
             data.add(new Naoty(i, "Java"+i, "Key"+i));
        }

        JTable tableau = new JTable(new NaotyTableModel(data, title));
        TableColumn sportColumn = tableau.getColumnModel().getColumn(3);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("FootBall");
        comboBox.addItem("BasketBall");
        comboBox.addItem("Volley");
        comboBox.addItem("neant");
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

        tableau.setDefaultRenderer(JComponent.class, new MyRenderer());
	    //Nous ajoutons notre tableau � notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        this.getContentPane().add(new JScrollPane(tableau));

    }

    public class MyRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            //Si la valeur de la cellule est un JButton, on transtype cette valeur
            if (value instanceof JButton) {
                return (JButton) value;
            } else if (value instanceof JComboBox) {
                return (JComboBox) value;
            } else {
                return this;
            }
        }
    }

    public class MyTableModel extends AbstractTableModel {

        private static final long serialVersionUID = 1L;
        Object[][] data;
        String[] title;

        public MyTableModel(Object[][] obj, String[] title) {
            this.data = obj;
            this.title = title;
        }

        @Override
        public int getColumnCount() {
            return this.title.length;
        }

        @Override
        public int getRowCount() {
            return this.data.length;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public String getColumnName(int column) {
            return this.title[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return data[0][columnIndex].getClass();
        }

    }

    public static void main(String[] args) {

        TestModel mainUI = new TestModel();

        mainUI.setVisible(true);

    }

}
