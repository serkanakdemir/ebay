package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang.ArrayUtils;


public class Sql {

    public Table init(InputStream csvContent) throws IOException {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(csvContent));

            String line = br.readLine();
            final String[] split = line.split(",");
            Table table = new Table(Arrays.asList(split));

            while ((line = br.readLine()) != null) {
                table.addRow(line.split(","));
            }
            return table;

        } catch (Exception ioe) {
            System.out.println("Exception while reading input " + ioe);
            throw ioe;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
        }
    }

    public Table orderByDesc(Table table, String columnName) {

        Table newTable = new Table(table.getColumnNames());
        Map<String, String[]> orderedMap = new TreeMap(Collections.reverseOrder());

        table.getAllRows().stream().forEach(row -> orderedMap.put(row[table.getColumnIndex(columnName)], row));
        orderedMap.values().stream().forEach(newTable::addRow);
        return newTable;
    }

    public Table join(Table left, Table right, String joinColumnTableLeft, String joinColumnTableRight) {

        final List<String> allColumnNames = left.getColumnNames();
        final List<String> rightColumnNames = right.getColumnNames();
        allColumnNames.addAll(rightColumnNames);

        Table newTable = new Table(allColumnNames);

        final int leftColumnIndex = left.getColumnIndex(joinColumnTableLeft);
        final int rightColumnIndex = right.getColumnIndex(joinColumnTableRight);

        left.getAllRows().stream().forEach(leftRow -> right.getAllRows().stream()
            .filter(rightRow -> leftRow[leftColumnIndex].equals(rightRow[rightColumnIndex]))
            .forEach(rightRow -> {
                newTable.addRow((String[]) ArrayUtils.addAll(leftRow, rightRow));
            }));
        return newTable;
    }
}
