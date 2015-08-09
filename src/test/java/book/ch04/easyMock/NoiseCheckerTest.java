package book.ch04.easyMock;

import book.ch04.jMock.*;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Sangjun on 2015. 8. 9..
 */
public class NoiseCheckerTest {
    @Test
    public void testSound_MUTE() {
        INoise noiseMock = createMock(INoise.class);
        final int MUTE = 0;
        expect(noiseMock.sound()).andReturn(MUTE);
        replay(noiseMock);

        NoiseChecker checker = new NoiseChecker();
        assertEquals(SoundType.MUTE, checker.checkDecibel(noiseMock));
        verify(noiseMock);
    }
}
