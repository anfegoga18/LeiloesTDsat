
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author Andrés Felipe González García
 */
public class vendasVIEW extends javax.swing.JFrame {
    
    listagemVIEW listagemView;
    ProdutosDAO produtosDAO = new ProdutosDAO();
    
    public vendasVIEW() {
        initComponents();
    }
    
    public vendasVIEW(listagemVIEW listagem) {
        initComponents();
        listagem = this.listagemView;
        listarProdutos(produtosDAO.listarProdutosVendidos());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pnl_vendas = new javax.swing.JPanel();
        Lbl_listaVendidos = new javax.swing.JLabel();
        scrollVendidos = new javax.swing.JScrollPane();
        Tbl_produtosVendidos = new javax.swing.JTable();
        Btn_voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listagem Vendas");

        Lbl_listaVendidos.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        Lbl_listaVendidos.setText("Lista de produtos vendidos");

        Tbl_produtosVendidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Valor", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollVendidos.setViewportView(Tbl_produtosVendidos);

        Btn_voltar.setText("Voltar");
        Btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_voltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Pnl_vendasLayout = new javax.swing.GroupLayout(Pnl_vendas);
        Pnl_vendas.setLayout(Pnl_vendasLayout);
        Pnl_vendasLayout.setHorizontalGroup(
            Pnl_vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pnl_vendasLayout.createSequentialGroup()
                .addGroup(Pnl_vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Pnl_vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Btn_voltar)
                        .addGroup(Pnl_vendasLayout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(scrollVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Pnl_vendasLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(Lbl_listaVendidos)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        Pnl_vendasLayout.setVerticalGroup(
            Pnl_vendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pnl_vendasLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(Lbl_listaVendidos)
                .addGap(18, 18, 18)
                .addComponent(scrollVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Btn_voltar)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pnl_vendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Pnl_vendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_voltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_Btn_voltarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_voltar;
    private javax.swing.JLabel Lbl_listaVendidos;
    private javax.swing.JPanel Pnl_vendas;
    private javax.swing.JTable Tbl_produtosVendidos;
    private javax.swing.JScrollPane scrollVendidos;
    // End of variables declaration//GEN-END:variables

//Métodos implementados na interface vendasVIEW

    private void listarProdutos(List<ProdutosDTO> lista){
        try {
            String [] columns = {"Id","Nome","Valor","Status"};
        
            if (produtosDAO.contarProdutos()){//Verificamos primeiro se a lista já tem elementos, pois se estiver vazia não precisa atualizar

                DefaultTableModel tablaModelo = new DefaultTableModel(columns, 0); //A cada atualização é criado o modelo novamente

                Tbl_produtosVendidos.setRowSorter(new TableRowSorter(tablaModelo));// Para conseguir por ordem crescente ou decrescente na tabela

                for(ProdutosDTO product : lista){
                //Cria-se um vetor e adiciona-se os produtos para depois adicioná-los à tabela 
                    Object [] vetorProdutos = new Object[] {
                        product.getId(),
                        product.getNome(),
                        product.getValor(),
                        product.getStatus()
                    };
                tablaModelo.addRow(vetorProdutos);
                }
                Tbl_produtosVendidos.setModel(tablaModelo);
            }else{//Se a lista de consultas está vazia
                DefaultTableModel tablaModelo = new DefaultTableModel( columns, 0);
                Tbl_produtosVendidos.setModel(tablaModelo);
                JOptionPane.showMessageDialog(null, "Não tem produtos para serem listados");
            }
        } catch (Exception e) {
        }
    
    }
}
