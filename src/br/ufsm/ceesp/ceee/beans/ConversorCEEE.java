package br.ufsm.ceesp.ceee.beans;

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

public class ConversorCEEE {

    public static void main(String[] args){

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

            // NodeList listaData = doc.getElementsByTagName("DATE");
            /*
            int tamanhoLista = listaData.getLength();

            for(int i=0; i<tamanhoLista; i++){

                Node noData = listaData.item(i);

                if(noData.getNodeType() == Node.ELEMENT_NODE) {

                    Element elementoData = (Element) noData;

                    // day="17" month="08" year="2016"
                    String day = elementoData.getAttribute("day");
                    String month = elementoData.getAttribute("month");
                    String year = elementoData.getAttribute("year");

                    System.out.println("Day: "+day+", Month: "+month+", Year: "+year);
                }
            } */

            NodeList listaTRF = doc.getElementsByTagName("TRF");

            int tamanhoLista = listaTRF.getLength();

            for(int i=0; i<tamanhoLista; i++){

                Node noTRF = listaTRF.item(i);

                if(noTRF.getNodeType() == Node.ELEMENT_NODE){

                    Element elementoTRF = (Element) noTRF;

                    String id = elementoTRF.getAttribute("id");

                    NodeList listaDeFilhosTRF = elementoTRF.getChildNodes();

                    int tamanhoListaFilho = listaDeFilhosTRF.getLength();

                    System.out.println("-------------------");
                    System.out.println("ID do Trafo: "+id);
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

                            System.out.println("");
                            System.out.println("CNS: id: "+idConsumidor+", Desc: "+desc+", GP: "+gp+", SGP: "+sgp+", " +
                                    "Class: "+cl+", Category: "+cat);


                        }
                    }
                    System.out.println("--------------------");
                }

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
