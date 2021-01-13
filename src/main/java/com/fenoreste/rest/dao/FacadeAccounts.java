package com.fenoreste.rest.dao;

import com.fenoreste.rest.dto.siscoop.accountDTO;
import com.fenoreste.rest.impl.AbstractFacade;
import com.fenoreste.rest.modelos.cuentas_banca_movil;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public abstract class FacadeAccounts<T> {

    private static EntityManagerFactory emf;
    private static final String PERSISTENCE_UNIT_NAME = "conexion";
    private static final EntityManager em = null;

    public FacadeAccounts(Class<T> entityClass) {
        emf = AbstractFacade.conexion();
    }

    /**
     * **********************************Validacion de datos para crear cuenta*******************************************
     */
    public List<Object[]> getValidaton(String customerId, String accountType, int productCode) {
        EntityManager em = emf.createEntityManager();
        List<Integer> lista_ahorros = Arrays.asList(100, 101, 102, 103, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 140, 141, 142, 143, 144, 146, 148, 150, 198, 501);
        List<Integer> lista_DFPS = Arrays.asList(200, 201);
        List<Integer> lista_prestamos = Arrays.asList(30000, 30102, 30103, 30104, 30105, 30106, 30112, 30113, 30115, 30122, 30123, 30202, 30212, 30222, 30302, 30312, 30322, 30402, 30412, 30422, 30502, 30512, 30522, 30602,
                30612, 30622, 30702, 30703, 30704, 30712, 30713, 30714, 30722, 30724, 30802, 30812, 30822, 30902, 30912, 30922, 31001, 31002, 31011, 31012, 31013, 31021, 31022, 31023,
                31033, 31102, 31112, 31122, 31203, 31213, 31223, 31303, 31304, 31305, 31306, 31313, 31314, 31315, 31323, 31324, 31325, 31403, 31413, 31423, 31503, 31513, 31523, 31603,
                31613, 31623, 31703, 31713, 31723, 31803, 31813, 31823, 32002, 32102, 32202, 32302, 32402, 32502, 32512, 32602, 32702, 32802, 32902, 33002, 33012, 33102, 33112, 33202,
                33212, 33302, 33312, 33402, 33412, 33502, 33512, 33602, 33612, 33702, 33712, 33802, 33812, 33822, 33902, 33912, 34002, 34012, 34102, 34112, 34122, 34202, 34212, 34302,
                34312, 34322, 34402, 34412, 34422, 34502, 34602, 34702, 34802, 34902, 34912, 34922, 35002, 35102, 35202, 35303, 35402, 35502, 35512, 35522, 35602, 35603, 35612, 35613, 35622, 35623, 35702, 35802, 35902);
        List<Object[]> objetos = null;
        List<Object[]> objetos1 = null;
        System.out.println(accountType.toUpperCase());
        /**
         * ******************************************Variables que ocupamos en consulta***********************************
         */
        String feecode = "";
        String descripcion = "";
        List<Object[]> lista = null;
        /**
         * ******************************************************************************************************************
         */
        boolean bandera = false;
        if (accountType.equalsIgnoreCase("ahorro")) {
            for (int i = 0; i < lista_ahorros.size(); i++) {
                if (productCode == lista_ahorros.get(i)) {
                    feecode = "CAPTACION";
                    descripcion = "PRODUCTO AHORRO";
                    bandera = true;
                }
            }
        }

        if (accountType.equalsIgnoreCase("dpfs")) {
            for (int i = 0; i < lista_ahorros.size(); i++) {
                if (productCode == lista_ahorros.get(i)) {
                    feecode = "CAPTACION";
                    descripcion = "PRODUCTO DPFS";
                    bandera = true;
                }
            }
        }

        if (accountType.equalsIgnoreCase("prestamo")) {
            for (int i = 0; i < lista_prestamos.size(); i++) {
                if (productCode == lista_prestamos.get(i)) {
                    feecode = "COLOCACION";
                    descripcion = "PRODUCTO PRESTAMO";
                    bandera = true;
                }
            }
        }
        
        Query queryD=em.createNativeQuery("SELECT description FROM siscoop_productos WHERE productCode="+productCode);
        descripcion=(String)queryD.getSingleResult();

        System.out.println(feecode + "," + descripcion);
        ///se usa para saber si existe el registro en auxiliares      
        String consulta = "SELECT *  FROM auxiliares WHERE replace((to_char(idorigen,'099999')||to_char(idgrupo,'09')||to_char(idsocio,'099999')),' ','')='" + customerId + "'AND idproducto=" + productCode + " AND estatus=2";
        ///se usa para comprobar si ya existe el registro en la tabla donde guarda y prepara las validaciones
        String consulta1 = "SELECT * FROM validar_crear_cuenta WHERE replace((to_char(idorigen,'099999')||to_char(idgrupo,'09')||to_char(idsocio,'099999')),' ','')='" + customerId + "' AND idproducto=" + productCode;
        ///Se usa para llevar los datos a la tabla validar_crear_cuenta
        String insertar_validar = "INSERT INTO validar_crear_cuenta VALUES(?,?,?,?,?,?,?)";
        //consulta para insetar en la tabla auxiliares de validacion aux_validaciones_cuenta
        String insertar_aux_va = "INSERT INTO aux_validaciones_cuenta VALUES (?,?,?,?,?,?,?)";
        //Query para obtener saldo y actualizar en registros anteriores si ya existes en la tabla de validar_crear cuenta
        String saldo = "SELECT saldo FROM auxiliares WHERE replace((to_char(idorigen,'099999')||to_char(idgrupo,'09')||to_char(idsocio,'099999')),' ','')='" + customerId + "' AND idproducto=" + productCode + "aND estatus=2";
        //Se obtiene el id de validacion para saber que registro actualizar el monto
        String id_validacion = "SELECT validationid FROM validar_crear_cuenta WHERE replace((to_char(idorigen,'099999')||to_char(idgrupo,'09')||to_char(idsocio,'099999')),' ','')='" + customerId + "'AND idproducto=" + productCode;
        ///Query de ejecucion para actualizar monto de registros existentes
        String act_monto = "UPDATE aux_validaciones_cuenta SET amount =:amon WHERE validationid=:validation";

        try {
            System.out.println(" Entrando al primer try");
            Query query = em.createNativeQuery(consulta);//Verifica si existen registros activos en auxiliares
            Query query1 = em.createNativeQuery(consulta1);//Apoyo para verificar si anteriorme se habia echo validacion con este producto
            System.out.println("aquiii");
            objetos = query.getResultList();
            objetos1 = query1.getResultList();
            System.out.println("Registros ob:" + objetos.size());
            System.out.println("Pasando de las consultas");
            if (objetos.size() > 0 && objetos1.size() == 0) {//si no se habia echo validacion entra aqui
                System.out.println("Entro en el primer if");
                try {
                    String o = "", a = "";
                    try {

                        Query queryInsertar = em.createNativeQuery("SELECT idorigenp,idauxiliar FROM auxiliares WHERE replace((to_char(idorigen,'099999')||to_char(idgrupo,'09')||to_char(idsocio,'099999')),' ','')='" + customerId + "'AND idproducto=" + productCode + " AND estatus=2 ");
                        List<Object[]> opa = queryInsertar.getResultList();

                        for (Object[] obj : opa) {
                            o = String.valueOf(obj[0].toString());
                            a = String.valueOf(obj[1].toString());
                        }

                        EntityTransaction tr1 = em.getTransaction();//consigue una transaccion para la persistencia e inserta registros de la consulta a la tabla crear_validar_cuenta
                        tr1.begin();
                        Query exe1 = em.createNativeQuery(insertar_validar);
                        exe1.setParameter(1, Integer.parseInt(customerId.substring(1, 6)));
                        exe1.setParameter(2, Integer.parseInt(customerId.substring(6, 8)));
                        exe1.setParameter(3, Integer.parseInt(customerId.substring(8, 14)));
                        exe1.setParameter(4, Integer.parseInt(o));
                        exe1.setParameter(5, productCode);
                        exe1.setParameter(6, Integer.parseInt(a));
                        exe1.setParameter(7, Integer.parseInt(o) + productCode + Integer.parseInt(a));
                        int rowsUpdated = exe1.executeUpdate();
                        System.out.println("Registros insertados en validar crear cuenta: " + rowsUpdated);
                        tr1.commit();

                    } catch (Exception e) {
                        System.out.println("error al insertar en validar_crear_cuenta:" + e.getMessage());
                    }

                    String fcode = "", desc = "", currenCode = "";
                    double amount = 0.0;
                    String daXe = "";
                    int validationId = Integer.parseInt(o) + productCode + Integer.parseInt(a);

                    try {

                        Query aux_val = em.createNativeQuery("SELECT tasaio,saldo,(SELECT nombre FROM productos WHERE idproducto=" + productCode + "),fechaactivacion FROM auxiliares WHERE idorigenp=" + Integer.parseInt(o) + "AND idproducto=" + productCode + "AND idauxiliar=" + Integer.parseInt(a));
                        List<Object[]> au = aux_val.getResultList();
                        System.out.println("Segundo for");
                        for (Object[] obj : au) {
                            feecode = String.valueOf(obj[0].toString());
                            amount = Double.parseDouble(String.valueOf(obj[1]));
                            desc = String.valueOf(obj[2]);
                            currenCode = String.valueOf(productCode);
                            daXe = String.valueOf(obj[3]);
                            validationId = Integer.parseInt(o) + productCode + Integer.parseInt(a);
                        }

                        EntityTransaction tr2 = em.getTransaction();//consigue una transaccion e inserta en la tabla de apoyo aux_validaciones_cuenta
                        tr2.begin();
                        Query exe2 = em.createNativeQuery(insertar_aux_va);
                        exe2.setParameter(1, feecode);
                        exe2.setParameter(2, amount);
                        exe2.setParameter(3, desc);
                        exe2.setParameter(4, daXe);
                        exe2.setParameter(5, currenCode);
                        exe2.setParameter(6, validationId);
                        exe2.setParameter(7, descripcion);
                        int rowsUpdated1 = exe2.executeUpdate();
                        System.out.println("Registros insertados en aux_validaciones_cuenta:" + rowsUpdated1);
                        tr2.commit();
                        String registros_nuevos = "SELECT vp.validationid,va.feescode,va.amount,va.currencycode,va.description,va.date_execution FROM validar_crear_cuenta vp inner join aux_validaciones_cuenta va using(validationid) WHERE vp.idproducto=" + productCode;//Consulta para insertar cuando no existen registros validados
                        Query re = em.createNativeQuery(registros_nuevos);
                        lista = re.getResultList();
                    } catch (Exception e) {
                        System.out.println("Error al insertar en aux_validar_crear:" + e.getMessage());
                    }

                } catch (Exception e) {
                    System.out.println("Error al insertar registros en validaciones:" + e.getMessage());
                }

            } else if (objetos.size() > 0 && objetos1.size() > 0) {//si ya existen registros entra aqui 
                System.out.println("Ya existen registros");
                try {
                    EntityTransaction tr = em.getTransaction();//consigue una transaccion para acutalizar el saldo al registro existente
                    tr.begin();
                    Query exec = em.createNativeQuery(saldo);
                    Query exec1 = em.createNativeQuery(id_validacion);
                    Double validationid = Double.parseDouble(exec1.getSingleResult().toString());
                    Query updt = em.createNativeQuery(act_monto);
                    updt.setParameter("amon", Double.parseDouble(exec.getSingleResult().toString()));
                    updt.setParameter("validation", validationid);

                    int registrosm = updt.executeUpdate();
                    System.out.println("Registros actualizados:" + registrosm);
                    tr.commit();
                    String registros_validado = "SELECT vp.validationid,va.feescode,va.amount,va.currencycode,va.description,va.date_execution FROM validar_crear_cuenta vp inner join aux_validaciones_cuenta va using(validationid) WHERE vp.idproducto=" + productCode;//Consulta para insertar cuando ya existen registros validados
                    Query re = em.createNativeQuery(registros_validado);
                    lista = re.getResultList();
                } catch (Exception e) {
                    System.out.println("Error en actualizar saldo:" + e.getMessage());
                }
                // return lista;
            } else {
                System.out.println("Error general catch");
                return null;
            }
        } catch (Exception e) {
        } finally {
            em.clear();
        }
        return lista;
    }

    /**
     * ***********************************Ejecutar datos validados crear cuenta*****************************************
     */
    public List<Object[]> setAccount(int validationId) {
        System.out.println("llego a face accounts");
        EntityManager em = emf.createEntityManager();
        List<Object[]> lista = null;
        String sql = "SELECT v.idorigen,v.idgrupo,v.idsocio,v.idorigenp,v.idproducto,v.idauxiliar,va.feescode,va.amount,va.description,va.date_execution,va.currencycode,va.validationid "
                + "FROM validar_crear_cuenta  v "
                + "INNER JOIN aux_validaciones_cuenta va using(validationid) WHERE validationid=" + validationId;
        System.out.println("paso");
        Query query1 = em.createNativeQuery(sql);
        List<Object[]> lista11 = query1.getResultList();
        System.out.println("aun pasoooooooooooooo");
        int idp = 0;
        String customer = "";

        int o = 0, g = 0, s = 0, o1 = 0, p = 0, a = 0, validationid = 0;
        double amount = 0;
        boolean estUser = false;
        String feesCode = "", description = "", currencyCode = "", dateExecution = "";

        for (Object[] obj : lista11) {
            System.out.println("dentro de l fro");
            o = Integer.parseInt(String.valueOf(obj[0].toString()));
            g = Integer.parseInt(String.valueOf(obj[1].toString()));
            s = Integer.parseInt(String.valueOf(obj[2].toString()));
            o1 = Integer.parseInt(String.valueOf(obj[3].toString()));
            p = Integer.parseInt(String.valueOf(obj[4].toString()));
            a = Integer.parseInt(String.valueOf(obj[5].toString()));
            estUser = true;
            feesCode = String.valueOf(obj[6].toString());
            amount = Double.parseDouble(String.valueOf(obj[7].toString()));
            description = String.valueOf(obj[8].toString());
            dateExecution = String.valueOf(obj[9].toString());
            currencyCode = String.valueOf(obj[10].toString());
            validationId = Integer.parseInt(String.valueOf(obj[11].toString()));

        }

        try {

            EntityTransaction et1;
            et1 = em.getTransaction();
            et1.begin();
            Query insertarUsuarios = em.createNativeQuery("INSERT INTO banca_movil_usuarios VALUES(?,?,?,?,?,?,?,?)");
            insertarUsuarios.setParameter(1, o);
            insertarUsuarios.setParameter(2, g);
            insertarUsuarios.setParameter(3, s);
            insertarUsuarios.setParameter(4, ' ');
            insertarUsuarios.setParameter(5, o1);
            insertarUsuarios.setParameter(6, p);
            insertarUsuarios.setParameter(7, a);
            insertarUsuarios.setParameter(8, estUser);

            Query quer = em.createNativeQuery("SELECT  COUNT(*)  FROM banca_movil_usuarios WHERE idorigenp=" + o + " AND idproducto=" + p + " AND idauxiliar=" + a);
            int result = Integer.parseInt(String.valueOf(quer.getSingleResult()));
            if (result == 0) {
                int rowsA = insertarUsuarios.executeUpdate();
                System.out.println("Usuarios insertados:" + rowsA);
            } else {
                System.out.println("Usuario ya registrado.");
            }
            et1.commit();
        } catch (Exception e) {
            System.out.println("Error al insertar usuarios:" + e.getMessage());
        }
        System.out.println("salio");
        try {
            EntityTransaction et2;
            et2 = em.getTransaction();
            et2.begin();
            Query query = em.createNativeQuery("INSERT INTO cuentas_banca_movil VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            query.setParameter(1, validationId);
            query.setParameter(2, 0);
            query.setParameter(3, o + p + a);
            query.setParameter(4, o + "" + p + "" + a);
            query.setParameter(5, o + "" + p + "" + a);
            query.setParameter(6, o * Math.random());
            query.setParameter(7, estUser);
            query.setParameter(8, o);
            query.setParameter(9, g);
            query.setParameter(10, s);
            query.setParameter(11, o1);
            query.setParameter(12, p);
            query.setParameter(13, a);
            query.setParameter(14, "Registro de datos");

            Query querys = em.createNativeQuery("SELECT * FROM cuentas_banca_movil WHERE validationid=" + validationId);
            lista = querys.getResultList();

            if (lista.size() == 0) {
                int rowsUpdated = query.executeUpdate();
                System.out.println("Registros insertados, Cuentas: " + rowsUpdated);
            } else {
                System.out.println("Cuenta ya registrada, Retornando datos");
            }
            et2.commit();
        } catch (Exception e) {
            System.out.println("Error en crear cuenta:" + e.getMessage());
        } finally {
            em.clear();
        }
        return lista;
    }

    /**
     * **********************************Obtener detalles de cuenta*****************************************************
     */
    public accountDTO detailsAccount(String accountId) {
       
        EntityManager em = emf.createEntityManager();
        List<Object[]> lista = null;
        accountDTO cuenta = null;
        try {
            System.out.println("Dentro del try");
        cuentas_banca_movil cuentas=em.find(cuentas_banca_movil.class,Integer.parseInt(accountId));
        Query queryAux=em.createNativeQuery("SELECT accounttype,currencycode from aux_validaciones_cuenta WHERE validationid="+cuentas.getValidationid());
        List<Object[]>listaObjetos=queryAux.getResultList();
        String accountType="";
        String currencyCode="";
        for(Object [] objetos:listaObjetos){
            accountType=objetos[0].toString();
            currencyCode=objetos[1].toString();            
        }
                
        
            cuenta = new accountDTO(accountId,cuentas.getAccountnumber(),cuentas.getDisplayaccountnumber(), accountType,currencyCode,String.valueOf(cuentas.getIdproducto()),true);
        } catch (Exception e) {
            System.out.println("Error en obtener detalles de cuenta:" + e.getMessage().toString());
        } finally {
            cerrar();
          }
        System.out.println("Cuenta:"+cuenta);
        return cuenta;
    }

    /**
     * *******************************GetAccountHolders(Titutares de la cuenta)*****************************************
     */
    public String getHolders(long accountId) {
        EntityManager em = emf.createEntityManager();
        String lista = "";
        try {
            String QuerySql = "SELECT (select p.appaterno||' '||p.apmaterno||' '||p.nombre from personas p where replace((to_char(idorigen,'099999')||to_char(idgrupo,'09')||to_char(idsocio,'099999')),' ','')=cbm.customerid) as nombre"
                    + "\n FROM cuentas_banca_movil cbm WHERE accountid=" + accountId;
            Query query = em.createNativeQuery(QuerySql);
            lista = (String) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error en holders:" + e.getMessage());
        }
        return lista;
    }

    /**
     * ************************************Get Account Holds Pendienteeeeeeeeeeeee**************************************
     */
    public List<Object[]> getHolds(int accountId, String property1, String property2) {
        EntityManager em = emf.createEntityManager();
        List<Object[]> lista = null;
        try {
            String QuerySql = "select p.appaterno||' '||p.apmaterno||' '||p.nombre as nombre,"
                    + "c.property1,c.property2 from personas p "
                    + "inner join cuentasbm c using(idorigen,idgrupo,idsocio)";
            Query query = em.createNativeQuery(QuerySql);
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("Error en holds:" + e.getMessage());
        }
        return lista;
    }

    /**
     * ********************************Validate Internal Account********************************************************
     */
    public List<Object[]> getValidateInternalAccount(Long accountNumber, String accountType) {
        EntityManager em = emf.createEntityManager();
        List<Object[]> lista = null;
        try {
            String QuerySql = "SELECT accountid,accounttype,"
                    + "(select nombre||' '||appaterno||' '||apmaterno from personas p where replace((to_char(idorigen,'099999')||to_char(idgrupo,'09')||to_char(idsocio,'099999')),' ','')=cbm.customerid)as nombre,"
                    + "displayaccountnumber FROM cuentas_banca_movil cbm WHERE accountnumber=" + accountNumber + " AND UPPER(accounttype) LIKE '%" + accountType.toUpperCase() + "%'";
            Query query = em.createNativeQuery(QuerySql);
            lista = query.getResultList();

        } catch (Exception e) {
            System.out.println("Error al validar cuenta interna:" + e.getMessage());
        }
        return lista;
    }

    /**
     * **************************** Statements**************************************************************************
     */
    /**
     * ****************************validate change account status*******************************************************
     */
    public List<Object[]> getValidateChangeStatus(long accountId, int statusCode, String reason) {
        EntityManager em = emf.createEntityManager();
        List<Object[]> lista = null;
        String descripcion = "";

        String consulta = "SELECT st.validationid,aux.feescode,aux.amount,aux.currencycode,aux.description,(select now()) \n"
                + " FROM validar_crear_cuenta vca \n"
                + " INNER JOIN aux_validaciones_cuenta aux USING(validationid) \n"
                + " INNER JOIN cuentas_banca_movil bm USING(validationid) \n"
                + "INNER JOIN status_code_accounts st ON(bm.accountid=st.accountid)"
                + "WHERE bm.accountid=" + accountId + "";

        String consultap = "SELECT * FROM status_code_accounts WHERE accountid=" + accountId;
        Query q = em.createNativeQuery(consultap);
        if (statusCode == 1) {
            descripcion = "enabled";
        } else if (statusCode == 2) {
            descripcion = "disabled";
        }
        if (q.getResultList().isEmpty()) {
            try {

                EntityTransaction et1 = em.getTransaction();
                et1.begin();
                Query query = em.createNativeQuery("INSERT INTO status_code_accounts VALUES(?,?,?,?,?)");

                query.setParameter(1, accountId);
                query.setParameter(2, statusCode);
                query.setParameter(3, reason);
                query.setParameter(4, descripcion);
                query.setParameter(5, accountId + statusCode);
                int rowsUpdated = query.executeUpdate();
                et1.commit();
                System.out.println("Registros insertados:" + rowsUpdated);
                Query queryN = em.createNativeQuery(consulta);
                lista = queryN.getResultList();
                System.out.println("Registros:" + lista.size());
            } catch (Exception e) {
                System.out.println("Error al validar cambio de estado:" + e.getMessage());
            }

        } else {
            EntityTransaction tr = em.getTransaction();
            tr.begin();
            Query qwery = em.createNativeQuery("UPDATE status_code_accounts SET statuscode=:stc,reasonchange=:re WHERE accountid=:ac");
            qwery.setParameter("stc", statusCode);
            qwery.setParameter("re", reason);
            qwery.setParameter("ac", accountId);
            int registrosu = qwery.executeUpdate();
            System.out.println("Registros actualizados:" + registrosu);
            tr.commit();
            Query queryN = em.createNativeQuery(consulta);
            lista = queryN.getResultList();
        }
        return lista;

    }

    /**
     * *************************************change status***************************************************************
     */
    public List<Object[]> getChangeStatus(long validationId, String originatorReferencedId) {
        EntityManager em = emf.createEntityManager();
        List<Object[]> lista = null;
        try {
            String sql = "SELECT st.statuscode,st.description,st.accountid FROM status_code_accounts st \n"
                    + "INNER JOIN cuentas_banca_movil cbm ON(cbm.accountid=st.accountid) \n"
                    + "WHERE st.validationid=" + validationId;
            Query io = em.createNativeQuery(sql);
            lista = io.getResultList();
            int stc = 0;
            long acc = 0;
            for (Object[] lo : lista) {
                stc = Integer.parseInt(lo[0].toString());
                acc = Long.parseLong(lo[2].toString());
            }
            EntityTransaction tr = em.getTransaction();
            tr.begin();
            Query t = em.createNativeQuery("UPDATE cuentas_banca_movil SET status=:st WHERE accountid=:ac");
            t.setParameter("st", stc);
            t.setParameter("ac", acc);

            System.out.println("objetos encontrados:" + lista.size());
        } catch (Exception e) {
            System.out.println("Error change status:" + e.getMessage());
        }
        return lista;
    }

    /**
     * ******************************************Get Balances ***********************************************************
     */
    public List<Object[]> getBalances(String accountId) {
        EntityManager em = emf.createEntityManager();
        List<Object[]> lista = null;
        try {
            String QuerySql = "select accountidstatuscode,reasonchange from cuentasbm where validationid='" + "";
            Query query = em.createNativeQuery(QuerySql);
            lista = query.getResultList();
            System.out.println("objetos encontrados:" + lista.size());
        } catch (Exception e) {
            System.out.println("Error change status:" + e.getMessage());
        }
        return lista;
    }

    /**
     * *********************************Cerrando conexiones ***********************************
     */
    public void cerrar(){
        emf.close();
    }

}
