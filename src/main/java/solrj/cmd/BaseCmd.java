package solrj.cmd;

import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;

import solrj.client.ZSolrClient;
import solrj.params.QueryType;

public class BaseCmd implements Cmd {
	protected SolrClient client;

	public BaseCmd(ZSolrClient zClient) {
		this.client = zClient.getClient();
	}

	@Override
	public QueryResponse execCmd(QueryType qType, Map<String, String> params) throws SolrServerException {
		SolrQuery query = new SolrQuery();

		query.set(CommonParams.QT, "/" + qType.value());
		if (params != null) {
			SolrParams solrParams = new MapSolrParams(params);
			query.add(solrParams);
		}

		return client.query(query);
	}
}
