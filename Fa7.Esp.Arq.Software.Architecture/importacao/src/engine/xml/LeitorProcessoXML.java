package engine.xml;

import java.io.InputStream;

import com.thoughtworks.xstream.XStream;

public class LeitorProcessoXML {

	public static TagProcesso ler(String path) {
		XStream xstream = new XStream();
		xstream.processAnnotations(TagProcesso.class);
		InputStream in = LeitorProcessoXML.class.getClassLoader().getResourceAsStream(path);
		TagProcesso processo = (TagProcesso) xstream.fromXML(in);
		return processo;
	}
}
