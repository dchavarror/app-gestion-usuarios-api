package co.com.dxc.gestion.usuario.maps;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.com.dxc.gestion.usuario.dtos.UsuarioDto;
import co.com.dxc.gestion.usuario.entities.UsuarioEntity;

@Mapper
public interface UsuarioMapper {
	
	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
	
	UsuarioDto entityToDto(UsuarioEntity entity);

	@InheritInverseConfiguration
	UsuarioEntity dtoToEntity(UsuarioDto dto);
	
	List<UsuarioDto> beanListToDtoList(List<UsuarioEntity> lista);
}
