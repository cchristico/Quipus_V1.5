
package com.ec.servicios.proveedor;

/**
 *
 * @author david
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ingresoProveedor {
    public  Boolean proviteLength(String nombreProveedor)
{
        return nombreProveedor.isEmpty();
        
}

public Boolean proviteNameisAString(String nombreProveedor)
{
     Pattern pat = Pattern.compile("[a-zA-Z]");
     Matcher mat = pat.matcher(nombreProveedor);
         return mat.find();
}
public boolean provitenameLengthMoreThan3lessThan20AndIsAString(String nombreProveedor)
{
    Pattern pat = Pattern.compile("[a-zA-Z]{3,20}");
    Matcher mat = pat.matcher(nombreProveedor);
    
                return mat.find();
}
    
}
