package com.cov.app;

import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.cov.beans.Point;
import com.cov.beans.Student;
import com.cov.beans.Triangle;

public class App_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctxt = new FileSystemXmlApplicationContext("spring.xml");
		Student student1 = (Student)ctxt.getBean("stud");
		System.out.println("Id: "+student1.getId()+" Name :"+student1.getName());
		Triangle triangle=(Triangle) ctxt.getBean("tri");
		System.out.println(triangle.getPointA()+" "+triangle.getPointB()+" "+triangle.getPointC());
		System.out.println("hello");
		}

}

