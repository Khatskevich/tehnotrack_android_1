package com.example.alexeykhatskevich.track_android_1;

import android.content.Context;

public class NumberToTextConverter {
    public static final int max_value = 1000000 - 1;

    public static String convert(Context context, int num) {
        if (num == 0) {
            return context.getString(R.string.description_null);
        }
        if (num > max_value){
            return context.getString(R.string.description_very_mytch);
        }
        String thousands = generateThousands(context, num);
        String lte_thousands = generateLessThenThousands(context, num);
        if (thousands.length() == 0 || lte_thousands.length() == 0)
            return thousands + lte_thousands;
        else
            return thousands + " " + lte_thousands;
    }

    static String generateLessThenThousands(Context context,int num) {
        num = getParticularExponent(num, 2) * 100 +
                getParticularExponent(num, 1) * 10 +
                getParticularExponent(num, 0);
        String ret_val = "";
        if (num == 0) {
            return ret_val;
        }
        ret_val = generate3exponent(context, num);
        if (getParticularExponent(num, 1) == 1) {
            return ret_val + " " + generateFrom10To19(context, num);
        } else {
            ret_val = ret_val + " " + generate2exponent(context, num) + " " + generate1exponent(context, num);
            return ret_val;
        }
    }

    static String generateThousands(Context context, int num) {
        int thousands_count = getParticularExponent(num, 5) * 100 +
                getParticularExponent(num, 4) * 10 +
                getParticularExponent(num, 3);
        String ret_val = "";
        if (thousands_count == 0) {
            return ret_val;
        }
        ret_val = generate3exponent(context, thousands_count);
        if (getParticularExponent(num, 4) == 1) {
            return ret_val + " " + generateFrom10To19(context, thousands_count) + " " + context.getString(R.string.description_thousands_after_5_6_7_8_9_russian);
        } else {
            ret_val = ret_val + " " + generate2exponent(context, thousands_count) + " " + generate1exponentFS(context, thousands_count);
            int exponent3 = getParticularExponent(num, 3);
            if (exponent3 == 1)
                ret_val = ret_val + " " + context.getString(R.string.description_thousands_after_1_russian);
            if (exponent3 == 2 || exponent3 == 3 || exponent3 == 4)
                ret_val = ret_val + " " + context.getString(R.string.description_thousands_after_2_3_4_russian);

            if (exponent3 >= 5)
                ret_val = ret_val + " " + context.getString(R.string.description_thousands_after_5_6_7_8_9_russian);
            return ret_val;
        }
    }

    private static String generate1exponent(Context context,int num) {
        int exponent = getParticularExponent(num, 0);
        switch (exponent) {
            case 0:
                return "";
            case 1:
                return context.getString(R.string.description_one);
            case 2:
                return context.getString(R.string.description_two);
            case 3:
                return context.getString(R.string.description_three);
            case 4:
                return context.getString(R.string.description_four);
            case 5:
                return context.getString(R.string.description_five);
            case 6:
                return context.getString(R.string.description_six);
            case 7:
                return context.getString(R.string.description_seven);
            case 8:
                return context.getString(R.string.description_eight);
            case 9:
                return context.getString(R.string.description_nine);
        }
        return "";
    }

    private static String generate1exponentFS(Context context,int num) {
        int exponent = getParticularExponent(num, 0);
        switch (exponent) {
            case 1:
                return context.getString(R.string.description_one_female);
            case 2:
                return context.getString(R.string.description_two_female);
            default:
                return generate1exponent(context, num);
        }
    }

    private static String generateFrom10To19(Context context,int num) {
        num = getParticularExponent(num, 1) * 10 +
                getParticularExponent(num, 0) * 1;
        switch (num) {
            case 10:
                return context.getString(R.string.description_ten);
            case 11:
                return context.getString(R.string.description_eleven);
            case 12:
                return context.getString(R.string.description_twelve);
            case 13:
                return context.getString(R.string.description_thirteen);
            case 14:
                return context.getString(R.string.description_fourteen);
            case 15:
                return context.getString(R.string.description_fifteen);
            case 16:
                return context.getString(R.string.description_sixteen);
            case 17:
                return context.getString(R.string.description_seventeen);
            case 18:
                return context.getString(R.string.description_eighteen);
            case 19:
                return context.getString(R.string.description_nineteen);
        }
        return "";
    }

    private static String generate2exponent(Context context,int num) {
        num = getParticularExponent(num, 1);
        switch (num) {
            case 2:
                return context.getString(R.string.description_twenty);
            case 3:
                return context.getString(R.string.description_thirty);
            case 4:
                return context.getString(R.string.description_forty);
            case 5:
                return context.getString(R.string.description_fifty);
            case 6:
                return context.getString(R.string.description_sixty);
            case 7:
                return context.getString(R.string.description_seventy);
            case 8:
                return context.getString(R.string.description_eighty);
            case 9:
                return context.getString(R.string.description_ninety);
        }
        return "";
    }

    private static String generate3exponent(Context context,int num) {
        int exponent = getParticularExponent(num, 2);
        switch (exponent) {
            case 1:
                return context.getString(R.string.description_one_hundred);
            case 2:
                return context.getString(R.string.description_two_hundred);
            case 3:
                return context.getString(R.string.description_three_hundred);
            case 4:
                return context.getString(R.string.description_four_hundred);
            case 5:
                return context.getString(R.string.description_five_hundred);
            case 6:
                return context.getString(R.string.description_six_hundred);
            case 7:
                return context.getString(R.string.description_seven_hundred);
            case 8:
                return context.getString(R.string.description_eight_hundred);
            case 9:
                return context.getString(R.string.description_nine_hundred);
        }
        return "";
    }

    private static int getParticularExponent(int num, int exponent) {
        int diexponented_val = 1;
        for (int i = 1; i <= exponent; i++) {
            diexponented_val *= 10;
        }
        return num / diexponented_val - (num / (diexponented_val * 10)) * 10;
    }
}
