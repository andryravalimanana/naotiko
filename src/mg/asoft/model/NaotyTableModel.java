package mg.asoft.model;

import mg.asoft.dateAndTime.Date;
import mg.asoft.dateAndTime.Time;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class NaotyTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    String[] header;
    ArrayList<Naoty> naoty;

    public NaotyTableModel(ArrayList<Naoty> naoty, String[] header) {

//			System.out.println("constructor EtdModel");
        this.header = header;
        this.naoty = naoty;
    }

    @Override
    public int getColumnCount() {
//			System.out.println("ClolumnCount");
        return this.header.length;
    }

    @Override
    public int getRowCount() {
//			System.out.println("RowCount");
        // TODO Auto-generated method stub
        return this.naoty.size();
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object obj = null;
        switch (columnIndex) {
            case 0:
                obj = this.naoty.get(rowIndex).getId();
                break;
            case 1:
                obj = this.naoty.get(rowIndex).getDate();
                break;
            case 2:
                obj = this.naoty.get(rowIndex).getTime();
                break;
            case 3:
                obj = this.naoty.get(rowIndex).getTitle();
                break;
            case 4:
                obj = this.naoty.get(rowIndex).getFirstKeyword();
                break;
        }
        return obj;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

//			System.out.println("getColumnClass");
        Class<?> clas = null;
        switch (columnIndex) {
            case 0:
                clas = this.naoty.get(0).getClass();
                break;
            case 1:
                clas = this.naoty.get(0).getDate().getClass();
                break;
            case 2:
                clas = this.naoty.get(0).getTime().getClass();
                break;
            case 3:
                clas = this.naoty.get(0).getTitle().getClass();
                break;
            case 4:
                clas = this.naoty.get(0).getFirstKeyword().getClass();
                break;
        }
        return clas;
    }

    @Override
    public String getColumnName(int column) {
        return this.header[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
        return false;
    }
    
    

    public boolean addRow(Naoty naoty) {
        this.naoty.add(naoty);
        fireTableDataChanged();
        return true;
    }

    public boolean upDateTable(ArrayList<Naoty> naotyList) {
        this.naoty = naotyList;
        fireTableDataChanged();
        return true;
    }

    public boolean deleteRow(int rowIndex) {
        this.naoty.remove(rowIndex);
        fireTableDataChanged();
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

//			System.out.println("setValueAt");
        switch (columnIndex) {
            case 0:
                this.naoty.get(rowIndex)
                        .setId((int) aValue);
                break;
            case 1:
                this.naoty.get(rowIndex)
                        .setDate((Date) aValue);
                break;
            case 2:
                this.naoty.get(rowIndex).setTime((Time) aValue);
                break;
            case 3:
                this.naoty.get(rowIndex).setTitle((String) aValue);
                break;

            case 4:
                this.naoty.get(rowIndex).setFirstKeyword((String) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
