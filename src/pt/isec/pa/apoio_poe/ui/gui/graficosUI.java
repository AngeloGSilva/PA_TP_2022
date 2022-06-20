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
    ObservableList<PieChart.Data> pieChartData,pieChartData2;

    PieChart.Data da,ras,si,pa,pna;

    BarChart bc,bc2;

    XYChart.Series series1,series2;

    String emp1,emp2,emp3,emp4,emp5,doc1,doc2,doc3,doc4,doc5;

    HashMap hm,hm2;

    public graficosUI(ProgManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        //PieChar dos ramos
        pieChartData =
                FXCollections.observableArrayList(
                        da = new PieChart.Data("DA -" + manager.contaPropostaDA(), 0.0),
                        ras = new PieChart.Data("RAS -" + manager.contaPropostaRAS(), 0.0),
                        si = new PieChart.Data("SI -" + manager.contaPropostaSI(), 0.0));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Propostas por ramo");
        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);

        this.getChildren().add(chart);
        GridPane.setConstraints(chart, 10, 10);

        //PieChart de propostas atribuidas vs nao atribuidas
        pieChartData2 =
                FXCollections.observableArrayList(
                        pa = new PieChart.Data("Atribuidas", 0.0),
                        pna = new PieChart.Data("N/Atribuidas", 0.0));
        final PieChart chart2 = new PieChart(pieChartData2);
        chart2.setTitle("Tipo de propostas");
        chart2.setLabelLineLength(10);
        chart2.setLegendSide(Side.RIGHT);

        this.getChildren().add(chart2);
        GridPane.setConstraints(chart2, 20, 10);

        //barchart de top de empresas
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        bc = new BarChart<>(xAxis, yAxis);
        bc.setBarGap(20);
        bc.setTitle("Top de empresas com mais estágios [T1]");
        xAxis.setLabel("Empresa");
        yAxis.setLabel("Número de estágios");

        series1 = new XYChart.Series<>();
        series1.setName("Número de estágios");

        this.getChildren().add(bc);
        GridPane.setConstraints(bc, 20, 20);

        //barchart de top Docentes com mais orientações
        final CategoryAxis xAxis2 = new CategoryAxis();
        final NumberAxis yAxis2 = new NumberAxis();
        bc2 = new BarChart<>(xAxis2, yAxis2);
        bc2.setBarGap(20);
        bc2.setTitle("Top de docentes com mais orientações");
        xAxis2.setLabel("Docente");
        yAxis2.setLabel("Número de orientações");

        series2 = new XYChart.Series<>();
        series2.setName("Número de orientações");

        this.getChildren().add(bc2);
        GridPane.setConstraints(bc2, 10, 20);

    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
        double contadorTotal = manager.contaPropostaDA() + manager.contaPropostaRAS() + manager.contaPropostaSI();
        da.setPieValue(manager.contaPropostaDA()/contadorTotal*100);
        ras.setPieValue(manager.contaPropostaRAS()/contadorTotal*100);
        si.setPieValue(manager.contaPropostaSI()/contadorTotal*100);
        double contadorTotal2 = manager.contaPropostasAtribuidas() + manager.contaPropostasNaoAtribuidas();
        pa.setPieValue(manager.contaPropostasAtribuidas()/contadorTotal2*100);
        pna.setPieValue(manager.contaPropostasNaoAtribuidas()/contadorTotal2*100);
    }

    private void update() {
        //barchart top empresas
        hm = new HashMap<>();
        hm2 = new HashMap<>();
        hm2 = manager.contaOrientacoes();
        hm = manager.contaEstagios();

        if (hm != null) {
            if (hm.size() >= 1) {
                emp1 = (String) hm.keySet().toArray()[0];
                series1.getData().add(new XYChart.Data(emp1, hm.get(emp1)));
            }
            if (hm.size() >= 2) {
                emp2 = (String) hm.keySet().toArray()[1];
                series1.getData().add(new XYChart.Data(emp2, hm.get(emp2)));
            }
            if (hm.size() >= 3) {
                emp3 = (String) hm.keySet().toArray()[2];
                series1.getData().add(new XYChart.Data(emp3, hm.get(emp3)));
            }
            if (hm.size() >= 4) {
                emp4 = (String) hm.keySet().toArray()[3];
                series1.getData().add(new XYChart.Data(emp4, hm.get(emp4)));
            }
            if (hm.size() >= 5) {
                emp5 = (String) hm.keySet().toArray()[4];
                series1.getData().add(new XYChart.Data(emp5, hm.get(emp5)));
            }
        }
        bc.getData().add(series1);

        if (hm2 != null) {
            if (hm2.size() >= 1) {
                doc1 = (String) hm2.keySet().toArray()[0];
                series2.getData().add(new XYChart.Data(doc1, hm2.get(doc1)));
            }
            if (hm2.size() >= 2) {
                doc2 = (String) hm2.keySet().toArray()[1];
                series2.getData().add(new XYChart.Data(doc2, hm2.get(doc2)));
            }
            if (hm2.size() >= 3) {
                doc3 = (String) hm2.keySet().toArray()[2];
                series2.getData().add(new XYChart.Data(doc3, hm2.get(doc3)));
            }
            if (hm2.size() >= 4) {
                doc4 = (String) hm2.keySet().toArray()[3];
                series2.getData().add(new XYChart.Data(doc4, hm2.get(doc4)));
            }
            if (hm2.size() >= 5) {
                doc5 = (String) hm2.keySet().toArray()[4];
                series2.getData().add(new XYChart.Data(doc5, hm2.get(doc5)));
            }
        }
        bc2.getData().add(series2);
    }
}

