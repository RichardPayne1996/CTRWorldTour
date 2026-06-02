package com.sparta.rp.ctrworldtour.dtos;

import com.sparta.rp.ctrworldtour.entities.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerDTO toDTO(Player player);
    Player toEntity(PlayerDTO playerDTO);
}
