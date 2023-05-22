import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DdlTool {
    private Connection connection;
    private DatabaseConnection databaseConnection;
    public boolean createTable(){
        String sentence="",read="";
        PreparedStatement ps = null;
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

           BufferedReader lector = new BufferedReader(new FileReader("src/FootballManager.sql"));

            while((read = lector.readLine())!=null){
                if(read.length()==0){
                    continue;
                }

                if(read.endsWith(";")){
                    if(sentence.length()>0) {
                        if (ps == null){
                            connection.prepareStatement(sentence + read);
                        } else {
                            ps.addBatch(sentence + read);
                        }
                    } else {
                        if (ps == null){
                            ps = connection.prepareStatement(sentence + read);
                        } else {
                            ps.addBatch(read);
                        }
                    }
                    sentence="";
                }else if(!read.endsWith(";")) {
                    sentence += read;
                    continue;
                }
            }
            // Ejecutar el lote de consultas
            if (ps != null) {
                ps.executeBatch();
                ps.clearBatch();
                ps.close();
            }

            lector.close();
            System.out.println("Ejecución de comandos SQL completada");
            return true;
        } catch (IOException e){
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            this.databaseConnection.disconnect();
        }
    }

    public boolean insertarDatos(){

        return false;
    }
    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
}
