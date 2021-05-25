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


        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            //먼저 DB에서 조회한 뒤 1차 캐시에 저장
            Member result1 = em.find(Member.class, 1L);
            System.out.println("1");

            // 1차캐시에서 조회
            Member result2 = em.find(Member.class, 1l);
            System.out.println("2");




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
