package vdg.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ConfigLSTM")
public class ConfiguracionLSTM {

		@Id
		@Column
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
		int idConfig;
		@Column
		int input_length;
		@Column
		int	output;
		@Column
		int	distanciaPermitida; 
		@Column
		int nunits;
		@Column
		int epochs;
		@Column
		int batch_size;
		@Column
		String pathDatos;
		@ManyToOne
		@JoinColumn(name = "idPersona")
	    private Persona idPersona;
			
		
		public int getInput_length() {
			return input_length;
		}
		public void setInput_length(int input_length) {
			this.input_length = input_length;
		}
		
		
		public int getOutput() {
			return output;
		}
		public void setOutput(int output) {
			this.output = output;
		}
		public int getDistanciaPermitida() {
			return distanciaPermitida;
		}
		public void setDistanciaPermitida(int distanciaPermitida) {
			this.distanciaPermitida = distanciaPermitida;
		}
		public int getNunits() {
			return nunits;
		}
		public void setNunits(int nunits) {
			this.nunits = nunits;
		}
		public int getEpochs() {
			return epochs;
		}
		public void setEpochs(int epochs) {
			this.epochs = epochs;
		}
		public int getBatch_size() {
			return batch_size;
		}
		public void setBatch_size(int batch_size) {
			this.batch_size = batch_size;
		}
		public String getPathDatos() {
			return pathDatos;
		}
		public void setPathDatos(String pathDatos) {
			this.pathDatos = pathDatos;
		}
		public int getIdConfig() {
			return idConfig;
		}
		public void setIdConfig(int idConfig) {
			this.idConfig = idConfig;
		}
		public Persona getIdPersona() {
			return idPersona;
		}
		public void setIdPersona(Persona idPersona) {
			this.idPersona = idPersona;
		}
		@Override
		public String toString() {
			return "ConfiguracionLSTM [idConfig=" + idConfig + ", input_length=" + input_length + ", output=" + output
					+ ", distanciaPermitida=" + distanciaPermitida + ", nunits=" + nunits + ", epochs=" + epochs
					+ ", batch_size=" + batch_size + ", pathDatos=" + pathDatos + ", idPersona=" + idPersona + "]";
		}
		
		
		
		
		
	
		
		
	
}
