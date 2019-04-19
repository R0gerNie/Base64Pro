import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board {
    public void createBoard(){
        //前端面板
        JFrame Frame=new JFrame("BaseNHex Pro By Francisco Nie0.01");
        JPanel jp=new JPanel();

        Frame.setSize(500,300);
        Box all=Box.createVerticalBox();


        Box bh11=Box.createHorizontalBox();
        Box bh12=Box.createHorizontalBox();
        Box bh13=Box.createHorizontalBox();

        JLabel t1=new JLabel();
        JLabel t2=new JLabel();
        JLabel t3=new JLabel();
        JTextArea plain=new JTextArea("要编码或解码的内容",5,25);
        JTextArea phex=new JTextArea("hex:");
        JScrollPane jsp=new JScrollPane(plain);
        jsp.setBounds(13, 10, 350, 340);

        jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane jpp=new JScrollPane(phex);
        jpp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        plain.setBounds(10,10,200,80);
        plain.setRows(5);
        plain.setColumns(15);
        plain.setLineWrap(true);
        plain.setWrapStyleWord(true);
        phex.setBounds(220,10,200,80);
        phex.setRows(5);
        phex.setColumns(15);
        phex.setLineWrap(true);
        phex.setWrapStyleWord(true);
        bh11.add(t1);

        //bh12.add(jsp);
        bh12.add(plain);

        //bh12.add(jpp);
        bh12.add(phex);
        bh13.add(t2);
        bh13.add(t3);


        Box bh2=Box.createHorizontalBox();
        JTextArea txtout=new JTextArea("输出的内容（文本）",5,8);
        txtout.setColumns(8);
        txtout.setRows(5);
        txtout.setEditable(false);
        txtout.setWrapStyleWord(true);
        txtout.setLineWrap(true);
        JTextArea hexout=new JTextArea("输出的内容（hex）",5,16);
        hexout.setColumns(16);
        hexout.setRows(5);
        hexout.setEditable(false);
        hexout.setWrapStyleWord(true);
        hexout.setLineWrap(true);

        bh2.add(txtout);
        bh2.add(hexout);

        Box bh3=Box.createHorizontalBox();
        JButton ffile=new JButton("From file");
        JButton encode=new JButton("Encode");
        JButton decode=new JButton("Decode");
        JButton decsave=new JButton("Decode and save");
        bh3.add(ffile);
        bh3.add(encode);
        bh3.add(decode);
        bh3.add(decsave);

        ffile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                JFileChooser jfc=new JFileChooser();
                int returnVal = jfc.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION)
                {
                    //获得打开的文件

                    File cfile = jfc.getSelectedFile();
                    String c="";

                    try {

                            BufferedInputStream inf = new BufferedInputStream(new FileInputStream(cfile.toString()));
                        int tempchar;
                        while ((tempchar = inf.read()) != -1) {
                            // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                            // 但如果这两个字符分开显示时，会换两次行。
                            // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                            if (((char) tempchar) != '\r') {

                                c=c+((char)tempchar);
                            }
                        }
                        plain.setText(c);

                        inf.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }




                }
            }





        });

        encode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Basebox bsx=new Basebox(plain.getText());
                phex.setText(TransStringTool.str2HexStr(plain.getText()));
                txtout.setText(bsx.encoding());
                hexout.setText(TransStringTool.str2HexStr(txtout.getText()));
            }
        });

        decode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String m=plain.getText();
                if(m.length()%4!=0){
                    JOptionPane.showMessageDialog(null, "the length is"+m.length()+", is not mod 4", "length error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    Basebox bsx = new Basebox(plain.getText());
                    phex.setText(TransStringTool.str2HexStr(plain.getText()));
                    txtout.setText(bsx.decoding());
                    hexout.setText(TransStringTool.str2HexStr(txtout.getText()));
                }
            }
        });

        decsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String m = plain.getText();
                if (m.length() % 4 != 0) {
                    JOptionPane.showMessageDialog(null, "the length is" + m.length() + ", is not mod 4", "length error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Basebox bsx = new Basebox(plain.getText());
                    phex.setText(TransStringTool.str2HexStr(plain.getText()));
                    txtout.setText(bsx.decoding());
                    hexout.setText(TransStringTool.str2HexStr(txtout.getText()));
                    byte[] bsave =Base64.getDecoder().decode(plain.getText());
                    JFileChooser jf = new JFileChooser("d:/");
                    int value = jf.showSaveDialog(null);
                    if (value == JFileChooser.APPROVE_OPTION) { //判断窗口是否点的是打开或保存
                        File getPath = jf.getSelectedFile(); //取得路径
                        try {
                            OutputStream of = new FileOutputStream(getPath);
                            of.write(bsave);
                        } catch (Exception e1) {
                        }

                    }

                }
            }
        });

        all.add(bh3);
        all.add(bh11,Box.LEFT_ALIGNMENT);
        all.add(bh12);
        all.add(bh13,Box.LEFT_ALIGNMENT);
        all.add(bh2);

        jp.add(all);
        Frame.add(jp);
        Frame.setVisible(true);
    }



}
