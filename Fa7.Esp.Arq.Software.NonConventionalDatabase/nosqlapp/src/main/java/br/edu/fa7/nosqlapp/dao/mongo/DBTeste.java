package br.edu.fa7.nosqlapp.dao.mongo;

import static java.util.Arrays.asList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class DBTeste {

	private static MongoClient mongoClient;

	public static void find(MongoDatabase db) {
		FindIterable<Document> iterable = db.getCollection("movie").find();
		
		for (Document document : iterable) {
			System.out.println("======================================");
			System.out.println(document.toJson());
			Set<String> keysSet = document.keySet();
			System.out.println("Propriedades:");
			for (Iterator iterator = keysSet.iterator(); iterator.hasNext();) {
				Object key = iterator.next();
				System.out.println(key + ": " + document.get(key));
			}
		}
	}
	
	public static void insert(MongoDatabase db) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.forLanguageTag("portuguese"));
		db.getCollection("restaurants").insertOne(
		        new Document("address",
		                new Document()
		                        .append("street", "2 Avenue")
		                        .append("zipcode", "10075")
		                        .append("building", "1480")
		                        .append("coord", asList(-73.9557413, 40.7720266)))
		                .append("borough", "Manhattan")
		                .append("cuisine", "Italian")
		                .append("grades", asList(
		                        new Document()
		                                .append("date", format.parse("2014-10-01T00:00:00Z"))
		                                .append("grade", "A")
		                                .append("score", 11),
		                        new Document()
		                                .append("date", format.parse("2014-01-16T00:00:00Z"))
		                                .append("grade", "B")
		                                .append("score", 17)))
		                .append("name", "Vella")
		                .append("restaurant_id", "41704620"));
	}
	
	public static void main(String[] args) {
			mongoClient = new MongoClient("localhost", 27017);
			
			// Now connect to your databases
			MongoDatabase db = mongoClient.getDatabase("test");
			
			find(db);
			
			
			//System.err.println(e.getClass().getName() + ": " + e.getMessage());
	}

}
