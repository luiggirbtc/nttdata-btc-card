package com.nttdata.btc.card.app.model.response;

import com.nttdata.btc.card.app.model.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Class response CardResponse.
 *
 * @author lrs
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardResponse extends BaseRequest {
    private String id_card;
    private Date register_date;
    private Boolean status = false;
}