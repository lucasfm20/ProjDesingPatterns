package com.example.Medico.ConsultaAdapter;

import com.example.Medico.ConsultaAdapter.ConsultaExternaAdapter;
import com.example.Medico.ConsultaAdapter.ConsultaExternaDTO;
import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adapter-teste")
public class ConsultaAdapterController {


    @PostMapping
    public ResponseEntity<ConsultaDTO> adaptarConsulta(@RequestBody ConsultaExternaDTO externaDTO) {
        ConsultaDTO adaptado = ConsultaExternaAdapter.adaptar(externaDTO);
        return ResponseEntity.ok(adaptado);
    }
}
