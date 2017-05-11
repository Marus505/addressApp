package com.mrs.address.controller;

import com.mrs.address.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 생일 통계 뷰에 대한 컨트롤러
 *
 * Created by marus505 on 2017. 5. 11.
 */
public class BirthdayStatisticsController {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // 영어 월 이름을 배열로 가져온다.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // 리스트로 변환하고 나서 ObservableList 에 추가한다.
        monthNames.addAll(Arrays.asList(months));

        // 수평축에 월 이름을 카테고리로 할당한다.
        xAxis.setCategories(monthNames);
    }

    public void setPersonData(List<Person> persons) {
        // 연락처 생일의 월 개수를 누적한다.
        int[] monthCounter = new int[12];
        for (Person p : persons) {
            int month = p.getBirthDay().getMonthValue() - 1;
            monthCounter[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // 월별로 XYChart.Data 객체를 만든다. series 에 추가한다.
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }

        barChart.getData().add(series);
    }
}
