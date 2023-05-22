import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase en la que elaboro con un método para importar y crear
 * una base de datos
 *
 * @author Mario
 * @version 1.0
 */
public class DdlTool {
    private Connection connection;
    private DatabaseConnection databaseConnection;

    /**
     * metodo para crear la base de datos e importar datos
     * @return: devuelve un true si la operacion ha sido
     * exitosa
     */
    public boolean createTable(){
        String sentence="",read="";
        PreparedStatement ps = null;
        try {
            //se abre la conexión usando la cadena de conexión guardada en el gestor
            //de conexión
            connection = DriverManager.getConnection(this.databaseConnection.getConnectionString());
            this.databaseConnection.setConexion(connection);

            //Lector para leer el script sql
           BufferedReader lector = new BufferedReader(new FileReader("src/FootballManager.sql"));

            //lee líneas de un archivo y omite aquellas líneas que están vacías,
            //saltándolas y continuando con la siguiente línea.
            while((read = lector.readLine())!=null){
                if(read.length()==0){
                    continue;
                }
                //Verifica si la línea leída termina con el carácter ";",
                //lo cual indica el final de una consulta SQL
                if(read.endsWith(";")){
                    //Verifica si la variable sentence tiene contenido,
                    //lo cual significa que hay una consulta SQL en proceso de construcción
                    if(sentence.length()>0) {
                        //Verifica si la consulta es nula, si lo es crea un nuevo objeto
                        //PreparedStatement a partir de la concatenación de sentence y read
                        //Si no lo es se agrega la concatenación de sentence y read al lote
                        //de consultas utilizando el método addBatch().
                        if (ps == null){
                            connection.prepareStatement(sentence + read);
                        } else {
                            ps.addBatch(sentence + read);
                        }
                        //Si no se cumple la condición if (ps == null), significa que
                        //ya se ha creado un objeto PreparedStatement y se está procesando
                        //una consulta SQL adicional. En este caso, se siguen las mismas
                        //lógicas mencionadas anteriormente, pero utilizando ps.addBatch()
                        //en lugar de crear un nuevo objeto PreparedStatement.
                    } else {
                        if (ps == null){
                            ps = connection.prepareStatement(sentence + read);
                        } else {
                            ps.addBatch(read);
                        }
                    }
                    sentence="";
                    //Si la línea leída no termina con el carácter ";", significa que
                    //es parte de una consulta SQL que se extiende a través de varias
                    //líneas. En este caso, se agrega el contenido de la línea a la
                    //variable sentence para construir la consulta SQL completa.
                }else if(!read.endsWith(";")) {
                    sentence += read;
                    continue;
                }
            }
            //Verifica si se ha creado un objeto PreparedStatement
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
    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
}
