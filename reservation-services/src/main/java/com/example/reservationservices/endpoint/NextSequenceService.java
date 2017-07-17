package com.example.reservationservices.endpoint;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.example.reservationservices.entity.Sequences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class NextSequenceService {
	@Autowired
	private MongoOperations mongo;

	public Long genNextSequence(String seqName) {
		Sequences  counter = mongo.findAndModify(
			query(where("_id").is(seqName)),
			new Update().inc("seq", 1),
			options().returnNew(true).upsert(true),
			Sequences.class
		);

		return counter.getSeq();
	}
}
