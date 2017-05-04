package com.mrs.address.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 연락처 리스트를 감싸는 헬퍼 클래스이다.
 * XML 로 저장하는 데 사용된다.
 *
 * Created by marus505 on 2017. 5. 4..
 *
 */

@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<Person> persons;

    @XmlElement(name = "person")
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
