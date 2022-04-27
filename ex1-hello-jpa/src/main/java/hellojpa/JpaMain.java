package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLOutput;


/**
 * 영속성 컨텍스트 = 엔티티를 영구 저장하는 환경
 * EntityManager.persist(entity); //db에 저장이 아닌 영속성 컨텍스트에 저장한다는 뜻
 *
 */
public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //비영속
            Member member1 = new Member();
            member1.setId(101L);
            member1.setName("HelloJPA");
            
            //영속
            System.out.println("====before===");
            em.persist(member1);
            System.out.println("=====after=====");
            em.find(Member.class, 101L);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();

        } finally {
            em.close();
        }
        emf.close();

    }
}
