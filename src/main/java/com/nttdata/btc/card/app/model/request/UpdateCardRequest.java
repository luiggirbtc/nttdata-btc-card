package com.nttdata.btc.card.app.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Class UpdateCardRequest.
 *
 * @author lrs
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateCardRequest extends BaseRequest {
    @NotNull(message = "Is mandatory")
    @NotEmpty(message = "Not be empty")
    @Schema(required = true, description = "Id card", example = "640d0e33ae8b8b50e2e61274")
    private String id_card;
}