package project.webshop.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import project.webshop.model.entity.user.PasswordResetToken;


@Service("passwordResetTokenRepository")
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {
}
