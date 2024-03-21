package com.example.catpuccino_back.converter;

import com.example.catpuccino_back.dto.AdopcionDTO;
import com.example.catpuccino_back.dto.GatoDTO;
import com.example.catpuccino_back.dto.SolicitudDTO;
import com.example.catpuccino_back.dto.UsuarioDTO;
import com.example.catpuccino_back.models.Adopcion;
import com.example.catpuccino_back.models.Gato;
import com.example.catpuccino_back.models.Solicitud;
import com.example.catpuccino_back.models.Usuario;
import com.example.catpuccino_back.service.GatoService;
import com.example.catpuccino_back.service.UsuarioService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SolicitudMapper {

    @Autowired
    protected UsuarioService usuarioService;

    UsuarioMapper usuarioMapper = Mappers.getMapper(UsuarioMapper.class);

    @Autowired
    protected GatoService gatoService;

    GatoMapper gatoMapper= Mappers.getMapper(GatoMapper.class);

    @Mapping(source = "idUsuario", target = "usuarioDTO", qualifiedByName = "conversorUsuarioDTO")
    @Mapping (source = "idGato", target = "gatoDTO", qualifiedByName = "conversorGatoDTO")
    public abstract SolicitudDTO toDTO (Solicitud entity);

    @Mapping(source = "usuarioDTO", target = "idUsuario",  qualifiedByName = "conversorUsuarioEntity")
    @Mapping(source = "gatoDTO", target = "idGato",  qualifiedByName = "conversorGatoEntity")
    public abstract Solicitud toEntity(SolicitudDTO dto);

    public abstract List<SolicitudDTO> toDTO(List<Solicitud>listAdopcion);
    public abstract List<Solicitud> toEntity(List<SolicitudDTO>listDTOs);

    @Named(value="conversorUsuarioDTO")
    UsuarioDTO conversorUsuario(Usuario entity){return usuarioMapper.toDTO(entity);}

    @Named(value="conversorGatoDTO")
    GatoDTO conversorGato(Gato entity){return gatoMapper.toDTO(entity);}

    @Named(value = "conversorUsuarioEntity")
    Usuario conversorUsuario (UsuarioDTO dto){
        Usuario usuario = usuarioService.getByIdUsuario(dto.getId());
        return usuario;
    }

    @Named(value="conversorGatoEntity")
    Gato conversorGato (GatoDTO dto){
        Gato gato = gatoService.getByIdGato(dto.getId());
        return gato;
    }



}