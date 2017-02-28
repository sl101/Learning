package com.foxminded.zhevaha.task_10.dao.tests;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.foxminded.zhevaha.task_10.dao.CustomDriverManager;

public class DaoTests {

	private static CustomDriverManager customDriverManager;

	@BeforeClass
	public static void initiateVariables() {
		customDriverManager = new CustomDriverManager();
	}

	@Test
	public void testReadDB() {
		String expected = "student_id group_id first_name last_name " + "\n1 1 VASIA PUPKIN " + "\n2 1 VASIA KOZLOV "
				+ "\n3 1 PASHA MOROZOV " + "\n4 1 PETR ROSHEN " + "\n5 1 YULIA TIMOSHENKO " + "\n6 1 VOVA PUTIN "
				+ "\n7 1 DONALD TRAMP " + "\n8 1 VAN GOG " + "\n9 1 VITIA YUSCHENKO " + "\n10 1 BRIUS LI "
				+ "\n11 1 DJEKI CHAN " + "\n12 2 ROMA ABRAMOVICH " + "\n13 2 PASHA VOLIA " + "\n14 2 PETR PERVIY "
				+ "\n15 2 TARAS BIULBA " + "\n16 2 TOM CAT " + "\n17 3 DJERY MOUSE " + "\n18 3 ARNOLD SCHWARZENEGGER "
				+ "\n19 4 ANGELINA JOLIE " + "\n20 4 ADAM SMIT " + "\n21 4 STIVEN KING \n";
		String actual = null;
		try {
			actual = customDriverManager.readFromDB("select * from students", "postgres", "slavaslava1");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		assertTrue(expected.equals(actual));
	}

}
