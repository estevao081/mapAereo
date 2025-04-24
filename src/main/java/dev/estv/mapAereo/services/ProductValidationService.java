package dev.estv.mapAereo.services;

import dev.estv.mapAereo.exceptions.InvalidAddressException;
import dev.estv.mapAereo.exceptions.InvalidCodeException;
import dev.estv.mapAereo.exceptions.InvalidDateException;
import dev.estv.mapAereo.exceptions.InvalidNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductValidationService {

    @Autowired
    DateValidationService dateValidationService;

    public void validateCode(Integer code) {
        if (code.toString().length() != 6) {
            throw new InvalidCodeException();
        }
    }

    public void validateName(String name) {
        if (!name.matches("^[\\p{L}\\p{N}-]+$")) {
            throw new InvalidNameException();
        }
    }

    public void validateDate(String date) {
        if (!date.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$")
                || !dateValidationService.isValid(date)) {
            throw new InvalidDateException();
        }
    }

    public void validateAddress(String address) {
        if (!address.matches("^(?:(?:[1-9]|1\\d|20)[AB]-R[1-7]|PAR-\\d+)$")) {
            throw new InvalidAddressException();
        }
    }


}