package book.ch06;

import book.ch05.DatabaseRepository;
import book.ch05.Repository;
import book.ch05.Seller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;

import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;

/**
 * Created by Sangjun on 2015. 8. 14..
 */

@RunWith(UnitilsJUnit4TestClassRunner.class)
@DataSet
public class DatabaseRepositoryTest {
    @Test
    public void testFindById() throws Exception {
        Repository repository = new DatabaseRepository();
        Seller actualSeller = repository.findById("horichoi");
        assertPropertyLenientEquals("id", "horichoi", actualSeller);
        assertPropertyLenientEquals("name", "최승호", actualSeller);
        assertPropertyLenientEquals("email", "megaseller@hatmail.com", actualSeller);
    }
}
