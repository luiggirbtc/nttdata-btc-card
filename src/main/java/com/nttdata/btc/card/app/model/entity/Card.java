package com.nttdata.btc.card.app.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity Card.
 *
 * @author lrs
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document(collection = "card")
public class Card {
    @Id
    private String id_card;

    private String client_id;

    private String product_id;

    private String main_account;

    /**
     * Saldo tarjeta credito.
     */
    private BigDecimal amount;

    private BigDecimal amount_available;

    private List<String> associated_accounts = new ArrayList<>();

    private Date register_date = new Date();

    private boolean status = true;

    /**
     * Constructor create a new card.
     *
     * @param client_id           {@link String}
     * @param product_id          {@link String}
     * @param main_account        {@link String}
     * @param amount              {@link BigDecimal}
     * @param amount_available    {@link BigDecimal}
     * @param associated_accounts {@link List<String>}
     */
    public Card(String client_id, String product_id, String main_account, BigDecimal amount,
                BigDecimal amount_available, List<String> associated_accounts) {
        this.client_id = client_id;
        this.product_id = product_id;
        this.main_account = main_account;
        this.amount = amount;
        this.amount_available = amount_available;
        this.associated_accounts = associated_accounts;
    }
}