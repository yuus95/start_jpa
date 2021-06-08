package hello.jpa;

import org.hibernate.sql.Update;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class hellojpa {
    public static void main(String[] args){

        //엔티티 매니저  팩토리는 하나만 생성해서 애플리케이션 전체에서 공유 .
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //엔티티 매니저는 쓰레드간에 공유X (사용하고 버려야 한다)
        EntityManager em = emf.createEntityManager();


        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try{


            /**
             *  페치조인 --> 즉시로딩 효과를 준다. 최적화 작업할 때 주로 사용한다. 엔티티디의 대부분 연관관계속성은 LAZT이기떄문에 최적화 할 떄 사용
             *  벌크연산 - 업데이트나 딜리트문을 한번에 처리
             */

            Team teamA = new Team();
            teamA.setName("팀에이");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀비");
            em.persist(teamB);


            Member member1 = new Member();
            member1.setName("김유신");
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("장강산");
            em.persist(member2);

            Member member3 = new Member();
            member3.setName("윤찬호");
            em.persist(member3);

            teamA.addMember(member1);
            teamA.addMember(member2);
            teamB.addMember(member3);

            em.flush();
            em.clear();


            //벌크업 연산  --> update
            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            System.out.println("update ==> " + resultCount);
            em.clear();

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember   "   + findMember );

//            List<Team> resultList = em.createQuery(Query, Team.class).getResultList();
//
//            for (Team team : resultList){
//                System.out.println("Team list == >> "+ team.getName() + "Member"  + team.getMembers())  ;
//                //그냥 조인을 사용했을 경우
//                //회원1 , 팀A(SQL)
//                //회원2, 팀A(1차캐시)
//                //회원3, 팀B(SQL)
//                // 회원 100명( 다 다른팀) ==> N+1
//
//                // fetch조인하면 한방쿼리를 보내기 떄문에 N+1발생 xx
//            }


            /**
             * TypeQuery, Query
             *
             * TypeQuery : 반환 타입이 병확할 떄 사용
             * Query : 반환 타입이 명확하지 않을 떄 사용 (name,age)
             */

//            Team team = new Team();
//            team.setName("TeamA'");
//            em.persist(team);
//
//
//            Member member1 = new Member();
//            member1.setName("Hello");
//            member1.setAge(10);
//
//            em.persist(member1);
//
//            team.addMember(member1);
//
//            em.flush();
//            em.clear();
//
//            //파라미터 바인딩
//            List<Member> resultList = em.createQuery("Select  m from Member m where m.name =:named", Member.class)
//                    .setParameter("named", "Hello")
//                    .getResultList();
//
//            for (Member m : resultList){
//                System.out.println("===>  "+ m.getName());
//            }
//
//
//            //여러 값 선택시 query사용
//            List<Object[]> resultList1 = em.createQuery("Select m.age,m.name from Member m ", Object[].class).getResultList();
//
//            for (Object[] o :resultList1){
//                System.out.println("age ==> " + (Integer) o[0]);
//                System.out.println("name ==> " + (String)o[1]);
//            }


//            em.createQuery("Select m,t from Member m join m.team t ")


            //Query
//            Query query = em.createQuery("select m.age,m.name from Member m");
//            List resultList = query.getResultList();
//
//            for (Object o : resultList){
//                    Object[] result = (Object[]) o;
//                    System.out.println("name"+ result[1]);
//                    System.out.println("age" + result[0]);
//
//            }
//            /**
//             * 값 타입 컬렉션  저장- 일대다로 하기
//             */
//            Member member = new Member();
//            member.setName("하위");
//            member.setHomeAddress(new Address("homeCity","sohaStreet","1000"));
//
//            member.getAddressList().add(new AddressEntity("oldCity1","sohaStreet","1000"));
//            member.getAddressList().add(new AddressEntity("oldCity2","sohaStreet","1000"));
//
//
//            em.persist(member);
//
//
//            em.flush();
//            em.clear();





            /**
             * 값타입 비교
             */

//            Address address1 = new Address("city","seoul","zz");
//            Address address2 = new Address("city","seoul","zz");
//
//            //True
//            System.out.println("address.eqault() ==> " + address1.equals(address2));

            /**
             * 임베디드 활용
             */
//            Member member =new Member();
//            member.setName("Hell");
//
//            member.setHomeAddress(new Address("city","street","zipcode"));
//            member.setWorkPeriod(new Period());
//            em.persist(member);

            /**
             * cascade
             */
//            Parent parent = new Parent();
//            Child child1 =new Child();
//            Child child2=new Child();
//
//            parent.addChild(child1);
//            parent.addChild(child2);
//            em.persist(parent);
//
//            em.flush();
//            em.clear();
//
//            Parent findParent = em.find(Parent.class, parent.getId());
//
//            findParent.getChildList().remove(0);
//

            /**
             * 프록시 연습
             */
//            Member member1 = new Member();
//            member1.setName("Hello");
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setName("Hello2");
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember = " + refMember.getClass());// Proxy
//
//
//            System.out.println("beFore ==?");
//            String refName = refMember.getName();// 프록시 강제 초기화
//            System.out.println("AFTER ===========");
//
//            System.out.println("refname==>"+ refName);
            //Hibernate.initialize(refMember); // 강제 초기화

            //초기화 여부 확인
//            System.out.println("==> " +emf.getPersistenceUnitUtil().isLoaded(refMember));




//            프록시 초기화 오류
//            em.detach(refMember);
//            em.clear();
//            refMember.getName();
            /**
             * 상속관계 연습
             */
//            Movie movie = new Movie();
//            movie.setDirecotr("aaa");
//            movie.setActor("bbbb");
//            movie.setName("바람과 함께 사라지다");
//            movie.setPrice(10000);
//            em.persist(movie);
//
//            em.flush();
//            em.clear();
//
//            Movie findMove = em.find(Movie.class, movie.getId());
//
//            System.out.println("findMove => "+ findMove);





            /**
             * 연관관계 정리
             */
//           Team team = new Team();
//           team.setName("TeamA");
//           em.persist(team);
//
//           Member member = new Member();
//          member.changeTeam(team);  // 연관관계 메소드 team.getMember를 안써도 된다
//           member.setName("A"); // **
//           em.persist(member);
//
//           team.addMember(member);



           //객체지향적으로 양쪽에 데이터를 넣는게 맞다.
//           team.getMember().add(member); //**
//           em.flush();
//           em.clear();


            /**
             * 쿼리문 전체 조회
             */
           // team1은 순수한 객체상태태
//           Team team1 = em.find(Team.class, team.getId()); // 1차 캐시
//            List<Member> member1 = team1.getMembers();
//
//            for (Member m : member1){
//                System.out.println("member --> " + m.getName());
//            }

//            DB쿼리 보는법
//            em.flush(); 영속성 컨테스트에 있는 DB쿼리 DB에 전송
//            em.clear(); 영속성 컨테스트 초기화

            /**
             * 참조 레퍼런스 변경
             */
//            Member member1 = em.find(Member.class, 1);
//            Team newTeam = new Team();
//            member1.setTeam(newTeam);

            tx.commit(); // comit 필수! 커밋을 해야 JPA가 인식을 함

        }
        catch(Exception e){
            tx.rollback();

        }
        finally {
            em.close();
        }
        //close
        emf.close();
    }
}
