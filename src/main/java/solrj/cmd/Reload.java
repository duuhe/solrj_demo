package solrj.cmd;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.CoreAdminParams;
import solrj.client.SolrCloudClient;
import solrj.client.ZSolrClient;
import solrj.params.QueryType;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by LINGQ on 2016-04-29.
 */
public class Reload extends BaseCmd {
    public Reload(ZSolrClient zClient) {
        super(zClient);
    }


    public void reload(String core) throws SolrServerException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "reload");
        params.put("name", core);

        QueryResponse response = execCmd(QueryType.COLLECTIONS, params);

        System.out.println(response);
    }

    public static void main(String[] args){
        String zkHost = "10.204.76.79:2181,10.204.76.80:2181,10.204.76.99:2181";
        String core = "admin";
        ZSolrClient zClient = new SolrCloudClient(zkHost, core);
        Reload cmd = new Reload(zClient);

        try {
            cmd.reload("soft");
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }
}
