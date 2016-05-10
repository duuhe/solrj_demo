package solrj.cmd;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.response.CollectionAdminResponse;
import solrj.client.SolrCloudClient;
import solrj.client.ZSolrClient;

import java.io.IOException;

/**
 * solr cloud reload collectios
 *
 * Created by zfy on 2016-04-29.
 */
public class Reload2 {

    public void reload(ZSolrClient zClient, String core) throws IOException, SolrServerException {
        CollectionAdminRequest.Reload reload = new CollectionAdminRequest.Reload();
        reload.setCollectionName(core);

        // reload
        CollectionAdminResponse response = reload.process(zClient.getClient());

        int status = response.getStatus();

        System.out.println(status);
    }

    public static void main(String[] args){
        String zkHost = "10.204.76.79:2181,10.204.76.80:2181,10.204.76.42:2181";
        String core = "soft";
        ZSolrClient zClient = new SolrCloudClient(zkHost, core);
        Reload2 cmd = new Reload2();

        try {
            cmd.reload(zClient, core);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }
}
