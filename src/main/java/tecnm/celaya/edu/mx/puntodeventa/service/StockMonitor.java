package tecnm.celaya.edu.mx.puntodeventa.service;

import javafx.application.Platform;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;
import tecnm.celaya.edu.mx.puntodeventa.model.Product;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StockMonitor {

    private final ProductService productService;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public StockMonitor(ProductService productService) {
        this.productService = productService;
    }

    public void startMonitoring() {
        scheduler.scheduleAtFixedRate(this::checkStock, 0, 5, TimeUnit.MINUTES);
    }

    public void stopMonitoring() {
        scheduler.shutdown();
    }

    private void checkStock() {
        List<Product> lowStockProducts = productService.getLowStockProducts();
        if (!lowStockProducts.isEmpty()) {
            Platform.runLater(() -> showLowStockAlert(lowStockProducts));
        }
    }

    private void showLowStockAlert(List<Product> products) {
        String title = "Alerta de Stock Bajo";
        StringBuilder message = new StringBuilder("Los siguientes productos tienen un stock bajo:\n\n");
        for (Product product : products) {
            message.append(String.format("- %s (%s): %d unidades\n", product.getName(), product.getCategory(), product.getStock()));
        }

        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(message.toString())
                .position(Pos.BOTTOM_RIGHT)
                .hideAfter(javafx.util.Duration.seconds(15));

        notificationBuilder.showWarning();
    }
}
