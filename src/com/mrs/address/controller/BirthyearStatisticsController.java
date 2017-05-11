package com.mrs.address.controller;

import com.mrs.address.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by marus505 on 2017. 5. 11..
 */
public class BirthyearStatisticsController {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> yearNames = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

    }

    public void setPersonData(List<Person> persons) {
        // 연락처 생일의 년도 개수를 누적한다.
        Map<String, Integer> yearCounter = new HashMap<>();

        for (Person p : persons) {
            int year = p.getBirthDay().getYear();
            String strYear = String.valueOf(year);

            if (yearCounter.containsKey(strYear)) {
                int newValue = yearCounter.get(strYear) + 1;
                yearCounter.replace(strYear, newValue);
            } else {
                yearCounter.put(strYear, 1);
            }
        }

        // 년도를 내림차순으로 정렬한다.
        Map<String, Integer> treeCounter = new TreeMap<>(yearCounter);

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        yearNames.addAll(treeCounter.keySet());

        // 월별로 XYChart.Data 객체를 만든다. series 에 추가한다.
        for (int i = 0; i < treeCounter.size(); i++) {
            series.getData().add(new XYChart.Data<>(yearNames.get(i), treeCounter.get(yearNames.get(i))));
        }

        // 수평축에 월 이름을 카테고리로 할당한다.
        xAxis.setCategories(yearNames);

        barChart.getData().add(series);
    }
}
