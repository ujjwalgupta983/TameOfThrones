package com.geektrust.tameofthrones;

import com.geektrust.tameofthrones.services.RulerServiceTest;
import com.geektrust.tameofthrones.utils.CaesarCipherTest;
import com.geektrust.tameofthrones.utils.InputParserTest;
import com.geektrust.tameofthrones.utils.ParseKingdomDetails;
import com.geektrust.tameofthrones.utils.ParseKingdomDetailsTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeekTrustTests {

	@Test
	void contextLoads() {
		InputParserTest input = new InputParserTest();
		input.getData(new String());
		input.getEmptyData(new String());
		CaesarCipherTest caesar = new CaesarCipherTest();
		caesar.decrypt("MANGO", 5);
		caesar.emptyDecrypt("", 3);
		ParseKingdomDetailsTest.getKingdomDetails(new String());
		RulerServiceTest rulerServiceTest = new RulerServiceTest();
	}

}
