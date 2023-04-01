package com.nttdata.btc.card.app.service;

import com.nttdata.btc.card.app.model.entity.Card;
import com.nttdata.btc.card.app.model.request.CardRequest;
import com.nttdata.btc.card.app.model.request.UpdateCardRequest;
import com.nttdata.btc.card.app.model.response.CardResponse;
import com.nttdata.btc.card.app.repository.CardRepository;
import com.nttdata.btc.card.app.service.impl.CardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {
    @InjectMocks
    CardServiceImpl service;
    @Mock
    CardRepository repository;


    private List<Card> listCards = new ArrayList<>();

    static final String ID_CARD = "640d0e33ae8b8b50e2e61274";

    @BeforeEach
    private void setUp() {
        Card operation = new Card();
        operation.setId_card(ID_CARD);
        operation.setClient_id("640bf4a36bf23c1c772da9d6");
        operation.setProduct_id("640c24cd3b905b25cfa2f25a");
        operation.setMain_account("640cc29c60650d1637e040a9");
        operation.setAmount(new BigDecimal(2550.0));
        operation.setAmount_available(new BigDecimal(7000.0));
        operation.setAssociated_accounts(new ArrayList<>());
        operation.setRegister_date(new Date());
        operation.setStatus(true);

        listCards.add(operation);
    }

    @Test
    @DisplayName("Return all cards")
    void testFindAll() {
        when(repository.findAll()).thenReturn(Flux.fromIterable(listCards));

        Flux<CardResponse> result = service.findAll();

        assertEquals(result.blockFirst().getId_card(), listCards.get(0).getId_card());
    }

    @Test
    @DisplayName("Return card by id")
    void testFindById() {
        when(repository.findById(anyString())).thenReturn(Mono.just(listCards.get(0)));

        Mono<CardResponse> result = service.findById(ID_CARD);

        assertEquals(result.block().getAmount_available(), listCards.get(0).getAmount_available());
    }

    @Test
    @DisplayName("Create new card")
    void testSave() {
        Card entity = listCards.get(0);

        CardRequest request = new CardRequest();
        request.setClient_id(entity.getClient_id());
        request.setProduct_id(entity.getProduct_id());
        request.setMain_account(entity.getMain_account());
        request.setAmount(entity.getAmount());
        request.setAmount_available(entity.getAmount_available());
        request.setAssociated_accounts(entity.getAssociated_accounts());

        when(repository.save(any())).thenReturn(Mono.just(entity));

        Mono<CardResponse> result = service.save(request);

        assertEquals(result.block().getClient_id(), entity.getClient_id());
    }

    @Test
    @DisplayName("Delete card")
    void testDelete() {
        Card entity = listCards.get(0);
        when(repository.findById(anyString())).thenReturn(Mono.just(entity));
        when(repository.save(any())).thenReturn(Mono.just(entity));

        Mono<Boolean> result = service.delete(ID_CARD).thenReturn(Boolean.TRUE);

        assertTrue(result.block());
    }

    @Test
    @DisplayName("Update card")
    void testUpdate() {
        Card entity = listCards.get(0);

        UpdateCardRequest request = new UpdateCardRequest();
        request.setId_card(ID_CARD);
        request.setClient_id(entity.getClient_id());
        request.setProduct_id(entity.getProduct_id());
        request.setMain_account(entity.getMain_account());
        request.setAmount(entity.getAmount());
        request.setAmount_available(entity.getAmount_available());
        request.setAssociated_accounts(entity.getAssociated_accounts());

        when(repository.findById(anyString())).thenReturn(Mono.just(entity));
        when(repository.save(any())).thenReturn(Mono.just(entity));

        Mono<CardResponse> result = service.update(request);

        assertEquals(result.block().getAmount(), entity.getAmount());
    }
}