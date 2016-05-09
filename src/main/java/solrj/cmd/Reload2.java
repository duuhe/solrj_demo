package solrj.cmd;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.common.params.CoreAdminParams.CoreAdminAction;
import org.apache.solr.common.util.NamedList;
import solrj.client.SolrCloudClient;
import solrj.client.ZSolrClient;

import java.io.IOException;

/**
 * Created by LINGQ on 2016-04-29.
 */
public class Reload2 {
    private String zkHost = "10.204.76.79:2181,10.204.76.80:2181,10.204.76.99:2181";
    private String core = "admin";

    public void reload(ZSolrClient zClient, String core) throws IOException, SolrServerException {
        CoreAdminRequest admin = new CoreAdminRequest();
        admin.setAction(CoreAdminAction.RELOAD);
        admin.setCoreName(core);

        CoreAdminResponse response = admin.process(new CloudSolrClient(zkHost));

        NamedList<NamedList<Object>> status = response.getCoreStatus();

        System.out.println(status);
    }

    public static void main(String[] args){
        String zkHost = "10.204.76.79:2181,10.204.76.80:2181,10.204.76.99:2181";
        String core = "admin";
        ZSolrClient zClient = new SolrCloudClient(zkHost, core);
        Reload2 cmd = new Reload2();

        try {
            cmd.reload(zClient, "soft");
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }
}
