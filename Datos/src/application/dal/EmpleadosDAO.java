package application.dal;

import java.util.Collection;
import java.util.Hashtable;


public class EmpleadosDAO {
	private static Hashtable<Integer, Empleado> listado = new Hashtable<Integer, Empleado>();
	static {
		listado.put(1, new Empleado(1, "Pepito", "Grillo", "Madrid", true));
		listado.put(2, new Empleado(2, "Carmelo", "Coton", "A Coruña", false));
	}
	
	public Collection<Empleado> getAll() {
		return listado.values();
	}
	
	public Empleado get(int key) throws Exception {
		if(0>key || key>listado.size() )
			throw new Exception("No encontrado.");
		Empleado rslt = listado.get(key);
		if(rslt == null)
			throw new Exception("No encontrado.");
		return rslt;
	}
	public Empleado add(Empleado item) throws Exception {
		if(item == null)
			throw new Exception("Argumentos invalidos.");
		Empleado rslt = listado.get(item.getIdEmpleado());
		if(rslt != null)
			throw new Exception("Clave duplicada.");
		if(item.isInvalid())
			throw new Exception("Error en datos.");
		listado.put(item.getIdEmpleado(), item);
		return rslt;
	}
	public Empleado edit(Empleado item) throws Exception {
		Empleado rslt = listado.get(item.getIdEmpleado());
		if(rslt == null)
			throw new Exception("No encontrado.");
		if(item.isInvalid())
			throw new Exception("Error en datos.");
		listado.put(item.getIdEmpleado(), item);
		return rslt;
	}
	public Empleado delete(int key) throws Exception {
		Empleado rslt = listado.get(key);
		if(rslt == null)
			throw new Exception("No encontrado.");
		listado.remove(key);
		return rslt;
	}
}
