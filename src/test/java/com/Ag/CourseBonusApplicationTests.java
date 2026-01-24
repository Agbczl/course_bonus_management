package com.Ag;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@ServletComponentScan
@SpringBootTest
class CourseBonusApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testjwt(){
		Map<String,Object> map = new HashMap<>();
		map.put("username","qgcl");
		map.put("password","123456");
		String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256,"qgcl1234143124123k3k2j1h2g1f2d3s4a5p6o7i8u9y0t5r4e3w2q1")
				.claims(map)
				.setExpiration(new Date(System.currentTimeMillis() + 3600*1000))
				.compact();
		System.out.println(jwt);
	}

}
