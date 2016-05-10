package solrj.cmd;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.CoreAdminParams;
import solrj.client.SolrCloudClient;
import solrj.client.SolrSingleClient;
import solrj.client.ZSolrClient;
import solrj.params.QueryType;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

/**
 * solr reload core
 *
 * Created by zfy on 2016-04-29.
 */
public class Reload{
    public void reload(ZSolrClient zClient, String coreName) throws SolrServerException {
        CoreAdminRequest admin = new CoreAdminRequest();
        admin.setMethod(SolrRequest.METHOD.POST);
        try {
            CoreAdminResponse response = admin.reloadCore(coreName, zClient.getClient());
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        String url = "http://10.204.76.42:8983/solr/";
        String core = "demo";
        ZSolrClient zClient = new SolrSingleClient(url);
        Reload reload = new Reload();

        try {
            reload.reload(zClient, core);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }
}
