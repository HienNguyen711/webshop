package project.webshop.controller;


import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import project.webshop.model.dto.CategoryDto;
import project.webshop.model.entity.payment.Currency;
import project.webshop.service.CurrencyService;

import javax.ws.rs.Produces;

@RestController
@RequestMapping("/v1/api/currencies")
//@Produces(MediaType.APPLICATION_JSON) // const
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getCurrencies() throws Exception {
        List<Currency> currencies;
        try {
            currencies = currencyService.getCurrencies();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.GET, path = "/{currencyId}/offers")
    public OffersView getOffers(@PathVariable final Long currencyId) {

        final Currency currency = currencyService.findCurrencyBy(currencyId);
        if (currency == null) {
            throw new CurrencyNotFoundException(currencyId);
        }

        return offerService.getAllOffersFor(currency);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{currencyId}/offers/{offerId}")
    public OfferView getOffer(@PathVariable final Long currencyId, @PathVariable final Long offerId) {

        final Currency currency = currencyService.findCurrencyBy(currencyId);
        if (currency == null) {
            throw new CurrencyNotFoundException(currencyId);
        }

        return offerService.getOfferFor(currency, offerId);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{currencyId}/offers")
    public ResponseEntity<?> addOffer(@PathVariable final Long currencyId, @RequestBody final Offer offer) {

        final Currency currency = currencyService.findCurrencyBy(currencyId);

        if (currency == null) {
            return noContent().build();
        }

        final Long offerId = offerService.addOffer(currency, offer);

        final URI location = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(offerId).toUri();

        return created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{currencyId}/offers/{offerId}")
    public ResponseEntity<?> updateOffer(@PathVariable final Long currencyId, @PathVariable final Long offerId, @RequestBody final Offer offer) {
        final Currency currency = currencyService.findCurrencyBy(currencyId);

        if (currency == null) {
            return noContent().build();
        }

        final Long updatedOfferId = offerService.updateOffer(currency, offerId, offer);

        if (updatedOfferId == null) {
            throw new OfferNotFoundException(currency.getName(), offerId);
        }

        final URI location = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}")
            .buildAndExpand(offerId).toUri();

        return ok(location);

    }
}
