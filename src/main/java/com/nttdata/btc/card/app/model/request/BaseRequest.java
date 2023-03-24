package com.nttdata.btc.card.app.model.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "Is mandatory")
    @NotEmpty(message = "Not be empty")
    @Schema(required = true, description = "Client id", example = "640bf4a36bf23c1c772da9d6")
    private String client_id;

    @NotNull(message = "Is mandatory")
    @NotEmpty(message = "Not be empty")
    @Schema(required = true, description = "Product id", example = "640c24cd3b905b25cfa2f25a")
    private String product_id;

    @NotNull(message = "Is mandatory")
    @NotEmpty(message = "Not be empty")
    @Schema(required = true, description = "Code main account", example = "640cc29c60650d1637e040a9")
    private String main_account;

    /**
     * Saldo tarjeta credito.
     */
    @NotNull(message = "Is mandatory")
    @NotEmpty(message = "Not be empty")
    @Schema(required = true, description = "Amount card", example = "250.0", type = "double")
    private BigDecimal amount = BigDecimal.ZERO;

    @Schema(required = true, description = "Amount available", example = "5000.0", type = "double")
    private BigDecimal amount_available = BigDecimal.ZERO;

    @Schema(required = false, description = "Associated accounts", example = "{}", type = "array")
    private List<String> associated_accounts = new ArrayList<>();
}