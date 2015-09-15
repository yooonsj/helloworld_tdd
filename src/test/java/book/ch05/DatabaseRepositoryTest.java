package book.ch05;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sangjun on 2015. 8. 14..
 */
public class DatabaseRepositoryTest {
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://52.69.174.164:3306/";
    private final String dbName = "shopdb";
    private final String id = "root";
    private final String password = "1234";

    private IDatabaseTester databaseTester;
    private String path = System.getProperty("user.dir") + "/src/test/resources/book/ch05";
    @Before
    public void setUp() throws Exception{
        databaseTester = new JdbcDatabaseTester(driver, url + dbName, id, password);

        try {
            IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(path, "seller.xml"));
            DatabaseOperation.CLEAN_INSERT.execute(databaseTester.getConnection(), dataSet);
        } finally {
            databaseTester.getConnection().close();
        }
    }

    @Test
    public void testAddNewSeller() throws Exception {
        Seller newSeller = new Seller("hssm", "이동욱", "scala@hssm.kr");
        Repository repository = new DatabaseRepository();
        repository.add(newSeller);

        IDataSet currentDBdataSet = databaseTester.getConnection().createDataSet();
        ITable actualTable = currentDBdataSet.getTable("SELLER");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File(path, "expected_seller.xml"));
        ITable expectedTable = expectedDataSet.getTable("SELLER");
        Assertion.assertEquals(new SortedTable(expectedTable), new SortedTable(actualTable));
    }

    @Test
    public void testFindById() throws Exception{

    }
}
