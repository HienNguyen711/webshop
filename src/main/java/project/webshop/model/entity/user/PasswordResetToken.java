package project.webshop.model.entity.user;
import lombok.Getter;
import lombok.Setter;
import project.webshop.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "password_reset_token")
@Getter
@Setter
@Table(name = "password_reset_tokens")
public class PasswordResetToken extends BaseEntity {

    private String token;

    private Date expiration;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    public PasswordResetToken() {
    }
}
