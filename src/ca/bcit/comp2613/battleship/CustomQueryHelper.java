package ca.bcit.comp2613.battleship;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;


import ca.bcit.comp2613.battleship.model.Player;

public class CustomQueryHelper {
	private EntityManagerFactory emf;

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public CustomQueryHelper(EntityManagerFactory emf) {
		this.emf = emf;
	}

//	public List<Student> getStudentsOfTeacherWithNativeQuery(String teacherId) {
//		List<Student> retval = null;
//		EntityManager em = null;
//		try {
//			em = emf.createEntityManager();
//			Query query = em
//					.createNativeQuery(
//							" select "
//									+ "       student.* "
//									+ "    from "
//									+ "        teacher_student "
//									+ "    left join "
//									+ "        student "
//									+ "            on teacher_student.student_id = student.id where teacher_student.teacher_id = :teacher_id",
//							Student.class);
//			query.setParameter("teacher_id", teacherId);
//			retval = query.getResultList();
//		} catch (Exception e) {
//
//		} finally {
//			try {
//				em.close();
//			} catch (Exception e) {
//			}
//		}
//		return retval;
//	}
	
	
	public List<Student> getStudentsOfTeacher(String teacherId) {
		List<Student> retval = null;
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			Teacher teacher = em.find(Teacher.class, teacherId);
			teacher.getStudents().size(); // make a db call
			return teacher.getStudents();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				em.close();
			} catch (Exception e) {
			}
		}
		return retval;
	}
	
	public void addStudentToTeacher(String teacherId, Long studentId) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			Teacher teacher = em.find(Teacher.class, teacherId);
			Student student = em.find(Student.class, studentId);
			System.out.println(teacher.getStudents().size()); // make a db call
			teacher.getStudents().add(student);
			em.getTransaction().begin();
			em.persist(teacher);
			em.getTransaction().commit();
			System.out.println(teacher.getStudents().size()); // make a db call
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				em.close();
			} catch (Exception e) {
			}
		}
		
	}
	
	
	public void removeStudentFromTeacher(String teacherId, Long studentId) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			Teacher teacher = em.find(Teacher.class, teacherId);
			teacher.getStudents().size(); // make a db call
			Iterator<Student> iter = teacher.getStudents().iterator();
			while (iter.hasNext()) {
				if (iter.next().getId().equals(studentId)) {
					iter.remove();
				}
			}		
			em.getTransaction().begin();
			em.persist(teacher);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				em.close();
			} catch (Exception e) {
			}
		}
		
	}
	
}
