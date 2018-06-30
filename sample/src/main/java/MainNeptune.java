import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph;

/**
 * This class aims to create a sample GraphDb on AWS Neptune
 * 
 * @author rodrigo.pereira@microsoft.com
 *
 */
public class MainNeptune {

	public static void main(String[] args) {
		// creates 
		Cluster.Builder builder = Cluster.build();
        builder.addContactPoint("url_neptune");
        builder.port(8182);
        
        Cluster cluster = builder.create();
        Client client = cluster.connect();
        
        for(int i=0; i < 1000000; i++) {
        	ResultSet results = client.submit("g.addV(\"person\").property(\"name\", \"bob"+i+"\").as(\"bob"+i+"\").\r\n" + 
            		"           addV(\"person\").property(\"name\", \"stephen"+i+"\").as(\"stephen"+i+"\").\r\n" + 
            		"           addV(\"company\").property(\"name\", \"Blueprints, Inc"+i+"\").as(\"blueprints"+i+"\").\r\n" + 
            		"           addV(\"company\").property(\"name\", \"Rexster, LLC"+i+"\").as(\"rexster"+i+"\").\r\n" + 
            		"           addV(\"job\").property(\"name\", \"job1"+i+"\").as(\"blueprintsJob1"+i+"\").\r\n" + 
            		"           addV(\"job\").property(\"name\", \"job2"+i+"\").as(\"blueprintsJob2"+i+"\").\r\n" + 
            		"           addV(\"job\").property(\"name\", \"job3"+i+"\").as(\"blueprintsJob3"+i+"\").\r\n" + 
            		"           addV(\"job\").property(\"name\", \"job4"+i+"\").as(\"rexsterJob1"+i+"\").\r\n" + 
            		"           addV(\"application\").property(\"name\", \"application1"+i+"\").as(\"appBob1"+i+"\").\r\n" + 
            		"           addV(\"application\").property(\"name\", \"application2"+i+"\").as(\"appBob2"+i+"\").\r\n" + 
            		"           addV(\"application\").property(\"name\", \"application3"+i+"\").as(\"appStephen1"+i+"\").\r\n" + 
            		"           addV(\"application\").property(\"name\", \"application4"+i+"\").as(\"appStephen2"+i+"\").\r\n" + 
            		"           addE(\"completes\").from(\"bob"+i+"\").to(\"appBob1"+i+"\").\r\n" + 
            		"           addE(\"completes\").from(\"bob"+i+"\").to(\"appBob2"+i+"\").\r\n" + 
            		"           addE(\"completes\").from(\"stephen"+i+"\").to(\"appStephen1"+i+"\").\r\n" + 
            		"           addE(\"completes\").from(\"stephen"+i+"\").to(\"appStephen2"+i+"\").\r\n" + 
            		"           addE(\"appliesTo\").from(\"appBob1"+i+"\").to(\"blueprintsJob1"+i+"\").\r\n" + 
            		"           addE(\"appliesTo\").from(\"appBob2"+i+"\").to(\"blueprintsJob2"+i+"\").\r\n" + 
            		"           addE(\"appliesTo\").from(\"appStephen1"+i+"\").to(\"rexsterJob1"+i+"\").\r\n" + 
            		"           addE(\"appliesTo\").from(\"appStephen2"+i+"\").to(\"blueprintsJob3"+i+"\").\r\n" + 
            		"           addE(\"created\").from(\"blueprints"+i+"\").to(\"blueprintsJob1"+i+"\").property(\"creationDate\", \"12/20/2015\").\r\n" + 
            		"           addE(\"created\").from(\"blueprints"+i+"\").to(\"blueprintsJob2"+i+"\").property(\"creationDate\", \"12/15/2015\").\r\n" + 
            		"           addE(\"created\").from(\"blueprints"+i+"\").to(\"blueprintsJob3"+i+"\").property(\"creationDate\", \"12/16/2015\").\r\n" + 
            		"           addE(\"created\").from(\"rexster"+i+"\").to(\"rexsterJob1"+i+"\").property(\"creationDate\", \"12/18/2015\")");
        	
            System.out.println("Current count: "+i);
            try {
				Thread.sleep(17);
			} catch (InterruptedException e) {}
        }
        
//        ResultSet results = client.submit("g.V().count()");
//        System.out.println( results.iterator().next());
        cluster.close();
	}
}

//Count2896964
// result{object=34034880 class=java.lang.Long}


//ResultSet results = client.submit("g.V().count()");

//ResultSet results = client.submit("g.V().drop().iterate()");


//System.out.println( results.iterator().next());
//GraphTraversalSource g = EmptyGraph.instance().traversal().withRemote(DriverRemoteConnection.using(cluster));
//
//GraphTraversal t = g.V().limit(2).valueMap();
//t.forEachRemaining(
//  e ->  System.out.println(e)
//);

