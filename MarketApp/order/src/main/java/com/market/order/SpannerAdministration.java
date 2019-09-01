package com.market.order;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.spanner.*;
import com.google.spanner.admin.database.v1.CreateDatabaseMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SpannerAdministration {
    // String orderId, String item, String description, int quantity, String comments
    static final List<Order> orders;

    static {
        orders = new ArrayList<>();
        Order order1 = new Order("1", "green shirt",
                "Reebok green shirt",
                2l, "Bottle green shirt");
        orders.add(order1);
        Order order2 = new Order("1", "red shirt",
                "Reebok red shirt",
                3l, "Bright Red shirt");
        orders.add(order2);
    }

    static void createDatabase(DatabaseAdminClient dbAdminClient, DatabaseId id) {
        OperationFuture<Database, CreateDatabaseMetadata> op =
                dbAdminClient.createDatabase(
                        id.getInstanceId().getInstance(),
                        id.getDatabase(),
                        Arrays.asList(
                                "CREATE TABLE Orders ("
                                        + "  orderId    STRING(1024) NOT NULL,"
                                        + "  item       STRING(1024),"
                                        + "  description   STRING(1024),"
                                        + "  comments      STRING(1024),"
                                        + "  quantity      INT64"
                                        + ") PRIMARY KEY (orderId)"));
        try {
            // Initiate the request which returns an OperationFuture.
            Database db = op.get();
            System.out.println("Created database [" + db.getId() + "]");

            writeExampleData(id);
        } catch (ExecutionException e) {
            // If the operation failed during execution, expose the cause.
            throw (SpannerException) e.getCause();
        } catch (InterruptedException e) {
            // Throw when a thread is waiting, sleeping, or otherwise occupied,
            // and the thread is interrupted, either before or during the activity.
            throw SpannerExceptionFactory.propagateInterrupt(e);
        }
    }

    static void writeExampleData(DatabaseId id) {

        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();

        DatabaseClient dbClient = spanner.getDatabaseClient(id);
        List<Mutation> mutations = new ArrayList<>();
        for (Order order : orders) {
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
        }
        dbClient.write(mutations);
    }
}
