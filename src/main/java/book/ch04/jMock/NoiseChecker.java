package book.ch04.jMock;

/**
 * Created by Sangjun on 2015. 8. 9..
 */

public class NoiseChecker {
    public SoundType checkDecibel(INoise noise) {
        if (noise.sound() == 0) {
            return SoundType.MUTE;
        } else if (noise.sound() > 0 && noise.sound() < 10) {
            return SoundType.SILENT;
        } else if (noise.sound() >= 10 && noise.sound() < 100) {
            return SoundType.NOISY;
        } else if (noise.sound() >= 100) {
            return SoundType.LOUD;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
