package br.ufsm.ceesp.ceee.beans;

import br.ufsm.ceesp.ceee.model.Curva;
import br.ufsm.ceesp.ceee.model.Importador;
import br.ufsm.ceesp.ceee.util.GeradorData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

public class ConversorCEEE {

    public static void main(String[] args) throws IOException {

        InputStream arquivo = null;
        File f = null;
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
            //This is where a real application would open the file.
            try {
                arquivo = new FileInputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(arquivo);

            NodeList listaTRF = doc.getElementsByTagName("TRF");

            int tamanhoLista = listaTRF.getLength();

            ArrayList<Importador> listaImp = new ArrayList<Importador>();

            for(int i=0; i<tamanhoLista; i++){

                Node noTRF = listaTRF.item(i);

                Importador imp = new Importador();

                if(noTRF.getNodeType() == Node.ELEMENT_NODE){

                    Element elementoTRF = (Element) noTRF;

                    String id = elementoTRF.getAttribute("id");

                    imp.setIdTrafo(Integer.parseInt(id));

                    NodeList listaDeFilhosTRF = elementoTRF.getChildNodes();

                    int tamanhoListaFilho = listaDeFilhosTRF.getLength();

                    // System.out.println("-------------------");
                    // System.out.println("ID do Trafo: "+id);
                    for(int j=0; j<tamanhoListaFilho; j++){

                        Node noFilho = listaDeFilhosTRF.item(j);

                        if(noFilho.getNodeType() == Node.ELEMENT_NODE){

                            Element elementoFilho = (Element) noFilho;

                            String idConsumidor = elementoFilho.getAttribute("id");
                            String desc = elementoFilho.getAttribute("dscr");
                            String gp = elementoFilho.getAttribute("group");
                            String sgp = elementoFilho.getAttribute("subgroup");
                            String cl = elementoFilho.getAttribute("class");
                            String cat = elementoFilho.getAttribute("category");

                            imp.setIdConsumidor(Integer.parseInt(idConsumidor));
                            imp.setDesc(desc);
                            imp.setGrupo(gp);
                            imp.setSubGrupo(sgp);
                            imp.setClasse(cl);
                            imp.setCategoria(cat);

                            // System.out.println("");
                            // System.out.println("CNS: id: "+idConsumidor+", Desc: "+desc+", GP: "+gp+", SGP: "+sgp+", " +
                            //        "Class: "+cl+", Category: "+cat);

                            NodeList listaDeFilhosCNS = noFilho.getChildNodes();

                            for(int k=0; k<listaDeFilhosCNS.getLength(); k++){

                                Node noFilhoCNS = listaDeFilhosCNS.item(k);

                                if(noFilhoCNS.getNodeType() == Node.ELEMENT_NODE){
                                    Element elementoFilhoCNS = (Element) noFilhoCNS;

                                    String tp = elementoFilhoCNS.getAttribute("type");
                                    String vl = elementoFilhoCNS.getAttribute("value");

                                    imp.setTipoMed(tp);
                                    imp.setValor(Double.parseDouble(vl));

                                    // System.out.println("");
                                    // System.out.println("MED: type: "+tp+", Value: "+vl);

                                    NodeList listaDeFilhosMED = noFilhoCNS.getChildNodes();

                                    for(int l=0; l<listaDeFilhosMED.getLength(); l++){

                                        Node noFilhoMED = listaDeFilhosMED.item(l);

                                        if(noFilhoMED.getNodeType() == Node.ELEMENT_NODE){
                                            // Element elementoFilhoMED = (Element) noFilhoMED;

                                            NodeList listaDeFilhosCRV = noFilhoMED.getChildNodes();

                                            ArrayList<Curva> lista = new ArrayList<Curva>();
                                            for(int h=0; h<listaDeFilhosCRV.getLength(); h++){

                                                Node noFilhoCRV = listaDeFilhosCRV.item(h);

                                                if(noFilhoCRV.getNodeType() == Node.ELEMENT_NODE){
                                                    Element elementoFilhoCRV = (Element) noFilhoCRV;

                                                    String p = elementoFilhoCRV.getAttribute("p");
                                                    String q = elementoFilhoCRV.getAttribute("q");

                                                    Curva curva = new Curva();
                                                    curva.setP(Double.parseDouble(p));
                                                    curva.setQ(Double.parseDouble(q));

                                                    lista.add(curva);

                                                    // System.out.println("P: "+p+", Q: "+q);
                                                }
                                            }
                                            imp.setCurva(lista);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    listaImp.add(imp);
                    // System.out.println("--------------------");
                }
            }

            // Criar arquivo
            File saida = new File(f.getAbsolutePath() + ".ceee.exp");

            if(!saida.exists()) {
                saida.createNewFile();
                System.out.println("Arquivo criado no diretorio..");
            }
            else{
                System.out.println("Arquivo Ja existente no diretorio..");
            }

            try {
                // Começo da escrita do arquivo.
                FileWriter fw = new FileWriter(saida);

                GeradorData gd = new GeradorData();
                String dataFormatada = gd.dataFormatada();
                String horaFormatada = gd.horaFormatada();
                String dataFormatadaReferencia = gd.dataFormatadaReferencia();

                fw.write("PRI;\r\n");
                fw.write(dataFormatada+" "+horaFormatada+"- v2.89;\r\n");
                fw.write("DATA DE REFERÊNCIA = "+dataFormatadaReferencia+";\r\n");

                fw.write("VER;\r\n4.0;\r\n\r\n");

                fw.write("CONSUMIDOR;\r\n");
                for(int i=0; i<listaImp.size(); i++){
                    if(listaImp.get(i).getIdConsumidor()>0){
                        fw.write(listaImp.get(i).getIdConsumidor()+";\t "+listaImp.get(i).getIdTrafo()+";\t "
                                +listaImp.get(i).getGrupo()+";\t "+listaImp.get(i).getDesc()
                                +";\t "+listaImp.get(i).getSubGrupo()+";\t "+listaImp.get(i).getCategoria()+";\t "
                                +listaImp.get(i).getTipoMed()+";\t "+listaImp.get(i).getValor()+";\r\n");
                    }
                }
                fw.write("CURVA;\r\n");
                for(int i=0; i<listaImp.size(); i++){
                    if(listaImp.get(i).getIdConsumidor()>0){
                        for(int j=0; j<listaImp.get(i).getCurva().size(); j++){
                            fw.write(listaImp.get(i).getIdConsumidor()+";\t "+listaImp.get(i).getCurva().get(j).getP()
                                    +"; \t"+listaImp.get(i).getCurva().get(j).getQ()+";\r\n");
                        }
                    }
                }

                fw.write("END;\r\n");

                fw.close();
                System.out.println("Arquivo preenchido..");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
