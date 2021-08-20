package projeto_jodo_da_forca;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AplicacaoGrafica {
    private JFrame frame;
    private JTextField textField;
    private JButton button_1;
    private JButton button;
    private JLabel label;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel label_4;
    private JLabel label_5;
    private JogoDaForca jogo;
    private JLabel label_6;
    private JLabel label_7;
    private JLabel label_8;

    /**
     * Launch the application.
     */

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AplicacaoGrafica window = new AplicacaoGrafica();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public AplicacaoGrafica() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
   private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
        frame.setTitle("Jogo da Forca");
        frame.setBounds(100, 100, 411, 221);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        label_4 = new JLabel("Letra:");
        label_4.setBounds(8, 72, 48, 14);
        frame.getContentPane().add(label_4);

        textField = new JTextField();
        textField.setBounds(44, 69, 35, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        label_5 = new JLabel("Palavra=");
        label_5.setForeground(Color.BLACK);
        label_5.setFont(new Font("Courier New", Font.BOLD, 15));
        label_5.setBounds(8, 100, 259, 25);
        frame.getContentPane().add(label_5);


       label_8 = new JLabel("Erros já digitados = ");
       label_8.setForeground(Color.BLACK);
       label_8.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
       label_8.setBounds(8, 118, 267, 25);
       frame.getContentPane().add(label_8);


        button = new JButton("Iniciar");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    jogo = new JogoDaForca("palavras.txt");
                    jogo.iniciar();
                    label_1.setText("Acertos: 0");
                    label_2.setText("Erros: 0");
                    label_3.setText("Dica:" + jogo.getDica());
                    label_5.setText("Palavra=" + jogo.getPalavra());
                    exibirImagem("0.png");
                    label.setText("Jogo iniciado");

                }
                catch(Exception e1) {
                    label.setText(e1.getMessage());
                    System.out.println(e1.getMessage());
                }
            }
        });
        button.setBounds(8, 15, 80, 23);
        frame.getContentPane().add(button);

        button_1 = new JButton("Adivinhar");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(textField.getText().isEmpty() ) {
                        label.setText("Campo vazio");
                        return;
                    }

                    String letra = textField.getText().trim().toUpperCase();
                    if(jogo.adivinhou(letra)) {
                        label.setText("acertou a letra " + letra);
                        label_1.setText("Acertos: "+jogo.getAcertos());
                        label_5.setText("Palavra=" + jogo.getPalavra());
                    }
                    else {
                        label.setText("penalidade: removeu "+jogo.getPenalidade());
                        label_2.setText("Erros: "+jogo.getErros());
                        label_8.setText("Erros já digitados: "+jogo.getLetrasErradas());
                        exibirImagem(jogo.getErros() + ".png");
                    }

                    if (jogo.terminou()) {
                        Thread.sleep(2000);	//pausa 2seg
                        label.setText(jogo.getResultado());
                    }
                }
                catch(Exception e1) {
                    label.setText(e1.getMessage());
                    System.out.println(e1.getMessage());
                }

            }
        });
        button_1.setBounds(89, 68, 104, 23);
        frame.getContentPane().add(button_1);

        label = new JLabel("");
        label.setBounds(8, 158, 377, 14);
        frame.getContentPane().add(label);

        label_1 = new JLabel("Acertos: 0");
        label_1.setForeground(new Color(0, 128, 0));
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_1.setBounds(144, 17, 68, 14);
        frame.getContentPane().add(label_1);

        label_2 = new JLabel("Erros: 0");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_2.setForeground(new Color(255, 0, 0));
        label_2.setBounds(254, 17, 68, 14);
        frame.getContentPane().add(label_2);

        label_3 = new JLabel("Dica:");
        label_3.setBounds(8, 49, 377, 14);
        frame.getContentPane().add(label_3);

        label_6 = new JLabel("");
        label_6.setBounds(291, 68, 80, 88);
        frame.getContentPane().add(label_6);

        label_7 = new JLabel("Imagem:");
        label_7.setBounds(218, 72, 61, 14);
        frame.getContentPane().add(label_7);
        exibirImagem("6.png");
    }

    public void exibirImagem(String arquivo) {
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/imagens/"+ arquivo));
        icon.setImage(icon.getImage().getScaledInstance(label_6.getWidth(), label_6.getHeight(), 1) );
        label_6.setIcon(icon);
    }
}

