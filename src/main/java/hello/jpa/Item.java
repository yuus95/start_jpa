package hello.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE ) // JOIND(테이블을 따로 분리해서 생성), SINGLE_TABLE(하나의 테이블로 생성), TABLE_PER_CLASS (사용 xx 각 테이블로 나눔)
@DiscriminatorColumn
public abstract class Item {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;


}
