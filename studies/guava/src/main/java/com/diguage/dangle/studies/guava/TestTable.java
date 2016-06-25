package com.diguage.dangle.studies.guava;

import com.google.common.collect.HashBasedTable;
import org.junit.Test;

import java.util.Map;

/**
 * Created by diguage on 16/6/17.
 */
public class TestTable {
    @Test
    public void test() {
        HashBasedTable<Integer, Integer, String> table =
                HashBasedTable.create();
        table.put(1, 1, "Rook");
        table.put(1, 2, "Knight");
        table.put(1, 3, "Bishop");
        boolean contains11 = table.contains(1, 1);
        boolean containColumn2 = table.containsColumn(2);
        boolean containsRow1 = table.containsRow(1);
        boolean containsRook = table.containsValue("Rook");
        table.remove(1, 3);
        table.get(3, 4);
        System.out.println(table);

        Map<Integer,String> columnMap = table.column(1);
        Map<Integer,String> rowMap = table.row(2);

        System.out.println(columnMap);
        System.out.println(rowMap);
    }
}
