package app.Chiphr.helper;

import java.util.Calendar;


public class CurrentDate {

    public static Calendar calendar   = Calendar.getInstance(); // mengambil data kalender
    public static int year            = calendar.get(Calendar.YEAR); // mengambil data tahun ini
    public static int month           = calendar.get(Calendar.MONTH); // mengambil data bulan ini
    public static int day             = calendar.get(Calendar.DAY_OF_MONTH); // mengambil data hari ini

}
