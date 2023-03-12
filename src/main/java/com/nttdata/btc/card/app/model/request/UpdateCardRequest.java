package com.nttdata.btc.card.app.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String id_card;
}