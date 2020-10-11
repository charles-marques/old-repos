package trabalhoBanco.mongo.persistence;

import java.io.Serializable;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 * @author milena.rodrigues
 * 
 */
public class EntityPersistence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4744645593332277479L;
	private static final String DATABASE = "test";
	private static final String HOST = "localhost";
	private static final Integer PORT = 27017;

	private static MongoClient mongoClient;
	MongoCollection<Document> table;

	private MongoDatabase db;

	public EntityPersistence() {
		mongoClient = new MongoClient(HOST, PORT);
		db = mongoClient.getDatabase(DATABASE);
	}

	protected void createCollection(String collectionName) {
		db.createCollection(collectionName);
	}
	
	protected void deleteAllCollection(String collectionName) {
		db.getCollection(collectionName).deleteMany(new Document());
	}
	
	protected void droplCollection(String collectionName) {
		db.getCollection(collectionName).drop();
	}
	
	protected void insert(String collection, Document document)
			throws Exception {
		try {
			db.getCollection(collection).insertOne(document);
		} catch (MongoWriteException mwe) {
			mwe.printStackTrace();
			throw new Exception(mwe.getMessage(), mwe.getCause());
		} catch (MongoWriteConcernException mwce) {
			mwce.printStackTrace();
			throw new Exception(mwce.getMessage(), mwce.getCause());
		} catch (MongoException me) {
			me.printStackTrace();
			throw new Exception(me.getMessage(), me.getCause());
		}

		return;
	}

	protected UpdateResult update(String collection, Document eFilter,
			Map<String, Object> eUpdate) throws Exception {
		try {
			Document document = new Document("$set", eUpdate);
			return db.getCollection(collection).updateOne(eFilter, document);
		} catch (MongoWriteException mwe) {
			mwe.printStackTrace();
			throw new Exception(mwe.getMessage(), mwe.getCause());
		} catch (MongoWriteConcernException mwce) {
			mwce.printStackTrace();
			throw new Exception(mwce.getMessage(), mwce.getCause());
		} catch (MongoException me) {
			me.printStackTrace();
			throw new Exception(me.getMessage(), me.getCause());
		}
	}

	protected DeleteResult delete(String collection, Document e) {
		return db.getCollection(collection).deleteOne(e);
	}

	protected FindIterable<Document> find(String collection, Document filter) {
		table = db.getCollection(collection);
		return table.find(filter);
	}

	protected FindIterable<Document> findAll(String collection) {
		return db.getCollection(collection).find();
	}
	
	protected FindIterable<Document> findById(String collection, Document filter) throws Exception {
		return db.getCollection(collection).find(filter);
	}
	
	protected FindIterable<Document> findByParameter(String collection, Document filter) throws Exception {
		return db.getCollection(collection).find(filter);
	}
}
