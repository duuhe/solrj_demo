package solrj.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

/**
 * @Description Solrj 使用demo
 * @author Zhifeiyu
 * @version 1.0
 *
 */
public class SolrService extends BaseSolrService {
	@Override
	public String getCore() {
		return "demo";
	}


	public QueryResponse search(String field, String key, int start, int count,
			String sortfield, Boolean sortflag) throws SolrServerException {

		String[] fields = new String[] { field };
		String[] keys = new String[] { key };
		String[] sortfields = new String[] { sortfield };
		Boolean[] sortflags = new Boolean[] { sortflag };

		return this.search(fields, keys, start, count, sortfields, sortflags);

	}

	public QueryResponse search(String field, String key, int start, int count)
			throws SolrServerException {

		String[] fields = new String[] { field };
		String[] keys = new String[] { key };

		return this.search(fields, keys, start, count, null, null);

	}
}
