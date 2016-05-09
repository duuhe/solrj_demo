package solrj.indexer;

import java.io.IOException;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;

public interface Indexer {
	int addBean(Object obj) throws IOException, SolrServerException;

	int addBeans(Collection<Object> objs) throws SolrServerException,
			IOException;

	int addDoc(SolrInputDocument doc) throws SolrServerException, IOException;

	int addDocs(Collection<SolrInputDocument> docs) throws SolrServerException,
			IOException;

	int delDocById(String id) throws SolrServerException, IOException;

	int delDocByQuery(String q) throws SolrServerException, IOException;

	int updateDoc(SolrInputDocument doc) throws SolrServerException,
			IOException;

	int updateDocs(Collection<SolrInputDocument> docs) throws SolrServerException,
			IOException;
}
