package project.webshop.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import project.webshop.model.entity.payment.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findById(final Long currencyId);
}
