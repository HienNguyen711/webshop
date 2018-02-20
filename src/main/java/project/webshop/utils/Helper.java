package project.webshop.utils;

import com.sun.source.tree.ParameterizedTypeTree;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    // year - month
    public static final String FORMAT = "YY/MM";
    public static final SimpleDateFormat format = new SimpleDateFormat(FORMAT);

    public static final String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@"
        + "((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z" + "\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    /**
     * Credit Card expiration does not need a day, it is used 1 as default
     * business value.
     */
    public static final int DAY_OF_MONTH = 1;

    public static Date instanceLocalDate(final String expireYY, final String expireMM){
        return java.sql.Date
            .valueOf(LocalDate.of(Integer.parseInt(expireYY), Integer.parseInt(expireMM), DAY_OF_MONTH));
    }

    // get format date
    public static String getModelAndViewFormatDate(final Date today) {

        Instant instant = Instant.ofEpochMilli(today.getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = localDateTime.toLocalDate();

        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
		localDate.format(formatter);*/

        return localDate.getYear() + " / " + localDate.getMonthValue();
    }

    // check if valid email
    public static boolean isValidEmail(String email) {

        Pattern p = Pattern.compile(emailPattern);
        Matcher matcher = p.matcher(email);
        return matcher.matches();
    }

}
