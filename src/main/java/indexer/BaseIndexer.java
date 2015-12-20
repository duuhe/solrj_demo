package indexer;

import java.io.IOException;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import client.ZSolrClient;

public class BaseIndexer implements Indexer {

	private SolrClient client;

	public BaseIndexer(ZSolrClient zClient) {
		this.client = zClient.getClient();
	}

	@Override
	public int addBean(Object obj) throws IOException, SolrServerException {
		UpdateResponse response = client.addBean(obj);
		client.commit();

		return response.getStatus();
	}

	@Override
	public int addBeans(Collection<Object> objs) throws SolrServerException,
			IOException {
		UpdateResponse response = client.addBeans(objs);
		client.commit();

		return response.getStatus();
	}

	@Override
	public int addDoc(SolrInputDocument doc) throws SolrServerException,
			IOException {
		UpdateResponse response = client.add(doc);
		client.commit();

		return response.getStatus();
	}

	@Override
	public int addDocs(Collection<SolrInputDocument> docs)
			throws SolrServerException, IOException {
		UpdateResponse response = client.add(docs);
		client.commit();

		return response.getStatus();
	}

	@Override
	public int delDocById(String id) throws SolrServerException, IOException {
		UpdateResponse response = client.deleteById(id);
		client.commit();

		return response.getStatus();
	}

	@Override
	public int delDocByQuery(String q) throws SolrServerException, IOException {
		UpdateResponse response = client.deleteByQuery(q);
		client.commit();

		return response.getStatus();
	}

	@Override
	public int updateDoc(SolrInputDocument doc) throws SolrServerException,
			IOException {
		/**
		 * Solr 无更新功能，若要更新某个doc，可调用addDoc接口，该接口会覆盖相同Id的doc
		 */
		return addDoc(doc);
	}

	@Override
	public int updateDocs(Collection<SolrInputDocument> docs) throws SolrServerException,
			IOException {
		/**
		 * Solr 无更新功能，若要更新docs，可调用addDocs接口，该接口会覆盖相同Id的doc
		 */
		return addDocs(docs);
	}

}
