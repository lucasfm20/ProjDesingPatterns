package com.example.Medico.ConsultaAdapter;

import com.example.Medico.ConsultaAdapter.ConsultaExternaAdapter;
import com.example.Medico.ConsultaAdapter.ConsultaExternaDTO;
import com.example.Medico.dtos.ConsultaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adapter-teste")
public class ConsultaAdapterController {

    @GetMapping
    public ResponseEntity<ConsultaDTO> testarAdapter() {

        ConsultaExternaDTO externaDTO = new ConsultaExternaDTO();
        externaDTO.setIdConsulta(999L);
        externaDTO.setIdPaciente(111L);
        externaDTO.setIdMedico(222L);
        externaDTO.setData("2025-06-04T14:30:00"); 
        externaDTO.setDetalhes("Consulta simulada via Adapter");


        ConsultaDTO consultaDTO = ConsultaExternaAdapter.adaptar(externaDTO);

        return ResponseEntity.ok(consultaDTO);
    }
}
