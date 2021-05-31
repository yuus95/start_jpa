package hello.jpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
             * 프록시 연습
             */
            Member member1 = new Member();
            member1.setName("Hello");
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("Hello2");
            em.persist(member2);


            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass());// Proxy
            refMember.getName(); // 프록시 강제 초기화 

            //Hibernate.initialize(refMember); // 강제 초기화

            System.out.println("==> " +emf.getPersistenceUnitUtil().isLoaded(refMember));




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
