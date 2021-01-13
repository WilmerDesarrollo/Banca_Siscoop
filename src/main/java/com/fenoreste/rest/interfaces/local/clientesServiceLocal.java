
package com.fenoreste.rest.interfaces.local;

import com.fenoreste.rest.dto.siscoop.clientesDTO;
import com.fenoreste.rest.modelos.PersonasPK;

public interface clientesServiceLocal {
  
    clientesDTO buscar(String idorigen,String idgrupo,String idsocio);
     
}
