package com.kim.app.tBoard;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAProject");
		EntityManager em = emf.createEntityManager(); // DAO클래스 생성파트

		EntityTransaction et = em.getTransaction();
		try {
			et.begin();

			TBoard tBoard = new TBoard();
			tBoard.setTitle("JPA실습");
			tBoard.setWriter("J");
			tBoard.setContent("PA");

			em.persist(tBoard);
			et.commit();
			
			// JPQL: sql문들의 표준.JPA구현체가 읽어들여서 해당DBMS의 sql문으로 처리
	         String jpql="select b from TBoard b";
	         List<TBoard> datas=em.createQuery(jpql,TBoard.class).getResultList();
	         for(TBoard v:datas) {
	            System.out.println(v);
	         }
	         
		} catch (Exception e) {
			et.rollback();
		}finally {
			em.close();
			emf.close();
		}
	}

}
