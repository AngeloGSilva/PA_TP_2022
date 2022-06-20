package pt.isec.pa.apoio_poe.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pt.isec.pa.apoio_poe.model.ProgManager;

import java.util.HashMap;

public class graficosUI extends GridPane {
    ProgManager manager;
    ObservableList<PieChart.Data> pieChartData;

    PieChart.Data da,ras,si;

    BarChart bc;

    XYChart.Series series1;

    String empresa1,empresa2,empresa3,empresa4,empresa5;

    HashMap hm;
    Label caption;

    Group r;
    public graficosUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        /*pieChartData =
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
        GridPane.setConstraints(chart, 10, 10);*/

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        bc = new BarChart<>(xAxis, yAxis);
        bc.setBarGap(30);
        bc.setTitle("Top 5 empresas com mais estágios");
        xAxis.setLabel("Nome da empresa");
        yAxis.setLabel("Número de estágios");

        series1 = new XYChart.Series<>();
        series1.setName("Número de estágios");

        this.getChildren().add(bc);
        GridPane.setConstraints(bc, 10, 10);

    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
        //double contadorTotal = manager.contaPropostaDA() + manager.contaPropostaRAS() + manager.contaPropostaSI();
        //da.setPieValue(manager.contaPropostaDA()/contadorTotal*100);
        //ras.setPieValue(manager.contaPropostaRAS()/contadorTotal*100);
        //si.setPieValue(manager.contaPropostaSI()/contadorTotal*100);
    }

    private void update() {
        hm = new HashMap<>();
        hm = manager.contaEstagios();

        if (hm != null) {
            if (hm.size() >= 1) {
                empresa1 = (String) hm.keySet().toArray()[0];
                series1.getData().add(new XYChart.Data(empresa1, hm.get(empresa1)));
            }
            if (hm.size() >= 2) {
                empresa2 = (String) hm.keySet().toArray()[1];
                series1.getData().add(new XYChart.Data(empresa2, hm.get(empresa2)));
            }
            if (hm.size() >= 3) {
                empresa3 = (String) hm.keySet().toArray()[2];
                series1.getData().add(new XYChart.Data(empresa3, hm.get(empresa3)));
            }
            if (hm.size() >= 4) {
                empresa4 = (String) hm.keySet().toArray()[3];
                series1.getData().add(new XYChart.Data(empresa4, hm.get(empresa4)));
            }
            if (hm.size() >= 5) {
                empresa5 = (String) hm.keySet().toArray()[4];
                series1.getData().add(new XYChart.Data(empresa5, hm.get(empresa5)));
            }
        }
        bc.getData().add(series1);
    }
}
