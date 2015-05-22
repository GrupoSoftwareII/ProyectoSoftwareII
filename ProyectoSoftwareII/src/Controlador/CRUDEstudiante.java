/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Estudiante;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Sebastian
 */
public class CRUDEstudiante {

    Estudiante estudiante;
    
//SOLO ENSAYO
    public CRUDEstudiante() {
        
        estudiante = new Estudiante();
    }

    public boolean agregarPuntos(String codigo, int puntos) {
        String sql = "Select function agregarpuntos("+codigo+","+puntos+")";
        return true;
    }

    public int consultarPuntos() {

        return 0;
    }

    public Estudiante consultarEstudiantes() {
        return estudiante;
    }
    
    public String listarPorArea(String area, Connection conexion, Statement sentencia) {

        String datos = "";
        ResultSet resultado = null;
        try {
            Class.forName("org.postgresql.Driver");
            String consultaSQL =  "select(select count(\"Estudiante_codigo\") from \"PreguntasFV_has_Estudiante\" where \"PreguntasFV_id\"=(select id from \"PreguntasFV\" where \"Area_codigo\"=(select codigo from \"Area\" where nombre='"+area+"')))+(select count(\"Estudiante_codigo\") from \"PreguntasMul_has_Estudiante\" where \"PreguntasMul_id\"=(select id from \"PreguntasMul\" where \"Area_codigo\"=(select codigo from \"Area\" where nombre='"+area+"')))";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) {
//                Long id = resultado.getLong("codigo");
//                String nombres = resultado.getString("nombre");
//                String apellidos = resultado.getString("apellido");
//                datos = (id + "\n" + nombres + "\n" + apellidos);
                datos+=resultado.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    //   sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    //   conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return datos;
    }
     
      public String listarPorGrupo(String grupo, Connection conexion, Statement sentencia) {

        String datos = "";
        ResultSet resultado = null;
        try {
            Class.forName("org.postgresql.Driver");
            String consultaSQL =  "select(select count(\"Estudiante_codigo\")  from \"PreguntasFV_has_Estudiante\" where \"Estudiante_codigo\" in(select codigo from \"Estudiante\" where grupo='"+grupo+"'))+(select count(\"Estudiante_codigo\") from \"PreguntasMul_has_Estudiante\" where \"Estudiante_codigo\" in(select codigo from \"Estudiante\" where grupo='"+grupo+"'))";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) {
//                Long id = resultado.getLong("codigo");
//                String nombres = resultado.getString("nombre");
//                String apellidos = resultado.getString("apellido");
//                datos = (id + "\n" + nombres + "\n" + apellidos);
                datos+=resultado.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    //   sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    //   conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return datos;
    }
    
         public String listarPorEstudiante(String estudiante, Connection conexion, Statement sentencia) {

        String datos = "";
        ResultSet resultado = null;
        try {
            Class.forName("org.postgresql.Driver");
            String consultaSQL =  "select (select count(\"Estudiante_codigo\") from \"PreguntasFV_has_Estudiante\" where \"Estudiante_codigo\" = '"+estudiante+"')+  (select count(\"Estudiante_codigo\") from \"PreguntasMul_has_Estudiante\" where \"Estudiante_codigo\" = '"+estudiante+"')";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) {
//                Long id = resultado.getLong("codigo");
//                String nombres = resultado.getString("nombre");
//                String apellidos = resultado.getString("apellido");
//                datos = (id + "\n" + nombres + "\n" + apellidos);
                datos+=resultado.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    //   sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    //   conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return datos;
    }
         
          public String listarPorAreaYGrupo(String area,String grupo, Connection conexion, Statement sentencia) {

        String datos = "";
        ResultSet resultado = null;
        try {
            Class.forName("org.postgresql.Driver");
            String consultaSQL =  "select(select count(\"Estudiante_codigo\") from \"PreguntasFV_has_Estudiante\",\"PreguntasFV\" ,\"Estudiante\",\"Area\" where \"PreguntasFV\".id=\"PreguntasFV_has_Estudiante\".\"PreguntasFV_id\" and \"Estudiante\".codigo=\"PreguntasFV_has_Estudiante\".\"Estudiante_codigo\" and  \"PreguntasFV\".\"Area_codigo\"=\"Area\".codigo   and \"Area\".codigo=(select codigo from \"Area\" where nombre='"+area+"') and \"Estudiante_codigo\"in(select codigo from \"Estudiante\" where grupo='"+grupo+"'))+(select count(\"Estudiante_codigo\") from \"PreguntasMul_has_Estudiante\",\"PreguntasMul\" ,\"Estudiante\",\"Area\" where \"PreguntasMul\".id=\"PreguntasMul_has_Estudiante\".\"PreguntasMul_id\" and \"Estudiante\".codigo=\"PreguntasMul_has_Estudiante\".\"Estudiante_codigo\" and  \"PreguntasMul\".\"Area_codigo\"=\"Area\".codigo   and \"Area\".codigo=(select codigo from \"Area\" where nombre='"+area+"') and \"Estudiante_codigo\"in(select codigo from \"Estudiante\" where grupo='"+grupo+"'))";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) {
//                Long id = resultado.getLong("codigo");
//                String nombres = resultado.getString("nombre");
//                String apellidos = resultado.getString("apellido");
//                datos = (id + "\n" + nombres + "\n" + apellidos);
                datos+=resultado.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    //   sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    //   conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return datos;
    }
          
             public String listarPorGrupoYEstudiante(String grupo,String estudiante, Connection conexion, Statement sentencia) {

        String datos = "";
        ResultSet resultado = null;
        try {
            Class.forName("org.postgresql.Driver");
            String consultaSQL =  "select(select count(\"Estudiante_codigo\") from \"PreguntasFV_has_Estudiante\",\"PreguntasFV\" ,\"Estudiante\" where \"PreguntasFV\".id=\"PreguntasFV_has_Estudiante\".\"PreguntasFV_id\" and \"PreguntasFV\".res_correcta=\"PreguntasFV_has_Estudiante\".respuesta and \"Estudiante\".codigo=\"PreguntasFV_has_Estudiante\".\"Estudiante_codigo\"      and \"Estudiante\".codigo='1' and \"Estudiante_codigo\"in(select codigo from \"Estudiante\" where grupo='"+grupo+"'))+(select count(\"Estudiante_codigo\") from \"PreguntasMul_has_Estudiante\",\"PreguntasMul\" ,\"Estudiante\" where \"PreguntasMul\".id=\"PreguntasMul_has_Estudiante\".\"PreguntasMul_id\" and \"PreguntasMul\".res_correcta=\"PreguntasMul_has_Estudiante\".respuesta and \"Estudiante\".codigo=\"PreguntasMul_has_Estudiante\".\"Estudiante_codigo\"      and \"Estudiante\".codigo='"+estudiante+"' and \"Estudiante_codigo\"in(select codigo from \"Estudiante\" where grupo='"+grupo+"'))";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) {
//                Long id = resultado.getLong("codigo");
//                String nombres = resultado.getString("nombre");
//                String apellidos = resultado.getString("apellido");
//                datos = (id + "\n" + nombres + "\n" + apellidos);
                datos+=resultado.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    //   sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    //   conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return datos;
    }
          
            public String listarPorAreaYEstudiante(String area,String estudiante, Connection conexion, Statement sentencia) {

        String datos = "";
        ResultSet resultado = null;
        try {
            Class.forName("org.postgresql.Driver");
            String consultaSQL =  "select(select count(\"Estudiante_codigo\") from \"Estudiante\",\"PreguntasFV_has_Estudiante\",\"PreguntasFV\" ,\"Area\" where \"PreguntasFV_has_Estudiante\".\"Estudiante_codigo\"=\"Estudiante\".codigo and \"Estudiante\".codigo='"+estudiante+"' and  \"PreguntasFV\".\"Area_codigo\"=\"Area\".codigo and \"Area\".codigo=(select codigo from \"Area\" where nombre='"+area+"'))+(select count(\"Estudiante_codigo\") from \"Estudiante\",\"PreguntasMul_has_Estudiante\",\"PreguntasMul\" ,\"Area\" where \"PreguntasMul_has_Estudiante\".\"Estudiante_codigo\"=\"Estudiante\".codigo and \"Estudiante\".codigo='1'and  \"PreguntasMul\".\"Area_codigo\"=\"Area\".codigo and \"Area\".codigo=(select codigo from \"Area\" where nombre='"+area+"'))";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) {
//                Long id = resultado.getLong("codigo");
//                String nombres = resultado.getString("nombre");
//                String apellidos = resultado.getString("apellido");
//                datos = (id + "\n" + nombres + "\n" + apellidos);
                datos+=resultado.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    //   sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    //   conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return datos;
    }
    public String listarEstudiantes(String cod, Connection conexion, Statement sentencia) {

        String datos = "";
        ResultSet resultado = null;
        try {
            Class.forName("org.postgresql.Driver");
            String consultaSQL = "SELECT * FROM \"Estudiante\" where codigo='" + cod + "'";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) {
                Long id = resultado.getLong("codigo");
                String nombres = resultado.getString("nombre");
                String apellidos = resultado.getString("apellido");
                datos = (id + "\n" + nombres + "\n" + apellidos);
            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    //   sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    //   conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return datos;
    }
    
          public String listarPorAreaGrupoYEstudiante(String area,String grupo,String estudiante, Connection conexion, Statement sentencia) {

        String datos = "";
        ResultSet resultado = null;
        try {
            Class.forName("org.postgresql.Driver");
            String consultaSQL =  "select(select count(\"Estudiante_codigo\") from \"Estudiante\",\"PreguntasFV_has_Estudiante\",\"PreguntasFV\" ,\"Area\" where \"PreguntasFV_has_Estudiante\".\"Estudiante_codigo\"=\"Estudiante\".codigo and \"PreguntasFV\".id=\"PreguntasFV_has_Estudiante\".\"PreguntasFV_id\" and \"PreguntasFV\".res_correcta=\"PreguntasFV_has_Estudiante\".respuesta  and \"Estudiante\".codigo='"+estudiante+"' and  \"PreguntasFV\".\"Area_codigo\"=\"Area\".codigo and \"Area\".codigo=(select codigo from \"Area\" where nombre='"+area+"') and \"Estudiante\".codigo in(select codigo from \"Estudiante\" where grupo='"+grupo+"'))+(select count(\"Estudiante_codigo\") from \"Estudiante\",\"PreguntasMul_has_Estudiante\",\"PreguntasMul\" ,\"Area\" where \"PreguntasMul_has_Estudiante\".\"Estudiante_codigo\"=\"Estudiante\".codigo and \"PreguntasMul\".id=\"PreguntasMul_has_Estudiante\".\"PreguntasMul_id\" and \"PreguntasMul\".res_correcta=\"PreguntasMul_has_Estudiante\".respuesta  and \"Estudiante\".codigo='"+estudiante+"'and  \"PreguntasMul\".\"Area_codigo\"=\"Area\".codigo and \"Area\".codigo=(select codigo from \"Area\" where nombre='"+area+"')and \"Estudiante\".codigo in(select codigo from \"Estudiante\" where grupo='"+grupo+"'))";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) {
//                Long id = resultado.getLong("codigo");
//                String nombres = resultado.getString("nombre");
//                String apellidos = resultado.getString("apellido");
//                datos = (id + "\n" + nombres + "\n" + apellidos);
                datos+=resultado.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    //   sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    //   conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return datos;
    }

}
