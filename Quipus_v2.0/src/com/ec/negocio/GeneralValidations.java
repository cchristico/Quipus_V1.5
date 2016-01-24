package com.ec.negocio;

import com.ec.entidades.Usuario;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Clase para validar todos los campos de entrada de todas las interfaces
 * 
 * @author Jonathan Morocho
 */
public class GeneralValidations {
	private String errorMessage = "";

	/**
	 * Método para validar el contenido de campos de tipo JTextField
	 * 
	 * @param textField
	 *            un objeto de tipo JFormattedTextField
	 * @param nameField
	 *            dato de tipo String referencial al contenido del objeto
	 */
	public void validteEmptyField(String textField, String nameField) {

		if (textField.trim().equals("-1") || textField.trim().equals("")
				|| textField.trim().equals("null")
				|| textField.trim().replaceAll("\\s", "").trim().equals("")) {
			errorMessage += "Campo '" + nameField + "' esta vacio \n";
		}
	}

	/**
	 * Método para validar si el campo de cédula o ruc está correctamente
	 * llenado
	 * 
	 * @author Wilmer Valdiviezo
	 * 
	 * @param document
	 * @param fieldName
	 */
	public void CIValidation(String document, String fieldName) {

		int NumberProvincias = 24;// 22;

		// verifica que tenga 10 dígitos y que contenga solo valores numéricos
		if (document == null
				|| (document.trim().replaceAll("\\s", "").length() != 10 || document
						.trim().equals(""))) {
			errorMessage += "Campo '" + fieldName + "' no es correcto \n";
			return;
		}
		if (!((document.length() == 10) && document.matches("^[0-9]{10}$"))) {
			errorMessage += "Campo '" + fieldName + "' no es correcto \n";
			return;
		}

		// verifica que los dos primeros dígitos correspondan a un valor entre 1
		// y NUMERO_DE_PROVINCIAS
		int prov = Integer.parseInt(document.substring(0, 2));

		if (!((prov > 0) && (prov <= NumberProvincias))) {
			errorMessage += "Campo '" + fieldName + "' no es correcto \n";
			return;
		}

		// Aplicar el algoritmo de validacion de cedula
		if (!isValidDocument(document)) {
			errorMessage += "Campo '" + fieldName + "' no es correcto \n";
		}
	}


	public boolean isValidDocument(String cedula) {

		// verifica que el último dígito de la cédula sea válido
		int[] d = new int[10];

		// Asignamos el string a un array
		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.parseInt(cedula.charAt(i) + "");
		}

		int imp = 0;
		int par = 0;

		// sumamos los duplos de posición impar
		for (int i = 0; i < d.length; i += 2) {
			d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
			imp += d[i];
		}

		// sumamos los digitos de posición par
		for (int i = 1; i < (d.length - 1); i += 2) {
			par += d[i];
		}

		// Sumamos los dos resultados
		int suma = imp + par;

