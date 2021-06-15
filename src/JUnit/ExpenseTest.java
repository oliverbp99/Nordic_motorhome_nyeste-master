import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {
    int base_cost = 500;
    int drop_off_extra = 50;
    int pick_up_extra = 50;
    String rental_start_date = "2019-01-02";
    String rental_end_date = "2020-02-01";
    int km_start = 2333;
    int km_end = 4100;
    int repair_fee = 100;
    double fuel_level = 0.7;
    String season = "middle";
    double full_price = 0;

    @Test
    public void test() throws ParseException {
        double x = base_cost;
        double z = drop_off_extra / 0.7;
        double f = pick_up_extra / 0.7;
        x = x + f + z;

        LocalDate localDate1 = LocalDate.parse(rental_start_date);
        LocalDate localDate2 = LocalDate.parse(rental_end_date);
        long noOfDaysDifference = ChronoUnit.DAYS.between(localDate1, localDate2);

        int kmDif = km_start - km_end;
        int maxKm = (int) (kmDif / noOfDaysDifference);
        if(maxKm > 400) {
            x = x + maxKm - 400;
        }
        x = x + repair_fee;

        if(fuel_level <= 0.5) {
            x = x + 70;
        }
        if (season.equals("middle")) {
            x *= 1.30;
        } else if (season.equals("peak")) {
            x = x * 1.6;
        }
        full_price = x;
        assertEquals(965, full_price);
    }
}
