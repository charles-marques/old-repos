package trabalhoBanco.mongo.persistence;

import java.io.Serializable;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bson.Document;

/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 * @author milena.rodrigues
 * @param <E>
 * 
 */
public abstract class MongoAppEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1014106406524835764L;

	private Document document;
	private Map<String, Object> documentUpdate = new HashMap<String, Object>();
	
	public MongoAppEntity() {
		document = new Document();
	}
	
	public final String getCollection() {
		return this.getClass().getSimpleName().toLowerCase();
	}
	
//	public final Map<String, Object> getProperties() {
//		if (oid != null) {
//			propertiesMap.put();
//		}
////		getPropertiesValues();
//		return propertiesMap;
//	}
//	
////	protected abstract void getPropertiesValues();
	
	public final Document toDocument() {
//		this.document.append("_id", oid);
		configDocument();
		return this.document;
	}
	
	public final Map<String, Object> toDocumentUpdate() {
		configDocumentUpdate();
		return documentUpdate;
	}
	
	public MongoAppEntity toObject(Document document) {
		if (document == null) return null;
		Set<Entry<String, Object>> entrySet = document.entrySet();
		for (Entry<String, Object> entry : entrySet) {
//			if (entry.getKey().equals("id")) {
//				this.oid = (ObjectId) entry.getValue();
//			}
			configProperties(entry);
		}
		return this;
	}
	
	/**
	 * Metodo para reconstrucao do objeto a partir de um {@link Document}.<br/>
	 * Method to objet's rebuilding from a {@link Document}.<br/>
	 * <b>Ex:</b><br/>
	 * <pre> 
	 * if (entry.getKey().equals("properties_name")) {
	 *    this.propertie = entry.getValue();
	 * }
	 * </pre>
	 * 
	 * @param entry
	 */
	protected abstract void configProperties(Entry<String, Object> entry);

	/**
	 * Metodo para configuracao do documento do objeto para persistencia. <br/>
	 * Methods to configuration of object document to persistence:
	 * Ex:
	 * <pre>
	 * append(key, content);
	 * </pre>
	 */
	protected abstract void configDocument();
	
	/**
	 * Metodo para configuracao do documento do objeto para atualizacao. <br/>
	 * Methods to configuration of object document to persistence:
	 * Ex:
	 * <pre>
	 * append(key, content);
	 * </pre>
	 */
	protected abstract void configDocumentUpdate();
	
	protected void append(String key, Object value) {
		this.document.append(key, value);
	}
	
	protected void appendUpdate(String key, Object value) {
		this.documentUpdate.put(key, value);
	}
}
