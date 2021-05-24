package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class hellojpa {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        Member member = new Member();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            List<Member> result = em.createQuery("select m from Member as m",Member.class)
                    .setFirstResult(1) // 페이징기법
                    .setMaxResults(10) // 페이징 기법
                    .getResultList(); // 리스트 가져오는법

            for (Member member1 : result){
                System.out.println("member.name="+member1.getName());
            }

//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("yushin kim");

//            System.out.println("findMember.id = "+findMember.getId());
//            System.out.println("findMember.name = "+findMember.getName());

            // em.remove(findMember 삭제
            // em.persist(member);
            tx.commit();

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
