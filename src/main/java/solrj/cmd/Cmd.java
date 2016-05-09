package solrj.cmd;

import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

import solrj.params.QueryType;

public interface Cmd {
	QueryResponse execCmd(QueryType qTyepe, Map<String, String> params) throws SolrServerException;
}
