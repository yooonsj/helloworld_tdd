package book.ch05;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.File;

/**
 * Created by Sangjun on 2015. 8. 14..
 */
public class RepositoryTest {
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://52.69.174.164:3306/";
    private final String dbName = "shopdb";
    private final String id = "root";
    private final String password = "1234";

    private IDatabaseTester databaseTester;
    private IDatabaseConnection connection;

    @Before
    public void setUp() throws Exception{
        databaseTester = new JdbcDatabaseTester(driver, url + dbName, id, password);
        connection = databaseTester.getConnection();
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("seller.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
    }

    @After
    public void tearDown() throws Exception{
        this.connection.close();
    }

    @Test
    public void testAddNewSeller() throws Exception {
        Seller newSeller = new Seller("hssm", "이동욱", "scala@hssm.kr");
        Repository repository = new DatabaseRepository();
        repository.add(newSeller);
        Seller sellerFromRepository = repository.findById("hssm");

        assertEquals(newSeller.getId(), sellerFromRepository.getId());
        assertEquals(newSeller.getName(), sellerFromRepository.getName());
        assertEquals(newSeller.getEmail(), sellerFromRepository.getEmail());
    }

    @Test
    public void testFindById() throws Exception{

    }
}
