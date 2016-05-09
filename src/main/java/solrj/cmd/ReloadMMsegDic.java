package solrj.cmd;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.NamedList;

import solrj.client.SolrSingleClient;
import solrj.client.ZSolrClient;
import solrj.params.QueryType;

public class ReloadMMsegDic {
	private String solr = "solr";
	private String http_proto = "http://";
	private String url;
	private String core;
	private String port;

	public ReloadMMsegDic() {
		// TODO Auto-generated constructor stub
	}
	
	public ReloadMMsegDic(String url, String core, String port) {
		this.url = url;
		this.core = core;
		this.port = port;
	}
	
	public Boolean reload(String url, String core, String port) throws SolrServerException {
		this.url = url;
		this.core = core;
		this.port = port;
		return reload();
	}
	
	public Boolean reload() throws SolrServerException {
		ZSolrClient zClient = new SolrSingleClient(genSolrUrl(url, port), core);
		Cmd cmd = new BaseCmd(zClient);
		QueryResponse response = cmd.execCmd(QueryType.MMSEG4J,null);
		zClient.shutdown();
		
		// resolve response
		NamedList<Object> objects = response.getResponse();
		@SuppressWarnings("unchecked")
		NamedList<Object> responseHeader = (NamedList<Object>) objects.get("responseHeader");
		@SuppressWarnings("unchecked")
		NamedList<Object> result = (NamedList<Object>) objects.get("result");
		
		System.out.println(responseHeader);
		System.out.println(result);
		if (responseHeader.get("status").equals("0") && result.getBooleanArg("changed")) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unused")
	private List<String> getUrlFormZkHost(String zkHost) {
		List<String> urlList = new ArrayList<String>();
		String url[] = zkHost.split(",");
		for (String str : url) {
			String str2[] = str.split(":");
			urlList.add(str2[0]);
		}
		return urlList;
	}

	private String genSolrUrl(String url, String port) {
		return (http_proto + url + ":" + port + "/" + solr);
	}
}
