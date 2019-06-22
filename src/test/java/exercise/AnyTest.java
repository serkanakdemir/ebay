package exercise;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

public class AnyTest {

    private static final String NAME = "NAME";

    @Test
    public void shouldInitializeTableForGivenInput() throws IOException {

        Sql sql = new Sql();
        File initialFile = new File("src/main/resources/users.csv");
        InputStream targetStream = new FileInputStream(initialFile);

        final Table table = sql.init(targetStream);
        Assert.assertEquals(5, table.getAllRows().size());
        Assert.assertEquals(Arrays.asList("USER_ID", "NAME", "EMAIL"), table.getColumnNames());

        Assert.assertEquals("2", table.getAllRows().get(0)[0]);
        Assert.assertEquals("manuel", table.getAllRows().get(0)[1]);
        Assert.assertEquals("manuel@foo.de", table.getAllRows().get(0)[2]);


    }

    @Test
    public void shouldOrderTableForGivenColumn() throws IOException {

        Sql sql = new Sql();
        File initialFile = new File("src/main/resources/users.csv");
        InputStream targetStream = new FileInputStream(initialFile);

        final Table init = sql.init(targetStream);
        final Table table = sql.orderByDesc(init, NAME);

        Assert.assertEquals(Arrays.asList("swen", "paul", "manuel", "lydia", "andre"),
            table.getAllRows().stream().map(row -> row[1]).collect(
                Collectors.toList()));


    }


    @Test
    public void shouldJoinTableForGivenInputs() throws Exception {

        Sql sql = new Sql();
        File users = new File("src/main/resources/users.csv");
        InputStream usersStream = new FileInputStream(users);

        File purchases = new File("src/main/resources/purchases.csv");
        InputStream purchasesStream = new FileInputStream(purchases);

        final Table usersTable = sql.init(usersStream);
        final Table purchasesTable = sql.init(purchasesStream);

        final Table table = sql.join(usersTable, purchasesTable, "USER_ID", "USER_ID");

        Assert.assertEquals(8, table.getAllRows().size());
        Assert.assertEquals(Arrays.asList("USER_ID", "NAME", "EMAIL", "AD_ID", "TITLE", "USER_ID"),
            table.getColumnNames());

        Assert.assertEquals("2", table.getAllRows().get(0)[0]);
        Assert.assertEquals("manuel", table.getAllRows().get(0)[1]);
        Assert.assertEquals("manuel@foo.de", table.getAllRows().get(0)[2]);
        Assert.assertEquals("4", table.getAllRows().get(0)[3]);
        Assert.assertEquals("guitar-1", table.getAllRows().get(0)[4]);
        Assert.assertEquals("2", table.getAllRows().get(0)[5]);


    }


}
