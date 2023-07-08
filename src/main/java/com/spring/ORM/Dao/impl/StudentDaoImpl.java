package com.spring.ORM.Dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.spring.ORM.entities.Student;

@Component("daoImpl")
public class StudentDaoImpl /* implements StudentDao */ {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional
	public int register(Student student) {
		
		int save = (Integer) hibernateTemplate.save(student);
		return save;
	}
	
	public Student getStudent(int studentId) {
		
		Student student = hibernateTemplate.get(Student.class,studentId);
		return student;
	}
	
	public List<Student> getAllStudent(){
		List<Student> loadAll = hibernateTemplate.loadAll(Student.class);
		return loadAll;
	}
	
	@Transactional
	public void deleteStudent(int studentId) {
		Student load = hibernateTemplate.load(Student.class, studentId);
		hibernateTemplate.delete(load);
	}
	
	@Transactional  
	public void updateStudent(Student student) {
		hibernateTemplate.update(student);
	}
}