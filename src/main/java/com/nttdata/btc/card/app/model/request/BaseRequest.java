package com.nttdata.btc.card.app.model.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class BaseRequest.
 *
 * @author lrs
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseRequest {
    private String client_id;

    private String product_id;

    private String main_account;

    /**
     * Saldo tarjeta credito.
     */
    private BigDecimal amount = BigDecimal.ZERO;

    private BigDecimal amount_available = BigDecimal.ZERO;

    private List<String> associated_accounts = new ArrayList<>();
}