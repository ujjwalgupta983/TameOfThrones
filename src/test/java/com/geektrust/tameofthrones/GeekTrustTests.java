package com.geektrust.tameofthrones;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.geektrust.tameofthrones.models.Kingdom;
import com.geektrust.tameofthrones.services.RulerServiceTest;
import com.geektrust.tameofthrones.utils.CaesarCipherTest;
import com.geektrust.tameofthrones.utils.InputParserTest;
import com.geektrust.tameofthrones.utils.Output;
import com.geektrust.tameofthrones.utils.ParseKingdomDetailsTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeekTrustTests {

	private List<Kingdom> kingdomList;
	private static HashSet<Kingdom> subjects = new HashSet<>();
	private static final int MINIMUMSUBJECTS = 3;
	
	@AfterAll
	@Test
	public static void contextLoads() {
		List<String> output = Output.displayResult(subjects, new Kingdom("SPACE", "Gorilla", "", true), MINIMUMSUBJECTS);
		assertEquals(4, output.size());
		assertEquals("LAND ", output.get(1));
	}

	@Test
	void inputParserTest() {
		// Calls InputParserTest Class
		InputParserTest input = new InputParserTest();
		input.getData(new String());
		input.getEmptyData(new String());
	}

	@Test
	void caesarCipherTest() {
		// Calls CaesarCipher Test Class
		CaesarCipherTest caesar = new CaesarCipherTest();
		caesar.decrypt("MANGO", 5);
		caesar.emptyDecrypt("", 3);
	}

	@Test 
	void parseKingdomDetailsTest() {
		// Calls ParseKingdomDetailsTest Class
		ParseKingdomDetailsTest.getKingdomDetails(new String());
	}

	@Test 
	void rulerServiceTest() {
		RulerServiceTest ruler = new RulerServiceTest();
		kingdomList = new ArrayList<>();
		kingdomList = ruler.setup();
		subjects = ruler.getSubjects(kingdomList);
	}
}
