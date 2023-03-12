package com.nttdata.btc.card.app.service;

import com.nttdata.btc.card.app.model.request.CardRequest;
import com.nttdata.btc.card.app.model.request.UpdateCardRequest;
import com.nttdata.btc.card.app.model.response.CardResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class service CardService.
 *
 * @author lrs.
 */
public interface CardService {
    /**
     * Method find all cards.
     *
     * @return {@link CardResponse}
     */
    Flux<CardResponse> findAll();

    /**
     * Method find card by id.
     *
     * @param id {@link String}
     * @return {@link CardResponse}
     */
    Mono<CardResponse> findById(String id);

    /**
     * Method save a card.
     *
     * @param request {@link CardRequest}
     * @return {@link CardResponse}
     */
    Mono<CardResponse> save(CardRequest request);

    /**
     * Method Delete card.
     *
     * @param id {@link String}
     * @return {@link Void}
     */
    Mono<Void> delete(String id);

    /**
     * Method update card.
     *
     * @param request {@link UpdateCardRequest}
     * @return {@link CardResponse}
     */
    Mono<CardResponse> update(UpdateCardRequest request);
}