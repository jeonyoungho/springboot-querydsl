package study.querydsl.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Getter
@Setter
@Entity
public class Hello { // Q파일은 git에 올리면 안됨, 자동으로 빌드할때 싱긴다.

    @Id
    @GeneratedValue
    private Long id;
}
