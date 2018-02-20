package project.webshop.service;
import org.springframework.stereotype.Service;
import project.webshop.model.dto.CategoryDto;
import project.webshop.model.entity.payment.Currency;

import java.util.List;

// Category service
@Service
public interface CurrencyService {


    //get all currencies available in the database
    List<Currency> getCurrencies();

    // find currency by id
    Currency findCurrencyById(final Long currencyId);
}
