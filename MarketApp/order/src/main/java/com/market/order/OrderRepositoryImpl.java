package com.market.order;

import com.google.cloud.spanner.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRepositoryImpl implements OrderRepository {
    private static final Log LOGGER = LogFactory.getLog(OrderApplication.class);


    @Value("${spanner.instanceId}")
    public String instanceId;

    @Value("${spanner.databaseId}")
    public String databaseId;

    @Override
    public List<Order> getOrders() {

        List<Order> response = new ArrayList<>();
        // Instantiates a client
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();


        try {
            // Creates a database client
            DatabaseClient dbClient = spanner.getDatabaseClient(DatabaseId.of(options.getProjectId(), instanceId, databaseId));
            // Queries the database
            ResultSet resultSet = dbClient.singleUse().executeQuery(Statement.of("SELECT orderId, item, description,comments,quantity FROM orders"));

            LOGGER.debug("\n\nResults:");
            // Prints the results
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getString("orderId"));
                order.setItem(resultSet.getString("item"));
                order.setDescription(resultSet.getString("description"));
                order.setComments(resultSet.getString("comments"));
                order.setQuantity(resultSet.getLong("quantity"));
                response.add(order);
            }
        } finally {
            spanner.close();
        }
        return response;
    }

    @Override
    public void saveOrders(Order order) {
        // Instantiates a client
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();


        try {
            // Creates a database client
            DatabaseClient dbClient = spanner.getDatabaseClient(DatabaseId.of(
                    options.getProjectId(), instanceId, databaseId));
            // Queries the database

            List<Mutation> mutations = new ArrayList<>();
            mutations.add(
                    Mutation.newInsertBuilder("Orders")
                            .set("orderId")
                            .to(order.getOrderId())
                            .set("item")
                            .to(order.getItem())
                            .set("description")
                            .to(order.getDescription())
                            .set("comments")
                            .to(order.getComments())
                            .set("quantity")
                            .to(order.getQuantity())
                            .build());

            dbClient.write(mutations);
        } finally {
            // Closes the client which will free up the resources used
            spanner.close();
        }
    }

    @Override
    public void init() {
// [START init_client]
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();
        try {
            //String command = args[0];
            DatabaseId db = DatabaseId.of(options.getProjectId(), instanceId, databaseId);
            // [END init_client]
            // This will return the default project id based on the environment.
            String clientProject = spanner.getOptions().getProjectId();
            if (!db.getInstanceId().getProject().equals(clientProject)) {
                LOGGER.error(
                        "Invalid project specified. Project in the database id should match"
                                + "the project name set in the environment variable GCLOUD_PROJECT. Expected: "
                                + clientProject);
                //printUsageAndExit();
            }
            // [START init_client]
            DatabaseClient dbClient = spanner.getDatabaseClient(db);
            DatabaseAdminClient dbAdminClient = spanner.getDatabaseAdminClient();
            // Use client here...
            // [END init_client]
            SpannerAdministration.createDatabase(dbAdminClient, db);
            // [START init_client]
        } finally {
            spanner.close();
        }
        // [END init_client]
        System.out.println("Closed client");
    }
}
