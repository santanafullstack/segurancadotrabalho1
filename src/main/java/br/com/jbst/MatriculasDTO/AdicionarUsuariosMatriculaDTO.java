package br.com.jbst.MatriculasDTO;

import java.util.List;
import java.util.UUID;

public class AdicionarUsuariosMatriculaDTO {
    private UUID idMatricula;
    private List<UUID> idsUsuarios;

    // Getters e Setters

    public UUID getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(UUID idMatricula) {
        this.idMatricula = idMatricula;
    }

    public List<UUID> getIdsUsuarios() {
        return idsUsuarios;
    }

    public void setIdsUsuarios(List<UUID> idsUsuarios) {
        this.idsUsuarios = idsUsuarios;
    }
}

