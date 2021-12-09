package co.edu.unbosque.back_cadena_lagenerica.sale;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class SaleSerializer extends JsonSerializer<Sale>{

	@Override
	public void serialize(Sale value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException{

		gen.writeNumberField("codigo_venta", value.getCodigo_venta());
		gen.writeNumberField("cedula_cliente", value.getCliente().getCedula_cliente());
		gen.writeStringField("nombre_cliente", value.getCliente().getNombre_cliente());
		gen.writeNumberField("cedula_usuario", value.getUsuario().getCedula_usuario());
		gen.writeNumberField("ivaventa", value.getIvaventa());
		gen.writeNumberField("total_venta", value.getTotal_venta());
		gen.writeNumberField("valor_venta", value.getValor_venta());
		
	}

}
