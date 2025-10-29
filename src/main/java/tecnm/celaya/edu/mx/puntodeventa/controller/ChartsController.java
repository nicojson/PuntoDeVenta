package tecnm.celaya.edu.mx.puntodeventa.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import tecnm.celaya.edu.mx.puntodeventa.dao.ReportingDAO;
import tecnm.celaya.edu.mx.puntodeventa.model.Product;

import java.util.List;
import java.util.Map;

public class ChartsController {

    @FXML
    private BarChart<String, Number> topProductsChart;
    @FXML
    private BarChart<String, Number> lowStockChart;

    private ReportingDAO reportingDAO;

    public void initialize(ReportingDAO reportingDAO) {
        this.reportingDAO = reportingDAO;
        loadChartData();
    }

    private void loadChartData() {
        // Cargar datos para el gráfico de productos más vendidos
        Map<String, Integer> topProducts = reportingDAO.getTop10SoldProducts();
        XYChart.Series<String, Number> topSeries = new XYChart.Series<>();
        topSeries.setName("Productos más vendidos");
        topProducts.forEach((productName, quantity) -> topSeries.getData().add(new XYChart.Data<>(productName, quantity)));
        topProductsChart.getData().add(topSeries);

        // Cargar datos para el gráfico de productos con poco stock
        List<Product> lowStockProducts = reportingDAO.getTop10LowStockProducts();
        XYChart.Series<String, Number> lowStockSeries = new XYChart.Series<>();
        lowStockSeries.setName("Productos con poco stock");
        lowStockProducts.forEach(product -> lowStockSeries.getData().add(new XYChart.Data<>(product.getName(), product.getStock())));
        lowStockChart.getData().add(lowStockSeries);
    }
}
