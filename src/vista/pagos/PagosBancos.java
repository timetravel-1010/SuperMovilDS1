package vista.pagos;

import controlador.ConnectionDB;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.usuarios.Login;

/**
 *
 * @author cristian
 */
public class PagosBancos {
    
    public ArrayList<String>leerDeArchivo(String ruta){
        
        ArrayList<String> lecturaLinea = new ArrayList<>();
       
        try{
            BufferedReader bf = new BufferedReader(new FileReader(ruta));
            String read;
            while((read = bf.readLine()) != null){
                StringTokenizer tokens=new StringTokenizer(read);
                while(tokens.hasMoreTokens()){
                    
                 lecturaLinea.add(tokens.nextToken());                 
                 
                } 
            }
             
        }catch(Exception e){
            System.out.print("No se encontro archivo");
        }
        return lecturaLinea;
    }
    public void escribirEnArchivo(String numeroLinea, String valorPagar){
        File f;
        FileWriter fw;
        BufferedWriter bf;
        PrintWriter pw;
        String rutaBanco;
        
        try{
            f = new File("/home/cristian/univalle/desarrolloDeSoftware/SuperMovilDS1/src/pagos/bancoA.txt");
            fw = new FileWriter(f,true);
            bf = new BufferedWriter (fw);
            pw = new PrintWriter(bf);
            
            
            Date fecha = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            String fechaN = sdf.format(fecha);
            
            pw.append(numeroLinea + " ");
            pw.append(valorPagar + " ");
            pw.append(fechaN + "\n");
            pw.close();
            bf.close();
            
        }catch(Exception e){
            System.out.print("no se encontro archivo");
        }
    }

}


