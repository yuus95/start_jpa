package hello.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


// 객체와 테이블을 매핑시킨다.
@Entity  //JPA가 관리할 객체
@Getter
@Setter
public class Member {
    @Id // 데이터베이스 pk와 매핑
    private Long id;

    @Column(name="name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // 데이터베이스한테 시간 형태를 알려주기위해
    private LocalDate createDate; //년월

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastModifiedDate; //년월일

    @Lob // varchar보다 큰 타입을 넣기 위해 사용한다.
    private String description;

//date타입은 옛날 버전 자바8이상은 LocalDate쓰기
}
