package project.webshop.integration;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.webshop.model.entity.payment.Currency;
import project.webshop.service.CurrencyService;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CurrencyServiceIntegrationTest {

    @Autowired
    private CurrencyService currencyService;

    @Before
    public void before(){

    }
    

    @Test
    public void getCurrenciesTest(){
        List<Currency> currencies = currencyService.getCurrencies();
        assertTrue("List of currencies is true",currencies.size()>0);
        assertFalse(currencies.isEmpty());
    }
}
