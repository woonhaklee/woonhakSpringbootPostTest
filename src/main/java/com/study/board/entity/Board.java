package com.study.board.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // DB의 Table을 의미함
@Data
public class Board { // mysql의 Column Name과 Datatype와 맞게 설정

    @Id
    //JPA가 객체를 관리할 때 식별할 기본키를 지정한다.
    //@Id만 사용할 경우 기본 키를 직접 할당해 주어야 합니다.
    //기본 키를 직접 할당하는 대신 데이터베이스가 생성해주는 값을 사용하려면 @GeneratedValue를 사용해주면 됩니다.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue
    // 기본 키를 자동 생성해주는 어노테이션E니다.
    // 속성으로는 strategy가 있는데, 이를 통해 자동 생성 전략을 지정해 줄 수 있습니다.
    // 이제부터 각각의 전략들을 살펴보도록 하겠습니다.

    // IDENTITY
    // IDENTITY 전략은 기본 키 생성을 데이터베이스에 위임하는 전략입니다.
    // 주로 MySQL, PostgreSQL, SQL Server에서 사용합니다.
    // 예를 들어 MySQL의 AUTO_INCREMENT 기능은 데이터베이스가 기본 키를 자동으로 생성해준다.
    // IDENTITY 전략은 AUTO_INCREMENT처럼 데이터베이스에 값을 저장하고 나서야 기본 키 값을 구할 수 있을 때 사용한다.

    private Integer id;
    //int : 자료형(primitive type)
    //산술 연산 가능함
    //null로 초기화 불가

    //Integer : 래퍼 클래스 (Wrapper class)
    //Unboxing하지 않을 시 산술 연산 불가능함
    //null값 처리 가능

    private String title;

    private String content;

    private String filename;

    private String filepath;

}