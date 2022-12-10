package Driver;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Entity.Course;

import Entity.Teacher;
import Entity.TeacherDetails;

public class InsertTeacherAndTeacherDetails {

	public static void main(String[] args) {
		

		//create session factory
		SessionFactory factory=new Configuration()
				                   .configure("Hibernate.cfg.xml")
				                   .addAnnotatedClass(Teacher.class)
				                   .addAnnotatedClass(TeacherDetails.class)
				                   .addAnnotatedClass(Course.class)
				                   .buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			//create objects
			Teacher theTeacher1=new Teacher("Ayushi","karade","Ayushi_k@gl.com");
			Teacher theTeacher2=new Teacher("Eklavya","Raut","Raut22@gl.com");
			
			// course object association
			Course c=new Course("Java");
			
			//Teachers details
			TeacherDetails td1=new TeacherDetails("Indore","Play Games");
			TeacherDetails td2=new TeacherDetails("Paris","Bussiness");
			//associate objects
			theTeacher1.setTeacherDetails(td1);
			theTeacher2.setTeacherDetails(td2);
			
			theTeacher1.courses.add(c);
			// start transaction
			session.beginTransaction();
			
			//save the teacher
			System.out.println("Saving Teacher Objects :");
			session.save(theTeacher1);
			session.save(theTeacher2);
			
			// commit
			session.getTransaction().commit();
			
			System.out.println("Completed Successfully :");
		}
		finally {
			factory.close();
			session.close();
		}
		
		
	}
}
