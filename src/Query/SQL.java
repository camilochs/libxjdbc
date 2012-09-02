package Query;

enum SQL{
	
	UPDATE("UPDATE"),
	INSERT("INSERT INTO"),
	SET(" SET "),
	DELETE("DELETE FROM");
	SQL(String name){
		this.name = name;
	}
	@Override public String toString(){
		return name;
	}
	private final String name;
	
	
	 
}