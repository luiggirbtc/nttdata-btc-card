package com.nttdata.btc.card.app.controller;

import com.nttdata.btc.card.app.model.request.CardRequest;
import com.nttdata.btc.card.app.model.request.UpdateCardRequest;
import com.nttdata.btc.card.app.model.response.CardResponse;
import com.nttdata.btc.card.app.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
@Tag(name = "Card", description = "Service Card")
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
    @Operation(summary = "Get a card by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CardResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)})
    @GetMapping(value = "id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CardResponse> findCardById(@PathVariable final String id) {
        return service.findById(id);
    }

    /**
     * Service create card.
     *
     * @param request {@link CardRequest}
     * @return {@link CardResponse}
     */
    @Operation(summary = "Create a new card")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CardResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CardResponse> createCard(@RequestBody final CardRequest request) {
        log.info("Start CreateCard.");
        return service.save(request);
    }

    /**
     * Service update a card.
     *
     * @param request {@link UpdateCardRequest}
     * @return {@link CardResponse}
     */
    @Operation(summary = "Update card")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CardResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CardResponse> updateCard(@RequestBody final UpdateCardRequest request) {
        log.info("Start UpdateCard.");
        return service.update(request);
    }

    /**
     * Service return all cards.
     *
     * @return {@link CardResponse}
     */
    @Operation(summary = "Get all cards")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CardResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @Operation(summary = "Delete card")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
    @DeleteMapping("/{id}")
    public Mono<Void> deleteCard(@PathVariable(value = "id") final String id) {
        log.info("Start deletecard");
        return service.delete(id);
    }
}