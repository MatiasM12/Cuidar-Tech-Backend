package vdg.model.domain;

import javax.persistence.*;
	
	@Entity
	@Table(name = "Provincia")
public class Provincia {
		
		@Id
		@Column
		private int idProvincia;

		@Column
		private String nombre;
		
		public Provincia() {
			
		}

		public int getIdProvincia() {
			return idProvincia;
		}

		public void setIdProvincia(int idProvincia) {
			this.idProvincia = idProvincia;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		

}
