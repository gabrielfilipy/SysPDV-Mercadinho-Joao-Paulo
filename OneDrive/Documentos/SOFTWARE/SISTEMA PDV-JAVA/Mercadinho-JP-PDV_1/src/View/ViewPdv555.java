/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControllerAbrirTuur;
import Controller.ControllerId;
import Controller.ControllerProduto;
import Controller.ControllerVendaProduto;
import Controller.ControllerVendaProduto2;
import Controller.ControllerVendaProdutoAbandono;
import Controller.ControllerVendaProdutoCartao;
import Controller.ControllerVendaProdutoCartao2;
import Controller.ControllerVendaProdutoTickets;
import Controller.ControllerVendaProdutoTickets2;
import Controller.Controllervenda;
import Controller.Controllervenda2;
import Controller.ControllervendaAbandono;
import Controller.ControllervendaCartao;
import Controller.ControllervendaCartao2;
import Controller.ControllervendaTickets;
import Controller.ControllervendaTickets2;
import Dao.DaoVenda2;
import Model.ModelAbrirTurno;
import Model.ModelId;
import Model.ModelProduto;
import Model.ModelVenda;
import Model.ModelVenda2;
import Model.ModelVendaAbandono;
import Model.ModelVendaCartao;
import Model.ModelVendaCartao2;
import Model.ModelVendaProduto;
import Model.ModelVendaProduto2;
import Model.ModelVendaProdutoAbandono;
import Model.ModelVendaProdutoCartao;
import Model.ModelVendaProdutoCartao2;
import Model.ModelVendaProdutoTickets;
import Model.ModelVendaProdutoTickets2;
import Model.ModelVendaTickets;
import Model.ModelVendaTickets2;
import Util.Formatador;
import java.awt.Color;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Monet
 */
public class ViewPdv555 extends javax.swing.JFrame {
    
    //salvar no banco de dados
    ModelVenda2 modelVenda2 = new ModelVenda2();
    Controllervenda2 controllervenda2 = new Controllervenda2();
    ArrayList<ModelVenda2> listaModelVenda2 = new ArrayList<>();
    ModelVendaProduto2 modelVendaProduto2 = new ModelVendaProduto2();
    ControllerVendaProduto2 controllerVendaProduto2 = new ControllerVendaProduto2();
    ArrayList<ModelVendaProduto2> listaModelVendaProduto2 = new ArrayList<>(); 
    //salvar no banco de daos
    
    Formatador formatador = new Formatador(); 
    String pagamento ="";
    ModelProduto modelProduto = new ModelProduto();
    ControllerProduto controllerProduto = new ControllerProduto();
    ArrayList<ModelProduto> listaModelProduto = new ArrayList<>();
    int quantidade =1;
    //VENDA DINHEIRO
    ModelVenda modelVenda = new ModelVenda();
    Controllervenda controller = new Controllervenda();
    ArrayList<ModelVenda> listaModelVenda = new ArrayList<>();
    //VENDA DINHEIRO
    
    //VENDA PRODUTO DINHEIRO
    ModelVendaProduto modelVendaProduto = new ModelVendaProduto();
    ControllerVendaProduto controllerVendaProduto = new ControllerVendaProduto();
    ArrayList<ModelVendaProduto> listaModelVendaProduto = new ArrayList<>();
    //VENDA PRODUTO DINHEIRO
    
     //VENDA CARTAO
    ModelVendaCartao modelVendaCartao = new ModelVendaCartao();
    ControllervendaCartao controllervendaCartao = new ControllervendaCartao();
    ArrayList<ModelVendaCartao> listaModelVendaCartao = new ArrayList<>();
    //VENDA CARTAO
    
    //VENDA PRODUTO CARTAO
    ModelVendaProdutoCartao modelVendaProdutoCartao = new ModelVendaProdutoCartao();
    ControllerVendaProdutoCartao controllerVendaProdutoCartao = new ControllerVendaProdutoCartao();
    ArrayList<ModelVendaProdutoCartao> listaModelVendaProdutoCartao = new ArrayList<>();
    //VENDA PRODUTO CARTAO
    
    //VENDA TICKETS
    ModelVendaTickets modelVendaTickets = new ModelVendaTickets();
    ControllervendaTickets controllervendaTickets = new ControllervendaTickets();
    ArrayList<ModelVendaTickets> listaModelVendaTickets = new ArrayList<>();
    //VENDA TICKETS
      
    //venda produto tickets 
    ModelVendaProdutoTickets modelVendaProdutoTickets = new ModelVendaProdutoTickets();
    ControllerVendaProdutoTickets controllerVendaProdutoTickets = new     ControllerVendaProdutoTickets();
    ArrayList<ModelVendaProdutoTickets>listaModelVendaProdutoTickets= new ArrayList<>();
    //venda produto tickets
    private ViewPagamentoPDV viewPagamentoPDV; 
    private ViewPagamentoPDVParcela viewPagamentopParcelaPDV;
    private ViewPagamentoPDVCartao viewPagamentoPDVCartao;
    private ViewPagamentoPDVCartaoNormal viewPagamentoPDVCartaoNormal;
    private ViewPagamentoPDVTickets viewPagamentoPDVTickets;
    private ViewListaClientes viewListaClientes;   
    DecimalFormat df = new DecimalFormat("#.00");
    int percorrertabela,codigoProduto, codCliente; 
    /**   
     * Creates new form ViewPdv555
     */
    public ViewPdv555() {  
        initComponents();
        setIcon();  
        tfBarras.requestFocus();
        DaoVenda2.numeros();    
        limparFormulario();
        tfValorBruto.setText("0,00");
        tabela.getTableHeader().setDefaultRenderer(new View.EstiloTabelaHeader());
        tabela.setDefaultRenderer(Object.class, new View.EstiloTabelaRenderer()); 
        this.viewPagamentoPDV = new ViewPagamentoPDV(this, true);
        this.viewPagamentopParcelaPDV = new ViewPagamentoPDVParcela(this, true);
        this.viewPagamentoPDVCartao = new ViewPagamentoPDVCartao(this, true);
        this.viewPagamentoPDVCartaoNormal = new ViewPagamentoPDVCartaoNormal(this, true);
        this.viewPagamentoPDVTickets = new ViewPagamentoPDVTickets(this, true);
        this.viewListaClientes = new ViewListaClientes();
        jScrollPane1.getViewport().setBackground(Color.WHITE);
    }
       
    private void limparFormulario(){  
    //tfNomeProduto.setText("SUB-TOTAL");
    tfIdCliente.setText("id Cliente");
    tfNomeCliente.setText("");
    tfNomeProduto.setText("- CAIXA LIVRE");
    tfCpfCliente.setText("CPF Cliente");
    //tfValorBruto.setText("0,00");
    DefaultTableModel modelo=(DefaultTableModel) tabela.getModel();
    modelo.setNumRows(0);
    tfNomeProduto.setForeground(Color.BLACK);
    } 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tfNomeProduto = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tfValorBruto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        tfBarras = new javax.swing.JTextField();
        tfData = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfHora = new javax.swing.JLabel();
        tfVenda = new javax.swing.JLabel();
        tfCpfCliente = new javax.swing.JLabel();
        tfAno = new javax.swing.JLabel();
        tfMes = new javax.swing.JLabel();
        tfTipo = new javax.swing.JLabel();
        tfUsuarioID = new javax.swing.JLabel();
        tfusuarionome = new javax.swing.JLabel();
        tfNomeCliente = new javax.swing.JLabel();
        tfIdCliente = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfTurno = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(6);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 3));

        tfNomeProduto.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        tfNomeProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tfNomeProduto.setText("1x Mocoquinha = 2,50");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfNomeProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfNomeProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));

        tfValorBruto.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        tfValorBruto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tfValorBruto.setText("999,23");
        tfValorBruto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(340, Short.MAX_VALUE)
                .addComponent(tfValorBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfValorBruto, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setDoubleBuffered(true);
        jScrollPane1.setFocusable(false);
        jScrollPane1.setInheritsPopupMenu(true);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Item", "Id", "Produto", "Quantidade", "Valor Unit.", "Valor Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.setSelectionForeground(new java.awt.Color(0, 255, 204));
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabelaFocusLost(evt);
            }
        });
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/telapdv2.png"))); // NOI18N

        tfBarras.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tfBarras.setBorder(null);
        tfBarras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfBarrasFocusLost(evt);
            }
        });
        tfBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfBarrasActionPerformed(evt);
            }
        });
        tfBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBarrasKeyReleased(evt);
            }
        });

        tfData.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tfData.setText("NFCe-n");

        jLabel5.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel5.setText("NFCe-n atualizado");

        tfHora.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tfHora.setText("NFCe-n");

        tfVenda.setForeground(new java.awt.Color(255, 255, 255));
        tfVenda.setText("NºVenda");

        tfCpfCliente.setForeground(new java.awt.Color(255, 255, 255));
        tfCpfCliente.setText("CPF Cliente");

        tfAno.setForeground(new java.awt.Color(255, 255, 255));
        tfAno.setText("Ano");

        tfMes.setForeground(new java.awt.Color(255, 255, 255));
        tfMes.setText("Mes");

        tfTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/U.png"))); // NOI18N

        tfUsuarioID.setForeground(new java.awt.Color(255, 255, 255));
        tfUsuarioID.setText("Id Usuario");

        tfusuarionome.setText("Nome Usuario");

        tfNomeCliente.setForeground(new java.awt.Color(255, 255, 255));
        tfNomeCliente.setText("Nome Cliente");

        tfIdCliente.setForeground(new java.awt.Color(255, 255, 255));
        tfIdCliente.setText("id Cliente");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/cod_barras.png"))); // NOI18N

        tfTurno.setForeground(new java.awt.Color(255, 255, 255));
        tfTurno.setText("Turno:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tfBarras)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfVenda)
                                            .addComponent(tfCpfCliente))
                                        .addGap(37, 37, 37)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(tfAno)
                                                .addGap(57, 57, 57)
                                                .addComponent(tfMes))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(tfNomeCliente)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tfIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(tfTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(tfTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfusuarionome, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfUsuarioID, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfData, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfHora, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfData)
                            .addComponent(jLabel5)
                            .addComponent(tfHora)
                            .addComponent(tfUsuarioID)
                            .addComponent(tfusuarionome)))
                    .addComponent(tfTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfVenda)
                            .addComponent(tfAno)
                            .addComponent(tfMes))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfCpfCliente)
                            .addComponent(tfNomeCliente)
                            .addComponent(tfIdCliente))
                        .addGap(70, 70, 70)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(163, 163, 163))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTurno))
                .addContainerGap())
        );

        jMenu3.setText("Funções");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem2.setText("QUANTIDADE");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem3.setText("ABANDONAR VENDA");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem4.setText("EXCLUIR UM PRODUTO DA LISTA");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItem5.setText("FINALIZAR VENDA (DINHEIRO)");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem10.setText("À PRAZO");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        jMenuItem6.setText("FINALIZAR VENDA QUANDO DIVIDIR CARTÃO COM DINHEIRO");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        jMenuItem13.setText("FINALIZAR VENDA (CARTÃO DE CRÉDITO)");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem13);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        jMenuItem7.setText("FINALIZAR VENDA (TICKETS)");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        jMenuItem8.setText("LISTA PRODUTOS");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        jMenuItem9.setText("LISTA CLIENTES");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        jMenuItem11.setText("SANGRIA");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        jMenuItem12.setText("INSERIR VALOR PRODUTOS");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Sair");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(0, 0, 1008, 714);
    }// </editor-fold>//GEN-END:initComponents
