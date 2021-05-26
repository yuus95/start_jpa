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

    @Id @GeneratedValue
    @Column(name ="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String name;


    /**
     * 테이블에 맞추어 모델링
     */
//    @Column(name="TEAM_ID")
//    private Long teamId;

    /**
     * 객체 지향 모델링
     */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID") // 이관계를 할 떄 조인하는 필드선정
    // 객체지향 설계시 일대다 인지 다대일인지 관계를 알려줘야 한다
    private Team team;


}
