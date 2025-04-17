package dev.estv.mapAereo.services;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateValidationService {

    public boolean isValid(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try {
            Date parsedDate = sdf.parse(date);
            Date today = new Date();

            if (parsedDate.after(removeTime(today))) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    private Date removeTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            return date;
        }
    }
}