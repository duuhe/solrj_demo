package solrj.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;

import solrj.client.SolrSingleClient;
import solrj.client.ZSolrClient;
import solrj.params.QueryType;

public class BaseCmdTest {
	private String zkHost = "10.204.76.42:2181,10.204.76.80:2181,10.204.76.99:2181";
	private String core = "soft";
	private String port = "8080";
	private String solr = "solr";
	private String http = "http://";

	// private ZSolrClient zClient = new SolrCloudClient(zkHost, core);

	private List<String> getUrlFormZkHost(String zkHost) {
		List<String> urlList = new ArrayList<String>();
		String url[] = zkHost.split(",");
		for (String str : url) {
			String str2[] = str.split(":");
			urlList.add(str2[0]);
		}
		return urlList;
	}

	private String genSolrUrl(String url) {
		return (http + url + ":" + port + "/" + solr);
	}

	@Test
	public void execCmdTest() {

		List<String> urls = getUrlFormZkHost(zkHost);

		for (String url : urls) {
			ZSolrClient zClient = new SolrSingleClient(genSolrUrl(url), core);
			Cmd cmd = new BaseCmd(zClient);
			Map<String, String> params = new HashMap<String, String>();
			// full-import
			// params.put("command", "full-import");
			// params.put("clean", "true");
			// params.put("commit", "true");
			// // delta-import
			// params.put("command", "delta-import");
			// params.put("clean", "false");
			// params.put("commit", "true");
			// status
			params.put("command", "status");
			try {
				System.out.println(cmd.execCmd(QueryType.DATAIMPORT, params));
			} catch (SolrServerException e) {
				e.printStackTrace();
			}

		}
		
	}
}
