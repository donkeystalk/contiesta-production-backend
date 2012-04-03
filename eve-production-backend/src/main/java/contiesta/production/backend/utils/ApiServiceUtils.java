package contiesta.production.backend.utils;

public class ApiServiceUtils {

	/**
	 * Params for RestTemplate call
	 * @keyID - Key ID from generated API
	 * @vCode - Verification Code
	 */
	public static final String CHARACTERS_SERVICE = "https://api.eveonline.com/account/Characters.xml.aspx?keyID={keyID}&vCode={vCode}";
	public static final String CHARACTERS_INDUSTRY_JOBS = "https://api.eveonline.com/corp/IndustryJobs.xml.aspx?keyID={keyID}&vCode={vCode}&characterID={characterID}";
	public static final String CHARACTER_SHEET_SERVICE = "https://api.eveonline.com/char/CharacterSheet.xml.aspx?keyID={keyID}&vCode={vCode}&characterID={characterID}";
}
