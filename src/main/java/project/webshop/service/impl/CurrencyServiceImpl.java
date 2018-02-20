package project.webshop.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.webshop.dao.CategoryDao;
import project.webshop.model.dto.CategoryDto;
import project.webshop.model.entity.Category;
import project.webshop.model.entity.payment.Currency;
import project.webshop.repository.CategoryRepository;
import project.webshop.repository.CurrencyRepository;
import project.webshop.service.CategoryService;
import project.webshop.service.CurrencyService;
import project.webshop.utils.Constants;
import project.webshop.utils.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    // autowired
    @Autowired
    private CurrencyRepository currencyRepository;


    @Override
    public List<Currency> getCurrencies() {
        final List<Currency> currencies = currencyRepository.findAll();
        return currencies;
    }

    @Override
    public Currency findCurrencyById(Long currencyId) {
        return currencyRepository.findById(currencyId);
    }
}
