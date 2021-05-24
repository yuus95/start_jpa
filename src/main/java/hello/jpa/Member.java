package hello.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


// 객체와 테이블을 매핑시킨다.
@Entity  //JPA가 관리할 객체
@Getter
@Setter
public class Member {



    @Id // 데이터베이스 pk와 매핑
    private Long id;
    private String name;


}
