package com.nttdata.btc.card.app.repository;

import com.nttdata.btc.card.app.model.entity.Card;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Class repository CardRepository.
 *
 * @author lrs
 */
@Repository
public interface CardRepository extends ReactiveMongoRepository<Card, String> {
}