		// Restamos de la decena superior
		int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1)
				+ "0")
				- suma;

		// Si es diez el décimo dígito es cero
		d10 = (d10 == 10) ? 0 : d10;

		// si el décimo dígito calculado es igual al digitado la cédula es
		// correcta

		return (d10 == d[9]);
	}

	
	public void rucValidation(String ruc, String nombreDeCampo) {
		int numeroProvincias = 24;

		// verifica que tenga 10 dígitos y que contenga solo valores numéricos
		if (ruc == null || (ruc.trim().replaceAll("\\s", "").length() != 13 
						|| ruc.trim().equals(""))) {
			errorMessage += "Campo '" + nombreDeCampo + "' no es correcto \n";
			return;
		}
		
		if (ruc.length() < 10) {
			errorMessage += "Campo '" + nombreDeCampo + "' no es correcto \n";
			return;
		}
		
		int provincia = Integer.parseInt(ruc.substring(0, 2));
		if (provincia <= 0 || provincia > numeroProvincias) {
			errorMessage += "Campo '" + nombreDeCampo + "' no es correcto \n";
			return;
		}
		
		// Aplicar el algoritmo de validacion de cedula
		if (!isRUCValid(ruc)) {
			errorMessage += "Campo '" + nombreDeCampo + "' no es correcto \n";
		}

	}

	/**
	 * Metodo que aplica el algoritmo de validacion de ruc de Ecuador.
	 * 
	 * @author Wilmer Valdiviezo
	 * @param ruc
	 * @return
	 */
	public boolean isRUCValid(String ruc) {
		boolean valor = true;
		try {
			int suma = 0;
			int residuo = 0;
			boolean privada = false;
			boolean publica = false;
			boolean natural = false;
			int digitoVerificador = 0;
			int modulo = 11;

			int d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;
			int p1, p2, p3, p4, p5, p6, p7, p8, p9;

			d1 = d2 = d3 = d4 = d5 = d6 = d7 = d8 = d9 = d10 = 0;
			p1 = p2 = p3 = p4 = p5 = p6 = p7 = p8 = p9 = 0;

			// Aqui almacenamos los digitos de la cedula en variables.
			d1 = Integer.parseInt(ruc.substring(0, 1));
			d2 = Integer.parseInt(ruc.substring(1, 2));
			d3 = Integer.parseInt(ruc.substring(2, 3));
			d4 = Integer.parseInt(ruc.substring(3, 4));
			d5 = Integer.parseInt(ruc.substring(4, 5));
			d6 = Integer.parseInt(ruc.substring(5, 6));
			d7 = Integer.parseInt(ruc.substring(6, 7));
			d8 = Integer.parseInt(ruc.substring(7, 8));
			d9 = Integer.parseInt(ruc.substring(8, 9));
			d10 = Integer.parseInt(ruc.substring(9, 10));

			// El tercer digito es:
			// 9 para sociedades privadas y extranjeros
			// 6 para sociedades publicas
			// menor que 6 (0,1,2,3,4,5) para personas naturales
			if (d3 == 7 || d3 == 8) {
				valor = false;
			}

			// Solo para personas naturales (modulo 10)
			if (d3 < 6) {
				natural = true;
				modulo = 10;
				p1 = d1 * 2;
				if (p1 >= 10)
					p1 -= 9;
				p2 = d2 * 1;
				if (p2 >= 10)
					p2 -= 9;
				p3 = d3 * 2;
				if (p3 >= 10)
					p3 -= 9;
				p4 = d4 * 1;
				if (p4 >= 10)
					p4 -= 9;
				p5 = d5 * 2;
				if (p5 >= 10)
					p5 -= 9;
				p6 = d6 * 1;
				if (p6 >= 10)
					p6 -= 9;
				p7 = d7 * 2;
				if (p7 >= 10)
					p7 -= 9;
				p8 = d8 * 1;
				if (p8 >= 10)
					p8 -= 9;
				p9 = d9 * 2;
				if (p9 >= 10)
					p9 -= 9;
			}

			// Solo para sociedades publicas (modulo 11)
			// Aqui el digito verficador esta en la posicion 9, en las otras 2
			// en la pos. 10
			if (d3 == 6) {
				publica = true;
				p1 = d1 * 3;
				p2 = d2 * 2;
				p3 = d3 * 7;
				p4 = d4 * 6;
				p5 = d5 * 5;
				p6 = d6 * 4;
				p7 = d7 * 3;
				p8 = d8 * 2;
				p9 = 0;
			}

			/* Solo para entidades privadas (modulo 11) */
			if (d3 == 9) {
				privada = true;
				p1 = d1 * 4;
				p2 = d2 * 3;
				p3 = d3 * 2;
				p4 = d4 * 7;
				p5 = d5 * 6;
				p6 = d6 * 5;
				p7 = d7 * 4;
				p8 = d8 * 3;
				p9 = d9 * 2;
			}

			suma = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9;
			residuo = suma % modulo;

			// Si residuo=0, dig.ver.=0, caso contrario 10 - residuo
			digitoVerificador = residuo == 0 ? 0 : modulo - residuo;
			int longitud = ruc.length(); // Longitud del string

			// ahora comparamos el elemento de la posicion 10 con el dig. ver.
			if (publica == true) {
				if (digitoVerificador != d9) {
					valor = false;
				}
				/* El ruc de las empresas del sector publico terminan con 0001 */
				if (!ruc.substring(9, longitud).equals("0001")) {
					valor = false;
				}
			}

			if (privada == true) {
				if (digitoVerificador != d10) {
					valor = false;
				}
				if (!ruc.substring(10, longitud).equals("001")) {
					valor = false;
				}
			}

			if (natural == true) {
				if (digitoVerificador != d10) {
					valor = false;
				}
				if (ruc.length() > 10
						&& !ruc.substring(10, longitud).equals("001")) {
					valor = false;
				}
			}
		} catch (Exception e) {
			valor = false;
		}
		return valor;

	}
	
	

	/**
	 * Método para validar el contenido de campos de tipo JPasswordTextField
	 * 
	 * @param textoDeCampo
	 *            un objeto de tipo JPasswordTextField
	 * @param nombreDeCampo
	 *            dato de tipo String referencial al contenido del objeto
	 */
	public void camposJFormattedVacios(JPasswordField textoDeCampo,
			String nombreDeCampo) {
		if (String.valueOf(textoDeCampo.getPassword()).trim().equals("")) {
			errorMessage += "Campo '" + nombreDeCampo
					+ "' se encuentra vacío \n";
		}

	}

        
        //Comprobar usuario y contraseña//
        
        public void UserExist(Usuario usuario)
{
    try {
        EntityManagerFactory emFac = Persistence.createEntityManagerFactory("Quipus_v2.0PU");
    EntityManager entityManager = emFac.createEntityManager();
    Query query1 = entityManager.createQuery("Select u.idUsuario from Usuario u where u.cedulaUsuario = :usuarioCI and u.nombreUsuario= :usuarioName");
    query1.setParameter("usuarioCI", usuario.getCedulaUsuario())
            .setParameter("usuarioName", usuario.getNombreUsuario());
    int resutado = (int) query1.getSingleResult(); 
    System.out.println("ID"+resutado);
    usuario.setIdUsuario(resutado);
    } catch (Exception e) {
        errorMessage+="El usuario no se encuentra registrado \n Verifique la información";
    }
        
}
   //Comprobar Usuarios Repetidos
        
        public void userDuplication(Usuario usuario)
        {
            try {
                EntityManagerFactory emFac = Persistence.createEntityManagerFactory("Quipus_v2.0PU");
    EntityManager entityManager = emFac.createEntityManager();
    Query query1 = entityManager.createQuery("Select u from Usuario u where u.cedulaUsuario = :usuarioCI or u.nombreUsuario= :usuarioName");
    query1.setParameter("usuarioCI", usuario.getCedulaUsuario())
            .setParameter("usuarioName", usuario.getNombreUsuario());
    
                    errorMessage +="El usuario ya se encuntra ingresado";
                List<Usuario> list = query1.getResultList();
                String nomUsr = list.get(2).toString();
                System.out.println(nomUsr);
                System.out.println(list);
    //int resutado = (int) query1.getSingleResult();                 
            } catch (Exception e) {
                
            }
                
        }
        
        
        
        //////////////
        
        public void repeatUser(Usuario usuario)
        {
            
        }
        
        
        
        public boolean ErrorMesgIsEmpy() {
		if (!errorMessage.equals("")) {
			JOptionPane.showMessageDialog(null, errorMessage, "Error",
					JOptionPane.ERROR_MESSAGE);
			errorMessage = "";
			return true;
		} else {
			errorMessage = "";
			return false;
		}
	}
        
        
        
}
