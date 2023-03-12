package com.nttdata.btc.card.app.controller;

import com.nttdata.btc.card.app.model.request.CardRequest;
import com.nttdata.btc.card.app.model.request.UpdateCardRequest;
import com.nttdata.btc.card.app.model.response.CardResponse;
import com.nttdata.btc.card.app.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class CardController.
 *
 * @author lrs
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/card")
public class CardController {
    /**
     * Inject dependency CardService.
     */
    @Autowired
    private CardService service;

    /**
     * Service find card by id.
     *
     * @param id {@link String}
     * @return {@link CardResponse}
     */
    @GetMapping("id/{id}")
    public Mono<ResponseEntity<CardResponse>> findCardById(@PathVariable final String id) {
        return service.findById(id)
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Service create card.
     *
     * @param request {@link CardRequest}
     * @return {@link CardResponse}
     */
    @PostMapping("/")
    public Mono<ResponseEntity<CardResponse>> createCard(@RequestBody final CardRequest request) {
        log.info("Start CreateCard.");
        return service.save(request)
                .map(p -> new ResponseEntity<>(p, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    /**
     * Service update a card.
     *
     * @param request {@link UpdateCardRequest}
     * @return {@link CardResponse}
     */
    @PutMapping("/")
    public Mono<ResponseEntity<CardResponse>> updateCard(@RequestBody final UpdateCardRequest request) {
        log.info("Start UpdateCard.");
        return service.update(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    /**
     * Service return all cards.
     *
     * @return {@link CardResponse}
     */
    @GetMapping("/")
    public Flux<CardResponse> findAllCards() {
        log.info("Start findAll Cards.");
        return service.findAll()
                .doOnNext(product -> log.info(product.toString()));
    }

    /**
     * Service delete card.
     *
     * @param id {@link String}
     * @return {@link Void}
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCard(@PathVariable(value = "id") final String id) {
        return service.delete(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}