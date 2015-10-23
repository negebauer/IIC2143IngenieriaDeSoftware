package backend.enums;

public enum School {
	ADMINISTRATIVESCIENCES,
	AESTHETICS,
	AGRICULTURE,
	ARCHITECTURE,
	ARTS,
	ASTROPHYSICS,
	BIOLOGICALSCIENCES,
	CHEMISTRY,
	CIVILCONSTRUCTION,
	COLLEGE,
	COMMUNICATIONS,
	DESIGN,
	DRAMA,
	ECONOMICS,
	EDUCATION,
	ENGINEERING,
	FORESTRY,
	GEOGRAPHY,
	HISTORY,
	LAW,
	LETTERS,
	MATHEMATICS,
	MEDICINE,
	MUSIC,
	NURSING,
	PHILOSOFY,
	PHYSICS,
	POLITICALSCIENCES,
	PSICOLOGIA,
	SOCIALOGY,
	SOCIALWORK,
	THEOLOGY,
	URBANSTUDIES,
	UNKNOWN;
	
	public static School defaultSchool() {
		return School.UNKNOWN;
	}
}