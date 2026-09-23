package xyz.uniblood.arcanumunveiled.common.lib.utils;

public class Utils {
    public static final int[] colors = new int[]{15790320, 15435844, 12801229, 6719955, 14602026, 4312372, 14188952, 4408131, 10526880, 2651799, 8073150, 2437522, 5320730, 3887386, 11743532, 1973019};
    public static final String[] colorNames = new String[]{"White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black"};

    public static boolean getBit(int value, int bit) {
        return (value & 1 << bit) != 0;
    }

    public static int setBit(int value, int bit) {
        return value | 1 << bit;
    }

    public static int clearBit(int value, int bit) {
        return value & ~(1 << bit);
    }

    public static int toggleBit(int value, int bit) {
        return value ^ 1 << bit;
    }

    public static byte pack(boolean[] vals) {
        byte result = 0;
        for (boolean bit : vals) {
            result = (byte) (result << 1 | (bit ? 1 : 0) & 1);
        }
        return result;
    }

    public static boolean[] unpack(byte val) {
        boolean[] result = new boolean[8];
        for (int i = 0; i < 8; ++i) {
            result[i] = (byte) (val >> 7 - i & 1) == 1;
        }
        return result;
    }
}
