package net.bounceme.chronos.springboot.backend.apirest.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "MedinaAzahara2468";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEpQIBAAKCAQEAqes9ckJ2AmrwyWR+/f51W8K3hqfajfx4Lb8nQpqhT0upvntA\n" + 
			"e18kUcfVF+6xT5+5l/wWze8MK0NE4lLf1jgq+eu+7dXBkt+tjunB6cbRNGRFjbUD\n" + 
			"iIC7qUwQ2owVWMd0O7YqS611XDa25Qa5s2rPq9D8DOYYvmMVbnDvge5bhaS6O47J\n" + 
			"ocjImgApLHCQ7gCZCD3jgaPWsxysFEl/yG/W1B+9/EP9JKBwW0GK8dN0w/uQT8hi\n" + 
			"vTc2h7wxolN9EKVfyP9dRpTMoYl1HrTCrtijYw+wZkhCz6Nt1jinC2E9gE0qNLa0\n" + 
			"+SyLsneG3vC6Dwhpguskqhq7a3bVAArvdzUy/QIDAQABAoIBAB82DJVeS9rp1czI\n" + 
			"XuM8yEXQoHFlh6mpBFIy2H1Nde/g0A8jCJcgxMUY1bWNrJgZmLeoIHA3Rp7zvkVs\n" + 
			"P51Tt+EsQmfhaasW1wR3P503XdonyPbclweNzeqtBDOAo7Mo/9qv/VjlRbdofXPY\n" + 
			"JmgkaH0A6CteYJEoFhmxOO0+4STM7p7iQQvK+u8BNUYxomqGyz1NeUhIiCOkd9x1\n" + 
			"dzBeXBA9Fr4vZbW5E5/M8A7Lgd92iP6OEmSRv4H1vPwwcsfapdFV4k4vrDmli/ap\n" + 
			"jZJFCSNEWSZUBLNaSkNRxdIJgQwuqa6vqFfj6sx8WPJNf9/Z3/upXQ4+k7haYb/e\n" + 
			"VbmJukECgYEA1AEJkbb8wdo8yfGVlA9Kp/maIvkenJjQo4T3Xj1sr9Gfp0Qa0En4\n" + 
			"xSpuydGxIsNH0z/8PmfRudN/EYt9M0HaV9H+eWgzrfVK/J4ar/YbzOkecLCw/Jvv\n" + 
			"hKNg8hC/1jVzF2vmGho6i0viCVA/La9iAYPvLXDicEg8vPJN2Njx/hkCgYEAzS5h\n" + 
			"Uqs2CwuNWbaljfxUa7elhqcFEbGSAlSZNZ8juQJkCg9u+PD3r2zd0NNUnxe0Bsn6\n" + 
			"0mdBwjUgcE5adQOLfsywxHqDxk5LtNnNO/BbSDNOxJCbFIm5Uhc9FDZRNSdi5tH5\n" + 
			"KJjY2bsHMMX5w40qIZ2Or5DCLtWE0gprWbyDsIUCgYEAtSWFxaozYnhVRMBV7ML8\n" + 
			"KXcPCx8N1wSEEZSFwVJ+0eQtpMB+oMbz95SAQZYY4vk1H/4mTDRtcXiTtKwZeHim\n" + 
			"Vi+qXpZ4wgU744o8h2mk63utdOCk+dNL32uyZGOHZ0MYakTZwifzKh7WrtqQgTrA\n" + 
			"ZUcUUSGJeiNfMptfhRAqBdECgYEAr9iLElWnyImLCBNQCcLy+yp2U1O7NXHQ5+xn\n" + 
			"LhyIehwBbtnRSssCC7eq1zj3Mi4XXSYnr8FNC0uK1JViUxzJpc95w7oYS5F4srNj\n" + 
			"gHY7VUKbxD0Gm/B5AlDmSPdQW9S4n4ffGRr95oSy4esaZq0KKaslyXOv6IGYBodl\n" + 
			"f4/f8R0CgYEAtoQLbPv9ypxOeIm3FTsh5aXP7OQP0WHR4W28QChb8mkt3IMPbMP/\n" + 
			"vl84FFjtnUfBTm3uf04+p/aAAvXLpspkZjEDsgjRncU+avG6dUY2rOG7lO9TEp/W\n" + 
			"i53v9Y7yMvljqt4DjI9TqTog+qEtXudvm9N6fUdzfFaXnVa9DEp/e0s=\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqes9ckJ2AmrwyWR+/f51\n" + 
			"W8K3hqfajfx4Lb8nQpqhT0upvntAe18kUcfVF+6xT5+5l/wWze8MK0NE4lLf1jgq\n" + 
			"+eu+7dXBkt+tjunB6cbRNGRFjbUDiIC7qUwQ2owVWMd0O7YqS611XDa25Qa5s2rP\n" + 
			"q9D8DOYYvmMVbnDvge5bhaS6O47JocjImgApLHCQ7gCZCD3jgaPWsxysFEl/yG/W\n" + 
			"1B+9/EP9JKBwW0GK8dN0w/uQT8hivTc2h7wxolN9EKVfyP9dRpTMoYl1HrTCrtij\n" + 
			"Yw+wZkhCz6Nt1jinC2E9gE0qNLa0+SyLsneG3vC6Dwhpguskqhq7a3bVAArvdzUy\n" + 
			"/QIDAQAB\n" + 
			"-----END PUBLIC KEY-----";
}
