package com.nttdata.btc.card.app.service.impl;

import com.nttdata.btc.card.app.model.entity.Card;
import com.nttdata.btc.card.app.model.request.CardRequest;
import com.nttdata.btc.card.app.model.request.UpdateCardRequest;
import com.nttdata.btc.card.app.model.response.CardResponse;
import com.nttdata.btc.card.app.repository.CardRepository;
import com.nttdata.btc.card.app.service.CardService;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.nttdata.btc.card.app.util.constant.Constants.DEFAULT_FALSE;

/**
 * Class implement methods from CardService.
 *
 * @author lrs
 */
@Service
public class CardServiceImpl implements CardService {
    /**
     * Inject dependency {@link CardRepository}
     */
    @Autowired
    private CardRepository repository;

    /**
     * This method return all cards.
     *
     * @return {@link List <CardResponse>}
     */
    @Override
    public Flux<CardResponse> findAll() {
        return repository.findAll().filter(Card::isStatus)
                .map(entity -> buildCardR.apply(entity))
                .onErrorReturn(new CardResponse());
    }

    /**
     * This method find a card by id.
     *
     * @param id {@link String}
     * @return {@link CardResponse}
     */
    @Override
    public Mono<CardResponse> findById(String id) {
        return repository.findById(id)
                .filter(Card::isStatus)
                .map(e -> buildCardR.apply(e))
                .onErrorReturn(new CardResponse());
    }

    /**
     * This method save a card.
     *
     * @param request {@link CardRequest}
     * @return {@link CardResponse}
     */
    @Override
    public Mono<CardResponse> save(CardRequest request) {
        return repository.save(buildCard.apply(request))
                .flatMap(entity -> Mono.just(buildCardR.apply(entity)))
                .onErrorReturn(new CardResponse());
    }

    /**
     * This method update status from card.
     *
     * @param id {@link String}
     * @return {@link Void}
     */
    @Override
    public Mono<Void> delete(String id) {
        return repository.findById(id).filter(Card::isStatus)
                .map(e -> updateStatus.apply(e, DEFAULT_FALSE))
                .flatMap(e -> repository.delete(e));
    }

    /**
     * This method update a card.
     *
     * @param request {@link UpdateCardRequest}
     * @return {@link CardResponse}
     */
    @Override
    public Mono<CardResponse> update(UpdateCardRequest request) {
        return repository.findById(request.getId_card())
                .map(entity -> updateCard.apply(request, entity))
                .flatMap(operation -> repository.save(operation))
                .flatMap(cupdated -> Mono.just(buildCardR.apply(cupdated)))
                .onErrorReturn(new CardResponse());
    }

    /**
     * BiFunction update Card.
     */
    BiFunction<UpdateCardRequest, Card, Card> updateCard = (request, bean) -> {
        bean.setClient_id(request.getClient_id());
        bean.setProduct_id(request.getProduct_id());
        bean.setMain_account(request.getMain_account());
        bean.setAmount(request.getAmount());
        bean.setAmount_available(request.getAmount_available());
        bean.setAssociated_accounts(request.getAssociated_accounts());
        return bean;
    };

    /**
     * BiFunction updateStatus from Card.
     */
    BiFunction<Card, Boolean, Card> updateStatus = (product, status) -> {
        product.setStatus(status);
        return product;
    };

    /**
     * Function build new Card.
     */
    Function<CardRequest, Card> buildCard = request -> new Card(request.getClient_id(), request.getProduct_id(), request.getMain_account(),
            request.getAmount(), request.getAmount_available(), request.getAssociated_accounts());

    /**
     * Function build new CardResponse.
     */
    Function<Card, CardResponse> buildCardR = entity -> {
        CardResponse response = new CardResponse();
        response.setId_card(entity.getId_card());
        response.setClient_id(entity.getClient_id());
        response.setProduct_id(entity.getProduct_id());
        response.setMain_account(entity.getMain_account());
        response.setAmount(entity.getAmount());
        response.setAmount_available(entity.getAmount_available());
        response.setAssociated_accounts(entity.getAssociated_accounts());
        response.setRegister_date(entity.getRegister_date());
        response.setStatus(entity.isStatus());
        return response;
    };
}