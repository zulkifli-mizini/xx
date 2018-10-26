package com.example.zul_mizini.xmlparser2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Main2Activity extends AppCompatActivity {
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        tv1 = (TextView) findViewById(R.id.textView1);

        try {
            InputStream is = getAssets().open("file.xml"); //get asset xml untuk parsing data

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("datakelompok"); // mengmbil semua tag employee datakelompok

            for (int i = 0; i < nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    tv1.setText(tv1.getText() + "\nNama : " + getValue("name", element2) + "\n");
                    tv1.setText(tv1.getText() + "Nama kelompok : " + getValue("namakelompok", element2) + "\n");
                    tv1.setText(tv1.getText() + "-----------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

}