class horas implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Date sistemaHora = new Date();
            String pmAm = "HH:mm:ss";
            SimpleDateFormat formato = new SimpleDateFormat(pmAm);
            Calendar now = Calendar.getInstance();
            tfHora.setText(String.format(formato.format(sistemaHora), now));
        }  
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
     Date sistemaData = new Date(); 
      
        SimpleDateFormat formato = new SimpleDateFormat("dd/MMMM/yyyy");
        SimpleDateFormat mes = new SimpleDateFormat("dd/yyyy");
        SimpleDateFormat ano = new SimpleDateFormat("yyyy");     
        tfData.setText(formato.format(sistemaData)); 
        tfMes.setText(mes.format(sistemaData));
        tfAno.setText(ano.format(sistemaData));
        //HORA DO SISTEMA 
        Timer hr = new Timer(100, new horas());
        hr.start(); 
    }//GEN-LAST:event_formWindowOpened

    private void tfBarrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBarrasKeyReleased
    if(tfBarras.equals(""))
    { 
    //Quando não tiver nada nessa JTextField; não vai aparecer nenhuma mensagem de alerta    
    }else
    { 
    //função de trazer pelo código de barras
    trazerProdutoEstoque(evt);
    }
        
    }//GEN-LAST:event_tfBarrasKeyReleased
    private void trazerProdutoEstoque(java.awt.event.KeyEvent e){             
    try {      
    tfNomeProduto.setForeground(Color.BLACK);
    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();        
    if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){  
        
    DaoVenda2.numeros();
    modelProduto = new ModelProduto();            
    modelProduto = controllerProduto.retornarCodigoBarrasController(tfBarras.getText());
    modelo.addRow(new Object[]{     
    modelo.getRowCount()+1, 
    modelProduto.getId(),   
    modelProduto.getNome(), 
    quantidade,
    formatador.converterPontos(df.format(modelProduto.getPreco_venda() )),
    formatador.converterPontos(df.format(modelProduto.getPreco_venda() * quantidade))
    });                 
    tfBarras.setText("");  
    tfValorBruto.setText(formatador.converterPontos(df.format(somarValor()))+"");
    tfNomeProduto.setText(" "+quantidade+"x  "+modelProduto.getNome()+" = "+formatador.converterPontos(df.format(modelProduto.getPreco_venda() * quantidade))+"");      
    quantidade = 1;
    }        
    } catch (Exception e3) {
    try {
    if(modelProduto.getId() != Integer.parseInt(tfBarras.getText())){
    JOptionPane.showMessageDialog(null, "Produto não cadastrado na base de dados!");    
    }else   
    {      
    }    
    } catch (Exception e5) {
    }      
    }       
    }
    private void tfBarrasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfBarrasFocusLost
   
        //tfBarras.requestFocus();
    }//GEN-LAST:event_tfBarrasFocusLost

    private void tfBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfBarrasActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
    
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        produtosKL();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        ViewSangria sangria = new ViewSangria();
        sangria.setVisible(true);
        sangria.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed

        viewListaClientes.setVisible(true);
        viewListaClientes.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        ViewListaProduto pro = new ViewListaProduto();
        pro.setVisible(true);
        pro.setLocationRelativeTo(null);// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        int cont = tabela.getRowCount();
        if(cont<1){
            JOptionPane.showMessageDialog(null, "Você precisa ter um produto na tabela de vendas!");
        }else{
            ModelAbrirTurno modelAbrirTurno = new ModelAbrirTurno();
            ControllerAbrirTuur controllerAbrirTuur = new ControllerAbrirTuur();
            ArrayList<ModelAbrirTurno> listaturno=new ArrayList<>();
            listaturno = controllerAbrirTuur.listaModelUsuarioController();
            for (int i = 0; i < listaturno.size(); i++) {
                int idUser = listaturno.get(i).getId();
                if(idUser != Integer.parseInt(tfUsuarioID.getText())){
                    JOptionPane.showMessageDialog(null, "Você não é o Usuário que iniciou este turno!");
                }else{
                    tfNomeProduto.setText("Finalizando a venda. Aguarde...");
                    viewPagamentoPDVTickets = new ViewPagamentoPDVTickets(this, true);
                    try {
                        viewPagamentoPDVTickets.setValorTotal(Float.parseFloat(tfValorBruto.getText()));
                        viewPagamentoPDVTickets.setValorRecebido(Float.parseFloat(tfValorBruto.getText()));
                        viewPagamentoPDVTickets.textFieldeValorTotal();
                        viewPagamentoPDVTickets.setLocationRelativeTo(null);
                        viewPagamentoPDVTickets.setVisible(true);
                        if(viewPagamentoPDVTickets.isPago()){
                            //salvarVendaTicketsNoServidor();
                            salvarVendaTickets();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        int cont = tabela.getRowCount();
        if(cont<1){
            JOptionPane.showMessageDialog(null, "Você precisa ter um produto na tabela de vendas!");
        }else{
            ModelAbrirTurno modelAbrirTurno = new ModelAbrirTurno();
            ControllerAbrirTuur controllerAbrirTuur = new ControllerAbrirTuur();
            ArrayList<ModelAbrirTurno> listaturno=new ArrayList<>();
            listaturno = controllerAbrirTuur.listaModelUsuarioController();
            for (int i = 0; i < listaturno.size(); i++) {
                int idUser = listaturno.get(i).getId();
                if(idUser != Integer.parseInt(tfUsuarioID.getText())){
                    JOptionPane.showMessageDialog(null, "Você não é o Usuário que iniciou este turno!");
                }else{
                    tfNomeProduto.setText("Finalizando a venda. Aguarde..");
                    viewPagamentoPDVCartaoNormal = new ViewPagamentoPDVCartaoNormal(this, true);
                    try {
                        viewPagamentoPDVCartaoNormal.setValorTotal(Float.parseFloat(tfValorBruto.getText()));
                        viewPagamentoPDVCartaoNormal.textFieldeValorTotal();
                        viewPagamentoPDVCartaoNormal.setLocationRelativeTo(null);
                        viewPagamentoPDVCartaoNormal.setVisible(true);
                        if(viewPagamentoPDVCartaoNormal.isPago()){
                            //salvarVendaCartaoNoServidor();
                            salvarVendaCartao();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        int cont = tabela.getRowCount();
        if(cont<1){
            JOptionPane.showMessageDialog(null, "Você precisa ter um produto na tabela de vendas!");
        }else{
            ModelAbrirTurno modelAbrirTurno = new ModelAbrirTurno();
            ControllerAbrirTuur controllerAbrirTuur = new ControllerAbrirTuur();
            ArrayList<ModelAbrirTurno> listaturno=new ArrayList<>();
            listaturno = controllerAbrirTuur.listaModelUsuarioController();
            for (int i = 0; i < listaturno.size(); i++) {
                int idUser = listaturno.get(i).getId();
                if(idUser != Integer.parseInt(tfUsuarioID.getText())){
                    JOptionPane.showMessageDialog(null, "Você não é o Usuário que iniciou este turno!");
                }else{
                    tfNomeProduto.setText("Finalizando a venda. Aguarde...");
                    viewPagamentoPDVCartao = new ViewPagamentoPDVCartao(this, true);
                    try {
                        viewPagamentoPDVCartao.setValorTotal(Float.parseFloat(tfValorBruto.getText()));
                        viewPagamentoPDVCartao.textFieldeValorTotal();
                        viewPagamentoPDVCartao.setLocationRelativeTo(null);
                        viewPagamentoPDVCartao.setVisible(true);
                        if(viewPagamentoPDVCartao.isPago()){
                            // salvarVendaCartaoNoServidor();
                            // salvarVendaDinheiroNoServidor();
                            salvarVendaDinheiroComCartao();
                            salvarVendaCartaoComDinheiro();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        ModelAbrirTurno modelAbrirTurno = new ModelAbrirTurno();
        ControllerAbrirTuur controllerAbrirTuur = new ControllerAbrirTuur();
        ArrayList<ModelAbrirTurno> listaturno=new ArrayList<>();
        if(tfIdCliente.getText().equals("id Cliente")){
            JOptionPane.showMessageDialog(null, "Informe o Cliente!");
        }else{
            listaturno = controllerAbrirTuur.listaModelUsuarioController();
            for (int i = 0; i < listaturno.size(); i++) {
                int idUser = listaturno.get(i).getId();
                if(idUser != Integer.parseInt(tfUsuarioID.getText())){
                    JOptionPane.showMessageDialog(null, "Você não é o Usuário que iniciou este turno!");
                }else{
                    tfNomeProduto.setText("Finalizando a venda. Aguarde...");
                    viewPagamentopParcelaPDV = new ViewPagamentoPDVParcela(this, true);
                    try {
                        viewPagamentopParcelaPDV.setValorTotal(Float.parseFloat(tfValorBruto.getText()));
                        viewPagamentopParcelaPDV.textFieldeValorTotal();
                        viewPagamentopParcelaPDV.setLocationRelativeTo(null);
                        viewPagamentopParcelaPDV.setVisible(true);
                        if(viewPagamentopParcelaPDV.isPago()){
                            //salvarVendaDinheiroParcelaNoServidor();
                            salvarVendaDinheiroParcela();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        int cont = tabela.getRowCount();
        if(cont<1){
            JOptionPane.showMessageDialog(null, "Você precisa ter um produto na tabela de vendas!");
        }else{
            ModelAbrirTurno modelAbrirTurno = new ModelAbrirTurno();
            ControllerAbrirTuur controllerAbrirTuur = new ControllerAbrirTuur();
            ArrayList<ModelAbrirTurno> listaturno=new ArrayList<>();
            listaturno = controllerAbrirTuur.listaModelUsuarioController();
            for (int i = 0; i < listaturno.size(); i++) {
                int idUser = listaturno.get(i).getId();
                if(idUser != Integer.parseInt(tfUsuarioID.getText())){
                    JOptionPane.showMessageDialog(null, "Você não é o Usuário que iniciou este turno!");
                }else{
                    tfNomeProduto.setText("Finalizando a venda. Aguarde...");
                    viewPagamentoPDV = new ViewPagamentoPDV(this, true);
                    try {
                        viewPagamentoPDV.setValorTotal(Float.parseFloat(tfValorBruto.getText()));
                        viewPagamentoPDV.textFieldeValorTotal();
                        viewPagamentoPDV.setLocationRelativeTo(null);
                        viewPagamentoPDV.setVisible(true);
                        if(viewPagamentoPDV.isPago()){
                            // salvarVendaDinheiroBanco();
                            //salvarVendaDinheiroNoServidor();
                            salvarVendaDinheiro();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
    try {
    int cont = tabela.getRowCount();
    if(cont<1){
    JOptionPane.showMessageDialog(null, "Não tem produto para excluir!");
    }else{
    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
    int linha = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do produto para remover?"));
    DaoVenda2.numeros();
    modelo.removeRow(linha-1);
    tfValorBruto.setText(somarValor()+"");
    for (int i = 0; i < cont; i++) {
    modelo.setValueAt(i+1, i, 0);
    }
    }
    } catch (Exception e) {
    }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        ModelVendaAbandono modelVendaAbandono = new ModelVendaAbandono();
        ControllervendaAbandono controllervendaAbandono = new ControllervendaAbandono();

        ModelVendaProdutoAbandono modelVendaProdutoAbandono = new ModelVendaProdutoAbandono();
        ControllerVendaProdutoAbandono  controllerVendaProdutoAbandono = new ControllerVendaProdutoAbandono();
        ArrayList<ModelVendaProdutoAbandono> listaModelVendaProdutoAbandono = new ArrayList<>();
        modelVendaAbandono = new ModelVendaAbandono();
        int percorrertabela,codigoProduto, codigoVenda, codCliente;
        if(tfUsuarioID.getText().equals("")){
            tfUsuarioID.setText(String.valueOf(3));
        }else{
            modelVendaAbandono.setCliente(Integer.parseInt(tfUsuarioID.getText()));
        }
        modelVendaAbandono.setCliente(Integer.parseInt(tfUsuarioID.getText()));
        modelVendaAbandono.setData(tfData.getText());
        modelVendaAbandono.setHora(tfHora.getText());
        modelVendaAbandono.setIdVenda(Integer.parseInt(tfVenda.getText()));
        modelVendaAbandono.setPagamento((String) viewPagamentoPDV.jcbPagamento.getSelectedItem());
        modelVendaAbandono.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
        modelVendaAbandono.setValorBruto(Double.parseDouble(tfValorBruto.getText()));
        modelVendaAbandono.setValorDesconto(Double.parseDouble(String.valueOf(viewPagamentoPDV.getDesconto())));
        modelVendaAbandono.setValorLiquido(Double.parseDouble(String.valueOf(viewPagamentoPDV.getValorTotal())));
        controllervendaAbandono.salvarvendaController(modelVendaAbandono);
        JOptionPane.showMessageDialog(null, "Abandono de vendas com exito!");

        percorrertabela = tabela.getRowCount();
        for (int i = 0; i < percorrertabela; i++) {
            codigoProduto=(int) tabela.getValueAt(i, 1);

            modelVendaProdutoAbandono = new ModelVendaProdutoAbandono();

            modelVendaProdutoAbandono.setProduto(codigoProduto);
            modelVendaProdutoAbandono.setVenda(Integer.parseInt(tfVenda.getText()));
            modelVendaProdutoAbandono.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
            modelVendaProdutoAbandono.setValorVenda(Double.parseDouble(String.valueOf(tabela.getValueAt(i, 4))));
            modelVendaProdutoAbandono.setQuantidade(Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));
            modelVendaProdutoAbandono.setNome_produto(String.valueOf(tabela.getValueAt(i, 2)));

            listaModelVendaProdutoAbandono.add(modelVendaProdutoAbandono);

        }
        controllerVendaProdutoAbandono.salvarVendaProdutoController(listaModelVendaProdutoAbandono);
        JOptionPane.showMessageDialog(null, "Produtos do Abandono de vendas salvo com exito!");
        geraId();
        DaoVenda2.numeros();
        limparFormulario();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            quantidade = Integer.parseInt(JOptionPane.showInputDialog("Insira a quantidade"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro! Só é possível inserir números!");
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
    int linha = tabela.getSelectedRow();
    String cod = String.valueOf(tabela.getValueAt(linha, 0));
    //System.out.println(cod);
    tfNomeProduto.setText(""+String.valueOf(tabela.getValueAt(linha, 3))+" "+"x"+" "+String.valueOf(tabela.getValueAt(linha, 2))+" "+"="+" "+String.valueOf(tabela.getValueAt(linha, 5))+" ");
    tfNomeProduto.setForeground(Color.red);
    
    int resposta =   JOptionPane.showConfirmDialog(this, "Deseja excluir o produto:"+String.valueOf(tabela.getValueAt(linha, 2))+"?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if(resposta==JOptionPane.YES_OPTION){
        
    try { 
    int cont = tabela.getRowCount();
    
    if(cont<1){
    JOptionPane.showMessageDialog(null, "Não tem produto para excluir!");
    }else{
    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
    modelo.removeRow(Integer.parseInt(cod)-1);
    tfValorBruto.setText(formatador.converterPontos(df.format(somarValor()))+"");
    for (int i = 0; i < cont; i++) {
    modelo.setValueAt(i+1, i, 0);
    } 
    }
    } catch (Exception e) {
    }
    
    }else if(resposta==JOptionPane.NO_OPTION){
    tfNomeProduto.setForeground(Color.BLACK);         
    }else if(resposta==JOptionPane.CLOSED_OPTION){
              
    }
    }//GEN-LAST:event_tabelaMouseClicked
 
    private void tabelaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabelaFocusLost
    tfBarras.requestFocus();
    }//GEN-LAST:event_tabelaFocusLost
         private float somarValor(){
         int cont = tabela.getRowCount(); 
         float valor=0, soma=0;
         
         for (int i = 0; i < cont; i++) {
         valor = Float.parseFloat(String.valueOf(tabela.getValueAt(i, 5)));
         soma += valor;    
         }
         return soma;
     }     private void formaPagamentoDinheiro(java.awt.event.KeyEvent e){
    if(e.getKeyCode() == java.awt.event.KeyEvent.VK_F1){  
    JOptionPane.showMessageDialog(null, "Dinheiro");
    }    
        
    }
    private void formaPagamentoCartao(java.awt.event.KeyEvent e){
    if(e.getKeyCode() == java.awt.event.KeyEvent.VK_F2){    
    JOptionPane.showMessageDialog(null, "Cartao");
    }
    }
    
    private void formaPagamentoTickets(java.awt.event.KeyEvent e){
    if(e.getKeyCode() == java.awt.event.KeyEvent.VK_F3){  
    JOptionPane.showMessageDialog(null, "forma pagamento tickets");
    }                
    }
    
    private void excluirUmProduto(java.awt.event.KeyEvent e){
    if(e.getKeyCode() == java.awt.event.KeyEvent.VK_F4){  
    JOptionPane.showMessageDialog(null, "excluirProduto");
    }                
    }
    
    private void abandono(java.awt.event.KeyEvent e){
    if(e.getKeyCode() == java.awt.event.KeyEvent.VK_F5){  
    JOptionPane.showMessageDialog(null, "abandono");
    }  
    }
    
    private void listarClientes(java.awt.event.KeyEvent e){
    if(e.getKeyCode() == java.awt.event.KeyEvent.VK_F6){  
    JOptionPane.showMessageDialog(null, "listarClientes");
    }  
    }
    
    private void produtos(java.awt.event.KeyEvent e){
    if(e.getKeyCode() == java.awt.event.KeyEvent.VK_F7){  
    JOptionPane.showMessageDialog(null, "produtos");
    }  
    }
    
    private void sangria(java.awt.event.KeyEvent e){
    if(e.getKeyCode() == java.awt.event.KeyEvent.VK_F8){  
    JOptionPane.showMessageDialog(null, "sangria");
    }  
    }
    
    private void quantidade(java.awt.event.KeyEvent e){
    if(e.getKeyCode() == java.awt.event.KeyEvent.VK_F9){  
    JOptionPane.showMessageDialog(null, "quantidade");
    }  
    }
    
    private void parcelamento(java.awt.event.KeyEvent e){
    if(e.getKeyCode() == java.awt.event.KeyEvent.VK_F10){  
    JOptionPane.showMessageDialog(null, "parcelamento");
    }  
    }
    
    private void inserirValorProduto(java.awt.event.KeyEvent e){
    if(e.getKeyCode() == java.awt.event.KeyEvent.VK_F11){  
    JOptionPane.showMessageDialog(null, "inserir valor produto");
    }  
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewPdv555.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPdv555.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPdv555.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPdv555.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPdv555().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    public static javax.swing.JLabel tfAno;
    private javax.swing.JTextField tfBarras;
    public static javax.swing.JLabel tfCpfCliente;
    private javax.swing.JLabel tfData;
    private javax.swing.JLabel tfHora;
    public static javax.swing.JLabel tfIdCliente;
    public static javax.swing.JLabel tfMes;
    public static javax.swing.JLabel tfNomeCliente;
    private javax.swing.JLabel tfNomeProduto;
    public static javax.swing.JLabel tfTipo;
    public static javax.swing.JLabel tfTurno;
    public static javax.swing.JLabel tfUsuarioID;
    public static javax.swing.JLabel tfValorBruto;
    public static javax.swing.JLabel tfVenda;
    public static javax.swing.JLabel tfusuarionome;
    // End of variables declaration//GEN-END:variables

private void setIcon() {
setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagem/comp.png"))); 
    }

 public void salvarVendaDinheiro(){
     pagamento = "A vista";
     try {      
     //modelProduto = new ModelProduto();
     modelVenda = new ModelVenda();
     modelVendaProduto = new ModelVendaProduto();
     listaModelVendaProduto = new ArrayList<>();
     
     if(tfIdCliente.getText().equals("id Cliente")){
     tfIdCliente.setText(String.valueOf(3));    
     } 
     modelVenda.setCliente(Integer.parseInt(tfIdCliente.getText()));    
      
 //  modelVenda.setCliente(Integer.parseInt(tfIdCliente.getText()));
     modelVenda.setData(tfData.getText());
     modelVenda.setIdVenda(Integer.parseInt(tfVenda.getText()));   
     modelVenda.setHora(tfHora.getText());   
     modelVenda.setData_relatorio(tfMes.getText());
     modelVenda.setAno(tfAno.getText());
     modelVenda.setPagamento((String) viewPagamentoPDV.jcbPagamento.getSelectedItem()); 
     modelVenda.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVenda.setValorBruto(Double.parseDouble(tfValorBruto.getText()));
     modelVenda.setValorDesconto(Double.parseDouble(String.valueOf(viewPagamentoPDV.getDesconto())));
     modelVenda.setValorLiquido(Double.parseDouble(String.valueOf(viewPagamentoPDV.getValorTotal())));
     controller.salvarvendaController(modelVenda);  
     percorrertabela = tabela.getRowCount();    
     for (int i = 0; i < percorrertabela; i++) { 
     codigoProduto=(int) tabela.getValueAt(i, 1);          
     modelVendaProduto = new ModelVendaProduto(); 
     modelProduto = new ModelProduto();
     modelVendaProduto.setProduto(codigoProduto);   
     modelVendaProduto.setMesAno(tfMes.getText());
     modelVendaProduto.setAno(tfAno.getText());    
     modelVendaProduto.setVenda(Integer.parseInt(tfVenda.getText()));
     modelVendaProduto.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVendaProduto.setValorVenda(Double.parseDouble(String.valueOf(tabela.getValueAt(i, 4))));
     modelVendaProduto.setQuantidade(Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));
     modelVendaProduto.setNome_produto(String.valueOf(tabela.getValueAt(i, 2)));
     //baixa no estoque 
     modelProduto.setId(codigoProduto); 
     modelProduto.setEstoque(controllerProduto.retornarController(codigoProduto).getEstoque()
     -Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));      
     listaModelVendaProduto.add(modelVendaProduto);
     listaModelProduto.add(modelProduto);     
     }          
     if(controllerVendaProduto.salvarVendaProdutoController(listaModelVendaProduto)){
     //alterando estoque estiva (produto)  
     controllerProduto.alterarEstoqueController(listaModelProduto);   
     geraId();
     DaoVenda2.numeros();
     JOptionPane.showMessageDialog(null, "Venda à vista finalizada!");
     int resposta =   JOptionPane.showConfirmDialog(this, "Deseja imprimir cupom?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
     if(resposta==JOptionPane.YES_OPTION){
     imprimirCupom(listaModelVendaProduto, modelVenda); 
     int codCli = Integer.parseInt(tfUsuarioID.getText());      
     limparFormulario(); 
     }else if(resposta==JOptionPane.NO_OPTION){
     int codCli = Integer.parseInt(tfUsuarioID.getText());      
     limparFormulario();       
     }else if(resposta==JOptionPane.CLOSED_OPTION){
     int codCli = Integer.parseInt(tfUsuarioID.getText());      
     limparFormulario();    
     }
     }else{
     JOptionPane.showMessageDialog(null, "erro");
     }    
     } catch (Exception e) {
     }
     }
 
 
     public void salvarVendaDinheiroComCartao(){
     System.out.println("Passou");
         pagamento = "A vista";
     try {      
     //modelProduto = new ModelProduto();
     modelVenda = new ModelVenda();
     modelVendaProduto = new ModelVendaProduto();
     listaModelVendaProduto = new ArrayList<>();
     
     if(tfIdCliente.getText().equals("id Cliente")){
     tfIdCliente.setText(String.valueOf(3));    
     } 
     modelVenda.setCliente(Integer.parseInt(tfIdCliente.getText()));    
      
 //  modelVenda.setCliente(Integer.parseInt(tfIdCliente.getText()));
     modelVenda.setData(tfData.getText());
     modelVenda.setIdVenda(Integer.parseInt(tfVenda.getText()));   
     modelVenda.setHora(tfHora.getText());   
     modelVenda.setData_relatorio(tfMes.getText());
     modelVenda.setAno(tfAno.getText());
     modelVenda.setPagamento((String) viewPagamentoPDVCartao.jcbPagamento.getSelectedItem()); 
     modelVenda.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVenda.setValorBruto(Double.parseDouble(tfValorBruto.getText()));
     modelVenda.setValorDesconto(Double.parseDouble(String.valueOf(viewPagamentoPDVCartao.getDesconto())));
     modelVenda.setValorLiquido(Double.parseDouble(String.valueOf(viewPagamentoPDVCartao.getValorTotalDinheiro())));
     controller.salvarvendaController(modelVenda);
      
     
     percorrertabela = tabela.getRowCount();
     
     for (int i = 0; i < percorrertabela; i++) { 
     codigoProduto=(int) tabela.getValueAt(i, 1);
            
     modelVendaProduto = new ModelVendaProduto(); 
     modelProduto = new ModelProduto();
     modelVendaProduto.setProduto(codigoProduto);
      
     
     modelVendaProduto.setMesAno(tfMes.getText());
     modelVendaProduto.setAno(tfAno.getText()); 
     
     modelVendaProduto.setVenda(Integer.parseInt(tfVenda.getText()));
     modelVendaProduto.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVendaProduto.setValorVenda(Double.parseDouble(String.valueOf(tabela.getValueAt(i, 4))));
     modelVendaProduto.setQuantidade(Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));
     modelVendaProduto.setNome_produto(String.valueOf(tabela.getValueAt(i, 2)));
     //baixa no estoque 
     modelProduto.setId(codigoProduto); 
     modelProduto.setEstoque(controllerProduto.retornarController(codigoProduto).getEstoque()
     -Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));
       
     listaModelVendaProduto.add(modelVendaProduto);
     listaModelProduto.add(modelProduto);     
     }          
     if(controllerVendaProduto.salvarVendaProdutoController(listaModelVendaProduto)){
     //alterando estoque estiva (produto)  
     controllerProduto.alterarEstoqueController(listaModelProduto);
     JOptionPane.showMessageDialog(null, "Venda com o cartão de crédito finalizada!");
     geraId();
     DaoVenda2.numeros();
     } 
     } catch (Exception e) {
     }
     }
 
 
 
  private void salvarVendaTickets(){
     pagamento = "Ticket"; 
     try {
     modelVendaTickets = new ModelVendaTickets();
     modelVendaProdutoTickets = new ModelVendaProdutoTickets();
     listaModelVendaProdutoTickets = new ArrayList<>();
     int percorrertabela, codigoVenda;
     if(tfIdCliente.getText().equals("id Cliente")){
     tfIdCliente.setText(String.valueOf(3));   
     }
     modelVendaTickets.setCliente(Integer.parseInt(tfIdCliente.getText()));    
    // modelVendaTickets.setCliente(Integer.parseInt(tfUsuarioID.getText()));
     modelVendaTickets.setData(tfData.getText());
     modelVendaTickets.setIdVenda(Integer.parseInt(tfVenda.getText())); 
     modelVendaTickets.setHora(tfHora.getText());
     modelVendaTickets.setData_relatorio(tfMes.getText());
     modelVendaTickets.setDataAnual(tfAno.getText()); 
     modelVendaTickets.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVendaTickets.setValorBruto(Double.parseDouble(tfValorBruto.getText()));
     modelVendaTickets.setValorDesconto(Double.parseDouble(String.valueOf(viewPagamentoPDVTickets.getDesconto())));
     modelVendaTickets.setValorLiquido(Double.parseDouble(String.valueOf(viewPagamentoPDVTickets.getValorTotal())));
     codigoVenda = controllervendaTickets.salvarvendaController(modelVendaTickets);
     if(codigoVenda>0){
    
     }
     
     percorrertabela = tabela.getRowCount();
     for (int i = 0; i < percorrertabela; i++) {
     codigoProduto=(int) tabela.getValueAt(i, 1);
            
     modelVendaProdutoTickets = new ModelVendaProdutoTickets();
     modelProduto = new ModelProduto();
     modelVendaProdutoTickets.setProduto(codigoProduto);
     modelVendaProdutoTickets.setVenda(Integer.parseInt(tfVenda.getText())); 
     modelVendaProdutoTickets.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVendaProdutoTickets.setValorVenda(Double.parseDouble(String.valueOf(tabela.getValueAt(i, 4))));
     modelVendaProdutoTickets.setNome_produto(String.valueOf(tabela.getValueAt(i, 2)));
     modelVendaProdutoTickets.setQuantidade(Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));
     modelVendaProdutoTickets.setAno(tfAno.getText());
     modelVendaProdutoTickets.setMesAno(tfMes.getText());
     //baixa no estoque 
     modelProduto.setId(codigoProduto); 
     modelProduto.setEstoque(controllerProduto.retornarController(codigoProduto).getEstoque()
     -Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));        
     listaModelVendaProdutoTickets.add(modelVendaProdutoTickets);
     listaModelProduto.add(modelProduto);     
     }   
     if(controllerVendaProdutoTickets.salvarVendaProdutoController(listaModelVendaProdutoTickets)){
     //alterando estoque estiva (produto)
     controllerProduto.alterarEstoqueController(listaModelProduto);
     JOptionPane.showMessageDialog(null, "Venda no ticket finalizada!");
     geraId();
     DaoVenda2.numeros();
     int resposta =   JOptionPane.showConfirmDialog(this, "Deseja imprimir cupom?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
     if(resposta==JOptionPane.YES_OPTION){
     imprimirCupomTickets(listaModelVendaProdutoTickets, modelVendaTickets);
     int codCli = Integer.parseInt(tfUsuarioID.getText());      
     limparFormulario(); 
     }else if(resposta==JOptionPane.NO_OPTION){
     int codCli = Integer.parseInt(tfUsuarioID.getText());      
     limparFormulario();       
     }else if(resposta==JOptionPane.CLOSED_OPTION){
     int codCli = Integer.parseInt(tfUsuarioID.getText());      
     limparFormulario();    
     }
        }else{
            JOptionPane.showMessageDialog(null, "erro");
        }
      } catch (Exception e) {
        }
    }
  
  
    private void salvarVendaTicketsNoServidor(){
    ModelVendaTickets2 modelVendaTickets = new ModelVendaTickets2();
    ControllervendaTickets2 controllervendaTickets = new ControllervendaTickets2();
    ArrayList<ModelVendaTickets2> listaModelVendaTickets = new ArrayList<>();
    
    ModelVendaProdutoTickets2 modelVendaProdutoTickets = new ModelVendaProdutoTickets2();
    ControllerVendaProdutoTickets2 controllerVendaProdutoTickets = new ControllerVendaProdutoTickets2();
    ArrayList<ModelVendaProdutoTickets2>listaModelVendaProdutoTickets= new ArrayList<>();
    
    pagamento = "Ticket"; 
    try {
    modelVendaTickets = new ModelVendaTickets2();
    modelVendaProdutoTickets = new ModelVendaProdutoTickets2();
    listaModelVendaProdutoTickets = new ArrayList<>();
    int percorrertabela, codigoVenda;
    if(tfIdCliente.getText().equals("id Cliente")){
    tfIdCliente.setText(String.valueOf(3));   
    }
    modelVendaTickets.setCliente(Integer.parseInt(tfIdCliente.getText()));    
    //modelVendaTickets.setCliente(Integer.parseInt(tfUsuarioID.getText()));
    modelVendaTickets.setData(tfData.getText());
    modelVendaTickets.setTurno(Integer.parseInt(tfTurno.getText()));
    modelVendaTickets.setIdVenda(Integer.parseInt(tfVenda.getText())); 
    modelVendaTickets.setHora(tfHora.getText());
    modelVendaTickets.setData_relatorio(tfMes.getText());
    modelVendaTickets.setDataAnual(tfAno.getText()); 
    modelVendaTickets.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
    modelVendaTickets.setValorBruto(Double.parseDouble(tfValorBruto.getText()));
    modelVendaTickets.setValorDesconto(Double.parseDouble(String.valueOf(viewPagamentoPDVTickets.getDesconto())));
    modelVendaTickets.setValorLiquido(Double.parseDouble(String.valueOf(viewPagamentoPDVTickets.getValorTotal())));
    codigoVenda = controllervendaTickets.salvarvendaController(modelVendaTickets);
    if(codigoVenda>0){   
    }  
    percorrertabela = tabela.getRowCount();
    for (int i = 0; i < percorrertabela; i++) {
    codigoProduto=(int) tabela.getValueAt(i, 1);        
    modelVendaProdutoTickets = new ModelVendaProdutoTickets2();
    modelProduto = new ModelProduto();
    modelVendaProdutoTickets.setProduto(codigoProduto);
    modelVendaProdutoTickets.setVenda(Integer.parseInt(tfVenda.getText())); 
    modelVendaProdutoTickets.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
    modelVendaProdutoTickets.setValorVenda(Double.parseDouble(String.valueOf(tabela.getValueAt(i, 4))));
    modelVendaProdutoTickets.setNome_produto(String.valueOf(tabela.getValueAt(i, 2)));
    modelVendaProdutoTickets.setQuantidade(Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));
    modelVendaProdutoTickets.setAno(tfAno.getText());
    modelVendaProdutoTickets.setMes(tfMes.getText());
    //baixa no estoque 
    modelProduto.setId(codigoProduto); 
    modelProduto.setEstoque(controllerProduto.retornarController(codigoProduto).getEstoque()
    -Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));      
    listaModelVendaProdutoTickets.add(modelVendaProdutoTickets);
    listaModelProduto.add(modelProduto);     
    } 
    controllerVendaProdutoTickets.salvarVendaProdutoController(listaModelVendaProdutoTickets); 
    } catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Erro ao salvar venda no cartão no servidor");
    }
    }
  
  private void salvarVendaCartao(){
     pagamento = "Cartao de credito";
     try { 
     modelVendaCartao = new ModelVendaCartao();
     modelVendaProdutoCartao = new ModelVendaProdutoCartao();
     listaModelVendaProdutoCartao = new ArrayList<>();
     int percorrertabela, codigoVenda;
     if(tfIdCliente.getText().equals("id Cliente")){
     tfIdCliente.setText("3");   
     }
     modelVendaCartao.setCliente(Integer.parseInt(tfIdCliente.getText()));    
      
   //modelVendaCartao.setCliente(Integer.parseInt(tfUsuarioID.getText()));
     modelVendaCartao.setData(tfData.getText()); 
     modelVendaCartao.setIdVenda(Integer.parseInt(tfVenda.getText())); 
     modelVendaCartao.setHora(tfHora.getText());    
     modelVendaCartao.setData_relatorio(tfMes.getText());
     modelVendaCartao.setDataAnual(tfAno.getText());
     modelVendaCartao.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVendaCartao.setValorBruto(Double.parseDouble(tfValorBruto.getText()));
     modelVendaCartao.setValorDesconto(Double.parseDouble(String.valueOf(viewPagamentoPDVCartaoNormal.getDesconto())));
     modelVendaCartao.setValorLiquido(Double.parseDouble(String.valueOf(viewPagamentoPDVCartaoNormal.getValorTotal())));
     modelVendaCartao.setValorDesconto(Double.parseDouble(String.valueOf(viewPagamentoPDVCartaoNormal.getDesconto())));
     codigoVenda = controllervendaCartao.salvarvendaController(modelVendaCartao);
     if(codigoVenda>0){   
     }    
     percorrertabela = tabela.getRowCount(); 
     for (int i = 0; i < percorrertabela; i++) {
     codigoProduto=(int) tabela.getValueAt(i, 1);           
     modelVendaProdutoCartao = new ModelVendaProdutoCartao();
     modelProduto = new ModelProduto();
     modelVendaProdutoCartao.setProduto(codigoProduto);
     modelVendaProdutoCartao.setVenda(Integer.parseInt(tfVenda.getText()));
     modelVendaProdutoCartao.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVendaProdutoCartao.setValorVenda(Double.parseDouble(String.valueOf(tabela.getValueAt(i, 4))));
     modelVendaProdutoCartao.setQuantidade(Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));
     modelVendaProdutoCartao.setNome_produto(String.valueOf(tabela.getValueAt(i, 2)));    
     modelVendaProdutoCartao.setMes(tfMes.getText());
     modelVendaProdutoCartao.setAno(tfAno.getText());   
     //baixa no estoque  
     modelProduto.setId(codigoProduto);
     modelProduto.setEstoque(controllerProduto.retornarController(codigoProduto).getEstoque()
     -Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));    
     listaModelVendaProdutoCartao.add(modelVendaProdutoCartao);
     listaModelProduto.add(modelProduto);     
     }
     if(controllerVendaProdutoCartao.salvarVendaProdutoController(listaModelVendaProdutoCartao)){
     //alterando estoque estiva (produto)
     controllerProduto.alterarEstoqueController(listaModelProduto);
     geraId();
     DaoVenda2.numeros();
     JOptionPane.showMessageDialog(null, "Venda no cartão de crédito finalizada!");
     int resposta =   JOptionPane.showConfirmDialog(this, "Deseja imprimir cupom?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
     if(resposta==JOptionPane.YES_OPTION){
     imprimirCupomCartao(listaModelVendaProdutoCartao, modelVendaCartao);
     int codCli = Integer.parseInt(tfUsuarioID.getText());      
     limparFormulario(); 
     }else if(resposta==JOptionPane.NO_OPTION){
     int codCli = Integer.parseInt(tfUsuarioID.getText());      
     limparFormulario();       
     }else if(resposta==JOptionPane.CLOSED_OPTION){
     int codCli = Integer.parseInt(tfUsuarioID.getText());      
     limparFormulario();    
     }
     
     }
        
      } catch (Exception e) {
        }
    }
  
    private void salvarVendaCartaoNoServidor(){
    ModelVendaCartao2 modelVendaCartao = new ModelVendaCartao2();
    ControllervendaCartao2 controllervendaCartao = new ControllervendaCartao2();
    ArrayList<ModelVendaCartao2> listaModelVendaCartao = new ArrayList<>();
    
    ModelVendaProdutoCartao2 modelVendaProdutoCartao = new ModelVendaProdutoCartao2();
    ControllerVendaProdutoCartao2 controllerVendaProdutoCartao = new ControllerVendaProdutoCartao2();
    ArrayList<ModelVendaProdutoCartao2> listaModelVendaProdutoCartao = new ArrayList<>();
    
    pagamento = "Cartao de credito";
    try { 
    modelVendaCartao = new ModelVendaCartao2();
    modelVendaProdutoCartao = new ModelVendaProdutoCartao2();
    listaModelVendaProdutoCartao = new ArrayList<>();
    int percorrertabela, codigoVenda;
    if(tfIdCliente.getText().equals("id Cliente")){
    tfIdCliente.setText("3");   
    }
    modelVendaCartao.setCliente(Integer.parseInt(tfIdCliente.getText()));         
    //modelVendaCartao.setCliente(Integer.parseInt(tfUsuarioID.getText()));
    modelVendaCartao.setData(tfData.getText()); 
    modelVendaCartao.setTurno(Integer.parseInt(tfTurno.getText())); 
    modelVendaCartao.setIdVenda(Integer.parseInt(tfVenda.getText())); 
    modelVendaCartao.setHora(tfHora.getText());    
    modelVendaCartao.setData_relatorio(tfMes.getText());
    modelVendaCartao.setDataAnual(tfAno.getText());
    modelVendaCartao.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
    modelVendaCartao.setValorBruto(Double.parseDouble(tfValorBruto.getText()));
    modelVendaCartao.setValorDesconto(Double.parseDouble(String.valueOf(viewPagamentoPDVCartaoNormal.getDesconto())));
    modelVendaCartao.setValorLiquido(Double.parseDouble(String.valueOf(viewPagamentoPDVCartaoNormal.getValorTotal())));
    modelVendaCartao.setValorDesconto(Double.parseDouble(String.valueOf(viewPagamentoPDVCartaoNormal.getDesconto())));
    codigoVenda = controllervendaCartao.salvarvendaController(modelVendaCartao);
    if(codigoVenda>0){
    }    
    percorrertabela = tabela.getRowCount(); 
    for (int i = 0; i < percorrertabela; i++) {
    codigoProduto=(int) tabela.getValueAt(i, 1);           
    modelVendaProdutoCartao = new ModelVendaProdutoCartao2();
    modelProduto = new ModelProduto();
    modelVendaProdutoCartao.setProduto(codigoProduto);
    modelVendaProdutoCartao.setVenda(Integer.parseInt(tfVenda.getText()));
    modelVendaProdutoCartao.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
    modelVendaProdutoCartao.setValorVenda(Double.parseDouble(String.valueOf(tabela.getValueAt(i, 4))));
    modelVendaProdutoCartao.setQuantidade(Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));
    modelVendaProdutoCartao.setNome_produto(String.valueOf(tabela.getValueAt(i, 2)));  
    modelVendaProdutoCartao.setMes(tfMes.getText());
    modelVendaProdutoCartao.setAno(tfAno.getText());   
    //baixa no estoque 
    modelProduto.setId(codigoProduto);
    modelProduto.setEstoque(controllerProduto.retornarController(codigoProduto).getEstoque()
    -Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));     
    listaModelVendaProdutoCartao.add(modelVendaProdutoCartao);
    listaModelProduto.add(modelProduto);     
    }
    controllerVendaProdutoCartao.salvarVendaProdutoController(listaModelVendaProdutoCartao);       
    } catch (Exception e) {
    }
    }
  
     private void salvarVendaCartaoComDinheiro(){
     pagamento = "Cartao de credito";
     try { 
     modelVendaCartao = new ModelVendaCartao();
     modelVendaProdutoCartao = new ModelVendaProdutoCartao();
     listaModelVendaProdutoCartao = new ArrayList<>();
     int percorrertabela, codigoVenda;
     if(tfIdCliente.getText().equals("id Cliente")){
     tfIdCliente.setText("3");   
     }
     modelVendaCartao.setCliente(Integer.parseInt(tfIdCliente.getText()));    
        
   //modelVendaCartao.setCliente(Integer.parseInt(tfUsuarioID.getText()));
     modelVendaCartao.setData(tfData.getText()); 
     modelVendaCartao.setIdVenda(Integer.parseInt(tfVenda.getText())); 
     modelVendaCartao.setHora(tfHora.getText());    
     modelVendaCartao.setData_relatorio(tfMes.getText());
     modelVendaCartao.setDataAnual(tfAno.getText());
     modelVendaCartao.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVendaCartao.setValorBruto(Double.parseDouble(tfValorBruto.getText()));
     modelVendaCartao.setValorDesconto(Double.parseDouble(String.valueOf(viewPagamentoPDVCartao.getDesconto())));
     modelVendaCartao.setValorLiquido(Double.parseDouble(String.valueOf(viewPagamentoPDVCartao.getValorTotal())));
     modelVendaCartao.setValorDesconto(Double.parseDouble(String.valueOf(viewPagamentoPDVCartao.getDesconto())));

     codigoVenda = controllervendaCartao.salvarvendaController(modelVendaCartao);
     if(codigoVenda>0){
    
     }
     
     percorrertabela = tabela.getRowCount(); 
     for (int i = 0; i < percorrertabela; i++) {
     codigoProduto=(int) tabela.getValueAt(i, 1);
            
     modelVendaProdutoCartao = new ModelVendaProdutoCartao();
     modelProduto = new ModelProduto();
     modelVendaProdutoCartao.setProduto(codigoProduto);
     modelVendaProdutoCartao.setVenda(Integer.parseInt(tfVenda.getText()));
     modelVendaProdutoCartao.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVendaProdutoCartao.setValorVenda(Double.parseDouble(String.valueOf(tabela.getValueAt(i, 4))));
     modelVendaProdutoCartao.setQuantidade(Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));
     modelVendaProdutoCartao.setNome_produto(String.valueOf(tabela.getValueAt(i, 2)));
     
     modelVendaProdutoCartao.setMes(tfMes.getText());
     modelVendaProdutoCartao.setAno(tfAno.getText());
     
     //baixa no estoque 
     modelProduto.setId(codigoProduto);
     modelProduto.setEstoque(controllerProduto.retornarController(codigoProduto).getEstoque()
     -Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));
       
     listaModelVendaProdutoCartao.add(modelVendaProdutoCartao);
     listaModelProduto.add(modelProduto);     
     }
     if(controllerVendaProdutoCartao.salvarVendaProdutoController(listaModelVendaProdutoCartao)){
     //alterando estoque estiva (produto)
     controllerProduto.alterarEstoqueController(listaModelProduto);
     JOptionPane.showMessageDialog(null, "Venda no dinheiro finalizada!");
     geraId();
     DaoVenda2.numeros(); 
     int resposta =   JOptionPane.showConfirmDialog(this, "Deseja imprimir cupom?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
     if(resposta==JOptionPane.YES_OPTION){
     imprimirCupomCartao(listaModelVendaProdutoCartao, modelVendaCartao);
     int codCli = Integer.parseInt(tfUsuarioID.getText());      
     limparFormulario(); 
     }else if(resposta==JOptionPane.NO_OPTION){
     int codCli = Integer.parseInt(tfUsuarioID.getText());      
     limparFormulario();       
     }else if(resposta==JOptionPane.CLOSED_OPTION){
     int codCli = Integer.parseInt(tfUsuarioID.getText());      
     limparFormulario();    
     }
     
     }
        
      } catch (Exception e) {
        }
    }
  
  
  private void salvarVendaDinheiroParcela(){
    pagamento = "A prazo";
     try {       
     modelVenda = new ModelVenda();
     modelVendaProduto = new ModelVendaProduto();
     listaModelVendaProduto = new ArrayList<>();
     int percorrertabela, codigoVenda, codCliente;   
     modelVenda.setCliente(Integer.parseInt(tfIdCliente.getText()));      
   //modelVenda.setCliente(Integer.parseInt(tfUsuarioID.getText()));
     modelVenda.setData(tfData.getText());
     modelVenda.setIdVenda(Integer.parseInt(tfVenda.getText()));
     modelVenda.setHora(tfHora.getText());
     modelVenda.setAno(tfAno.getText()); 
     modelVenda.setData_relatorio(tfMes.getText()); 
     modelVenda.setPagamento((String) viewPagamentopParcelaPDV.jcbPagamento.getSelectedItem()); 
     modelVenda.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVenda.setValorBruto(Double.parseDouble(tfValorBruto.getText()));
     modelVenda.setValorDesconto(Double.parseDouble(String.valueOf(viewPagamentopParcelaPDV.getDesconto())));
     modelVenda.setValorLiquido(Double.parseDouble(String.valueOf(viewPagamentopParcelaPDV.getValorTotal())));
     codigoVenda = controller.salvarvendaController(modelVenda);
     if(codigoVenda>0){
     }
        
     percorrertabela = tabela.getRowCount();
     
     for (int i = 0; i < percorrertabela; i++) {
     codigoProduto=(int) tabela.getValueAt(i, 1);
            
     modelVendaProduto = new ModelVendaProduto();
     modelProduto = new ModelProduto();
     modelVendaProduto.setProduto(codigoProduto);
     modelVendaProduto.setVenda(Integer.parseInt(tfVenda.getText()));
     modelVendaProduto.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVendaProduto.setValorVenda(Double.parseDouble(String.valueOf(tabela.getValueAt(i, 4))));
     modelVendaProduto.setQuantidade(Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));
     modelVendaProduto.setNome_produto(String.valueOf(tabela.getValueAt(i, 2)));
     modelVendaProduto.setAno(tfAno.getText()); 
     modelVendaProduto.setMesAno(tfMes.getText()); 
     //baixa no estoque 
     modelProduto.setId(codigoProduto);
     modelProduto.setEstoque(controllerProduto.retornarController(codigoProduto).getEstoque()
     -Integer.parseInt(String.valueOf(tabela.getValueAt(i, 1))));
       
     listaModelVendaProduto.add(modelVendaProduto);
     listaModelProduto.add(modelProduto);     
        }         
     if(controllerVendaProduto.salvarVendaProdutoController(listaModelVendaProduto)){
     //alterando estoque estiva (produto)  
     controllerProduto.alterarEstoqueController(listaModelProduto);
     JOptionPane.showMessageDialog(null, "Venda a prazo finalizada!"); 
     geraId();
     DaoVenda2.numeros();
    
     int codCli = Integer.parseInt(tfUsuarioID.getText());
     JOptionPane.showMessageDialog(null, "Cupom para o operador");
     imprimirCupomParcelamento(listaModelVendaProduto, modelVenda);
     JOptionPane.showMessageDialog(null, "Cupom para o cliente");
     imprimirCupomParcelamento(listaModelVendaProduto, modelVenda);
     limparFormulario();
     /*
     if(viewPagamentopParcelaPDV.jcbPagamento.getSelectedItem().equals("A prazo")){      
    
     ViewParcelamento p = new ViewParcelamento(Integer.parseInt(tfVenda.getText()), codCli, tfUsuarioID.getText(), tfCpfCliente.getText());     
     p.setVisible(true); 
     p.setLocationRelativeTo(null);     
     dispose();          
     }     
     */
     }else{
     JOptionPane.showMessageDialog(null, "erro");
     }
    
     } catch (Exception e) {
     } 
     } 
  
     private void salvarVendaDinheiroParcelaNoServidor(){
     ModelVenda2 modelVenda = new ModelVenda2();
     Controllervenda2 controller = new Controllervenda2();
     ArrayList<ModelVenda2> listaModelVenda = new ArrayList<>();    
     ModelVendaProduto2 modelVendaProduto = new ModelVendaProduto2();
     ControllerVendaProduto2 controllerVendaProduto = new ControllerVendaProduto2();
     ArrayList<ModelVendaProduto2> listaModelVendaProduto = new ArrayList<>();
     pagamento = "A prazo";
     try {       
     modelVenda = new ModelVenda2();
     modelVendaProduto = new ModelVendaProduto2();
     listaModelVendaProduto = new ArrayList<>();
     int percorrertabela, codigoVenda, codCliente;   
     modelVenda.setCliente(Integer.parseInt(tfIdCliente.getText()));      
   //modelVenda.setCliente(Integer.parseInt(tfUsuarioID.getText()));
     modelVenda.setData(tfData.getText());
     modelVenda.setTurno(Integer.parseInt(tfTurno.getText()));
     modelVenda.setIdVenda(Integer.parseInt(tfVenda.getText()));
     modelVenda.setHora(tfHora.getText());
     modelVenda.setdataAnual(tfAno.getText()); 
     modelVenda.setData_relatorio(tfMes.getText()); 
     modelVenda.setPagamento((String) viewPagamentopParcelaPDV.jcbPagamento.getSelectedItem()); 
     modelVenda.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVenda.setValorBruto(Double.parseDouble(tfValorBruto.getText()));
     modelVenda.setValorDesconto(Double.parseDouble(String.valueOf(viewPagamentopParcelaPDV.getDesconto())));
     modelVenda.setValorLiquido(Double.parseDouble(String.valueOf(viewPagamentopParcelaPDV.getValorTotal())));
     codigoVenda = controller.salvarvendaController(modelVenda);
     if(codigoVenda>0){
     }     
     percorrertabela = tabela.getRowCount();   
     for (int i = 0; i < percorrertabela; i++) {
     codigoProduto=(int) tabela.getValueAt(i, 1);          
     modelVendaProduto = new ModelVendaProduto2();
     modelProduto = new ModelProduto();
     modelVendaProduto.setProduto(codigoProduto);
     modelVendaProduto.setVenda(Integer.parseInt(tfVenda.getText()));
     modelVendaProduto.setUsuario(Integer.parseInt(tfUsuarioID.getText()));
     modelVendaProduto.setValorVenda(Double.parseDouble(String.valueOf(tabela.getValueAt(i, 4))));
     modelVendaProduto.setQuantidade(Integer.parseInt(String.valueOf(tabela.getValueAt(i, 3))));
     modelVendaProduto.setNom_produto(String.valueOf(tabela.getValueAt(i, 2)));
     modelVendaProduto.setAno(tfAno.getText()); 
     modelVendaProduto.setMesAno(tfMes.getText()); 
     //baixa no estoque 
     modelProduto.setId(codigoProduto);
     modelProduto.setEstoque(controllerProduto.retornarController(codigoProduto).getEstoque()
     -Integer.parseInt(String.valueOf(tabela.getValueAt(i, 1))));     
     listaModelVendaProduto.add(modelVendaProduto);
     listaModelProduto.add(modelProduto);     
     }         
     controllerVendaProduto.salvarVendaProdutoController(listaModelVendaProduto);  
     } catch (Exception e) {
     } 
     } 
  
  private void imprimirCupom(ArrayList<ModelVendaProduto> listaModelVendaProduto, ModelVenda modelVenda){
        String conteudoImprimir = "";
        for (int x = 0; x < listaModelVendaProduto.size(); x++) {
          conteudoImprimir  += listaModelVendaProduto.get(x).getProduto()  + "    "+
          listaModelVendaProduto.get(x).getQuantidade() +"   "+
          listaModelVendaProduto.get(x).getValorVenda() +"    "+
          listaModelVendaProduto.get(x).getNome_produto() +"\n\r";
   
        }  
        this.imprimir("     \n\r" 
        + "==========================================\n\r"    
        + "               JOAO PAULO I               \n\r"    
        + "         CNPJ: 26.635.415/0001-25         \n\r" 
        + "==========================================\n\r"
        + "             CUPOM NAO FISCAL             \n\r"
        + "------------------------------------------\n\r"  
        + "COD  QT  PRECO  DESCRICAO                 \n\r"
        + conteudoImprimir + ""
        + "------------------------------------------\n\r"
        + "Valor Bruto: "+ modelVenda.getValorBruto()+"\n\r" 
        
        + "Desconto: "+ modelVenda.getValorDesconto()+"\n\r"
        + "Valor Total: "+ modelVenda.getValorLiquido()+"\n\r"   
        + "------------------------------------------\n\r"
        + "Forma do Pagamento: "+pagamento+"\n\r"      
        + "Vendedor: "+tfusuarionome.getText()+"\n\r"
        + ""+tfData.getText()+" - "+tfHora.getText()+"\n\r"
        + "------------------------------------------\n\r"
        +"\n\r \n\r \f" 
        + " OBRIGADO PELA PREFERENCIA VOLTE SEMPRE! \n\r"
        + "            JESUS ESTA VOLTANDO!         \n\r"
        +"\n\r \n\r \f"   
        + "");
        
      }
     
     
     private void imprimirCupomParcelamento(ArrayList<ModelVendaProduto> listaModelVendaProduto, ModelVenda modelVenda){
        String conteudoImprimir = "";
        for (int x = 0; x < listaModelVendaProduto.size(); x++) {
          conteudoImprimir  += listaModelVendaProduto.get(x).getProduto()  + "    "+
          listaModelVendaProduto.get(x).getQuantidade() +"   "+
          listaModelVendaProduto.get(x).getValorVenda() +"    "+
          listaModelVendaProduto.get(x).getNome_produto() +"\n\r";
   
        }
        String Parcelada="A Prazo";
        this.imprimir("     \n\r" 
        + "==========================================\n\r"    
        + "               JOAO PAULO I               \n\r"    
        + "         CNPJ: 26.635.415/0001-25         \n\r"   
        + "==========================================\n\r"
        + "             CUPOM NAO FISCAL             \n\r"
        + "------------------------------------------\n\r"  
        + "COD  QT  PRECO  DESCRICAO                 \n\r"
        + conteudoImprimir + ""
        + "------------------------------------------\n\r"
        + "Valor Bruto: "+ modelVenda.getValorBruto()+"\n\r" 
         
        + "Desconto: "+ modelVenda.getValorDesconto()+"\n\r"
        + "Valor Total: "+ modelVenda.getValorLiquido()+"\n\r"
        + "------------------------------------------\n\r"
        + "Forma do Pagamento: "+Parcelada+""+"\n\r"       
        + "Vendedor: "+tfusuarionome.getText()+"\n\r"  
        + "Cliente: "+tfNomeCliente.getText()+"\n\r"
        + "CPF: "+tfCpfCliente.getText()+"\n\r" 
        + ""+tfData.getText()+" - "+tfHora.getText()+"\n\r" 
        + "------------------------------------------\n\r"
        +"\n\r \n\r \f" 
        + " OBRIGADO PELA PREFERENCIA VOLTE SEMPRE! \n\r"
        + "            JESUS ESTA VOLTANDO!         \n\r"
        +"\n\r \n\r \f" 
        + ""); 
           
      }
     
      
     private void imprimirCupomTickets(ArrayList<ModelVendaProdutoTickets> listaModelVendaProduto, ModelVendaTickets modelVenda){
        String conteudoImprimir = "";
        for (int x = 0; x < listaModelVendaProduto.size(); x++) {
          conteudoImprimir  += listaModelVendaProduto.get(x).getProduto()  + "    "+
          listaModelVendaProduto.get(x).getQuantidade() +"   "+
          listaModelVendaProduto.get(x).getValorVenda() +"    "+
          listaModelVendaProduto.get(x).getNome_produto() +"\n\r";
   
        }
        this.imprimir("     \n\r" 
        + "==========================================\n\r"    
        + "               JOAO PAULO I               \n\r"    
        + "         CNPJ: 26.635.415/0001-25         \n\r" 
        + "==========================================\n\r"
        + "             CUPOM NAO FISCAL             \n\r"
        + "------------------------------------------\n\r"  
        + "COD  QT  PRECO  DESCRICAO                 \n\r"
        + conteudoImprimir + ""
        + "------------------------------------------\n\r"
        + "Valor Bruto: "+ modelVenda.getValorBruto()+"\n\r" 
        
        + "Desconto: "+ modelVenda.getValorDesconto()+"\n\r"
        + "Valor Total: "+ modelVenda.getValorLiquido()+"\n\r"
        + "------------------------------------------\n\r"
        + "Forma do Pagamento: "+pagamento+"\n\r"      
        + "Vendedor: "+tfUsuarioID.getText()+"\n\r"
        + ""+tfData.getText()+" - "+tfHora.getText()+"\n\r"
        + "------------------------------------------\n\r"
        +"\n\r \n\r \f" 
        + " OBRIGADO PELA PREFERENCIA VOLTE SEMPRE! \n\r"
        + "            JESUS ESTA VOLTANDO!         \n\r"
        +"\n\r \n\r \f"
        + "");
        
      }
     
       private void imprimirCupomCartao(ArrayList<ModelVendaProdutoCartao> listaModelVendaProduto, ModelVendaCartao modelVenda){
        String conteudoImprimir = "";
        for (int x = 0; x < listaModelVendaProduto.size(); x++) {
          conteudoImprimir  += listaModelVendaProduto.get(x).getProduto()  + "    "+
          listaModelVendaProduto.get(x).getQuantidade() +"   "+
          listaModelVendaProduto.get(x).getValorVenda() +"    "+
          listaModelVendaProduto.get(x).getNome_produto() +"\n\r";
   
        }  
        this.imprimir("     \n\r" 
        + "==========================================\n\r"    
        + "               JOAO PAULO I               \n\r"    
        + "         CNPJ: 26.635.415/0001-25         \n\r" 
        + "==========================================\n\r"
        + "             CUPOM NAO FISCAL             \n\r"
        + "------------------------------------------\n\r"  
        + "COD  QT  PRECO  DESCRICAO                 \n\r"
        + conteudoImprimir + ""
        + "------------------------------------------\n\r"
        + "Valor Bruto: "+ modelVenda.getValorBruto()+"\n\r" 
        
        + "Desconto: "+ modelVenda.getValorDesconto()+"\n\r"
        + "Valor Total: "+ modelVenda.getValorLiquido()+"\n\r"
        + "------------------------------------------\n\r"
        + "Forma do Pagamento: "+pagamento+"\n\r"      
        + "Vendedor: "+tfUsuarioID.getText()+"\n\r"
        + ""+tfData.getText()+" - "+tfHora.getText()+"\n\r"
        + "------------------------------------------\n\r"
        +"\n\r \n\r \f" 
        + " OBRIGADO PELA PREFERENCIA VOLTE SEMPRE! \n\r"
        + "            JESUS ESTA VOLTANDO!         \n\r"
        +"\n\r \n\r \f"
        + "");
        
      }
      public void imprimir(String pTexto){
        try {
            InputStream prin = new ByteArrayInputStream(pTexto.getBytes());
            DocFlavor doqFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            SimpleDoc documentoTexto = new SimpleDoc(prin, doqFlavor, null);
            PrintService impressora = PrintServiceLookup.lookupDefaultPrintService();
            //pega a impressora padrao
            PrintRequestAttributeSet printAtribut = new HashPrintRequestAttributeSet();
            printAtribut.add(new JobName("Impressao", null));
            printAtribut.add(OrientationRequested.PORTRAIT);
            printAtribut.add(MediaSizeName.ISO_A4);
            
            //informe o tipo da folha
            DocPrintJob printJob = impressora.createPrintJob();
            try {
            printJob.print(documentoTexto, (PrintRequestAttributeSet) printAtribut);
            //tenta imprimir
            } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"Nao foi possivel realizar essa impressao!!!");
            }
            prin.close();
        } catch (Exception e) {
        }
    }
      private void geraId(){
        ModelId modelId = new ModelId();
        ControllerId controllerId = new ControllerId();
        
        modelId.setNumero(0);
        if(controllerId.salvarController(modelId)>0){
            
        }
           
        
    }
    public void produtosKL(){     
    try {        
    int codProduto = 7;
    Double valorProduto = Double.parseDouble(formatador.converterPontos(JOptionPane.showInputDialog("Informe o valor desse produto produto")));  
    formatador.arredondamentoValoresDouble(valorProduto);
   //String valorProduto = JOptionPane.showInputDialog(this, "Informe o valor desse produto produto", "CONFIRME", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
    int c = JOptionPane.showConfirmDialog(this, "Está certo?", "CONFIRME", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
    if(c == JOptionPane.NO_OPTION){
    JOptionPane.showMessageDialog(null, "Operação cancelada!");   
    }else if(c == JOptionPane.YES_OPTION){  
    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel(); 
    modelProduto = controllerProduto.retornarController(codProduto);
    codigoProduto = codProduto;
    modelo.addRow(new Object []{
    modelo.getRowCount()+1,     
    codigoProduto,
    modelProduto.getNome(),  
    quantidade,
    formatador.arredondamentoValoresDouble(valorProduto),
    formatador.arredondamentoValoresDouble(valorProduto * quantidade)
    });  
    tfNomeProduto.setText(" "+quantidade+"x  "+modelProduto.getNome()+" = "+formatador.arredondamentoValoresDouble(valorProduto * quantidade)+"");      
    quantidade = 1;
    tfValorBruto.setText(formatador.converterPontos(df.format(somarValor()))+"");          
    }     
    } catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Você só pode inserir número!"); 
    }
    }
    }      
