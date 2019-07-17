package com.studentapp.junit.studentsinfo;

import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;


@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest extends TestBase {
	
	static String firstName="SMOKEUSER";
	static String lastName="SMOKEUSER";
	static String programme="CS";
	static String email=TestUtils.getRandomValue()+"xyz@gmail.com";
	
	
	@Title("This test will create new student")
	@Test
	public void test001() {
		
		ArrayList<String> courses=new ArrayList<String>();
		courses.add("JAVA");
		courses.add("PYTHON");
		
		StudentClass student=new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		SerenityRest.given()
		.contentType(ContentType.JSON)
		.log()
		.all()
		.when()
		.body(student)
		.post()
		.then()
		.log()
		.all()
		.statusCode(201);
		
	}
	
	@Title("Verify if the student was added to the application")
	@Test
	public void test002() {
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log().all().statusCode(200);
	}
	
	

}
