package br.com.deliverit.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Datas {

    public static Integer diffInDays(Date inicio, Date fim) {
        try {
            LocalDateTime LocalDataEntrada = new SimpleDateFormat("yyyy-MM-dd").parse(dataParaString(inicio, "yyyy-MM-dd")).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime LocalDataSaida = new SimpleDateFormat("yyyy-MM-dd").parse(dataParaString(fim, "yyyy-MM-dd")).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return (int) ChronoUnit.DAYS.between(LocalDataEntrada, LocalDataSaida);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String dataParaString(Date date, String formato) {
        SimpleDateFormat dt1 = new SimpleDateFormat(formato);
        return dt1.format(date);
    }

}