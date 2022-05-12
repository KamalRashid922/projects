package eg.edu.alexu.csd.oop.db.cs49;

/*
 /////////////////////////////counter test/////////////////////////////////
 library ieee;
use ieee.std_logic_1164.all;

entity BCD_COUNTER_TEST is
end entity;

architecture STRUCT of BCD_COUNTER_TEST is
component BCD_COUNTER
	port(x, clk: in std_logic;
	     y: out std_logic_vector(3 downto 0) := (others => '0'));
end component;
signal x, clk: std_logic := '0';
signal y: std_logic_vector(3 downto 0);
begin
	x <= not x after 200 ns;
	clk <= not clk after 10 ns;
	COUNTER: BCD_COUNTER port map(x, clk, y);
end architecture;
 
 /////////////////////counter/////////////////////////////
  library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity BCD_COUNTER is
	port(x, clk: in std_logic;
	     y: out std_logic_vector(3 downto 0));
end entity;

architecture STRUCT of BCD_COUNTER is
signal y_int: std_logic_vector(3 downto 0) := (others => '0');
begin
	y <= y_int;
	process(clk)
	begin
		if rising_edge(clk) then
			if x = '0' then
				y_int <= std_logic_vector(unsigned(y_int) + 1);
			elsif x = '1' then
				y_int <= std_logic_vector(unsigned(y_int) - 1);
			end if;
		end if;
	end process;
end architecture;
 
 */











public interface Database {
	/**
	 * Create database with the given name, or use it if exists. This method
	 * performs a call to executeStructureQuery() internally to create or drop
	 * the database.
	 * 
	 * @param databaseName
	 *            Database name, can be a path not a name only
	 * @param dropIfExists
	 *            if true, then delete the database and recreate it again.
	 * @return the absolute path of the database directory wherein data is
	 *         stored
	 */
	public String createDatabase(String databaseName, boolean dropIfExists);

	/**
	 * Creates/drops table or database.
	 * 
	 * @param query
	 *            create or drop, table or database query
	 * @returns true if success, or false otherwise
	 * @throws SQLException
	 *             syntax error
	 */
	public boolean executeStructureQuery(String query)
			throws java.sql.SQLException;

	/**
	 * Select from table
	 * 
	 * @param query
	 *            select query
	 * @return the selected records or an empty array if no records match.
	 *         Columns types must be preserved (i.e. int column returns Integer
	 *         objects)
	 * @throws SQLException
	 *             syntax error
	 */
	public Object[][] executeQuery(String query) throws java.sql.SQLException;

	/**
	 * Insert or update or delete the data
	 * 
	 * @param query
	 *            data manipulation query
	 * @return the updated rows count
	 * @throws SQLException
	 *             syntax error
	 */
	public int executeUpdateQuery(String query) throws java.sql.SQLException;
}