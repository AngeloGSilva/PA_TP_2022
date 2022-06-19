package pt.isec.pa.apoio_poe.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pt.isec.pa.apoio_poe.model.ProgManager;

public class graficosUI extends GridPane {
    ProgManager manager;
    ObservableList<PieChart.Data> pieChartData;

    PieChart.Data da,ras,si;

    Label caption;

    Group r;

    public graficosUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        pieChartData =
                FXCollections.observableArrayList(
                        da = new PieChart.Data("DA", 0.0),
                        ras = new PieChart.Data("RAS", 0.0),
                        si = new PieChart.Data("SI", 0.0));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Distribuição de estágios/projetos por ramo");
        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);
        caption = new Label("teste");
        caption.setTextFill(Color.BLUE);
        caption.setStyle("-fx-font: 24 arial;");

        r = new Group(chart, caption);
        r.maxWidth(500);

        this.getChildren().add(chart);
        GridPane.setConstraints(chart, 10, 10);
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
        double contadorTotal = manager.contaPropostaDA() + manager.contaPropostaRAS() + manager.contaPropostaSI();
        da.setPieValue(manager.contaPropostaDA()/contadorTotal*100);
        ras.setPieValue(manager.contaPropostaRAS()/contadorTotal*100);
        si.setPieValue(manager.contaPropostaSI()/contadorTotal*100);
    }

    private void update() {

    }
}
