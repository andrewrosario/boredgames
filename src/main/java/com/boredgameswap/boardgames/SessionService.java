//package com.boredgameswap.boardgames;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SessionService {
//    private static SessionFactory sessionFactory;
//
//    private SessionService(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    public static Session createSession() {
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure()
//                .build();
//        try {
//            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
//        } catch (Exception e) {
//            StandardServiceRegistryBuilder.destroy( registry );
//        }
//        return sessionFactory.openSession();
//    }
//
//
//
//
//}
