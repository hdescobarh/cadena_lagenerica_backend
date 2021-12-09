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

		gen.writeNumberField("codigo_venta", value.codigo_venta);
		gen.writeNumberField("cedula_cliente", value.cliente.getCedula_cliente());
		gen.writeStringField("nombre_cliente", value.cliente.getNombre_cliente());
		gen.writeNumberField("cedula_usuario", value.usuario.getCedula_usuario());
		gen.writeNumberField("ivaventa", value.ivaventa);
		gen.writeNumberField("total_venta", value.total_venta);
		gen.writeNumberField("valor_venta", value.valor_venta);
		
	}

}
