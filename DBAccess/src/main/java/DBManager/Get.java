package DBManager;

import Models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: Игорь
 * Date: 2/19/14
 * Time: 5:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Get {
    public static java.util.List<Publication> getPublication()
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");// populates the data of the
        // configuration file

        // creating seession factory object
        SessionFactory factory = cfg.buildSessionFactory();

        // creating session object
        Session session = factory.openSession();

        // creating transaction object
        Transaction t = session.beginTransaction();

        Query query = session.createQuery("from Publication ");
        java.util.List<Publication> list = query.list();
        t.commit();
        session.close();
        return list;
    }

    public static List<Profile> listProfiles(){
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");// populates the data of the
        // configuration file

        // creating seession factory object
        SessionFactory factory = cfg.buildSessionFactory();

        // creating session object
        Session session = factory.openSession();

        // creating transaction object
        Transaction t = session.beginTransaction();

        Query query = session.createQuery("from Profile ");
        java.util.List<Profile> list = query.list();
        t.commit();
        session.close();
        return list;
    }

    public static Profile getProfile(Long id) {
        Profile profile;
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");// populates the data of the
        // configuration file

        // creating seession factory object
        SessionFactory factory = cfg.buildSessionFactory();

        // creating session object
        Session session = factory.openSession();

        // creating transaction object
        Transaction t = session.beginTransaction();

        profile = (Profile)session.get(Profile.class, id);
        t.commit();
        session.close();
        return profile;
    }

    public static void updateProfile(Profile profile, Long profileID) {
        Profile loadedProfile;
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");// populates the data of the
        // configuration file

        // creating seession factory object
        SessionFactory factory = cfg.buildSessionFactory();

        // creating session object
        Session session = factory.openSession();

        // creating transaction object
        Transaction t = session.beginTransaction();

        System.out.println("PROFILE ID IS " + profile.getId());
        loadedProfile = (Profile)session.load(Profile.class, profileID);
        loadedProfile.setText(profile.getText());
        loadedProfile.setName(profile.getName());
        session.update(loadedProfile);
        t.commit();
        session.close();
    }

    public static void addProfile(Profile profile) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");// populates the data of the
        // configuration file

        // creating seession factory object
        SessionFactory factory = cfg.buildSessionFactory();

        // creating session object
        Session session = factory.openSession();

        // creating transaction object
        Transaction t = session.beginTransaction();

        session.save(profile);
        t.commit();
        session.flush();
        session.close();
    }

    public static void deleteProfile(Long profileID) {
        Profile profile;
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");// populates the data of the
        // configuration file

        // creating seession factory object
        SessionFactory factory = cfg.buildSessionFactory();

        // creating session object
        Session session = factory.openSession();

        // creating transaction object
        Transaction t = session.beginTransaction();

        profile = (Profile)session.get(Profile.class, profileID);
        if(profile != null) {
            session.delete(profile);
        }
        t.commit();
        session.close();
    }

    public static Map<String, String> createReport(String template) {
        Map<String, String> reportMap;
        String[] events;

        reportMap = new HashMap<String, String>();
        events = new String[] {
            "Robbery", "Criticism"
        };
        for (int i = 0; i < events.length; i++) {
            reportMap.put(events[i], getReportAboutEvent(events[i], template));
        }
        return reportMap;
    }

    private static String getReportAboutEvent (String eventName, String template) {
        Session sessions = null;
        Transaction transaction;
        List results;
        String[] items;
        String resultReport = new String(template);

        /*items = new String[] {
                "Person", "Position", "Organization", "SportsGame"
        };*/

        //GET PLACEHOLDER
        Pattern pattern = Pattern.compile("%(.*?)%");
        Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {
            String matchString = matcher.group();
            String event = matchString.substring(1, matchString.indexOf('('));
            int resultsNumber = Integer.valueOf(matchString.substring(matchString.indexOf('(') + 1, matchString.length() - 2));
            System.out.println("EVENT = " + event + ", RESULTS_NUMBER = " + resultsNumber);

        // GET DATA FOR PLACEHOLDER
            try {
                SessionFactory sessionFactory1 = new Configuration().configure().buildSessionFactory();
                sessions = sessionFactory1.openSession();
                transaction = sessions.beginTransaction();
                transaction.setTimeout(5);
                    Query query =  sessions.createSQLQuery("SELECT NAME FROM OPENCALAISTAG ot"
                            + " INNER JOIN PUBLICATIONTAGS pt ON ot.id = pt.id"
                            + " INNER JOIN PUBLICATION p ON p.pk = pt.pk"
                            + " WHERE ot.category = :term AND p.event = :event"
                            + " GROUP BY(NAME)"
                            + " ORDER BY COUNT(NAME) DESC")
                            .setParameter("term", event)
                            .setParameter("event", eventName);
                    results = query.list();
                    results = results.subList(0
                            , results.size() <= resultsNumber ? results.size() : resultsNumber);

                    String placeholderString = new String();
                    for(Object result : results) {
                        placeholderString += (String)result + ", ";
                    }
                    placeholderString = placeholderString.substring(0, placeholderString.length() - 2);
                    resultReport = resultReport.replace(matchString, placeholderString);
                // Create report about 1 term
                transaction.commit();
            } catch (Exception ex) {
                ;
            } finally {
                sessions.close();
            }
        }

        return resultReport;
    }
}
