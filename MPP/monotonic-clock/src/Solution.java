import org.jetbrains.annotations.NotNull;

/**
 * @author Korotkov Arsentiy
 */
public class Solution implements MonotonicClock {
    private final RegularInt c11 = new RegularInt(0);
    private final RegularInt c12 = new RegularInt(0);
    private final RegularInt c13 = new RegularInt(0);

    private final RegularInt c21 = new RegularInt(0);
    private final RegularInt c22 = new RegularInt(0);

    @Override
    public void write(@NotNull Time time) {
        // write right-to-left
        c21.setValue(time.getD1());
        c22.setValue(time.getD2());

        c13.setValue(time.getD3());
        c12.setValue(c22.getValue());
        c11.setValue(c21.getValue());
    }

    @NotNull
    @Override
    public Time read() {
        // read left-to-right
        final int r11 = c11.getValue();
        final int r12 = c12.getValue();
        final int r13 = c13.getValue();

        final int r22 = c22.getValue();
        final int r23 = c21.getValue();

        if (r11 == r23) {
            if (r12 == r22) {
                return new Time(r11, r12, r13);
            } else {
                return new Time(r11, r22, 0);
            }
        } else {
            return new Time(r23, 0, 0);
        }
    }
}

