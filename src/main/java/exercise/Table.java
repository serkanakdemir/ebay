package exercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.ToString;

@ToString
public class Table {

    private final List<String> columnNames = new ArrayList<>();
    private List<String[]> rowList = new ArrayList<>();

    public Table(List<String> columnNames) {
        columnNames.stream().forEach(this.columnNames::add);
    }

    public void addRow(String[] row) {
        if (row.length == columnNames.size()) {
            String[] targetRow = new String[row.length];
            System.arraycopy(row, 0, targetRow, 0, row.length);
            rowList.add(targetRow);
        } else {
            throw new RuntimeException("Out of bounds exception");
        }
    }

    public List<String> getColumnNames() {
        return columnNames.stream()
            .collect(Collectors.toList());
    }

    public int getColumnIndex(String columnName) {
        final int i = columnNames.indexOf(columnName);
        if (i < 0) {
            throw new RuntimeException("Index Not Found");
        }
        return i;
    }

    public List<String[]> getAllRows() {
        List<String[]> rowListClone = new ArrayList<>();
        Iterator<String[]> iterator = rowList.iterator();
        while (iterator.hasNext()) {
            rowListClone.add(iterator.next().clone());
        }
        return rowListClone;
    }

}
