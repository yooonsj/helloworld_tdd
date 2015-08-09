package book.ch04.jMock;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Sangjun on 2015. 8. 9..
 */

@RunWith(JMock.class)
public class INoiseTest {
    Mockery context = new JUnit4Mockery();

    @Test
    public void testSound_MUTE() {
        final INoise noise = context.mock(INoise.class);
        final int MUTE = 0;
        context.checking(new Expectations(){
            {
                allowing(noise).sound();
                will(returnValue(MUTE));
            }
        });

        NoiseChecker checker = new NoiseChecker();
        assertThat(checker.checkDecibel(noise), is(SoundType.MUTE));
    }
}
