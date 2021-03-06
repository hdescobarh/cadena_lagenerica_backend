package co.edu.unbosque.back_cadena_lagenerica.product;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class ProductJsonSerializer extends JsonSerializer<Product> {
	
	@Override
	public void serialize(Product value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException, JsonProcessingException {
				
		gen.writeNumberField("codigo_producto", value.getCodigo_producto());
		gen.writeNumberField("ivacompra", value.getIvacompra());
		gen.writeNumberField("nitproveedor", value.getProveedor().getNitproveedor());
		gen.writeStringField("nombre_producto", value.getNombre_producto());
		gen.writeNumberField("precio_compra", value.getPrecio_compra());
		gen.writeNumberField("precio_venta", value.getPrecio_venta());

			
		}
}