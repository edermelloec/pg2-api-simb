package com.magossi.apisimb.testes;


import com.magossi.apisimb.domain.bovino.Bovino;
import com.magossi.apisimb.domain.bovino.Desmama;
import com.magossi.apisimb.domain.bovino.Morto;
import com.magossi.apisimb.domain.matriz.FichaTouro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GestaoRespositoryBanco {

    Float taxa;

    String sql;
    String json=null;




    public String prenhezTodos() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();


            sql = "select (select count(*) from resultado r where r.resultado = 'Cheia' ),count(*) from ficha_matriz fm where fm.status = 'True'";


            PreparedStatement prepareStatement;


            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();

            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }
            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }





        return json;


    }

    public String prenhezNovilha() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();


            sql = "select count(r.id_ficha_matriz),(select count(*) from ficha_matriz fm where fm.quant_parto = '0')  from ficha_matriz fm\n" +
                    "inner join resultado r on r.id_ficha_matriz=fm.id_ficha_matriz\n" +
                    "where r.resultado = 'Cheia' and fm.quant_parto = '0'";


            PreparedStatement prepareStatement;


            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }
            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return json;
    }

    public String prenhezPrimi() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();


            sql = "select count(r.id_ficha_matriz),(select count(*) from ficha_matriz fm where fm.quant_parto = '1')  from ficha_matriz fm\n" +
                    "inner join resultado r on r.id_ficha_matriz=fm.id_ficha_matriz\n" +
                    "where r.resultado = 'Cheia' and fm.quant_parto = '1'";


            PreparedStatement prepareStatement;
            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }
            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return json;
    }


    public String prenhezMult() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();


            sql = "select count(r.id_ficha_matriz),(select count(*) from ficha_matriz fm where fm.quant_parto > '1')  from ficha_matriz fm\n" +
                    "inner join resultado r on r.id_ficha_matriz=fm.id_ficha_matriz\n" +
                    "where r.resultado = 'Cheia' and fm.quant_parto > '1'";


            PreparedStatement prepareStatement;


            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }
            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return json;
    }

    public String natalidadeTodos() {

        try {
            Connection conexao = ConexaoFactory.criarConexao();


            sql = "select count(*),(select count(*) from parto p where p.status = 'Vivo') from ficha_matriz fm where fm.status = 'True'";


            PreparedStatement prepareStatement;

            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }
            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }





        return json;


    }

    public String natalidadeNovilha() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();


            sql = "select (select count(p.id_ficha_matriz)  from ficha_matriz fm inner join parto p on p.id_ficha_matriz=fm.id_ficha_matriz where p.status = 'Vivo' and fm.quant_parto = '1'),count(*) from ficha_matriz fm where fm.quant_parto = '1'";

            PreparedStatement prepareStatement;


            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }
            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }





        return json;


    }

    public String natalidadePrimi() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();


            sql = "select (select count(p.id_ficha_matriz)  from ficha_matriz fm inner join parto p on p.id_ficha_matriz=fm.id_ficha_matriz where p.status = 'Vivo' and fm.quant_parto = '2'),count(*) from ficha_matriz fm where fm.quant_parto = '2'";

            PreparedStatement prepareStatement;

            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }
            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




        return json;


    }

    public String natalidadeMult() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();


            sql = "select (select count(p.id_ficha_matriz)  from ficha_matriz fm\n inner join parto p on p.id_ficha_matriz=fm.id_ficha_matriz where p.status = 'Vivo' and fm.quant_parto > '2'),count(*) from ficha_matriz fm where fm.quant_parto > '2'";

            PreparedStatement prepareStatement;


            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }
            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }





        return json;


    }

    public String idadePrimeiraCria(String bovino) {

        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "SELECT min((DATE(p.data_parto)-DATE(b.data_nascimento))/30) AS DIASDECORRIDOS FROM bovino b\n" +
                    "\t\tinner join parto p on p.id_ficha_matriz=b.ficha_matriz_idfichamatriz\n" +
                    "\t\twhere b.nome_bovino = " + "'" + bovino + "'";
            PreparedStatement prepareStatement;

            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            taxa = result.getFloat(1);

            conexao.close();
            json = "[{\"nome\": \"" + bovino + "\",\"fertilidade\": " + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }





        return json;


    }

    public String idadeDesmama(String bovino) {

        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "select ((DATE(d.data_desmama)-DATE(b.data_nascimento))/30) from desmama d\n" +
                    "\t\t\tinner join bovino b on b.id_bovino=d.id_bovino\n" +
                    "\t\t\twhere b.nome_bovino =" + "'" + bovino + "'";

            PreparedStatement prepareStatement;

            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            taxa = result.getFloat(1);

            conexao.close();
            json = "[{\"nome\": \"" + bovino + "\",\"fertilidade\": " + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }





        return json;


    }

    public String mortalidadeTotal() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "select count(*), (select count(*) from bovino where status = 't')from bovino where situacao='Morto'\n";
            PreparedStatement prepareStatement;

            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();

            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }

            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return json;


    }

    public String mortalidadePreParto() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "select count(*),(select count(*) from bovino where status = 't') as dias from bovino b where b.situacao = 'Morto' and  (DATE(data_falecimento) - DATE (data_nascimento)) ='0'";
            PreparedStatement prepareStatement;

            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();

            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }
            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return json;


    }

    public String mortalideAdulto() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "select count(*),(select count(*) from bovino where status = 't') as dias from bovino b where b.situacao = 'Morto' and  (DATE(data_falecimento) - DATE (data_nascimento)) > '730'";
            PreparedStatement prepareStatement;

            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }


            conexao.close();



            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return json;


    }

    public String mortalideJovens() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "select count(*),(select count(*) from bovino where status = 't') as dias from bovino b where b.situacao = 'Morto' and  (DATE(data_falecimento) - DATE (data_nascimento)) >= '365' and  (DATE(data_falecimento) - DATE (data_nascimento)) <= '730'";
            PreparedStatement prepareStatement;

            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }

            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return json;


    }

    public String mortalideAteDesmama() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "select count(*),(select count(*) from bovino where status = 't') as dias from bovino b where b.situacao = 'Morto' and  (DATE(data_falecimento) - DATE (data_nascimento)) > '0' and  (DATE(data_falecimento) - DATE (data_nascimento)) < '365'";
            PreparedStatement prepareStatement;

            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }
            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return json;


    }

    public String taxaDesmama() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "select (select count(*) from desmama),count(*) from ficha_matriz where status = 't'";
            PreparedStatement prepareStatement;

            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }
            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return json;


    }

    public String taxaAbate() {
        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "select (select count(*) from bovino where situacao = 'Abatido'),count(*) from bovino where status = 't'";
            PreparedStatement prepareStatement;

            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            if(result.getFloat(2) != 0) {
                taxa = result.getFloat(1);
                taxa = ((taxa / result.getFloat(2)) * 100);
            }else{
                taxa = 0.0f;
            }
            conexao.close();
            json = "[{\"fertilidade\":" + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return json;


    }

    public String taxaDesfrute(String data) {


        try {
            Connection conexao = ConexaoFactory.criarConexao();


            sql = "select (select count(*) as inicial from bovino where DATE(data_inclusao) <= '"+data+"' ),count(*) as dois,(select count(*) as vendido from bovino where situacao = 'Vendido') from bovino where status = true and DATE(data_inclusao) >= '"+data+"'";


            PreparedStatement prepareStatement;
            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();
            taxa = (result.getFloat(1) - (result.getFloat(2)));
            taxa = taxa + result.getFloat(3);
            taxa = taxa / result.getFloat(1);

            conexao.close();
            json = "[{\"nome\": \"" + data + "\",\"fertilidade\": " + taxa + "}]";
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return json;


    }

    public String taxaTouroVaca() {


        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "select (select count(*) as touro from ficha_touro),count(*) as vaca from ficha_matriz";
            PreparedStatement prepareStatement;
            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();

            json = "[{\"nome\": \"" + result.getInt(1)+" : "+result.getInt(2) + "\",\"fertilidade\": " + taxa + "}]";
            conexao.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return json;


    }

    public void alterarBovino(Integer a) {


        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "UPDATE bovino SET situacao = 'Vivo' WHERE id_bovino =" + a;
            PreparedStatement prepareStatement;
            prepareStatement = conexao.prepareStatement(sql);
            prepareStatement.execute();

            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public Bovino salvarBovino(Bovino bovino) {


        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "insert into bovino(id_bovino,data_inclusao,data_nascimento,genero,mae,nome_bovino,pai,status,tag,url_foto,fazenda_idfazenda,pelagem_idpelagem,proprietario_idproprietario,raca_idraca,situacao) \n" +
                    "\tvalues (default,'" + bovino.getDataInclusao() + "','" + bovino.getDataNascimento() + "','" + bovino.getGenero() + "','" + bovino.getMae() + "','" + bovino.getNomeBovino() + "','" + bovino.getPai() + "','" + bovino.getStatus() + "','" + bovino.getTag() + "','http://comprerural.com.s3-us-west-2.amazonaws.com/wp-content/uploads/2015/11/20202430/boi_touro_backup_.jpg'" + "," + bovino.getFazenda().getIdFazenda() + "," + bovino.getPelagem().getIdPelagem() + "," + bovino.getProprietario().getIdProprietario() + "," + bovino.getRaca().getIdRaca() + ",'Vivo'" + ")";



            PreparedStatement ps;

            ps = conexao.prepareStatement(sql);


            ps.execute();

            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bovino;

    }


    public Bovino alterarBovino(Bovino bovino) {


        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "update bovino set data_nascimento = '" + bovino.getDataNascimento() + "',genero =" + bovino.getGenero() + ",status =" + bovino.getStatus() + ",mae = '" + bovino.getMae() + "',nome_bovino = '" + bovino.getNomeBovino() + "',pai='" + bovino.getPai() + "',fazenda_idfazenda = " + bovino.getFazenda().idFazenda + ",pelagem_idpelagem = " + bovino.getPelagem().getIdPelagem() + ",proprietario_idproprietario=" + bovino.getProprietario().getIdProprietario() + ",raca_idraca= " + bovino.getRaca().getIdRaca() + "where id_bovino =" + bovino.getIdBovino();




            PreparedStatement ps;

            ps = conexao.prepareStatement(sql);


            ps.execute();

            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bovino;

    }

    public Morto salvarMorte(Morto morto) {


        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "insert into morto (id_morte, causa, data_morte, id_bovino, status)\n" +
                    "\tvalues (default, '"+morto.getCausa()+"', '"+morto.getDataMorte()+"',"+morto.getIdBovino()+", "+true+")";


            PreparedStatement ps;

            ps = conexao.prepareStatement(sql);


            ps.execute();
            sql = "UPDATE bovino set status = false, situacao = 'Morto', data_falecimento = '"+morto.getDataMorte()+"' where id_bovino = "+morto.getIdBovino();
            ps = conexao.prepareStatement(sql);
            ps.execute();
            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return morto;

    }
    public Long salvarVendido(Long id) {


        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "UPDATE bovino SET situacao = 'Vendido', status = false where id_bovino = "+id;


            PreparedStatement ps;

            ps = conexao.prepareStatement(sql);


            ps.execute();

            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return id;

    }
    public Long salvarAbatido(Long id) {


        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "UPDATE bovino SET situacao = 'Abatido', status = false where id_bovino = "+id;


            PreparedStatement ps;

            ps = conexao.prepareStatement(sql);


            ps.execute();

            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return id;

    }

    public Desmama salvarDesmama(Desmama desmama) {


        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = " insert into desmama(id_desmama,data_desmama,status,id_ficha_matriz,id_bovino)\n" +
                    "\tvalues (default,'" + desmama.getDataDesmama() + "','True','" + desmama.getIdFichaMatriz() + "','" + desmama.getIdBovino() + "')";


            PreparedStatement ps;

            ps = conexao.prepareStatement(sql);


            ps.execute();

            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return desmama;

    }


    public List<Bovino> listarMatriz() {
        List<Bovino> bovinos = new ArrayList<>();

        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "select ficha_matriz_idfichamatriz,nome_bovino from bovino where ficha_matriz_idfichamatriz <> 0 and status = true order by ficha_matriz_idfichamatriz";


            PreparedStatement ps;

            ps = conexao.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            Bovino bovino;
            while (result.next()) {
                bovino = new Bovino();
                bovino.setIdBovino(result.getLong(1));
                bovino.setNomeBovino(result.getString(2));
                bovinos.add(bovino);

            }

            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bovinos;

    }

    public Bovino buscarNomeMatriz(Integer id) {
        Bovino bovino = null;

        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "select nome_bovino from bovino where ficha_matriz_idfichamatriz ="+id;


            PreparedStatement ps;

            ps = conexao.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

                bovino = new Bovino();
                result.next();
                bovino.setNomeBovino(result.getString(1));




            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bovino;

    }

    public List<Bovino> listarTouro() {
        List<Bovino> bovinos = new ArrayList<>();

        try {
            Connection conexao = ConexaoFactory.criarConexao();
            sql = "select id_bovino,nome_bovino from bovino where genero = true and status = true order by nome_bovino";


            PreparedStatement ps;

            ps = conexao.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            Bovino bovino;
            while (result.next()) {
                bovino = new Bovino();
                bovino.setIdBovino(result.getLong(1));
                bovino.setNomeBovino(result.getString(2));
                bovinos.add(bovino);
            }

            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bovinos;

    }

    public List<Bovino> listarFemea() {
        List<Bovino> bovinos = new ArrayList<>();

        try {
            Connection conexao = ConexaoFactory.criarConexao();
            sql = "select id_bovino,nome_bovino from bovino where genero = false and status = true and ficha_matriz_idfichamatriz is null order by nome_bovino";


            PreparedStatement ps;

            ps = conexao.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            Bovino bovino;
            while (result.next()) {
                bovino = new Bovino();
                bovino.setIdBovino(result.getLong(1));
                bovino.setNomeBovino(result.getString(2));
                bovinos.add(bovino);
            }

            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bovinos;

    }


    public void salvarQtdParto(Integer id) {
        Integer qtdParto=0;
        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "select quant_parto from ficha_matriz where id_ficha_matriz = "+id;
            PreparedStatement prepareStatement;
            prepareStatement = conexao.prepareStatement(sql);
            ResultSet result = prepareStatement.executeQuery();


            result.next();

            qtdParto = result.getInt(1);

            conexao.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            Connection conexao = ConexaoFactory.criarConexao();
            if(qtdParto == 0){
                sql = "update ficha_matriz set quant_parto = 1 where id_ficha_matriz = " + id;
            }else {
                sql = "update ficha_matriz set quant_parto = (quant_parto + 1) where id_ficha_matriz = " + id;
            }

            PreparedStatement ps;

            ps = conexao.prepareStatement(sql);


            ps.execute();

            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }



    public FichaTouro salvarTouro(FichaTouro touro) {


        try {
            Connection conexao = ConexaoFactory.criarConexao();

            sql = "insert into ficha_touro (id_ficha_touro, data_inclusao, id_bovino, status)\n" +
                    "\tvalues (default, '"+touro.getDataInclusao()+"', "+touro.getIdBovino()+","+touro.getStatus()+")";


            PreparedStatement ps;

            ps = conexao.prepareStatement(sql);


            ps.execute();

            conexao.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }


        return touro;
    }


}
