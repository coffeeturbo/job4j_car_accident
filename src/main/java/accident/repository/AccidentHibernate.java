package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccidentHibernate implements accident.repository.Repository {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select distinct a from Accident a left join fetch a.type left outer join fetch a.rules", Accident.class)
                    .list();
        }
    }

    @Override
    public Accident create(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(accident);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accident;
    }

    @Override
    public Accident update(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(accident);
            session.getTransaction().commit();
        }

        return accident;
    }

    @Override
    public Accident findAccidentById(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident c WHERE c.id=:id", Accident.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    @Override
    public List<AccidentType> getAccidentTypes() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    @Override
    public AccidentType findTypeById(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType a WHERE a.id=:id", AccidentType.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    @Override
    public List<Rule> getRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }

    @Override
    public Rule findRuleById(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule r WHERE r.id=:id", Rule.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }
}
