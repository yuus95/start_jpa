package hello.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

// 둘중 하나만 사용해도 되지만 둘다 사용하기
//  @Embedded
//  @Embeddable

    //기간 Period
    @Embedded
    private Period workPeriod;

    // 주소
    @Embedded
    private Address homeAddress;

  //  @AttrbuteOverride:속성 사용법 -> 위에 address클래스를 사용하고 한번 더 사용 할 경우
    //직장주소
//    @Embedded
//    @AttributeOverrides(
//        @AttributeOverride(name="city",
//            column = @Column(name = "WORK_CITY")),
//        @AttributeOverride(name="street",
//            column = @Column(name = "WORK_STREET")),
//        @AttributeOverride(name="zipcode",
//            column = @Column(name = "WORK_ZIPCODE"))
//    )
//    private Address workAddress;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProductList=  new ArrayList<>();

    /**
     * 테이블에 맞추어 모델링
     */
//    @Column(name="TEAM_ID")
//    private Long teamId;

    /**
     * 객체 지향 모델링
     */
    @ManyToOne
    @JoinColumn(name ="TEAM_ID") // 관계를 할 떄 조인하는 필드선정
    // 객체지향 설계시 일대다 인지 다대일인지 관계를 알려줘야 한다
    private Team team;

    //연관관계 편의 메소드 --> 양쪽에 값을 설정하는걸 한번에 할 수 있다.
//    public void changeTeam(Team team){
//        this.team = team;
//        team.getMembers().add(this);
//    }



}
