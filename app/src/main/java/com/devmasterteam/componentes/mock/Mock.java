package com.devmasterteam.componentes.mock;

import java.util.ArrayList;
import java.util.List;

public class Mock {

    /**
     * Retorna lista de strings para spinner
     */
    public static List<String> spinnerMock() {
        List<String> lst = new ArrayList<>();
        lst.add("Gramas");
        lst.add("Kg");
        lst.add("Arroba");
        lst.add("Tonelada");

        return lst;
    }

}