package com.nttdata.btc.card.app.controller;

import com.nttdata.btc.card.app.model.request.CardRequest;
import com.nttdata.btc.card.app.model.request.UpdateCardRequest;
import com.nttdata.btc.card.app.model.response.CardResponse;
import com.nttdata.btc.card.app.service.CardService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardControllerTest {

    @InjectMocks
    CardController controller;

    @Mock
    CardService service;


    private List<CardResponse> listCards = new ArrayList<>();

    static final String ID_CARD = "640d0e33ae8b8b50e2e61274";

    @BeforeEach
    private void setUp() {
        CardResponse operation = new CardResponse();
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
    void testFindAllCards() {
        when(service.findAll()).thenReturn(Flux.fromIterable(listCards));

        Flux<CardResponse> result = controller.findAllCards();

        assertEquals(result.blockFirst().getId_card(), listCards.get(0).getId_card());
    }

    @Test
    @DisplayName("Return card by id")
    void testFindCardById() {
        when(service.findById(anyString())).thenReturn(Mono.just(listCards.get(0)));

        Mono<CardResponse> result = controller.findCardById(ID_CARD);

        assertEquals(result.block().getAmount_available(), listCards.get(0).getAmount_available());
    }

    @Test
    @DisplayName("Create new card")
    void testCreateCard() {
        CardResponse response = listCards.get(0);

        CardRequest request = new CardRequest();
        request.setClient_id(response.getClient_id());
        request.setProduct_id(response.getProduct_id());
        request.setMain_account(response.getMain_account());
        request.setAmount(response.getAmount());
        request.setAmount_available(response.getAmount_available());
        request.setAssociated_accounts(response.getAssociated_accounts());

        when(service.save(request)).thenReturn(Mono.just(response));

        Mono<CardResponse> result = controller.createCard(request);

        assertEquals(result.block().getClient_id(), response.getClient_id());
    }

    @Test
    @DisplayName("Update card")
    void testUpdateCard() {
        CardResponse response = listCards.get(0);

        UpdateCardRequest request = new UpdateCardRequest();
        request.setId_card(ID_CARD);
        request.setClient_id(response.getClient_id());
        request.setProduct_id(response.getProduct_id());
        request.setMain_account(response.getMain_account());
        request.setAmount(response.getAmount());
        request.setAmount_available(response.getAmount_available());
        request.setAssociated_accounts(response.getAssociated_accounts());

        when(service.update(request)).thenReturn(Mono.just(response));

        Mono<CardResponse> result = controller.updateCard(request);

        assertEquals(result.block().getClient_id(), response.getClient_id());
    }

    @Test
    @DisplayName("Delete card")
    void testDeleteCard() {
        when(service.delete(anyString())).thenReturn(Mono.empty());

        Mono<Boolean> result = controller.deleteCard(ID_CARD).thenReturn(Boolean.TRUE);

        assertTrue(result.block());
    }
}