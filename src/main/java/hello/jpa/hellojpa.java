package hello.jpa;

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

        Member member = new Member();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            //전체 회원 조회 -- 검색 쿼리 생성
            List<Member> result = em.createQuery("select m from Member as m",Member.class)
                    //.setFirstResult(1) // 페이징기법
                    //.setMaxResults(10) // 페이징 기법
                    .getResultList(); // 리스트 가져오는법
            // 단일 회원 조회
            Member result2 = em.find(Member.class, 1L);

            //회원삭제
            em.remove(result2);

            //엔티티를 영속화 시킨다. (엔티티를 영구 저장하는 환경에 보낸다?)
            em.persist(result2);


            for (Member member1 : result){
                System.out.println("member.name="+member1.getName());
            }

//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("yushin kim");

//            System.out.println("findMember.id = "+findMember.getId());
//            System.out.println("findMember.name = "+findMember.getName());


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
