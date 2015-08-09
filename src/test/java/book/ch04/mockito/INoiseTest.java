package book.ch04.mockito;

import book.ch04.jMock.INoise;
import book.ch04.jMock.NoiseChecker;
import book.ch04.jMock.SoundType;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Sangjun on 2015. 8. 9..
 */
public class INoiseTest {
    @Test
    public void testSound_MUTE() {
        final INoise noise = mock(INoise.class);
        final int MUTE = 0;
        when(noise.sound()).thenReturn(MUTE);

        NoiseChecker checker = new NoiseChecker();
        assertThat(checker.checkDecibel(noise), is(SoundType.MUTE));
        verify(noise).sound();
    }

    @Test
    public void testSound_NOISY() {
        final INoise noise = mock(INoise.class);
        final int NOISY_SOUND = 11;
        when(noise.sound()).thenReturn(NOISY_SOUND);

        NoiseChecker checker = new NoiseChecker();
        assertThat(checker.checkDecibel(noise), is(SoundType.NOISY));
        verify(noise, times(5)).sound();
    }
}
