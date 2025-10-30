package me.rcortesb.swingy.models;

public class Artifact {
	String	name;
	String	type;
	int		value;
	String	description;

	public Artifact(String p_name, String p_type, int p_value, String p_description) {
		this.name = p_name;
		this.type = p_type;
		this.value = p_value;
		this.description = p_description;
	}
}