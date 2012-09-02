package Main.Connect;

public interface XLibDB_Connection {
	//Lists driver URL.
	enum LocationDriver
	{
		POSTGRES("org.postgresql.Driver");
	
		LocationDriver(String driver){
			this.driver = driver;
		}
		final public String getPath(){
			return driver;
		}
		private String driver;
	}

	void Connect();

}