package app.Chiphr.helper;

import java.util.Calendar;



/**
 *dalam class currentdate ini
 */
public class CurrentDate {

    /**
     * mengambil data waktu dengan package kalender
     */
    public static Calendar calendar   = Calendar.getInstance();

    /**
     * mengambil data tahun menurut waktu sistem
     */
    public static int year            = calendar.get(Calendar.YEAR);

    /**
     * mengambil data bulan menurut waktu sistem
     */
    public static int month           = calendar.get(Calendar.MONTH);

    /**
     * mengambil data hari menurut waktu sistem
     */
    public static int day             = calendar.get(Calendar.DAY_OF_MONTH);

}
