package solrj.cmd;

import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;

public class ReloadMMsegDicTest {
	private String url = "10.204.76.80";
	private String core = "soft";
	private String port = "8080";

	@Test
	public void reload() {
		ReloadMMsegDic reloadDic = new ReloadMMsegDic();
		try {
			System.out.println(reloadDic.reload(url, core, port));
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
