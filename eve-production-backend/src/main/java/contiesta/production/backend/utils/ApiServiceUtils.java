package contiesta.production.backend.utils;

public class ApiServiceUtils {

	/**
	 * Params for RestTemplate call
	 * @keyID - Key ID from generated API
	 * @vCode - Verification Code
	 */
	public static final String CHARACTERS_SERVICE = "https://api.eveonline.com/account/Characters.xml.aspx?keyID={keyID}&vCode={vCode}";
	
}
