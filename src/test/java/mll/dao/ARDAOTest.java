package mll.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ARDAOTest {

	@Test
	public void testGetRegisteredMusician1() {
		{
			try {

				assertEquals(true, new ARHomePageDAO().getRegisteredMusicians(-11).size() == 0);
			} catch (Exception e) {
			}
		}
	}

	@Test
	public void testGetRegisteredMusician2() {
		{
			try {
				assertEquals(true, new ARHomePageDAO().getRegisteredMusicians(100) != null);
			} catch (Exception e) {
			}
		}
	}

	@Test
	public void testGetRegisteredMusician3() {
		{
			try {
				assertEquals(true, new ARHomePageDAO().getRegisteredMusicians(1) != null);
			} catch (Exception e) {

			}
		}
	}

	@Test
	public void testGetRegisteredMusician4() {
		{
			try {
				assertEquals(true, new ARHomePageDAO().getRegisteredMusicians(4) != null);
			} catch (Exception e) {

			}
		}

	}

	@Test
	public void testGetUnRegisteredMusician1() {
		{
			try {
				assertEquals(true, new ARHomePageDAO().getUnRegisteredMusicians(-2).size() == 0);
			} catch (Exception e) {

			}
		}
	}

	@Test
	public void testGetUnRegisteredMusician2() {
		{
			try {
				assertEquals(true, new ARHomePageDAO().getUnRegisteredMusicians(100) != null);
			} catch (Exception e) {

			}
		}
	}

	@Test
	public void testGetUnRegisteredMusician3() {
		{
			try {
				assertEquals(true, new ARHomePageDAO().getUnRegisteredMusicians(1) != null);
			} catch (Exception e) {

			}
		}
	}
}
